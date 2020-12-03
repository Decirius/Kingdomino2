import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IaAleatoire extends Ia
{
    public IaAleatoire(String nom) {
        super(nom);
    }

    //tire une tuile aléatoirement dans le tableau de tirage passé en paramètre
    //si la tuile est déjà prise recommence
    //l'ordre n'est pas mis à jour dans cette méthode
    public int Reserver(Tuile[] tirage) {
        //prend la partie entière d'un nombre compris entre 0 et 3,99999 : entier entre 0 et 4
        int i = (int) (Math.random() * 4);
        while (tirage[i] == null) {
            i = (int) (Math.random() * 4);
        }
        this.setReservation(tirage[i]);
        //return la position de la tuile choisie dans le tableau (devra passer cette tuile à null car elle est reservée)
        return i;
    }

    //return true si une tuile a été placée ou défaussée
    //met à jour la reservation
    public boolean placerTuile() {
        //choisi une des tuiles reservées au hasard
        int tuile = Math.random() >= 0.5 ? 1 : 0;
        //si celle choisie est déjà placée prend l'autre
        if (this.getReservation()[tuile] == null)
            tuile = tuile == 0 ? 1 : 0;
        //choisi un des terrain de la tuile au hasard
        int terrain = Math.random() >= 0.5 ? 1 : 0;

        //cherche à placer sa tuile
        //récupère type du terrain choisi
        int typeTerrain = terrain == 0 ? this.getReservation()[tuile].getTerrain1()[0] : this.getReservation()[tuile].getTerrain2()[0];
        //récupère les terrains qui rendent possible de jouer ce terrain de la tuile
        List<Coord> terrains = this.chercherTerrain(typeTerrain);
        //listes des emplacements jouables
        List<Coord[]> places = new ArrayList<Coord[]>();
        //si la liste n'est pas vide
        if (!terrains.isEmpty()) {
            //cherche les emplacments jouables
            places = this.chercherPlace(terrains);
        }
        //s'il n'y a pas d'emplacements jouables cherche sur l'autre terrain de la tuile
        if (places.isEmpty()) {
            terrain = terrain == 0 ? 1 : 0;
            typeTerrain = terrain == 0 ? this.getReservation()[tuile].getTerrain1()[0] : this.getReservation()[tuile].getTerrain2()[0];
            terrains = this.chercherTerrain(typeTerrain);
            if (!terrains.isEmpty()) {
                places = this.chercherPlace(terrains);
            }
        }
        //s'il n'y a toujours pas d'emplacement jouables défausse la tuile
        if (places.isEmpty()) {
            this.getReservation()[tuile] = null;
            this.defausser(tuile);
            return true;
        }
        //sinon prend un des emplacements au hasard
        Collections.shuffle(places);
        Coord[] emplacement = places.get(0);
        //essaye de poser la tuile (normalement cela marche et renvoi true)
        if (this.getGrille().recevoirTuile(this.getReservation()[tuile], emplacement[0], emplacement[1])) {
            //retire de la reservation avant de renvoyer true
            this.getReservation()[tuile] = null;
            return true;
        }
        //si malfré tout un problème renvoie false
        return false;
    }
    public boolean placerTuile(int num, Coord coord1, Coord coord2){
        return false;
    }

}
