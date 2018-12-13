package echauffement_1;

import java.util.Vector;

public class Main {	
	
	public static void main(String[] args) {
		
		FileConverter cv = new FileConverter("donnees/cles_alea/jeu_5_nb_cles_50000.txt");
		Vector <Cle128Bit> cles = cv.getCle();
		
		for(Cle128Bit c : cles)
			System.out.println(c);
	}

}
