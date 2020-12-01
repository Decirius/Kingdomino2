import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Partie {
    private Tuile[] pile = {new Tuile(new int[] {0,0},new int[] {0,0},1),
							new Tuile(new int[] {0,0},new int[] {0,0},2),    						
							new Tuile(new int[] {4,0},new int[] {4,0},3),    		
							new Tuile(new int[] {4,0},new int[] {4,0},4),    		
							new Tuile(new int[] {4,0},new int[] {4,0},5),    		
							new Tuile(new int[] {4,0},new int[] {4,0},6),
							new Tuile(new int[] {2,0},new int[] {2,0},7), 
							new Tuile(new int[] {2,0},new int[] {2,0},8),
							new Tuile(new int[] {2,0},new int[] {2,0},9),
							new Tuile(new int[] {1,0},new int[] {1,0},10),
							new Tuile(new int[] {1,0},new int[] {1,0},11),
							new Tuile(new int[] {3,0},new int[] {3,0},12),
							new Tuile(new int[] {0,0},new int[] {4,0},13),
							new Tuile(new int[] {0,0},new int[] {2,0},14),
							new Tuile(new int[] {0,0},new int[] {1,0},15),
							new Tuile(new int[] {0,0},new int[] {3,0},16),
							new Tuile(new int[] {4,0},new int[] {2,0},17),
							new Tuile(new int[] {4,0},new int[] {1,0},18),
							new Tuile(new int[] {0,1},new int[] {4,0},19),
							new Tuile(new int[] {0,1},new int[] {2,0},20),
							new Tuile(new int[] {0,1},new int[] {1,0},21),
							new Tuile(new int[] {0,1},new int[] {3,0},22),
							new Tuile(new int[] {0,1},new int[] {5,0},23),
							new Tuile(new int[] {4,1},new int[] {0,0},24),
							new Tuile(new int[] {4,1},new int[] {0,0},25),
							new Tuile(new int[] {4,1},new int[] {0,0},26),
							new Tuile(new int[] {4,1},new int[] {0,0},27),
							new Tuile(new int[] {4,1},new int[] {2,0},28),
							new Tuile(new int[] {4,1},new int[] {1,0},29),
							new Tuile(new int[] {2,1},new int[] {0,0},30),
							new Tuile(new int[] {2,1},new int[] {0,0},31),
							new Tuile(new int[] {2,1},new int[] {4,0},32),
							new Tuile(new int[] {2,1},new int[] {4,0},33),
							new Tuile(new int[] {2,1},new int[] {4,0},34),
							new Tuile(new int[] {2,1},new int[] {4,0},35),
							new Tuile(new int[] {0,0},new int[] {1,1},36),
							new Tuile(new int[] {2,0},new int[] {1,1},37),
							new Tuile(new int[] {0,0},new int[] {3,1},38),
							new Tuile(new int[] {1,0},new int[] {3,1},39),
							new Tuile(new int[] {5,1},new int[] {0,0},40),
							new Tuile(new int[] {0,0},new int[] {1,2},41),
							new Tuile(new int[] {2,0},new int[] {1,2},42),
							new Tuile(new int[] {0,0},new int[] {3,2},43),
							new Tuile(new int[] {1,0},new int[] {3,2},44),
							new Tuile(new int[] {5,2},new int[] {0,0},45),
							new Tuile(new int[] {3,0},new int[] {5,2},46),
							new Tuile(new int[] {3,0},new int[] {5,2},47),
							new Tuile(new int[] {0,0},new int[] {5,3},43)};
    
    
    private Joueur j1;
    private Joueur j2;
    private Tuile[] tirage;
    

    public Partie(String j1, String j2){
        this.j1 = new Joueur(j1);
        this.j2 = new Joueur((j2));
        melangerPile();
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
