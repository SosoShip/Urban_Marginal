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
		this.setPosX(Common.randXY(minX, maxX));
		this.objectLengthX = Constante.tailleDesMurs;

		int minY = 0 + Constante.tailleDesMurs;
		int maxY = Constante.hauteurArene;
		this.setPosY(Common.randXY(minY, maxY));
		this.objectHeightY = Constante.tailleDesMurs;
		
		// label deffinissant les murs :
		lblMur = new JLabel("");
		lblMur.setBounds(getPosX(), getPosY(), Constante.tailleDesMurs, Constante.tailleDesMurs);		
		lblMur.setIcon(new ImageIcon(Constante.getInstance().iconMur));		
		}
	}
			
	

