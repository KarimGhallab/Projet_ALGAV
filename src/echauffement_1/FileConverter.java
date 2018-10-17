package echauffement_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class FileConverter {
	
	private File file;
	
	/**
	 * Constructeur
	 * @param path : chemin du fichier a lire.
	 */
	public FileConverter(String path) {
		file = new File(path);
	}
	
	/**
	 *Li dans le fichier pour recuperer les cles en les stocke dans un vector. 
	 * @return le vector contenant les cles.
	 */
	public Vector<Cle> getCle(){
		Vector<Cle> cles = new Vector<Cle>();
		BufferedReader br;
		
		try {
				br = new BufferedReader(new FileReader(file));
				String ligne;
				while ((ligne = br.readLine()) != null) {
					
					String cleS = ligne.substring(2);
					
					Cle cle = new Cle(cleS);
					
					cles.add(cle);
				}
				br.close();
		}catch (IOException e) {
			System.out.println("erreur");
		}
		
		return cles;
	}
}
