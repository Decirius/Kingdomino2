
public class Coord {
	
	private int ligne;
	private int colonne;
	
	
	public Coord(int x, int y) {					// Creation d'une coordonnee
		this.ligne = x;
		this.colonne = y;
	}
	
	
	public int getLigne() {							// Recuperation de la ligne
		return this.ligne;
	}
	
	
	public int getColonne() {						// Recuperation de la colonne
		return this.colonne;
	}
	
	
	public boolean estValide() {					// Est valide pour la ligne cad ligne et colonne entre 0 et 6 
		return this.colonne >= 0 && this.colonne <= 6 && this.ligne >= 0 && this.ligne <= 6;
	}

}
