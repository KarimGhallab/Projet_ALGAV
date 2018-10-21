package autres;

import static org.junit.jupiter.api.Assertions.assertThrows;

import echauffement_1.Cle128Bit;
import interfaces.ICle;

public class CleInteger implements ICle{

	private Integer valeur;
	
	public CleInteger(Integer valeur) {
		this.valeur = valeur;
	}
	
	public Integer getValeur() {
		return valeur;
	}

	@Override
	public boolean inf(ICle cle1) throws RuntimeException{
		if (cle1 instanceof CleInteger)
			return valeur < ((CleInteger) cle1).getValeur();
		else
			throw new RuntimeException("Impossible de comparer une \"CleInteger\" avec une autre classe");
	}

	@Override
	public boolean eg(ICle cle1) {
		if (cle1 instanceof CleInteger)
			return valeur == ((CleInteger) cle1).getValeur();
		else
			throw new RuntimeException("Impossible de comparer une \"CleInteger\" avec une autre classe");
	}
	
	@Override
	public String toString() {
		return ""+valeur;
	}
}
