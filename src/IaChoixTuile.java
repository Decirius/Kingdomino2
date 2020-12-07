public class IaChoixTuile extends IaAleatoire {


    public IaChoixTuile(String nom) {
        super(nom);
    }

    public int reserver(Tuile[] tirage,int[] tempOrdre, Grille grilleAdv) {

        // TODO : a modifier en fonction des tuiles d�j� plac�es
        int reserved=-1;
        int max=0;
        int[] scoreTuile= new int[4];

        for (int i=0;i<4;i++) {					// Pour chaque tuile, on calcule son score
            scoreTuile[i]=scoreSelection(tirage[i]);
            System.out.println("Score de la tuile "+i+" = "+scoreTuile[i]);
        }

        for (int j=0;j<4;j++) {					// On prend le score le + haut
            if (tempOrdre[j]==0) {
                System.out.println("Reservation");
                if (scoreTuile[j]>max) {
                    System.out.println("Reservee : "+j);
                    reserved=j;
                    max=scoreTuile[j];
                }
            }
        }
        int indice=0;

        while (reserved==-1) {					// si on a toujours pas r�serv�
            if (tempOrdre[indice]==0) {			// on prend la 1ere disponible
                reserved=indice;
            }
            indice+=1;
        }

        this.setReservation(tirage[reserved]);

        return reserved;						// On reserve
    }


    public int scoreSelection(Tuile t) {
        // Calcule d'un score de selection pour la tuile
        int scoreT1=0;							// On initialise un score pour chaque terrain
        int scoreT2=0;

        // Il prioritise les terrains de type qu'il a deja
        for (int i=0;i<7;i++) {
            for (int j=0;j<7;j++) {
                Coord coord = new Coord(i,j);
                if (this.getGrille().getCase(coord)!=null && !(coord.getLigne()==3 && coord.getColonne()==3)) {

                    if (this.getGrille().getCase(coord)[0]==t.getTerrain1()[0]) {
                        scoreT1=scoreT1+1*(1+this.getGrille().getCase(coord)[1]);
                    }
                    if (this.getGrille().getCase(coord)[0]==t.getTerrain2()[0]) {
                        scoreT2=scoreT2+1*(1+this.getGrille().getCase(coord)[1]);
                    }

                }

            }
        }
        scoreT1=scoreT1+scoreT1*t.getTerrain1()[1];
        scoreT2=scoreT2+scoreT2*t.getTerrain2()[1];

        return (scoreT1+scoreT2);
    }
}
