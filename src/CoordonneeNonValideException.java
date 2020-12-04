import  java.lang.Exception;

public class CoordonneeNonValideException extends Exception {
	

	public CoordonneeNonValideException(String s){				// Creation d'une exception : les coordonnees ne sont pas valides
        super(s);
    }
	 
}
