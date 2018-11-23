package tas_priorite_min_2;

import java.util.List;

import interfaces.ICle;
import interfaces.ITasMin;

public class TasMinArbre implements ITasMin {

	private Noeud racine;
	private Noeud prochaineInsertion;
	
	public TasMinArbre() {
		racine = null;
		prochaineInsertion = racine;
	}
	
	public TasMinArbre(ICle n) {
		racine = new Noeud(n, null, false, false);		// La racine n'a pas de pêre est n'est ni un fils gauche, ni un fils droit
		prochaineInsertion = racine.getFilsGauche();
	}	
		
	
	@Override
	public boolean supprMin() {
		return false;
	}

	@Override
	public boolean ajout(ICle c) {
		if (prochaineInsertion == null) {		// Insertion à la racine
			racine = new Noeud(c, null, false, false);
			prochaineInsertion = racine.getFilsGauche();
		}
		else
			prochaineInsertion = prochaineInsertion.add(c);
		
		return false;
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
