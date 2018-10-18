package arbre_de_recherche_5.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import arbre_de_recherche_5.AVL;
import autres.CleInteger;
import interfaces.IAVL;

public class AVLTest {

	@Test
	public void test() {
		IAVL<CleInteger> arbre = new AVL<CleInteger>();
		for (int i=0; i<10; i++)
			arbre.inserer(new CleInteger(i));
		
		assertTrue(arbre.getRacine().getCle().eg(new CleInteger(3)));
		List<CleInteger> l = arbre.getCleTriee();
		for(CleInteger c : l)
			System.out.println("Valeur : " + c.getValeur());
	}

}
