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
		
		int N = 1000000;
		for (int i=N; i>=1; i--)
			tArbre.ajout(new CleInteger(i));
	}
	
	@Test
	public void testAjoutCle() {
		testAjoutCleRec(tArbre.getRacine());
	}
	
	public void testAjoutCleRec(Noeud courant) {
		if (courant.getFilsGauche() != null) {
			if (courant.getFilsGauche().getNoeud() != null) {
				assertTrue(courant.getNoeud().inf(courant.getFilsGauche().getNoeud()));
				testAjoutCleRec(courant.getFilsGauche());
			}
		}
		if (courant.getFilsDroit() != null) {
			if (courant.getFilsDroit().getNoeud() != null) {
				assertTrue(courant.getNoeud().inf(courant.getFilsDroit().getNoeud()));
				testAjoutCleRec(courant.getFilsDroit());
			}
		}
	}	
}
