import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.*;

import javax.swing.*;

public class FenetreJeu extends JFrame implements ActionListener {


	JPanel panel = new JPanel();

	JMenuItem quitter;
	JMenuItem rules;
	
	JButton defausse;
	
	Partie tempPartie;
	int tuileSelected;
	int[] terrainSelected;
	Coord[] coordSelected= new Coord[2];
	int[] reserved = new int[]{0,0,0,0};
	boolean defausseActive;
	
	private static final long serialVersionUID = 1L;

		
	public FenetreJeu(Partie partie) {
		
		// Creation de la fenetre
		this.tempPartie=partie;
		setTitle("Kingdomino - Partie en cours");
		setSize(1000,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setJMenuBar(buildMenu());
		setVisible(true);			
		
		if (tempPartie.getModePopup()) {
			JOptionPane.showMessageDialog(null, "<html>Vous incarnez un Seigneur en quete de terres pour etendre son Royaume. Champs de bles, lacs, montagnes ..."
					+ " Il vous faut tout explorer pour reperer les meilleures parcelles. <br/>" +
					"Mais d'autres Seigneurs convoitent les memes terres que vous. <br/>" +
					"Connectez astucieusement vos dominos pour construire le royaume de 5x5 cases le plus prestigieux.<br/>" +
					"L'ordre du tour de jeu est determine par la position des rois sur la ligne des dominos. Quand c'est a"
					+ " votre tour, cliquez sur la tuile que vous souhaitez prendre <br/>" +
					" Au moment de placer vos tuile, cliquez sur le terrain que vous souhaitez placer, puis sur l'emplacement que vous voulez qu'il prenne <br/>" +
					"cliquez ensuite sur l'emplacement ou placer le deuxieme terrain de la tuile <br/>" +
					"si vous ne voulez pas placer une tuile sur votre grille, cliquez sur le bouton defausser et sur la tuile a faire disparaitre <br/></html>","Rappel de regles", JOptionPane.INFORMATION_MESSAGE);
		}
		
		debutTour();
	}
	
	
	private JPanel buildContentPane() {
		
		
		panel.setLayout(new BorderLayout(50,50));
		
		JPanel panelHaut=new JPanel();
		panelHaut.setLayout(new GridLayout(1,2));
		
		JPanel panelNom = new JPanel();
		panelNom.setLayout(new GridLayout(1,2));
		
		JPanel panelGauche = new JPanel();
		JPanel panelDroite=new JPanel();
		
		JPanel panelBas=new JPanel();
		panelBas.setLayout(new BorderLayout(20,20));
		
		JPanel panelBasTuile = new JPanel();
		JPanel panelBasToken = new JPanel();
				
				
		JPanel grille1=constructGrille(tempPartie.getJ1().getGrille(),1);
		JPanel grille2=constructGrille(tempPartie.getJ2().getGrille(),2);
		
		JLabel labelNom1= new JLabel(tempPartie.getJ1().getNom());
		JLabel labelNom2= new JLabel(tempPartie.getJ2().getNom());
		
		labelNom1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelNom2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		panelNom.add(labelNom1);
		panelNom.add(labelNom2);
		
	
		panelGauche.add(grille1);
		panelDroite.add(grille2);
		
		
		if (tempPartie.getPhase()<5) {
			for (int i=0; i<4;i++ ) {
				panelBasTuile.add(constructTuile(tempPartie.getTirage()[i],i));		
			}	
			
			
			for (int i=0; i<4;i++ ) {
				
				if (tempPartie.getTempOrdre()[i]==0) {
					panelBasToken.add(constructToken(0,-1));
				} else {
					panelBasToken.add(constructToken(tempPartie.getTempOrdre()[i],1));
				}
			
			}
			
		} else {
			defausse = new JButton("Defausser");
			defausse.addActionListener(this);
			panelBasToken.add(defausse);
			
			for (int j=0;j<2;j++) {
				if (tempPartie.getTour()==1) {
					if (tempPartie.getJ1().getReservation()[j]!=null) {
						panelBasTuile.add(constructTuile(tempPartie.getJ1().getReservation()[j],j));	
					}
				} else {
					if (tempPartie.getJ2().getReservation()[j]!=null) {
						panelBasTuile.add(constructTuile(tempPartie.getJ2().getReservation()[j],j));	
					}
				}
			}
		}
		
		panelBas.add(panelBasToken, BorderLayout.CENTER);
		panelBas.add(panelBasTuile, BorderLayout.SOUTH);
		
		panelHaut.add(panelGauche);
		panelHaut.add(panelDroite);
		panel.add(panelNom,BorderLayout.NORTH);
		panel.add(panelHaut,BorderLayout.CENTER);
		panel.add(panelBas,BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel constructGrille (Grille grilletest,int idJ) {
		
		JPanel grille = new JPanel();
		grille.setLayout(new GridLayout(7,7));
		grille.setBorder(BorderFactory.createEmptyBorder(2,2,2,2)); 
		
		ImageIcon imgbg;
		
		
		for (int iGrille =0; iGrille<(7); iGrille++){ 
			for (int jGrille=0; jGrille<7;jGrille++) {
				
				Coord coord=new Coord(iGrille,jGrille);
				String url="";
				
				if (grilletest.getCase(coord)==null) {
					 url="images/null.jpg";
				} 
				else {
					url="images/"+grilletest.getCase(coord)[0]+"-"+grilletest.getCase(coord)[1]+".jpg";
				}
				
				imgbg= new ImageIcon(url); 
				JLabel label = new JLabel(imgbg); 
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label.setName(""+iGrille+""+jGrille);
				
				
				label.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						System.out.println(label.getName());
						if (tempPartie.getPhase()>4) {
							if (terrainSelected!=null) {
								int var1=Integer.parseInt(label.getName().charAt(0)+"");
								int var2=Integer.parseInt(label.getName().charAt(1)+"");
								if (coordSelected[0]==null) {
									coordSelected[0]=new Coord(var1,var2);
								} else {
									coordSelected[1]=new Coord(var1,var2);
									positionne();
								}
		
							} else {
								System.out.println("Erreur : pas de terrain selectionne");
							}
						}
					}
				});
				
				grille.add(label); 
				
				
			}
		} 
	
		return grille;
	}
	
	private JPanel constructTuile(Tuile t,int indice) {
		
		JPanel tuile = new JPanel();
		tuile.setLayout(new GridLayout(2,1));
		tuile.setBorder(BorderFactory.createEmptyBorder(2,2,2,2)); 
		
		String url="images/"+t.getTerrain1()[0]+"-"+t.getTerrain1()[1]+".jpg";
		ImageIcon imgbg= new ImageIcon(url); 
		JLabel label = new JLabel(imgbg); 
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tuile.add(label); 		
		
		String url2="images/"+t.getTerrain2()[0]+"-"+t.getTerrain2()[1]+".jpg";
		ImageIcon imgbg2= new ImageIcon(url2); 
		JLabel label2 = new JLabel(imgbg2); 
		label2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tuile.add(label2);
		
		tuile.setName("tuile"+indice);
		
		tuile.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tuileSelected=indice;			
				verif();
				
				
			}
		});
		
		if (tempPartie.getPhase()>4) {
			
			label.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					terrainSelected=new int[] {indice,1};
					if (defausseActive) {
						defausser();
					}
				}
			});
			
			label2.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					terrainSelected=new int[] {indice,2};
					if (defausseActive) {
						defausser();
					}
				}
			});
		}
		
		return tuile;
	}
	
	private JMenuBar buildMenu() {
		JMenuBar menu = new JMenuBar();

		quitter = new JMenuItem ("Quitter");
		rules = new JMenuItem ("Regles");
		
		JMenu menuFile = new JMenu("Fichier");
		JMenu menuAide = new JMenu("Aide");

		menuFile.add(quitter);
		
		menuAide.add(rules);

		quitter.addActionListener(this);
		rules.addActionListener(this);
		
		menu.add(menuFile);
		menu.add(menuAide);
		
		return menu;
	}
	
	private JPanel constructToken(int idJ,int val) {
		
		JPanel token = new JPanel();
		
		String couleur;
		String url;
		
		if (val!=-1) {
			if (idJ==1) {
				couleur="J1";
			} 
			else {
				couleur="J2";
			}
			
			url="images/"+couleur+".jpg";
		}
		else {
			url="images/null.jpg";
		}
		
		ImageIcon imgbg= new ImageIcon(url); 
		JLabel label = new JLabel(imgbg); 
		token.add(label); 
		
		
		return token;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(quitter)) {
			this.dispose();
		}
		if (e.getSource().equals(rules)) {
			JOptionPane.showMessageDialog(null, "<html>Vous incarnez un Seigneur en quete de terres pour etendre son Royaume. Champs de bles, lacs, montagnes ..."
					+ " Il vous faut tout explorer pour reperer les meilleures parcelles. <br/>" +
					"Mais d'autres Seigneurs convoitent les memes terres que vous. <br/>" +
					"Connectez astucieusement vos dominos pour construire le royaume de 5x5 cases le plus prestigieux.<br/>" +
					"L'ordre du tour de jeu est determine par la position des rois sur la ligne des dominos. Quand c'est a"
					+ " votre tour, cliquez sur la tuile que vous souhaitez prendre <br/>" +
					" Au moment de placer vos tuile, cliquez sur le terrain que vous souhaitez placer, puis sur l'emplacement que vous voulez qu'il prenne <br/>" +
					"cliquez ensuite sur l'emplacement ou placer le deuxieme terrain de la tuile <br/>" +
					"si vous ne voulez pas placer une tuile sur votre grille, cliquez sur le bouton defausser et sur la tuile a faire disparaitre <br/></html>","Rappel de regles", JOptionPane.INFORMATION_MESSAGE);
		}
		if (e.getSource().equals(defausse)) {
			defausseActive=true;
			if (tempPartie.getModePopup()) {
				JOptionPane.showMessageDialog(null, "<html>Choisissez la tuile a defausser.</html>","Defausse", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	
	public void debutTour() {

		defausseActive=false;
		
	
		if (tempPartie.getTour()==1) {
			if (tempPartie.getJ1() instanceof Ia ) {
				tourIa(1);
			} else {
				tourHumain(1);
			}
		} else {
			if (tempPartie.getJ2() instanceof Ia) {
				tourIa(2);
			} else {
				tourHumain(2);
			}
		}
	}
	
	public void tourHumain(int idJ) {
		String text1 ;
		String text2 ;
		
		if (idJ == 1) {
			text1="<html>"+tempPartie.getJ1().getNom()+", a toi de jouer.<br><br>";
		}
		else {
			text1="<html>"+tempPartie.getJ2().getNom()+", a toi de jouer.<br><br>";
		}
		
		switch (tempPartie.getPhase()) {
			case 1:
			case 2:
			case 3:
			case 4:
				text2="Selectionner une tuile.</html>";
				break;
			case 5:
			case 6: 
			case 7: 
			case 8: 
				text2="Selectionner le terrain que vous voulez positionner, puis "
						+ "cliquer sur la case de destination. <br>Puis selectionner"
						+ "la case du second terrain de la tuile.";
				break;
			default :
				text2="None";
				break;
		}
		if (tempPartie.getModePopup()) {
			JOptionPane.showMessageDialog(null, text1+text2,"Tour de jeu", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void tourIa(int idJ) {

		switch (tempPartie.getPhase()) {
		case 1:
		case 2:
		case 3:
		case 4:
			int tuileChoisie ;
			
			if (idJ==1) {
 				tuileChoisie = tempPartie.getJ1().reserver(tempPartie.getTirage(),tempPartie.getTempOrdre(),tempPartie.getJ2().getGrille());
 				
 				
			} else {
				tuileChoisie = tempPartie.getJ2().reserver(tempPartie.getTirage(),tempPartie.getTempOrdre(), tempPartie.getJ1().getGrille());
			}
			
			this.tempPartie.setTempOrdreIndice(tuileChoisie, this.tempPartie.getTour());
			
			break;
		case 5:
		case 6: 
		case 7: 
		case 8: 
			if (idJ==1) {
				tempPartie.getJ1().placerTuile();
			} else {
				tempPartie.getJ2().placerTuile();
			}
			break;
		default :
			break;
		}
		
		panel.removeAll();
		setContentPane(buildContentPane());
		if (tempPartie.getModePopup()) {
			JOptionPane.showMessageDialog(null, "Fin du tour de l'IA","Fin ia",JOptionPane.INFORMATION_MESSAGE);
		}
		finPhase();
		
	}
	
	public void finPhase(){
		switch (tempPartie.getPhase()) {
			case 1:
			case 2:
			case 3:
				
				if (tempPartie.getOrdreActuel()[tempPartie.getPhase()]==1) {
					tempPartie.setTour(1);
				} else {
					tempPartie.setTour(2);
				}
				
				break;
			case 4:
			case 5:
				tempPartie.setTour(1);
				break;
			case 6:
			case 7:
				tempPartie.setTour(2);
				break;
			
		}

		
		if (tempPartie.getPhase()==8) {
			finRound();
		} else {
			
			tempPartie.setPhase(tempPartie.getPhase()+1);
			panel.removeAll();
			setContentPane(buildContentPane());
			debutTour();
		}
		
	}
	
	
	public void finRound() {
		
		
		if (tempPartie.getRound()==12) {
			
			panel.removeAll();
			setContentPane(buildContentPane());
			
			
			JOptionPane.showMessageDialog(null, "<html>Partie finie"
					+ "<br><br>Score "+tempPartie.getJ1().getNom()+" : "+tempPartie.getJ1().getScore()
					+ "<br><br>Score "+tempPartie.getJ2().getNom()+" : "+tempPartie.getJ2().getScore()
					+ "</html>","Fin de partie",JOptionPane.INFORMATION_MESSAGE);
			
			// afficher le score, clap clap bravo
			
		} else {
			tempPartie.setRound(tempPartie.getRound()+1);
			tempPartie.setPhase(1);
			tempPartie.faireTirage();
			tempPartie.majOrdre();
			
			if (tempPartie.getOrdreActuel()[0]==1) {
				tempPartie.setTour(1);
			} else {
				tempPartie.setTour(2);
			}
			
			panel.removeAll();
			setContentPane(buildContentPane());
			
			debutTour();
		}
	}
	
	public void verif() {
		
		if (this.tempPartie.verifChoixTuile(this.tuileSelected)==true) {
			if (tempPartie.getModePopup()) {
				JOptionPane.showMessageDialog(null, "<html>Erreur.<br><br>Cette tuile est deja reservee.</html>","Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else {			
			this.tempPartie.setTempOrdreIndice(this.tuileSelected, this.tempPartie.getTour());
			finPhase();
		}
	}
	
	public void positionne() {
		
		boolean correct;
		int terrain1=0;
		int terrain2=0;
		
	    if (terrainSelected[1]==1) {
	    	terrain1=1;
	    	terrain2=2;
	    } else {
	    	terrain1=2;
	    	terrain2=1;
	    }
	    
	    System.out.println("------------------------positionne()--------------------");
	    System.out.println("Tuile choisie : "+terrainSelected[0]);
	    System.out.println("1er terrain choisi : " + terrain1 + " aux coordonnees "+coordSelected[0].getLigne()+coordSelected[0].getColonne());
	    System.out.println("2nd terrain choisi : " + terrain2 + " aux coordonnees "+coordSelected[1].getLigne()+coordSelected[1].getColonne());
	    
	    
		if(tempPartie.getTour()==1) {
			correct=tempPartie.getJ1().placerTuile(terrainSelected[0],coordSelected[0],terrain1, coordSelected[1],terrain2);
			System.out.println("score humain : "+tempPartie.getJ1().getScore());
		} else {
			correct=tempPartie.getJ2().placerTuile(terrainSelected[0],coordSelected[0],terrain1, coordSelected[1],terrain2);
			System.out.println("score humain : "+tempPartie.getJ2().getScore());
		}
		
		terrainSelected = null;
		coordSelected = new Coord[2];
		
		if (correct) {
			finPhase();
		} else {
			if (tempPartie.getModePopup()) {
				JOptionPane.showMessageDialog(null, "<html>Erreur lors du positionnement.</html>","Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
	}
	
	public void defausser() {
		defausseActive=false;
		
		if (tempPartie.getTour()==1) {
			tempPartie.getJ1().defausser(terrainSelected[0]);
		} else {
			tempPartie.getJ2().defausser(terrainSelected[0]);
		}
		
		finPhase();
		
	}

}