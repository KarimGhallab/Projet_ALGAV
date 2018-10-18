package arbre_de_recherche_5;

import interfaces.ICle;

/**
 * Classe représentant un noeud pour nos arbres.
 * @param <C> Le type de la clé contenue dans le noeud
 */
public class Noeud<C extends ICle> {
	/** La clé présente dans le noeud */
    private C cle;
    /** La hauteur du noeud */
    private int hauteur;
    
    /** Le fils gauche du noeud */
    private Noeud<C> filsGauche;
    /** Le fils droit du noeud */
    private Noeud<C> filsDroit; 
  
    /**
     * Constructeur d'un noeud à partir d'une clé
     * @param cle La clé du noeud.
     */
    Noeud(C cle) { 
        this.cle = cle; 
        hauteur = 1;
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
	 * @return La chaine de caractère correpondant à la suite des clé depuis la racine
	 * selon un parcours infixe.
	 */
	public String infixeToString() { 
		String tmp = "";
		if (getFilsGauche() != null)
			tmp += getFilsGauche().infixeToString();
		
		tmp += "\t Clé : " + getCle().toString() + " - Hauteur : " + getHauteur() + ",\n";
		
		if (getFilsDroit() != null)
			tmp += getFilsDroit().infixeToString();
		
		return tmp;
	}
	
	public String prefixeToString() {
		return prefixeToString("");
	}
	
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
