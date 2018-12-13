package I_echauffement;

import java.math.BigInteger;
import java.util.BitSet;

import interfaces.ICle;
/**
 * Clé codé sur 128 bits.
 *
 */
public class Cle128Bit implements ICle{

	/** Representation hexadécimal de la clé. */
	private String cleString;
	
	/** Representation binaire sur 128 bits. */
	private BitSet cle;
	
	/**
	 * Constructeur
	 * @param cleS : hexadecimal à transformer en cle de 128 bits
	 */
	public Cle128Bit(String cleS) {
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
	public boolean inf(ICle cle1) {
		if (cle1 instanceof Cle128Bit) {
			Cle128Bit c = (Cle128Bit) cle1;
			boolean result = false;
			
			if(cle.length() < c.cle.length())
				return true;
			else if(cle.length() > c.cle.length())
				return false;
			else {
				for(int i = 0; i< c.cle.length(); i++) {
					if(cle.get(i) != c.cle.get(i)) {
						if(cle.get(i) == false)
							return true;
						else
							return false;
					}
				}
			}
			
			return result;
		}
		else
			throw new RuntimeException("Impossible de comparer une \"Cle\" avec une autre classe");
			
	}

	@Override
	public boolean eg(ICle cle1) {
		
		if (cle1 instanceof Cle128Bit) {
			Cle128Bit c = (Cle128Bit) cle1;
			
			boolean result = true;
			
			if(cle.length() != c.cle.length())
				return false;
			
			for(int i = 0; i< c.cle.length(); i++) {
				if(cle.get(i) != c.cle.get(i)) {
					return false;
				}
			}
			
			return result;
		}
		else
			throw new RuntimeException("Impossible de comparer une \"Cle\" avec une autre classe");
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Cle128Bit) {
			Cle128Bit c = (Cle128Bit) o;
			return this.eg(c);
		}
		else
			return false;
			
	}
	
	@Override
	public int hashCode() {
		return cleString.hashCode();
	}
}
