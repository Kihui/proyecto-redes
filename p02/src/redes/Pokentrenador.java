package redes;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Pokentrenador {

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader inSys;
    private boolean continua;
    
    public Pokentrenador(String ip, int puerto) {
        try {
            socket = new Socket(ip, puerto);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(Exception e){
            // e.printStackTrace();
            error("Error al intentar conectarse al servidor.", true);
        }
        continua = true;
    }

    private void error(String mensaje, boolean salir) {
        System.err.println(mensaje);
        if(salir)
            System.exit(1);        
    }

    public void empezar() {
        inSys = new BufferedReader(new InputStreamReader(System.in));
        while(continua) {
            try {
                System.out.println("Mensaje: ");
                String mensaje = inSys.readLine();
                String respuesta = mandaMensaje(mensaje);
                if(mensaje.equals("END"))
                    terminar();
                else {
                    if(respuesta != null) {
                        System.out.println(respuesta);
                    } else error("Error al recibir mensaje del servidor, puede que la conexión " +
                                 "haya expirado. Cerrando conexión.", true);
                }
            } catch(Exception e) {
                error("Error al leer entrada.", false);
            }
        }
    }
    
    public String mandaMensaje(String mensaje) {
            out.println(mensaje);
            String resp = "";
            try {
                resp = in.readLine();
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
