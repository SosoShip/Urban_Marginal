package controleur;

/**
 * Mémorisation des constantes
 * @author Marjorie
 *
 */
public class Constante {	
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
	 * vie de d�part pour tous les joueurs :
	 */
	public static final int MAXVIE = 10 ;
	/**
	 * gain de points de vie lors d'une attaque :
	 */
	public static final int GAIN = 1 ; 
	/**
	 * perte de points de vie lors d'une attaque :
	 */
	public static final int PERTE = 2 ; 	
	/**
	 * pseudo saisi :
	 */
	public static String pseudo = "pseudo";
	/**
	 * n� correspondant au personnage (avatar) pour le fichier correspondant
	 */
	public static int numPerso = 0; 
	/**
	 * largeur et hauteur des murs :
	 */
	public static final int tailleDesMurs = 50;
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
