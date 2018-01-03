package redes;

public class Mensaje10 extends MensajeGenerico {

    String nombre;
    
    public Mensaje10(byte[] c) {
        super(c);
        nombre = null;
    }

    public String getNombre() {
        return nombre;
    }
}
