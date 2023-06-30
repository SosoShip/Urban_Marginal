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
 * Gestion des joueurs :
 *
 */
public class Joueur extends Objet {
	/**
	 * instance de JeuServeur pour communiquer avec lui :
	 */
	private JeuServeur jeuServeur ;
	/**
	 * num�ro d'�tape dans l'animation (de la marche, touch� ou mort) :
	 */
	private int etape ;
	/**
	 * tourn� vers la gauche (0) ou vers la droite (1) :
	 */
	private int orientation ;
	/**
	 * la boule du joueur :
	 */
	private Boule boule ;
	/**
	 * Nom du joueur :
	 */
	private String pseudo ;
	/**
	 * numero correspondant au personnage (avatar) pour le fichier correspondant :
	 */
	public int numPerso = 0; 
	/**
	 * vie restante du joueur
	 */
	private int vie ; 
	/**
	 * message du joueur :
	 */
	private JLabel lblMessage;
	/**
	 * Un joueur touche un mur :
	 */
	Boolean isTouchMur = true;
	/**
	 * Un joueur touche un autre joueur :
	 */
	Boolean isTouchJoueur = true;
	
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
		this.objectLengthX = Constante.largeurJoueurs;
		this.objectHeightY = Constante.hauteurJoueurs + Constante.hauteurDuMsgJoueur;	
		
		// Création du label Joueur :
		this.lblJoueur = new JLabel("");
			
		//ajout du Label message au label joueur:
		lblMessage = new JLabel("");
		lblMessage.setBounds(this.getPosX() - 10, this.getPosY() + Constante.hauteurJoueurs , Constante.largeurDuMsgJoueur, Constante.hauteurDuMsgJoueur);
		
		// Création de la boule du joueur :
		boule = new Boule(this.jeuServeur);

		//Placement de depart du joueur et de son message :
		premierePosition(lesMurs, lesJoueurs);		
		this.lblJoueur.setBounds(this.getPosX(), this.getPosY(), Constante.largeurJoueurs, Constante.hauteurJoueurs + Constante.hauteurDuMsgJoueur);
		
		Font fontMessage = new Font("Dialog",Font.BOLD, 8);
		lblMessage.setFont(fontMessage);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblJoueur.add(lblMessage);
		this.lblJoueur.repaint();
		
		this.jeuServeur.ajoutJLabelJeu(this.lblJoueur);	
		//this.jeuServeur.ajoutJLabelJeu(lblMessage);	
		this.jeuServeur.ajoutJLabelJeu(boule.lblBoule);	
		affiche(Constante.marche, etape, getOrientation());	
	}

	/**
	 * Calcul de la premi�re position al�atoire du joueur (sans chevaucher un autre joueur ou un mur)
	 * @param ArrayList<>
	 * @param Collection<>
	 */
	private void premierePosition(ArrayList<Mur> lesMurs, Collection<Joueur> lesJoueurs) {
		
		while (isTouchMur || isTouchJoueur) {
			// Nombres aléatoires pour placer les joueurs sur X et y :
			int minX = 0 + Constante.largeurJoueurs;
			int maxX = Constante.longeurArene - Constante.largeurJoueurs;
			this.setPosX(Common.randXY(minX, maxX));

			int minY = 0 + Constante.hauteurJoueurs;
			int maxY = Constante.hauteurArene - Constante.hauteurJoueurs;
			this.setPosY(Common.randXY(minY, maxY));	
			
			isTouchMur = super.toucheMur(lesMurs);
			isTouchJoueur = this.toucheJoueur(lesJoueurs);
			
			if (!(isTouchMur || isTouchJoueur)) {
				break;
			}
		}
	}
	
	/**
	 * Affiche le personnage et son message
	 * @param int
	 * @param int
	 */
	public void affiche(String position, int etape, int orientation) {
		// image du joueur
		String chemin = "personnages\\perso" + numPerso + position + etape + "d" + orientation + ".gif";
		URL resource = getClass().getClassLoader().getResource(chemin);
		this.lblJoueur.setIcon(new ImageIcon(resource));
		// Message du joueur
		this.lblMessage.setText(this.pseudo +" : "+ this.vie );
		// ordre d'envoi du pannel joueur a tous les joueurs :
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * G�re une action de déplacement :
	 * @param mouv : Integer représentant l'action du joueur :
	 * @param  : ArrayList<Mur> : Liste de Murs
	 * @param lesJoueurs : ArrayList<Connection> : liste des joueurs :
	 */
	public void actionDeplacement(Integer mouv, ArrayList<Mur> lesMurs, Collection<Joueur> lesJoueurs) {
		if(! estMort()) {
			switch (mouv) {
			// Down :
			case KeyEvent.VK_DOWN :
				this.setPosY(this.getPosY() + Constante.tailleDUnPas);
				
				if ((this.sortDeLArene(this.getPosX(),this.getPosY())) 
						|| (super.toucheMur(lesMurs))
						|| (this.toucheJoueur(lesJoueurs))) {
					this.setPosY(this.getPosY() - Constante.tailleDUnPas);
				}				
				break;
				
			// Up :
			case KeyEvent.VK_UP :
				this.setPosY(this.getPosY() - Constante.tailleDUnPas);
				if ((this.sortDeLArene(this.getPosX(),this.getPosY()))
					|| (super.toucheMur(lesMurs))
					|| (this.toucheJoueur(lesJoueurs))) {
					this.setPosY(this.getPosY() + Constante.tailleDUnPas);
				}				
				break;
				
			// Left :
			case KeyEvent.VK_LEFT :
				orientation = 0;
				this.setPosX(this.getPosX() - Constante.tailleDUnPas);
				if ((this.sortDeLArene(this.getPosX(),this.getPosY()))
						|| (super.toucheMur(lesMurs))
						|| (this.toucheJoueur(lesJoueurs))) {
					this.setPosX(this.getPosX() + Constante.tailleDUnPas);
				}				
				break;
				
			// Right :
			case KeyEvent.VK_RIGHT :
				orientation = 1;
				this.setPosX(this.getPosX() + Constante.tailleDUnPas);
				if ((this.sortDeLArene(this.getPosX(),this.getPosY()))
						|| (super.toucheMur(lesMurs))
						|| (this.toucheJoueur(lesJoueurs))) {
					this.setPosX(this.getPosX() - Constante.tailleDUnPas);
				}				
				break;		
				
			default:
				throw new IllegalArgumentException("Unexpected value: ");			
			}
			
			//Affichage du joueur :
			this.courseDuJoueur(this.getPosX(), this.getPosY());
			this.affiche(Constante.marche, etape, getOrientation());
		}	
	}
	
	/**
	 * Gere une action de tir : 
	 * @param  : ArrayList<Mur> : Liste de Murs
	 * @param lesJoueurs : ArrayList<Connection> : liste des joueurs :
	 */
	public void actionTir(ArrayList<Mur> lesMurs, Collection<Joueur> lesJoueurs) {
		if(! estMort()) {
			// Verification qu'une boule n'est pas déja tirée :
			if (!boule.lblBoule.isVisible()) {
				boule.tirBoule(this, lesMurs);
				this.etape = 1;
				this.affiche(Constante.marche, etape, getOrientation());
			}
		}						
	}
		
	/**
	 * Vérifie que la nouvelle position du joueur est dans les limites de l'arene :
	 * @param posX
	 * @param posY
	 * @return sortDeLArene : boolean confirmant la sortie d'arene :
	 */
	public boolean sortDeLArene (int posX, int posY) {
		boolean sortDeLArene = (this.getPosX() <= 20 ) || (this.getPosX() >= Constante.longeurArene - 20) 
				|| (this.getPosY() <= 20) || (this.getPosY() >= Constante.hauteurArene - 20);
		return sortDeLArene;
	}

	/**
	 * Gere les icones de la marche des joueurs
	 * @param posX
	 * @param posY
	 */
	private void courseDuJoueur(int posX, int posY) { 
		// Gestion des icones pour une marche dynamique :
		if (this.etape < Constante.etapeMax) {
			this.etape ++;
		}
		else {
			this.etape = 2;
		}
		
		// Position du joueur dans l'arene :
		lblJoueur.setBounds(this.getPosX(), this.getPosY(), Constante.largeurJoueurs, Constante.hauteurJoueurs + Constante.hauteurDuMsgJoueur);		
	}

	/**
	 * Contr�le si le joueur touche un des autres joueurs
	 * @param Collection<>
	 * @return Boolean
	 */
	private Boolean toucheJoueur(Collection<Joueur> lesJoueurs) {
 		for (Joueur unJoueur : lesJoueurs) {
 			if(this.equals(unJoueur)) {
 				isTouchJoueur = false;
 				continue;
 			}	
		    isTouchJoueur = super.toucheObjet(unJoueur);
			if (isTouchJoueur) {
				return isTouchJoueur;
			}
		}
		return isTouchJoueur;
	}

		
	/**
	 * Gain de points de vie apr�s avoir touch� un joueur
	 */
	public void gainVie(Joueur lejoueur) {
		lejoueur.vie = lejoueur.vie + Constante.gain;
	}
	
	/**
	 * Perte de points de vie apr�s avoir �t� touch� 
	 */
	public void perteVie() {
		if (this.vie > 1) {
			this.vie = this.vie - Constante.perte;	
		}
		if (this.vie == 1) {
			this.vie = 0;
		}		
	}
	
	/**
	 * vrai si la vie est � 0
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		if (this.vie == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Le joueur se d�connecte et disparait
	 */
	public void departJoueur() {
		if(this.lblJoueur != null) {
			this.lblJoueur.setVisible(false);
		}
		if(this.lblMessage != null) {
			this.lblMessage.setVisible(false);
		}
		if (this.lblBoule != null) {
			this.lblBoule.setVisible(false);
		}
		jeuServeur.envoiJeuATous();
	}

	/**
	 * Retourne le pseudo du joueur :
	 * @return String:
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Retourne l'orientation du joueur :
	 * @return int
	 */
	public int getOrientation() {
		return orientation;
	}
	
	public int getEtape() {
		return etape;
	}
	
}
