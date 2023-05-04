package controleur;

/**
 * Gestion des méthodes communes :
 * @author Marjorie
 *
 */
public class Common {
	/**
	 * Retourne un nombre aléatoire entre un minimum et un maximun :
	 * @param min
	 * @param max
	 * @return random value to min max
	 */
	public static int randXY (int min, int max) {
		int result = min + (int)(Math.random() * ((max - min) + 1));		
		return result;
	}
}
