package redes;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ClienteHilo extends Thread {

    private Estado actual;
    private Socket socket;
    private OutputStream out;
    private InputStream in;
    private boolean continua;
    private FabricaMensaje fabrica;
    
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

    private void error(String mensaje, boolean salir) {
        System.err.println(mensaje);
        if(salir)
            System.exit(1);        
    }

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

    private byte[] siguienteEstado(MensajeGenerico mensaje) {
        switch(actual) {
        case Q0:
            break;
        case Q1:
            break;
        case Q2:
            break;
        case Q3:
            break;
        case Q4:
            break;
        case Q5:
            break;
        case Q6:
            break;
        case Q7:
            break;
        case Q8:
            break;
        case Q9:
            break;
        }
        return null;
    }
    
    private MensajeGenerico mandaMensaje(byte[] mensaje) {
        MensajeGenerico m = null;
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
        return m;
    } 
}
