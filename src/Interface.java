import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Interface {

	private JFrame frmKingdomino;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frmKingdomino.setVisible(true);
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
		frmKingdomino = new JFrame();
		frmKingdomino.setTitle("Kingdomino");
		frmKingdomino.setBounds(100, 100, 733, 538);
		frmKingdomino.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
