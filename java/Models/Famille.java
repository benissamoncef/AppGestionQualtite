package Models;

import java.sql.Date;


public class Famille {
	
	
        public Famille(int idFamille, String codeFamilleInterne, String codeFamilleClient, String commentaire, Date dateCreation, int idProjet) {
        this.idFamille = idFamille;
        this.codeFamilleInterne = codeFamilleInterne;
        this.codeFamilleClient = codeFamilleClient;
        this.commentaire = commentaire;
        this.dateCreation = dateCreation;
        this.idProjet = idProjet;
    }

    //Attributs:
    private int idFamille;
    private String codeFamilleInterne;
    private String codeFamilleClient;
    private String commentaire;
    private Date dateCreation;

    private Projet projet;
    private String labelProjet;
    private int idProjet;

//Getters & Setters:

    public int getIdFamille() {
        return idFamille;
    }

    public void setIdFamille(int idFamille) {
        this.idFamille = idFamille;
    }
    public String getCodeFamilleInterne() {
        return codeFamilleInterne;
    }
    public void setCodeFamilleInterne(String codeFamilleInterne) {
        this.codeFamilleInterne = codeFamilleInterne;
    }
    public String getCodeFamilleClient() {
        return codeFamilleClient;
    }
    public void setCodeFamilleClient(String codeFamilleClient) {
        this.codeFamilleClient = codeFamilleClient;
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
    public String getLabelProjet() {
        return labelProjet;
    }

    public void setLabelProjet(String labelProjet) {
        this.labelProjet = labelProjet;
    }

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    //Les constructeurs:

    public Famille() {

    }

    public Famille(int idFamille, String codeFamilleInterne, String codeFamilleClient, String commentaire, Date dateCreation) {
        this.idFamille = idFamille;
        this.codeFamilleInterne = codeFamilleInterne;
        this.codeFamilleClient = codeFamilleClient;
        this.commentaire = commentaire;
        this.dateCreation = dateCreation;
    }

    public Famille(String codeFamilleInterne, String codeFamilleClient, String commentaire) {
        this.codeFamilleInterne = codeFamilleInterne;
        this.codeFamilleClient = codeFamilleClient;
        this.commentaire = commentaire;
    }

    public Famille(String codeFamilleInterne, String codeFamilleClient, String commentaire, int idProjet) {
        this.codeFamilleInterne = codeFamilleInterne;
        this.codeFamilleClient = codeFamilleClient;
        this.commentaire = commentaire;
        this.idProjet = idProjet;
    }

    public Famille(int idFamille, String codeFamilleInterne, String codeFamilleClient, String commentaire, int idProjet) {
        this.idFamille = idFamille;
        this.codeFamilleInterne = codeFamilleInterne;
        this.codeFamilleClient = codeFamilleClient;
        this.commentaire = commentaire;
        this.idProjet = idProjet;
    }

    public Famille(int idFamille, String codeFamilleInterne, String codeFamilleClient, String commentaire) {
        this.idFamille = idFamille;
        this.codeFamilleInterne = codeFamilleInterne;
        this.codeFamilleClient = codeFamilleClient;
        this.commentaire = commentaire;
    }

	@Override
	public String toString() {
		return "Famille [idFamille=" + idFamille + ", codeFamilleInterne=" + codeFamilleInterne + ", codeFamilleClient="
				+ codeFamilleClient + ", commentaire=" + commentaire + ", dateCreation=" + dateCreation + ", projet="
				+ projet + ", labelProjet=" + labelProjet + ", idProjet=" + idProjet + "]";
	}

    
    
    

}
