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

    //entree correspond à un terrain d'une tuile
    public void setCase(Coord c,int[] entree){
        this.contenu[c.getLigne()][c.getColonne()] = entree;
    }

    public boolean verifieValidite(Coord coord, int[] terrain) {
        if(!this.estLibre(coord)){
            return false;
        }
        Coord [] cases = this.getCasesAutour(coord);
        int[] centre = new int[] {-1,-1};
        for (int i = 0; i < cases.length; i++) {
            if(this.getCase(cases[i]) != null && (this.getCase(cases[i])[0] == terrain[0] || Arrays.equals(this.getCase(cases[i]), centre))) {
                return true;
            }
        }
        return false;
    }

    public Coord[] getCasesAutour(Coord cor) {
        int ligne = cor.getLigne();
        int colonne = cor.getColonne();
        if(ligne == 0 && colonne ==0){
            return new Coord[] {new Coord(ligne+1,colonne), new Coord(ligne,colonne+1)};
        }
        if(ligne == 0 && colonne == 6){
            return new Coord[] {new Coord(ligne+1,colonne),new Coord(ligne,colonne-1)};
        }
        if(ligne == 0){
            return new Coord[] {new Coord(ligne+1,colonne),new Coord(ligne,colonne+1),new Coord(ligne,colonne-1)};
        }
        if(ligne == 6 && colonne ==0){
            return new Coord[] {new Coord(ligne-1,colonne),new Coord(ligne,colonne+1)};
        }
        if(ligne == 6 && colonne == 6){
            return new Coord[] {new Coord(ligne-1,colonne),new Coord(ligne,colonne-1)};
        }
        if(ligne == 6){
            return new Coord[] {new Coord(ligne-1,colonne),new Coord(ligne,colonne+1),new Coord(ligne,colonne-1)};
        }
        else {
            return new Coord[] {new Coord(ligne + 1, colonne), new Coord(ligne - 1, colonne),
                    new Coord(ligne, colonne + 1), new Coord(ligne, colonne - 1)};
        }
    }

    public boolean recevoirTuile(Tuile tuile, Coord coord1, Coord coord2) {
        int orientation = tuile.getOrientation();
        if(orientation == 0 || orientation == 1){
            if(this.verifieValidite(coord1,tuile.getTerrain1()) || this.verifieValidite(coord2, tuile.getTerrain2())){
                this.setCase(coord1, tuile.getTerrain1());
                this.setCase(coord2, tuile.getTerrain2());
                return true;
            }
        return false;
        }
        if(orientation == 2 || orientation == 3){
            if(this.verifieValidite(coord2,tuile.getTerrain1()) || this.verifieValidite(coord1, tuile.getTerrain2())){
                this.setCase(coord2, tuile.getTerrain1());
                this.setCase(coord1, tuile.getTerrain2());
                return true;
            }
            return false;
        }
        return false;
    }



    public static void main(String[] args) {
    Grille grille = new Grille();
    System.out.println(Arrays.deepToString(grille.getContenu()));
    Coord coordMilieu = new Coord(3,3);
    System.out.println(Arrays.toString(grille.getCase(coordMilieu)));
    Coord coord = new Coord(2,3);
    grille.setCase(coord, new int[] {2,1});
    System.out.println(grille.estLibre(coord));
    System.out.println(Arrays.deepToString(grille.getContenu()));
    }

}
