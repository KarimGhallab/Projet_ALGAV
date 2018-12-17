package autres;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import Ex1_echauffement.FileConverter;
import Ex2_tas_priorite_min.TasMinArbre;
import Ex2_tas_priorite_min.TasMinTab;
import Ex3_files_binomiales.FileBinomiale;
import Ex3_files_binomiales.TournoiBinomial;
import interfaces.ICle;

public class Outils {

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
	
	@SuppressWarnings("unused")
	public static void calculerTempsConsIterArbre() {
		TasMinArbre tas;
		ArrayList<ICle> liste;
		HashMap<Integer, ArrayList<Float>> tempsParTaille = new HashMap<>();
		
		int tailles[] = {100, 200, 500, 1000, 5000, 10000, 20000, 50000};
		int nb = 5; int cpt = 0;
		
		String nomFichier;
		FileConverter fc;
		
		long debut, fin;
		float ecoule;
		float f = 1000000;				// Division pour obtenir le temps en milliseconde
		
		String nomFichierCSV = "consIter_arbre.csv";
		for(int i=1; i<=nb; i++) {
			for(int j=0; j<tailles.length; j++) {
				if (!tempsParTaille.containsKey(tailles[j]))		// Initialisation des ArrayList des Hashmap
					tempsParTaille.put(tailles[j], new ArrayList<Float>());
				
				tas = new TasMinArbre();
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
		}// Toutes les constructions ont été effectuées
		
		System.out.println("Sauvegarde des resultats dans le fichier \"" + nomFichierCSV + "\"...");
		if (sauvegarderResultatTasMin(nomFichierCSV, tempsParTaille))
			System.out.println("Les résultats ont été sauvegardés !");
		else
			System.err.println("Erreur lors de la sauvagardes des résultats");
	}
	
	public static void calculerTempsUnionArbre() {
		TasMinArbre tas1, tas2;
		ArrayList<ICle> liste1, liste2;
		HashMap<Integer, ArrayList<Float>> tempsParTaille = new HashMap<>();
		
		int tailles[] = {100, 200, 500, 1000, 5000, 10000, 20000, 50000};
		int nb = 5;
		
		String nomFichier1, nomFichier2;
		FileConverter fc1, fc2;
		
		long debut, fin;
		float ecoule;
		float f = 1000000;				// Division pour obtenir le temps en milliseconde
		
		String nomFichierCSV = "union_arbre.csv";
		for(int i=1; i<nb; i++) {
			for(int j=0; j<tailles.length; j++) {
				if (!tempsParTaille.containsKey(tailles[j]))		// Initialisation des ArrayList des Hashmap
					tempsParTaille.put(tailles[j], new ArrayList<Float>());
				
				nomFichier1 = "jeu_"+i+"_nb_cles_"+tailles[j]+".txt";
				fc1 = new FileConverter("donnees/cles_alea/"+nomFichier1);
				liste1 = new ArrayList<ICle>(fc1.getCle());
				tas1 = new TasMinArbre();
				tas1.consIter(liste1);
				
				System.out.println("Union de " + nomFichier1 + " avec : ");
				for (int k=i+1; k<=nb; k++) {
					nomFichier2 = "jeu_"+k+"_nb_cles_"+tailles[j]+".txt";
					System.out.println("\t- " + nomFichier2);
					fc2 = new FileConverter("donnees/cles_alea/"+nomFichier2);
					liste2 = new ArrayList<ICle>(fc2.getCle());
					tas2 = new TasMinArbre();
					tas2.consIter(liste2);
					
					debut = System.nanoTime();
					tas2.union(tas1);
					fin = System.nanoTime();
					
					ecoule = ((fin - debut)/f);
					
					System.out.println("\tTemps d'exécution : " + ecoule + "ms");
					tempsParTaille.get(tailles[j]).add(ecoule);
				}
				System.out.println();
			}
		}// Toutes les constructions ont été effectuées
		
		System.out.println("Sauvegarde des resultats dans le fichier \"" + nomFichierCSV + "\"...");
		if (sauvegarderResultatTasMin(nomFichierCSV, tempsParTaille))
			System.out.println("Les résultats ont été sauvegardés !");
		else
			System.err.println("Erreur lors de la sauvagardes des résultats");
	}
	
	@SuppressWarnings("unused")
	public static void calculerTempsConsIterTab() {
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
		}// Toutes les constructions ont été effectuées
		
		System.out.println("Sauvegarde des resultats dans le fichier \"" + nomFichierCSV + "\"...");
		if (sauvegarderResultatTasMin(nomFichierCSV, tempsParTaille))
			System.out.println("Les résultats ont été sauvegardés !");
		else
			System.err.println("Erreur lors de la sauvagardes des résultats");
	}
	
	public static void calculerTempsAjoutTab() {
		TasMinTab tas;
		HashMap<Integer, ArrayList<Float>> tempsParTaille = new HashMap<>();
		
		int tailles[] = {100, 200, 500, 1000, 5000, 10000, 20000, 50000};
		int nb = 5; int cpt = 0;
		
		String nomFichier;
		FileConverter fc;
		
		long debut, fin;
		float ecoule;
		float f = 1000000;				// Division pour obtenir le temps en milliseconde
		
		String nomFichierCSV = "ajout_tab.csv";
		for(int i=1; i<=nb; i++) {
			for(int j=0; j<tailles.length; j++) {
				if (!tempsParTaille.containsKey(tailles[j]))		// Initialisation des ArrayList des Hashmap
					tempsParTaille.put(tailles[j], new ArrayList<Float>());
				
				tas = new TasMinTab(10000);
				cpt++;
				nomFichier = "jeu_"+i+"_nb_cles_"+tailles[j]+".txt";
				System.out.println("Fichier : " + nomFichier + "Progression : " + cpt + "/" + nb*tailles.length);
				
				fc = new FileConverter("donnees/cles_alea/"+nomFichier);
				Vector<ICle> cles = fc.getCle();
				// Ajout de toutes les cles
				for (int k = 0; k<cles.size(); k++) {
					ICle c = cles.get(k);
					if (k < cles.size() - (cles.size()/10))
						tas.ajout(c);

					// Les 10% des ajouts restant
					// Il faut calculer le temps de l'ajout
					else {
						debut = System.nanoTime();
						
						tas.ajout(c);
						
						fin = System.nanoTime();
						ecoule = ((fin - debut)/f);
						
						System.out.println("\tTemps d'exécution : " + ecoule + "ms");
						tempsParTaille.get(tailles[j]).add(ecoule);
					}
				}
			}
		}// Toutes les constructions ont été effectuées
		
		System.out.println("Sauvegarde des resultats dans le fichier \"" + nomFichierCSV + "\"...");
		if (sauvegarderResultatTasMin(nomFichierCSV, tempsParTaille))
			System.out.println("Les résultats ont été sauvegardés !");
		else
			System.err.println("Erreur lors de la sauvagardes des résultats");
	}
	
	public static void calculerTempsSupprMinTab() {
		TasMinTab tas;
		HashMap<Integer, ArrayList<Float>> tempsParTaille = new HashMap<>();
		
		int tailles[] = {100, 200, 500, 1000, 5000, 10000, 20000, 50000};
		int nb = 5; int cpt = 0;
		
		String nomFichier;
		FileConverter fc;
		
		long debut, fin;
		float ecoule;
		float f = 1000000;				// Division pour obtenir le temps en milliseconde
		
		String nomFichierCSV = "supprMin_tab.csv";
		for(int i=1; i<=nb; i++) {
			for(int j=0; j<tailles.length; j++) {
				if (!tempsParTaille.containsKey(tailles[j]))		// Initialisation des ArrayList des Hashmap
					tempsParTaille.put(tailles[j], new ArrayList<Float>());
				
				tas = new TasMinTab(10000);
				cpt++;
				nomFichier = "jeu_"+i+"_nb_cles_"+tailles[j]+".txt";
				System.out.println("Fichier : " + nomFichier + "Progression : " + cpt + "/" + nb*tailles.length);
				
				fc = new FileConverter("donnees/cles_alea/"+nomFichier);
				
				tas.consIter(new ArrayList<>(fc.getCle()));
				int tmpTaille = tas.size();
				for (int k=0; k<tmpTaille/10; k++ ) {
					debut = System.nanoTime();
					
					tas.supprMin();
					
					fin = System.nanoTime();
					ecoule = ((fin - debut)/f);
					
					System.out.println("\tTemps d'exécution : " + ecoule + "ms");
					tempsParTaille.get(tailles[j]).add(ecoule);
				}
			}
		}// Toutes les constructions ont été effectuées
		
		System.out.println("Sauvegarde des resultats dans le fichier \"" + nomFichierCSV + "\"...");
		if (sauvegarderResultatTasMin(nomFichierCSV, tempsParTaille))
			System.out.println("Les résultats ont été sauvegardés !");
		else
			System.err.println("Erreur lors de la sauvagardes des résultats");
	}
	
	@SuppressWarnings("unused")
	public static void calculerTempsUnionTab() {
		TasMinTab tas1, tas2;
		ArrayList<ICle> liste1, liste2;
		HashMap<Integer, ArrayList<Float>> tempsParTaille = new HashMap<>();
		
		int tailles[] = {100, 200, 500, 1000, 5000, 10000, 20000, 50000};
		int nb = 5;
		
		String nomFichier1, nomFichier2;
		FileConverter fc1, fc2;
		
		long debut, fin;
		float ecoule;
		float f = 1000000;				// Division pour obtenir le temps en milliseconde
		
		String nomFichierCSV = "union_tab.csv";
		for(int i=1; i<nb; i++) {
			for(int j=0; j<tailles.length; j++) {
				if (!tempsParTaille.containsKey(tailles[j]))		// Initialisation des ArrayList des Hashmap
					tempsParTaille.put(tailles[j], new ArrayList<Float>());
				
				nomFichier1 = "jeu_"+i+"_nb_cles_"+tailles[j]+".txt";
				fc1 = new FileConverter("donnees/cles_alea/"+nomFichier1);
				liste1 = new ArrayList<ICle>(fc1.getCle());
				tas1 = new TasMinTab(10000);
				tas1.consIter(liste1);
				
				System.out.println("Union de " + nomFichier1 + " avec : ");
				for (int k=i+1; k<=nb; k++) {
					nomFichier2 = "jeu_"+k+"_nb_cles_"+tailles[j]+".txt";
					System.out.println("\t- " + nomFichier2);
					fc2 = new FileConverter("donnees/cles_alea/"+nomFichier2);
					liste2 = new ArrayList<ICle>(fc2.getCle());
					tas2 = new TasMinTab(10000);
					tas2.consIter(liste2);
					
					debut = System.nanoTime();
					tas2.union(tas1);
					fin = System.nanoTime();
					
					ecoule = ((fin - debut)/f);
					
					System.out.println("\tTemps d'exécution : " + ecoule + "ms");
					tempsParTaille.get(tailles[j]).add(ecoule);
				}
				System.out.println();
			}
		}// Toutes les constructions ont été effectuées
		
		System.out.println("Sauvegarde des resultats dans le fichier \"" + nomFichierCSV + "\"...");
		if (sauvegarderResultatTasMin(nomFichierCSV, tempsParTaille))
			System.out.println("Les résultats ont été sauvegardés !");
		else
			System.err.println("Erreur lors de la sauvagardes des résultats");
	}
	
	public static boolean sauvegarderResultatTasMin(String nomFichierCSV, HashMap<Integer, ArrayList<Float>> tempsParTaille) {
		ArrayList<Float> liste;
		float moyenne;
		float max;
		ArrayList<Integer> listeTriee = new ArrayList<>(tempsParTaille.keySet());
		Collections.sort(listeTriee);

		// Écriture des résultats
		try {
			File fichierCSV = new File("resultats/" + nomFichierCSV);
	        String aEcrire = "Taille moyenne maximum\n";
	        DecimalFormat df = new DecimalFormat("#.#####");			// Version valeurs normales
	        // DecimalFormat df = new DecimalFormat("#.###");			// Version valeurs normales
	        for (Integer taille : listeTriee) {
				liste = new ArrayList<>(tempsParTaille.get(taille));
				moyenne = getMoyenne(liste);
				max = getMax(liste);
				aEcrire += taille + " " + df.format(moyenne)+ " " + df.format(max) + "\n";
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
	public static float getMoyenne(ArrayList<Float> liste) {
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
	public static float getMax(ArrayList<Float> liste) {
		float max = -1;
		for(Float x : liste) {
			if (x > max)
				max = x;
		}
		
		return max;
	}

	public static void calculerTempsConsIterFileBinomiale() {
		FileBinomiale fileBinomiale;
		HashMap<Integer, ArrayList<Float>> tempsParTaille = new HashMap<>();
		
		int tailles[] = {100, 200, 500, 1000, 5000, 10000, 20000, 50000};
		int nb = 5; int cpt = 0;
		
		String nomFichier;
		FileConverter fc;
		
		long debut, fin;
		float ecoule;
		float f = 1000000;				// Division pour obtenir le temps en milliseconde
		
		String nomFichierCSV = "consIter_fileBinomiale.csv";
		for(int i=1; i<=nb; i++) {
			for(int j=0; j<tailles.length; j++) {
				if (!tempsParTaille.containsKey(tailles[j]))		// Initialisation des ArrayList des Hashmap
					tempsParTaille.put(tailles[j], new ArrayList<Float>());
				
				fileBinomiale = new FileBinomiale(0);
				cpt++;
				nomFichier = "jeu_"+i+"_nb_cles_"+tailles[j]+".txt";
				System.out.println("Fichier : " + nomFichier + "Progression : " + cpt + "/" + nb*tailles.length);
				
				fc = new FileConverter("donnees/cles_alea/"+nomFichier);
				
				List<ICle> cles = fc.getCle();
				
				debut = System.nanoTime();
				FileBinomiale.constItr(cles, fileBinomiale);
				fin = System.nanoTime();
				
				ecoule = ((fin - debut)/f);
				
				System.out.println("\tTemps d'exécution : " + ecoule + "ms");
				tempsParTaille.get(tailles[j]).add(ecoule);
				
			}
		}// Toutes les constructions ont été effectuées
		
		System.out.println("Sauvegarde des resultats dans le fichier \"" + nomFichierCSV + "\"...");
		if (sauvegarderResultatFileBinomiale(nomFichierCSV, tempsParTaille))
			System.out.println("Les résultats ont été sauvegardés !");
		else
			System.err.println("Erreur lors de la sauvagardes des résultats");
		
	}
	
	public static void calculerTempsUnionFileBinomiale(){
		
		FileBinomiale fileBinomiale1, fileBinomiale2;
		HashMap<Integer, ArrayList<Float>> tempsParTaille = new HashMap<>();
		
		int tailles[] = {100, 200, 500, 1000, 5000, 10000, 20000, 50000};
		int nb = 5;
		
		String nomFichier1, nomFichier2;
		FileConverter fc1, fc2;
		
		long debut, fin;
		float ecoule;
		float f = 1000000;				// Division pour obtenir le temps en milliseconde
		
		String nomFichierCSV = "union_fileBinomiale.csv";
		for(int i=1; i<nb; i++) {
			for(int j=0; j<tailles.length; j++) {
				if (!tempsParTaille.containsKey(tailles[j]))		// Initialisation des ArrayList des Hashmap
					tempsParTaille.put(tailles[j], new ArrayList<Float>());
				
				nomFichier1 = "jeu_"+i+"_nb_cles_"+tailles[j]+".txt";
				fc1 = new FileConverter("donnees/cles_alea/"+nomFichier1);
				
				fileBinomiale1 = new FileBinomiale(0);
				FileBinomiale.constItr(fc1.getCle(), fileBinomiale1);
				
				System.out.println("Union de " + nomFichier1 + " avec : ");
				for (int k=i+1; k<=nb; k++) {
					nomFichier2 = "jeu_"+k+"_nb_cles_"+tailles[j]+".txt";
					System.out.println("\t- " + nomFichier2);
					fc2 = new FileConverter("donnees/cles_alea/"+nomFichier2);
					
					fileBinomiale2 = new FileBinomiale(0);
					FileBinomiale.constItr(fc2.getCle(), fileBinomiale2);
					
					debut = System.nanoTime();
					
					fileBinomiale2 = (FileBinomiale)fileBinomiale2.unionFile(fileBinomiale2, fileBinomiale1);
					
					fin = System.nanoTime();
					
					ecoule = ((fin - debut)/f);
					
					System.out.println("\tTemps d'exécution : " + ecoule + "ms");
					tempsParTaille.get(tailles[j]).add(ecoule);
				}
				System.out.println();
			}
		}// Toutes les constructions ont été effectuées
		
		System.out.println("Sauvegarde des resultats dans le fichier \"" + nomFichierCSV + "\"...");
		if (sauvegarderResultatFileBinomiale(nomFichierCSV, tempsParTaille))
			System.out.println("Les résultats ont été sauvegardés !");
		else
			System.err.println("Erreur lors de la sauvagardes des résultats");
	}

	public static void calculerTempsAjoutFileBinomiale() {
		FileBinomiale file;
		HashMap<Integer, ArrayList<Float>> tempsParTaille = new HashMap<>();
		
		int tailles[] = {100, 200, 500, 1000, 5000, 10000, 20000, 50000};
		int nb = 5; int cpt = 0;
		
		String nomFichier;
		FileConverter fc;
		
		long debut, fin;
		float ecoule;
		float f = 1000000;				// Division pour obtenir le temps en milliseconde
		
		String nomFichierCSV = "ajout_fileBinomiale.csv";
		for(int i=1; i<=nb; i++) {
			for(int j=0; j<tailles.length; j++) {
				if (!tempsParTaille.containsKey(tailles[j]))		// Initialisation des ArrayList des Hashmap
					tempsParTaille.put(tailles[j], new ArrayList<Float>());
				
				file = new FileBinomiale(10000);
				cpt++;
				nomFichier = "jeu_"+i+"_nb_cles_"+tailles[j]+".txt";
				System.out.println("Fichier : " + nomFichier + "Progression : " + cpt + "/" + nb*tailles.length);
				
				fc = new FileConverter("donnees/cles_alea/"+nomFichier);
				Vector<ICle> cles = fc.getCle();
				// Ajout de toutes les cles
				for (int k = 0; k<cles.size(); k++) {
					ICle c = cles.get(k);
					if (k < cles.size() - (cles.size()/10))
						file = (FileBinomiale) file.add(new TournoiBinomial(c));

					// Les 10% des ajouts restant
					// Il faut calculer le temps de l'ajout
					else {
						debut = System.nanoTime();
						
						file = (FileBinomiale) file.add(new TournoiBinomial(c));
						
						fin = System.nanoTime();
						ecoule = ((fin - debut)/f);
						
						System.out.println("\tTemps d'exécution : " + ecoule + "ms");
						tempsParTaille.get(tailles[j]).add(ecoule);
					}
				}
			}
		}// Toutes les constructions ont été effectuées
		
		System.out.println("Sauvegarde des resultats dans le fichier \"" + nomFichierCSV + "\"...");
		if (sauvegarderResultatTasMin(nomFichierCSV, tempsParTaille))
			System.out.println("Les résultats ont été sauvegardés !");
		else
			System.err.println("Erreur lors de la sauvagardes des résultats");
	}
	
	public static void calculerTempsSupprMinFileBinomiale() {
		FileBinomiale file;
		HashMap<Integer, ArrayList<Float>> tempsParTaille = new HashMap<>();
		
		int tailles[] = {100, 200, 500, 1000, 5000, 10000, 20000, 50000};
		int nb = 5; int cpt = 0;
		
		String nomFichier;
		FileConverter fc;
		
		long debut, fin;
		float ecoule;
		float f = 1000000;				// Division pour obtenir le temps en milliseconde
		
		String nomFichierCSV = "supprMin_fileBinomiale.csv";
		for(int i=1; i<=nb; i++) {
			for(int j=0; j<tailles.length; j++) {
				if (!tempsParTaille.containsKey(tailles[j]))		// Initialisation des ArrayList des Hashmap
					tempsParTaille.put(tailles[j], new ArrayList<Float>());
				
				file = new FileBinomiale(0);
				cpt++;
				nomFichier = "jeu_"+i+"_nb_cles_"+tailles[j]+".txt";
				System.out.println("Fichier : " + nomFichier + "Progression : " + cpt + "/" + nb*tailles.length);
				
				fc = new FileConverter("donnees/cles_alea/"+nomFichier);
				
				FileBinomiale.constItr(fc.getCle(), (FileBinomiale) file);
				for (int k=0; k<tailles[j]/10; k++ ) {
					debut = System.nanoTime();
					
					file = (FileBinomiale) file.supprCleMin();
					
					fin = System.nanoTime();
					ecoule = ((fin - debut)/f);
					
					System.out.println("\tTemps d'exécution : " + ecoule + "ms");
					tempsParTaille.get(tailles[j]).add(ecoule);
				}
			}
		}// Toutes les constructions ont été effectuées
		
		System.out.println("Sauvegarde des resultats dans le fichier \"" + nomFichierCSV + "\"...");
		if (sauvegarderResultatTasMin(nomFichierCSV, tempsParTaille))
			System.out.println("Les résultats ont été sauvegardés !");
		else
			System.err.println("Erreur lors de la sauvagardes des résultats");
	}
	
	public static boolean sauvegarderResultatFileBinomiale(String nomFichierCSV, HashMap<Integer, ArrayList<Float>> tempsParTaille) {
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
				aEcrire += taille + " " + df.format(moyenne)+ " " + df.format(max) + "\n";
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
}
