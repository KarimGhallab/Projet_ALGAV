package tas_priorite_min_2.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import autres.CleInteger;
import tas_priorite_min_2.Noeud;
import tas_priorite_min_2.TasMinArbre;

public class testTasMinArbre {
	
	TasMinArbre tArbre;
	
	@Before
	public void initialize() {
		tArbre = new TasMinArbre();
		
		int N = 100000;
		for (int i=N; i>=1; i--) {
			tArbre.ajout(new CleInteger(i));
			tArbre.ajout(new CleInteger(i));
		}
		
		assertTrue(tArbre.size() == N*2);
	}
	
	@Test
	public void testAjoutCle() {
		testStructureTasMin(tArbre.getRacine());
	}
	
	@Test
	public void testSupprMin() {
		for (int i=0; i<100000; i++)
			tArbre.supprMin();
		
		testStructureTasMin(tArbre.getRacine());
	}
	
	@Test
	public void testSupprMin2() {
		int taille = tArbre.size();
		for (int i=0; i<taille; i++)
			tArbre.supprMin();
		
		assertTrue(tArbre.size() == 0);
		testStructureTasMin(tArbre.getRacine());
	}
	
	@Test
	public void testSupprMin3() {
		for (int i=0; i>=10000; i--) {
			tArbre.ajout(new CleInteger(i));
			tArbre.supprMin();
		}
		
		testStructureTasMin(tArbre.getRacine());
	}
	
	public void testStructureTasMin(Noeud courant) {
		if (courant.getFilsGauche() != null) {
			if (courant.getFilsGauche().getNoeud() != null) {
				boolean assertion = (courant.getNoeud().inf(courant.getFilsGauche().getNoeud()) || courant.getNoeud().eg(courant.getFilsGauche().getNoeud())); 
				// System.out.println("(" + courant.getNoeud() + " <= " + courant.getFilsGauche().getNoeud() + ")   ==> " + assertion);
				assertTrue(assertion);
				testStructureTasMin(courant.getFilsGauche());
			}
		}
		if (courant.getFilsDroit() != null) {
			if (courant.getFilsDroit().getNoeud() != null) {
				boolean assertion = (courant.getNoeud().inf(courant.getFilsDroit().getNoeud()) || courant.getNoeud().eg(courant.getFilsDroit().getNoeud())); 
				// System.out.println("(" + courant.getNoeud() + " <= " + courant.getFilsDroit().getNoeud() + ")   ==> " + assertion);
				assertTrue(assertion);
				testStructureTasMin(courant.getFilsDroit());
			}
		}
	}	
}
