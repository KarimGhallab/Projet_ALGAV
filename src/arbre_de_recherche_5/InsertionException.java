package arbre_de_recherche_5;

public class InsertionException extends Exception{
	public InsertionException() {
		super("La clé est déjà présente dans l'arbre");
	}
}
