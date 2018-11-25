package interfaces;

import java.util.List;

public interface ITasMin {
	
	/**
	 * Renvoie le nombre de clé dans le tas.
	 * @return Le nombre de clé dans le tas au moment de l'appel.
	 */
	public int size();
	
	/**
	 * Supprime la cle minimal 
	 * @return La clé de valeur minimal dans le tas.
	 */
	public ICle supprMin();
	/**
	 * Ajoute une cle au tas minimal 
	 * @param c cle a ajouter
	 * @return true si la cle a correctement ete ajoute, false sinon.
	 */
	public boolean ajout(ICle c);
	
	/**
	 * Construit un tas minimal, a partir d'une liste de cles.
	 * @param elems
	 * @return le tas minimal cree
	 */
	public boolean consIter(List<ICle> elems);
	
	/**
	 * Fusionne 2 tas minimaux, pour former un seul tas minimal. 
	 * @param t2
	 * @return Le tas minimal issu de la fusion des 2 tas minimaux.
	 */
	public boolean union(ITasMin t2);
	
}
