package controleur;

import vue.EntreeJeu;
import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import outils.connexion.AsyncResponse;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;

/**
 * Contr�leur et point d'entr�e de l'applicaton 
 * @author emds
 *
 */
public class Controle implements AsyncResponse {

	private EntreeJeu frmEntreeJeu ;

	/**
	 * M�thode de d�marrage
	 * @param args non utilis�
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * Choix création serveur ou rejoindre  
	 * @param info
	 */
	
	public void evenementEntréeJeu(String info) {	
		Jeu infoJeu = null;
		if( info.equals("serveur")) {
			(new Arene()).setVisible(true);
			infoJeu = new JeuServeur();
			ServeurSocket serveurSocket = new ServeurSocket(this, 6666);
		}
		else {
			(new ChoixJoueur()).setVisible(true);
			infoJeu = new JeuClient();
			ClientSocket clientSocket = new ClientSocket(this, info, 6666);
		}	
		this.frmEntreeJeu.dispose();
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		// TODO Auto-generated method stub
		switch (ordre) {
		case "connexion":
			
		}
		
	}

}
