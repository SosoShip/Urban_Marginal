package U_M_Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controleur.Constante;
import modele.Mur;
import modele.Objet;

class ObjetTest {
	private MockObjet mur1 = new MockObjet();
	private MockObjet mur2 = new MockObjet();

	
	
	/**
	@Test
	void testToucheMur() {
		fail("Not yet implemented");
	}
	**/

	@Test
	void testToucheObjet() {
		mur1.pouetPosX(100); 
		mur1.pouetPosY(100);
		mur2.pouetPosX(100); 
		mur2.pouetPosY(100);
		
		//assertTrue(mur.toucheObjet(mur2));
		//assertFalse(mur.toucheObjet(mur2));
		assertEquals(true, mur1.toucheObjet(mur2));

	}
	
	/**	
	@Test
	void testSetPosX() {
		fail("Not yet implemented");
	}

	@Test
	void testSetPosY() {
		fail("Not yet implemented");
	}
	@Test
	void testGetPosX() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPosY() {
		fail("Not yet implemented");
	}
**/
}
