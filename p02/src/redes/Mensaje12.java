package redes;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Mensaje12 extends MensajeGenerico {

    String nombre;
    
    public Mensaje12(byte[] c) {
        super(c);
        byte[] n = Arrays.copyOfRange(c, 1, c.length);
        nombre = new String(n, StandardCharsets.UTF_8);
    }

    public String getNombre() {
        return nombre;
    }
}
