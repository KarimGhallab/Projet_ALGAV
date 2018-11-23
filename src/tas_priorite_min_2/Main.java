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
		
		t.union(t2);
		
		System.out.println("\t" + t.toString());
		
		System.out.println("Tas min avec un arbre : ");
		
		TasMinArbre tArbre1 = new TasMinArbre();
		
		int N = 1023;
		for (int i=1; i<=N; i++)
			tArbre1.ajout(new CleInteger(i));
		
		System.out.println(tArbre1.infixeToString());
		
	}

}
