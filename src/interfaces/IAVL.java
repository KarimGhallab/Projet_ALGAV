package interfaces;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import arbre_de_recherche_5.InsertionException;
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
	 * Récupère la hauteur depuis la racine de l'arbre.
	 * @return La hauteur depuis la racine de l'arbre.
	 */
	public int getHauteur();
	
	/**
	 * Récupère la taille de notre arbre
	 * @return La taille de l'arbre.
	 */
	public int size();
	
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
	 * @throws InsertionException En cas d'insèrtion d'une clé déjà présente dans l'arbre.
	 */
	public void inserer(C cle) throws InsertionException;
	
	/**
	 * Recherche une clé dans l'arbre.
	 * @param cle La clé à rechercher.
	 * @return Une map associant au noeud que l'on a trouvé, le nombre de comparaison necessaire à cet recherche.
	 * @throws Exception La clé n'est pas présente dans l'arbre.
	 */
	public SimpleEntry<Noeud<C>, Integer> rechercher(C cle) throws Exception;
	
	/**
	 * Récupère la liste triée des clés.
	 * @return La liste triée des clés.
	 */
	public List<C> getCleTriee();
}