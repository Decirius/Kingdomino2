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
	JRadioButton choixIa;
	JRadioButton choix2Humain ;
	JRadioButton choix2Ia ;
	
	
	public FenetreChoix() {
		
		// Création de la fenetre
		
		setTitle("Kingdomino - Choix des joueurs");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setVisible(true);		
				
	}
	
	private JPanel buildContentPane() {
			
		
		panel.setLayout(new GridLayout(4,1));
		
		JPanel panelHaut = new JPanel();
		panelHaut.setLayout(new GridLayout(1,2));
		
		JPanel panelMilieu = new JPanel();
		panelMilieu.setLayout(new GridLayout(1,2));
		
		JPanel panelBas = new JPanel();
		panelBas.setLayout(new GridLayout(1,2));
		
		JPanel panelBouton = new JPanel();
		
		JPanel panelTxtg = new JPanel();
		JPanel panelTxtd = new JPanel();
		
		JPanel panelCg = new JPanel();
		JPanel panelCd = new JPanel();
		
		JLabel nom = new JLabel("J1");
		JLabel nom2 = new JLabel("J2");
		nom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		nom2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		choixnom = new TextField ("Nom J1");
		choixnom2 = new TextField ("Nom J2");
		choixnom.setColumns(10);
		choixnom2.setColumns(10);
		
		choixHumain = new JRadioButton ("Humain",true);
		choixIa = new JRadioButton ("IA");
		choix2Humain = new JRadioButton("Humain",true);
		choix2Ia = new JRadioButton ("IA");
		
		boutonQuitter = new JButton("Quitter");
		boutonValider = new JButton ("Valider");
		boutonQuitter.addActionListener(this);
		boutonValider.addActionListener(this);
		
		boutonQuitter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		boutonValider.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		panelHaut.add(nom);
		panelHaut.add(nom2);
		panelTxtg.add(choixnom);
		panelTxtd.add(choixnom2);
		panelMilieu.add(panelTxtg);
		panelMilieu.add(panelTxtd);
		panelCg.add(choixHumain);
		panelCg.add(choixIa);
		panelCd.add(choix2Humain);
		panelCd.add(choix2Ia);
		panelBas.add(panelCg);
		panelBas.add(panelCd);
		panelBouton.add(boutonValider);
		panelBouton.add(boutonQuitter);
		
		panel.add(panelHaut);
		panel.add(panelMilieu);
		panel.add(panelBas);
		panel.add(panelBouton);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(boutonQuitter)) {
			this.dispose();
		}
		
		if (e.getSource().equals(boutonValider)) {
			
			Partie partie = new Partie(choixnom.getText(),choixnom2.getText());
			FenetreJeu jeu = new FenetreJeu(partie);
			jeu.setVisible(true);
			this.setVisible(false);
		}
	}
	
}
