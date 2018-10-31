package tas_priorite_min_2;

import java.util.List;

import interfaces.ICle;
import interfaces.ITasMin;

public class TasMinArbre implements ITasMin {

	private Noeud racine;
	
	public TasMinArbre(ICle n) {
		racine = new Noeud(n);
	}	
		
	
	@Override
	public boolean supprMin() {
		return false;
	}

	@Override
	public boolean ajout(ICle c) {
		
		if(racine == null)
			racine = new Noeud(c);
		
		return racine.add(c);
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

	
	
}
