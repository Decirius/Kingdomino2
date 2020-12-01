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
	int tour ;
	int phase;
	int tuileSelected;
	Coord coordSelected;
	int[] tempOrdre = new int[] {0,0,0,0};
	int[] reserved = new int[]{0,0,0,0};
	
	private static final long serialVersionUID = 1L;

		
	public FenetreJeu(Partie partie) {
		
		// Création de la fenetre
		
		initPartie(partie);
		
		setTitle("Kingdomino - Partie en cours");
		setSize(1000,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setJMenuBar(buildMenu());
		setVisible(true);			
		
		
		JOptionPane.showMessageDialog(null, "<html>Blabla règle blabla</html>","Rappel de règles", JOptionPane.INFORMATION_MESSAGE);
		
		tourJeu();
	}
	
	private void initPartie(Partie partie) {
		tempPartie=partie;
		tempPartie.faireTirage();
		tempPartie.getJ1().setOrdre(new int[]{-1,-1});
		tempPartie.getJ2().setOrdre(new int[]{-1,-1});
		tour=1;
		phase=1;
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
		
		
		
		for (int i=0; i<4;i++ ) {
			panelBasTuile.add(constructTuile(tempPartie.getTirage()[i],i));		
		}	
		
		
		for (int i=0; i<4;i++ ) {
			
			if (reserved[i]==0) {
				panelBasToken.add(constructToken(0,-1));
			}
			else {
				for (int j=0;j<2;j++) {
					if (i==tempPartie.getJ1().getOrdre()[j]) {
						panelBasToken.add(constructToken(0,1));
					}
					if (i==tempPartie.getJ2().getOrdre()[j]) {
						panelBasToken.add(constructToken(1,1));
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
	
	private JPanel constructGrille (Grille grilletest,int idG) {
		
		JPanel grille = new JPanel();
		grille.setLayout(new GridLayout(7,7));
		grille.setBorder(BorderFactory.createEmptyBorder(2,2,2,2)); 
		
		ImageIcon imgbg;
		
		for (int i =0; i<(7); i++){ 
			for (int j=0; j<7;j++) {
				
				Coord coord=new Coord(i,j);
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
				label.setName(""+i+""+j);
				
				label.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						System.out.println(label.getName());
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
				verifChoixTuile();
			}
		});
		
		return tuile;
	}
	
	private JMenuBar buildMenu() {
		JMenuBar menu = new JMenuBar();
		
		sauvegarder = new JMenuItem ("Sauvegarder");
		quitter = new JMenuItem ("Quitter");
		rules = new JMenuItem ("Règles");
		
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
			if (idJ==0) {
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
			JOptionPane.showMessageDialog(null, "<html>Blabla règle blabla</html>","Rappel de règles", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public void tourJeu() {
		String text1 ;
		String text2;
		
		if (tour == 1) {
			text1="<html>Joueur 1, à toi de jouer.<br><br>";
		}
		else {
			text1="<html>Joueur 2, à toi de jouer.<br><br>";
		}
		
		switch (phase) {
			case 1:
			case 2:
			case 3:
			case 4:
				text2="Sélectionner une tuile.</html>";
				break;
			case 5:
			case 6: 
			case 7: 
			case 8: 
				text2="Positionner la tuile.";
				break;
			default :
				text2="None";
				break;
		}
		
		
		JOptionPane.showMessageDialog(null, text1+text2,"Tour de jeu", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void finPhase() {
		if (tour==1) {
			tour=2;
		} 
		else {
			tour=1;
		}
		phase=phase+1;
		
		panel.removeAll();
		setContentPane(buildContentPane());
		
		tourJeu();
		
		
		
	}
	
	public void verifChoixTuile() {
		boolean dejaRes=false;
		for (int i=0;i<4;i++) {
			if (reserved[tuileSelected]==1) {
				dejaRes=true;
			}
		}
		if (dejaRes==true) {
			JOptionPane.showMessageDialog(null, "<html>Erreur.<br><br>Cette tuile est déjà réservée.</html>","Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			reserved[tuileSelected]=1;
			
			switch (phase) {
				case 1:
					tempPartie.getJ1().setOrdre(new int[]{tuileSelected,tempPartie.getJ1().getOrdre()[1]});
					//tempPartie.getJ1().setReservation(tempPartie.getTirage()[tuileSelected]);
					break;
				case 2:
					tempPartie.getJ2().setOrdre(new int[]{tuileSelected,tempPartie.getJ2().getOrdre()[1]});
					break;
				case 3:
					tempPartie.getJ1().setOrdre(new int[]{tempPartie.getJ1().getOrdre()[0],tuileSelected});
					break;
				case 4:
					tempPartie.getJ2().setOrdre(new int[]{tempPartie.getJ2().getOrdre()[0],tuileSelected});
					break;
			}
			
			finPhase();
		}
	}

}