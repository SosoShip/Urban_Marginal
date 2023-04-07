package controleur;

import vue.EntreeJeu;
import vue.Arene;
import vue.ChoixJoueur;

/**
 * Contr�leur et point d'entr�e de l'applicaton 
 * @author emds
 *
 */
public class Controle {

	private EntreeJeu frmEntreeJeu ;

	/**
	 * M�thode de d�marrage
	 * @param args non utilis�
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	
	public void evenementEntréeJeu(String info) {	
		if( info.equals("serveur")) {
			(new Arene()).setVisible(true);
			this.frmEntreeJeu.dispose();
		}
		else {
			(new ChoixJoueur()).setVisible(true);
			this.frmEntreeJeu.dispose();
			
		}
	}

}
