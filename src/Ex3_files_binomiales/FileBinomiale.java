package Ex3_files_binomiales;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import interfaces.ICle;
import interfaces.IFileBinomiale;
import interfaces.ITournoiBinomial;

public class FileBinomiale implements IFileBinomiale{
	
	/** Degre de la file binomiale */
	private int taille;
	/** Nombre de tournois binomiaux actuellement dans la file binomiale */
	private int nbElmt; 
	/** Ensemble des tournois binomiaux composant la file binomiale */
	private LinkedList<ITournoiBinomial> tas;
	/** On stockera à chaque ajout, l'éventuel nouveau tournoi binomiale de plus petite clé, de la file binomiale */
	private ITournoiBinomial tournoiMin;
	
	/**
	 * Constructeur d'une file binomiale avec une taille fixée.
	 * @param n la taille de la file.
	 */
	public FileBinomiale(int n) {
		// Le nombre d'éléments d'une file, est le nombre de bits à 1 dans la décomposition binaire de sa taille.
		// FB5 = <TB4, TB1> on a 5 = 1001, avec 2 bits a 1, donc 2 éléments. 
		nbElmt = Integer.bitCount(n); //bitCount : nombres de bits à 1.  
		taille = n; 
		tas = new LinkedList<ITournoiBinomial>();
	}
	
	/**
	 * Constructeur d'une file binomiale, en lui fournissant une forêt (liste) de tournois.
	 * @param l la liste de tournois composant la file.
	 */
	public FileBinomiale(LinkedList<ITournoiBinomial> l) {
		nbElmt = l.size(); //Le nombre d'éléments de la file binomiale, c'est le nombre d'éléments de la liste de tournois binomiale.
		tas = l; 
	}
	
	/**
	 * Constructeur qui cree une file binomiale avec 1 seul élément.
	 * @param init seul tournoi binomial de la file.
	 */
	public FileBinomiale(ITournoiBinomial init) {
		nbElmt = 1;
		tas = new LinkedList<ITournoiBinomial>();
		tournoiMin = init; //La plus petit tournoi de la file, est l'unique tournoi la composant.
		tas.addFirst(init);
		// La taille de la file est egal a 2^(degre de l'unique tournoi le composant)
		// exemple : FB8 = <TB3> 8 = 2^3
		taille = (int) Math.pow(2,init.degre());  
	}
	
	@Override
	public IFileBinomiale add(ITournoiBinomial t){
		return unionFile(this, t.file());
	}

	@Override
	public TournoiBinomial minDeg() {
		return (TournoiBinomial)tas.getFirst(); // On cast car getFirst renvoie un ITournoiBinomial.
	}

	@Override
	public boolean estVide() {
		return tas.isEmpty();
	}

	@Override
	public IFileBinomiale reste() {
		taille -= Math.pow(2, tas.pollFirst().degre()); // On enlève un tournoi, donc on réduit le nombre de nœuds de la file. 
		return this;
	}

	@Override
	public IFileBinomiale ajoutMin(ITournoiBinomial t, IFileBinomiale f) {
		FileBinomiale tmp = (FileBinomiale)f; // Caster l'interface IFileBinomiale en FileBinomiale
		
		//On maintient un pointeur sur le tournoi avec la plus petite racine.
		if(tmp.tournoiMin == null || t.getCle().inf(tmp.tournoiMin.getCle()))
			tmp.tournoiMin = t;
		
		tmp.tas.addFirst(t); // Le tournoi étant le futur tournoi de plus petit degré de la file, on l'ajoute en premier de la file.
		tmp.taille += Math.pow(2,t.degre()); // On ajoute un tournoi à la file, donc on augmente le nombre de nœuds total que contient la file..
		
		return tmp;
	}
	
	/**
	 * Ajoute un tournoi binomiale, en fin de la liste de tournois de la file. 
	 * @param t le tournoi à ajouter à la file.
	 */
	public void ajoutMax(ITournoiBinomial t) {
		tas.addLast(t);
		taille += Math.pow(2,t.degre()); // On ajoute un tournoi à la file, donc on augmente le nombre de nœuds total que contient la file..
	}
	
	@Override
	public IFileBinomiale unionFile(IFileBinomiale f1, IFileBinomiale f2) {
		return UFret(f1, f2, new TournoiBinomial());
	}

	@Override
	public  IFileBinomiale UFret(IFileBinomiale f1, IFileBinomiale f2, ITournoiBinomial retenue) {
		
			if(retenue.estVide()) { //Si lors de l'addition, il n'y a pas de tas en retenue.
				if(f1.estVide()) // Si f1 est vide, on retourne f2.
					return f2;
				
				if(f2.estVide()) // Si f2 est vide, on retourne f1.
					return f1;
				
				// On recupere les 2 tas de degres minimal, des 2 files.
				TournoiBinomial t1 = (TournoiBinomial)f1.minDeg();
				TournoiBinomial t2 = (TournoiBinomial)f2.minDeg();
				
				// Si le tas 1 est plus petit que le tas 2, on ajoute t1 en tete de la file resultant de l'union entre le reste de f1 (privé de t1) et la file f2.
				if(t1.degre() < t2.degre())
					return ajoutMin(t1, unionFile(f1.reste(), f2));
				
				// Si le tas 2 est plus petit que le tas 1, on ajoute t2 en tete de la file resultant de l'union entre le reste de f2 (privé de t2) et la file f1.
				if(t2.degre() < t1.degre())
					return ajoutMin(t2, unionFile(f2.reste(), f1));
				
				// Si les 2 tas sont de meme degre, on fait l'union du reste des file f1 et f2 (privee de leur tas minimal), et la retenue (l'union des 2 tas minimals t1 et t2)
				if(t1.degre() == t2.degre())
					return UFret(f1.reste(), f2.reste(), t1.union(t2));				
			
			}else { // Il y a un tas en retenue
				
				if(f1.estVide()) // Si f1 vide, faire renvoyer l'union de la retenue et de f2.
					return unionFile(retenue.file(), f2);
				if(f2.estVide()) // Si f2 vide, faire renvoyer l'union de la retenue et de f1.
					return unionFile(retenue.file(), f1);
				
				// On recupere les 2 tas de degres minimal, des 2 files.
				ITournoiBinomial t1 = f1.minDeg();
				ITournoiBinomial t2 = f2.minDeg();
						
				// Si le degre de la retenue, est inferieur à celui des deux tas minimal de f1 et f2, on ajoute la retenue,  à l'union des 2 files f1 et f2.
				if (retenue.degre() < t1.degre() && retenue.degre() < t2.degre())
					return ajoutMin(retenue, unionFile(f1 ,f2));
					
				// Si le degre de la retenue, est egal à celui des deux tas minimal de f1 et f2, 
				// on ajoute la retenue, à l'union du reste des 2 files f1 et f2, plus l'union entre leur tas minimal.
				if (retenue.degre() == t1.degre() && retenue.degre() == t2.degre())
					return ajoutMin(retenue, UFret(f1.reste(),f2.reste(),t1.union(t2)));
				
				// Si le tas retenue est de meme degre que le tas minimal de f1 (t1), et que leur degres sont inferieur à celui du tas minimal de f2,
				// on retourne l'union entre le reste de la file 1 (privee de son tas minimal), 
				// f2, et le tas resultant de l'union entre tas minimal de f1 et le tas en retenue
				if (retenue.degre() == t1.degre() && retenue.degre() < t2.degre())
					return UFret (f1.reste(), f2 , t1.union(retenue));
				
				
				// Si le tas retenue est de meme degre que le tas minimal de f2 (t2), et que leur degres sont inferieur à celui du tas minimal de f1,
				// on retourne l'union entre le reste de la file 2 (privee de son tas minimal), 
				// f1, et le tas resultant de l'union entre tas minimal de f2 et le tas en retenue
				if (retenue.degre() == t2.degre() && retenue.degre() < t1.degre())
					return UFret (f2.reste(), f1 , t2.union(retenue));
		}
			
		return null;
	}
	
	/**
	 *Construit une file binomiale, composée des clé de la liste. 
	 * @param l liste de clé pour la contruction de la file.
	 * @f la file qui va être construite avec les clés.
	 */
	public static void constItr(List<ICle> elems, FileBinomiale f) {
		itrAvecCles(elems, f); // Appel de la fonction qui crée les tas constituant la file binomiale.
		
		// Défnition du nouveau tournoi min
		for (ITournoiBinomial tb : f.tas) {
			if ( f.tournoiMin == null || tb.getCle().inf(f.tournoiMin.getCle()) )
				f.tournoiMin = tb;
		}
	}
	
	/**
	 * Construit des tournois binomiaux, qui vont constituer la file binomiale.
	 * @param elems les clés permettant de construire les tournois à ajouter à la file binomiale.
	 * @param f la file binomiale qui va être construite.
	 */
	public static void itrAvecCles(List<ICle> elems, FileBinomiale f) {
		if(elems.size() == 0) { // S'il n'y a pas de clés dans la liste, on s'arrête.
			return; 
		}else { // Sinon, créer autant de tournois binomiaux de degrés 1, que possible, pour les fusionner après.
			Vector<ITournoiBinomial> elemsTournois = new Vector<>();
			int taille = elems.size();			
			
			for(int i=0; i< taille; i = i+2) { // Parcourir le tableau de clés 2 à 2, pour créer les tournois de degré 1.
				if(i == taille-1) { // Si le nombre de clés est impair, la dernière clé restante après le parcour 2 a 2, devient un tournoi binomial de degré 0.
					f.ajoutMax(new TournoiBinomial(elems.get(i))); // On l'ajoute en fin de la liste des tournois de la file binomiale.
				}else { // Sinon, prendre les 2 clé et les fusionner pour avoir un tournoi binomial de degré 1.
					TournoiBinomial t1 = new TournoiBinomial(elems.get(i));
					TournoiBinomial t2 = new TournoiBinomial(elems.get(i+1));
					elemsTournois.add(t1.union(t2));
				}						
			}			
			
			itrAvecTournois(elemsTournois, f); // Tous les tournois sont créés, on commence la fusion des tournois pour l'ajout à la file binomiale..
		}
	}
	
	/**
	 * Fusionne les tournois qui peuvent l'être jusqu'à pouvoir les ajouter à la file binomiale.
	 * @param elems les tournois à ajouter à la file binomiale.
	 * @param f la file binomiale qui va être construite.
	 */
	public static void itrAvecTournois(Vector<ITournoiBinomial> elems, FileBinomiale result) {
		if(elems.size() == 0) { // S'il n'y a pas de tournois à fusionner, on termine.
			return;
		}else if(elems.size() == 1){ // Cas terminal : il reste un seul tournoi, donc il faut l'ajouter à la file pour finir.
			result.ajoutMax(elems.get(0));
		}
		else { // Sinon, il y a des tournois à fusionner :
			Vector<ITournoiBinomial> elemsTournois = new Vector<>();
			int taille = elems.size();
	
			for(int i=0; i< taille; i = i+2) { // On prend les tournois 2 à 2 (ils sont de même taille), on les fusionne pour créer des tournois de degré supérieurs pour l'éventuel tours suivant.
				if(i == taille-1) { //Si le nombre de tournois est impair, il en restera 1 après le parcours 2 à 2:  
					result.ajoutMax(elems.get(i)); // On l'ajoute à la fin de la file binomiale.
					break;
				}else { // Sinon, on fusionne les 2 tournois, pour avoir un tournoi de degré supérieur.
					TournoiBinomial t1 = (TournoiBinomial) elems.get(i);
					TournoiBinomial t2 = (TournoiBinomial) elems.get(i+1);
					
					elemsTournois.add(t1.union(t2));
				}						
			}
			
			itrAvecTournois(elemsTournois, result); // On itère jusqu'à ce qu'il ne reste qu'un tournoi.
		}		
	}
	
	@Override
	public ICle rechercheMin() {
		return tournoiMin.getCle();
	}

	@Override
	public IFileBinomiale supprCleMin() {
		tas.remove(tournoiMin);// On enlève le tournoi stockant la clé minimale de la file.
		return unionFile(this, tournoiMin.decapite()); // Faire l'union entre la file, et la file résultant de la suppression du noeud racine du tournoi (tournoiMin).
	}
	
	@Override
	public int getTaille() {
		return taille;
	}
	
	/**
	 * Renvoie le tournoi avec la cle la plus petite de la file.
	 * @return le tournoi avec la cle la plus petite de la file.
	 */
	public ITournoiBinomial getTournoiMin() {
		return tournoiMin;
	}

	/**
	 * Permet de pointer sur le tournoi de clé minimal de la file.
	 */
	public void getTournoiCleMin() {
		for(ITournoiBinomial t : tas)
			if( ((TournoiBinomial) t).getCle().inf(tournoiMin.getCle()))
				tournoiMin = t;
	}
	
	/**
	 * Affiche les tournois de la file binomiale. 
	 */
	public void print() {
		System.out.println("FILE BINOMIALE : FB"+taille+" : ");
		for(ITournoiBinomial b : tas) {
			System.out.print("TB"+b.degre()+" : ");	
			((TournoiBinomial) b).print();
			System.out.println();
		}
	}

	@Override
	public int getNbTas() {
		return tas.size();
	}

	@Override
	public ITournoiBinomial getTas(int index) {
		return tas.get(index);
	}
}
