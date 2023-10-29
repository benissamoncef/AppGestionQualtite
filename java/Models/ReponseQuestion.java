	package Models;

import java.sql.Date;



public class ReponseQuestion {
	
	
	private Integer id;
	

	private String Evaluation;

	private String AnomaliesObservées;

	private String ActionsExigées;

	private String RespRéalisation;

	private Date DateRéalisation;
	

	
	
	private Integer id_ReponseFormulaire;
	
	private Integer id_Question;
	
	
	
	

	public ReponseQuestion(Integer id, String evaluation, String anomaliesObservées, String actionsExigées,
			String RespRéalisation, Date dateRéalisation, Integer id_ReponseFormulaire, Integer id_Question) {
		super();
		this.id = id;
		Evaluation = evaluation;
		AnomaliesObservées = anomaliesObservées;
		ActionsExigées = actionsExigées;
		this.setRespRéalisation(RespRéalisation);
		DateRéalisation = dateRéalisation;
		this.id_ReponseFormulaire = id_ReponseFormulaire;
		this.id_Question = id_Question;
	}

	public ReponseQuestion(String evaluation, String anomaliesObservées, String actionsExigées, String RespRéalisation,
			Date dateRéalisation, Integer id_ReponseFormulaire, Integer id_Question) {
		super();
		Evaluation = evaluation;
		AnomaliesObservées = anomaliesObservées;
		ActionsExigées = actionsExigées;
		this.setRespRéalisation(RespRéalisation);
		DateRéalisation = dateRéalisation;
		this.id_ReponseFormulaire = id_ReponseFormulaire;
		this.id_Question = id_Question;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvaluation() {
		return Evaluation;
	}

	public void setEvaluation(String evaluation) {
		Evaluation = evaluation;
	}

	public String getAnomaliesObservées() {
		return AnomaliesObservées;
	}

	public void setAnomaliesObservées(String anomaliesObservées) {
		AnomaliesObservées = anomaliesObservées;
	}

	public String getActionsExigées() {
		return ActionsExigées;
	}

	public void setActionsExigées(String actionsExigées) {
		ActionsExigées = actionsExigées;
	}


	public Date getDateRéalisation() {
		return DateRéalisation;
	}

	public void setDateRéalisation(Date dateRéalisation) {
		DateRéalisation = dateRéalisation;
	}

	public Integer getId_ReponseFormulaire() {
		return id_ReponseFormulaire;
	}

	public void setId_ReponseFormulaire(Integer id_ReponseFormulaire) {
		this.id_ReponseFormulaire = id_ReponseFormulaire;
	}

	public Integer getId_Question() {
		return id_Question;
	}

	public void setId_Question(Integer id_Question) {
		this.id_Question = id_Question;
	}

	public String getRespRéalisation() {
		return RespRéalisation;
	}

	public void setRespRéalisation(String respRéalisation) {
		RespRéalisation = respRéalisation;
	}



	

}
