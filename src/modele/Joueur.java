package modele;

import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
	 * Constructeur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		vie = Constante.MAXVIE;
		etape = 1;
		orientation = 1;
	}

	/**
	 * Initialisation d'un joueur (pseudo et num�ro, calcul de la 1�re position, affichage, cr�ation de la boule)
	 */
	public void initPerso(Object pseudoDuJoueur, int numDuPerso, ArrayList<Mur> lesMurs, Collection<Joueur> lesJoueurs) {
		//Caractéristiques du joueur :
		Constante.pseudo = pseudoDuJoueur.toString();
		Constante.numPerso = numDuPerso;
		this.objectLengthX = Constante.tailleDesJoueurs;
		this.objectHeightY = Constante.tailleDesJoueurs + Constante.hauteurDuMsgJoueur;	
		
		// Création du label Joueur :
		lblJoueur = new JLabel("");
			
		//ajout du Label message au label joueur:
		lblMessage = new JLabel("");
		lblMessage.setBounds(Constante.tailleDesJoueurs - 40, Constante.tailleDesJoueurs, Constante.largeurDuMsgJoueur, Constante.hauteurDuMsgJoueur);
		
		//Placement de ddepart du joueur :
		premierePosition(lesMurs, lesJoueurs);
		
		lblJoueur.setBounds(this.posX, this.posY, Constante.tailleDesJoueurs, Constante.tailleDesJoueurs + Constante.hauteurDuMsgJoueur);
		Font fontMessage = new Font("Dialog",Font.BOLD, 8);
		lblMessage.setFont(fontMessage);
		lblJoueur.add(lblMessage);
		lblJoueur.repaint();
		
		jeuServeur.ajoutJLabelJeu(lblJoueur);	
		affiche(etape, orientation);
	
	}

	/**
	 * Calcul de la premi�re position al�atoire du joueur (sans chevaucher un autre joueur ou un mur)
	 */
	private void premierePosition(ArrayList<Mur> lesMurs, Collection<Joueur> lesJoueurs) {
		Boolean isExit = true;
		while(isExit) {
			// Nombres aléatoires pour placer les murs sur X et y :
			int minX = 0 + Constante.tailleDesJoueurs;
			int maxX = Constante.longeurArene - Constante.tailleDesJoueurs;
			this.posX = Common.randXY(minX, maxX);

			int minY = 0 + Constante.tailleDesJoueurs;
			int maxY = Constante.hauteurArene - Constante.tailleDesJoueurs;
			this.posY = Common.randXY(minY, maxY);
			//this.posX = Common.randXY(0, Constante.longeurArene);
			//this.posY = Common.randXY(0, Constante.hauteurArene);	
			
			isExit = this.toucheMur(lesMurs);
			isExit = this.toucheJoueur(lesJoueurs);
			
			if(!isExit) {
				break;
			}
		}
	}
	
	/**
	 * Affiche le personnage et son message
	 */
	public void affiche(int etape, int orientation) {
		// image du joueur
		String chemin = "personnages\\perso"+Constante.numPerso+"marche"+etape+"d"+orientation+".gif";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblJoueur.setIcon(new ImageIcon(resource));
		// Message du joueur
		lblMessage.setText(Constante.pseudo+" : "+ vie );
	}

	/**
	 * G�re une action re�ue et qu'il faut afficher (d�placement, tire de boule...)
	 */
	public void action() {
	}

	/**
	 * G�re le d�placement du personnage
	 */
	private void deplace() { 
	}

	/**
	 * Contr�le si le joueur touche un des autres joueurs
	 * @return true si deux joueurs se touchent
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
		if(istouch) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	* Contr�le si le joueur touche un des murs
	 * @return true si un joueur touche un mur
	 */
	//TODO verifier qu'il rentre bien if(istouch)
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
	
}
