package Ex5_arbre_de_recherche;

import interfaces.ICle;

/**
 * Classe représentant un noeud pour nos arbres.
 * @param <C> Le type de la clé contenue dans le noeud.
 */
public class Noeud<C extends ICle> {
	/** La clé présente dans le noeud. */
    private C cle;
    /** La hauteur du noeud. */
    private int hauteur;
    
    /** Le fils gauche du noeud. */
    private Noeud<C> filsGauche;
    /** Le fils droit du noeud. */
    private Noeud<C> filsDroit; 
  
    /**
     * Constructeur d'un noeud à partir d'une clé.
     * @param cle La clé du noeud.
     */
    Noeud(C cle) { 
        this.cle = cle; 
        hauteur = 0;
        filsGauche = null;
        filsDroit = null;
    }

    /**
     * Getteur sur la clé du noeud.
     * @return La clé du noeud.
     */
	public C getCle() {
		return cle;
	}

	/**
	 * Setteur sur la clé du noeud.
	 * @param cle La nouvelle clé du noeud.
	 */
	public void setCle(C cle) {
		this.cle = cle;
	}

	/**
	 * Getteur sur la hauteur du noeud.
	 * @return La hauteur du noeud.
	 */
	public int getHauteur() {
		return hauteur;
	}

	/**
	 * Setteur sur la hauteur du noeud.
	 * @param hauteur La nouvelle hauteur du noeud.
	 */
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	/**
	 * Getteur sur le fils gauche du noeud.
	 * @return Le fils gauche du noeud
	 */
	public Noeud<C> getFilsGauche() {
		return filsGauche;
	}

	/**
	 * Setteur sur le fils gauche du noeud.
	 * @param filsGauche Le nouveau fils gauche du noeud.
	 */
	public void setFilsGauche(Noeud<C> filsGauche) {
		this.filsGauche = filsGauche;
	}

	/**
	 * Getteur sur le fils droit du noeud.
	 * @return Le fils droit du noeud
	 */
	public Noeud<C> getFilsDroit() {
		return filsDroit;
	}

	/**
	 * Setteur sur le fils droit  du noeud.
	 * @param filsDroit Le nouveau fils droit du noeud.
	 */
	public void setFilsDroit(Noeud<C> filsDroit) {
		this.filsDroit = filsDroit;
	}
	
	/**
	 * Affichage du parcours infixe depuis un noeud.
	 * @param racine La racine depuis laquelle il faut effectuer le parcours.
	 * @return La chaîne de caractère correspondant à la suite des clé depuis la racine
	 * selon un parcours infixe.
	 */
	public String infixeToString(String tabLvl) { 
		String tmp = "";
		if (getFilsGauche() != null)
			tmp += getFilsGauche().infixeToString(tabLvl + "\t");
		
		tmp += tabLvl + "Clé : " + getCle().toString() + "\n";
		
		if (getFilsDroit() != null)
			tmp += getFilsDroit().infixeToString(tabLvl + "\t");
		
		return tmp;
	}
	
	/**
	 * Calcule la chaîne de caractère représentant le parcours infixe depuis le noeud courant.
	 * @return La chaîne de caractère représentant le parcours infixe depuis le noeud courant.
	 */
	public String prefixeToString() {
		return prefixeToString("");
	}
	
	/**
	 * Calcule la chaîne de caractère représentant le parcours infixe depuis le noeud courant.
	 * @param niveauTabulation Le niveau de tabulation actuel.
	 * @return La chaîne de caractère représentant le parcours infixe depuis le noeud courant.
	 */
	private String prefixeToString(String niveauTabulation) { 
		String tmp = niveauTabulation;
		
		tmp += "\t Clé : " + getCle().toString() + " - Hauteur : " + getHauteur() + ",\n";
		
		if (getFilsGauche() != null)
			tmp += "FG -> " + getFilsGauche().prefixeToString(niveauTabulation + "\t");
		
		if (getFilsDroit() != null)
			tmp += "FD -> " + getFilsDroit().prefixeToString(niveauTabulation + "\t");
		
		return tmp;
	}
}
