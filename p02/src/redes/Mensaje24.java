package redes;

public class Mensaje24 extends MensajeGenerico {

    String nombre;
    byte[] imagen;
    
    public Mensaje24(byte[] c) {
        super(c);
        nombre = null;
        imagen = null;
    }    

    public String getNombre() {
        return nombre;
    }
    
    public byte[] getImagen() {
        return imagen;
    }

}
