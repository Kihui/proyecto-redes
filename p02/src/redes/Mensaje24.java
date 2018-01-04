package redes;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.nio.ByteBuffer;

public class Mensaje24 extends MensajeGenerico {

    String nombre;
    byte[] imagen;
    
    public Mensaje24(byte[] c) {
        super(c);
        int nomLong = ByteBuffer.wrap(Arrays.copyOfRange(c, 1, 5)).getInt();
        nombre = new String(Arrays.copyOfRange(c, 5, 5 + nomLong), StandardCharsets.UTF_8);
        imagen = Arrays.copyOfRange(c, 5 + nomLong, c.length);
    }    

    public String getNombre() {
        return nombre;
    }
    
    public byte[] getImagen() {
        return imagen;
    }
}
