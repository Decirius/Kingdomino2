import java.util.*;

public abstract class Ia extends Joueur {

    public Ia(String nom) {
        super(nom);
    }

    public abstract int Reserver(Tuile[] tirage);

    public abstract boolean placerTuile();
    
    //retourne toutes les coordonnees de la grille qui contiennent le meme terrain qu'en parametre
    public List<Coord> chercherTerrain(int terrain) {
        List<Coord> liste = new ArrayList<Coord>();
        for (int i = 0; i < this.getGrille().getContenu().length; i++) {
            for (int j = 0; j < this.getGrille().getContenu()[i].length; j++) {
                //stocke si c'est le meme terrain ou le centre de la grille
                if (this.getGrille().getCase(new Coord(i, j))[0] == terrain || this.getGrille().getCase(new Coord(i, j))[0] == -1) {
                    liste.add(new Coord(i, j));
                }
            }
        }
        return liste;
    }

    //retourne la liste de toutes les combinaisons de placement de tuile pres d'un terrain
    //les combinaisons sont des tableaux de deux coordonnées
    //la premiere coordonnee est celle a mettre pres du terrain
    public List<Coord[]> chercherPlace(List<Coord> terrains) {
        List<Coord[]> places = new ArrayList<Coord[]>();
        terrains.forEach(terrain -> {
            //les cases vides autour du terrain
            Coord[] cases = this.getGrille().getCasesAutour(terrain);
            for (int i = 0; i < cases.length; i++) {
                //les cases vides autour de la case vide (cherche les espaces a peut mettre une tuile)
                Coord[] cases2 = this.getGrille().getCasesAutour(cases[i]);
                for (int j = 0; j < cases2.length; j++) {
                    //si la case autour de la case vide est aussi vide, ajoute a la liste
                    if (this.getGrille().getCase(cases2[j]) == null) {
                        Coord[] tab = {cases[i], cases2[j]};
                        places.add(tab);
                    }
                }
            }
        });
        return places;
    }

}
