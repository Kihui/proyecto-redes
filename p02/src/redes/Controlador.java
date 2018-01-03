/**
 * Clase controlador 
 *
 */
public class Controlador{
    private ConexionBD con;

    /**
     * Construcro de la clase
     * @param url la ruta del archivo de base de datos 
     */
    public Controlador(String url) {
	con = ConexionBD("org.sqlite.JDBC", url);		    
    }
}
