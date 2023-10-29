package Models;

public class Employee {
	
	
	private Integer matricule;
	private String nom;
	private String prenom;
	private String numero_telephone;
	private String email;
	private String mission;
	private String poste;
	private String commentaire;
	
	



	public Employee(Integer matricule, String nom, String prenom, String numero_telephone, String email, String mission,
			 String poste, String commentaire) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.numero_telephone = numero_telephone;
		this.email = email;
		this.mission = mission;
		this.poste = poste;
		this.commentaire = commentaire;
	}


	public Integer getMatricule() {
		return matricule;
	}


	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNumero_telephone() {
		return numero_telephone;
	}


	public void setNumero_telephone(String numero_telephone) {
		this.numero_telephone = numero_telephone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMission() {
		return mission;
	}


	public void setMission(String mission) {
		this.mission = mission;
	}


	public String getPoste() {
		return poste;
	}


	public void setPoste(String poste) {
		this.poste = poste;
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}



	
	

}
