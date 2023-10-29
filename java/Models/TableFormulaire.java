package Models;

public class TableFormulaire {
	
	private Integer id;
	
	private String nom_table;
	
	private Integer id_formulaire;
	
	

	public TableFormulaire(Integer id, String nom_table, Integer id_formulaire) {
		super();
		this.id = id;
		this.nom_table = nom_table;
		this.id_formulaire = id_formulaire;
	}



	public TableFormulaire(String nom_table, Integer id_formulaire) {
		super();
		this.nom_table = nom_table;
		this.id_formulaire = id_formulaire;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNom_table() {
		return nom_table;
	}



	public void setNom_table(String nom_table) {
		this.nom_table = nom_table;
	}



	public Integer getId_formulaire() {
		return id_formulaire;
	}



	public void setId_formulaire(Integer id_formulaire) {
		this.id_formulaire = id_formulaire;
	}
	


}
