package redes;

import java.nio.charset.StandardCharsets;
import java.nio.ByteBuffer;

/**
 * <p>Clase que permite crear mensajes en forma de
 * un arreglo de bytes.</p>
 * <p>Permite también crear objetos de la clase padre
 * MensajeGenerico a partir de un arreglo de bytes.</p>
 */
public class FabricaMensaje {
    
    /**
     * Crea un objeto de la clase padre MensajeGenerico
     * a partir de un arreglo de bytes.
     * @param c el arreglo de bytes
     */
    public MensajeGenerico getMensaje(byte[] c) {
        switch((int)c[0]) {
        case 10: return new Mensaje10(c);
        case 12: return new Mensaje12(c);
        case 15: return new Mensaje15(c);
        case 21: return new Mensaje21(c);
        case 22: return new Mensaje22(c);
        case 23: return new Mensaje23(c);
        case 24: return new Mensaje24(c);
        default: return new MensajeGenerico(c);
        }
    }

    /**
     * Regresa un mensaje de la forma [longitud|codigo]
     * en forma de arreglo de bytes. La longitud es el tamaño en bytes
     * del mensaje.
     * @param codigo el codigo del mensaje
     * @return el mensaje como arreglo de bytes
     */
    public byte[] creaMensaje(int codigo) {
        return new byte[]{0, 0, 0, 1, (byte)codigo};
    }
    
    /**
     * Regresa un arreglo de bytes de la forma [longitud|10|nombre].
     * La longitud es el tamaño del nombre + 1 (por el código) en bytes.
     * @param nombre el nombre a ser concatenado en el arreglo
     * @return un mensaje en forma de arreglo de bytes
     */    
    public byte[] creaMensaje(int codigo, String nombre) {
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

    /**
     * Regresa un arreglo de bytes de la forma
     * [longitud|codigo|longitud_nombre|nombre|imagen].
     * @param nombre el nombre a ser concatenado en el arreglo
     * @param imagen la imagen incluida en el mensaje
     * @return un mensaje en forma de arreglo de bytes
     */    
    public byte[] creaMensaje(int codigo, String nombre, byte[] imagen) {
        byte[] n = nombre.getBytes(StandardCharsets.UTF_8);
        int longitud = 5 + n.length + imagen.length;
        byte[] l = ByteBuffer.allocate(4).putInt(longitud).array();
        byte[] lnom = ByteBuffer.allocate(4).putInt(n.length).array();
        byte[] b = new byte[longitud + 4];
        int j = 0;
        for(int i = 0; i < 4; i++, j++)            
            b[j] = l[i];
        b[j++] = (byte)codigo;
        for(int i = 0; i < 4; i++, j++)
            b[j] = lnom[i];
        for(int i = 0; i < n.length; i++, j++)
            b[j] = n[i];
        for(int i = 0; i < imagen.length; i++, j++)
            b[j] = imagen[i];
        return b;
    }

    /**
     * Regresa un arreglo de bytes de la forma
     * [longitud|codigo|intentos|nombre].
     * @param nombre el nombre a ser concatenado en el arreglo
     * @param intentos el numero de intentos restantes para
     *                 la captura de un pokemon
     * @return un mensaje en forma de arreglo de bytes
     */    
    public byte[] creaMensaje(int codigo, int intentos, String nombre) {
        byte[] n = nombre.getBytes(StandardCharsets.UTF_8);
        int longitud = n.length + 2;
        byte[] b = new byte[longitud + 4];
        byte[] l = ByteBuffer.allocate(4).putInt(longitud).array();
        int j = 0;
        for(int i = 0; i < 4; i++, j++)            
            b[j] = l[i];
        b[j++] = (byte)codigo;
        b[j++] = (byte)intentos;
        for(int i = 0; i < n.length; i++, j++)
            b[j] = n[i];
        return b;
    }
    
    /**
     * Regresa un arreglo de bytes de la forma
     * [longitud|codigo|intentos].
     * @param intentos el numero de intentos restantes para
     *                 la captura de un pokemon
     * @return un mensaje en forma de arreglo de bytes
     */    
    public byte[] creaMensaje(int codigo, int intentos) {
        byte[] b = new byte[6];
        byte[] longitud = ByteBuffer.allocate(4).putInt(2).array();
        for(int i = 0; i < 4; i++)
            b[i] = longitud[i];
        b[4] = (byte)codigo;
        b[5] = (byte)intentos;
        return b;
    }
}
