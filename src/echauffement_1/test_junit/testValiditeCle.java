package echauffement_1.test_junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import echauffement_1.Cle;
import echauffement_1.FileConverter;

public class testValiditeCle{

	FileConverter fc;
	Cle cle32;
	Cle cle64;
	Cle cle96;
	Vector <Cle> cles;
	
	@Before
	public void initialize() {
		fc = new FileConverter("donnees/cles_alea/jeu_1_nb_cles_100.txt");
		cles = fc.getCle();
		cle32 = new Cle("9CF48CF5");
		cle64 = new Cle("9CF48CF5E89AF1A2");
		cle96 = new Cle("9CF48CF5E89AF1A2EFC892A8");
	}
	
	@Test
	public void testInfCle() {
		assertTrue(cle32.inf(cle64));
		assertFalse(cle96.inf(cle32));
	}
	
	@Test
	public void testEgCle() {
		assertTrue(cle32.eg(cle32));
		assertFalse(cle32.eg(cle64));
	}
	
	@Test
	public void testEgAndInfCle() {
		assertTrue(cles.get(0).eg(cles.get(0)));
		assertFalse(cles.get(0).inf(cles.get(0)));
		assertFalse(cles.get(0).inf(cles.get(1)));
	}
}