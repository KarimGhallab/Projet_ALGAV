package arbre_de_recherche_5;

public class AVL { 
		Noeud racine; 

		public Noeud getRacine() {
			return racine;
		}

		public void setRacine(Noeud racine) {
			this.racine = racine;
		}

		/**
		 * Récupère la hauteur d'un arbre.
		 * @param noeud Le noeud racine de l'arbre.
		 * @return La hauteur de l'arbre.
		 */
		int getHauteur(Noeud noeud) {
			if (noeud == null) 
				return 0; 

			return noeud.getHauteur(); 
		} 
		
		/**
		 * Parcours infixe depuis une racine.
		 * @param racine La racine de l'arbre.
		 * @return La chaine de caractère correpondant à la suite des clé de la racine
		 * selon un parcours infixe.
		 */
		String infixeToString(Noeud racine) { 
			if (racine != null) {
				String tmp = "";
				tmp += infixeToString(racine.getFilsGauche());
				tmp += "\t" + racine.getCle() + "-[H] : " + racine.getHauteur() + ",\n";
				tmp += infixeToString(racine.getFilsDroit());
				return tmp;
			}
			return "";
		}

		/**
		 * Calcule et renvoie le maximum parmis deux entiers. 
		 * @param a Le premier entier à comparer.
		 * @param b Le second entier à comparer.
		 * @return Le maximum des deux entiers.
		 */
		int max(int a, int b) { 
			return (a > b) ? a : b; 
		}
		
		/**
		 * Calcule le niveau d'equilibrage d'un noeud. 
		 * @param racine La racine pour laquelle on souhaite calculer son niveau d'equilibrage
		 * @return Le niveau d'equilibrage d'un noeud
		 */
		int getEquilibrage(Noeud racine) { 
			if (racine == null) 
				return 0; 

			return getHauteur(racine.getFilsGauche()) - getHauteur(racine.getFilsDroit()); 
		} 
 
		/**
		 * Effectue une rotation droite avec l'arbre dont la racine est le noeud passé en paramètre.
		 * @param q La racine à partir de laquelle il faut effectuer la rotation.
		 * @return La nouvelle racine après la rotation.
		 */
		Noeud rotationDroite(Noeud q) { 
			Noeud p = q.getFilsGauche(); 
			Noeud v = p.getFilsDroit();

			// La rotation !
			p.setFilsDroit(q); 
			q.setFilsGauche(v);

			// Il faut mettre à jour les hauteurs
			q.setHauteur(max(getHauteur(q.getFilsGauche()), getHauteur(q.getFilsDroit())) + 1);
			p.setHauteur(max(getHauteur(p.getFilsGauche()), getHauteur(p.getFilsDroit())) + 1); 
			
			return p; 
		} 

		/**
		 * Effectue une rotation gauche avec l'arbre dont la racine est le noeud passé en paramètre.
		 * @param p La racine à partir de laquelle il faut effectuer la rotation.
		 * @return La nouvelle racine après la rotation.
		 */ 
		Noeud rotationGauche(Noeud p) { 
			Noeud q = p.getFilsDroit(); 
			Noeud v = q.getFilsGauche(); 

			// Rotation !
			q.setFilsGauche(p);
			p.setFilsDroit(v);

			// Il faut mettre à jour les hauteurs
			q.setHauteur(max(getHauteur(q.getFilsGauche()), getHauteur(q.getFilsDroit())) + 1);
			p.setHauteur(max(getHauteur(p.getFilsGauche()), getHauteur(p.getFilsDroit())) + 1); 
 
			return q; 
		} 

		/**
		 * Insère une clé dans l'arbre
		 * @param racine La racine de l'arbre.
		 * @param cle La clé à insérer.
		 * @return La racine avec la clé insérée.
		 */
		Noeud inserer(Noeud racine, int cle) { 		// Voir Cours 2 slide 15-16
			// On effectue l'insertion
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
			else if (equilibrage < -1) {
				// L'arbre contient plus de noeud à droite
				if (cle > racine.getFilsDroit().getCle())
					return rotationGauche(racine); 										// Rotation Gauche
				else if (cle < racine.getFilsDroit().getCle()) {
					racine.setFilsDroit(rotationDroite(racine.getFilsDroit()));			// Rotation droite gauche 
					return rotationGauche(racine);
				}
			}
		return racine; 
	}
		
	/**
	 * Recherche une clé dans la racine passée en argument.
	 * @param racine Le noeud racine dans lequel on souhaite effectuer la recherche.
	 * @param cle La clé à recherchée.
	 * @return Le noeud contenant la clé.
	 * @throws Exception La clé n'est pas présente ni dans la racine, ni dans ses fils.
	 */
	Noeud rechercher(Noeud racine, int cle) throws Exception {
		if (racine != null) {
			if (cle == racine.getCle())
				return racine;
			else if (cle < racine.getCle())
				return rechercher(racine.getFilsGauche(), cle);
			else
				return rechercher(racine.getFilsDroit(), cle);
		}
		else
			throw new Exception("La clé n'est pas présente dans l'arbre");
	}
}
