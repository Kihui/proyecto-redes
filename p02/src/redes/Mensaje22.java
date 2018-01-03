package redes;

public class Mensaje22 extends MensajeGenerico {

    String nombre;
    int intentos;
    
    public Mensaje22(char[] c) {
        super(c);
        nombre = null;
        intentos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIntentos() {
        return intentos;
    }
}
