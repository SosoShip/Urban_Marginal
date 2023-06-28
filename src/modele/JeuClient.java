package modele;

import controleur.Constante;
import controleur.Controle;
import outils.connexion.Connection;
import javax.swing.JPanel;

/**
 * Gestion du jeu c�t� client
 *
 */
public class JeuClient extends Jeu {
	/**
	 * instance de la connexion du jeu en cour:
	 */
	private Connection connexion;
	/**
	 * Retourne vrai si le panel de mur à déja été affiché:
	 */
	boolean mursOk = false;
	
	/**
	 * Constructeur :
	 * @param controle
	 */
	public JeuClient(Controle controle) {
		this.controleJeu = controle;
	}
	
	@Override
	public void connexion(Connection connexion) {
		this.connexion = connexion;
	}

	@Override
	public void reception(Connection connexion, Object info) {
		// Test pour connaitre la nature de info :

		if (info instanceof JPanel) {
			if(!mursOk) {
				controleJeu.evenementJeuClient(Constante.ordreAjoutPanelMurs, info);
				mursOk = true;
			}
			else {
				controleJeu.evenementJeuClient(Constante.ordreAjoutTousLesLblJeu, info);
			}
		}

		if (info instanceof String) {
			controleJeu.evenementJeuClient(Constante.modifChat, info);
		}
		
		if (info instanceof Integer) {
			controleJeu.evenementJeuClient(Constante.ordreJouerSon, info);
		}
		
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois � l'envoi dans la classe Jeu
	 * @param info
	 */
	public void envoi(String info) {
		super.envoi(connexion, (Object)info);
	}
	
	

}
