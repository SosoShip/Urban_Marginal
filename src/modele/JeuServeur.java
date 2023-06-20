package modele;



import java.awt.event.KeyEvent;
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
	 * @return les joueurs du dictionnaire lesJoueurs :
	 */
	public Collection<Joueur> getLesJoueurs() {
		return lesJoueurs.values();
	}	
	
	/**
	 * Communication avec la classe arene
	 */
	private Arene arene;
	/**
	 * Retourne le frame de l'arene en cour:
	 * @return
	 */
	public Arene getArene() {
		return arene;
	}

	/**
	 * Constructeur
	 * @param controle
	 */
	public JeuServeur(Controle controle) {
		this.controleJeu = controle;
	}
	
	@Override
	public void connexion(Connection connexion) {
		
		if(connexion == null) {
			throw new IllegalArgumentException("Could not connect : connection null");
		}
		
		lesJoueurs.put(connexion, new Joueur(this));
	}

	@Override
	public void reception(Connection connexion, Object info) {
		String infoDuPerso[] = ((String)info).split(Constante.separation); 
		// infoDuPerso[0] = ordre
		switch (infoDuPerso[0]) {
		case Constante.infoDuPerso :
			// Envoi du num de connexion client afin d'afficher les murs coté client
			controleJeu.evenementJeuServeur(Constante.ordreAjoutPanelMurs, (Object)connexion);
			// récupération du numero de personnage (cast en int)
			int numPerso = 0;
			try {
				//infoDuPerso[2] = numPerso
				numPerso = Integer.parseInt(infoDuPerso[2]);
			}
			catch (Exception ex){
	            ex.printStackTrace();
	            return;
			}
			//Initialisation du joueur :
			// infoDuPerso[1] = pseudo
			lesJoueurs.get(connexion).initPerso(infoDuPerso[1],  numPerso, lesMurs, getLesJoueurs());
			controleJeu.evenementJeuServeur(Constante.ordreAjoutChat, (Object)"*** " + infoDuPerso[1] + " vient de se connecter ***");
			break;
			
			// Concatène le pseudo et sa phrase :
		case Constante.ordreLeChat :
			//verifier si la phrase est bien infoDuPerso[1]
			String chatSaisi = lesJoueurs.get(connexion).getPseudo() + " > " + infoDuPerso[1];			
			controleJeu.evenementJeuServeur(Constante.ordreAjoutChat, (Object)chatSaisi);
			break;
			
		case Constante.ordreAction :		
			int action = 0;
			try {
				//infoDuPerso[1] = deplacement ou tirs :
				action = Integer.parseInt(infoDuPerso[1]);
			}
			catch (Exception ex){
	            ex.printStackTrace();
	            return;
			}
			if (action == KeyEvent.VK_SPACE)			{
				lesJoueurs.get(connexion).actionTir(lesMurs, getLesJoueurs());
			}
			else {
				lesJoueurs.get(connexion).actionDeplacement((action), lesMurs, (getLesJoueurs()));
			}
			break;	
			
		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}
	}
	
	@Override
	public void deconnexion() {
	}

	/**
	 * G�n�ration des murs
	 */
	public void constructionMurs() {
		for (Integer i = 1; i <= 20; i++) {
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
	public void envoiJeuATous() {
		for (Connection connect : lesJoueurs.keySet()) {
			controleJeu.evenementJeuServeur(Constante.ordreAjoutTousLesLblJeu, (Object)connect);
		}
	}
	
	/**
	 * Envoi du chat mis à jour à chaque joueur :
	 */
	@Override
	public void envoi( Connection connexion, Object info) {	
		
		
		
		for (Connection laConnectDUnJoueur : Collections.list(lesJoueurs.keys())){						
			super.envoi(laConnectDUnJoueur, info);
			
			
			
		}
	}
	
}
	
	
	
	
	
	
