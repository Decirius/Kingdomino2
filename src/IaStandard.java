import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IaStandard extends Ia {

	public IaStandard(String nom) {
		super(nom);
	}

	@Override
	public boolean placerTuile() {
						// TODO On garde la selection au hasard pour l'instant
		
		int tuile = Math.random() >= 0.5 ? 1 : 0;   //choisi une des tuiles reservees au hasard

	    if (this.getReservation()[tuile] == null)   //si celle choisie est deja placee prend l'autre
	    	tuile = tuile == 0 ? 1 : 0;

	    int terrain = Math.random() >= 0.5 ? 1 : 0; //choisi un des terrain de la tuile au hasard
	     
		//cherche a placer sa tuile
	    int typeTerrain = terrain == 0 ? this.getReservation()[tuile].getTerrain1()[0] : this.getReservation()[tuile].getTerrain2()[0]; //recupere type du terrain choisi
	   
	                                    //recupere les terrains qui rendent possible de jouer ce terrain de la tuile
	    List<Coord> terrains = this.chercherTerrain(typeTerrain);   //listes des emplacements jouables
	   

	    List<Coord[]> places = new ArrayList<Coord[]>();

	    if (!terrains.isEmpty()) { //si la liste n'est pas vide
	        places = this.chercherPlace(terrains);  //cherche les emplacments jouables
	       
	    }

	    if (places.isEmpty()) {    //s'il n'y a pas d'emplacements jouables cherche sur l'autre terrain de la tuile
	        
	        terrain = terrain == 0 ? 1 : 0;
	        typeTerrain = terrain == 0 ? this.getReservation()[tuile].getTerrain1()[0] : this.getReservation()[tuile].getTerrain2()[0];
	        
	        terrains = this.chercherTerrain(typeTerrain);
	        
	        if (!terrains.isEmpty()) {
	            places = this.chercherPlace(terrains);
	            
	        }
	    }

	        												//s'il n'y a toujours pas d'emplacement jouables defausse la tuile
	    if (places.isEmpty()) {
	        this.getReservation()[tuile] = null;
	        this.defausser(tuile);
	        return true;
	    }
	        														//sinon prend un des emplacements au hasard
	    Collections.shuffle(places);
	    Coord[] emplacement = places.get(0);
	   														//essaye de poser la tuile (normalement cela marche et renvoi true)
	    if(terrain == 0) {
	        if (this.getGrille().recevoirTuile(this.getReservation()[tuile], emplacement[0], 1, emplacement[1], 2)) {

	            this.getReservation()[tuile] = null;    //retire de la reservation avant de renvoyer true
	            return true;
	        }
	    } else {
	        if (this.getGrille().recevoirTuile(this.getReservation()[tuile], emplacement[0], 2, emplacement[1], 1)) {

	            this.getReservation()[tuile] = null;    //retire de la reservation avant de renvoyer true
	            return true;
	        }
	    }

	    return false;   //si malgre tout un probleme renvoie false
	     	
	}
	
	public int reserver(Tuile[] tirage,int[] tempOrdre) {
		
		// TODO : a modifier en fonction des tuiles déjà placées
		int reserved=-1;
		int max=0;
		int[] scoreTuile= new int[4];
												
        for (int i=0;i<4;i++) {					// Pour chaque tuile, on calcule son score	
        	scoreTuile[i]=scoreSelection(tirage[i]);
        }
        
        for (int j=0;j<4;j++) {					// On prend le score le + haut
	        if (tempOrdre[j]==0) {	
        		if (scoreTuile[j]>=max) {
	        		reserved=j;
	        		max=scoreTuile[j];
	        	}
	        }
        }
        
        this.setReservation(tirage[reserved]);
        										
        return reserved;						// On reserve
    }
	
	public int scoreSelection(Tuile t) {
												// Calcule d'un score de selection pour la tuile
		int scoreT1=0;							// On initialise un score pour chaque terrain
		int scoreT2=0;
												// Il prioritise les terrains de type qu'il a deja
		for (int i=0;i<7;i++) {
			for (int j=0;j<7;j++) {
				Coord coord = new Coord(i,j);
				if (this.getGrille().getCase(coord)!=null) {
					if (this.getGrille().getCase(coord)[0]==t.getTerrain1()[0]) {
						scoreT1+=1;
					}
					if (this.getGrille().getCase(coord)[0]==t.getTerrain2()[0]) {
						scoreT2+=1;
					}
				}
			}
		}
		
		return (scoreT1+scoreT2);
	}
	
	public int scorePlacement() {
		
		return 0;
	}
	
	

	@Override
	public boolean placerTuile(int num, Coord coord1, int terrrain1, Coord coord2, int terrain2) {
		return false;
	}

}
