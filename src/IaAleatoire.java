import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IaAleatoire extends Ia
{
    public IaAleatoire(String nom) {
        super(nom);
    }

		    //tire une tuile aleatoirement dans le tableau de tirage passee en parametre
		    //si la tuile est deja� prise recommence
		    //l'ordre n'est pas mis a� jour dans cette méthode
    
    public int reserver(Tuile[] tirage,int[] tempOrdre) {
        //prend la partie entiere d'un nombre compris entre 0 et 3,99999 : entier entre 0 et 4
        int i = (int) (Math.random() * 4);
        
        while (tempOrdre[i]!=0) {
            i = (int) (Math.random() * 4);
        }
        this.setReservation(tirage[i]);
        														//return la position de la tuile choisie dans le tableau (devra passer cette tuile a null car elle est reservee)
        return i;
    }

                //return true si une tuile a ete placee ou defaussee
                //met a jour la reservation
    public boolean placerTuile() {

        int tuile = Math.random() >= 0.5 ? 1 : 0;   //choisi une des tuiles reservees au hasard
        if (this.getReservation()[tuile] == null)   //si celle choisie est deja placee prend l'autre
            tuile = tuile == 0 ? 1 : 0;
        int terrain = Math.random() >= 0.5 ? 1 : 0; //choisi un des terrain de la tuile au hasard

        							//cherche a placer sa tuile
        int typeTerrain = terrain == 0 ? this.getReservation()[tuile].getTerrain1()[0] : this.getReservation()[tuile].getTerrain2()[0]; //recupere type du terrain choisi

                                    //recupere les terrains qui rendent possible de jouer ce terrain de la tuile
        List<Coord> terrains = this.chercherTerrain(typeTerrain);   //listes des emplacements jouable        

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

        if (places.isEmpty()) {         //s'il n'y a toujours pas d'emplacement jouables defausse la tuile
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
                this.majGrilleZone(this.getReservation()[tuile].getTerrain1(), emplacement[0]);
             
                this.majGrilleZone(this.getReservation()[tuile].getTerrain2(), emplacement[1]);

                
                this.calculScore();
                
                this.getReservation()[tuile] = null;    //retire de la reservation avant de renvoyer true
                return true;
            }
        } else {
            if (this.getGrille().recevoirTuile(this.getReservation()[tuile], emplacement[0], 2, emplacement[1], 1)) {
            	
                this.majGrilleZone(this.getReservation()[tuile].getTerrain2(), emplacement[0]);
                
                this.majGrilleZone(this.getReservation()[tuile].getTerrain1(), emplacement[1]);

                
                this.calculScore();
                
                this.getReservation()[tuile] = null;    //retire de la reservation avant de renvoyer true
                return true;
            }
        }

        
        return false;   //si malgre tout un probleme renvoie false
    }
    
    
    
    public boolean placerTuile(int num, Coord coord1, int terrrain1, Coord coord2, int terrain2){
        return false;
    }

}
