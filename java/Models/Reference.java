package Models;

import java.sql.Date;

public class Reference {
	
	//Attributs:
	private int idReference;
	private String codeReferenceInterne; 
	private String codeReferenceClient; 
	private String commentaire; 
	private Date dateCreation;
	
	//Traduction des associations:
	
	private Projet projet;
	private int idProjet;
	private String labelProjet;

	public int getIdReference() {
		return idReference;
	}

	public void setIdReference(int idReference) {
		this.idReference = idReference;
	}

	private Famille famille;
	private String labelFamille;

	public String getLabelProjet() {
		return labelProjet;
	}

	public Reference(int idReference, String codeReferenceInterne, String codeReferenceClient, String commentaire, int idProjet, int idFamille) {
		this.idReference = idReference;
		this.codeReferenceInterne = codeReferenceInterne;
		this.codeReferenceClient = codeReferenceClient;
		this.commentaire = commentaire;
		this.idProjet = idProjet;
		this.idFamille = idFamille;
	}

	public void setLabelProjet(String labelProjet) {
		this.labelProjet = labelProjet;
	}

	public String getLabelFamille() {
		return labelFamille;
	}

	public void setLabelFamille(String labelFamille) {
		this.labelFamille = labelFamille;
	}

	public int getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(int idProjet) {
		this.idProjet = idProjet;
	}

	public int getIdFamille() {
		return idFamille;
	}

	public void setIdFamille(int idFamille) {
		this.idFamille = idFamille;
	}

	private int idFamille;

	public Reference(int idReference, String codeReferenceInterne, String codeReferenceClient, String commentaire, Date dateCreation, int idProjet, int idFamille) {
		this.idReference = idReference;
		this.codeReferenceInterne = codeReferenceInterne;
		this.codeReferenceClient = codeReferenceClient;
		this.commentaire = commentaire;
		this.dateCreation = dateCreation;
		this.idProjet = idProjet;
		this.idFamille = idFamille;
	}

	public Reference(String codeReferenceInterne, String codeReferenceClient, String commentaire, Projet projet, Famille famille) {
		this.codeReferenceInterne = codeReferenceInterne;
		this.codeReferenceClient = codeReferenceClient;
		this.commentaire = commentaire;
		this.projet = projet;
		this.famille = famille;
	}

	//Getters & Setters:
	
	public Projet getProjet() {
		return projet;
	}
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	public Famille getFamille() {
		return famille;
	}
	public void setFamille(Famille famille) {
		this.famille = famille;
	}
	public String getCodeReferenceInterne() {
		return codeReferenceInterne;
	}
	public void setCodeReferenceInterne(String codeReferenceInterne) {
		this.codeReferenceInterne = codeReferenceInterne;
	}
	public String getCodeReferenceClient() {
		return codeReferenceClient;
	}
	public void setCodeReferenceClient(String codeReferenceClient) {
		this.codeReferenceClient = codeReferenceClient;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	} 
	
	
}
