
public class Tuile implements Comparable {

    final static String [] decod_terrain = {"champ","pre","lac","marais","foret","mine"};			// Indice pour decoder les types de terrains (terrain[0])
    final private int[] terrain1;										
    final private int[] terrain2;																	// Terrain de format : [0] -> type ; [1] : nombre de couronnes
    final private int id_tuile;

    public Tuile(int t1[],int[] t2, int id){							// Creation de la tuile a partir d'un id et de deux terrains
        this.terrain1 = t1;
        this.terrain2 = t2;
        this.id_tuile = id;
    }

    public int[] getTerrain1() {										// Renvoie terrain 1
        return this.terrain1;
    }
    
    public int[] getTerrain2() {										// Renvoie terrain 2
        return this.terrain2;
    }

    public int getId() {												// Renvoie id de la tuile
        return this.id_tuile;
    }

    public int compareTo(Object o) {									// Compare deux tuiles : si > 0, this.tuile est plus grand, sinon this.tuile est plus petit 
        if(o instanceof Tuile){
            Tuile t = (Tuile) o;
            return this.id_tuile - t.id_tuile;
        }
       throw (new IllegalArgumentException() );
    }
}
