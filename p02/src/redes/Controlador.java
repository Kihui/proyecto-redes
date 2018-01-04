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
	return !conn.isClosed();
    }

    public void cerrarConexion() {
	conn.cierra();
    }

    /**
     * Método de consulta de usuarios. Para permitir el inicio de sesión. 
     * @param nombre el nombre de usuario que intenta iniciar sesión.
     * @return boolean true en caso de que haya coincidencia en la bd., false en caso contrario.
     */
    public synchronized boolean findUser(String nombre){
	if(!abrirConexion())
	    return false;
        ResultSet rs = makeQuery("SELECT name from entrenador WHERE name = \""+nombre+"\"");
	String name = null;
	try{
	    name = rs.getString("name");
	} catch(SQLException sqle){
            System.err.println("Nombre de usuario no encontrado.");
            // sqle.printStackTrace();
        }
	cerrarConexion();
	return name != null;
        
    }

    /** 
     * Método de consulta en la pokédex.
     * @param pokemon el nombre del pokémon a buscar.
     * @param entrenador el nombre del entrenador asociado.
     * @return String el url del pokémon
     */
    public synchronized String getPokemon(String entrenador, String pokemon) {
	if(!abrirConexion())
	    return null;
	String s = null;
	ResultSet rs = makeQuery("SELECT url FROM pokedex inner join pokemon on pokedex.id_pokemon = pokemon.id inner join entrenador on pokedex.id_entrenador = entrenador.id WHERE entrenador.name = \""+entrenador+"\" AND pokemon.name = \""+pokemon+"\"");
	//rs.next();
	try{
	    s = rs.getString("url");
	}
	catch(Exception e){System.err.println("Pokemon no encontrado en la pokedex de usuario");}
	cerrarConexion();
	return s;	
    }

    /**
     * Método para obtener el nombre de un pokémon aleatorio
     * para capturar. Se supone que ya está abierta la conexión.
     * @return String el nombre del pokémon disponible para captura.
     */
    public synchronized String getRandomPokemon() {
	if(!abrirConexion())
	    return null;
	String s = null;
	Random rand = new Random();
	int random = rand.nextInt(150 - 1 + 1) + 1;
	System.out.println(random);
	ResultSet rs = makeQuery("SELECT name from pokemon WHERE id = "+random);
	//rs.next();
	try{
	    s = rs.getString("name");
	}
	catch (Exception e){System.err.println("Error en la obtención de un pokemon aleatorio");}
	cerrarConexion();
	return s;
    }

    /** 
     * Método para agregar un pokémon a la pokédex de un entrenador.
     * @param pokemon el nombre del pokémon a agregar a la pokédex.
     * @param entrenador el nombre del dueño de la pokédex.
     * @return la ruta url de la imagen del pokémon.
     */
    public synchronized String addPokemon(String entrenador, String pokemon){
	//String s = getPokemon(entrenador, pokemon);
	
	
	if(!abrirConexion())
	    return null;
	//supuestamente a prueba de fallos porque los argumentos no están en manos del cliente .
	String out = null;
	try {
	    ResultSet rs = makeQuery("SELECT url FROM pokedex inner join pokemon on pokedex.id_pokemon = pokemon.id inner join entrenador on pokedex.id_entrenador = entrenador.id WHERE entrenador.name = \""+entrenador+"\" AND pokemon.name = \""+pokemon+"\"");
	    
	    int id_e = makeQuery("SELECT id FROM entrenador WHERE name = \""+entrenador+"\"").getInt("id");
	    ResultSet pq = makeQuery("SELECT id,url FROM pokemon WHERE name = \""+pokemon+"\"");
	    int id_p = pq.getInt("id");
	    out = pq.getString("url");
	    if(rs.next())
		makeQuery("UPDATE pokedex SET counter = counter+1 WHERE id_entrenador = "+id_e+" AND id_pokemon = "+id_p);
	    else
		makeQuery("INSERT INTO pokedex(id_entrenador, id_pokemon) VALUES("+id_e+","+id_p+")");
	}
	catch(SQLException sqle){
            System.err.println("Error agregando el pokémon a la pokédex.");
            // sqle.printStackTrace();
        }
	cerrarConexion();
	return out;
    }

    /**
     * Método auxiliar para efectuar una query a la base de datos.
     * @param q la cadena con la consulta a la base.
     * @return ResultSet resultado de la consulta.
     */
    public synchronized ResultSet makeQuery(String q) {
	return conn.results(q);
    }

}
