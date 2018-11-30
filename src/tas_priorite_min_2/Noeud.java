package tas_priorite_min_2;

import interfaces.ICle;

public class Noeud {

	/** La clé du noeud. */
	private ICle noeud;
	/** Le père du noeud. Vaut null si le noeud est la racine */
	private Noeud pere;
	/** Le fils gauche du noeud. */
	private Noeud filsGauche;
	/** Le fils droit du noeud */
	private Noeud filsDroit;
	/** Un booléen indiquant si le noeud est un fils gauche. */
	private boolean estFilsGauche;
	/** Un booléen indiquant si le noeud est le noeud le plus à droite. */
	private boolean estExtremiteDroite; 
	
	/**
	 * Constructeur d'un noeud avec une clé.
	 * @param n La clé.
	 * @param pere Le père du noeud.
	 * @param estFilsGauche Booléen précisant si le noeud construit est fils gauche dans l'arbre.
	 * @param estExtremiteDroite Booléen précisant si le noeud construit le fils le plus à droite dans l'arbre.
	 */
	public Noeud(ICle n, Noeud pere, boolean estFilsGauche, boolean estExtremiteDroite) {
		noeud = n;
		this.pere = pere;
		filsGauche = new Noeud(this, true, false);
		filsDroit = new Noeud(this, false, true);
		
		this.estFilsGauche = estFilsGauche;
		this.estExtremiteDroite = estExtremiteDroite;
	}
	
	/**
	 * Constructeur sans clé d'un noeud.
	 * @param pere Le père du noeud.
	 * @param estFilsGauche Booléen précisant si le noeud construit est fils gauche dans l'arbre.
	 * @param estExtremiteDroite Booléen précisant si le noeud construit le fils le plus à droite dans l'arbre.
	 */
	public Noeud(Noeud pere, boolean estFilsGauche, boolean estExtremiteDroite) {
		this.pere = pere;
		noeud = null;
		filsGauche = null;
		filsDroit = null;
		
		this.estFilsGauche = estFilsGauche;
		this.estExtremiteDroite = estExtremiteDroite;
	}
	
	/**
	 * Getteur sur la clé du noeud
	 * @return	La clé du noeud.
	 */
	public ICle getNoeud() {
		return noeud;
	}
	
	/**
	 * Getteur sur le fils gauche du noeud.
	 * @return Le fils gauche du noeud.
	 */
	public Noeud getFilsGauche() {
		return filsGauche;
	}
	
	/**
	 * Getteur sur le fils droit du noeud.
	 * @return Le fils droit du noeud.
	 */
	public Noeud getFilsDroit() {
		return filsDroit;
	}
	
	/**
	 * Getteur sur l'attribut fils gauche du noeud.
	 * @return true si le noeud est un fils gauche, false sinon.
	 */
	public boolean estFilsGauche() {
		return estFilsGauche;
	}
	
	/**
	 * Getteur sur l'attribut fils droit du noeud.
	 * @return true si le noeud est un fils droit, false sinon.
	 */
	public boolean estFilsDroit() {
		return estFilsGauche;
	}
	
	/**
	 * Getteur sur l'attribut fils droit du noeud.
	 * @return true si le noeud est un fils droit, false sinon.
	 */
	public boolean estExtremiteDroite() {
		return estExtremiteDroite;
	}
	
	/**
	 * Getteur sur le père du noeud.
	 * @return Le père du noeud, null si le noeud courant est la racine.
	 */
	public Noeud getPere() {
		return pere;
	}
	
	/**
	 * Setteur sur l'attribut indiquant si le noeud courant est l'extrémité droit de l'arbre.
	 * @param estExtremiteDroite Un booléen indiquant si le noeud courant est l'extrémité droit de l'arbre.
	 */
	public void setEstExtremiteDroite(boolean estExtremiteDroite) {
		this.estExtremiteDroite = estExtremiteDroite;
	}
	
	/**
	 * Setteur sur la clé du noeud.
	 * @param cle La nouvelle clé du noeud.
	 */
	public void setNoeud(ICle cle) {
		noeud = cle;
	}
	
	/**
	 * Setteur du fils gauche d'un noeud.
	 * @param filsGauche Le nouveau fils gauche du noeud.
	 */
	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}
	
	/**
	 * Setteur du fils droit d'un noeud.
	 * @param filsGauche Le nouveau fils droit du noeud.
	 */
	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	}
	
	/**
	 * Ajoute une clé au noeud.
	 * @param c La clé à ajouter.
	 */
	public void add(ICle c) {
		noeud = c;
		filsGauche = new Noeud(this, true, false);
		
		// Le fils droit de l'extrémité est la nouvelle extrémité
		// Si le noeud courant n'est pas l'ectrémité, alors son fils droit ne le sera pas non plus
		filsDroit = new Noeud(this, false, estExtremiteDroite);
	}
	
	/**
	 * Affichage infixe de l'arbre
	 * @param tabLvl le niveau de tabulation courant.
	 * @return La string d'affichage de l'arbre.
	 */
	public String infixeToString(String tabLvl) { 
		String tmp = "";
		if (noeud != null) {
			if (filsGauche.getNoeud() != null)
				tmp += filsGauche.infixeToString(tabLvl+"\t");
			
			if (pere == null)
				tmp += tabLvl+"R : ";
			else if (estFilsGauche)
				tmp += tabLvl+"FG : ";
			else
				tmp += tabLvl+"FD : ";
			tmp += " Clé : " + noeud.toString() + " \n";
			
			if (filsDroit.getNoeud() != null)
				tmp += filsDroit.infixeToString(tabLvl+"\t");
		}
		return tmp;
	}
}
