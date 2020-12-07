import java.util.ArrayList;
import java.util.List;

public class IaPoseTuile extends IaAleatoire {

    public IaPoseTuile(String nom) {
        super(nom);
    }

    public boolean placerTuile() {
        // TODO On garde la selection au hasard pour l'instant
        int tuileSelected=-1;
        int terrainSelected=-1;
        Coord[] placeSelected=new Coord[2];
        int max=0;
        boolean placeTrouvee=false;

        for (int numTuile=0;numTuile<2;numTuile++) {			// Pour les deux tuiles

            if (this.getReservation()[numTuile]!=null) {			// Si elle n'a pas deja ete posee



                for (int numTerrain=0;numTerrain<2;numTerrain++) {		// Pour chaque terrain

                    int typeTerrain;
                    int[] terrainComplet;
                    int[] terrainComplet2;
                    int numTerrain2;

                    if (numTerrain==0) {								// On recupere le type du terrain lie a l'indice
                        terrainComplet = this.getReservation()[numTuile].getTerrain1();
                        terrainComplet2= this.getReservation()[numTuile].getTerrain2();
                        numTerrain2=1;

                    } else {
                        terrainComplet = this.getReservation()[numTuile].getTerrain2();
                        terrainComplet2= this.getReservation()[numTuile].getTerrain1();
                        numTerrain2=0;
                    }

                    typeTerrain=terrainComplet[0];

                    List<Coord> terrains = this.chercherTerrain(typeTerrain);		// On recupere les terrains qui permettent de jouer
                    List<Coord[]> places = new ArrayList<Coord[]>(); 				// On cree la liste de places pour cette tuile

                    if (!terrains.isEmpty()) { 										// Si il existe des terrains qui permettent de jouer, on cherche les places
                        places = this.chercherPlace(terrains);

                    }

                    if (!places.isEmpty()) {										// Si il existe des places
                        placeTrouvee=true;

                        for (int numPlace=0;numPlace<places.size();numPlace++) {	// On calcule le score de chaque place

                            int scoreTuile2 = 0;

                            int tempScore1 =scorePlacement(this.getReservation()[numTuile],terrainComplet,places.get(numPlace)[0]);

                            int tempScore2= scorePlacement(this.getReservation()[numTuile],terrainComplet2,places.get(numPlace)[1]);

                            scoreTuile2=scoreTuile2+tempScore1+tempScore2;

                            if (scoreTuile2>=max) {

                                tuileSelected=numTuile;
                                terrainSelected=numTerrain;
                                max=scoreTuile2;
                                placeSelected=places.get(numPlace);
                                System.out.println("Meilleure place trouvee : "+scoreTuile2);

                            }
                        }
                    }
                }
            }
        }

        if (placeTrouvee==false) {						// Si aucune place trouvee pour les terrains des deux tuiles : on defausse la premiere dispo
            if (this.getReservation()[0]!=null) {
                this.getReservation()[0] = null;
                this.defausser(0);
                return true;
            } else {
                this.getReservation()[1] = null;
                this.defausser(1);
                return true;
            }
        } else {										// Sinon on a trouve la meilleure place :

            int[]terrainComplet;
            int[]terrainComplet2;

            if (terrainSelected==0) {
                terrainComplet=this.getReservation()[tuileSelected].getTerrain1();
                terrainComplet2=this.getReservation()[tuileSelected].getTerrain2();
            } else {
                terrainComplet2=this.getReservation()[tuileSelected].getTerrain1();
                terrainComplet=this.getReservation()[tuileSelected].getTerrain2();
            }

            System.out.println("_____________________________Meilleur terrain trouve_______________________________________");
            System.out.println("1er terrain : "+terrainComplet[0] +"aux coord : "+placeSelected[0].getLigne()+placeSelected[0].getColonne());
            System.out.println("2nd terrain : " +terrainComplet2[0]+" aux coord : "+placeSelected[1].getLigne()+placeSelected[1].getColonne());


            if (terrainSelected==0) {
                if (this.getGrille().recevoirTuile(this.getReservation()[tuileSelected], placeSelected[0], 1, placeSelected[1], 2)) {
                    this.majGrilleZone(this.getReservation()[tuileSelected].getTerrain1(), placeSelected[0]);
                    this.majGrilleZone(this.getReservation()[tuileSelected].getTerrain2(), placeSelected[1]);

                    this.calculScore();
                    this.getReservation()[tuileSelected] = null;
                    return true;
                }
            } else {
                if (this.getGrille().recevoirTuile(this.getReservation()[tuileSelected], placeSelected[0], 2, placeSelected[1], 1)) {
                    this.majGrilleZone(this.getReservation()[tuileSelected].getTerrain2(), placeSelected[0]);
                    this.majGrilleZone(this.getReservation()[tuileSelected].getTerrain1(), placeSelected[1]);

                    this.calculScore();
                    this.getReservation()[tuileSelected] = null;
                    return true;
                }
            }
        }

        return false;   //si malgre tout un probleme renvoie false

    }

    public int scorePlacement(Tuile t, int[] terrain, Coord coord) {

        Coord centre = new Coord(3,3);
        int scoreAjout=0;


        Coord[] autour = this.getGrilleZone().getCasesAutour(coord);
        boolean zoneAutour=false;


        for (int i=0;i<autour.length;i++) {
            if (this.grilleZone.getCase(autour[i])!=null && autour[i]!=centre) {
                int multiplicateur=0;
                if (this.grilleZone.getCase(autour[i])[0]==terrain[0]) {

                    if (!zoneAutour) {
                        zoneAutour=true;
                        int indice=this.grilleZone.getCase(autour[i])[1];
                        switch(terrain[0]) {
                            case 0:
                                multiplicateur=(this.getChamps().get(indice)[1])*(this.getChamps().get(indice)[0]);
                                scoreAjout=scoreAjout+this.getChamps().get(indice)[0]+multiplicateur;
                                break;
                            case 1:
                                multiplicateur=(this.getPres().get(indice)[1])*(this.getPres().get(indice)[0]);
                                scoreAjout=scoreAjout+this.getPres().get(indice)[0]+multiplicateur;
                                break;
                            case 2 :
                                multiplicateur=(this.getLacs().get(indice)[1])*(this.getLacs().get(indice)[0]);
                                scoreAjout=scoreAjout+this.getLacs().get(indice)[0]+multiplicateur;
                                break;
                            case 3 :
                                multiplicateur=(this.getMarais().get(indice)[1])*(this.getMarais().get(indice)[0]);
                                scoreAjout=scoreAjout+this.getMarais().get(indice)[0]+multiplicateur;
                                break;
                            case 4:
                                multiplicateur=(this.getForets().get(indice)[1])*(this.getForets().get(indice)[0]);
                                scoreAjout=scoreAjout+this.getForets().get(indice)[0]+multiplicateur;
                                break;
                            case 5:
                                multiplicateur=(this.getMines().get(indice)[1])*(this.getMines().get(indice)[0]);
                                scoreAjout=scoreAjout+this.getMines().get(indice)[0]+multiplicateur;
                                break;
                        }
                    }
                }
            }
        }

        return (scoreAjout);
    }


}
