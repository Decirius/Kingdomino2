import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IaCompetitive extends IaStandard {
    public IaCompetitive(String nom) {
        super(nom);
    }

    public int reserver(Tuile[] tirage,int[] tempOrdre, Grille grilleAdv) {

        // TODO : a modifier en fonction des tuiles deja placees
        int reserved = -1;
        int max;
        int[] scoreTuile = new int[4];   //score de chaque tuile pour le joueur
        int[] scoreTuileAdv = new int[4]; //score de chaque tuile pour l'adversaire
        int[] scoreFinal = new int[4];  //score en prenant en compte adversaire

        for (int i=0;i<4;i++) {					// Pour chaque tuile, on calcule son score
            scoreTuile[i]=scoreSelection(tirage[i]);    //pour le joueur dans sa grille
            scoreTuileAdv[i]=scoreSelectionAdv(tirage[i], grilleAdv);   //pour l'adversaire dans sa grille
        }


            //liste des scores d'une tuile moins le score de la meilleure tuile restante pour l'adversaire
        for(int i = 0;i<4; i++){
                //initialise à la première tuile restante pour l'adversaire
            int maxAdv;
            if(i == 0){
                maxAdv = scoreTuileAdv[1];
            } else {
                maxAdv = scoreTuileAdv[0];
            }
                //parcours les tuiles restantes
            for(int j = 0; j<4; j++){
                //si la tuile n'est pas celle prise
                if(i != j){
                    if(scoreTuileAdv[j] > maxAdv){
                        maxAdv = scoreTuileAdv[j];
                    }
                }
            }
            scoreFinal[i] = scoreTuile[i] - maxAdv;
        }

            //iniitalise max avec le score de la premiere tuile de scoreFinal jouable
        int parcours = 0;
        while(tempOrdre[parcours] != 0){
            parcours = parcours + 1;
        }
        max = scoreFinal[parcours];
            //choisi cette tuile comme celle a jouer
        reserved = parcours;

            // On prend le score jouable le + haut dans scoreFinal
            //prend en charge si c'est le dernier a jouer car dans ce cas prendra le dernier choix disponible
        for (int j=0;j<4;j++) {
            if (tempOrdre[j]==0) {
                if (scoreFinal[j] > max) {
                    max=scoreFinal[j];
                    reserved = j;       //choisi cette tuile a jouer, comme ca si la premiere etait la bonne reste la bonne, sinon ameliore
                }

            }
        }

        this.setReservation(tirage[reserved]);

        return reserved;						// On reserve
    }



    public int scoreSelectionAdv(Tuile t, Grille g) {
        // Calcule d'un score de selection pour la tuile
        int scoreT1=0;							// On initialise un score pour chaque terrain
        int scoreT2=0;

        // Il prioritise les terrains de type qu'il a deja
        for (int i=0;i<7;i++) {
            for (int j=0;j<7;j++) {
                Coord coord = new Coord(i,j);
                if (g.getCase(coord)!=null && !(coord.getLigne()==3 && coord.getColonne()==3)) {

                    if (g.getCase(coord)[0]==t.getTerrain1()[0]) {
                        scoreT1=scoreT1+1*(1+g.getCase(coord)[1]);
                    }
                    if (g.getCase(coord)[0]==t.getTerrain2()[0]) {
                        scoreT2=scoreT2+1*(1+g.getCase(coord)[1]);
                    }
                }
            }
        }
        scoreT1=scoreT1+scoreT1*t.getTerrain1()[1];
        scoreT2=scoreT2+scoreT2*t.getTerrain2()[1];

        return (scoreT1+scoreT2);
    }
}
