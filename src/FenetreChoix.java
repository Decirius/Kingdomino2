import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FenetreChoix extends JFrame implements ActionListener {
	
	JPanel panel = new JPanel();
	JButton boutonQuitter;
	JButton boutonValider;
	TextField choixnom;
	TextField choixnom2;
	JRadioButton choixHumain;
	JRadioButton choixIaAleatoire;
	JRadioButton choixIaStandard;
	JRadioButton choix2Humain;
	JRadioButton choix2IaAleatoire;
	JRadioButton choix2IaStandard;
	
	
	public FenetreChoix() {
																	
		setTitle("Kingdomino - Choix des joueurs");					// Creation de la fenetre
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setVisible(true);
				
	}
	
	
	private JPanel buildContentPane() {
			
		
		panel.setLayout(new GridLayout(4,1));						// Panel general : grille de 4 lignes et 1 colonne
		
		JPanel panelHaut = new JPanel();							// Panel de tout en haut, contient J1, J2, grille de 1 ligne et 2 colonnes
		panelHaut.setLayout(new GridLayout(1,2));
		
		JPanel panelMilieu = new JPanel();							// Panel contenant les champs des noms, grille de 1 ligne et 2 colonnes
		panelMilieu.setLayout(new GridLayout(1,2));
		
		JPanel panelBas = new JPanel();								// Panel contenant les radioboutons, grille de 1 ligne et 3 colonnes 
		panelBas.setLayout(new GridLayout(1,3));
		
		JPanel panelBouton = new JPanel();							// Panel contenant les boutons d'action
		
		JPanel panelTxtg = new JPanel();							// Sous-panel contenant les textes J1, J2
		JPanel panelTxtd = new JPanel();
		
		JPanel panelCg = new JPanel();								// Sous-panel contenant les radioboutons
		JPanel panelCd = new JPanel();
		
		JLabel nom = new JLabel("J1");								// Entete des colonnes
		JLabel nom2 = new JLabel("J2");
		nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nom2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		choixnom = new TextField ("Nom J1");						// Champs textes des noms J1/J2
		choixnom2 = new TextField ("Nom J2");
		choixnom.setColumns(10);
		choixnom2.setColumns(10);
		
		choixHumain = new JRadioButton ("Humain",true);				// Radioboutons du J1
		choixIaAleatoire = new JRadioButton ("IaAleatoire");
		choixIaStandard = new JRadioButton("IaStandard");
		
		choixHumain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixIaAleatoire.setSelected(false);
				choixIaStandard.setSelected(false);
			}
		});
		choixIaAleatoire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixHumain.setSelected(false);
				choixIaStandard.setSelected(false);
			}
		});
		choixIaStandard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixHumain.setSelected(false);
				choixIaAleatoire.setSelected(false);
			}
		});
		
		
		
		choix2Humain = new JRadioButton("Humain",true);					// Radioboutons du J2
		choix2IaAleatoire = new JRadioButton ("IaAleatoire");
		choix2IaStandard = new JRadioButton ("IaStandard");
		
		choix2Humain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix2IaAleatoire.setSelected(false);
				choix2IaStandard.setSelected(false);
			}
		});
		choix2IaAleatoire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix2Humain.setSelected(false);
				choix2IaStandard.setSelected(false);
			}
		});
		choix2IaStandard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix2Humain.setSelected(false);
				choix2IaAleatoire.setSelected(false);
			}
		});
		
		boutonQuitter = new JButton("Quitter");
		boutonValider = new JButton ("Valider");
		boutonQuitter.addActionListener(this);							// On rattache les boutons a leur action
		boutonValider.addActionListener(this);
		
		boutonQuitter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);		// On alligne les boutons par esthetisme
		boutonValider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		panelHaut.add(nom);												// On rassemble tous les panels les uns dans les autres
		panelHaut.add(nom2);
		panelTxtg.add(choixnom);
		panelTxtd.add(choixnom2);
		panelMilieu.add(panelTxtg);
		panelMilieu.add(panelTxtd);
		panelCg.add(choixHumain);
		panelCg.add(choixIaAleatoire);
		panelCd.add(choix2Humain);
		panelCd.add(choix2IaAleatoire);
		panelBas.add(panelCg);
		panelBas.add(panelCd);
		panelBouton.add(boutonValider);
		panelBouton.add(boutonQuitter);
		
		panel.add(panelHaut);
		panel.add(panelMilieu);
		panel.add(panelBas);
		panel.add(panelBouton);
		
		return panel;													// On renvoit le panel qui contient tous les tous les sous-panels et infos
		
	}

	
	public void actionPerformed(ActionEvent e) {						// Action des boutons
		// TODO Auto-generated method stub
		if (e.getSource().equals(boutonQuitter)) {						// Si bouton quitter : detruit la fenetre
			this.dispose();
		}
		
		if (e.getSource().equals(boutonValider)) {						// Si bouton valider : on cree la partie
			
			int valJ1=0;
			int valJ2=0;
			
			if (choixHumain.isSelected()) {								// Codage du choix du type de joueur 1
				valJ1=0;
			} else if (choixIaAleatoire.isSelected()) {
				valJ1=1;
			} else if (choixIaStandard.isSelected()) {
				valJ1=2;
			}
			
			if (choix2Humain.isSelected()) {							// Codage du choix du type de joueur 2
				valJ2=0;
			} else if (choix2IaAleatoire.isSelected()) {
				valJ2=1;
			} else if (choix2IaStandard.isSelected()) {
				valJ2=2;
			}
					
				
			Partie partie = new Partie(choixnom.getText(),choixnom2.getText(),valJ1,valJ2);				// On cree la partie
			FenetreJeu jeu = new FenetreJeu(partie);													// On lance la fenetre de jeu avec la partie nouvellement cree
			this.setVisible(false);
			jeu.setVisible(true);
	
		}
		
	}
	
}
