package arbre_de_recherche_5;

public class Noeud { 
    private int cle, hauteur; 
    private Noeud filsGauche, filsDroit; 
  
    Noeud(int cle) { 
        this.cle = cle; 
        hauteur = 1;
        filsGauche = null;
        filsDroit = null;
    }

	public int getCle() {
		return cle;
	}

	public void setCle(int cle) {
		this.cle = cle;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public Noeud getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(Noeud filsGauche) {
		this.filsGauche = filsGauche;
	}

	public Noeud getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(Noeud filsDroit) {
		this.filsDroit = filsDroit;
	} 
	  
}
