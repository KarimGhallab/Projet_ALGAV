package arbre_de_recherche_5;

public class Main {

	public static void main(String[] args) { 
		IAVL arbre = new AVL(); 

		// On construit un arbre
		arbre.inserer(0);
		arbre.inserer(10);
		arbre.inserer(9);
		arbre.inserer(80);
		arbre.inserer(7);
		arbre.inserer(8);
		arbre.inserer(9);
		arbre.inserer(10);
		arbre.inserer(11);
		arbre.inserer(12);
		arbre.inserer(2);
		arbre.inserer(0);
		arbre.inserer(17);
		
		System.out.println("[AVL] Parcours infixe : \n" + arbre.infixeToString());
		System.out.println("[AVL] Parcours prefixe : \n" + arbre.prefixeToString());
		try {
			Noeud n = arbre.rechercher(15);
			System.out.println("[AVL] Parcours infixe : \n" + n.infixeToString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
