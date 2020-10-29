import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

import javax.swing.*;

public class Fenetre_jeu extends JFrame implements ActionListener {

	JButton bouton_quitter = new JButton("Quitter");
	JLabel[][] myLabelArray1 = new JLabel[9][9];
	JLabel[][] myLabelArray2 = new JLabel[9][9];
	JPanel panel = new JPanel();
	
	private static final long serialVersionUID = 1L;

		
	public Fenetre_jeu() {
		
		// Création de la fenetre
		setTitle("Kingdomino - Partie en cours");
		setSize(1000,1000);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setVisible(true);
				
	}
	
	
	private JPanel buildContentPane() {
		
		
		panel.setLayout(new GridLayout(1,2));
		JPanel panel_gauche = new JPanel();
		JPanel panel_droite=new JPanel();
		
		
		
		bouton_quitter.addActionListener(this);
		panel_droite.add(constructGrille(myLabelArray1));
		panel_gauche.add(constructGrille(myLabelArray2));
		
		panel.add(panel_gauche);
		panel.add(panel_droite);
		
		return panel;
	}
	
	private JPanel constructGrille (JLabel[][] array) {
		
		JPanel grille = new JPanel();
		grille.setLayout(new GridLayout(9,9));
		grille.setBorder(BorderFactory.createEmptyBorder(2,2,2,2)); 
		
		ImageIcon imgbg=new ImageIcon("images/0-0.jpg");
		
		for (int i =0; i<(9); i++){ 
			for (int j=0; j<9;j++) {
				JLabel label = new JLabel(imgbg); 
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				label.setName(""+i+""+j+"");
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bouton_quitter)) {
			this.dispose();
		}
		
		
		
	}

}
