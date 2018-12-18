package Ex1_echauffement;

import java.util.Vector;

import interfaces.ICle;

public class Main {	
	
	public static void main(String[] args) {
		
		FileConverter cv = new FileConverter("donnees/cles_alea/jeu_5_nb_cles_50000.txt");
		Vector <ICle> cles = cv.getCle();
		Vector <String> clesS = cv.getCleHexa();
		
		ICle c;
		String hex;
		
		for(int i=0; i<cles.size(); i++) {
			c = cles.get(i);
			hex = clesS.get(i);
			
			System.out.println(hex + " ==> " + c);
		}
	}

}
