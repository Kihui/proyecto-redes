package redes;

public class Mensaje24 extends MensajeGenerico {

    byte[] imagen;
    
    public Mensaje24(char[] c) {
        super(c);
        imagen = null;
    }    

    public byte[] getImagen() {
        return imagen;
    }

}
