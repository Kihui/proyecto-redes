package redes.test;

import redes.Controlador;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link Controlador}.
 */
public class ControladorTest {

    private Controlador controlador;
    /**
     * Creación de un controlador utilizando la base de datos
     *
     */
    public ControladorTest(){
        controlador = new Controlador("src/sql/hi.db");
	//controlador.cerrarConexion();
	Assert.assertTrue(controlador.abrirConexion());
    }

    @Test public void getRandomPokemonTest(){
	String s = controlador.getRandomPokemon();
	Assert.assertTrue(s != null);
	System.out.println("Pokemon obtenido aleatoriamente: "+s);
    }

    @Test public void getPokemonTest(){
	String url = controlador.getPokemon("Paulo", "Pikachu");
	Assert.assertTrue(url != null);
	System.out.println("Ruta del pokemon: "+url);
    }

    @Test public void addPokemonTest(){
	boolean b = controlador.addPokemon("Paulo", "Haunter");
	Assert.assertTrue(b);
	System.out.println("Haunter agregado al pokedex de Paulo.");
	String find = controlador.getPokemon("Paulo", "Haunter");
	Assert.assertTrue(find != null);
	controlador.cerrarConexion();
    }
    
}
