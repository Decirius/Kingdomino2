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
							new Tuile(new int[] {0,0},new int[] {5,3},48)};
    
    
    private Joueur j1;
    private Joueur j2;
    private Tuile[] tirage;
    private int tour;					// Varie de tour (J)1 a (J)2 
    private int phase;					// ------------------
    private int round; 					// Variant de 1 a 12
    private int[] tempOrdre;			// ordre pour le Round prochain
    private int[] ordreActuel;			// ordre pour le Round courant



	//type joueur 0 pour humain, 1 pour iaAleatoire
    public Partie(String j1, String j2, int typeJoueur1, int typeJoueur2){
    	switch (typeJoueur1) {
			case (0):
				this.j1 = new Humain(j1);
				break;
			case (1):
				this.j1 = new IaAleatoire(j1);
				break;
			case (2) :
				this.j1 = new IaStandard(j1);
				break;
		}
		switch (typeJoueur2) {
			case (0):
				this.j2 = new Humain(j2);
				break;
			case (1):
				this.j2 = new IaAleatoire(j2);
				break;
			case (2) :
				this.j2 = new IaStandard(j2);
				break;
		}
        melangerPile();
        this.tirage = new Tuile[4];
        faireTirage();
        this.tour=1;
        this.phase=1;
        this.round=1;
        initTempOrdre();
        this.j1.setOrdre(new int[] {1,3});
        this.j2.setOrdre(new int[] {2,4});
        this.ordreActuel=new int[] {1,2,1,2};
    }

    public void melangerPile(){
        List<Tuile> intList = Arrays.asList(this.pile);		//transforme le tableau en liste
        Collections.shuffle(intList);						//melange la liste
        intList.toArray(this.pile);							//retransforme la liste en un tableau
    }

    public void faireTirage() {
        this.tirage = Arrays.copyOfRange(this.pile, 0, 4);
        Arrays.sort(this.tirage);
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
    
    public int getTour() {
    	return this.tour;
    }
    
    public int getPhase() {
    	return this.phase;
    }
    
    public void setPhase(int pha) {
    	this.phase=pha;
    }
    
    public void setTour(int tou) {
    	this.tour=tou;
    }

    public int getRound() {
    	return this.round;
    }
    
    public void setRound(int rou) {
    	this.round=rou;
    }
    
    public int[] getTempOrdre() {
    	return this.tempOrdre;
    }
    
    public void setTempOrdre(int[] to) {
    	this.tempOrdre=to;
    }
    
    public void setTempOrdreIndice(int indice, int val) {
    	this.tempOrdre[indice]=val;
    }
    
    public void initTempOrdre() {
    	this.tempOrdre=new int[] {0,0,0,0};
    }
    
    public int[] getOrdreActuel() {
    	return this.ordreActuel;
    }
    
    public void setOrdreActuel(int[] oa) {
    	this.ordreActuel=oa;
    }
    
    public void setOrdreActuelIndice(int indice, int val) {
    	this.ordreActuel[indice]=val;
    }
    
    public boolean verifChoixTuile(int tuileSelected) {
    	
    	boolean dejaRes=false;
    	
    	for (int i=0; i<2;i++) {  // On verifie si la tuile est d�j� r�serv�e chez J1 ou J2
    		if (this.j1.getReservation()[i]!=null) {
	    		if (this.j1.getReservation()[i].getId()==this.tirage[tuileSelected].getId()) {
	       			dejaRes=true;
	    		}
    		}
	    	if (this.j2.getReservation()[i]!=null) {
	    		if (this.j2.getReservation()[i].getId()==this.tirage[tuileSelected].getId()) {
	    			dejaRes=true;
	    		}
	    	}
    	}
    	
    	if (dejaRes==false) {    // la tuile est disponible
	    	if (this.tour==1) {
	    		this.j1.setReservation(this.tirage[tuileSelected]);
	    	} else {
	    		this.j2.setReservation(this.tirage[tuileSelected]);
	    	}
    	}
    	
    	return dejaRes;			  
    }
    
    public void majOrdre() {
    	
    	int indice1=0;
    	int indice2=0;
    	
    	for (int i=0;i<4;i++) {
    	
    		if (this.tempOrdre[i]==1) {
    			System.out.println("J1");
    			this.j1.setOrdreIndice(indice1,i);
    			indice1+=1;
    		} else {
    			System.out.println("J2");
    			this.j2.setOrdreIndice(indice2,i);
    			indice2+=1;
    		}
    			
    	}
    	
    	this.ordreActuel=this.tempOrdre;
    	initTempOrdre();
    }

    
}
