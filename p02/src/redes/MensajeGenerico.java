package redes;

public class MensajeGenerico {

    private int codigo;
    
    public MensajeGenerico(char[] c) {
        codigo = (int)c[0];
    }
    
    public int getCodigo() {
        return codigo;
    }    
}
