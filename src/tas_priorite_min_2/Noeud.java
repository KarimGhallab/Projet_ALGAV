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
	 * Ajoute une clé au noeud.
	 * @param c La clé à ajouter.
	 * @return Le noeud sur lequel se fera la prochaine insertion.
	 */
	public Noeud add(ICle c) {
		// On fait l'insertion
		if(noeud == null) {
			noeud = c;
			filsGauche = new Noeud(this, true, false);
			
			// On etait sur l'extremité de l'arbre
			if (estExtremiteDroite) {
				filsDroit = new Noeud(this, false, true);		// La nouvelle estremité est son fils droit

				Noeud res = prochaineInsertion();
				estExtremiteDroite = false;
				
				return res;
			}
			else
				filsDroit = new Noeud(this, false, false);
			return prochaineInsertion();
		}
		else if(filsGauche.getNoeud() == null && filsDroit.getNoeud() == null) {
			return filsGauche.add(c);
		}
		else if(filsGauche.getNoeud() != null && filsDroit.getNoeud() == null) {
			return filsDroit.add(c);
		}
		else if(filsGauche.getNoeud() == null && filsDroit.getNoeud() != null) {
			return filsGauche.add(c);
		}
		else
			return filsGauche.add(c);
	}
	
	/**
	 * getteur sur l'attribut fils gauche du noeud.
	 * @return true si le noeud est un fils gauche, false sinon.
	 */
	public boolean estFilsGauche() {
		return estFilsGauche;
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
	
	/**
	 * Calcule le prochain noeud sur lequel se fera la prochaine insertion.
	 * @return Le noeud sur lequel se fera la prochaine insertion.
	 */
	private Noeud prochaineInsertion() {
		if (estFilsGauche)
			return pere.filsDroit;
		else if (estExtremiteDroite)
			return prochaineInsertionDepuisExtremite();
		else
			return prochaineInsertionDepuisFilsDroit();
	}
	
	
	/**
	 * Calcule le prochain noeud sur lequel doit se faire la prochaine insertion en cas d'insertion dans un fils droit.
	 * Le fils droit ne doit pas être l'extrémité de l'arbre.
	 * 
	 * Le noeud renvoyé sera le noeud le plus à gauche par rapport au fils droit de la racine.
	 * @return Le noeud sur lequel se fera la prochaine insertion.
	 */
	private Noeud prochaineInsertionDepuisFilsDroit() {
		// On est sur un fils gauche.
		// La prochaine insertion se fait sur le fils droit du pêre puis tout à gauche
		if(estFilsGauche)
			return pere.filsDroit.trouverExtremiteGauche(); 

		// On est à la racine.
		// La prochaine insertion se fait sur le chemin le plus à gauche depuis le fils droit
		else  if (pere == null)
			return filsDroit.prochaineInsertionDepuisFilsDroit();
		
		// On est sur un fils droit.
		// Il faut continuer à monter.
		else
			return pere.prochaineInsertionDepuisFilsDroit();
	}
	
	/**
	 * Calcule le prochain noeud sur lequel doit se faire la prochaine insertion en cas d'insertion à l'extémité de l'arbre.
	 * 
	 * Le noeud renvoyé sera le noeud le plus à gauche par rapport à la racine.
	 * @return Le noeud sur lequel se fera la prochaine insertion.
	 */
	private Noeud prochaineInsertionDepuisExtremite() {
		// On remonte d'abord jusqu'à la racine
		Noeud racine = trouverRacine();
		
		// Puis on cherche l'extremité gauche de la racine
		return racine.trouverExtremiteGauche();
	}
	
	/**
	 * Recherche la racine de l'arbre depuis le noeud courant.
	 * @return La racine de l'arbre.
	 */
	private Noeud trouverRacine() {
		if (pere == null)
			return this;
		Noeud courant = this;
		while (true) {
			if (courant.pere == null)
				break;
			else
				courant = courant.pere;
		}
		return courant;
	}
	
	/**
	 * Recherche le noeud le plus à gauche depuis le noeud courant.
	 * @return Le noeud le plus à gauche depuis le noeud courant.
	 */
	private Noeud trouverExtremiteGauche() {
		Noeud courant = this;
		while( true) {
			if (courant.filsGauche == null)
				break;
			else
				courant = courant.filsGauche;
		}
		return courant;
	}
}
