package Ex1_echauffement;

import java.util.Vector;

import interfaces.ICle;

public class Main {	
	
	public static void main(String[] args) {
		
		FileConverter cv = new FileConverter("donnees/cles_alea/jeu_5_nb_cles_50000.txt");
		Vector <ICle> cles = cv.getCle();
		
		for(ICle c : cles)
			System.out.println(c);
	}

}
