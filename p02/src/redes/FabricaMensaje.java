package redes;

import java.nio.charset.StandardCharsets;
import java.nio.ByteBuffer;

public class FabricaMensaje {

    public MensajeGenerico getMensaje(byte[] c) {
        switch((int)c[0]) {
        case 10: return new Mensaje10(c);
        case 12: return new Mensaje12(c);
        case 21: return new Mensaje21(c);
        case 22: return new Mensaje22(c);
        case 24: return new Mensaje24(c);
        default: return new MensajeGenerico(c);
        }
    }

    public byte[] creaMensajeGenerico(int codigo) {
        return new byte[]{0, 0, 0, 1, (byte)codigo};
    }
    
    private byte[] creaMensajeNombre(int codigo, String nombre) {
        byte[] n = nombre.getBytes(StandardCharsets.UTF_8);
        byte[] longitud = ByteBuffer.allocate(4).putInt(n.length + 1).array();
        byte[] b = new byte[n.length + 5];
        for(int i = 0; i < 4; i++)
            b[i] = longitud[i];
        b[4] = (byte)codigo;
        for(int i = 0; i < n.length; i++)
            b[i + 5] = n[i];
        return b;
    }

    public byte[] creaMensaje10(int codigo, String nombre) {
        return creaMensajeNombre(codigo, nombre);
    }
    
    public byte[] creaMensaje12(int codigo, String nombre) {
        return creaMensajeNombre(codigo, nombre);
    }

    public byte[] creaMensaje21(int codigo, String nombre,
                                byte[] imagen) {
        return null;
    }

    public byte[] creaMensaje22(int codigo, String nombre,
                                int intentos) {
        return null;
    }

    public byte[] creaMensaje24(int codigo, byte[] imagen) {
        return null;
    }
}
