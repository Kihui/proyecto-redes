package redes;

import java.net.ServerSocket;

/**
 * <p>Clase que permite crear un servidor para capturar pokemones</p>
 */
public class Pokeservidor {
    
    private ServerSocket serverSocket;
    /* Constate que indica el timeout de los sockets cliente */
    private static final int TIMEOUT = 60000;
    
    /**
     * Constructor que recibe el puerto
     * por el que aceptarán conexiones.
     * @param puerto el puerto por el que se aceptarán las conexiones
     */
    public Pokeservidor(int puerto) {
        try {
            serverSocket = new ServerSocket(puerto);
        } catch(Exception e){
            // e.printStackTrace();
            error("Hubo un error al correr el servidor.", true);
        }
    }

    /* Imprime el error 'mensaje'. Si salir es true, se sale del programa */
    private void error(String mensaje, boolean salir) {
        System.err.println(mensaje);
        if(salir)
            System.exit(1);        
    }

    /**
     * Escucha conexiones de los clientes y crea un hilo para cada uno.
     */
    public void empezar() {
        try {
            while(true)
                new ClienteHilo(serverSocket.accept(), TIMEOUT).start();
        } catch(Exception e){
            // e.printStackTrace();
            error("Hubo un error al aceptar conexión de un cliente.", true);
        }
    }

    /**
     * Cierra el socket que escucha a conexiones de clientes.
     */     
    public void terminar() {
        try {
            serverSocket.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
