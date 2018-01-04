package redes;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Mensaje23 extends MensajeGenerico {

    int intentos;
    String nombre;
    
    public Mensaje23(byte[] c) {
        super(c);
        intentos = c[1];
        nombre = new String(Arrays.copyOfRange(c, 2, c.length), StandardCharsets.UTF_8);
    }

    public int getIntentos() {
        return intentos;
    }

    public String getNombre() {
        return nombre;
    }
}
