package interfaces;

public interface ICle {
	
	/**
	 * Permet de déterminer si la cle est strictement inférieure à cle.
	 * @param cle1 clé dont on veux vérifier l'inférieurité.
	 */
	public boolean inf(ICle cle1);
	
	/**
	 * Verifie si l'egalité entre les cle1
	 * @param cle1 clé avec laquelle on veux vérifier l'égalité.
	 */
	public boolean eg(ICle cle1);
	
}
