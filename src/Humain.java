import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Humain extends Joueur {

    public Humain(String nom){
        super(nom);
    }
    
    public int scoreSelection() {
		
		return 0;
	}
	
	public int scorePlacement() {
		
		return 0;
	}
    
    public boolean placerTuile() {
    	return false;
    };

    		//prend num la position de la tuile dans reservation les deux terrains(1 ou 2) et leur coordonnees
    		//retourne true si a marchee
    public boolean placerTuile(int num, Coord coord1, int terrain1, Coord coord2, int terrain2){
    	
    	System.out.println("----------------------------placerTuile()----------------------");
    	System.out.println("num : "+num);
	    System.out.println("terrain1 " + terrain1 + " aux coordonnees "+coord1.getLigne()+coord1.getColonne());
	    System.out.println("terrain2 : " + terrain2 + " aux coordonnees "+coord2.getLigne()+coord2.getColonne());

        		//verifie que les deux coordonnees sont adjacentes
        if ( (coord1.getLigne() == coord2.getLigne() && coord1.getColonne() == (coord2.getColonne() - 1)) ||
            (coord1.getLigne() == coord2.getLigne() && coord2.getColonne() == (coord1.getColonne() - 1)) ||
            (coord1.getColonne() == coord2.getColonne() && coord1.getLigne() == (coord2.getLigne() - 1)) ||
            (coord1.getColonne() == coord2.getColonne() && coord2.getLigne() == (coord1.getLigne() - 1))) {
        } else {
                System.out.println("terrains non adjacents");
                return false;
        }

        
        //appele la methode recevoir tuile pour positionner la tuile
        boolean succes = this.grille.recevoirTuile(this.reservation[num], coord1, terrain1, coord2, terrain2);
        if(succes){
        	System.out.println("---------- Terrain 1 -------------");
            this.majGrilleZone(this.reservation[num].getTerrain1(), coord1);
            System.out.println("---------- Terrain 2 -------------");
            this.majGrilleZone(this.reservation[num].getTerrain2(), coord2);
            this.afficheZones();
            this.calculScore();
            this.reservation[num] = null;
            return true;
        }
        return false;
    }
    
    public int reserver(Tuile[] tirage,int[] tempOrdre) {
    	return 0;
    }
}
