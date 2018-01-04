package redes;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * <p>Clase que contiene la lógica para el manejo en un hilo
 * de la conexión con un cliente pokentrenador.</p>
 */
public class ClienteHilo extends Thread {

    private Estado actual;
    private Socket socket;
    private OutputStream out;
    private InputStream in;
    private boolean continua;
    private FabricaMensaje fabrica;
    private Controlador con;
    private String entrenador;
    private Random random;
    private int intentos_max;
    private int id;
    
    /**
     * Constructor que recibe el socket por el cual
     * se efectuará la comunicación con el cliente.
     * @param s el socket con la conexión al cliente
     * @param timeout el tiempo máximo de espera de un mensaje del cliente
     */
    public ClienteHilo(int id, Socket s, int timeout, int i, Controlador c) {
        this.id = id;
        socket = s;
        continua = true;
        actual = Estado.Q0;
        fabrica = new FabricaMensaje();
        con = c;
        intentos_max = i;
        random = new Random();
        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
            socket.setSoTimeout(timeout);
        } catch(Exception e) {
            // e.printStackTrace();
            System.err.println(String.format("No se pudo crear conexión con cliente %d", id));
        }
    }

    /* Cierra la conexión con el cliente e indica que se termine el programa. */
    private void terminar() {
        continua = false;
        try {
            in.close();
            out.close();
            socket.close();
        } catch(Exception e) {
            // e.printStackTrace();
            System.err.println(String.format("Error al cerrar conexión %d", id));
        }
    }    

    /**
     * Corre el hilo que interactuará con el cliente
     */
    public void run() {
        while(continua) {
            try {
                MensajeGenerico mensaje = leeMensaje();
                byte[] respuesta = siguienteEstado(mensaje);
                if(respuesta != null)
                    mandaMensaje(respuesta);
            } catch(NullPointerException npe) {
                System.err.println(String.format("Error inesperado en conexión %d.", id));
            }
        }
    }

    /* Apartir del nombre de una imagen, regresa su representación en bytes */
    private byte[] getImagenBytes(String path) {
        byte[] imagen = null;
        try {            
            imagen = Files.readAllBytes(Paths.get(path));
        } catch (Exception e) {
            System.err.println(String.format("Error al leer imagen para cliente %d", id));
          // e.printStackTrace();
        }
        return imagen;
    }
    
    /* Estado Q0: "Conexión establecida, inicio de aplicación" */
    private byte[] estadoQ0(MensajeGenerico mensaje) {
        byte[] respuesta = null;
        if(mensaje.getCodigo() == 10) {
            // Inicio de sesión
            entrenador = ((Mensaje10)mensaje).getNombre();
            if(con.findUser(entrenador)) {
                actual = Estado.Q2;
                respuesta = fabrica.creaMensaje(20);
            } else respuesta = fabrica.creaMensaje(44);
        } else if(mensaje.getCodigo() == 0)
            terminar();
        return respuesta;
    }

    /* Estado Q2: "Menú de juego" */
    private byte[] estadoQ2(MensajeGenerico mensaje) {
        byte[] respuesta = null;
        if(mensaje.getCodigo() == 12) {
            // Búsqueda de un pokemon en la pokedex
            String pokemon = ((Mensaje12)mensaje).getNombre();
            String path = con.getPokemon(entrenador, pokemon);
            if(path != null)
                respuesta = fabrica.creaMensaje(21, pokemon, getImagenBytes(path));
            else respuesta = fabrica.creaMensaje(44);
        }
        else if(mensaje.getCodigo() == 11) {
            // Cerrar sesión
            entrenador = null;
            actual = Estado.Q0;
        } else if(mensaje.getCodigo() == 13) {
            // Solicitud de captura de pokemón
            String pokemon = con.getRandomPokemon();
            respuesta = fabrica.creaMensaje(22, intentos_max, pokemon);
            actual = Estado.Q5;
        }
        return respuesta;
    }

    /* Estado Q5: "Captura de pokemon" */
    private byte[] estadoQ5(MensajeGenerico mensaje) {        
        byte[] respuesta = null;
        actual = Estado.Q2;
        if(mensaje.getCodigo() == 15) {
            int intentos = ((Mensaje15)mensaje).getIntentos();
            if(intentos > 1) {
                String pokemon = ((Mensaje15)mensaje).getNombre();
                if(random.nextDouble() < 0.3) {
                    byte[] img = getImagenBytes(con.addPokemon(entrenador, pokemon));
                    respuesta = fabrica.creaMensaje(24, pokemon, img);                    
                } else {
                    respuesta = fabrica.creaMensaje(23, intentos - 1, pokemon);
                    actual = Estado.Q5;
                }
            } else respuesta = fabrica.creaMensaje(41);            
        }
        return respuesta;
    }
    
    /* Avanza a otro estado del automata y
     * regresa el mensaje que se le enviará al cliente */
    private byte[] siguienteEstado(MensajeGenerico mensaje) {
        switch(actual) {
        case Q0: return estadoQ0(mensaje);
        case Q2: return estadoQ2(mensaje);
        case Q5: return estadoQ5(mensaje);
        }
        return null;
    }
        
    /* Se manda mensaje al cliente */
    private void mandaMensaje(byte[] mensaje) {
        try {
            out.write(mensaje);
        } catch (Exception e) {
            terminar();
            System.err.println(String.format("Hubo un error al mandar mensaje " +
                                             "al cliente %d o la conexión ha expirado. Conexión terminada.", id));
            // e.printStackTrace();
        }
    }
    
    /* Se lee un mensaje del cliente y se regresa
     * una instancia de MensajeGenerico */
    private MensajeGenerico leeMensaje() {
        MensajeGenerico m = null;
        try {
            byte[] longitud = new byte[4];
            in.read(longitud);
            byte[] respuesta = new byte[ByteBuffer.wrap(longitud).asIntBuffer().get()];
            in.read(respuesta);
            m = fabrica.getMensaje(respuesta);
        } catch (Exception e) {
            terminar();
            System.err.println(String.format("Hubo un error al leer mensaje " +
                                             "del cliente %d o la conexión ha expirado. Conexión terminada.", id));
            // e.printStackTrace();
        }
        return m;
    } 
}
