package arbre_de_recherche_5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import interfaces.IAVL;
import interfaces.ICle;

/**
 * Classe AVL représentant un arbre de recherche équilibré de type AVL (Adelson–Velsky, Landis).
 * @param <C> @param <C> Le type des clés contenues dans l'arbre
 */
public class AVL<C extends ICle> implements IAVL<C> {
	
	/** La racine de l'arbre. */
	private Noeud<C> racine; 

	@Override
	public Noeud<C> getRacine() {
		return racine;
	}
		
	@Override
	public void setRacine(Noeud<C> racine) {
		this.racine = racine;
	}

	@Override
	public int getHauteur() {
		return getHauteur(getRacine());
	} 
	
	/**
	 * Retourne la hauteur d'un noeud.
	 * @param n Le noeud
	 * @return La hauteur du noeud, 0 si le noeud n'existe pas.
	 */
	private int getHauteur(Noeud<C> n) {
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
	private String infixeToString(Noeud<C> racine) { 
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
	private String prefixeToString(Noeud<C> racine) { 
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
	private int getEquilibrage(Noeud<C> racine) { 
		if (racine == null) 
			return 0; 

		return getHauteur(racine.getFilsGauche()) - getHauteur(racine.getFilsDroit()); 
	} 
	
	@Override
	public void inserer(C cle) {
		setRacine(inserer(getRacine(), cle));	
	}
		
	/**
	 * Insère une clé dans la racine
	 * @param racine La racine dans laquelle on souhaite effectuer l'insertion.
	 * @param cle La clé à insérer.
	 * @return La racine avec la clé insérée.
	 */
	private Noeud<C> inserer(Noeud<C> racine, C cle) { 		// Voir Cours 2 slide 15-16 et 18
		if (racine == null)
			return new Noeud<C>(cle);
		else if (cle.eg(racine.getCle()))				// La clé existe déjà, aucune insertion
			return racine;
		else if (cle.inf(racine.getCle()))					// Insertion à gauche
        	racine.setFilsGauche(inserer(racine.getFilsGauche(), cle));
        else											// Insertion à droite
        	racine.setFilsDroit(inserer(racine.getFilsDroit(), cle));
        
        racine.setHauteur( 1 + max(getHauteur(racine.getFilsGauche()), getHauteur(racine.getFilsDroit())));
        return equilibrage(racine);
	}
		
	/**
	 * Effectue un réequilibrage depuis un noeud racine.
	 * @param racine Le noeud depuis lequelon souhaite rééquilibrer.
	 * @return La nouvelle racine après rééquilibrage.
	 */
	private Noeud<C> equilibrage(Noeud<C> racine) {
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
	private Noeud<C> rotationDroite(Noeud<C> q) { 
		Noeud<C> p = q.getFilsGauche();
		
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
	private Noeud<C> rotationGauche(Noeud<C> p) { 
		Noeud<C> q = p.getFilsDroit();
		
		// La rotation
		p.setFilsDroit(q.getFilsGauche());		// V
		q.setFilsGauche(p);
		
		// Il faut recalculer les hauteurs
        p.setHauteur(1 + max(getHauteur(p.getFilsGauche()), getHauteur(p.getFilsDroit())));
        q.setHauteur(1 + max(getHauteur(q.getFilsGauche()), getHauteur(q.getFilsDroit())));
        
        return q;
	}
	
	@Override
	public Noeud<C> rechercher(C cle) throws Exception {
		return rechercher(getRacine(), cle);
	}
		
	/**
	 * Recherche une clé dans la racine passée en argument.
	 * @param racine Le noeud racine dans lequel on souhaite effectuer la recherche.
	 * @param cle La clé à recherchée.
	 * @return Le noeud contenant la clé.
	 * @throws Exception La clé n'est pas présente ni dans la racine, ni dans ses fils.
	 */
	private Noeud<C> rechercher(Noeud<C> racine, C cle) throws Exception {
		if (racine != null) {
			if (cle.eg(racine.getCle()))
				return racine;
			else if (cle.inf(racine.getCle()))
				return rechercher(racine.getFilsGauche(), cle);
			else
				return rechercher(racine.getFilsDroit(), cle);
		}
		else
			throw new Exception("La clé " + cle + " n'est pas présente dans l'arbre");
	}

	@Override
	public List<C> getCleTriee() {
		List<C> l = new ArrayList<C>();
		l = getCleTriee(racine, l);
		return l;
	}
	
	private List<C> getCleTriee(Noeud<C> racine, List<C> l) {
		if (racine != null) {
			System.out.println("V :" + racine.getCle());
			l.addAll(getCleTriee(racine.getFilsGauche(), l));
			l.add(racine.getCle());
			l.addAll(getCleTriee(racine.getFilsDroit(), l));
			return l;
		}
		else
			return Collections.emptyList();
	}
}