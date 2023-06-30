package modele;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.management.loading.PrivateClassLoader;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controleur.Constante;


/**
 * Gestion de la boule
 *
 */
public class Boule extends Objet implements Runnable{

	/**
	 * instance de JeuServeur pour la communication
	 */
	private JeuServeur jeuServeur ;
	/**
	 * donne la direction de la boule :
	 */
	Integer directionBoule = 0;
	/**
	 * Collection des murs :
	 */
	private ArrayList<Mur> lesMurs;
	/**
	 * joueur qui utilise une boule :
	 */
	private Joueur leJoueur;
	
	/**
	 * Constructeur
	 * @param jeuServeur
	 */
	public Boule(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.lblBoule = new JLabel();
		this.lblBoule.setVisible(false);
		this.lblBoule.setBounds(0, 0, Constante.tailleBoule, Constante.tailleBoule);		
		this.lblBoule.setIcon(new ImageIcon(Constante.getInstance().iconBoule));
	}
	
	/**
	 * tir d'une boule :
	 * @param leJoueur : Joueur : joueur qui tire la boule
	 * @param lesMurs : liste de murs
	 */
	public void tirBoule(Joueur leJoueur, ArrayList<Mur> lesMurs) {
		this.leJoueur = leJoueur;
		this.lesMurs = lesMurs;
		// Joueur orienté a droite :
		if (leJoueur.getOrientation() == 1) {
			directionBoule = Constante.vitesseBoule;
			this.setPosX(leJoueur.getPosX() + Constante.largeurJoueurs + 1);
			this.setPosY(leJoueur.getPosY() + 15);
		}
		// Joueur orienté a gauche :
		else {
			directionBoule = - Constante.vitesseBoule;
			this.setPosX(leJoueur.getPosX() - 10);
			this.setPosY(leJoueur.getPosY() + 15);
		}
		new Thread(this).start();	
	}

	@Override
	public void run() {
		// Envoi musique d'une boule lancée :
		this.jeuServeur.envoi(null, 0);	
		// retourne le joueur touché par un boule :
		Joueur victime = null;
		// la boule touche un mur :
		Boolean isTouchMur = false;
		
		
		// Affichage de la boule :
		this.lblBoule.setBounds(this.getPosX(), this.getPosY(), Constante.tailleBoule, Constante.tailleBoule);
		this.lblBoule.setVisible(true);
		
		// Gestion du déplacement et des collisions :
		while (victime == null && !isTouchMur && this.posX >= 0 + Constante.tailleDesMurs && this.posX <= Constante.longeurArene) {
			// Déplacement et affichage de la boule :
  			this.setPosX(this.posX + directionBoule);
			this.lblBoule.setBounds(this.getPosX(), this.getPosY(), Constante.tailleBoule, Constante.tailleBoule);
			this.jeuServeur.envoiJeuATous();
			
			// Gestion des joueurs :
			Collection<Joueur> lesJoueurs = jeuServeur.getLesJoueurs();
			victime = (Joueur)this.toucheVictime(lesJoueurs);
			
			// Gestion des murs : 
			isTouchMur = super.toucheMur(lesMurs);			
		}	
		// Gestion des joueurs :
		if (victime != null && !victime.estMort()) {
			// envoi musique joueur touché :
			this.jeuServeur.envoi(null, 1);	
			
			victime.perteVie();
			
			// Joueur victorieux :
			leJoueur.gainVie(leJoueur);
			leJoueur.affiche(Constante.marche, leJoueur.getEtape(), leJoueur.getOrientation());
			
			// Affichage du joueur touché :
			for (Integer touchIndex = 1; touchIndex <= 2; touchIndex ++) {
				victime.affiche(Constante.touche, touchIndex, victime.getOrientation());
				pause();
			}
			victime.affiche(Constante.marche, 1, victime.getOrientation());
			
			// Affichage du joeur mort :
			if (victime.estMort()) {
				// envoi musique joueur touché :
				this.jeuServeur.envoi(null, 2);	
				
				for (Integer deathIndex = 1; deathIndex <= 2; deathIndex ++) {
					victime.affiche(Constante.mort, deathIndex, victime.getOrientation());
					pause();
				}
			}
		}
		this.lblBoule.setVisible(false);
		jeuServeur.envoiJeuATous();
	}
	
	/**
	 * Controle si une boule touche un des joueurs
	 * @param Collection<Joueur>
	 * @return Joueur
	 */		
	private Joueur toucheVictime(Collection<Joueur> lesJoueurs) {
		Boolean isTouchJoueur = true;
		for (Joueur unJoueur : lesJoueurs) {				
			isTouchJoueur = toucheObjet(unJoueur);
			if (isTouchJoueur) {
				return unJoueur;
			}
		}
		return null;
	}
	
	private void pause(){
		try {
			new Thread(this).sleep(80, 0);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erreur : pause");
			return;
		}
	}
}
