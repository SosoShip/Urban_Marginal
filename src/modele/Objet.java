package modele;

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
	 * contr�le si l'objet actuel touche l'objet pass� en param�tre
	 * @param objet contient l'objet � contr�ler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet (Objet objet) {
		// Simplification des coordonées X et Y des objets entre eux :
		boolean posXInferieur = this.posX < objet.posX 
				&& this.posX + this.objectLengthX < objet.posX;
		boolean posXSuperieur = this.posX > objet.posX + objet.objectLengthX 
				&& this.posX + this.objectLengthX > objet.posX + objet.objectLengthX;
		boolean posYInferieur = this.posY < objet.posY 
				&& this.posY + this.objectHeightY < objet.posY;
		boolean posYSuperieur = this.posY > objet.posY + objet.objectHeightY 
				&& this.posY + this.objectHeightY > objet.posY + objet.objectHeightY;
				
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
	
}
