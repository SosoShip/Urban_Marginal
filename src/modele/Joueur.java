package modele;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Common;
import controleur.Constante;
import outils.connexion.Connection;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet {
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur ;
	/**
	 * num�ro d'�tape dans l'animation (de la marche, touch� ou mort)
	 */
	private int etape ;
	/**
	 * la boule du joueur
	 */
	private Boule boule ;
	/**
	 * Nom du joueur :
	 */
	private String pseudo ;
	/**
	 * numero correspondant au personnage (avatar) pour le fichier correspondant
	 */
	// pourquoi ChoixJoueur demande qu'il soit en static??
	public int numPerso = 0; 
	/**
	 * vie restante du joueur
	 */
	private int vie ; 
	/**
	 * tourn� vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation ;
	/**
	 * message du joueur :
	 */
	private static JLabel lblMessage;
	
	/**
	 * Retourne le frame de l'arene en cour:
	 * @param jeuServeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		vie = Constante.MAXVIE;
		etape = 1;
		orientation = 1;
	}

	/**
	 * Initialisation d'un joueur (pseudo et num�ro, calcul de la 1�re position, affichage, cr�ation de la boule)
	 * @param pseudoDuJoueur : Object du pseudo du joueur
	 * @param numDuPerso : Object du pseudo du joueur :
	 * @param lesMurs  : ArrayList<> des murs du jeu en cours :
	 * @param lesJoueurs : Collection<> des joueurs du jeu en cours :
	 */
	public void initPerso(Object pseudoDuJoueur, int numDuPerso, ArrayList<Mur> lesMurs, Collection<Joueur> lesJoueurs) {
		//Caractéristiques du joueur :
		pseudo = pseudoDuJoueur.toString();
		numPerso = numDuPerso;
		this.objectLengthX = Constante.tailleDesJoueurs;
		this.objectHeightY = Constante.tailleDesJoueurs + Constante.hauteurDuMsgJoueur;	
		
		// Création du label Joueur :
		lblJoueur = new JLabel("");
			
		//ajout du Label message au label joueur:
		lblMessage = new JLabel("");
		lblMessage.setBounds(Constante.tailleDesJoueurs - Constante.largeurDuMsgJoueur, Constante.tailleDesJoueurs, Constante.largeurDuMsgJoueur, Constante.hauteurDuMsgJoueur);
		
		//Placement de depart du joueur et de son message :
		premierePosition(lesMurs, lesJoueurs);		
		lblJoueur.setBounds(this.posX, this.posY, Constante.tailleDesJoueurs, Constante.tailleDesJoueurs + Constante.hauteurDuMsgJoueur);
		
		Font fontMessage = new Font("Dialog",Font.BOLD, 8);
		lblMessage.setFont(fontMessage);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblJoueur.add(lblMessage);
		lblJoueur.repaint();
		
		jeuServeur.ajoutJLabelJeu(lblJoueur);	
		affiche(etape, orientation);	
	}

	/**
	 * Calcul de la premi�re position al�atoire du joueur (sans chevaucher un autre joueur ou un mur)
	 * @param ArrayList<>
	 * @param Collection<>
	 */
	private void premierePosition(ArrayList<Mur> lesMurs, Collection<Joueur> lesJoueurs) {
		Boolean isTouchMur = true;
		Boolean isTouchJoueur = true;
		while (isTouchMur || isTouchJoueur) {
			// Nombres aléatoires pour placer les murs sur X et y :
			int minX = 0 + Constante.tailleDesJoueurs;
			int maxX = Constante.longeurArene - Constante.tailleDesJoueurs;
			this.posX = Common.randXY(minX, maxX);

			int minY = 0 + Constante.tailleDesJoueurs;
			int maxY = Constante.hauteurArene - Constante.tailleDesJoueurs;
			this.posY = Common.randXY(minY, maxY);	
			
			isTouchMur = this.toucheMur(lesMurs);
			isTouchJoueur = this.toucheJoueur(lesJoueurs);
			
			if (!(isTouchMur && isTouchJoueur)) {
				break;
			}
		}
	}
	
	/**
	 * Affiche le personnage et son message
	 * @param int
	 * @param int
	 */
	public void affiche(int etape, int orientation) {
		// image du joueur
		String chemin = "personnages\\perso"+numPerso+"marche"+etape+"d"+orientation+".gif";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblJoueur.setIcon(new ImageIcon(resource));
		// Message du joueur
		lblMessage.setText(pseudo +" : "+ vie );
		// ordre d'envoi du pannel joueur a tous les joueurs :
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * G�re une action re�ue et qu'il faut afficher (d�placement, tire de boule...)
	 * @param mouv : Integer représentant l'action du joueur :
	 * @param lesJoueurs : ArrayList<Connection> : liste des joueurs :
	 */
	public void action(Integer mouv, ArrayList<Connection>lesJoueurs) {
		switch (mouv) {
		// Down :
		case KeyEvent.VK_DOWN :
			lblJoueur.setBounds(this.posX, this.posY + 10, Constante.tailleDesJoueurs, Constante.tailleDesJoueurs + Constante.hauteurDuMsgJoueur);
			etape = 2;
			orientation = 1;
			affiche(etape, orientation);	
			break;
		// Up :
		case KeyEvent.VK_UP :
			lblJoueur.setBounds(this.posX, this.posY - 10, Constante.tailleDesJoueurs, Constante.tailleDesJoueurs + Constante.hauteurDuMsgJoueur);
			etape = 2;
			orientation = 1;
			affiche(etape, orientation);	
			break;
		// Left :
		case KeyEvent.VK_LEFT :
			lblJoueur.setBounds(this.posX - 10, this.posY, Constante.tailleDesJoueurs, Constante.tailleDesJoueurs + Constante.hauteurDuMsgJoueur);
			etape = 2;
			orientation = 0;
			affiche(etape, orientation);	
			break;
		// Right :
		case KeyEvent.VK_RIGHT :
			lblJoueur.setBounds(this.posX + 10, this.posY, Constante.tailleDesJoueurs, Constante.tailleDesJoueurs + Constante.hauteurDuMsgJoueur);
			etape = 2;
			orientation = 1;
			affiche(etape, orientation);	
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}
	}

	/**
	 * G�re le d�placement du personnage
	 */
	private void deplace() { 
	}

	/**
	 * Contr�le si le joueur touche un des autres joueurs
	 * @param Collection<>
	 * @return Boolean
	 */
	private Boolean toucheJoueur(Collection<Joueur> lesJoueurs) {
		boolean istouch = false;
		for (Joueur unJoueur : lesJoueurs) {
			istouch = this.toucheObjet(unJoueur);
			if(istouch) {
				istouch = true;
				if(this.equals(unJoueur)) {
					istouch = false;
				}
			}
		}
		if (istouch) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Contr�le si le joueur touche un des murs
	 * @param ArrayList<>
	 * @return Boolean
	 */
	private Boolean toucheMur(ArrayList<Mur> lesMurs) {
		for (Mur unMur : lesMurs) {			
			boolean istouch = this.toucheObjet(unMur);
			if(istouch) {
				return true;
			}		
		}
		return false;
	}
	
	/**
	 * Gain de points de vie apr�s avoir touch� un joueur
	 */
	public void gainVie() {
	}
	
	/**
	 * Perte de points de vie apr�s avoir �t� touch� 
	 */
	public void perteVie() {
	}
	
	/**
	 * vrai si la vie est � 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return null;
	}
	
	/**
	 * Le joueur se d�connecte et disparait
	 */
	public void departJoueur() {
	}

	/**
	 * Retourne le pseudo du joueur :
	 * @return String:
	 */
	public String getPseudo() {
		return pseudo;
	}
	
}
