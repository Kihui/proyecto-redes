package redes;

public class MultiThreadPrueba{
    public static void MultithreadTest(){
	Controlador controlador = new Controlador("src/sql/hi.db");
	Hilo[] threads = new Hilo[10];
        for (int x = 0; x < 10; x++) {
	    threads[x] = new Hilo(x, controlador);
	}
	//Se inician los threads
        for (Hilo c : threads) {
            c.start();
        }
	
    }

    static class Hilo extends Thread {
	Controlador c;
	int id;

	Hilo(int id, Controlador c){
	    this.c = c;
	    this.id = id;
	}

	@Override
	public void run(){
	    System.out.println("iniciando thread " + id);
	    int x = 0;
	    try{
		while(x < 100){
		    if(c.getRandomPokemon() == null)
			System.out.println("Falla get random poke");
		    else
			System.out.println("Random poke ok");
		    Thread.sleep((long)(Math.random() * 100));
		    if(c.addPokemon("Iti","Pidgey") == null)		    
			System.out.println("Falla add poke ");
		    else
			System.out.println("Add poke ok");
		    if(!c.findUser("Paulo"))
			System.out.println("Falla find user");
		    else
			System.out.println("Find user ok");
		    if(c.addPokemon("Chipes","Pidgey") == null)
			System.out.println("Falla add poke");
		    else
			System.out.println("Add poke ok");
		    Thread.sleep((long)(Math.random() * 100));
		    if(c.addPokemon("Paulo","Gastly") == null)
			System.out.println("Falla add poke");
		    else
			System.out.println("Add poke ok");
		    if(!c.findUser("Iti"))
			System.out.println("Falla find user");
		    else
			System.out.println("Find user ok");
		    Thread.sleep((long)(Math.random() * 100));
		    if(c.getRandomPokemon() == null)
			System.out.println("Falla get random poke");
		    else
			System.out.println("Random poke ok");
		    x++;
		}
	    } catch(InterruptedException ie) {ie.printStackTrace();}
	}
	
    }
    public static void main(String[] args){
	MultithreadTest();
    }
}
