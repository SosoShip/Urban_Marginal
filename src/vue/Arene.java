package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Constante;
import controleur.Controle;
import modele.JeuClient;
import modele.Mur;
import outils.son.Son;

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
	 * Liste des sons :
	 */
	private ArrayList<Son> listAttackSon = new ArrayList<>();
	/**
	 * Communication avec la classe Controle
	 */
	private Controle controleJeu;

	/**
	 * Create the frame:
	 * @param controle
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
		Constante.getInstance().soundAmbiance.playContinue();
			
		if (this.controleJeu.isClient()) {
			// Ecoute du déplacement sur le JPanel contentPane:
			this.contentPane.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP  
						|| e.getKeyCode() == KeyEvent.VK_LEFT ||  e.getKeyCode() == KeyEvent.VK_RIGHT
						|| e.getKeyCode() == KeyEvent.VK_SPACE){ 
						controleJeu.evenementArene((Object)e.getKeyCode());
					}					
				}
			});			
			// Remplissage de la listes des sons d'attaque:
			listAttackSon.add(Constante.getInstance().soundfight);
			listAttackSon.add(Constante.getInstance().soundhurt);
			listAttackSon.add(Constante.getInstance().sounddeath);
		}
		
		
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
	
		// 	Création de la zone de saisie coté jeuClient :	
		if (this.controleJeu.isClient()) {			
			// Ecoute  des entrées sur le JPanel saisie du chat:
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
		// Ecoute du déplacement et des tirs sur le JPanel contentPane:
		this.txtChat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_KP_DOWN || e.getKeyCode() == KeyEvent.VK_KP_UP  
					|| e.getKeyCode() == KeyEvent.VK_KP_LEFT ||  e.getKeyCode() == KeyEvent.VK_KP_RIGHT
					|| e.getKeyCode() == KeyEvent.VK_SPACE){ 
					controleJeu.evenementArene((Object)e.getKeyCode());
				}					
			}
		});
		
		// Visuel de l'arene :
		JLabel lblFond = new JLabel("");		
		lblFond.setIcon(new ImageIcon(Constante.getInstance().iconArene));			
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
	 * Ajoute le texte saisi par un joueur dans tchat :
	 * @param textSaisi
	 * @return getTxtChat()
	 */
	public String ajoutChat(String textSaisi) {
		this.setTxtChat(getTxtChat() + "\r\n" + textSaisi + "\r\n");
		return getTxtChat();
	}	
	
	public void joueSon (Integer musicNumber){
		listAttackSon.get(musicNumber).play();
	}
	
	/**
	 * getter panel Mur :
	 * @return jpnMurs
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
	 * @return jpnJeu
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
		// ici ou avant l'ecoute des fleche?
		this.contentPane.requestFocus();
	}

	/**
	 * Getter texte du chat :
	 * @return txtChat.getText()
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