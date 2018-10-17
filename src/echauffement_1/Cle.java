package echauffement_1;

import java.math.BigInteger;
import java.util.BitSet;

public class Cle implements ICle{

	private String cleString; // Representation hexadécimal
	private BitSet cle; // Representation binaire sur 128 bits
	
	/**
	 * Constructeur
	 * @param cleS : hexadecimal à transformer en cle
	 */
	public Cle(String cleS) {
		cleString = cleS;
		cle = new BitSet(128);
		String cleBinaire = new BigInteger(cleString, 16).toString(2);
	
	    for(int i = 0; i < cleBinaire.length(); i++) {	 
	    	if(cleBinaire.charAt(i) == '0') {
	    		cle.set(i, false);
	    	}else
	    		cle.set(i, true);
	    }

	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < cle.size(); i++) {	
	    	if(cle.get(i))
	    		sb.append('1');
	    	else
	    		sb.append('0');
	    }
		
		return sb.toString();
	}
	

	
	@Override
	public boolean inf(Cle cle1) {
		
		boolean result = false;
		
		if(cle.length() < cle1.cle.length())
			return true;
		else if(cle.length() > cle1.cle.length())
			return false;
		else {
			for(int i = 0; i< cle1.cle.length(); i++) {
				if(cle.get(i) != cle1.cle.get(i)) {
					if(cle.get(i) == false)
						return true;
					else
						return false;
				}
			}
		}
		
		return result;
	}

	@Override
	public boolean eg(Cle cle1) {
		boolean result = true;
		
		if(cle.length() != cle1.cle.length())
			return false;
		
		for(int i = 0; i< cle1.cle.length(); i++) {
			if(cle.get(i) != cle1.cle.get(i)) {
				return false;
			}
		}
		
		return result;
	}
	
}
