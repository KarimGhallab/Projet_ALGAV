package arbre_de_recherche_5;

public class Main {

	public static void main(String[] args) { 
		AVL arbre = new AVL(); 

		// On construit un arbre
		arbre.setRacine(arbre.inserer(arbre.getRacine(), 10));
		arbre.setRacine(arbre.inserer(arbre.getRacine(), 4));
		arbre.setRacine(arbre.inserer(arbre.getRacine(), 17));
		arbre.setRacine(arbre.inserer(arbre.getRacine(), 80));
		arbre.setRacine(arbre.inserer(arbre.getRacine(), 90));
		arbre.setRacine(arbre.inserer(arbre.getRacine(), 2));
		arbre.setRacine(arbre.inserer(arbre.getRacine(), 0));
		arbre.setRacine(arbre.inserer(arbre.getRacine(), 11));
		arbre.setRacine(arbre.inserer(arbre.getRacine(), 12));
		arbre.setRacine(arbre.inserer(arbre.getRacine(), 10));

		System.out.println("[AVL] Parcours infixe : \n" + arbre.infixeToString(arbre.getRacine()));
		try {
			Noeud n = arbre.rechercher(arbre.getRacine(), 15);
			System.out.println("[AVL] Parcours infixe : \n" + arbre.infixeToString(n));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
