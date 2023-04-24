package modele;

import controleur.Controle;
import outils.connexion.Connection;

/**
 * Informations et m�thodes communes aux jeux client et serveur
 *
 */
public abstract class Jeu {
	
	/**
	 * Communication avec la classe controle :
	 */
	protected Controle controleJeu;

	/**
	 * R�ception d'une connexion (pour communiquer avec un ordinateur distant)
	 */
	public abstract void connexion(Connection connexion) ;
	
	/**
	 * R�ception d'une information provenant de l'ordinateur distant
	 */
	public abstract void reception(Connection connexion, Object object) ;
	
	/**
	 * D�connexion de l'ordinateur distant
	 */
	public abstract void deconnexion() ;
	
	/**
	 * Envoi d'une information vers un ordinateur distant
	 */
	public void envoi( Connection connexion, Object pseudo) {
		controleJeu.envoi(connexion, pseudo);
	}
	
	
	
}
