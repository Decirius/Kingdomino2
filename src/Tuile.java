
public class Tuile {

    final static String [] decod_terrain = {"champ","pré","lac","marais","forêt","mine"};

    final private int[] terrain1;
    final private int[] terrain2;
    final private int id_tuile;
    private int orientation;

    public Tuile(int t1[],int[] t2, int id){
        this.terrain1 = t1;
        this.terrain2 = t2;
        this.id_tuile = id;
        this.orientation = 0;
    }

    public int getOrientation() {
        return this.orientation;
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

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
}
