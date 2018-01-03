package redes;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Pokentrenador {

    private Estado actual;
    private Socket socket;
    private OutputStream out;
    private BufferedReader in;
    private BufferedReader inSys;
    private boolean continua;
    
    public Pokentrenador(String ip, int puerto) {
        try {
            socket = new Socket(ip, puerto);
            out = socket.getOutputStream();
            in = socket.getInputStream();
            inSys = new BufferedReader(new InputStreamReader(System.in));
        } catch(Exception e){
            // e.printStackTrace();
            error("Error al intentar conectarse al servidor.", true);
        }
        continua = true;
        actual = Estado.Q0;
    }

    private void error(String mensaje, boolean salir) {
        System.err.println(mensaje);
        if(salir)
            System.exit(1);        
    }

    public void empezar() {
        byte[] respuesta = null;
        while(continua) {
            try {
                byte[] mensaje = siguienteEstado(respuesta);
                respuesta = mandaMensaje(mensaje);       
                if(respuesta == null) 
                    error("Error al recibir mensaje del servidor, puede que la conexión " +
                          "haya expirado. Cerrando conexión.", true);
            } catch(Exception e) {
                error("Error al leer entrada.", false);
            }
        }
    }
    
    private byte[] siguienteEstado(byte[] mensaje) {
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
        return null;
    }
    
    private byte[] mandaMensaje(byte[] mensaje) {
            byte[] resp = null;
            try {
                out.write(mensaje);
                byte[] mensaje = new byte[3];
                in.read(codigo);
                mensaje = new byte[tamanio];
                in.read(codigo);
            } catch (Exception e){
                error("Error al mandar mensaje al servidor.", true);
                // e.printStackTrace();
            }
        return resp;
    }
 
    public void terminar() {
        try {
            in.close();
            out.close();
            socket.close();
            continua = false;
        } catch(Exception e){
            error("Error al desconectar cliente", false);
            e.printStackTrace();
        }
    }
}
