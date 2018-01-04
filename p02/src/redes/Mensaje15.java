package redes;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Mensaje15 extends MensajeGenerico {

    int intentos;
    String nombre;
    
    public Mensaje15(byte[] c) {
        super(c);
        intentos = c[1];
        byte[] n = Arrays.copyOfRange(c, 2, c.length);
        nombre = new String(n, StandardCharsets.UTF_8);
    }

    public int getIntentos() {
        return intentos;
    }

    public String getNombre() {
        return nombre;
    }
}
