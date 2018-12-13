package III_files_binomiales;

import java.util.Vector;

import autres.CleInteger;
import interfaces.ICle;

public class Main {

	public static void main(String[] args) {
		
		
		Vector<ICle> cles = new Vector<>();
		
		for(int i=0; i < 10000; i++)
			cles.add(new CleInteger((int)(Math.random() * 100000)));
		
		FileBinomiale fconst = new FileBinomiale(0);
		
		long debut = System.currentTimeMillis();
		
		FileBinomiale.itrAvecCles(cles,fconst);
		
		System.out.println(System.currentTimeMillis()-debut);
		
	}
}
