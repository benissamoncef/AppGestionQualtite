package Models;

public class Formulaire {
	
	private Integer id;
	private String Titre;

	
	
	public Formulaire(Integer id, String titre) {
		super();
		this.id = id;
		Titre = titre;
	}

	public Formulaire(String titre) {
		super();
		Titre = titre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return Titre;
	}

	public void setTitre(String titre) {
		Titre = titre;
	}
	
	

}
