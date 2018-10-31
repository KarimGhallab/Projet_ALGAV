package tas_priorite_min_2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import autres.CleInteger;
import echauffement_1.Cle128Bit;
import interfaces.ICle;
import tas_priorite_min_2.TasMinTab;

public class testTasMinTab {
	TasMinTab tas1;
	TasMinTab tasConstLoop;
	TasMinTab tas2;
	List<ICle> list;
	
	@Before
	public void initialize() {
		tas1 = new TasMinTab(5);
		tas2 = new TasMinTab(5);
		tasConstLoop = new TasMinTab(5);
		
		list = new ArrayList<>();
		list.add(new CleInteger(1));
		list.add(new CleInteger(9));
		list.add(new CleInteger(5));
		list.add(new CleInteger(10));
		list.add(new CleInteger(11));
		list.add(new CleInteger(3));
		list.add(new CleInteger(2));
	}
	
	@Test
	public void testAjoutCle() {
		tas1.ajout(new Cle128Bit("9CF48CF5"));
		tas1.ajout(new Cle128Bit("6CF48C5"));
		tas1.ajout(new Cle128Bit("AEF48CF5"));
		
		assertEquals(tas1.size(), 3);
		
		//Pour des contrainte d'affichage, on utilise ICleInteger
		tas1 = new TasMinTab(5);
		
		tas1.ajout(new CleInteger(1));
		tas1.ajout(new CleInteger(9));
		tas1.ajout(new CleInteger(5));
		tas1.ajout(new CleInteger(10));
		tas1.ajout(new CleInteger(11));
		tas1.ajout(new CleInteger(3));
		
		assertEquals(tas1.size(), 6);
		assertEquals(tas1.toString(), "1 9 3 10 11 5");
	}
	
	@Test
	public void testDeleteCleMin() {
		
		tas1.ajout(new Cle128Bit("149CF48CF5"));
		tas1.ajout(new Cle128Bit("56CF48C5"));
		tas1.ajout(new Cle128Bit("EF48CF5"));
		tas1.ajout(new Cle128Bit("EF48CF8A954A5"));
		
		assertEquals(tas1.size(), 4);
		assertTrue(tas1.supprMin());
		assertEquals(tas1.size(), 3);
		assertTrue(tas1.supprMin());
		assertTrue(tas1.supprMin());
		assertEquals(tas1.size(), 1);
		
		//Pour des contrainte d'affichage, on utilise ICleInteger
		tas1 = new TasMinTab(5);
		
		tas1.ajout(new CleInteger(10));
		tas1.ajout(new CleInteger(9));
		tas1.ajout(new CleInteger(15));
		tas1.ajout(new CleInteger(10));
		tas1.ajout(new CleInteger(11));
		tas1.ajout(new CleInteger(30));
		
		assertEquals(tas1.size(), 6);
		assertEquals("9 10 15 10 11 30", tas1.toString());
		
		assertTrue(tas1.supprMin());
		
		assertEquals(tas1.size(), 5);
		assertEquals(tas1.toString(), "15 10 30 10 11");
	}
	
	@Test
	public void testConstIterTasMin() {
		
		assertEquals(tasConstLoop.size(), 0);
		
		tasConstLoop.consIter(list);
		
		assertEquals(tasConstLoop.size(), list.size());
		assertEquals(tasConstLoop.toString(), "1 9 2 10 11 5 3");
		
	}
	
	@Test
	public void testUnionTas() {
		
		tas1.ajout(new Cle128Bit("149CF48CF5"));
		tas1.ajout(new Cle128Bit("56CF48C5"));
		tas1.ajout(new Cle128Bit("EF48CF5"));
		tas1.ajout(new Cle128Bit("EF48CF8A954A5"));
		
		tas2.ajout(new Cle128Bit("11C9CF49CF7"));
		tas2.ajout(new Cle128Bit("EA4CCC5"));
		tas2.ajout(new Cle128Bit("AEF49C64A954A5"));
		
		assertEquals(tas1.size(), 4);
		tas1.union(tas2);
		assertEquals(tas1.size(), 7);
		
		//Pour des contrainte d'affichage, on utilise ICleInteger
		tas1 = new TasMinTab(5);
		tas2 = new TasMinTab(5);
		
		tas1.ajout(new CleInteger(10));
		tas1.ajout(new CleInteger(9));
		tas1.ajout(new CleInteger(15));
		
		tas2.ajout(new CleInteger(10));
		tas2.ajout(new CleInteger(11));
		tas2.ajout(new CleInteger(30));
		tas2.ajout(new CleInteger(0));
		tas2.ajout(new CleInteger(90));
		
		assertTrue(tas1.union(tas2));
		assertEquals(tas1.toString(), "0 9 11 10 10 30 15 90");
		
	}
	
}
