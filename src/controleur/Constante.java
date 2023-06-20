package controleur;

/**
 * Mémorisation des constantes
 * @author Marjorie
 *
 */
public class Constante {	
	/**
	 * N° du port de connexion du ServeurSocket:
	 */
	public static final int port = 6666;
	/**
	 * Chaine de caratere permetant de lancer un jeu coté serveur :
	 */
	public static final String jeuServeur = "jeuServeur";
	/**
	 * Separation des chaines de caractere visant à envoyer des informations :
	 */
	public static final String separation = "separation";
	/**
	 * Chaine de caratere permetant de visualiser l'ordre de récupérer les info d'un personnage :
	 */
	public static final String infoDuPerso = "InfoDuPerso";
	/**
	 * Chaine de caratere permetant de visualiser l'ordre d'une connection :
	 */
	public static final String laConnexion = "laConnexion";
	/**
	 * Chaine de caratere permetant de visualiser l'ordre d'une reception :
	 */
	public static final String lareception = "laReception";
	/**
	 * Chaine de caratere permetant de visualiser l'ordre d'une déconnection :
	 */
	public static final String laDeConnexion = "laDeConnexion";
	
	
	/**
	 * Chaine de caratere permetant de visualiser l'ordre d'ajout d'un mur :
	 */
	public static final String ordreAjoutMur = "AjoutMur";
	/**
	 * Chaine de caratere permetant de visualiser l'ordre d'ajout d'un panel de murs :
	 */
	public static final String ordreAjoutPanelMurs = "AjoutPanelMurs";
	/**
	 * Chaine de caratere permetant de visualiser l'ordre d'ajout d'un label joueur sur le jeu :
	 */
	public static final String ordreAjoutLblJeu = "AjoutLblJeuArene";
	/**
	 * Chaine de caratere permetant de visualiser l'ordre d'ajout de tout les label joueur sur le jeu :
	 */
	public static final String ordreAjoutTousLesLblJeu = "AjoutTousLesLblJeuArene";
	/**
	 * ordre de transmettre la phrase saisie par un joueur dans la zone de saisie:
	 */
	public static final String ordreLeChat = "leChat";
	/**
	 * ordre d'ajouter la phrase saisie par un joueur dans le chat :
	 */
	public static final String ordreAjoutChat = "ajoutLeChat";
	/**
	 * ordre d'ajouter la phrase saisie par un joueur dans le chat :
	 */
	public static final String modifChat = "modifLeChat";
	/**
	 * ordre de réaliser l'action d'un joueur :
	 */
	public static final String ordreAction = "action";
	
	
	/**
	 * vie de d�part pour tous les joueurs :
	 */
	public static final int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque :
	 */
	public static final int gain = 1 ; 
	/**
	 * perte de points de vie lors d'une attaque :
	 */
	public static final int perte = 2 ; 	
	/**
	 * largeur et hauteur des joueurs :
	 */
	public static final int hauteurJoueurs = 48;
	/**
	 * largeur et hauteur des joueurs :
	 */
	public static final int largeurJoueurs = 35;
	/**
	 * Hauteur du label joueur:
	 */
	public static final int hauteurDuMsgJoueur = 8;
	/**
	 * Hauteur du label joueur:
	 */
	public static final int largeurDuMsgJoueur = 45;
	/**
	 * Hauteur du label joueur:
	 */
	public static final int tailleBoule = 18;
	/**
	 * Hauteur du label joueur:
	 */
	public static int vitesseBoule = 10;
	/**
	 * icone du personnage marche :
	 */
	public static String marche = "marche";
	/**
	 * icone du personnage touché :
	 */
	public static String touche = "touche";
	/**
	 * icone du personnage mort :
	 */
	public static String mort = "mort";
	/**
	 * Nombre d'icone maximum du joueur en train de marcher :
	 */
	public static int etapeMax = 4;
	/**
	 * Taille du pas des joueurs :
	 */
	public static final int tailleDUnPas = 10;

	
	/**
	 * largeur et hauteur des murs :
	 */
	public static final int tailleDesMurs = 35;	
	/**
	 * Hauteur de l'arene :
	 */
	public static final int hauteurArene = 600- tailleDesMurs;
	/**
	 * Longueur de l'arene :
	 */
	public static final int longeurArene = 800- tailleDesMurs;
	
	

	
	//Mémorisation des chemins vers le fichier media

}
