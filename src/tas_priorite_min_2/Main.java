package tas_priorite_min_2;

import java.util.ArrayList;
import java.util.List;

import autres.CleInteger;
import interfaces.ICle;

public class Main {

	public static void main(String[] args) {
		
		TasMinTab t = new TasMinTab();
		
		t.ajout(new CleInteger(1));
		t.ajout(new CleInteger(9));
		t.ajout(new CleInteger(5));
		t.ajout(new CleInteger(10));
		t.ajout(new CleInteger(11));
		t.ajout(new CleInteger(3));
		t.ajout(new CleInteger(2));
		t.ajout(new CleInteger(15));
		t.ajout(new CleInteger(6));
		//t.supprMin();
		
		System.out.println(t.toString());
		
		TasMinTab tLoop = new TasMinTab();
		
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
		
		System.out.println(tLoop.toString());
		
	}

}
