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
		
		int N = 1000000/2;
		for (int i=N; i>=1; i--) {
			tArbre.ajout(new CleInteger(i));
			tArbre.ajout(new CleInteger(i));
		}
	}
	
	@Test
	public void testAjoutCle() {
		testAjoutCleRec(tArbre.getRacine());
	}
	
	public void testAjoutCleRec(Noeud courant) {
		if (courant.getFilsGauche() != null) {
			if (courant.getFilsGauche().getNoeud() != null) {
				boolean assertion = (courant.getNoeud().inf(courant.getFilsGauche().getNoeud()) || courant.getNoeud().eg(courant.getFilsGauche().getNoeud())); 
				System.out.println("(" + courant.getNoeud() + " <= " + courant.getFilsGauche().getNoeud() + ")   ==> " + assertion);
				assertTrue(assertion);
				testAjoutCleRec(courant.getFilsGauche());
			}
		}
		if (courant.getFilsDroit() != null) {
			if (courant.getFilsDroit().getNoeud() != null) {
				boolean assertion = (courant.getNoeud().inf(courant.getFilsDroit().getNoeud()) || courant.getNoeud().eg(courant.getFilsDroit().getNoeud())); 
				System.out.println("(" + courant.getNoeud() + " <= " + courant.getFilsDroit().getNoeud() + ")   ==> " + assertion);
				assertTrue(assertion);
				testAjoutCleRec(courant.getFilsDroit());
			}
		}
	}	
}
