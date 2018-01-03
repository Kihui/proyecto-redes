package redes;

import java.io.InputStream;
import java.io.OutputStream;
// import java.io.BufferedReader;
import java.util.Scanner;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Pokentrenador {

    private Estado actual;
    private Socket socket;
    private OutputStream out;
    private InputStream in;
    private Scanner scanner;
    // private BufferedReader inSys;
    private boolean continua;
    private FabricaMensaje fabrica;
    
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

    private void error(String mensaje, boolean salir) {
        System.err.println(mensaje);
        if(salir)
            System.exit(1);
    }

    public void empezar() {
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
            error("Error al mandar mensaje al servidor, puede que la conexión" +
                          " haya expirado. Conexión terminada.", true);
            // e.printStackTrace();
        }
        return m;
    } 
    
    public void terminar() {
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
