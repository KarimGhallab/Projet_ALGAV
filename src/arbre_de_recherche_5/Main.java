package arbre_de_recherche_5;

import autres.CleInteger;
import interfaces.IAVL;

public class Main {

	public static void main(String[] args) { 
		// On construit un arbre
		IAVL<CleInteger> arbre = new AVL<CleInteger>();
		for (int i=0; i<10; i++)
			arbre.inserer(new CleInteger(i));
		
		System.out.println("[AVL] Parcours infixe : \n" + arbre.infixeToString());
		System.out.println("[AVL] Parcours prefixe : \n" + arbre.prefixeToString());
		try {
			Noeud<CleInteger> n = arbre.rechercher(new CleInteger(10));
			System.out.println("[AVL] Parcours infixe : \n" + n.infixeToString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
