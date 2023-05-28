package modele;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.Constante;
import controleur.Controle;
import outils.connexion.Connection;
import vue.Arene;


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
	 * Communication avec la classe arene
	 */
	private Arene arene;
	public Arene getArene() {
		return arene;
	}

	/**
	 * Constructeur
	 */
	public JeuServeur(Controle controle) {
		this.controleJeu = controle;
	}
	
	@Override
	public void connexion(Connection connexion) {
		lesJoueurs.put(connexion, new Joueur(this));
	}

	@Override
	public void reception(Connection connexion, Object info) {
		String infoDuPerso[] = ((String)info).split(Constante.separation); 
		
		switch(infoDuPerso[0]) {
		case Constante.infoDuPerso:
			// Envoi du num de connexion client afin d'afficher les murs coté client
			controleJeu.evenementJeuServeur(Constante.ordreAjoutPanelMurs, (Object)connexion);
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
			lesJoueurs.get(connexion).initPerso(infoDuPerso[1],  numPerso, lesMurs, (Collection)lesJoueurs.values());
			break;
			// Concatène le pseudo et sa phrase :
		case Constante.ordreLeChat:
			//verifier si la phrase est bien infoDuPerso[1]
			String chatSaisi = lesJoueurs.get(connexion).getPseudo() + " > " + infoDuPerso[1];			
			controleJeu.evenementJeuServeur(Constante.ordreAjoutChat, (Object)chatSaisi);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients
	 * fais appel plusieurs fois � l'envoi de la classe Jeu
	public void envoi() {
	}

	/**
	 * G�n�ration des murs
	 */
	public void constructionMurs() {
		for(Integer i = 1; i <= 20; i++) {
			Mur unMur = new Mur();
			unMur.objectLengthX = Constante.tailleDesMurs;
			unMur.objectHeightY = Constante.tailleDesMurs;
			lesMurs.add(unMur);
			controleJeu.evenementJeuServeur(Constante.ordreAjoutMur, (Object)unMur.lblMur);
		}
	
	}
	
	/**
	 * Ajoute le label d'un personnage dans JPanelJeu
	 * @param labelPerso
	 */
	public void ajoutJLabelJeu(JLabel labelPerso) {
		controleJeu.evenementJeuServeur(Constante.ordreAjoutLblJeu, labelPerso);

	}
	
	/**
	 *  Envoi de l'ordre pour effectuer un changement de panel à tous les joueurs :
	 */
	public void EnvoiJeuATous() {
		for (Connection connect : lesJoueurs.keySet()) {
			controleJeu.evenementJeuServeur(Constante.ordreAjoutTousLesLblJeu, (Object)connect);
		}
	}
	
	/**
	 * Envoi du chat mis à jour à chaque joueur :
	 */
	@Override
	public void envoi( Connection connexion, Object info) {
		//ArrayList<Connection> connectJoueur = new ArrayList<>();
		ArrayList<Connection> connectJoueur = Collections.list(lesJoueurs.keys());
		
		for(Connection laConnectDUnJoueur : connectJoueur ){
			super.envoi(laConnectDUnJoueur, info);
		}
	}
	
	/**
	 * debut du cour > dit de la placer coté serveur, fin du cour dans arene 
	public String ajoutChat(String textSaisi) {
		String leTextDuChat = arene.getTxtChat() + "\r\n" + textSaisi + "\r\n";
		return leTextDuChat;
	}
	**/
}
	
	
	
	
	
	
