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

public class FenetreMain extends JFrame implements ActionListener {
	
	JButton boutonNouveau = new JButton("Nouvelle Partie");
	JButton boutonCharger = new JButton("Charger une Partie");	
	JButton boutonQuitter = new JButton("Quitter");
	
	
	public FenetreMain() {		
																		// Création de la fenetre
		setTitle("Kingdomino");
		setSize(500,690);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setVisible(true);
		
	}
	
	
	private JPanel buildContentPane() {
																		
		JPanel panel = new JPanel();									// Panel general qui contient tout, grille 2 lignes 1 colonne
		panel.setLayout(new GridLayout(2,1));
		JPanel panelLogo = new JPanel();								// Panel contenant l'image d'entete, grille 1 ligne 1 colonne
		panelLogo.setLayout(new GridLayout(1,1));
		JPanel panelBouton = new JPanel();								// Panel contenant les boutons d'action, 3 lignes, 1 colonne
		panelBouton.setLayout(new GridLayout(3,1));		
																		
		panelBouton.setBackground(Color.white);
		
		ImageIcon imgbg=new ImageIcon("images/kingmain.png");			// Importation de l'image dans le panel
		JLabel img = new JLabel(imgbg);
		panelLogo.add(img);
		
		panelBouton.add(boutonNouveau);									// Ajout des boutons
		panelBouton.add(boutonCharger);
		panelBouton.add(boutonQuitter);
		
		boutonQuitter.addActionListener(this);							// Connexions des actions des boutons
		boutonNouveau.addActionListener(this);
		boutonCharger.addActionListener(this);
		
		panel.add(panelLogo);											// Assemblage des panels
		panel.add(panelBouton);
		
		return panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(boutonQuitter)) {						// Si bouton quitter : on detruit la fenetre
			this.dispose();
		}
		if (e.getSource().equals(boutonNouveau)) {						// Si bouton nouveau : creation de la fenetre de choix
			FenetreChoix choix = new FenetreChoix();
			choix.setVisible(true);
			this.setVisible(false);
		}
		if (e.getSource().equals(boutonCharger)) {						// Si bouton charger : rien pour l'instant
		
		}
		
	}
}
