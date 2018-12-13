package echauffement_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * Lit un fichier contenant les valeurs des clés en hexadécimal et renvoit un vecteur contenant les traductions
 * de ses clés en entiers de 128 bit.
 *
 */
public class FileConverter {
	/** Le fichier à lire. */
	private File file;
	
	/**
	 * Constructeur
	 * @param path : chemin du fichier a lire.
	 */
	public FileConverter(String path) {
		file = new File(path);
	}
	
	/**
	 * Lit dans le fichier pour récupérer les clés en les stocke dans un vector. 
	 * @return le vector contenant les clés.
	 */
	public Vector<Cle128Bit> getCle(){
		Vector<Cle128Bit> cles = new Vector<Cle128Bit>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String ligne;
			while ((ligne = br.readLine()) != null) {
				
				String cleS = ligne.substring(2);
				
				Cle128Bit cle = new Cle128Bit(cleS);
				
				cles.add(cle);
			}
			br.close();
		}catch (IOException e) {
			System.out.println("erreur");
		}
		
		return cles;
	}
}
