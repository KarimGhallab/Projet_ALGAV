package Ex1_echauffement.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import Ex1_echauffement.Cle128Bit;
import Ex1_echauffement.FileConverter;
import interfaces.ICle;

public class testValiditeCle{

	FileConverter fc;
	Cle128Bit cle32;
	Cle128Bit cle64;
	Cle128Bit cle96;
	Vector <ICle> cles;
	
	@Before
	public void initialize() {
		fc = new FileConverter("donnees/cles_alea/jeu_1_nb_cles_100.txt");
		cles = fc.getCle();
		cle32 = new Cle128Bit("9CF48CF5");
		cle64 = new Cle128Bit("9CF48CF5E89AF1A2");
		cle96 = new Cle128Bit("9CF48CF5E89AF1A2EFC892A8");
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
