package Models;

public class GroupeQuestions {

	private int id;
	private String titre_groupe;
	private int id_table_formulaire;


	
	public GroupeQuestions(String titre_groupe, int id_table_formulaire) {
		super();
		this.titre_groupe = titre_groupe;
		this.id_table_formulaire = id_table_formulaire;
	}

	

	public GroupeQuestions(int id, String titre_groupe, int id_table_formulaire) {
		super();
		this.id = id;
		this.titre_groupe = titre_groupe;
		this.id_table_formulaire = id_table_formulaire;
	}

	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitre_groupe() {
		return titre_groupe;
	}


	public void setTitre_groupe(String titre_groupe) {
		this.titre_groupe = titre_groupe;
	}


	public int getId_table_formulaire() {
		return id_table_formulaire;
	}


	public void setId_table_formulaire(int id_table_formulaire) {
		this.id_table_formulaire = id_table_formulaire;
	}

	
	
}
