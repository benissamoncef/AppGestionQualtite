package Models;

import java.time.LocalTime;

import org.openjfx.SGQV2.Forms.FomulairePriseDonnéesController;

import com.dlsc.gemsfx.TimePicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import impl.com.calendarfx.view.NumericTextField;

public class RéponseRapportArretsTableau {
	
	
	private int id_réponseFormulaire;
	
	private int id;
	private int numero;
	private String type;
	private Integer intervension = 0;
	private String intervenant;
	private String référence;
	private String description;
	private String matricule;
	private LocalTime heurArret = LocalTime.now();
	private LocalTime heurReprise = LocalTime.now();
	private Integer NbrOp = 0;
	
	
	// Questions Rapport des arrets:
	
		private Integer QnumeroRA;
		private JFXTextField QTypeArret = new JFXTextField();
		private NumericTextField QNumeroIntervension = new NumericTextField();
		private JFXTextField QIntervenant = new JFXTextField();
		private JFXComboBox<String> QRéférenceRA = new JFXComboBox<String>();
		private JFXTextField QDescriptionRA = new JFXTextField();
		private NumericTextField QMatriculeRA = new NumericTextField();	
		private TimePicker QheurArret = new TimePicker();
		private TimePicker QheurReprise = new TimePicker();
		private NumericTextField QNbrOp = new NumericTextField();
	
	
	
		public RéponseRapportArretsTableau() {
			QRéférenceRA = new JFXComboBox<String>(FomulairePriseDonnéesController.References);
			
		}
		
		
		

	public RéponseRapportArretsTableau(int id, int numero, String type, int intervension,
			String intervenant, String référence, String description, String matricule, LocalTime heurArret, LocalTime heurReprise,Integer  NbrOp,int id_réponseFormulaire) {
		super();
		this.id_réponseFormulaire = id_réponseFormulaire;
		this.id = id;
		this.numero = numero;
		this.type = type;
		this.intervension = intervension;
		this.intervenant = intervenant;
		this.référence = référence;
		this.description = description;
		this.matricule = matricule;
		this.heurArret = heurArret;
		this.heurReprise = heurReprise;
		this.setNbrOp(NbrOp);
	}




	public RéponseRapportArretsTableau(int id_réponseFormulaire, int numero, String type, int intervension,
			String intervenant, String référence, String description, String matricule, LocalTime heurArret,Integer NbrOp, LocalTime heurReprise) {
		super();
		this.id_réponseFormulaire = id_réponseFormulaire;
		this.numero = numero;
		this.type = type;
		this.intervension = intervension;
		this.intervenant = intervenant;
		this.référence = référence;
		this.description = description;
		this.matricule = matricule;
		this.heurArret = heurArret;
		this.heurReprise = heurReprise;
		this.setNbrOp(NbrOp);
	}




	public int getNumero() {
		return numero;
	}




	public void setNumero(int numero) {
		this.numero = numero;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public Integer getIntervension() {
		return intervension;
	}




	public void setIntervension(Integer intervension) {
		this.intervension = intervension;
	}




	public String getIntervenant() {
		return intervenant;
	}




	public void setIntervenant(String intervenant) {
		this.intervenant = intervenant;
	}







	public String getRéférence() {
		return référence;
	}




	public void setRéférence(String référence) {
		this.référence = référence;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getMatricule() {
		return matricule;
	}




	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}




	public LocalTime getHeurArret() {
		return heurArret;
	}




	public void setHeurArret(LocalTime heurArret) {
		this.heurArret = heurArret;
	}




	public LocalTime getHeurReprise() {
		return heurReprise;
	}




	public void setHeurReprise(LocalTime heurReprise) {
		this.heurReprise = heurReprise;
	}




	public int getId_réponseFormulaire() {
		return id_réponseFormulaire;
	}




	public void setId_réponseFormulaire(int id_réponseFormulaire) {
		this.id_réponseFormulaire = id_réponseFormulaire;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public Integer getQnumeroRA() {
		return QnumeroRA;
	}




	public void setQnumeroRA(Integer qnumeroRA) {
		QnumeroRA = qnumeroRA;
	}




	public JFXTextField getQTypeArret() {
		return QTypeArret;
	}




	public void setQTypeArret(JFXTextField qTypeArret) {
		QTypeArret = qTypeArret;
	}




	public NumericTextField getQNumeroIntervension() {
		return QNumeroIntervension;
	}




	public void setQNumeroIntervension(NumericTextField qNumeroIntervension) {
		QNumeroIntervension = qNumeroIntervension;
	}




	public JFXTextField getQIntervenant() {
		return QIntervenant;
	}




	public void setQIntervenant(JFXTextField qIntervenant) {
		QIntervenant = qIntervenant;
	}




	public JFXComboBox<String> getQRéférenceRA() {
		return QRéférenceRA;
	}




	public void setQRéférenceRA(JFXComboBox<String> qRéférenceRA) {
		QRéférenceRA = qRéférenceRA;
	}




	public JFXTextField getQDescriptionRA() {
		return QDescriptionRA;
	}




	public void setQDescriptionRA(JFXTextField qDescriptionRA) {
		QDescriptionRA = qDescriptionRA;
	}




	public NumericTextField getQMatriculeRA() {
		return QMatriculeRA;
	}




	public void setQMatriculeRA(NumericTextField qMatriculeRA) {
		QMatriculeRA = qMatriculeRA;
	}




	public TimePicker getQheurArret() {
		return QheurArret;
	}




	public void setQheurArret(TimePicker qheurArret) {
		QheurArret = qheurArret;
	}




	public TimePicker getQheurReprise() {
		return QheurReprise;
	}




	public void setQheurReprise(TimePicker qheurReprise) {
		QheurReprise = qheurReprise;
	}




	public NumericTextField getQNbrOp() {
		return QNbrOp;
	}




	public void setQNbrOp(NumericTextField qNbrOp) {
		QNbrOp = qNbrOp;
	}




	public Integer getNbrOp() {
		return NbrOp;
	}




	public void setNbrOp(Integer nbrOp) {
		NbrOp = nbrOp;
	}
	
	
	
	
	
	
	
	


}
