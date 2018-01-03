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
	Assert.assertTrue(controlador.abrirConexion());
    }

    @Test public void getRandomPokemonTest(){
	String s = controlador.getRandomPokemon();
	Assert.assertTrue(s != null);
	System.out.println("Pokemon obtenido aleatoriamente: "+s);
    }
    
}
