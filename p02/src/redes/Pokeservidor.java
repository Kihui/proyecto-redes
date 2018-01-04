package redes;

import java.net.ServerSocket;

/**
 * <p>Clase que permite crear un servidor para capturar pokemones</p>
 */
public class Pokeservidor {
    
    private ServerSocket serverSocket;
    /* Constate que indica el timeout de los sockets cliente */
    private static final int TIMEOUT = 60000;
    private static final int INTENTOS_MAX = 5;
    private Controlador controlador;
    
    /**
     * Constructor que recibe el puerto
     * por el que aceptarán conexiones.
     * @param puerto el puerto por el que se aceptarán las conexiones
     */
    public Pokeservidor(int puerto, String bd) {
        try {
            serverSocket = new ServerSocket(puerto);
            controlador = new Controlador(bd);
        } catch(Exception e){
            // e.printStackTrace();
            terminar();
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
        int id = 0;
        try {
            while(true)
                new ClienteHilo(id++, serverSocket.accept(), TIMEOUT, INTENTOS_MAX, controlador).start();
        } catch(Exception e){
            // e.printStackTrace();
            terminar();
            error("Hubo un error al aceptar conexión de un cliente.", true);
        }
    }

    /**
     * Cierra el socket que escucha a conexiones de clientes.
     */     
    public void terminar() {
        try {
            serverSocket.close();
            controlador.cerrarConexion();
        } catch(Exception e){
            // e.printStackTrace();
            error("Hubo un error al deshabilitar el servidor.", true);
        }
    }
}
