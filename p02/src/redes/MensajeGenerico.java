package redes;

public class MensajeGenerico {

    private int codigo;
    
    public MensajeGenerico(byte[] c) {
        codigo = c[0];
    }
    
    public int getCodigo() {
        return codigo;
    }    
}
