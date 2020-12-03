import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        Partie partie1 = new Partie("nom","nom2", 0, 0);
        Joueur joueur = partie1.getJ1();
        Grille grille = joueur.getGrille();
        System.out.println(Arrays.deepToString(grille.getContenu()));
        System.out.println("grille libre en 2,3"+grille.estLibre(new Coord(2, 3)));
        System.out.println("coord valide en 2,3 pour 1 "+grille.verifieValidite(new Coord(2,3), new int[]{1,2}));
        System.out.println("coord valide en 2,2 pour 1 "+grille.verifieValidite(new Coord(2,2),new int[] {1,2}));
        System.out.println("");

        grille.setCase(new Coord(2,3),new int[] {1,2});
        System.out.println(Arrays.deepToString(grille.getContenu()));
        System.out.println("grille libre en 2,3"+grille.estLibre(new Coord(2, 3)));
        System.out.println("coord valide en 2,2 pour 1 "+grille.verifieValidite(new Coord(2,2), new int []{1,2}));
        System.out.println("coord valide en 2,2 pour 2 "+grille.verifieValidite(new Coord(2,2), new int[]{2,2}));
        System.out.println("coord valide en 3,2 pour 2 "+grille.verifieValidite(new Coord(3,2), new int []{2,2}));
        System.out.println("");

        grille.setCase(new Coord(3,2),new int [] {2,2});
        System.out.println(Arrays.deepToString(grille.getContenu()));
        System.out.println("coord valide en 2,2 pour 2"+grille.verifieValidite(new Coord(2,2), new int[] {2,2}));
        grille.setCase(new Coord(1,5),new int []{3,2});
        System.out.println(Arrays.deepToString(grille.getContenu()));
        System.out.println("coord valide en 0,5 pour 2"+grille.verifieValidite(new Coord(0,5),new int [] {2,2}));
        System.out.println("coord valide en 0,5 pour 3"+grille.verifieValidite(new Coord(0,5), new int []{3,2}));
        System.out.println(Arrays.deepToString(grille.getContenu()));
        System.out.println("");

        Tuile tuile1 = new Tuile(new int[] {4,0}, new int[]{5,1}, 1);
        Tuile tuile2 = new Tuile(new int[]{4,0},new int[] {2,1}, 2);
        System.out.println("essai1 tuile");
        System.out.println(grille.recevoirTuile(tuile1, new Coord(2, 1),new Coord(2, 2)));
        System.out.println(Arrays.deepToString(grille.getContenu()));
        System.out.println("essai2 tuile");
        System.out.println(grille.recevoirTuile(tuile2, new Coord(2, 1), new Coord(2, 2)));
        System.out.println(Arrays.deepToString(grille.getContenu()));
    }
}
