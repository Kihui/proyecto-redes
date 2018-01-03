package redes;

import java.net.ServerSocket;

public class Pokeservidor {
    
    private ServerSocket serverSocket;
    private static final int TIMEOUT = 60000;
    
    public Pokeservidor(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
        } catch(Exception e){
            // e.printStackTrace();
            error("Hubo un error al correr el servidor.", true);
        }
    }

    private void error(String mensaje, boolean salir) {
        System.err.println(mensaje);
        if(salir)
            System.exit(1);        
    }

    public void empezar() {
        try {
            while(true)
                new ClienteHilo(serverSocket.accept(), TIMEOUT).start();
        } catch(Exception e){
            // e.printStackTrace();
            error("Hubo un error al aceptar conexión de un cliente.", true);
        }
    }
    
    public void terminar() {
        try {
            serverSocket.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
