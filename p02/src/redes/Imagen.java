package redes;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.LinkedList;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Clase para mostrar imágenes.
 */
public class Imagen {

    private LinkedList<JFrame> lista;

    /**
     * Constructor de la clase Imagen.
     */
    public Imagen() {
        lista = new LinkedList<>();
    }

    /**
     * Cierra los páneles abiertos para permitir que
     * se termine el programa.
     */
    public void terminar() {
        while(!lista.isEmpty()) {
            JFrame f = lista.pop();
            f.setVisible(false); 
            f.dispose();
        }
    }

    /**
     * Muestra una imagen en la pantalla usando Swing.
     * @param imagen la imagen en bytes a ser mostrada
     */
    public void mostrarImagen(byte[] imagen) {
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(imagen));
            ImageIcon icon = new ImageIcon(img);
            JFrame frame = new JFrame();
            frame.setLayout(new FlowLayout());
            frame.setSize(200, 200);
            JLabel lbl = new JLabel();
            lbl.setIcon(icon);
            frame.add(lbl);
            frame.setVisible(true);
            lista.add(frame);
            // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch(Exception e) {
            System.err.println("Error al cargar imagen");
        }
    }
    
    /**
     * Guarda una imagen de un pokemon en el directorio capturados/
     * @param pokemon el pokemon representado en la imagen
     * @param imagen la imagen en bytes a ser guardada
     */
    public void guardarImagen(String pokemon, byte[] imagen) {
        try {
        Files.createDirectories(Paths.get("capturados"));       
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(imagen));
        ImageIO.write(img, "png", new File(String.format("capturados/%s.png", pokemon)));
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("No se pudo guardar imagen.");
        }
    }
}
