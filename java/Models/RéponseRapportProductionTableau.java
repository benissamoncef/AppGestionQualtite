package Models;

import org.openjfx.SGQV2.Forms.FomulairePriseDonnéesController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import impl.com.calendarfx.view.NumericTextField;

public class RéponseRapportProductionTableau {
	
	private int id_réponseFormulaire;

	
	private int id;
	private int numero;
	private String référence;
	private Integer qte = 0;
	private String Obs;
	
	
	
	
	// Questions Rapport de production:
	
		private Integer QnumeroRP;
		private JFXComboBox<String> QRéférenceRP = new JFXComboBox<String>();
		private NumericTextField QqteRP  = new NumericTextField();
		private JFXTextField QObservationRP = new JFXTextField();
	
	
		public RéponseRapportProductionTableau() {
			QRéférenceRP = new JFXComboBox<String>(FomulairePriseDonnéesController.References);
		}




	public RéponseRapportProductionTableau(int id, int numero, String référence, int qte,
			String obs, int id_réponseFormulaire) {
		super();
		this.id_réponseFormulaire = id_réponseFormulaire;
		this.setId(id);
		this.numero = numero;
		this.référence = référence;
		this.qte = qte;
		Obs = obs;
	}



	public RéponseRapportProductionTableau(int id_réponseFormulaire, int numero, String référence, int qte,
			String obs) {
		super();
		this.setId_réponseFormulaire(id_réponseFormulaire);
		this.numero = numero;
		this.référence = référence;
		this.qte = qte;
		Obs = obs;
	}



	public int getNumero() {
		return numero;
	}



	public void setNumero(int numero) {
		this.numero = numero;
	}



	public String getRéférence() {
		return référence;
	}


	public void setRéférence(String référence) {
		this.référence = référence;
	}



	public Integer getQte() {
		return qte;
	}



	public void setQte(int qte) {
		this.qte = qte;
	}



	public String getObs() {
		return Obs;
	}



	public void setObs(String obs) {
		Obs = obs;
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



	public Integer getQnumeroRP() {
		return QnumeroRP;
	}



	public void setQnumeroRP(Integer qnumeroRP) {
		QnumeroRP = qnumeroRP;
	}



	public JFXComboBox<String> getQRéférenceRP() {
		return QRéférenceRP;
	}



	public void setQRéférenceRP(JFXComboBox<String> qRéférenceRP) {
		QRéférenceRP = qRéférenceRP;
	}



	public NumericTextField getQqteRP() {
		return QqteRP;
	}



	public void setQqteRP(NumericTextField qqteRP) {
		QqteRP = qqteRP;
	}



	public JFXTextField getQObservationRP() {
		return QObservationRP;
	}



	public void setQObservationRP(JFXTextField qObservationRP) {
		QObservationRP = qObservationRP;
	}



}
