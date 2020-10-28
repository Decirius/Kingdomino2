import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Partie {
    private Tuile[] pile = {new Tuile(new int[] {2,1},new int[] {0,1},1),new Tuile(new int[] {4,3},new int[] {2,0},2)};
    private Joueur j1;
    private Joueur j2;
    private Tuile[] tirage;

    public Partie(String j1, String j2){
        this.j1 = new Joueur(j1);
        this.j2 = new Joueur((j2));
        this.tirage = new Tuile[4];
    }

    public void melangerPile(){
        List<Tuile> intList = Arrays.asList(this.pile);//transforme le tableau en liste
        Collections.shuffle(intList);//melange la liste
        intList.toArray(this.pile);//retransforme la liste en un tableau
    }

    public void faireTirage() {
        this.tirage = Arrays.copyOfRange(this.pile, 0, 4);
        this.pile = Arrays.copyOfRange(this.pile, 4, this.pile.length);
    }

    public Tuile[] getTirage(){
        return  this.tirage;
    }

    public void setTirage(Tuile[] tab){
        this.tirage = tab;
    }

    public Joueur getJ1(){
        return  this.j1;
    }

    public Joueur getJ2(){
        return this.j2;
    }


}
