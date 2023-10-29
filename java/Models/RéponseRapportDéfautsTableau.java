package Models;

import org.openjfx.SGQV2.Forms.FomulairePriseDonnéesController;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import impl.com.calendarfx.view.NumericTextField;


public class RéponseRapportDéfautsTableau {

	
	
	
	
	private int id_réponseFormulaire;

	private int id;
	private Integer numero = 0;
	private Integer numeroSérie = 0;
	private String Référence;
	private Integer poste = 0;
	private Integer matricule = 0;
	private Integer codeDéfaut = 0;
	private String composant ;
	private String circuit;
	private Integer voie1 = 0;
	private Integer voie2 = 0;
	private Integer qte = 0;
	private String obs;
	
	
	
	
	// Questions Rapport des défauts qualité :
	
	
	
		private Integer QnumeroRDQ;
		private NumericTextField QNumSérieRDQ = new NumericTextField();
		private JFXComboBox<String> QRéférenceRDQ = new JFXComboBox<String>();
		private NumericTextField QpostRDQ = new NumericTextField();
		private NumericTextField QMatricule = new NumericTextField();
		private JFXComboBox<Integer> QDéfautsRDQ = new JFXComboBox<Integer>();
		private JFXTextField QComposantRDQ = new JFXTextField();
		private JFXTextField QCircuitRDQ = new JFXTextField();
		private NumericTextField Qvoie1 = new NumericTextField();
		private NumericTextField Qvoie2 = new NumericTextField();
		private NumericTextField QqteRDQ = new NumericTextField();
		private JFXTextField QObservationRDQ = new JFXTextField();
		
		
		
		
		
	public RéponseRapportDéfautsTableau() {
		QRéférenceRDQ = new JFXComboBox<String>(FomulairePriseDonnéesController.References);
		QDéfautsRDQ = new JFXComboBox<Integer>(FomulairePriseDonnéesController.Défauts);
	}



	public Integer getQnumeroRDQ() {
			return QnumeroRDQ;
		}


		public void setQnumeroRDQ(Integer qnumeroRDQ) {
			QnumeroRDQ = qnumeroRDQ;
		}


		public NumericTextField getQNumSérieRDQ() {
			return QNumSérieRDQ;
		}


		public void setQNumSérieRDQ(NumericTextField qNumSérieRDQ) {
			QNumSérieRDQ = qNumSérieRDQ;
		}


		public JFXComboBox<String> getQRéférenceRDQ() {
			return QRéférenceRDQ;
		}


		public void setQRéférenceRDQ(JFXComboBox<String> qRéférenceRDQ) {
			QRéférenceRDQ = qRéférenceRDQ;
		}


		public NumericTextField getQpostRDQ() {
			return QpostRDQ;
		}


		public void setQpostRDQ(NumericTextField qpostRDQ) {
			QpostRDQ = qpostRDQ;
		}


		public NumericTextField getQMatricule() {
			return QMatricule;
		}


		public void setQMatricule(NumericTextField qMatricule) {
			QMatricule = qMatricule;
		}


		public JFXComboBox<Integer> getQDéfautsRDQ() {
			return QDéfautsRDQ;
		}


		public void setQDéfautsRDQ(JFXComboBox<Integer> qDéfautsRDQ) {
			QDéfautsRDQ = qDéfautsRDQ;
		}


		public JFXTextField getQComposantRDQ() {
			return QComposantRDQ;
		}


		public void setQComposantRDQ(JFXTextField qComposantRDQ) {
			QComposantRDQ = qComposantRDQ;
		}


		public JFXTextField getQCircuitRDQ() {
			return QCircuitRDQ;
		}


		public void setQCircuitRDQ(JFXTextField qCircuitRDQ) {
			QCircuitRDQ = qCircuitRDQ;
		}


		public NumericTextField getQvoie1() {
			return Qvoie1;
		}


		public void setQvoie1(NumericTextField qvoie1) {
			Qvoie1 = qvoie1;
		}


		public NumericTextField getQvoie2() {
			return Qvoie2;
		}


		public void setQvoie2(NumericTextField qvoie2) {
			Qvoie2 = qvoie2;
		}


		public NumericTextField getQqteRDQ() {
			return QqteRDQ;
		}


		public void setQqteRDQ(NumericTextField qqteRDQ) {
			QqteRDQ = qqteRDQ;
		}


		public JFXTextField getQObservationRDQ() {
			return QObservationRDQ;
		}


		public void setQObservationRDQ(JFXTextField qObservationRDQ) {
			QObservationRDQ = qObservationRDQ;
		}


		
		
		

	public RéponseRapportDéfautsTableau(int id, int numero, int numeroSérie, String référence,
			int poste, int matricule, int codeDéfaut, String composant, String circuit, int voie1, int voie2, int qte,
			String obs, int id_réponseFormulaire) {
		super();
		this.id_réponseFormulaire = id_réponseFormulaire;
		this.setId(id);
		this.numero = numero;
		this.numeroSérie = numeroSérie;
		Référence = référence;
		this.poste = poste;
		this.matricule = matricule;
		this.codeDéfaut = codeDéfaut;
		this.composant = composant;
		this.circuit = circuit;
		this.voie1 = voie1;
		this.voie2 = voie2;
		this.qte = qte;
		this.obs = obs;
	}


	public RéponseRapportDéfautsTableau(int id_réponseFormulaire, int numero, int numeroSérie, String Référence,
			int poste, int matricule, int codeDéfaut, String composant, String circuit, int voie1, int voie2, int qte,
			String obs) {
		super();
		this.id_réponseFormulaire = id_réponseFormulaire;
		this.numero = numero;
		this.setNumeroSérie(numeroSérie);
		this.Référence = Référence;
		this.poste = poste;
		this.matricule = matricule;
		this.codeDéfaut = codeDéfaut;
		this.composant = composant;
		this.circuit = circuit;
		this.voie1 = voie1;
		this.voie2 = voie2;
		this.qte = qte;
		this.obs = obs;
	}


	public Integer getNumero() {
		return numero;
	}


	public void setNumero(Integer numero) {
		this.numero = numero;
	}


	public String getRéférence() {
		return Référence;
	}


	public void setRéférence(String id_Référence) {
		this.Référence = id_Référence;
	}


	public Integer getPoste() {
		return poste;
	}


	public void setPoste(Integer poste) {
		this.poste = poste;
	}


	public Integer getMatricule() {
		return matricule;
	}


	public void setMatricule(Integer matricule) {
		this.matricule = matricule;
	}


	public Integer getCodeDéfaut() {
		return codeDéfaut;
	}


	public void setCodeDéfaut(Integer codeDéfaut) {
		this.codeDéfaut = codeDéfaut;
	}


	public String getComposant() {
		return composant;
	}


	public void setComposant(String composant) {
		this.composant = composant;
	}


	public String getCircuit() {
		return circuit;
	}


	public void setCircuit(String circuit) {
		this.circuit = circuit;
	}


	public Integer getVoie1() {
		return voie1;
	}


	public void setVoie1(Integer voie1) {
		this.voie1 = voie1;
	}


	public Integer getVoie2() {
		return voie2;
	}


	public void setVoie2(Integer voie2) {
		this.voie2 = voie2;
	}


	public Integer getQte() {
		return qte;
	}


	public void setQte(Integer qte) {
		this.qte = qte;
	}


	public String getObs() {
		return obs;
	}


	public void setObs(String obs) {
		this.obs = obs;
	}


	public Integer getId_réponseFormulaire() {
		return id_réponseFormulaire;
	}


	public void setId_réponseFormulaire(Integer id_réponseFormulaire) {
		this.id_réponseFormulaire = id_réponseFormulaire;
	}


	public Integer getNumeroSérie() {
		return numeroSérie;
	}


	public void setNumeroSérie(Integer numeroSérie) {
		this.numeroSérie = numeroSérie;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	


}
