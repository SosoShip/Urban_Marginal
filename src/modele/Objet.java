package modele;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JLabel;

import controleur.Constante;

/**
 * Informations communes � tous les objets (joueurs, murs, boules)
 * permet de m�moriser la position de l'objet et de g�rer les  collisions
 * @author Marjorie
 *
 * @param Objet<get>
 */
public abstract class Objet<get> {

	/**
	 * position X de l'objet
	 */
	protected int posX ;
	/**
	 * position Y de l'objet
	 */
	protected int posY ;
	/**
	 * Longueur des objets :
	 */
	protected int objectLengthX = 0;
	/**
	 * Hauteur des objets :
	 */
	protected int objectHeightY = 0;
	/**
	 * JLabel mur utilisés par toute les classes :
	 */
	protected JLabel lblMur;
	/**
	 * JLabel joueur utilisés par toute les classes :
	 */
	protected JLabel lblJoueur;
	/** 
	 * Visuel de la boule :
	 */
	protected JLabel lblBoule;
	
	/**
	 * Contr�le si le joueur touche un des murs
	 * @param ArrayList<>
	 * @return Boolean
	 */
	protected Boolean toucheMur(ArrayList<Mur> lesMurs) {
		Boolean isTouchMur = false;
		for (Mur unMur : lesMurs) {			
			isTouchMur = toucheObjet(unMur);
			if (isTouchMur)	{
				break;
			}
		}
		return isTouchMur;
	}
	
	/**
	 * contr�le si l'objet actuel touche l'objet pass� en param�tre
	 * @param objet contient l'objet � contr�ler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet (Objet objet) {
		// Simplification des coordonées X et Y des objets entre eux :
		boolean posXInferieur = this.getPosX() < objet.getPosX() 
				&& this.getPosX() + this.objectLengthX < objet.getPosX();
		boolean posXSuperieur = this.getPosX() > objet.getPosX() + objet.objectLengthX 
				&& this.getPosX() + this.objectLengthX > objet.getPosX() + objet.objectLengthX;
		boolean posYInferieur = this.getPosY() < objet.getPosY() 
				&& this.getPosY() + this.objectHeightY < objet.getPosY();
		boolean posYSuperieur = this.getPosY() > objet.getPosY() + objet.objectHeightY 
				&& this.getPosY() + this.objectHeightY > objet.getPosY() + objet.objectHeightY;
				
		if((this == null)||(objet == null)) {
			return null;
		}
		else {
			if( ! ((posXInferieur) || (posXSuperieur)) && ! ((posYInferieur)||(posYSuperieur))) {				
				return true;				
			}			
			else {
				return false;
			}
		}		
	}

	/**
	 * retourne la position de l'objet sur l'axe des ordonées :
	 * @return int
	 */
	protected int getPosX() {
		return posX;
	}

	/**
	 * retourne la position de l'objet sur l'axe des abcisses :
	 * @return int
	 */
	protected int getPosY() {
		return posY;
	}
	
}
