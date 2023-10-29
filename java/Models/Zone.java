package Models;

public class Zone {
	
	private int idZone;
	private String labelZone; 
	private String commentaire;

	public Zone(String labelZone, String commentaire) {
		super();
		this.labelZone = labelZone;
		this.commentaire = commentaire;
	}
	public Zone(int idZone, String labelZone, String commentaire) {
		this.idZone = idZone;
		this.labelZone = labelZone;
		this.commentaire = commentaire;
	}



	// Traduction des associations:
	
	/*private List<Ligne> lignes;
	
	//Getters & Setters: 
	

	public List<Ligne> getLignes() {
		return lignes;
	}
	public void setLignes(List<Ligne> lignes) {
		this.lignes = lignes;
	}*/
	public int getIdZone() {
		return idZone;
	}
	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}
	public String getLabelZone() {
		return labelZone;
	}
	public void setLabelZone(String labelZone) {
		this.labelZone = labelZone;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	@Override
	public String toString() {
		return "Zone{" +
				"idZone=" + idZone +
				", labelZone='" + labelZone + '\'' +
				", commentaire='" + commentaire + '\'' +
				'}';
	}
}
