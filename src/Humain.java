
public class Humain extends Joueur {

    public Humain(String nom){
        super(nom);
    }

    //prend num la position de la tuile dans reservation les deux terrains(1 ou 2) et leur coordonnées
    //retourne true si a marché
    public boolean placerTuile(int num, Coord coord1, int terrain1, Coord coord2, int terrain2){


        //vérifie que les deux coordonnées sont adjacentes
        if ( (coord1.getLigne() == coord2.getLigne() && coord1.getColonne() == (coord2.getColonne() - 1)) ||
            (coord1.getLigne() == coord2.getLigne() && coord2.getColonne() == (coord1.getColonne() - 1)) ||
            (coord1.getColonne() == coord2.getColonne() && coord1.getLigne() == (coord2.getLigne() - 1)) ||
            (coord1.getColonne() == coord2.getColonne() && coord2.getLigne() == (coord1.getLigne() - 1))) {
                System.out.println("ok t'as bien positionné");
        } else {
                System.out.println("terrains non adjacents");
                return false;
        }


        //appele la methode recevoir tuile pour positionner la tuile
        boolean succes = this.grille.recevoirTuile(this.reservation[num], coord1, terrain1, coord2, terrain2);
        if(succes){
            this.reservation[num] = null;
            return true;
        }
        return false;
    }
}
