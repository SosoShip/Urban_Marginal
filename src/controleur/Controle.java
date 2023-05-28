package controleur;

import vue.EntreeJeu;

import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import modele.Joueur;
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
			
			arene = new Arene(this);
			((JeuServeur)leJeu).constructionMurs();
			arene.setVisible(true);			
			this.frmEntreeJeu.dispose();						
		}
		else {
			leJeu = new JeuClient(this);
			ClientSocket clientSocket = new ClientSocket(this, info, port);			
		}
		
	}
	
	/**
	 * Envoi les informations du personnage au jeuClient:
	 * @param txtPseudo
	 * @param numPersonnage
	 */
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
		case Constante.ordreAjoutLblJeu:
			arene.ajoutLblPersoArene(info);
			break;
		case Constante.ordreAjoutTousLesLblJeu:
			leJeu.envoi((Connection)info, arene.getJpnJeu());
			break;
		case Constante.ordreAjoutChat:
			String leChat = arene.ajoutChat((String)info);
			// Envois du chat mis à jour à la liste de joueurs :	
			((JeuServeur)leJeu).envoi(null, leChat);
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
		case Constante.ordreAjoutTousLesLblJeu:
			arene.setJpnJeu((JPanel)info);
			break;
		case Constante.modifChat:
			arene.setTxtChat((String)info);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + ordre);
		}
	}
	
	/**
	 * envoi le text saisi a jeu client
	 * @param textSaisi
	 */
	public void evenementArene(String textSaisi) {
		//if (leJeu instanceof JeuClient) {
			((JeuClient) leJeu).envoi(Constante.ordreLeChat + Constante.separation + textSaisi);
		}
	//}

	@Override
	// recupere une reponse d'un ordi distant 
	public void reception(Connection connexion, String ordre, Object info) {
		switch (ordre) {
		// ordre connexion > serveur ou client
		case Constante.laConnexion:
			//connexion client> choix d'un joueur et creation d'une arene
			if(leJeu instanceof JeuClient) {
				choixJoueur = new ChoixJoueur(this);
				choixJoueur.setVisible(true);
				//TOTO vraiment creer une nouvelle arene???
				arene = new Arene(this);
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

	/**
	 * Envoi une connection et un objet à la clesse connection :
	 * @param connexion
	 * @param info
	 */
	public void envoi (Connection connexion, Object info) {
		connexion.envoi(info);
	}	

}
