package redes;

public class Mensaje21 extends MensajeGenerico {

    String nombre;
    byte[] imagen;
    
    public Mensaje21(byte[] c) {
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
