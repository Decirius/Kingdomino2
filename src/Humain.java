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
    public boolean placerTuile(int num, Coord tempCoord1, int terrain1, Coord tempCoord2, int terrain2){
    	
    	Coord coord1;
    	Coord coord2;
    	
    	if (terrain1==1) {
    		coord1=tempCoord1;
    		coord2=tempCoord2;
    	} else {
    		coord1=tempCoord2;
    		coord2=tempCoord1;
    	}
    	
    	System.out.println("----------------------------placerTuile()----------------------");
    	System.out.println("num : "+num);
	    System.out.println("terrain1 " + terrain1 + " aux coordonnees "+tempCoord1.getLigne()+tempCoord1.getColonne());
	    System.out.println("terrain2 : " + terrain2 + " aux coordonnees "+tempCoord2.getLigne()+tempCoord2.getColonne());

        		//verifie que les deux coordonnees sont adjacentes
        if ( (tempCoord1.getLigne() == tempCoord2.getLigne() && tempCoord1.getColonne() == (tempCoord2.getColonne() - 1)) ||
            (tempCoord1.getLigne() == tempCoord2.getLigne() && tempCoord2.getColonne() == (tempCoord1.getColonne() - 1)) ||
            (tempCoord1.getColonne() == tempCoord2.getColonne() && tempCoord1.getLigne() == (tempCoord2.getLigne() - 1)) ||
            (tempCoord1.getColonne() == tempCoord2.getColonne() && tempCoord2.getLigne() == (tempCoord1.getLigne() - 1))) {
        } else {
                System.out.println("terrains non adjacents");
                return false;
        }

        
        //appele la methode recevoir tuile pour positionner la tuile
        boolean succes = this.grille.recevoirTuile(this.reservation[num], tempCoord1, terrain1, tempCoord2, terrain2);
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
    
    public int reserver(Tuile[] tirage,int[] tempOrdre, Grille grilleAdv) {
    	return 0;
    }
}
