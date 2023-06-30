package U_M_Test;

import controleur.Constante;
import modele.Objet;

public class MockObjet extends Objet {
	/**
	 * @param posX the posX to set
	 */
	public void pouetPosX(int posX) {
		super.setPosX(posX);
	}

	/**
	 * @param posY the posY to set
	 */
	public void pouetPosY(int posY) {
		super.setPosY(posY);
	}
	
	public MockObjet() {
		int objectLengthX = Constante.tailleDesMurs;
		int objectHeightY = Constante.tailleDesMurs;
	}

}
