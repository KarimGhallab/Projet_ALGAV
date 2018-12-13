package Ex3_files_binomiales;

import java.util.LinkedList;

import interfaces.ICle;
import interfaces.IFileBinomiale;
import interfaces.ITournoiBinomial;

public class TournoiBinomial implements ITournoiBinomial{
	/** Ensemble des tournois binomiaux fils, du tournoi binomial */
	private LinkedList<ITournoiBinomial> fils;
	/** Clé stockée dans le noeud */
	private ICle cle;
	/** Degre a la racine (TBk) */
	private int degre;  
	/** Nombre de cle inserrer dans le tournoi */
	private int taille;
	
	/**
	 * Constructeur d'un tournoi binomial, stockant une clé en noeud.
	 * @param c clé à stocker dans le noeud
	 */
	public TournoiBinomial(ICle c) {
		fils = new LinkedList<>();
		cle = c;
		degre = 0; // Initialement on a un TB0, ne possèdant pas de fils, donc de degré 0.
		taille = 1;
	}
	
	/**
	 * Constructeur d'un tournoi binomial vide.
	 */
	public TournoiBinomial() {
		fils = new LinkedList<>();
		degre = 0; // Initialement le tournoi est vide, donc de degre 0.
		taille = 0;
	}
	
	@Override
	public IFileBinomiale decapite() {
		IFileBinomiale result = new FileBinomiale(fils);
		return result;
	}

	@Override
	public IFileBinomiale file() {
		FileBinomiale result = new FileBinomiale(this);
		return result;
	}

	@Override
	public ITournoiBinomial union(ITournoiBinomial t2) {
		TournoiBinomial tmp = (TournoiBinomial)t2; // On cast le ITournoiBinomial en paramètre, en TournoiBinomial, afin d'accèder directement à ses attributs.
		TournoiBinomial copieCourant = this; // On fait une copie du tournoi courant, afin de pouvoir le retourner.
		
		if(copieCourant.cle.inf(tmp.cle)) { //Si la clé stockée en racine du tournoi courant, est inférieur à celle du tournoi t2:
			copieCourant.fils.add(tmp); // On ajoute le tournoi t2 au fils du tournoi courant.
			copieCourant.degre++; //L'union de 2 tournoi de même taille, crée un tournoi de taille, taille+1.
			return copieCourant; 
		}else { //Sinon c'est la clé en racine du tournoi t2 qui est inférieur alors :
			tmp.fils.add(copieCourant); // On ajoute le tournoi courant au fils du tournoi t2.
			tmp.degre++; //L'union de 2 tournoi de même taille, crée un tournoi de taille, taille+1.
			return tmp;
		}
	}
	
	/**
	 * Affiche un tournoi binomial : (racine , puis fils en partant vers la gauche), pour chaque noeud.
	 */
	public void print() {
		System.out.println(toString());
	}
	

	public String toString() {
		return toString("", "R");
	}
	
	public String toString(String tabLvl, String role) {
		String tmp = "";
		int nbNoeud = fils.size();
		
		/* Affichage de la première moitié des fils */
		int i = 0;
		if(nbNoeud > 0) {
			for (i = 0; i < nbNoeud/2; i++)
				tmp += ((TournoiBinomial)fils.get(i)).toString(tabLvl+"\t", "F"+(i+1));
		}
		
		if (cle != null) {
			tmp += tabLvl+role+" "+cle.toString()+"\n";
		}
		
		/* Affichage de la seconde moitié des fils */
		if(nbNoeud > 0) {
			for (int j=i; j < nbNoeud; j++)
				tmp+= ((TournoiBinomial)fils.get(j)).toString(tabLvl+"\t", "F"+(j+1));
		}
		
		return tmp;
	}
	/**
	 * Renvoie le nombre d'elements ajoute au tournoi.
	 * @return le nombre d'elements ajoute au tournoi.
	 */
	public int getTaille() {
		return fils.size()+1;
	}

	@Override
	public boolean estVide() {		
		return cle == null;
	}

	@Override
	public int degre() {
		return degre;
	}
	
	@Override
	public ICle getCle() {
		return cle;
	}
}