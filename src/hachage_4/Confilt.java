package hachage_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Classe permettant de récupèrer le nombre de conflit de MD5 générer 
 * à partir des mots des oeuvres de Shakespeare
 *
 */
public class Confilt {
	
	public static void main(String[] args) {
		File folder = new File("donnees/Shakespeare/");
		File file;
		File[] listeFichiers = folder.listFiles();
		HashMap<String, String> mots = new HashMap<>();
		HashMap<String, Integer> collisions = new HashMap<>();
		
		for (int i=0; i<listeFichiers.length; i++) {
			file = listeFichiers[i];
			collisions.put(file.getName(), 0);
			BufferedReader bufferedReader;
			String mot, md5;
			System.out.println("Lecture du fichier : " + file.getName() + " : Progression : " + (i+1) + "/" + listeFichiers.length);
			try {
				bufferedReader = new BufferedReader(new FileReader(file));
				mot = bufferedReader.readLine();
				while (mot != null) {
					md5 = MD5.genererMd5(mot);
					//System.out.println(md5 + " <= " + mot);
					if(!mots.containsKey(md5)) {
						mots.put(md5, mot);
					}
					else {			// Il y a une collisions
						if (!mots.get(md5).equals(mot))		// Deux mots differents ont eu le même MD5
							collisions.put(file.getName(), collisions.get(file.getName())+1);
					}
					mot = bufferedReader.readLine();
				}	// Fin du fichier en cours
				bufferedReader.close();
			} catch (IOException e) {
				System.err.println("Erreur avec le fichier : " + file.getName());
				e.printStackTrace();
			}
		}
		// On parcours les collisions
		for (String nomFichier : collisions.keySet()) {
			System.out.println(nomFichier + " : " + collisions.get(nomFichier) + " collision(s)");
		}
	}
}
