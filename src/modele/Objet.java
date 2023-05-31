package modele;

import javax.swing.JLabel;

import controleur.Constante;

/**
 * Informations communes � tous les objets (joueurs, murs, boules)
 * permet de m�moriser la position de l'objet et de g�rer les  collisions
 * @param <get>
 *
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
		if((this == null)||(objet == null)) {
			return null;
		}
		else {
			if(! ((this.posX < objet.posX 
					&& this.posX + this.objectLengthX < objet.posX)
				|| (this.posX > objet.posX + objet.objectLengthX 
					&& this.posX + this.objectLengthX > objet.posX + objet.objectLengthX))) {
				return true;				
			}
			if(!((this.posY < objet.posY 
					&& this.posY + this.objectHeightY < objet.posY)
				||(this.posY > objet.posY + objet.objectHeightY 
					&& this.posY + this.objectHeightY > objet.posY + objet.objectHeightY))) {
				return true;
			}
			else {
				return false;
			}
		}		
	}
	
}
