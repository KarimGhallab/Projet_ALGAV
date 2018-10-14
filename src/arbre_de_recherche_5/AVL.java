package arbre_de_recherche_5;

/**
 * Classe AVL représentant un arbre de recherche équilibré de type AVL (Adelson–Velsky, Landis).
 */
public class AVL implements IAVL {
	
	/** La racine de l'arbre. */
	private Noeud racine; 

	@Override
	public Noeud getRacine() {
		return racine;
	}
		
	@Override
	public void setRacine(Noeud racine) {
		this.racine = racine;
	}

	@Override
	public int getHauteur() {
		return getHauteur(getRacine());
	} 
	
	private int getHauteur(Noeud n) {
		if (n != null)
			return n.getHauteur();
		else
			return 0;
	}
		
	@Override
	public String infixeToString() {
		return infixeToString(getRacine());
	}
		
	/**
	 * Affichage du parcours infixe depuis une racine.
	 * @param racine La racine de l'arbre.
	 * @return La chaine de caractère correpondant à la suite des clés depuis la racine
	 * selon un parcours infixe.
	 */
	private String infixeToString(Noeud racine) { 
		if (racine != null)
			return racine.infixeToString();
		return "";
	}
	
	@Override
	public String prefixeToString() {
		return prefixeToString(getRacine());
	}
		
	/**
	 * Affichage du parcours prefixe depuis une racine.
	 * @param racine La racine de l'arbre.
	 * @return La chaine de caractère correpondant à la suite des clés depuis la racine
	 * selon un parcours prefixe.
	 */
	private String prefixeToString(Noeud racine) { 
		if (racine != null)
			return racine.prefixeToString();
		return "";
	}

	/**
	 * Calcule et renvoie le maximum parmis deux entiers. 
	 * @param a Le premier entier à comparer.
	 * @param b Le second entier à comparer.
	 * @return Le maximum des deux entiers.
	 */
	private int max(int a, int b) { 
		return (a > b) ? a : b; 
	}
		
	/**
	 * Calcule le niveau d'equilibrage d'un noeud. 
	 * @param racine La racine pour laquelle on souhaite calculer son niveau d'equilibrage
	 * @return Le niveau d'equilibrage d'un noeud
	 */
	private int getEquilibrage(Noeud racine) { 
		if (racine == null) 
			return 0; 

		return getHauteur(racine.getFilsGauche()) - getHauteur(racine.getFilsDroit()); 
	} 
	
	@Override
	public void inserer(int cle) {
		setRacine(inserer(getRacine(), cle));	
	}
		
	/**
	 * Insère une clé dans la racine
	 * @param racine La racine dans laquelle on souhaite effectuer l'insertion.
	 * @param cle La clé à insérer.
	 * @return La racine avec la clé insérée.
	 */
	private Noeud inserer(Noeud racine, int cle) { 		// Voir Cours 2 slide 15-16
		/*// On effectue l'insertion
		if (racine == null) 
			return (new Noeud(cle)); 
		
		int racineCle = racine.getCle();
		if (cle < racineCle)		// Il faut insérer à gauche
			racine.setFilsGauche(inserer(racine.getFilsGauche(), cle));
		else if (cle > racineCle) 		// Il faut insérer à droite
			racine.setFilsDroit(inserer(racine.getFilsDroit(), cle)); 
		else 							// La racine est déjà présente dans l'arbre 
			return racine; 

		// On met à jour la hauteur de la racine
		racine.setHauteur(max(getHauteur(racine.getFilsGauche()), getHauteur(racine.getFilsDroit())) + 1); 
		
		int equilibrage = getEquilibrage(racine); 

		// Si notre racine est déséquilibrée, il faut procéder à une rotation pour l'equilibrer
		if (equilibrage > 1) {
			// L'arbre contient plus de noeud à gauche
			if (cle < racine.getFilsGauche().getCle())
				return rotationDroite(racine); 										// Rotation Droite
			else if (cle > racine.getFilsGauche().getCle()) {
				racine.setFilsGauche(rotationGauche(racine.getFilsGauche()));		// Rotation gauche droite 
				return rotationDroite(racine);
			}
		}
		else if (equilibrage < -1)
		{
			// L'arbre contient plus de noeud à droite
			if (cle > racine.getFilsDroit().getCle())
				return rotationGauche(racine); 										// Rotation Gauche
			else if (cle < racine.getFilsDroit().getCle()) {
				racine.setFilsDroit(rotationDroite(racine.getFilsDroit()));			// Rotation droite gauche 
				return rotationGauche(racine);
			}
		}
		return racine;*/
		
		if (racine == null)
			return new Noeud(cle);
        if (cle < racine.getCle())
        	racine.setFilsGauche(inserer(racine.getFilsGauche(), cle));
        else if (cle > racine.getCle())
        	racine.setFilsDroit(inserer(racine.getFilsDroit(), cle));
        else 
            return racine;
        racine.setHauteur( 1 + max(getHauteur(racine.getFilsGauche()), getHauteur(racine.getFilsDroit())));
        return equilibrage(racine);
	}
		
	/**
	 * Effectue un réequilibrage depuis un noeud racine
	 * @param racine Le noeud depuis lequelle on souhaire rééquilibrer.
	 */
	private Noeud equilibrage(Noeud racine) {
		if (getEquilibrage(racine) < -1) {
            if (getEquilibrage(racine.getFilsDroit()) > 0) {
                racine.setFilsDroit(rotationDroite(racine.getFilsDroit()));
            }
            racine = rotationGauche(racine);
        }
        else if (getEquilibrage(racine) > 1) {
            if (getEquilibrage(racine.getFilsGauche()) < 0) {
                racine.setFilsGauche(rotationGauche(racine.getFilsGauche()));
            }
            racine = rotationDroite(racine);
        }
        return racine;
	}
	
	/**
	 * Effectue une rotation droite avec l'arbre dont la racine est le noeud passé en paramètre.
	 * @param q La racine à partir de laquelle il faut effectuer la rotation.
	 * @return La nouvelle racine après la rotation.
	 */
	private Noeud rotationDroite(Noeud q) { 
		Noeud p = q.getFilsGauche();
		
		// La rotation !
		q.setFilsGauche(p.getFilsDroit());		// V
        p.setFilsDroit(q);
        
        // Il faut recalculer les hauteurs
        q.setHauteur(1 + max(getHauteur(q.getFilsGauche()), getHauteur(q.getFilsDroit())));
        p.setHauteur(1 + max(getHauteur(p.getFilsGauche()), getHauteur(p.getFilsDroit())));
        
        return p;
	} 

	/**
	 * Effectue une rotation gauche avec l'arbre dont la racine est le noeud passé en paramètre.
	 * @param p La racine à partir de laquelle il faut effectuer la rotation.
	 * @return La nouvelle racine après la rotation.
	 */ 
	private Noeud rotationGauche(Noeud p) { 
		Noeud q = p.getFilsDroit();
		
		// La rotation
		p.setFilsDroit(q.getFilsGauche());		// V
		q.setFilsGauche(p);
		
		// Il faut recalculer les hauteurs
        p.setHauteur(1 + max(getHauteur(p.getFilsGauche()), getHauteur(p.getFilsDroit())));
        q.setHauteur(1 + max(getHauteur(q.getFilsGauche()), getHauteur(q.getFilsDroit())));
        
        return q;
	}
	
	@Override
	public Noeud rechercher(int cle) throws Exception {
		return rechercher(getRacine(), cle);
	}
		
	/**
	 * Recherche une clé dans la racine passée en argument.
	 * @param racine Le noeud racine dans lequel on souhaite effectuer la recherche.
	 * @param cle La clé à recherchée.
	 * @return Le noeud contenant la clé.
	 * @throws Exception La clé n'est pas présente ni dans la racine, ni dans ses fils.
	 */
	private Noeud rechercher(Noeud racine, int cle) throws Exception {
		if (racine != null) {
			if (cle == racine.getCle())
				return racine;
			else if (cle < racine.getCle())
				return rechercher(racine.getFilsGauche(), cle);
			else
				return rechercher(racine.getFilsDroit(), cle);
		}
		else
			throw new Exception("La clé " + cle + " n'est pas présente dans l'arbre");
	}
}
