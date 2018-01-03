package redes;

import java.sql.ResultSet;
import java.util.Random;
import java.sql.SQLException;

/**
 * Clase controlador 
 *
 */
public class Controlador{
    private ConexionBD conn;

    /**
     * Constructor de la clase
     * @param url la ruta del archivo de base de datos 
     */
    public Controlador(String url) {
	conn = new ConexionBD("org.sqlite.JDBC", "jdbc:sqlite:"+url);		    
    }

    public boolean abrirConexion() {
	conn.abre();
	return conn != null;
    }

    public void cerrarConexion() {
	conn.cierra();
    }

    /** 
     * Método de consulta en la pokédex. Se supone que ya está abierta la conexión.
     * @param pokemon el nombre del pokémon a buscar.
     * @param entrenador el nombre del entrenador asociado.
     * @return String el url del pokémon
     */
    public String getPokemon(String entrenador, String pokemon) {
	ResultSet rs = makeQuery("SELECT url FROM pokedex inner join pokemon on pokedex.id_pokemon = pokemon.id inner join entrenador on pokedex.id_entrenador = entrenador.id WHERE entrenador.name = \""+entrenador+"\" AND pokemon.name = \""+pokemon+"\"");
	//rs.next();
	try{
	    return rs.getString("url");
	}
	catch(Exception e){System.err.println("Error en la consulta de la pokedex");}
	return null;	
    }

    /**
     * Método para obtener el nombre de un pokémon aleatorio
     * para capturar. Se supone que ya está abierta la conexión.
     * @return String el nombre del pokémon disponible para captura.
     */
    public String getRandomPokemon() {
	Random rand = new Random();
	int random = rand.nextInt(150 - 1 + 1) + 1;
	System.out.println(random);
	ResultSet rs = makeQuery("SELECT name from pokemon WHERE id = "+random);
	//rs.next();
	try{
	    return rs.getString("name");
	}
	catch (Exception e){System.err.println("Error en la obtención de un pokemon aleatorio");}
	return null;
    }

    /** 
     * Método para agregar un pokémon a la pokédex de un entrenador.
     * @param pokemon el nombre del pokémon a agregar a la pokédex.
     * @param entrenador el nombre del dueño de la pokédex.
     * @return boolean si la inserción fue exitosa.
     */
    public boolean addPokemon(String entrenador, String pokemon){
	//boolean exito = false;
	String s = getPokemon(entrenador, pokemon);
	//supuestamente a prueba de fallos porque los argumentos no están en manos del cliente .
	try {
	    int id_e = makeQuery("SELECT id FROM entrenador WHERE name = \""+entrenador+"\"").getInt("id");
	    int id_p = makeQuery("SELECT id FROM pokemon WHERE name = \""+pokemon+"\"").getInt("id");
	    if(s != null) {
		makeQuery("UPDATE pokedex SET counter = counter+1 WHERE id_entrenador = "+id_e+" AND id_pokemon = "+id_p);
		return true;
	    }
	    makeQuery("INSERT INTO pokedex(id_entrenador, id_pokemon) VALUES("+id_e+","+id_p+")");
	    return true;
	}
	catch(SQLException sqle){System.err.println("Error agregando el pokémon a la pokédex."); sqle.printStackTrace();}
	return false;
    }

    /**
     * Método auxiliar para efectuar un query a la base de datos.
     * @param q la cadena con la consulta a la base.
     * @return ResultSet resultado de la consulta.
     */
    public ResultSet makeQuery(String q) {
	return conn.results(q);
    }

}
