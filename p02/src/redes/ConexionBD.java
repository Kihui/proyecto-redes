package redes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

/** 
 * Clase para manejar la conexión con la base de datos
 *
 */
public class ConexionBD {
    private Connection conn;
    private String driver, url;

    /** 
     * Constructor de la clase
     * @param driver el controlador del SMBD
     * @param url la ruta relativa del archivo de base de datos
     */
    public ConexionBD(String driver, String url) {
	this.driver = driver;
	this.url = url;
    }

    public boolean isClosed(){
	try{
	return conn.isClosed();
	} catch(SQLException sqle){System.err.println("No existe conexión."); sqle.printStackTrace();}
	return false;
    }

    /**
     * Apertura de la conexión
     *
     */
    public void abre(){
	try {
	    Class.forName(driver);
	    conn = DriverManager.getConnection(url);
	} catch(Exception e) {System.err.println("Error en la creación de la conexión");e.printStackTrace();}
    }

    /** 
    * Método para cerrar la conexión con la base de datos
    */
    public void cierra(){
	try {
	    conn.close();
	} catch(Exception e) {System.err.println("Error en el cierre de la conexión.");}
	//println("Conexión finalizada")
    }

    /**
     * Método para establecer el resultSet dada una consulta en cadena
     * @param q la cadena que contiene la consulta
     * 
     */
    public ResultSet results(String q) {
	ResultSet resultSet = null;
	try{
	    Statement statement = conn.createStatement();
	    if(q.charAt(0) == 'S')
		resultSet = statement.executeQuery(q);
	    else
		statement.executeUpdate(q);
	    //print(" Éxito.\n")
	} catch(SQLException e) {
	    System.err.println(" Error en consulta.");
	    e.printStackTrace();
	    
	}
	return resultSet;
    }
}
