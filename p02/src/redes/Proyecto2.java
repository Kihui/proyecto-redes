package redes;

/**
 * Programa que permite correr un servidor
 *  o un cliente que implementan un protocolo para la captura
 * de pokemons.
 */
public class Proyecto2 {
    
    public static int getInt(String s, String error) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
            if(i < 0) {
                System.err.println("No se permiten números negativos");
                System.exit(1);
            }
        } catch(Exception e) {
            System.err.println(error);
            System.exit(1);
        }
        return i;
    }
    
    public static void main(String args[]) {
        int puerto = 9999;
        String servidor = "127.0.0.1";
        boolean cliente = false;
        String bd = "src/sql/base.db";
        int timeout = 60*1000;
        int intentos = 5;
        try {
            for(int i = 0; i < args.length; i++) {
                if(args[i].equals("-p"))
                    puerto = getInt(args[i + 1], "Ingresar un número de puerto válido.");
                else if(args[i].equals("-c"))
                    cliente = true;
                else if(args[i].equals("-s"))
                    servidor = args[i + 1];
                else if(args[i].equals("-b"))
                    bd = args[i + 1];
                else if(args[i].equals("-t"))
                    timeout = getInt(args[i + 1], "Ingresar un valor de timeout válido.") * 1000;
                else if(args[i].equals("-i"))
                    intentos = getInt(args[i + 1], "Ingresar un valor de número de intentos válido.");
            }
        } catch(ArrayIndexOutOfBoundsException aioobe) {
            System.err.println("Argumentos insuficientes.");
            System.exit(1);
        }
        if(!cliente) {
            new Pokeservidor(puerto, bd, timeout, intentos).empezar();
        } else {
            new Pokentrenador(servidor, puerto).empezar();
        }
    }
}
