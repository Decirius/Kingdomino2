import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

import javax.swing.*;

public class Fenetre_jeu extends JFrame implements ActionListener {

	JLabel[][] myLabelArray1 = new JLabel[9][9];
	JLabel[][] myLabelArray2 = new JLabel[9][9];
	JPanel panel = new JPanel();
	
	JMenuItem sauvegarder;
	JMenuItem quitter;
	
	Partie temp_partie;
	
	private static final long serialVersionUID = 1L;

		
	public Fenetre_jeu() {
		
		// Création de la fenetre
		
		temp_partie=new Partie("Michou","Jean Pipol");
		temp_partie.faireTirage();
		
		setTitle("Kingdomino - Partie en cours");
		setSize(1000,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setJMenuBar(buildMenu());
		setVisible(true);
		
		
				
	}
	
	
	private JPanel buildContentPane() {
		
		
		panel.setLayout(new BorderLayout(50,50));
		
		JPanel panel_haut=new JPanel();
		panel_haut.setLayout(new GridLayout(1,2));
		
		JPanel panel_nom = new JPanel();
		panel_nom.setLayout(new GridLayout(1,2));
		
		JPanel panel_gauche = new JPanel();
		JPanel panel_droite=new JPanel();
		
		JPanel panel_bas=new JPanel();
		panel_bas.setLayout(new BorderLayout(20,20));
		
		JPanel panel_bas_tuile = new JPanel();
		JPanel panel_bas_token = new JPanel();
				
				
		JPanel grille1=constructGrille(myLabelArray1,temp_partie.getJ1().getGrille());
		JPanel grille2=constructGrille(myLabelArray2,temp_partie.getJ2().getGrille());
		
		JLabel label_nom_1= new JLabel(temp_partie.getJ1().getNom());
		JLabel label_nom_2= new JLabel(temp_partie.getJ2().getNom());
		
		label_nom_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		label_nom_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		panel_nom.add(label_nom_1);
		panel_nom.add(label_nom_2);
		
	
		panel_droite.add(grille1);
		panel_gauche.add(grille2);
		
		
		for (int i=0; i<4;i++ ) {
			panel_bas_tuile.add(constructTuile(temp_partie.getTirage()[i]));
			panel_bas_token.add(constructToken());
			
		}	
		
		panel_bas.add(panel_bas_token, BorderLayout.CENTER);
		panel_bas.add(panel_bas_tuile, BorderLayout.SOUTH);
		
		panel_haut.add(panel_gauche);
		panel_haut.add(panel_droite);
		panel.add(panel_nom,BorderLayout.NORTH);
		panel.add(panel_haut,BorderLayout.CENTER);
		panel.add(panel_bas,BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel constructGrille (JLabel[][] array,Grille grilletest) {
		
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
				label.setName(""+i+""+j+""+url);
				array[i][j]=label;
				
				array[i][j].addMouseListener(new MouseAdapter() {
				    public void mousePressed(MouseEvent e) {
				        System.out.println(label.getName());
				    }
				});
				grille.add(label); 
				
			}
		} 
		
		return grille;
	}
	
	private JPanel constructTuile(Tuile t) {
		
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
		
		return tuile;
	}
	
	private JMenuBar buildMenu() {
		JMenuBar menu = new JMenuBar();
		
		sauvegarder = new JMenuItem ("Sauvegarder");
		quitter = new JMenuItem ("Quitter");
		
		JMenu menuFile = new JMenu("Fichier");

		menuFile.add(sauvegarder);
		menuFile.add(quitter);
		
		sauvegarder.addActionListener(this);
		quitter.addActionListener(this);

		
		menu.add(menuFile);
		
		return menu;
	}
	
	private JPanel constructToken() {
		
		JPanel token = new JPanel();
		
		
		String url="images/J1.jpg";
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
		
		
		
	}

}
