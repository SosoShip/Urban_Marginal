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
	private Connection connexion;
	boolean mursOk = false;
	
	/**
	 * Constructeur :
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
		// Si c'est un JPanel :

		if (info instanceof JPanel) {
			if(!mursOk) {
				controleJeu.evenementJeuClient(Constante.ordreAjoutPanelMurs, info);
				mursOk = true;
			}
			else {
				controleJeu.evenementJeuClient(Constante.ordreAjoutTousLesLblJeu, info);
			}
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur
	 * fais appel une fois � l'envoi dans la classe Jeu
	 */
	public void envoi(String pseudo) {
		envoi( connexion, pseudo);
	}
	
	

}
