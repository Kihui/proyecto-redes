package redes;

import java.io.InputStream;
import java.io.OutputStream;
// import java.io.BufferedReader;
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
    // private BufferedReader inSys;
    private boolean continua;
    private FabricaMensaje fabrica;

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
            // inSys = new BufferedReader(new InputStreamReader(System.in));
            scanner = new Scanner(System.in);
        } catch(Exception e){
            // e.printStackTrace();
            error("Error al intentar conectarse al servidor.", true);
        }
        continua = true;
        actual = Estado.Q0;
        fabrica = new FabricaMensaje();
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
        while(continua) {
            byte[] mensaje = siguienteEstado(respuesta);
            respuesta = mandaMensaje(mensaje);       
        }
        terminar();
    }

    /* Estado Q0: "Conexión establecida, inicio de aplicación" */
    private byte[] estadoQ0(MensajeGenerico mensaje) {
        if(mensaje != null && mensaje.getCodigo() == 44)
            System.out.println("Nombre de usuario no identificado.");
        boolean b = true;
        byte[] respuesta = null;
        while(b) {
            System.out.println("Selecciona una opción");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Cerrar conexión");
            int op = scanner.nextInt();
            if(op == 1) {
                System.out.println("Ingresa tu nombre de usuario");
                String usuario = scanner.nextLine();
                if(!usuario.isEmpty()) {
                    respuesta = fabrica.creaMensaje(10, usuario);
                    actual = Estado.Q2;
                    b = false;
                } else System.out.println("Nombre de usuario inválido.");
            } if(op == 2) {
                respuesta = fabrica.creaMensaje(0);
                actual = Estado.Q9;
                b = false;
            } else System.out.println("Opción inválida.");            
        }
        return respuesta;
    }

    // NO IMPLEMENTADO**** 
    /* Estado Q2: "Menú de juego" */
    private byte[] estadoQ2(MensajeGenerico mensaje) {
        byte[] respuesta = null;
        return respuesta;
    }

    // NO IMPLEMENTADO**** 
    /* Estado Q5: "Aparición de un pokemon salvaje" */
    private byte[] estadoQ5(MensajeGenerico mensaje) {
        byte[] respuesta = null;
        return respuesta;
    }

    /* Avanza a otro estado del automata y
     * regresa el mensaje que se le enviará al servidor */
    private byte[] siguienteEstado(MensajeGenerico mensaje) {
        switch(actual) {
        case Q0: return estadoQ0(mensaje);
        case Q2: return estadoQ2(mensaje);
        case Q5: return estadoQ5(mensaje);
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
                byte[] longitud = new byte[4];
                in.read(longitud);
                byte[] respuesta = new byte[ByteBuffer.wrap(longitud).asIntBuffer().get()];
                in.read(respuesta);
                m = fabrica.getMensaje(respuesta);
            } catch (Exception e) {
                terminar();
                error("Error al mandar mensaje al servidor, puede que la conexión" +
                      " haya expirado. Conexión terminada.", true);
                // e.printStackTrace();
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
        } catch(Exception e){
            error("Error al desconectar cliente", true);
            // e.printStackTrace();
        }
    }
}
