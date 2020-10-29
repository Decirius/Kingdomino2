import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Button;

public class Interface {

	private JFrame start_fen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.start_fen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		start_fen = new JFrame();
		start_fen.setTitle("Kingdomino");
		start_fen.setBounds(100, 100, 700, 500);
		start_fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JPanel panel_ge = new JPanel();
		panel_ge.setBackground(Color.LIGHT_GRAY);
		start_fen.getContentPane().add(panel_ge, BorderLayout.CENTER);
		panel_ge.setLayout(new GridLayout(2, 1, 0, 10));
		
		JPanel panel_haut = new JPanel();
		panel_haut.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_haut.setBackground(Color.PINK);
		panel_haut.setForeground(new Color(0, 0, 0));
		panel_ge.add(panel_haut);
		
		JPanel panel_bas = new JPanel();
		panel_ge.add(panel_bas);
		panel_bas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_bas.setBackground(Color.ORANGE);
		panel_bas.setLayout(new GridLayout(0, 2, 50, 0));
		
		String url = "images/kingmain.png";
		ImageIcon image = new ImageIcon(url);
		JLabel label = new JLabel(image,JLabel.CENTER);
		panel_haut.add(label);
		
		JPanel panel_bas_gauche = new JPanel();
		panel_bas_gauche.setBackground(Color.CYAN);
		panel_bas.add(panel_bas_gauche);
		panel_bas_gauche.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton bouton_new = new JButton("Nouvelle Partie");
		panel_bas_gauche.add(bouton_new);
		
		JButton btnNewButton = new JButton("Charger Partie");
		panel_bas_gauche.add(btnNewButton);
		
		JPanel panel_bas_droit = new JPanel();
		panel_bas_droit.setBackground(Color.MAGENTA);
		panel_bas.add(panel_bas_droit);
		panel_bas_droit.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton bouton_quitter = new JButton("Quitter");
		panel_bas_droit.add(bouton_quitter);
		
		
		bouton_quitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				start_fen.dispose();
			}
		});
	}

}
