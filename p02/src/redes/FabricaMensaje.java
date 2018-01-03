package redes;

public class FabricaMensaje {

    public MensajeGenerico getMensaje(char[] c) {
        switch((int)c[0]) {
        case 10: return new Mensaje10(c);
        case 12: return new Mensaje12(c);
        case 21: return new Mensaje21(c);
        case 22: return new Mensaje22(c);
        case 24: return new Mensaje24(c);
        default: return new MensajeGenerico(c);
        }
    }

    public char[] creaMensajeGenerico(int codigo) {
        return new char[]{(char)codigo};
    }
    
    private char[] creaMensajeNombre(int codigo, String nombre) {
        char[] c = new char[nombre.length() + 1];
        c[0] = (char)codigo;
        for(int i = 1; i <= nombre.length(); i++)
            c[i] = nombre.charAt(i - 1);
        return c;
    }

    public char[] creaMensaje10(int codigo, String nombre) {
        return creaMensajeNombre(codigo, nombre);
    }
    
    public char[] creaMensaje12(int codigo, String nombre) {
        return creaMensajeNombre(codigo, nombre);
    }

    public char[] creaMensaje21(int codigo, String nombre,
                                byte[] imagen) {
        return null;
    }

    public char[] creaMensaje22(int codigo, String nombre,
                                int intentos) {
        return null;
    }

    public char[] creaMensaje24(int codigo, byte[] imagen) {
        return null;
    }
}
