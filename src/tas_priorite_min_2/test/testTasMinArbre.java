package tas_priorite_min_2.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import autres.CleInteger;
import echauffement_1.Cle128Bit;
import echauffement_1.FileConverter;
import interfaces.ICle;
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
		testStructureTasMin(tArbre.getRacine(), tArbre.size());
	}
	
	@Test
	public void testSupprMin() {
		for (int i=0; i<100000; i++)
			tArbre.supprMin();
		
		testStructureTasMin(tArbre.getRacine(), tArbre.size());
	}
	
	@Test
	public void testSupprMin2() {
		int taille = tArbre.size();
		for (int i=0; i<taille; i++)
			tArbre.supprMin();
		
		assertTrue(tArbre.size() == 0);
		testStructureTasMin(tArbre.getRacine(), tArbre.size());
	}
	
	@Test
	public void testSupprMin3() {
		for (int i=0; i>=10000; i--) {
			tArbre.ajout(new CleInteger(i));
			tArbre.supprMin();
		}
		
		testStructureTasMin(tArbre.getRacine(), tArbre.size());
	}
	
	public void testStructureTasMin(Noeud courant, int taille) {
		if (taille > 0) {
			if (courant.getFilsGauche() != null) {
				if (courant.getFilsGauche().getNoeud() != null) {
					boolean assertion = (courant.getNoeud().inf(courant.getFilsGauche().getNoeud()) || courant.getNoeud().eg(courant.getFilsGauche().getNoeud())); 
					// System.out.println("(" + courant.getNoeud() + " <= " + courant.getFilsGauche().getNoeud() + ")   ==> " + assertion);
					assertTrue(assertion);
					testStructureTasMin(courant.getFilsGauche(), taille);
				}
			}
			if (courant.getFilsDroit() != null) {
				if (courant.getFilsDroit().getNoeud() != null) {
					boolean assertion = (courant.getNoeud().inf(courant.getFilsDroit().getNoeud()) || courant.getNoeud().eg(courant.getFilsDroit().getNoeud())); 
					// System.out.println("(" + courant.getNoeud() + " <= " + courant.getFilsDroit().getNoeud() + ")   ==> " + assertion);
					assertTrue(assertion);
					testStructureTasMin(courant.getFilsDroit(), taille);
				}
			}
		}
	}
	
	@Test
	public void consIter() {
		List<ICle> liste = new ArrayList<ICle>();
		
		for (int i=1000000; i>0; i--)
			liste.add(new CleInteger(i));
		
		TasMinArbre tArbreIter = new TasMinArbre();
		tArbreIter.consIter(liste);
		
		testStructureTasMin(tArbreIter.getRacine(), tArbreIter.size());
		
		for (int i=5000000; i>4000000; i--)
			tArbreIter.ajout(new CleInteger(i));
		
		testStructureTasMin(tArbreIter.getRacine(), tArbreIter.size());
		
		for (int i=0; i<1500000; i++)
			tArbreIter.supprMin();
			
		testStructureTasMin(tArbreIter.getRacine(), tArbreIter.size());
	}
	
	@Test
	public void testUnion() {
		TasMinArbre tUnion1 = new TasMinArbre();
		TasMinArbre tUnion2 = new TasMinArbre();
		for (int i=100000; i>0; i--)
			tUnion1.ajout(new CleInteger(i));
		
		for (int i=0; i<250000; i++)
			tUnion2.ajout(new CleInteger(i));
		
		tUnion1.union(tUnion2);
		testStructureTasMin(tUnion1.getRacine(), tUnion1.size());
	}
	
	@Test
	public void testAvecCle128Bit() {
		TasMinArbre tArbre128 = new TasMinArbre();
		FileConverter fc = new FileConverter("donnees/cles_alea/jeu_4_nb_cles_50000.txt");
		Vector<Cle128Bit> v = fc.getCle();
		
		for(Cle128Bit c : v)
			tArbre128.ajout(c);
		
		testStructureTasMin(tArbre128.getRacine(), tArbre128.size());
		
		tArbre128.supprMin();
		tArbre128.supprMin();
		tArbre128.supprMin();
		
		testStructureTasMin(tArbre128.getRacine(), tArbre128.size());
		
		fc = new FileConverter("donnees/cles_alea/jeu_2_nb_cles_20000.txt");
		v = fc.getCle();
		for(Cle128Bit c : v)
			tArbre128.ajout(c);
		
		testStructureTasMin(tArbre128.getRacine(), tArbre128.size());
		
		tArbre128.supprMin();
		
		testStructureTasMin(tArbre128.getRacine(), tArbre128.size());
	}
}
