package tas_priorite_min_2;

import java.util.List;

import interfaces.ICle;

public class TasMinTab implements ITasMin {

	private ICle [] tas;
	
	public TasMinTab() {
		tas = new ICle[0];
	}
	
	/**
	 * Inverse la position de 2 elements i et j.
	 * @param i
	 * @param j
	 */
	public void swap(int i, int j) {
	        ICle tmp = tas[j];
	        tas[j] = tas[i];
	        tas[i] = tmp;
	}
	
	@Override
	public boolean supprMin() {
		
		if(tas.length == 0) { // Cas ou le tas est vide.
			System.out.println("Tas vide, suppression impossible.");
			return false;

		}else if(tas.length == 1) { // Cas ou le tas possede 1 element à supprimer.			
			tas = new ICle[0];
			return true;
			
		}
		// Cas ou le tas possede plus de 1 element.
		
		int s = size()-1;
    	ICle c = tas[s];
    	
    	tas[0] = c;
    	
    	ICle [] tasTmp = new ICle[tas.length - 1]; 
		
		for(int i=0; i < tasTmp.length; i++)
			tasTmp[i] = tas[i];
			
		tas = tasTmp;		
		
		// on verifie que le tas est toujour minimal
		verifMin(0);
		
		return true;
	}
	
	
	/**
	 * Verifie que le tas est toujours minimal, et le rend minimal si cela est neccessaire.
	 * @param noeud
	 */
    public void verifMin(int noeud) {
	
        int fGauche = idFilsGauche(noeud);
        int fDroit = idFilsDroit(noeud);
        
        int idMin = -1;
        int nbElems = tas.length-1;
        
        if (fDroit <= nbElems && fDroit > 0 && tas[fDroit].inf(tas[noeud])) { // Verifie si le fils droit n'est pas plus grand que le pere.
            idMin = fDroit;
        } else if (fGauche <= nbElems && fGauche > 0 && tas[fGauche].inf(tas[noeud])) { //Verifie si le fils gauche n'est pas plus grand que le pere.
            idMin = fGauche;
        } else {
            idMin = noeud;
        }

        if (idMin != noeud) {
            swap(noeud, idMin);
            verifMin(idMin); // On continue la descente
        }
        
    }
	
	/**
	 * Renvoie l'indice du fils gauche du noeud d'indice "noeud", -1 si ce noeud ne possède pas de fils gauche.
	 * @param noeud
	 * @return indice du fils gauche de "noeud".
	 */
	public int idFilsGauche(int noeud) {
		
		if((2*noeud + 1) > tas.length)
			return -1;
		else {
			return (2*noeud + 1);
		}
	
	}
	
	/**
	 * Renvoie l'indice du fils droit du noeud d'indice "noeud", -1 si ce noeud ne possède pas de fils droit.
	 * @param noeud
	 * @return indice du fils droit de "noeud".
	 */
	public int idFilsDroit(int noeud) {
		
		if((2*noeud + 2) > tas.length)
			return -1;
		else {
			return (2*noeud + 2);
		}
	
	}

	/**
	 * Renvoie la taille du tas.
	 * @return
	 */
	public int size() {
		return tas.length;
	}
	
	@Override
	public boolean ajout(ICle c) {
		
		ICle [] tasTmp = new ICle[tas.length + 1]; 
		
		for(int i=0; i < tas.length; i++)
			tasTmp[i] = tas[i];
		
		tasTmp[tas.length] = c; // Cle ajoute en fin de tableau (en feuille)
			
		tas = tasTmp;
		
		// Reagencement des cles par rapport a la nouvelle cle inseree.
		
		int lastId = tas.length - 1;
		int pere = idPere(lastId); // indice du pere de la derniere cle ajoutee
		
		while(pere != lastId && tas[lastId].inf(tas[pere])) {
			swap(lastId, pere);
			lastId = pere;
			pere = idPere(lastId);
		}
				
		return true;
	}

	
	/**
	 * Renvoie l'indice dans le tas du noeud pere, d'un noeud. 
	 * @param noeud dont on cherche le pere.
	 * @return id du noeud pere
	 */
	public int idPere(int noeud) {
        if (noeud % 2 == 1) {
            return noeud / 2;
        }

        return (noeud - 1) / 2;
	}
	
	@Override
	public boolean consIter(List<ICle> elems) {
		
		if(elems.size() == 0) {
			return false;
		}else {
			for(ICle c : elems)
				ajout(c);
		}
		return true;
	}

	@Override
	public boolean union(ITasMin t2) {
		
		if(t2 instanceof TasMinTab) {
			TasMinTab tas2 = (TasMinTab) t2;
			ICle [] tabTas2 = tas2.getTas();
			for(int i =0; i < tabTas2.length; i++)
				ajout(tabTas2[i]);
			
			return true;
		}
		else {
			System.out.println("Union impossible.");
			return false;
		}	
		
	}
	
	/**
	 * Renvoie le tableau.
	 * @return tas
	 */
	public ICle[] getTas(){
		return tas;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		for(int i = 0; i<tas.length; i++) {
			str.append(tas[i]);
			
			if(i!=tas.length-1)
				str.append(" ");
		}
		
		return str.toString();
	}

}
