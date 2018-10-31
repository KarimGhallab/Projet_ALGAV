package tas_priorite_min_2;

import interfaces.ICle;

public class Noeud {

	private ICle noeud;
	private Noeud filsGauche, filsDroit;
	
	public Noeud(ICle n) {
		noeud = n;
		filsGauche = new Noeud();
		filsDroit = new Noeud();
	}
	
	public Noeud() {
		noeud = null;
		filsGauche = null;
		filsDroit = null;
	}
	
	public ICle getNoeud() {
		return noeud;
	}
	
	public boolean add(ICle c) {
		
		if(noeud == null) {
			noeud = c;
		}else if(filsGauche == null && filsDroit == null) {
			filsGauche.add(c);
		}else if(filsGauche != null && filsDroit == null) {
			filsDroit.add(c);
		}else if(filsGauche == null && filsDroit != null) {
			filsGauche.add(c);
		}
		
		return false;
	}
	
}
