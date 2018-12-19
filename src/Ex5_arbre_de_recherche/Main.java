package Ex5_arbre_de_recherche;

import java.util.Vector;

import Ex1_echauffement.Cle128Bit;
import Ex1_echauffement.FileConverter;
import autres.CleInteger;
import interfaces.ICle;

public class Main {

	public static void main(String[] args) throws InsertionException {
		mainCleInteger();
		mainCle128();

	}
	
	public static void mainCleInteger() throws InsertionException {
		System.out.println("#################");
		System.out.println("AVL - CleInteger");
		
		AVL<CleInteger> avl = new AVL<>();
		for (int i=0; i<18; i++)
			avl.inserer(new CleInteger(i));
		
		System.out.println(avl.infixeToString() + "\n");
	}
	
	public static void mainCle128() throws InsertionException {
		System.out.println("#################");
		System.out.println("AVL - Cle128Bit");
		
		AVL<Cle128Bit> avl = new AVL<>();
		FileConverter fc = new FileConverter("donnees/cles_alea/jeu_3_nb_cles_5000.txt");
		Vector<ICle> v = fc.getCle();
		
		for(int i=0; i<14; i++) {
			ICle c = v.get(i);
			avl.inserer((Cle128Bit) c);
		}
		
		System.out.println(avl.infixeToString() + "\n");
	}

}
