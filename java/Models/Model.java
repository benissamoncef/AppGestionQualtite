package Models;

public class Model {
	
	private Integer id;
	private String LabelModel;
	private String commentaire;
	
	public Model(String labelModel, String commentaire) {
		super();
		LabelModel = labelModel;
		this.commentaire = commentaire;
	}
	


	public Model(Integer id, String labelModel, String commentaire) {
		super();
		this.id = id;
		LabelModel = labelModel;
		this.commentaire = commentaire;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getLabelModel() {
		return LabelModel;
	}


	public void setLabelModel(String labelModel) {
		LabelModel = labelModel;
	}



	public String getCommentaire() {
		return commentaire;
	}



	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
	


}
