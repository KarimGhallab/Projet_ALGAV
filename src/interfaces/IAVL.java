package interfaces;

import arbre_de_recherche_5.Noeud;

/**
 * Interface d'un arbre AVL
 * @author 3772468
 *
 * @param <C> @param <C> Le type des clés contenues dans l'arbre
 */
public interface IAVL<C extends ICle> {
	/**
	 * Getteur sur la racine de l'arbre.
	 * @return La racine de l'arbre.
	 */
	public Noeud<C> getRacine();
	
	/**
	 * Setteur de la racine de l'arbre.
	 * @param racine La nouvelle racine pour l'arbre.
	 */
	public void setRacine(Noeud<C> racine);
	
	/**
	 * Récupère la hauteur de la racine de l'arbre.
	 * @return La hauteur de la racine de l'arbre.
	 */
	public int getHauteur();
	
	/**
	 * Affichage du parcours infixe de l'arbre.
	 * @return La chaine de caractère correpondant à la suite des clés de l'arbre
	 * selon un parcours infixe.
	 */
	public String infixeToString();
	
	/**
	 * Affichage du parcours prefixe de l'arbre.
	 * @return La chaine de caractère correpondant à la suite des clés de l'arbre
	 * selon un parcours prefixe.
	 */
	public String prefixeToString();
	
	/**
	 * Insère une clé dans l'arbre.
	 * @param cle La clé à insérer.
	 */
	public void inserer(C cle);
	
	/**
	 * Recherche une clé dans l'arbre.
	 * @param cle La clé à rechercher.
	 * @return Le noeud contenant la clé.
	 * @throws Exception La clé n'est pas présente dans l'arbre.
	 */
	public Noeud<C> rechercher(C cle) throws Exception;
}
