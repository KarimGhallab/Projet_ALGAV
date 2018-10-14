package arbre_de_recherche_5;

/**
 * Classe représentant un noeud pour nos arbres.
 *
 */
public class Noeud {
	/** La clé présente dans le noeud */
    private int cle;
    /** La hauteur du noeud */
    private int hauteur;
    
    /** Le fils gauche du noeud */
    private Noeud filsGauche;
    /** Le fils droit du noeud */
    private Noeud filsDroit; 
  
    /**
     * Constructeur d'un noeud à partir d'une clé
     * @param cle La clé du noeud.
     */
    Noeud(int cle) { 
        this.cle = cle; 
        hauteur = 1;
        filsGauche = null;
        filsDroit = null;
    }

    /**
     * Getteur sur la clé du noeud.
     * @return La clé du noeud.
     */
	public int getCle() {
		return cle;
	}

	/**
	 * Setteur sur la clé du noeud.
	 * @param cle La nouvelle clé du noeud.
	 */
	public void setCle(int cle) {
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
	public Noeud getFilsGauche() {
		return filsGauche;
	}

	/**
	 * Setteur sur le fils gauche du noeud.
	 * @param filsGauche Le nouveau fils gauche du noeud.
	 */
	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	/**
	 * Getteur sur le fils droit du noeud.
	 * @return Le fils droit du noeud
	 */
	public Noeud getFilsDroit() {
		return filsDroit;
	}

	/**
	 * Setteur sur le fils droit  du noeud.
	 * @param filsDroit Le nouveau fils droit du noeud.
	 */
	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	} 
}
