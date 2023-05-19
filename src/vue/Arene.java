package vue;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modele.Mur;

/**
 * frame de l'ar�ne du jeu
 * @author emds
 *
 */
public class Arene extends JFrame {

	/**
	 * Panel g�n�ral
	 */
	private JPanel contentPane;
	/**
	 * Zone de saisie du t'chat
	 */
	private JTextField txtSaisie;
	/**
	 * Zone d'affichage du t'chat
	 */
	private JTextArea txtChat ;
	/**
	 * Zone d'affichage des murs :
	 */
	private JPanel jpnMurs;
	/**
	 * Zone d'affichage des joueurs :
	 */
	private JPanel jpnJeu;

	/**
	 * Create the frame.
	 */
	public Arene() {
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(800, 600 + 25 + 140));
	    this.pack();
	    // interdiction de changer la taille
	    
		this.setResizable(false);
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.setJpnJeu(new JPanel());
		setJpnJeu(new JPanel());
		getJpnJeu().setBounds(0, 0, 800, 600);
		getJpnJeu().setOpaque(false);
		getJpnJeu().repaint();
		contentPane.add(getJpnJeu());
		
		this.jpnMurs = new JPanel();
		setJpnMurs(new JPanel());
		getJpnMurs().setBounds(0, 0, 800, 600);
		getJpnMurs().setOpaque(false);
		getJpnMurs().repaint();
		contentPane.add(getJpnMurs());
		getJpnMurs().setLayout(null);
	
		txtSaisie = new JTextField();
		txtSaisie.setBounds(0, 600, 800, 25);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		contentPane.add(jspChat);
		
		txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);
		
		JLabel lblFond = new JLabel("");
		String chemin = "fonds\\fondarene.jpg";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));		
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);		
	}
	
	public void ajoutMurs(Object unMur) {
		getJpnMurs().add((JLabel)unMur);
	}
	
	public void ajoutLblPersoArene(Object lblPerso) {
		getJpnMurs().add((JLabel)lblPerso);
		getJpnJeu().repaint();
	}

	public JPanel getJpnMurs() {
		return jpnMurs;
	}

	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}

	public JPanel getJpnJeu() {
		return jpnJeu;
	}

	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu = jpnJeu;
	}
	

}