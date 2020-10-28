
public class Humain extends Joueur {

    public Humain(String nom){
        super(nom);
    }

    public void choisirTuile(){

    }
    public void orienterTuile(int num, int val){
        int orientation = this.getReservation()[num].getOrientation();
        orientation = orientation + val;
        if(orientation < 0)
            orientation = 3;
        if(orientation > 3)
            orientation = 0;
        this.getReservation()[num].setOrientation(orientation);
    }
    public  void placerTuile(){

    }

    public void defausser(){

    }
}
