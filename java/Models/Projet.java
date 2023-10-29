package Models;

public class Projet {
	
	private int id;
	private String LabelProjet;
	private String Commentaire;
	private Integer id_client;
	private Integer id_model;
	private String StringNomClient;
	private String StringModel;
	
	
	


	

	public Projet(int id, String labelProjet, String commentaire, Integer id_client, Integer id_model) {
		super();
		this.id = id;
		LabelProjet = labelProjet;
		Commentaire = commentaire;
		this.id_client = id_client;
		this.id_model = id_model;
	}
	
	public Projet(String labelProjet, String commentaire, Integer id_client, Integer id_model) {
		super();

		LabelProjet = labelProjet;
		Commentaire = commentaire;
		this.id_client = id_client;
		this.id_model = id_model;
	}

	public Projet(int id, String labelProjet, String commentaire, Integer id_client, Integer id_model,
			String stringNomClient, String stringModel) {
		super();
		this.id = id;
		LabelProjet = labelProjet;
		Commentaire = commentaire;
		this.id_client = id_client;
		this.id_model = id_model;
		StringNomClient = stringNomClient;
		StringModel = stringModel;
	}



	public Projet(int id, String labelProjet) {
		super();
		this.id = id;
		LabelProjet = labelProjet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabelProjet() {
		return LabelProjet;
	}

	public void setLabelProjet(String LabelProjet) {
		this.LabelProjet = LabelProjet;
	}

	public String getCommentaire() {
		return Commentaire;
	}

	public void setCommentaire(String commentaire) {
		Commentaire = commentaire;
	}

	public Integer getId_client() {
		return id_client;
	}

	public void setId_client(Integer id_client) {
		this.id_client = id_client;
	}

	public Integer getId_model() {
		return id_model;
	}

	public void setId_model(Integer id_model) {
		this.id_model = id_model;
	}

	
	public String getStringNomClient() {
		return StringNomClient;
	}

	public void setStringNomClient(String stringNomClient) {
		StringNomClient = stringNomClient;
	}

	public String getStringModel() {
		return StringModel;
	}

	public void setStringModel(String stringModel) {
		StringModel = stringModel;
	}

	@Override
	public String toString() {
		return "Projet [id=" + id + ", LabelProjet=" + LabelProjet + ", Commentaire=" + Commentaire + ", id_client="
				+ id_client + ", id_model=" + id_model + ", StringNomClient=" + StringNomClient + ", StringModel="
				+ StringModel + "]";
	}
	
	
	


}
