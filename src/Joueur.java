
public class Joueur {

    protected Grille grille;
    protected String nom;
    protected Tuile [] reservation;
    protected int [] ordre;
    protected Tuile [] defausse;
    protected int score;

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public void setReservation(Tuile[] reservation) {
        this.reservation = reservation;
    }

    public void setOrdre(int[] ordre) {
        this.ordre = ordre;
    }

    public void setDefausse(Tuile[] defausse) {
        this.defausse = defausse;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Joueur(String nom){
        this.grille = new Grille();
        this.nom = nom;
        this.reservation = new Tuile[2];
        this.ordre = new int[2];
        this.defausse = new Tuile[12];//nombre maximum de domino que peut avoir un joueur
        this.score = 0;
    }

    //prend en l'id num et les deux terrains t1 et t2 de la tuile et les coord respectives de chaque terrain coord1, coord2
    //retourne true si a marché
    public boolean placerTuile(int num, Coord coord1, Coord coord2){
        //trouve la position de la tuile dans la reservation
        int i = 0;
        for(;this.reservation[i].getId() != num;i++){
        }

        //modifie l'orientation de la tuile selon les terrains de positionnement choisis
        //vérifie orientation 0
        if(coord1.getLigne() == coord2.getLigne() && coord1.getColonne() == (coord2.getColonne() - 1)) {
            this.reservation[i].setOrientation(0);
        } else //vérifie position 2
            if(coord1.getLigne() == coord2.getLigne() && coord2.getColonne() == (coord1.getColonne() - 1)) {
                this.reservation[i].setOrientation(2);
            } else //vérifie position 1
                if(coord1.getColonne() == coord2.getColonne() && coord1.getLigne() == (coord2.getLigne() - 1)) {
                    this.reservation[i].setOrientation(1);
                } else //vérifie position 3
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

    public void defausser(int num){
        int i = 0;
        //s'arrete quand est arrivé sur le premier emplacement vide de la défausse
        for (; this.defausse[i] != null; i++) {

        }
        //place la tuile dans la defausse
        this.defausse[i] = this.reservation[num];
        //enlever la tuile de la reservation
        this.reservation[num] = null;
    }

    public Grille getGrille() {
        return grille;
    }

    public String getNom() {
        return nom;
    }

    public Tuile[] getReservation() {
        return reservation;
    }

    public int[] getOrdre() {
        return ordre;
    }

    public Tuile[] getDefausse() {
        return defausse;
    }

    public int getScore() {
        return score;
    }
}