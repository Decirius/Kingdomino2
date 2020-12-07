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
	JRadioButton choixIaCompetitive;
	JRadioButton choixIaPoseTuile;
	JRadioButton choixIaChoixTuile;
	JRadioButton choix2Humain ;
	JRadioButton choix2IaAleatoire ;
	JRadioButton choix2IaStandard;
	JRadioButton choix2IaCompetitive;
	JRadioButton choix2IaPoseTuile;
	JRadioButton choix2IaChoixTuile;
	JCheckBox modeSansPopup;
	
	
	public FenetreChoix() {
		
		// Creation de la fenetre
		
		setTitle("Kingdomino - Choix des joueurs");
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
		setVisible(true);		
				
	}
	
	private JPanel buildContentPane() {
			
		
		panel.setLayout(new GridLayout(5,1));
		
		JPanel panelHaut = new JPanel();
		panelHaut.setLayout(new GridLayout(1,2));
		
		JPanel panelMilieu = new JPanel();
		panelMilieu.setLayout(new GridLayout(1,2));
		
		JPanel panelBas = new JPanel();
		panelBas.setLayout(new GridLayout(1,2));
		
		JPanel choixMode = new JPanel();
		
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
		choixIaAleatoire = new JRadioButton ("IA Aleatoire");
		choixIaStandard = new JRadioButton ("IA Standard");
		choixIaCompetitive = new JRadioButton ("IA Competitive");
		choixIaPoseTuile = new JRadioButton("IA pose de tuile");
		choixIaChoixTuile = new JRadioButton("IA choix tuile");
		
		choixHumain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixIaAleatoire.setSelected(false);
				choixIaStandard.setSelected(false);
				choixHumain.setSelected(true);
				choixIaCompetitive.setSelected(false);
				choixIaPoseTuile.setSelected(false);
				choixIaChoixTuile.setSelected(false);
			}
		});
		choixIaAleatoire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixHumain.setSelected(false);
				choixIaStandard.setSelected(false);
				choixIaAleatoire.setSelected(true);
				choixIaCompetitive.setSelected(false);
				choixIaPoseTuile.setSelected(false);
				choixIaChoixTuile.setSelected(false);
			}
		});
		choixIaStandard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixIaAleatoire.setSelected(false);
				choixHumain.setSelected(false);
				choixIaStandard.setSelected(true);
				choixIaCompetitive.setSelected(false);
				choixIaPoseTuile.setSelected(false);
				choixIaChoixTuile.setSelected(false);
			}
		});
		choixIaCompetitive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixIaAleatoire.setSelected(false);
				choixHumain.setSelected(false);
				choixIaStandard.setSelected(false);
				choixIaCompetitive.setSelected(true);
				choixIaPoseTuile.setSelected(false);
        choixIaChoixTuile.setSelected(false);
			}
		});

		choixIaPoseTuile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixIaAleatoire.setSelected(false);
				choixHumain.setSelected(false);
				choixIaStandard.setSelected(false);
				choixIaCompetitive.setSelected(false);
				choixIaPoseTuile.setSelected(true);
				choixIaChoixTuile.setSelected(false);
			}
		});

		choixIaChoixTuile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choixIaAleatoire.setSelected(false);
				choixHumain.setSelected(false);
				choixIaStandard.setSelected(false);
				choixIaCompetitive.setSelected(false);
				choixIaChoixTuile.setSelected(true);
        choixIaPoseTuile.setSelected(false);
			}
		});




		choix2Humain = new JRadioButton("Humain",true);
		choix2IaAleatoire = new JRadioButton ("IA Aleatoire");
		choix2IaStandard = new JRadioButton ("IA Standard");
		choix2IaCompetitive = new JRadioButton ("IA Competitive");
		choix2IaPoseTuile = new JRadioButton("IA pose de tuile");
		choix2IaChoixTuile = new JRadioButton("IA choix tuile");
		
		choix2Humain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix2IaAleatoire.setSelected(false);
				choix2IaStandard.setSelected(false);
				choix2Humain.setSelected(true);
				choix2IaCompetitive.setSelected(false);
				choix2IaPoseTuile.setSelected(false);
				choix2IaChoixTuile.setSelected(false);
			}
		});
		choix2IaAleatoire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix2Humain.setSelected(false);
				choix2IaStandard.setSelected(false);
				choix2IaAleatoire.setSelected(true);
				choix2IaCompetitive.setSelected(false);
				choix2IaPoseTuile.setSelected(false);
				choix2IaChoixTuile.setSelected(false);
			}
		});
		choix2IaStandard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix2Humain.setSelected(false);
				choix2IaAleatoire.setSelected(false);
				choix2IaStandard.setSelected(true);
				choix2IaCompetitive.setSelected(false);
				choix2IaPoseTuile.setSelected(false);
				choix2IaChoixTuile.setSelected(false);
			}
		});
		choix2IaCompetitive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix2Humain.setSelected(false);
				choix2IaAleatoire.setSelected(false);
				choix2IaStandard.setSelected(false);
				choix2IaCompetitive.setSelected(true);
				choix2IaPoseTuile.setSelected(false);
        choix2IaChoixTuile.setSelected(false);
			}
		});

		choix2IaPoseTuile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix2Humain.setSelected(false);
				choix2IaAleatoire.setSelected(false);
				choix2IaStandard.setSelected(false);
				choix2IaCompetitive.setSelected(false);
				choix2IaPoseTuile.setSelected(true);
        choix2IaChoixTuile.setSelected(false);
			}
		});

		choix2IaChoixTuile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				choix2Humain.setSelected(false);
				choix2IaAleatoire.setSelected(false);
				choix2IaStandard.setSelected(false);
				choix2IaCompetitive.setSelected(false);
				choix2IaChoixTuile.setSelected(true);
        choix2IaPoseTuile.setSelected(false);
			}
		});


		modeSansPopup= new JCheckBox("Mode expert");
		
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
		panelCg.add(choixIaStandard);
		panelCg.add(choixIaAleatoire);
		panelCg.add(choixIaCompetitive);
		panelCg.add(choixIaPoseTuile);
		panelCg.add(choixIaChoixTuile);
		panelCd.add(choix2Humain);
		panelCd.add(choix2IaStandard);
		panelCd.add(choix2IaAleatoire);
		panelCd.add(choix2IaCompetitive);
		panelCd.add(choix2IaPoseTuile);
		panelCd.add(choix2IaChoixTuile);
		panelBas.add(panelCg);
		panelBas.add(panelCd);
		choixMode.add(modeSansPopup);
		panelBouton.add(boutonValider);
		panelBouton.add(boutonQuitter);
		
		panel.add(panelHaut);
		panel.add(panelMilieu);
		panel.add(panelBas);
		panel.add(choixMode);
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
			
			int valJ1=0;
			int valJ2=0;
			
			if (choixHumain.isSelected()) {
				valJ1=0;
			} else if (choixIaAleatoire.isSelected()) {
				valJ1=1;
			} else if (choixIaStandard.isSelected()) {
				valJ1=2;
			} else if (choixIaCompetitive.isSelected()) {
				valJ1=3;
      } else if (choixIaChoixTuile.isSelected()) {
				valJ1=4;
			} else if (choixIaPoseTuile.isSelected()) {
				valJ1=5;
			} 
			
			if (choix2Humain.isSelected()) {
				valJ2=0;
			} else if (choix2IaAleatoire.isSelected()) {
				valJ2=1;
			} else if (choix2IaStandard.isSelected()) {
				valJ2=2;
			} else if (choix2IaCompetitive.isSelected()) {
				valJ2=3;
      } else if (choix2IaChoixTuile.isSelected()) {
				valJ2=4;
			} else if (choix2IaPoseTuile.isSelected()) {
				valJ2=5;
			} 

			Partie partie = new Partie(choixnom.getText(),choixnom2.getText(),valJ1,valJ2,!modeSansPopup.isSelected());
			FenetreJeu jeu = new FenetreJeu(partie);
			this.setVisible(false);
			jeu.setVisible(true);
		}
	}
	
}
