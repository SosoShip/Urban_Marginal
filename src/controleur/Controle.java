package controleur;

import vue.EntreeJeu;

import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.JTextField;

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
	private Jeu leJeu;
	private int port = 6666;
	
	public Arene getArene() {
		return arene;
	}

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
	public Controle() {
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * Choix création serveur ou rejoindre  
	 * @param info
	 */
	
	public void evenementEntréeJeu(String info) {			
		if( info.equals(Constante.jeuServeur)) {				
			leJeu = new JeuServeur(this);
			ServeurSocket serveurSocket = new ServeurSocket(this, port);
			
			arene = new Arene();
			((JeuServeur)leJeu).constructionMurs();
			arene.setVisible(true);			
			this.frmEntreeJeu.dispose();						
		}
		else {
			leJeu = new JeuClient(this);
			ClientSocket clientSocket = new ClientSocket(this, info, port);			
		}
		
	}
	
	public void evenementChoixJoueur(String txtPseudo, int numPersonnage) {		
		System.out.println(txtPseudo);
		((JeuClient)leJeu).envoi(Constante.infoDuPerso+Constante.separation+txtPseudo+Constante.separation+numPersonnage);	
	}
	
	/**
	 * Agencement de l'arene coté serveur :
	 * @param ordre
	 * @param info
	 */
	public void evenementJeuServeur(String ordre, Object info) {
		switch (ordre) {
		case Constante.ordreAjoutMur: 			
			arene.ajoutMurs(info);
			break;
		case Constante.ordreAjoutPanelMurs :
			leJeu.envoi((Connection)info, arene.getJpnMurs());
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + ordre);
		}
	}
	
	/**
	 * Agencement de l'arene coté client:
	 * @param ordre
	 * @param info
	 */
	public void evenementJeuClient(String ordre, Object info) {
		switch (ordre) {
		case Constante.ordreAjoutPanelMurs: 			
			arene.setJpnMurs((JPanel)info);
			break;
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + ordre);
		}
	}

	@Override
	// recupere une reponse d'un ordi distant 
	// TODO info null
	public void reception(Connection connexion, String ordre, Object info) {
		switch (ordre) {
		// ordre connexion > serveur ou client
		case Constante.laConnexion:
			//connexion client> choix d'un joueur et creation d'une arene
			if(leJeu instanceof JeuClient) {
				choixJoueur = new ChoixJoueur(this);
				choixJoueur.setVisible(true);
				//TOTO vraiment creer une nouvelle arene???
				arene = new Arene();
				getArene().setVisible(false);
				this.frmEntreeJeu.dispose();
			}
			// Connection avec serveur <> client  :
			leJeu.connexion(connexion); 
			break;
			
		case Constante.lareception:	
			leJeu.reception(connexion, info);
			break;
		}
		
	}

	public void envoi (Connection connexion, Object pseudo) {
		connexion.envoi(pseudo);
	}	

}
