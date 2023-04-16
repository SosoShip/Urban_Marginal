package vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.Controle;

import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.SwingConstants;

/**
 * Frame du choix du joueur
 * @author emds
 *
 */
public class ChoixJoueur extends JFrame {
	/**
	 * Communication avec la classe controle
	 */	
	private Controle controle;

	/**
	 * Panel g�n�ral
	 */
	private JPanel contentPane;
	
	/**
	 * Zone d'affichage du joueur à choisir :
	 */
	private JLabel lblPersonnage;

	/**
	 * Zone de saisie du pseudo
	 */
	private JTextField txtPseudo;
	
	/**
	 * Numéro du personnage à afficher :
	 */
	private int numPersonnage = 1;
	
	/**
	 * nombre de personnages maximum
	 */
	private int persoMax = 3;

	/**
	 * Clic sur la fl�che "pr�c�dent" pour afficher le personnage pr�c�dent
	 */
	private void lblPrecedent_clic() {
		System.out.println("Clic sur precedent");
		personnagePrecedant();
	}
	
	/**
	 * Clic sur la fl�che "suivant" pour afficher le personnage suivant
	 */
	private void lblSuivant_clic() {
		System.out.println("Clic sur suivant");
		personnageSuivant();
	}
	
	/**
	 * Clic sur GO pour envoyer les informations
	 */
	private void lblGo_clic() {
		(new Arene()).setVisible(true);
		this.dispose();
	}

	private void affichePerso(int numPersonnage) {
		String chemin = "personnages\\perso"+numPersonnage+"marche1d1.gif";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblPersonnage.setIcon(new ImageIcon(resource));
	}
	
	/**
	 * Personnage suivant :
	 */
	private void personnageSuivant() {		
		if (numPersonnage < persoMax) {
			numPersonnage ++;		
		}
		else {
			numPersonnage = 1;			
		}
		affichePerso(numPersonnage);
	}
	
	/**
	 * personnage precedant :
	 */
	private void personnagePrecedant() {
		
		if (numPersonnage > 1) {
			numPersonnage --;		
		}
		else {
			numPersonnage = persoMax;			
		}
		affichePerso(numPersonnage);
	}
	
	/**
	 * Souris normale sur les zones non clickables :
	 */
	private void sourisNormale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	
	/*
	 * Souris en forme de main sur les zones clickables :
	 */
	private void sourisMain() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	
	/**
	 * Create the frame.
	 */
	public ChoixJoueur() {
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
	    this.pack();
	    // interdiction de changer la taille
		this.setResizable(false);
		 
		setTitle("Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrecedent = new JLabel("");
		lblPrecedent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblPrecedent_clic();				
			}
			// Changement de forme de la souris :
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisMain();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		
		JLabel lblSuivant = new JLabel("");
		lblSuivant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblSuivant_clic();
			}
			// Changement de forme de la souris :
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisMain();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		
		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();				
			}
			// Changement de forme de la souris :
			@Override
			public void mouseEntered(MouseEvent e) {
				sourisMain();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		
		lblPersonnage = new JLabel("New label");
		lblPersonnage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonnage.setBounds(142, 115, 120, 117);
		contentPane.add(lblPersonnage);
		
		txtPseudo = new JTextField();
		txtPseudo.setBounds(142, 245, 120, 20);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		lblGo.setBounds(311, 202, 65, 61);
		contentPane.add(lblGo);
		lblSuivant.setBounds(301, 145, 25, 46);
		contentPane.add(lblSuivant);
		lblPrecedent.setBounds(65, 146, 31, 45);
		contentPane.add(lblPrecedent);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		String chemin = "fonds\\fondchoix.jpg";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));		
		contentPane.add(lblFond);				
						
		// positionnement sur la zone de saisie
		txtPseudo.requestFocus();
		
		// Affichage du premier personnage :
		numPersonnage = 1;
		affichePerso(numPersonnage);

	}
}