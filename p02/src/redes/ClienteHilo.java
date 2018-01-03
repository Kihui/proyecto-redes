package redes;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

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

    /**
     * Constructor que recibe el socket por el cual
     * se efectuará la comunicación con el cliente.
     * @param s el socket con la conexión al cliente
     * @param timeout el tiempo máximo de espera de un mensaje del cliente
     */    
    public ClienteHilo(Socket s, int timeout) {
        socket = s;
        continua = true;
        actual = Estado.Q0;
        fabrica = new FabricaMensaje();
        try {
            out = socket.getOutputStream();
            in = socket.getInputStream();
            socket.setSoTimeout(timeout);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /* Imprime el error 'mensaje'. Si salir es true, se sale del programa */
    private void error(String mensaje, boolean salir) {
        System.err.println(mensaje);
        if(salir)
            System.exit(1);        
    }

    /* Cierra la conexión con el cliente e indica que se termine el programa. */
    private void terminar() {
        try {
            in.close();
            out.close();
            socket.close();
            continua = false;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }    
    
    public void run() {
        MensajeGenerico respuesta = null;
        while(continua) {
            byte[] mensaje = siguienteEstado(respuesta);
            respuesta = mandaMensaje(mensaje);
        }
        terminar();
    }

    
    // NO IMPLEMENTADO**** 
    /* Estado Q1: "Inicio de sesión" */
    private byte[] estadoQ1(MensajeGenerico mensaje) {
        byte[] respuesta = null;
        return respuesta;
    }

    
    // NO IMPLEMENTADO**** 
    /* Estado Q3: "Solicitud de captura de pokemon" */
    private byte[] estadoQ3(MensajeGenerico mensaje) {
        byte[] respuesta = null;
        return respuesta;
    }

    
    // NO IMPLEMENTADO**** 
    /* Estado Q4: "Búsqueda de un pokemon en la pokedex" */
    private byte[] estadoQ4(MensajeGenerico mensaje) {
        byte[] respuesta = null;
        return respuesta;
    }

    
    // NO IMPLEMENTADO**** 
    /* Estado Q6: "Intento de captura de un pokemon" */
    private byte[] estadoQ6(MensajeGenerico mensaje) {
        byte[] respuesta = null;
        return respuesta;
    }

    
    // NO IMPLEMENTADO**** 
    /* Estado Q9: "Cierre de conexion" */
    private byte[] estadoQ9(MensajeGenerico mensaje) {
        byte[] respuesta = null;
        return respuesta;
    }

    /* Avanza a otro estado del automata y
     * regresa el mensaje que se le enviará al cliente */
    private byte[] siguienteEstado(MensajeGenerico mensaje) {
        switch(actual) {
        case Q1:
            break;
        case Q3:
            break;
        case Q4:
            break;
        case Q6:
            break;
        case Q9:
            break;
        }
        return null;
    }

    /* Se manda mensaje al cliente y se regresa
     * una instancia de MensajeGenerico con la respuesta     
     * del cliente. */    
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
                error("Hubo un error o la conexión ha expirado. Conexión terminada.", true);
                // e.printStackTrace();
            }
        }
        return m;
    } 
}
