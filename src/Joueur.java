
public abstract class Joueur {

    protected Grille grille;
    protected String nom;
    protected Tuile [] reservation;
    protected int [] ordre;
    protected Tuile [] defausse;
    protected int score;


    public Joueur(String nom){
        this.grille = new Grille();
        this.nom = nom;
        this.reservation = new Tuile[2];
        this.ordre = new int[2];
        this.defausse = new Tuile[12];			//nombre maximum de domino que peut avoir un joueur
        this.score = 0;
    }

    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    public void setReservation(Tuile[] reservation) {
        this.reservation = reservation;
    }

    public void setReservation(Tuile t){
        if(this.reservation[0] == null){
            this.reservation[0] = t;
        } else {
            this.reservation[1] = t;
        }
    }
    public void videReservation(){
        this.reservation = new Tuile[2];
    }

    public void setOrdre(int[] ordre) {
        this.ordre = ordre;
    }

    public void setOrdreIndice(int indice, int ordre) {
        this.ordre[indice] = ordre;
    }

    public void setDefausse(Tuile[] defausse) {
        this.defausse = defausse;
    }

    public void setScore(int score) {
        this.score = score;
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
    
    public abstract boolean placerTuile();
    
    public int reserver(Tuile[] tirage,int[] tempOrdre) {
    	return 0;
    }
    
    public int scoreSelection() {
		
		return 0;
	}
	
	public int scorePlacement() {
		
		return 0;
	}

    public void defausser(int num){
    	
    	System.out.println("defausse");
    	
        int i = 0;
        				//s'arrete quand est arrivee sur le premier emplacement vide de la défausse
        for (; this.defausse[i] != null; i++) {

        }
        				//place la tuile dans la defausse
        this.defausse[i] = this.reservation[num];
        				//enlever la tuile de la reservation
        this.reservation[num] = null;
    }

    public abstract boolean placerTuile(int num, Coord coord1, int terrrain1, Coord coord2, int terrain2);
}