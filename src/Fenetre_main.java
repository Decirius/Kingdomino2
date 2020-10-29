import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre_main extends JFrame implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JButton bouton_nouveau = new JButton("Nouvelle Partie");
	JButton bouton_charger = new JButton("Charger une Partie");	
	JButton bouton_quitter = new JButton("Quitter");
	
	public Fenetre_main() {
		
		// Création de la fenetre
		setTitle("Kingdomino");
		setSize(500,690);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setVisible(true);
		
	}
	
	
	private JPanel buildContentPane() {
		// Initialisation des panels pour organiser la page
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		JPanel panel_logo = new JPanel();
		panel_logo.setLayout(new GridLayout(1,1));
		JPanel panel_bouton = new JPanel();
		panel_bouton.setLayout(new GridLayout(3,1));
			
		// Initialisation des panels
		panel_bouton.setBackground(Color.white);


		ImageIcon imgbg=new ImageIcon("images/kingmain.png");
		JLabel img = new JLabel(imgbg);
		panel_logo.add(img);
		
		panel_bouton.add(bouton_nouveau);
		panel_bouton.add(bouton_charger);
		panel_bouton.add(bouton_quitter);
		
		bouton_quitter.addActionListener(this);
		bouton_nouveau.addActionListener(this);
		bouton_charger.addActionListener(this);
		
		// Assemblage panels
		panel.add(panel_logo);
		panel.add(panel_bouton);
		
		return panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bouton_quitter)) {
			this.dispose();
		}
		if (e.getSource().equals(bouton_nouveau)) {
			Fenetre_jeu jeu = new Fenetre_jeu();
			jeu.setVisible(true);
			this.setVisible(false);
		}
		if (e.getSource().equals(bouton_charger)) {
		
		}
		
	}
}
