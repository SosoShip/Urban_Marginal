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
	private ChoixJoueur choixJoueur;
	private Arene arene;
	private Jeu infoJeu;

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
		if( info.equals("serveur")) {
			(new Arene()).setVisible(true);
			infoJeu = new JeuServeur();
			ServeurSocket serveurSocket = new ServeurSocket(this, 6666);
			this.frmEntreeJeu.dispose();
		}
		else {
			infoJeu = new JeuClient();
			ClientSocket clientSocket = new ClientSocket(this, info, 6666);
		}	
	}

	@Override
	// recupere une reponse d'un ordi distant 
	public void reception(Connection connexion, String ordre, Object info) {
		// TODO Auto-generated method stub
		switch (ordre) {
		// ordre connexion > serveur ou client
		case "connexion":
			//connexion client> choix d'un jouer et creation d'une arene
			if(infoJeu instanceof JeuClient) {
				choixJoueur = new ChoixJoueur();
				choixJoueur.setVisible(true);
				//TOTO vraiment creer une nouvelle arene???
				arene = new Arene();
				arene.setVisible(false);
				this.frmEntreeJeu.dispose();
			}
		}
		
	}

}
