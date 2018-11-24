package tas_priorite_min_2;

import java.util.List;

import interfaces.ICle;
import interfaces.ITasMin;

public class TasMinArbre implements ITasMin {

	/** La racine de l'arbre. */
	private Noeud racine;
	
	/** Le noeud sur lequel se fera la prochaine insertion. */
	private Noeud prochaineInsertion;
	
	/**
	 * Constructeur d'un tas min vide.
	 */
	public TasMinArbre() {
		racine = null;
		prochaineInsertion = racine;
	}
	
	/**
	 * Constructeur d'un tas min avec une clé.
	 * @param n La clé à insérer.
	 */
	public TasMinArbre(ICle n) {
		racine = new Noeud(n, null, false, false);		// La racine n'a pas de pêre est n'est ni un fils gauche, ni un fils droit
		prochaineInsertion = racine.getFilsGauche();
	}
	
	/** Retourne la racine de l'arbre sans la supprimer */
	public Noeud getRacine() {
		return racine;
	}
		
	@Override
	public boolean supprMin() {
		return false;
	}

	@Override
	public boolean ajout(ICle c) {
		// Insertion à la racine
		if (prochaineInsertion == null) {
			racine = new Noeud(c, null, false, false);
			prochaineInsertion = racine.getFilsGauche();
		}
		else {
			// Ajout de la clé dans le noeud déstiné à la prochaine insertion
			prochaineInsertion.add(c);
			
			Noeud ancienneProchaineInsertion = prochaineInsertion;
			
			// On calcule le noeud sur lequel devra se faire la prochaine insertion.
			prochaineInsertion = prochaineInsertion(ancienneProchaineInsertion);
			
			// Si le noeud sur lequel on vient de faire l'insertion etait l'extrémité droite de l'arbre,
			// alors ce n'est plus l'extrémité.
			if (ancienneProchaineInsertion.estExtremiteDroite())
				ancienneProchaineInsertion.setEstExtremiteDroite(false);
			
			// Il faut remonter la clé
			remonterCle(ancienneProchaineInsertion);
		}
		
		return false;
	}
	
	/**
	 * Calcule le prochain noeud sur lequel se fera la prochaine insertion.
	 * @param courant Le noeud sur lequel on vient d'insérer.
	 * @return Le noeud sur lequel se fera la prochaine insertion.
	 */
	private Noeud prochaineInsertion(Noeud courant) {
		if (courant.estFilsGauche())
			return courant.getPere().getFilsDroit();
		else if (courant.estExtremiteDroite())
			return prochaineInsertionDepuisExtremite();
		else
			return prochaineInsertionDepuisFilsDroit(courant);
	}
	
	
	/**
	 * Calcule le prochain noeud sur lequel doit se faire la prochaine insertion en cas d'insertion dans un fils droit.
	 * Le fils droit ne doit pas être l'extrémité de l'arbre.
	 * 
	 * Le noeud renvoyé sera le noeud le plus à gauche par rapport au premier fils gauche à la remontée.
	 * @param courant Le noeud sur lequel on vient d'insérer.
	 * @return Le noeud sur lequel se fera la prochaine insertion.
	 */
	private Noeud prochaineInsertionDepuisFilsDroit(Noeud courant) {
		// On est sur un fils gauche.
		// La prochaine insertion se fait sur le fils droit du pêre puis tout à gauche
		if(courant.estFilsGauche())
			return trouverExtremiteGauche(courant.getPere().getFilsDroit()); 

		// On est à la racine.
		// La prochaine insertion se fait sur le chemin le plus à gauche depuis le fils droit
		else  if (courant.getPere() == null)
			return trouverExtremiteGauche(courant.getFilsDroit());
		
		// On est sur un fils droit.
		// Il faut continuer à monter.
		else
			return prochaineInsertionDepuisFilsDroit(courant.getPere());
	}
	
	/**
	 * Calcule le prochain noeud sur lequel doit se faire la prochaine insertion en cas d'insertion à l'extémité de l'arbre.
	 * 
	 * Le noeud renvoyé sera le noeud le plus à gauche par rapport à la racine.
	 * @return Le noeud sur lequel se fera la prochaine insertion.
	 */
	private Noeud prochaineInsertionDepuisExtremite() {	
		// On cherche l'extremité gauche de la racine
		return trouverExtremiteGauche(racine);
	}
	
	/**
	 * Recherche le noeud le plus à gauche depuis le noeud courant.
	 * @param courant Le noeud sur lequel on vient d'insérer.
	 * @return Le noeud le plus à gauche depuis le noeud courant.
	 */
	private Noeud trouverExtremiteGauche(Noeud courant) {
		while( true) {
			if (courant.getFilsGauche() == null)
				break;
			else
				courant = courant.getFilsGauche();
		}
		return courant;
	}
	
	/**
	 * Remonte la clé dans l'arbre afin de garder une structure de tas min.
	 * @param courant Le noeud depuis lequel il faut faire la remontée.
	 */
	private void remonterCle(Noeud courant) {
		if (courant.getPere() != null) {
			if (courant.getNoeud().inf(courant.getPere().getNoeud())) {			// La valeur de la clé courante est inférieure à celle du père
				ICle tmp = courant.getNoeud();
				courant.setNoeud(courant.getPere().getNoeud());
				courant.getPere().setNoeud(tmp);
				remonterCle(courant.getPere());
			}
		}
	}

	@Override
	public boolean consIter(List<ICle> elems) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean union(ITasMin t2) {
		// TODO Auto-generated method stub
		return false;
	}

	public String infixeToString() {
		return racine.infixeToString("\t");
	}
}
