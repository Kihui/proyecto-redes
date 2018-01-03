package redes;

public class Mensaje12 extends MensajeGenerico {

    String nombre;
    
    public Mensaje12(byte[] c) {
        super(c);
        nombre = null;
    }

    public String getNombre() {
        return nombre;
    }
}
