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

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JButton boutonNouveau = new JButton("Nouvelle Partie");
	JButton boutonCharger = new JButton("Statistiques (a venir)");
	JButton boutonQuitter = new JButton("Quitter");
	
	public FenetreMain() {
		
		// Cr�ation de la fenetre
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
		JPanel panelLogo = new JPanel();
		panelLogo.setLayout(new GridLayout(1,1));
		JPanel panelBouton = new JPanel();
		panelBouton.setLayout(new GridLayout(3,1));
			
		// Initialisation des panels
		panelBouton.setBackground(Color.white);


		ImageIcon imgbg=new ImageIcon("images/kingmain.png");
		JLabel img = new JLabel(imgbg);
		panelLogo.add(img);
		
		panelBouton.add(boutonNouveau);
		panelBouton.add(boutonCharger);
		panelBouton.add(boutonQuitter);
		
		boutonQuitter.addActionListener(this);
		boutonNouveau.addActionListener(this);
		boutonCharger.addActionListener(this);
		
		// Assemblage panels
		panel.add(panelLogo);
		panel.add(panelBouton);
		
		return panel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(boutonQuitter)) {
			this.dispose();
		}
		if (e.getSource().equals(boutonNouveau)) {
			FenetreChoix choix = new FenetreChoix();
			choix.setVisible(true);
			this.setVisible(false);
		}
		if (e.getSource().equals(boutonCharger)) {
		
		}
		
	}
}
