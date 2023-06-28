package controleur;

import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import outils.son.Son;


/**
 * Mémorisation des constantes
 * @author Marjorie
 *
 */
public class Constante {
	 private static Constante constante;
	/**
	 * Constructeur
	 */
	private Constante() {
		//Son
		soundfight = new Son(resourcefight);
		soundhurt = new Son(resourcehurt);
		sounddeath = new Son(resourcedeath);
		soundWelcome = new Son(welcome);
		soundPrevious = new Son(previous);
		soundNext = new Son(next);
		soundGo = new Son(Go);
		soundAmbiance = new Son(ambiance);
		
		//Image
		iconBoule = getClass().getClassLoader().getResource(chemin);
		iconArene = getClass().getClassLoader().getResource(chemin2);
		iconChoixJoueur = getClass().getClassLoader().getResource(chemin3);
		iconMur = getClass().getClassLoader().getResource(chemin4);
	}
	
	/**
	 * retourne une constante non statique :
	 */
	 public static Constante getInstance() {
	        if (constante == null) {
	        	constante = new Constante();
	        }
	        return constante;	 
	    }
			
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
	 * ordre de jouer une mélodie spécifique à une action :
	 */
	public static final String ordreJouerSon = "jouerSon";
	
	
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
	// Sons :	 
	public String cheminfight = "sons\\fight.wav";
	public URL resourcefight = getClass().getClassLoader().getResource(cheminfight);
	public final Son soundfight;
	
	public String cheminhurt = "sons\\hurt.wav";
	public URL resourcehurt = getClass().getClassLoader().getResource(cheminhurt);
	public final Son soundhurt;
	
	public String chemindeath = "sons\\death.wav";
	public URL resourcedeath = getClass().getClassLoader().getResource(chemindeath);
	public final Son sounddeath;
	
	public String cheminWelcomme = "sons\\welcome.wav";
	public URL welcome = getClass().getClassLoader().getResource(cheminWelcomme);
	public final Son soundWelcome;
	
	public String cheminPrevious = "sons\\precedent.wav";
	public URL previous = getClass().getClassLoader().getResource(cheminPrevious);
	public final Son soundPrevious;
	
	public String cheminNext = "sons\\suivant.wav";
	public URL next = getClass().getClassLoader().getResource(cheminNext);
	public final Son soundNext;
	
	public String cheminGo = "sons\\go.wav";
	public URL Go = getClass().getClassLoader().getResource(cheminGo);
	public final Son soundGo;
	
	public String cheminAmbiance = "sons\\ambiance.wav";
	public URL ambiance = getClass().getClassLoader().getResource(cheminAmbiance);
	public final Son soundAmbiance;
	
	// Images :
	public String chemin = "boules\\boule.gif";
	public URL iconBoule;
	
	public String chemin2 = "fonds\\fondarene.jpg";
	public URL iconArene;
	
	public String chemin3 = "fonds\\fondchoix.jpg";
	public URL iconChoixJoueur;
	
	public String chemin4 = "murs\\mur.gif";
	public URL iconMur;
}
