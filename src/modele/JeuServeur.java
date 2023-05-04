package modele;



import java.util.ArrayList;
import java.util.Hashtable;
import controleur.Constante;
import controleur.Controle;
import outils.connexion.Connection;


/**
 * Gestion du jeu c�t� serveur
 *
 */
public class JeuServeur extends Jeu {

	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	
	/**
	 * Collection de joueurs
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	

	/**
	 * Constructeur
	 */
	public JeuServeur(Controle controle) {
		this.controleJeu = controle;
	}
	
	@Override
	public void connexion(Connection connexion) {
		lesJoueurs.put(connexion, new Joueur());
	}

	@Override
	public void reception(Connection connexion, Object info) {
		String infoDuPerso[] = ((String)info).split(Constante.separation); 
		
		switch(infoDuPerso[0]) {
		case Constante.infoDuPerso:
			// récupération du numero de personnage (cast en int)
			int numPerso = 0;
			try {
				numPerso = Integer.parseInt(infoDuPerso[2]);
			}
			catch (Exception ex){
	            ex.printStackTrace();
	            return;
			}
			//Initialisation du joueur :
			lesJoueurs.get(connexion).initPerso(infoDuPerso[1],  numPerso);
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients
	 * fais appel plusieurs fois � l'envoi de la classe Jeu
	 */
	public void envoi() {
	}

	/**
	 * G�n�ration des murs
	 */
	public void constructionMurs() {
		for(Integer i = 1; i <= 20; i++) {
			Mur unMur = new Mur();
			lesMurs.add(unMur);
			controleJeu.evenementJeuServeur(Constante.ordreAjoutMur, (Object)unMur.lblMur);
		}
	
	}
}
	
	
	
	
	
	
