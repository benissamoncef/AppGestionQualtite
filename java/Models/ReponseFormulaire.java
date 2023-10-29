package Models;

import java.sql.Date;
import java.time.temporal.ChronoField;

public class ReponseFormulaire {
	
	
	private String labelShift;
	private Integer id;
	private Boolean etat_reponse;
	private Integer id_formulaire;
	private Integer id_compte_technicien;
	private Integer id_famille;
	private Integer id_ligne;
	private Integer id_shift;
	private String equipe;
	private Integer week;
	private Date CreatedAt;
	private String teamLeader; 
	private String opérateur; 
	

	
	

	public ReponseFormulaire(Integer id, Boolean etat_reponse, Integer id_formulaire, Integer id_compte_technicien,
			Integer id_famille, Integer id_ligne, Integer id_shift, String equipe, Integer week, Date createdAt) {
		super();
		this.id = id;
		this.etat_reponse = etat_reponse;
		this.id_formulaire = id_formulaire;
		this.id_compte_technicien = id_compte_technicien;
		this.id_famille = id_famille;
		this.id_ligne = id_ligne;
		this.id_shift = id_shift;
		this.equipe = equipe;
		this.week = week;
		CreatedAt = createdAt;
	}


	
	
	

	public ReponseFormulaire(Boolean etat_reponse, Integer id_formulaire, Integer id_compte_technicien,
			Integer id_famille, Integer id_ligne, Integer id_shift, String equipe, String teamLeader, String opérateur, Date createdAt) {
		super();
		this.etat_reponse = etat_reponse;
		this.id_formulaire = id_formulaire;
		this.id_compte_technicien = id_compte_technicien;
		this.id_famille = id_famille;
		this.id_ligne = id_ligne;
		this.id_shift = id_shift;
		this.equipe = equipe;
		this.teamLeader = teamLeader;
		this.opérateur = opérateur;
		this.week = createdAt.toLocalDate().get(ChronoField.ALIGNED_WEEK_OF_YEAR);
		CreatedAt = createdAt;
	}
	
	
	public ReponseFormulaire(Integer id, Boolean etat_reponse, Integer id_formulaire, Integer id_compte_technicien,
			Integer id_famille, Integer id_ligne, Integer id_shift, String equipe, String teamLeader, String opérateur, Date createdAt) {
		super();
		this.id = id;
		this.etat_reponse = etat_reponse;
		this.id_formulaire = id_formulaire;
		this.id_compte_technicien = id_compte_technicien;
		this.id_famille = id_famille;
		this.id_ligne = id_ligne;
		this.id_shift = id_shift;
		this.equipe = equipe;
		this.teamLeader = teamLeader;
		this.opérateur = opérateur;
		this.week = createdAt.toLocalDate().get(ChronoField.ALIGNED_WEEK_OF_YEAR);
		CreatedAt = createdAt;
	}
	
	
	public ReponseFormulaire(Integer id_formulaire, Integer id_compte_technicien,
			Integer id_famille, Integer id_ligne, Date createdAt) {
		super();

		this.id_formulaire = id_formulaire;
		this.id_compte_technicien = id_compte_technicien;
		this.id_famille = id_famille;
		this.id_ligne = id_ligne;
		this.week = createdAt.toLocalDate().get(ChronoField.ALIGNED_WEEK_OF_YEAR);
		CreatedAt = createdAt;
	}

	public ReponseFormulaire(Integer id_formulaire, Integer id_compte_technicien,
			Integer id_famille, Integer id_ligne, Integer id_shift,  Date createdAt) {
		super();

		this.id_formulaire = id_formulaire;
		this.id_compte_technicien = id_compte_technicien;
		this.id_famille = id_famille;
		this.id_ligne = id_ligne;
		this.id_shift = id_shift;
		this.week = createdAt.toLocalDate().get(ChronoField.ALIGNED_WEEK_OF_YEAR);
		CreatedAt = createdAt;
	}

	public ReponseFormulaire(Integer id_formulaire, Integer id_compte_technicien, Integer id_famille, Integer id_ligne, String labelShift, String equipe, Date createdAt) {
		this.id_formulaire = id_formulaire;
		this.id_compte_technicien = id_compte_technicien;
		this.id_famille = id_famille;
		this.id_ligne = id_ligne;
		this.labelShift = labelShift;
		this.equipe = equipe;
		CreatedAt = createdAt;
	}


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}






	public Boolean getEtat_reponse() {
		return etat_reponse;
	}



	public void setEtat_reponse(Boolean etat_reponse) {
		this.etat_reponse = etat_reponse;
	}



	public Integer getId_formulaire() {
		return id_formulaire;
	}



	public void setId_formulaire(Integer id_formulaire) {
		this.id_formulaire = id_formulaire;
	}



	public String getLabelShift() {
		return labelShift;
	}



	public void setLabelShift(String labelShift) {
		this.labelShift = labelShift;
	}



	public Integer getId_compte_technicien() {
		return id_compte_technicien;
	}



	public void setId_compte_technicien(Integer id_compte_technicien) {
		this.id_compte_technicien = id_compte_technicien;
	}



	public Integer getId_famille() {
		return id_famille;
	}



	public void setId_famille(Integer id_famille) {
		this.id_famille = id_famille;
	}



	public Integer getId_ligne() {
		return id_ligne;
	}



	public void setId_ligne(Integer id_ligne) {
		this.id_ligne = id_ligne;
	}



	public Integer getId_shift() {
		return id_shift;
	}



	public void setId_shift(Integer id_shift) {
		this.id_shift = id_shift;
	}



	public String getEquipe() {
		return equipe;
	}



	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}



	public Date getCreatedAt() {
		return CreatedAt;
	}



	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}



	public Integer getWeek() {
		return week;
	}



	public void setWeek(Integer week) {
		this.week = week;
	}



	public String getOpérateur() {
		return opérateur;
	}



	public void setOpérateur(String opérateur) {
		this.opérateur = opérateur;
	}



	public String getTeamLeader() {
		return teamLeader;
	}



	public void setTeamLeader(String teamLeader) {
		this.teamLeader = teamLeader;
	}
	
	
	



	
	

}
