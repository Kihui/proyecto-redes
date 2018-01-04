package redes;

public class Mensaje23 extends MensajeGenerico {

    int intentos;
    String nombre;
    
    public Mensaje23(byte[] c) {
        super(c);
        intentos = 0;
        nombre = null;
    }

    public int getIntentos() {
        return intentos;
    }

    public String getNombre() {
        return nombre;
    }
}
