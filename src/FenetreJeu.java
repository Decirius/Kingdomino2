import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

import javax.swing.*;

public class FenetreJeu extends JFrame implements ActionListener {


	JPanel panel = new JPanel();
	
	JMenuItem sauvegarder;
	JMenuItem quitter;
	JMenuItem rules;
	
	Partie tempPartie;
	int tuileSelected;
	int[] terrainSelected;
	Coord[] coordSelected= new Coord[2];
	int[] reserved = new int[]{0,0,0,0};
	
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
		
		
		JOptionPane.showMessageDialog(null, "<html>Blabla regle blabla</html>","Rappel de regles", JOptionPane.INFORMATION_MESSAGE);
		
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
								System.out.println("Erreur : pas de terrain s�lectionn�");
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
					System.out.println("Terrain 1 Tuile "+indice);
				}
			});
			
			label2.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					terrainSelected=new int[] {indice,2};
					System.out.println("Terrain 2 Tuile "+indice);
				}
			});
		}
		
		return tuile;
	}
	
	private JMenuBar buildMenu() {
		JMenuBar menu = new JMenuBar();
		
		sauvegarder = new JMenuItem ("Sauvegarder");
		quitter = new JMenuItem ("Quitter");
		rules = new JMenuItem ("R�gles");
		
		JMenu menuFile = new JMenu("Fichier");
		JMenu menuAide = new JMenu("Aide");

		menuFile.add(sauvegarder);
		menuFile.add(quitter);
		
		menuAide.add(rules);
		
		sauvegarder.addActionListener(this);
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
			JOptionPane.showMessageDialog(null, "<html>Blabla regle blabla</html>","Rappel de regles", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void debutTour() {
		String text1 ;
		String text2;
		
		if (tempPartie.getTour() == 1) {
			text1="<html>Joueur 1, a toi de jouer.<br><br>";
		}
		else {
			text1="<html>Joueur 2, a toi de jouer.<br><br>";
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
						+ "cliquer sur la case de destination. Puis selectionner"
						+ "la case du second terrain de la tuile.";
				break;
			default :
				text2="None";
				break;
		}
		
		
		JOptionPane.showMessageDialog(null, text1+text2,"Tour de jeu", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void finPhase() {
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

		
		if (tempPartie.getRound()<12) {
			if (tempPartie.getPhase()==8) {
				finRound();
				
			} else {
				tempPartie.setPhase(tempPartie.getPhase()+1);
			}
			panel.removeAll();
			setContentPane(buildContentPane());
			debutTour(); 
		} else {
			panel.removeAll();
			setContentPane(buildContentPane());
			finRound();
		}
		
		
	}
	
	
	public void finRound() {
		
		
		if (tempPartie.getRound()<12) {
			
			tempPartie.setRound(tempPartie.getRound()+1);
			tempPartie.setPhase(1);
			tempPartie.faireTirage();
			tempPartie.majOrdre();
			debutTour();
		}
		else {
		JOptionPane.showMessageDialog(null, "Partie finie","Fin de partie",JOptionPane.INFORMATION_MESSAGE);
		// afficher le score, clap clap bravo
		}
	}
	
	public void verif() {
		
		if (this.tempPartie.verifChoixTuile(this.tuileSelected)==true) {
			JOptionPane.showMessageDialog(null, "<html>Erreur.<br><br>Cette tuile est deja reservee.</html>","Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		else {			
			this.tempPartie.setTempOrdreIndice(this.tuileSelected, this.tempPartie.getTour());
			finPhase();
		}
	}
	
	public void positionne() {
		
		boolean correct;
		
		 System.out.println("1 : Ligne "+coordSelected[0].getLigne()+", colonne :"+coordSelected[0].getColonne());
	     System.out.println("2 : Ligne "+coordSelected[1].getLigne()+", colonne :"+coordSelected[1].getColonne());
		
		if(tempPartie.getTour()==1) {
			correct=tempPartie.getJ1().placerTuile(terrainSelected[0],coordSelected[0], coordSelected[1]);
		} else {
			correct=tempPartie.getJ2().placerTuile(terrainSelected[0],coordSelected[0], coordSelected[1]);
		}
		
		terrainSelected = new int[2];
		coordSelected = new Coord[2];
		
		if (correct) {
			System.out.println("WAOUH");
			finPhase();
		} else {
			System.out.println("aie...");
		}
		
		
	}

}