import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Joueur {

    protected Grille grille;
    protected String nom;
    protected Tuile [] reservation;
    protected int [] ordre;
    protected Tuile [] defausse;
    protected int score;

    //attributs pour scores

    protected Grille grilleZone;
    List<int[]> champs;
    List<int[]> pres;
    List<int[]> lacs;
    List<int[]> marais;
    List<int[]> forets;
    List<int[]> mines;


    public Joueur(String nom){
        this.grille = new Grille();
        this.grilleZone = new Grille();
        this.nom = nom;
        this.reservation = new Tuile[2];
        this.ordre = new int[2];
        this.defausse = new Tuile[12];			//nombre maximum de domino que peut avoir un joueur
        this.score = 0;
        this.champs = new ArrayList<int[]>();
        this.pres = new ArrayList<int[]>();;
        this.lacs = new ArrayList<int[]>();;
        this.marais = new ArrayList<int[]>();;
        this.forets = new ArrayList<int[]>();;
        this.mines = new ArrayList<int[]>();;
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

    public Grille getGrilleZone() {
        return this.grilleZone;
    }

    public void setGrilleZone(Coord c, int[] cases) {
        this.grilleZone.setCase(c, cases);
    }

    public List<int[]> getChamps() {
        return champs;
    }


        //ajout d'un terrain dans la grille de zone
    public void majGrilleZone(int[] terrain, Coord c){

        Coord[] autour = this.getGrilleZone().getCasesAutour(c);
        Coord centre = new Coord(3,3);
        boolean terrainAutour = false; //si un terrain autour a ete trouve

        for(int i = 0;i < autour.length;i++){

            if(this.grilleZone.getCase(autour[i]) != null && autour[i] != centre) {

                if (this.grilleZone.getCase(autour[i])[0] == terrain[0]) { //si le même type de terrain
                    System.out.println("zone trouvée");

                    if(! terrainAutour) {
                        terrainAutour = true;   //dit qu'a déjà trouvé une zone
                        switch (terrain[0]) {
                            case (0):
                                this.majChamps(this.grilleZone.getCase(autour[i])[1], terrain);
                                break;
                            case (1):
                                this.majPres(this.grilleZone.getCase(autour[i])[1], terrain);
                                break;
                            case (2):
                                this.majLacs(this.grilleZone.getCase(autour[i])[1], terrain);
                                break;
                            case (3):
                                this.majMarais(this.grilleZone.getCase(autour[i])[1], terrain);
                                break;
                            case (4):
                                this.majForets(this.grilleZone.getCase(autour[i])[1], terrain);
                                break;
                            case (5):
                                this.majMines(this.grilleZone.getCase(autour[i])[1], terrain);
                                break;
                        }
                    this.grilleZone.setCase(c, new int[]{terrain[0], this.grilleZone.getCase(autour[i])[1]}); //rempli grille zone ou le terrain est pose
                                                                                                                        //met le type du terrain et la zone a laquelle est rattache
                    } else {
                        //a deja trouve un terrain et doit maintenant reunir deux zones
                        int indiceZone1 = this.grilleZone.getCase(c)[1];
                        int indiceZone2 = this.grilleZone.getCase((autour[i]))[1];
                        reunirZones(terrain[0],indiceZone1,indiceZone2);
                    }
                } else {
                    System.out.println("case autour non null mais pas bon terrain");
                }
            } else {
                System.out.println("case autour null");
            }
        }   //si n'a pas trouvé de zone a laquelle rattacher le terrain
        if(! terrainAutour){
            creerZone(terrain, c);
        }
    }
    public void creerZone(int[] terrain, Coord c){
        System.out.println("creation d'une zone pour la coord"+c.getLigne()+c.getColonne());
            //si ne rejoint pas une zone doit la creer
            int indice;
            switch (terrain[0]) {
                case (0):
                    this.champs.add(new int[] {1, terrain[1]});
                    indice = this.champs.size()-1;
                    break;
                case (1):
                    this.pres.add(new int[] {1, terrain[1]});
                    indice = this.pres.size()-1;
                    break;
                case (2):
                    this.lacs.add(new int[] {1, terrain[1]});
                    indice = this.lacs.size()-1;
                    break;
                case (3):
                    this.marais.add(new int[] {1, terrain[1]});
                    indice = this.marais.size()-1;
                    break;
                case (4):
                    this.forets.add(new int[] {1, terrain[1]});
                    indice = this.forets.size()-1;
                    break;
                case (5):
                    this.mines.add(new int[] {1, terrain[1]});
                    indice = this.mines.size()-1;
                    break;
                default:
                    indice = -1;
            }
            this.grilleZone.setCase(c, new int[]{terrain[0], indice}); //rempli grille zone ou le terrain est pose

    }

    public void calculScore() {
        this.score = 0;

        this.champs.forEach(champ -> {
            this.score = this.score + (champ[0] * champ[1]);
        });

        this.pres.forEach(pre -> {
            this.score = this.score + (pre[0] * pre[1]);
        });
        this.lacs.forEach( lac -> {
            this.score = this.score + (lac[0] * lac[1]);
        });
        this.marais.forEach(mar -> {
            this.score = this.score + (mar[0] * mar[1]);
        });
        this.forets.forEach(foret -> {
            this.score = this.score + (foret[0] * foret[1]);
        });
        this.mines.forEach(mine -> {
            this.score = this.score + (mine[0] * mine[1]);
        });
    }

    public void setChamps(List<int[]> champs) {
        this.champs = champs;
    }

    public void majChamps(int indice, int[] champs) {
        int [] zone = this.champs.get(indice);
        zone[0] = zone[0] + 1;  //rajoute 1 à la taille de la zone
        zone[1] = zone[1] + champs[1];  //ajoute le nombre de courrones apportées par le terrain
        this.champs.set(indice, zone);
    }

    public List<int[]> getPres() {
        return pres;
    }

    public void setPres(List<int[]> pres) {
        this.pres = pres;
    }

    public void majPres(int indice, int[] pres) {
        int [] zone = this.pres.get(indice);
        zone[0] = zone[0] + 1;  //rajoute 1 à la taille de la zone
        zone[1] = zone[1] + pres[1];  //ajoute le nombre de courrones apportées par le terrain
        this.pres.set(indice, zone);
    }

    public List<int[]> getLacs() {
        return lacs;
    }

    public void setLacs(List<int[]> lacs) {
        this.lacs = lacs;
    }

    public void majLacs(int indice, int[] lac) {
        int [] zone = this.lacs.get(indice);
        zone[0] = zone[0] + 1;  //rajoute 1 à la taille de la zone
        zone[1] = zone[1] + lac[1];  //ajoute le nombre de courrones apportées par le terrain
        this.lacs.set(indice, zone);
    }

    public List<int[]> getMarais() {
        return marais;
    }

    public void setMarais(List<int[]> marais) {
        this.marais = marais;
    }

    public void majMarais(int indice, int[] marais) {
        int [] zone = this.marais.get(indice);
        zone[0] = zone[0] + 1;  //rajoute 1 à la taille de la zone
        zone[1] = zone[1] + marais[1];  //ajoute le nombre de courrones apportées par le terrain
        this.marais.set(indice, zone);
    }

    public List<int[]> getForets() {
        return forets;
    }

    public void setForets(List<int[]> forets) {
        this.forets = forets;
    }

    public void majForets(int indice, int[] foret) {
        int [] zone = this.forets.get(indice);
        zone[0] = zone[0] + 1;  //rajoute 1 à la taille de la zone
        zone[1] = zone[1] + foret[1];  //ajoute le nombre de courrones apportées par le terrain
        this.forets.set(indice, zone);
    }

    public List<int[]> getMines() {
        return mines;
    }

    public void setMines(List<int[]> mines) {
        this.mines = mines;
    }

    public void majMines(int indice, int[] mine) {
        int [] zone = this.mines.get(indice);
        zone[0] = zone[0] + 1;  //rajoute 1 à la taille de la zone
        zone[1] = zone[1] + mine[1];  //ajoute le nombre de courrones apportées par le terrain
        this.mines.set(indice, zone);
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

    public void reunirZones(int typeTerrain, int indiceZone1, int indiceZone2){
        int nbZones = -1;
        int[] changee;
        int[] detruite;
        int petit;
        int grand;
        if(indiceZone1 >= indiceZone2){
            petit = indiceZone2;
            grand = indiceZone1;
        } else {
            petit = indiceZone1;
            grand = indiceZone2;
        }
        switch (typeTerrain) {
            case (0):
                nbZones = this.champs.size();
                detruite = this.champs.get(grand);
                changee = this.champs.get(petit);
                changee[0] = changee[0] + detruite[0];
                changee[1] = changee[1] + detruite[1];
                this.champs.set(petit, changee);
                this.champs.remove(grand);
                break;
            case (1):
                nbZones = this.pres.size();
                detruite = this.pres.get(grand);
                changee = this.pres.get(petit);
                changee[0] = changee[0] + detruite[0];
                changee[1] = changee[1] + detruite[1];
                this.pres.set(petit, changee);
                this.pres.remove(grand);
                break;
            case (2):
                nbZones = this.lacs.size();
                detruite = this.lacs.get(grand);
                changee = this.lacs.get(petit);
                changee[0] = changee[0] + detruite[0];
                changee[1] = changee[1] + detruite[1];
                this.lacs.set(petit, changee);
                this.lacs.remove(grand);
                break;
            case (3):
                nbZones = this.marais.size();
                detruite = this.marais.get(grand);
                changee = this.marais.get(petit);
                changee[0] = changee[0] + detruite[0];
                changee[1] = changee[1] + detruite[1];
                this.marais.set(petit, changee);
                this.marais.remove(grand);
                break;
            case (4):
                nbZones = this.forets.size();
                detruite = this.forets.get(grand);
                changee = this.forets.get(petit);
                changee[0] = changee[0] + detruite[0];
                changee[1] = changee[1] + detruite[1];
                this.forets.set(petit, changee);
                this.forets.remove(grand);
                break;
            case (5):
                nbZones = this.mines.size();
                detruite = this.mines.get(grand);
                changee = this.mines.get(petit);
                changee[0] = changee[0] + detruite[0];
                changee[1] = changee[1] + detruite[1];
                this.mines.set(petit, changee);
                this.mines.remove(grand);
                break;
        }
                //mise a jour de la grille zone
        for(int i = 0; i < this.grilleZone.getContenu().length; i++){
            for(int j =0; j < this.grilleZone.getContenu()[i].length; j++){
                if(this.grilleZone.getCase(new Coord(i,j)) != null) {
                    //recupère le contenu de la case de coord ij si pas nulle
                    int[] terrain = this.grilleZone.getCase(new Coord(i, j));
                    //vérifie que c'est le type de terrain des zones reliees
                    if (terrain[0] == typeTerrain) {
                                //le deplace dans la zone qui absorbe si fait partie de la zone absorbee
                        if (terrain[1] == grand) {
                            this.grilleZone.setCase(new Coord(i, j), new int[]{typeTerrain, petit});
                        }
                        if (terrain[1] > grand) {
                                //reduit l'indice s'il etait dans un zone apres celle supprimee
                            this.grilleZone.setCase(new Coord(i, j), new int[]{typeTerrain, (terrain[1] - 1)});
                        }
                    }
                }
            }
        }

    }


    public void afficheZones(){
        System.out.println("les champs");
        this.champs.forEach(c -> {
            System.out.println("zone de taille "+c[0]+" et avec "+c[1]+" courrones)");
        });
        System.out.println("les pres");
        this.pres.forEach(c -> {
            System.out.println("zone de taille "+c[0]+" et avec "+c[1]+" courrones)");
        });
        System.out.println("les lacs");
        this.lacs.forEach(c -> {
            System.out.println("zone de taille "+c[0]+" et avec "+c[1]+" courrones)");
        });
        System.out.println("les marais");
        this.marais.forEach(c -> {
            System.out.println("zone de taille "+c[0]+" et avec "+c[1]+" courrones)");
        });
        System.out.println("les forets");
        this.forets.forEach(c -> {
            System.out.println("zone de taille "+c[0]+" et avec "+c[1]+" courrones)");
        });
        System.out.println("les mines");
        this.mines.forEach(c -> {
            System.out.println("zone de taille "+c[0]+" et avec "+c[1]+" courrones)");
        });
    }
}