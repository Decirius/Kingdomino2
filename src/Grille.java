import java.awt.*;
import java.util.Arrays;

public class Grille {

    private int[][][] contenu;

    public Grille() {
        this.contenu = new int[7][7][2];
        for (int i = 0; i < this.contenu.length; i++) {
            for (int j = 0; j < contenu[i].length; j++) {
                this.contenu[i][j] = null;
            }
        }
        int[] init = {-1, -1};
        this.contenu[3][3] = init;
    }
    
    public int[] getCase(Coord coord) {
        return this.contenu[coord.getLigne()][coord.getColonne()];
    }

    public int[][][] getContenu() {
        return  this.contenu;
    }

    public boolean estLibre(Coord c) {
        int[] Case = this.getCase(c);
        return Case == null;
    }

    //entree correspond a un terrain d'une tuile
    public void setCase(Coord c,int[] entree){
        this.contenu[c.getLigne()][c.getColonne()] = entree;
    }

    //verifie que la case est libre et qu'une des cases autour a le même type de terrain
    public boolean verifieValidite(Coord coord, int[] terrain) {
        //verfie que la case est libre
        if(!this.estLibre(coord)){
            return false;
        }
        //recupere les terrains autour
        Coord [] cases = this.getCasesAutour(coord);
        int[] centre = new int[] {-1,-1};
        for (int i = 0; i < cases.length; i++) {
            //compare les terrains autour (que sont occupees et que le terrain est identique)
            if(this.getCase(cases[i]) != null && (this.getCase(cases[i])[0] == terrain[0] || Arrays.equals(this.getCase(cases[i]), centre))) {
                return true;
            }
        }
        return false;
    }

    public Coord[] getCasesAutour(Coord cor) {
        int ligne = cor.getLigne();
        int colonne = cor.getColonne();
      
        if (ligne == 0 && colonne == 0) {
            return new Coord[]{new Coord(ligne + 1, colonne), new Coord(ligne, colonne + 1)};
        }
        if (ligne == 0 && colonne == 6) {
            return new Coord[]{new Coord(ligne + 1, colonne), new Coord(ligne, colonne - 1)};
        }
        if (ligne == 0) {
            return new Coord[]{new Coord(ligne + 1, colonne), new Coord(ligne, colonne + 1), new Coord(ligne, colonne - 1)};
        }
        if (ligne == 6 && colonne == 0) {
            return new Coord[]{new Coord(ligne - 1, colonne), new Coord(ligne, colonne + 1)};
        }
        if (ligne == 6 && colonne == 6) {
            return new Coord[]{new Coord(ligne - 1, colonne), new Coord(ligne, colonne - 1)};
        }
        if (ligne == 6) {
            return new Coord[]{new Coord(ligne - 1, colonne), new Coord(ligne, colonne + 1), new Coord(ligne, colonne - 1)};
        }
        if (colonne == 0) {
            return new Coord[]{new Coord(ligne - 1, colonne), new Coord(ligne + 1, colonne ), new Coord(ligne, colonne + 1)};
        }
        if (colonne == 6) {
            return new Coord[]{new Coord(ligne - 1, colonne), new Coord(ligne + 1, colonne), new Coord(ligne, colonne - 1)};
        }
        else {
            return new Coord[] {new Coord(ligne + 1, colonne), new Coord(ligne - 1, colonne),
                    new Coord(ligne, colonne + 1), new Coord(ligne, colonne - 1)};
        }
    }

    //retourne false et ne fait rien si c'est impossible de placer la tuile sur les coordonnées données
    public boolean recevoirTuile(Tuile tuile, Coord coord1, int terrain1, Coord coord2, int terrain2) {
    						// la tuile			coord  & indice 1er terrain      coord & indice 2n terrain
    	
    	System.out.println("----------------------------recevoirTuile()----------------------");
    	System.out.println("id tuile : "+tuile.getId());
	    
        if(terrain1 == 1){
        	
        	System.out.println("terrain1 :" + tuile.getTerrain1()[0]  +" aux coordonnees "+coord1.getLigne()+coord1.getColonne());
    	    System.out.println("terrain2 : " + tuile.getTerrain2()[0] + " aux coordonnees "+coord2.getLigne()+coord2.getColonne());
        	
            if(this.verifieValidite(coord1,tuile.getTerrain1()) || this.verifieValidite(coord2, tuile.getTerrain2())){
                this.setCase(coord1, tuile.getTerrain1());
                this.setCase(coord2, tuile.getTerrain2());
                return true;
            }
        return false;
        }
        if(terrain1 ==2){
        	System.out.println("terrain1 " + tuile.getTerrain2()[0]  +" aux coordonnees "+coord1.getLigne()+coord1.getColonne());
    	    System.out.println("terrain2 : " + tuile.getTerrain1()[0]+ " aux coordonnees "+coord2.getLigne()+coord2.getColonne());
    	    
	        if(this.verifieValidite(coord2,tuile.getTerrain1()) || this.verifieValidite(coord1, tuile.getTerrain2())){
                this.setCase(coord2, tuile.getTerrain1());
                this.setCase(coord1, tuile.getTerrain2());
                return true;
            }
            return false;
        }
        return false;
    }
    
    public boolean grilleComplete() {
    	for (int i=0;i<7;i++) {
    		for (int j=0;j<7;j++) {
    			if (this.getCase(new Coord(i,j))==null) {
    				return false;
    			}
    		}
    	} 
    	return true;
    }
    

}
