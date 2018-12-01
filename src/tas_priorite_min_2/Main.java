package tas_priorite_min_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import arbre_de_recherche_5.AVL;
import arbre_de_recherche_5.Noeud;
import autres.CleInteger;
import echauffement_1.Cle128Bit;
import echauffement_1.FileConverter;
import interfaces.ICle;
import interfaces.ITasMin;

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
		
		calculerTempsCalculConsIter();
		
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
	
	private static void calculerTempsCalculConsIter() {
		TasMinTab tas;
		ArrayList<ICle> liste;
		HashMap<Integer, ArrayList<Float>> tempsParTaille = new HashMap<>();
		
		int tailles[] = {100, 200, 500, 1000, 5000, 10000, 20000, 50000};
		int nb = 5; int cpt = 0;
		
		String nomFichier;
		FileConverter fc;
		
		long debut, fin;
		float ecoule;
		float f = 1000000;				// Division pour obtenir le temps en milliseconde
		
		String nomFichierCSV = "consIter_tab.csv";
		for(int i=1; i<=nb; i++) {
			for(int j=0; j<tailles.length; j++) {
				if (!tempsParTaille.containsKey(tailles[j]))		// Initialisation des ArrayList des Hashmap
					tempsParTaille.put(tailles[j], new ArrayList<Float>());
				
				tas = new TasMinTab(10000);
				cpt++;
				nomFichier = "jeu_"+i+"_nb_cles_"+tailles[j]+".txt";
				System.out.println("Fichier : " + nomFichier + "Progression : " + cpt + "/" + nb*tailles.length);
				
				fc = new FileConverter("donnees/cles_alea/"+nomFichier);
				liste = new ArrayList<ICle>(fc.getCle());					// La liste des clé à insérer
				
				debut = System.nanoTime();
				
				tas.consIter(liste);
				
				fin = System.nanoTime();
				ecoule = ((fin - debut)/f);
				
				System.out.println("\tTemps d'exécution : " + ecoule + "ms");
				tempsParTaille.get(tailles[j]).add(ecoule);
				
			}
		}// Toutes les recherches ont été effectués
		
		System.out.println("Sauvegarde des resultats dans le fichier \"" + nomFichierCSV + "\"...");
		if (sauvegarderResultat(nomFichierCSV, tempsParTaille))
			System.out.println("Les résultats ont été sauvegardés !");
		else
			System.err.println("Erreur lors de la sauvagardes des résultats");
	}
	
	private static boolean sauvegarderResultat(String nomFichierCSV, HashMap<Integer, ArrayList<Float>> tempsParTaille) {
		ArrayList<Float> liste;
		float moyenne;
		float max;
		ArrayList<Integer> listeTriee = new ArrayList<>(tempsParTaille.keySet());
		Collections.sort(listeTriee);

		// Écriture des résultats
		try {
			File fichierCSV = new File("resultats/" + nomFichierCSV);
	        String aEcrire = "Taille moyenne maximum\n";
	        DecimalFormat df = new DecimalFormat("#.###");
	        for (Integer taille : listeTriee) {
				liste = new ArrayList<>(tempsParTaille.get(taille));
				moyenne = getMoyenne(liste);
				max = getMax(liste);
				aEcrire += taille + " " + df.format(moyenne )+ " " + max + "\n";
			}
	        FileWriter writer = new FileWriter(fichierCSV);
	        writer.write(aEcrire);
	        writer.close();
	        
	        return true;
	        
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Calcule la moyenne des éléments d'une liste.
	 * @param liste La liste pour laquelle on souhaite la moyenne des éléments.
	 * @return La moyenne des éléments de la liste.
	 */
	private static float getMoyenne(ArrayList<Float> liste) {
		float somme = 0;
		for(Float x : liste)
			somme += x;
			
		return somme/liste.size();
	}
	
	/**
	 * Récupère la valeur maximale dans une liste.
	 * @param liste La liste pour laquelle on souhaite récupérer la valeur maximale.
	 * @return L'élément le plus grand de la liste.
	 */
	private static float getMax(ArrayList<Float> liste) {
		float max = -1;
		for(Float x : liste) {
			if (x > max)
				max = x;
		}
		
		return max;
	}
}
