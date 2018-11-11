package arbre_de_recherche_5;

public class InsertionException extends Exception{
	private static final long serialVersionUID = -3745107194548896174L;

	public InsertionException() {
		super("La clé est déjà présente dans l'arbre");
	}
}
