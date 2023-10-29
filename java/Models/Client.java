package Models;

public class Client {
	
	private Integer id;
	private String labelClient;
	private String commentaire;
	
	public Client(String labelClient, String commentaire) {
		super();
		this.labelClient = labelClient;
		this.commentaire = commentaire;
	}
	
	public Client(Integer id, String labelClient, String commentaire) {
		super();
		this.id = id;
		this.labelClient = labelClient;
		this.commentaire = commentaire;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabelClient() {
		return labelClient;
	}
	public void setLabelClient(String labelClient) {
		this.labelClient = labelClient;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	
	
	

}
