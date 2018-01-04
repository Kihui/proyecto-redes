package redes;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * <p>Clase que permite a un pokentrenador capturar pokemones</p>
 */
public class Pokentrenador {

    private Estado actual;
    private Socket socket;
    private OutputStream out;
    private InputStream in;
    private Scanner scanner;
    private boolean continua;
    private FabricaMensaje fabrica;
    private Imagen imagen;
    private boolean leer;
    
    /**
     * Constructor que recibe la dirección IP del servidor
     * y el puerto al cual conectarse.
     * @param ip dirección IP del servidor
     * @param puerto el puerto al que se hará la conexión
     */
    public Pokentrenador(String ip, int puerto) {
        try {
            socket = new Socket(ip, puerto);
            out = socket.getOutputStream();
            in = socket.getInputStream();
            scanner = new Scanner(System.in);
        } catch(Exception e){
             // e.printStackTrace();
            error("Error al intentar conectarse al servidor.", true);
        }
        continua = true;
        actual = Estado.Q0;
        fabrica = new FabricaMensaje();
        imagen = new Imagen();
        leer = true;
    }

    /* Imprime el error 'mensaje'. Si salir es true, se sale del programa */
    private void error(String mensaje, boolean salir) {
        System.err.println(mensaje);
        if(salir)
            System.exit(1);
    }

    /**
     * Se comienza el intercambio de mensajes entre cliente y servidor.
     */
    public void empezar() {
        MensajeGenerico respuesta = null;
        Estado anterior = actual;
        while(continua) {
            anterior = actual;
            byte[] mensaje = siguienteEstado(respuesta);
            if(anterior != Estado.Q6 && anterior != Estado.Q1)
                respuesta = mandaMensaje(mensaje);
        }
    }

    /* Estado Q0: "Conexión establecida, inicio de aplicación" */
    private byte[] estadoQ0(MensajeGenerico mensaje) {
        if(mensaje != null && mensaje.getCodigo() == 44)
            System.out.println("Nombre de usuario no identificado.");
        boolean b = true;
        byte[] respuesta = null;
        while(b) {
            try {
                System.out.println("Selecciona una opción");
                System.out.println("1. Iniciar sesión");
                System.out.println("2. Cerrar conexión");
                int op = scanner.nextInt();
                scanner.nextLine();
                if(op == 1) {
                    leer = true;
                    System.out.println("Ingresa tu nombre de usuario");
                    String usuario = scanner.nextLine().replace("\n", "");
                    if(!usuario.isEmpty()) {
                        respuesta = fabrica.creaMensaje(10, usuario);
                        actual = Estado.Q1;
                        b = false;
                    } else System.out.println("Nombre de usuario inválido.");
                } else if(op == 2) {
                    respuesta = fabrica.creaMensaje(0);
                    actual = Estado.Q9;
                    b = false;
                    leer = false;
                } else System.out.println("Opción inválida.");
            } catch (Exception e) {
                scanner.next();
                System.out.println("Error al leer entrada.");                
            }
        }
        return respuesta;
    }

    /* Estado Q1: "Inicio de sesión" */
    private byte[] estadoQ1(MensajeGenerico mensaje) {
        if(mensaje.getCodigo() == 20)
            actual = Estado.Q2;
        else if(mensaje.getCodigo() == 44)
            actual = Estado.Q0;
        return null;
    }
    
    /* Estado Q2: "Menú de juego" */
    private byte[] estadoQ2(MensajeGenerico mensaje) {
        byte[] respuesta = null;
        if(mensaje != null) {
            if(mensaje.getCodigo() == 20)
                System.out.println("Inicio de sesión exitoso");
            else if(mensaje.getCodigo() == 44)
                System.out.println("Pokémon no encontrado");
            else if(mensaje.getCodigo() == 41)
                System.out.println("!El pokémon se escapó!");
            else if(mensaje.getCodigo() == 24) {            
                System.out.println(String.format("%s fue capturado", ((Mensaje24)mensaje).getNombre()));
                imagen.mostrarImagen(((Mensaje24)mensaje).getImagen());
            } else if(mensaje.getCodigo() == 21) {
                System.out.println(String.format("%s encontrado", ((Mensaje21)mensaje).getNombre()));
                imagen.mostrarImagen(((Mensaje21)mensaje).getImagen());
            }
        }
        boolean b = true;
        while(b) {
            try {
                System.out.println("Selecciona una opción");
                System.out.println("1. Utilizar pokedex");
                System.out.println("2. Capturar un pokémon");
                System.out.println("3. Cerrar sesión");
                int op = scanner.nextInt();
                scanner.nextLine();
                if(op == 1) {
                    System.out.println("¿Qué pokémon quieres buscar?");
                    String pokemon = scanner.nextLine().replace("\n", "");
                    leer = true;
                    if(!pokemon.isEmpty()) {
                        respuesta = fabrica.creaMensaje(12, pokemon);
                        b = false;
                    } else System.out.println("Nombre de pokémon inválido.");
                } else if(op == 2) {
                    leer = true;
                    respuesta = fabrica.creaMensaje(13);
                    actual = Estado.Q5;
                    b = false;
                } else if(op == 3) {
                    leer = false;
                    respuesta = fabrica.creaMensaje(11);
                    actual = Estado.Q0;
                    b = false;
                } else System.out.println("Opción inválida.");
            } catch (Exception e) {
                scanner.next();
                System.out.println("Error al leer entrada.");
            }
        }
        return respuesta;
    }

    /* Estado Q5: "Aparición de un pokemon salvaje" */
    private byte[] estadoQ5(MensajeGenerico mensaje) {
        byte[] respuesta = null;
        int intentos = 0;
        String pokemon = null;
        if(mensaje.getCodigo() == 22) {
            pokemon = ((Mensaje22)mensaje).getNombre();
            System.out.println(String.format("¡Un %s salvaje apareció!", pokemon));
            intentos = ((Mensaje22)mensaje).getIntentos();
        }
        else if(mensaje.getCodigo() == 23) {
            pokemon = ((Mensaje23)mensaje).getNombre();
            System.out.println(String.format("Fallaste en capturar al %s salvaje", pokemon));
            intentos = ((Mensaje23)mensaje).getIntentos();
        }
        System.out.println(String.format("Tienes %d intento(s) restante(s)", intentos));
        boolean b = true;
        while(b) {
            try {
                System.out.println("Selecciona una opción");
                System.out.println("1. Lanzar pokebola");
                System.out.println("2. Ignorar");
                int op = scanner.nextInt();
                scanner.nextLine();
                if(op == 1) {
                    respuesta = fabrica.creaMensaje(15, intentos, pokemon);
                    actual = Estado.Q6;
                    b = false;
                    leer = true;
                } else if(op == 2) {
                    respuesta = fabrica.creaMensaje(14);
                    actual = Estado.Q2;
                    b = false;
                    leer = false;
                } else System.out.println("Opción inválida.");
            } catch (Exception e) {
                scanner.next();
                System.out.println("Error al leer entrada.");
            }
        }
        return respuesta;
    }

    /* Estado Q6: "Intento de captura de pokémon" */
    private byte[] estadoQ6(MensajeGenerico mensaje) {
        if(mensaje.getCodigo() == 24 || mensaje.getCodigo() == 41)
            actual = Estado.Q2;
        else if(mensaje.getCodigo() == 23)
            actual = Estado.Q5;
        return null;
    }
    
    /* Avanza a otro estado del automata y
     * regresa el mensaje que se le enviará al servidor */
    private byte[] siguienteEstado(MensajeGenerico mensaje) {
        switch(actual) {
        case Q0: return estadoQ0(mensaje);
        case Q1: return estadoQ1(mensaje);
        case Q2: return estadoQ2(mensaje);
        case Q5: return estadoQ5(mensaje);
        case Q6: return estadoQ6(mensaje);
        case Q9: terminar();
        }
        return null;
    }        

    /* Se manda mensaje al servidor y se regresa
     * una instancia de MensajeGenerico con la respuesta     
     * del servidor. */
    private MensajeGenerico mandaMensaje(byte[] mensaje) {
        MensajeGenerico m = null;
        if(mensaje != null) {
            try {
                out.write(mensaje);
                if(leer) {
                    byte[] longitud = new byte[4];                
                    in.read(longitud);
                    byte[] respuesta = new byte[ByteBuffer.wrap(longitud).asIntBuffer().get()];
                    in.read(respuesta);
                    m = fabrica.getMensaje(respuesta);
                }
            } catch (Exception e) {
                terminar();
                // e.printStackTrace();
                error("Error al mandar mensaje al servidor, puede que la conexión" +
                      " haya expirado. Conexión terminada.", true);
            }
        }
        return m;
    }
    
    /* Cierra la conexión con el servidor e indica que se termine el programa. */
    private void terminar() {
        try {
            in.close();
            out.close();
            socket.close();
            continua = false;
            leer = false;
            imagen.terminar();
        } catch(Exception e){
            error("Error al desconectar cliente", true);
             // e.printStackTrace();
        }
    }
}
