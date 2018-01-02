package redes;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteHilo extends Thread {

    private boolean continua;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    public ClienteHilo(Socket s) {
        socket = s;
        continua = true;
        try {
            socket.setSoTimeout(5000);
        } catch(Exception e) {
            e.printStackTrace();
        }
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
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            while(continua) {
                String mensaje = in.readLine();
                System.out.println(mensaje);
                out.println(mensaje);
                if(mensaje.equals("END"))
                    terminar();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
