package etude_experimentale_6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import arbre_de_recherche_5.AVL;
import arbre_de_recherche_5.InsertionException;
import echauffement_1.Cle128Bit;
import hachage_4.MD5;

/**
 * Classe Main de l'étude expérimentale
 *
 */
public class Main {

	public static void main(String[] args) {
		stockerMD5DansArbre();
	}
	
	/**
	 * Stocke tous les MD5 des mots de Shakespeare dans un AVL et affiche le nombre de collision.
	 */
	public static void stockerMD5DansArbre() {
		int nbCollision = 0;
		File folder = new File("donnees/Shakespeare/");
		File file;
		File[] listeFichiers = folder.listFiles();
		AVL<Cle128Bit> avl = new AVL<>();
		HashSet<String> motsAjoutes = new HashSet<>();
		ArrayList<String> listeMot = new ArrayList<>();
		
		for (int i=0; i<listeFichiers.length; i++) {
			file = listeFichiers[i];
			BufferedReader bufferedReader;
			String mot, md5;
			System.out.println("Lecture du fichier : " + file.getName() + " : Progression : " + (i+1) + "/" + listeFichiers.length);
			try {
				bufferedReader = new BufferedReader(new FileReader(file));
				mot = bufferedReader.readLine();
				// Parcours et ajout de tous les MD5 des mots du fichier courant
				while (mot != null) {
					if (!motsAjoutes.contains(mot)) {
						motsAjoutes.add(mot);
						listeMot.add(mot);
						md5 = MD5.genererMd5(mot);
						// System.out.println(md5 + " <= " + mot);
						try {
							avl.inserer(new Cle128Bit(md5));
						} catch (InsertionException e) {
							System.err.println("Collison du MD5 : " + md5);
							nbCollision++;
						}
					}
					mot = bufferedReader.readLine();
				}	// Fin du fichier en cours
				bufferedReader.close();
			} catch (IOException e) {
				System.err.println("Erreur avec le fichier : " + file.getName());
				e.printStackTrace();
				return;
			}
		}// Fin des ajouts
		System.out.println("Nombre de collision : " + nbCollision);
		System.out.println("Affichage de la liste de mot : ");
		for(int i=0; i<listeMot.size(); i++) {
			System.out.println("\t" + listeMot.get(i));
		}
	}
}
