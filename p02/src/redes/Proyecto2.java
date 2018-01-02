package redes;

/**
 * Programa que permite correr un servidor
 *  o un cliente que implementan un protocolo para la captura
 * de pokemons.
 */
public class Proyecto2 {
    
    public static void main(String args[]) {
        int puerto = 6666;
        String servidor = "127.0.0.1";
        boolean cliente = false;
        
        for(int i = 0; i < args.length; i++) {
            if(args[i].equals("-p"))
                try {
                    puerto = Integer.parseInt(args[i + 1]);
                } catch(Exception e) {
                    System.err.println("Ingresar un número de puerto válido.");
                    System.exit(1);
                }
            else if(args[i].equals("-c"))
                cliente = true;
            else if(args[i].equals("-s"))
                servidor = args[i];
        }
        
        if(!cliente) {
            new Pokeservidor(puerto).empezar();
        } else {
            new Pokentrenador(servidor, puerto).empezar();
        }
    }
}
