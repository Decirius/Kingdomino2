
public class Tuile implements Comparable {

    final static String [] decod_terrain = {"champ","pre","lac","marais","foret","mine"};
    //terrain[0] = type de terrain et terrain[1] = nb de courrones
    final private int[] terrain1;
    final private int[] terrain2;
    final private int id_tuile;


    public Tuile(int t1[],int[] t2, int id){
        this.terrain1 = t1;
        this.terrain2 = t2;
        this.id_tuile = id;
        
    }

    public int[] getTerrain1() {
        return this.terrain1;
    }
    public int[] getTerrain2() {
        return this.terrain2;
    }

    public int getId() {
        return this.id_tuile;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Tuile){
            Tuile t = (Tuile) o;
            return this.id_tuile - t.id_tuile;
        }
       throw (new IllegalArgumentException() );
    }
}
