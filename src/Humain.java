
public class Humain extends Joueur {

    public Humain(String nom){
        super(nom);
    }

    //prend en l'id num et les deux terrains t1 et t2 de la tuile et les coord respectives de chaque terrain coord1, coord2
    //retourne true si a marche
    public boolean placerTuile(int num, Coord coord1, Coord coord2){
    	
        //trouve la position de la tuile dans la reservation
        int i = num;

        //modifie l'orientation de la tuile selon les terrains de positionnement choisis
        //verifie orientation 0
        if(coord1.getLigne() == coord2.getLigne() && coord1.getColonne() == (coord2.getColonne() - 1)) {
            this.reservation[i].setOrientation(0);
            
        } else //verifie position 2
            if(coord1.getLigne() == coord2.getLigne() && coord2.getColonne() == (coord1.getColonne() - 1)) {
                this.reservation[i].setOrientation(2);
                
            } else //verifie position 1
                if(coord1.getColonne() == coord2.getColonne() && coord1.getLigne() == (coord2.getLigne() - 1)) {
                    this.reservation[i].setOrientation(1);
                    
                } else //verifie position 3
                    if(coord1.getColonne() == coord2.getColonne() && coord2.getLigne() == (coord1.getLigne() - 1)){
                        this.reservation[i].setOrientation(3);
                        
                    } else {
                        //les terrains ne sont pas adjacents
                        return false;
                    }

        //appele la methode recevoir tuile pour positionner la tuile
        boolean succes = this.grille.recevoirTuile(this.reservation[i], coord1,coord2);
        if(succes){
            this.reservation[num] = null;
            return true;
        }
        return false;
    }
}
