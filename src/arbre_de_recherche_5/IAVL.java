package arbre_de_recherche_5;

public interface IAVL {
	/**
	 * Getteur sur la racine de l'arbre.
	 * @return La racine de l'arbre.
	 */
	public Noeud getRacine();
	
	/**
	 * Setteur de la racine de l'arbre.
	 * @param racine La nouvelle racine pour l'arbre.
	 */
	public void setRacine(Noeud racine);
	
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
	public void inserer(int cle);
	
	/**
	 * Recherche une clé dans l'arbre.
	 * @param cle La clé à rechercher.
	 * @return Le noeud contenant la clé.
	 * @throws Exception La clé n'est pas présente dans l'arbre.
	 */
	public Noeud rechercher(int cle) throws Exception;
}
