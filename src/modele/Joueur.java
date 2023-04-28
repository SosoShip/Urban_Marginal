package modele;

import controleur.Constante;

/**
 * Gestion des joueurs
 *
 */
public class Joueur extends Objet {

	/**
	 * vie de d�part pour tous les joueurs
	 */
	//private static final int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque
	 */
	//private static final int GAIN = 1 ; 
	/**
	 * perte de points de vie lors d'une attaque
	 */
	//private static final int PERTE = 2 ; 
	
	/**
	 * pseudo saisi
	 */
	//private String pseudo ;
	/**
	 * n� correspondant au personnage (avatar) pour le fichier correspondant
	 */
	//private int numPerso ; 
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
	 * Constructeur
	 */
	public Joueur() {
	}

	/**
	 * Initialisation d'un joueur (pseudo et num�ro, calcul de la 1�re position, affichage, cr�ation de la boule)
	 */
	public void initPerso(Object pseudoDuJoueur, int numDuPerso) {
		Constante.pseudo = pseudoDuJoueur.toString();
		 Constante.numPerso = numDuPerso;
		 // TODO pour le test
		 System.out.println("joueur "+Constante.pseudo+" - num perso "+Constante.numPerso+" créé");
	}

	/**
	 * Calcul de la premi�re position al�atoire du joueur (sans chevaucher un autre joueur ou un mur)
	 */
	private void premierePosition() {
	}
	
	/**
	 * Affiche le personnage et son message
	 */
	public void affiche() {
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
	private Boolean toucheJoueur() {
		return null;
	}

	/**
	* Contr�le si le joueur touche un des murs
	 * @return true si un joueur touche un mur
	 */
	private Boolean toucheMur() {
		return null;
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
