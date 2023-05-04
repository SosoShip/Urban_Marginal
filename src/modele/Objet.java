package modele;

import javax.swing.JLabel;

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
	 * JLabel utilisés par toute les classes :
	 */
	protected JLabel lblMur;
	
	/**
	 * contr�le si l'objet actuel touche l'objet pass� en param�tre
	 * @param objet contient l'objet � contr�ler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet (Objet objet) {
		return null;
	}
	
}
