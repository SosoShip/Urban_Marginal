package modele;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controleur.Common;
import controleur.Constante;

/**
 * Gestion des murs
 *
 */
public class Mur extends Objet {

	/**
	 * Constructeur
	 */
	public Mur() {				
		// Nombres aléatoires pour placer les murs sur X et y :
		int minX = 0 + Constante.tailleDesMurs;
		int maxX = Constante.longeurArene;
		posX = Common.randXY(minX, maxX);
		this.objectLengthX = getPosX();

		int minY = 0 + Constante.tailleDesMurs;
		int maxY = Constante.hauteurArene;
		posY = Common.randXY(minY, maxY);
		this.objectHeightY = getPosY();
		
		// label deffinissant les murs :
		lblMur = new JLabel("");
		lblMur.setBounds(getPosX(), getPosY(), Constante.tailleDesMurs, Constante.tailleDesMurs);		
		lblMur.setIcon(new ImageIcon(Constante.getInstance().iconMur));		
		}
	}
			
	

