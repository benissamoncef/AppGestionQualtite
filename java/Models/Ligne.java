package Models;




public class Ligne {
	
	private int idLigne;
	private String LabelLigne; 
	private String commentaire;
	private Integer id_zone;
	private String StringZone;
	
	
	
	
	public String getStringZone() {
		return StringZone;
	}

	public void setStringZone(String stringZone) {
		StringZone = stringZone;
	}

	public Ligne(int idLigne, String labelLigne, String commentaire, Integer id_zone, String stringZone) {
		super();
		this.idLigne = idLigne;
		LabelLigne = labelLigne;
		this.commentaire = commentaire;
		this.id_zone = id_zone;
		StringZone = stringZone;
	}

	public Ligne(int idLigne, String labelLigne, String commentaire, Integer id_zone) {
		super();
		this.idLigne = idLigne;
		LabelLigne = labelLigne;
		this.commentaire = commentaire;
		this.id_zone = id_zone;
	}

	public Ligne(String labelLigne, String commentaire, Integer id_zone) {
		super();
		LabelLigne = labelLigne;
		this.commentaire = commentaire;
		this.id_zone = id_zone;
	}

	public int getIdLigne() {
		return idLigne;
	}

	public void setIdLigne(int idLigne) {
		this.idLigne = idLigne;
	}

	public String getLabelLigne() {
		return LabelLigne;
	}

	public void setLabelLigne(String labelLigne) {
		LabelLigne = labelLigne;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Integer getId_zone() {
		return id_zone;
	}

	public void setId_zone(Integer id_zone) {
		this.id_zone = id_zone;
	}
	
	
	
	


}
