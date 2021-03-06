package interfaces;

/**
 * Interface d'une file binomiale.
 */
public interface IFileBinomiale {
	
	/**
	 * Ajoute un tournoi à une file binomiale.
	 * @param tb Le tournoi binomial à ajouter.
	 * @return La nouvelle file contenant le tournoi.
	 */
	public IFileBinomiale add(ITournoiBinomial tb);
	
	/**
	 * Renvoie le tournoi de degre minimal dans la file.
	 * @return le tournoi de degre minimal dans la file.
	 */
	public ITournoiBinomial minDeg();
	
	/**
	 * Renvoie vrai si et seulement si la file est vide.
	 * @return true, si la file est vide, false sinon.
	 */
	public boolean estVide();
	
	/**
	 * Renvoie la file privee de son tournoi de degre minimal.
	 * @return la file privee de son tournoi de degre minimal
	 */
	public IFileBinomiale reste();
	
	/**
	 * Ajoute un tournoi binomial en tete des elements de la file.
	 * @param t tournoi binomial a ajouter.
	 * @return la nouvelle file avec pour nouveau tournoi de degre min, le tournoi qui a ete ajoute.
	 */
	public IFileBinomiale ajoutMin(ITournoiBinomial t, IFileBinomiale f);
	
	/**
	 * Renvoie la file binomiale union des deux files la file courante et 'f'.
	 * @param f la file avec laquelle ont faire l'union.
	 * @return une nouvelle file union des deux autres (la courante et 'f').
	 */
	public IFileBinomiale unionFile(IFileBinomiale f1, IFileBinomiale f2);
	
	/**
	 * Renvoie la file binomiale union de deux files et d’un tournoi.
	 * @param f la file avec laquelle ont faire l'union.
	 * @param retenue un tournoi en retenu
	 * @return une nouvelle file union des deux autres (la courante et 'f').
	 */
	public IFileBinomiale UFret(IFileBinomiale f1, IFileBinomiale f2, ITournoiBinomial retenue);
	
	/**
	 * Renvoie la plus petit cle, presente dans la file.
	 * @return la plus petit cle, presente dans la file.
	 */
	public ICle rechercheMin();
	
	/**
	 * Supprime la plus petite clé de la file binomiale.
	 * @return une file privée de sa clé minimale.
	 */
	public IFileBinomiale supprCleMin();

	/**
	 * Renvoie la taille de la file binomiale.
	 * @return la taille de la file binomiale.
	 */
	public int getTaille();
	
	/**
	 * Renvoie le nombre de tas présent dans la file.
	 * @return Le nombre de tas présent dans la file.
	 */
	public int getNbTas();
	
	/**
	 * Renvoie le tournoi binomial présent à l'indice donnée en paramètre.
	 * @param index L'indice du tas dans la file.
	 * @return Le tournoi binomial présent à l'indice donnée en paramètre.
	 */
	public ITournoiBinomial getTas(int index);
}
