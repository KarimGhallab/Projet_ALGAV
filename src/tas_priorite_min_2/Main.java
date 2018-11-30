package tas_priorite_min_2;

import java.util.ArrayList;
import java.util.List;

import autres.CleInteger;
import interfaces.ICle;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Tas min avec un tableau : ");
		TasMinTab t = new TasMinTab(5);
		
		t.ajout(new CleInteger(1));
		t.ajout(new CleInteger(9));
		t.ajout(new CleInteger(5));
		t.ajout(new CleInteger(10));
		t.ajout(new CleInteger(11));
		t.ajout(new CleInteger(3));
		
		t.supprMin();
		
		System.out.println("\t" + t.toString());
		
		//Test consIter
		TasMinTab tLoop = new TasMinTab(5);
		
		List<ICle> l = new ArrayList<>();
		l.add(new CleInteger(1));
		l.add(new CleInteger(9));
		l.add(new CleInteger(5));
		l.add(new CleInteger(10));
		l.add(new CleInteger(11));
		l.add(new CleInteger(3));
		l.add(new CleInteger(2));
		l.add(new CleInteger(15));
		l.add(new CleInteger(6));
		
		tLoop.consIter(l);
		
		System.out.println("\t" + tLoop.toString());
		
		// Test union
		TasMinTab t2 = new TasMinTab(9);
		t2.ajout(new CleInteger(1));
		t2.ajout(new CleInteger(99));
		t2.ajout(new CleInteger(65));
		t2.ajout(new CleInteger(13));
		t2.ajout(new CleInteger(11));
		t2.ajout(new CleInteger(14));
		t2.ajout(new CleInteger(22));
		t2.ajout(new CleInteger(5));
		t2.ajout(new CleInteger(9));
		
		
		System.out.println("\tunion");
		System.out.println("\t\t" + t.toString());
		System.out.println("\t\t" + t2.toString());
		t.union(t2);
		
		System.out.println("\t\t" + t.toString());
		
		System.out.println("Tas min avec un arbre : ");
		
		TasMinArbre tArbre1 = new TasMinArbre();
		
		int N = 30;
		for (int i=N; i>=1; i--) {
			tArbre1.ajout(new CleInteger(i+20));
			tArbre1.ajout(new CleInteger(i));
			tArbre1.supprMin();
		}
		
		System.out.println("premier arbre : \n" + tArbre1.infixeToString());
		
		TasMinArbre tArbre2 = new TasMinArbre();
		
		for (int i=N; i>=1; i--)
			tArbre2.ajout(new CleInteger(i));
		
		for (int i=0; i<N/7; i++) {
			tArbre2.supprMin();
		}
		
		tArbre2.ajout(new CleInteger(0));
		
		System.out.println("Second arbre : \n" + tArbre2.infixeToString());
		
		List<ICle> liste = new ArrayList<ICle>();
		for (int i=20; i>0; i--)
			liste.add(new CleInteger(i));
		
		TasMinArbre tArbre3 = new TasMinArbre();
		
		System.out.println("ConsIter");
		tArbre3.consIter(liste);
		
		tArbre3.ajout(new CleInteger(18));
		tArbre3.ajout(new CleInteger(25));
		
		tArbre3.ajout(new CleInteger(4));
		tArbre3.ajout(new CleInteger(0));
		System.out.println("Troisième arbre : \n" + tArbre3.infixeToString());
		
		TasMinArbre tUnion1 = new TasMinArbre();
		TasMinArbre tUnion2 = new TasMinArbre();
		for (int i=10; i>0; i--)
			tUnion1.ajout(new CleInteger(i));
		
		for (int i=0; i<10; i++)
			tUnion2.ajout(new CleInteger(i));
		
		System.out.println("Union");
		System.out.println("Union 1: \n" + tUnion1.infixeToString());
		
		System.out.println("Union 2: \n" + tUnion2.infixeToString());
		
		tUnion1.union(tUnion2);
		System.out.println("Union : \n" + tUnion1.infixeToString());
		
		/*printTab(tUnion1.getRepresentationTableau());
		printTab(tUnion2.getRepresentationTableau());
		
		tUnion1.union(tUnion2);
		printTab(tUnion1.getRepresentationTableau());*/
		
	}
	
	@SuppressWarnings("unused")
	private static void printTab(ICle[] tab) {
		ICle c;
		System.out.print("[");
		for (int i=0; i<tab.length; i++) {
			c = tab[i];
			System.out.print(c);
			if (i != tab.length-1)
				System.out.print(", ");
		}
		System.out.println("]");
	}
}
