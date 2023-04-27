package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Gestion du jeu c�t� client
 *
 */
public class JeuClient extends Jeu {
	private Connection connexion;

	
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
