package Ex6_etude_experimentale;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import Ex5_arbre_de_recherche.AVL;
import Ex5_arbre_de_recherche.InsertionException;
import autres.Outils;
import Ex1_echauffement.Cle128Bit;
import Ex4_hachage.MD5;

/**
 * Classe Main de l'étude expérimentale
 *
 */
public class Main {

	public static void main(String[] args) {
		// stockerMD5DansArbre();
		// Outils.calculerTempsConsIterFileBinomiale();
		Outils.calculerTempsUnionFileBinomiale();
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
					if (!motsAjoutes.contains(mot)) {		// On a pas encore calculé la signature de ce mot
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
		System.out.println("Nombre de mots différents dans le jeu de données : " + listeMot.size());
	}	
}
