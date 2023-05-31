package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controle;
import modele.JeuClient;
import modele.Mur;
import java.awt.event.KeyAdapter;

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
	 * Communication avec la classe Controle
	 */
	private Controle controleJeu;

	/**
	 * Create the frame.
	 */
	public Arene(Controle controle) {
		this.controleJeu = controle;
		
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(800, 600 + 25 + 140));
	    this.pack();
	    // interdiction de changer la taille	    
		this.setResizable(false);
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new JPanel();
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		this.jpnJeu = new JPanel();
		this.setJpnJeu(new JPanel());
		setJpnJeu(new JPanel());
		getJpnJeu().setBounds(0, 0, 800, 600);
		getJpnJeu().setOpaque(false);
		getJpnJeu().repaint();
		contentPane.add(getJpnJeu());
		getJpnJeu().setLayout(null);
		
		this.jpnMurs = new JPanel();
		setJpnMurs(new JPanel());
		getJpnMurs().setBounds(0, 0, 800, 600);
		getJpnMurs().setOpaque(false);
		getJpnMurs().repaint();
		contentPane.add(getJpnMurs());
		getJpnMurs().setLayout(null);
	
		// 	Création de la zon de saisie coté jeuClient :	
		if (controleJeu.isClient()) {			
		
			this.txtSaisie = new JTextField();
			this.txtSaisie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						if(!txtSaisie.getText().isEmpty() || !txtSaisie.getText().isBlank()) {
							controleJeu.evenementArene(txtSaisie.getText());
							txtSaisie.setText("");
						}
					}
				}
			});
			this.txtSaisie.setBounds(0, 600, 800, 25);
			this.contentPane.add(txtSaisie);
			this.txtSaisie.setColumns(10);						
		}
		// Création zone du chat :
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		this.contentPane.add(jspChat);
		
		this.txtChat = new JTextArea();		
		jspChat.setViewportView(this.txtChat);
		this.txtChat.setEditable(false);
		
		JLabel lblFond = new JLabel("");
		String chemin = "fonds\\fondarene.jpg";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));		
		lblFond.setBounds(0, 0, 800, 600);
		this.contentPane.add(lblFond);		
	}
	
	/**
	 * Ajoute le label d'un mur dans l'arene :
	 * @param unMur
	 */
	public void ajoutMurs(Object unMur) {
		getJpnMurs().add((JLabel)unMur);
	}
	/**
	 * Ajoute le label d'un personnage dans l'arene :
	 * @param lblPerso
	 */
	public void ajoutLblPersoArene(Object lblPerso) {
		getJpnJeu().add((JLabel)lblPerso);
		getJpnJeu().repaint();
	}
	/**
	 * Ajoute le texte dsais par un joueur au tchat :
	 * @param textSaisi
	 * @return
	 */
	public String ajoutChat(String textSaisi) {
		this.setTxtChat( getTxtChat() + "\r\n" + textSaisi + "\r\n");
		return getTxtChat();
	}	

	/**
	 * getter panel Mur :
	 * @return
	 */
	public JPanel getJpnMurs() {
		return this.jpnMurs;
	}

	/**
	 * Setter panel mur :
	 * @param jpnMurs
	 */
	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}

	/**
	 * getter panel jeu :
	 * @return
	 */
	public JPanel getJpnJeu() {
		return this.jpnJeu;
	}

	/**
	 * Setter panel jeu :
	 * @param jpnJeu
	 */
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();		
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
	}

	/**
	 * Getter texte du chat :
	 * @return
	 */
	public String getTxtChat() {
		//return txtChat;
		return this.txtChat.getText();
	}

	/**
	 * Setter texte du chat :
	 * @param txtChat
	 */
	public void setTxtChat(String txtChat) {
		this.txtChat.setText(txtChat);
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength()); 
	}
	

}