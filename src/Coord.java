
public class Coord {
	
	private int ligne;
	private int colonne;
	
	public Coord(int x, int y) {
		this.ligne = x;
		this.colonne = y;
	}
	public int getLigne() {
		return this.ligne;
	}
	
	public int getColonne() {
		return this.colonne;
	}
	public boolean estValide() {
		return this.colonne >= 0 && this.colonne <= 6 && this.ligne >= 0 && this.ligne <= 6;
	}

	public static void main(String[] args) {
		Coord j = new Coord(2,1);
		System.out.println(j.getLigne());
	}



}
