package redes;

public class Mensaje {

    private int codigo;
    
    public Mensaje(String s) {
        codigo = getCodigo(s);
    }

    private int getCodigo(String s) {
        return 1;
    }
    
}
