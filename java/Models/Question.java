package Models;


import Transactions.DéfautTransaction;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class Question {
	
	private Integer id;
	private String question;
	private Boolean isAuditProcess;
	private Boolean isFirstPiece;	
	private String commentaire;
	
	
	// Questions Audit Process Attributes
	private DatePicker  DateRéalisationPicker = new DatePicker ();
	private JFXTextField RespRéalisationTextField = new JFXTextField();
	private JFXTextField ActionsExigéesTextField = new JFXTextField();
	private JFXTextField AnomaliesObservéesTextField = new JFXTextField();
	private JFXComboBox<String> Evaluation = new JFXComboBox<String>();
	
	
	
	
	
	// Questions Audit Process Attributes: TAP
	
	private String  questionAP;
	
	private JFXComboBox<String> EvaluationTAPAPC1 = new JFXComboBox<String>();
	private JFXComboBox<String> EvaluationTAPAPC2 = new JFXComboBox<String>();
	private JFXComboBox<String> EvaluationTAPAPC3 = new JFXComboBox<String>();
	private JFXComboBox<String> EvaluationTAPAPC4 = new JFXComboBox<String>();



	private JFXComboBox<Integer> DDTAP1 = new JFXComboBox<Integer>();
	private JFXComboBox<Integer> DDTAP2 = new JFXComboBox<Integer>();
	private JFXComboBox<Integer> DDTAP3 = new JFXComboBox<Integer>();
	private JFXComboBox<Integer> DDTAP4 = new JFXComboBox<Integer>();
	private VBox DDTAP = new VBox(DDTAP1,DDTAP2,DDTAP3,DDTAP4);





	// Questions Audit Process Attributes: TPP


	private JFXComboBox<String> EvaluationTPPAPC1 = new JFXComboBox<String>();
	private JFXComboBox<String> EvaluationTPPAPC2 = new JFXComboBox<String>();
	private JFXComboBox<String> EvaluationTPPAPC3 = new JFXComboBox<String>();
	private JFXComboBox<String> EvaluationTPPAPC4 = new JFXComboBox<String>();



	private JFXComboBox<Integer> DDTPP1 = new JFXComboBox<Integer>();
	private JFXComboBox<Integer> DDTPP2 = new JFXComboBox<Integer>();
	private JFXComboBox<Integer> DDTPP3 = new JFXComboBox<Integer>();
	private JFXComboBox<Integer> DDTPP4 = new JFXComboBox<Integer>();

	private VBox DDTPP = new VBox(DDTPP1,DDTPP2,DDTPP3,DDTPP4);

	//serial numbers:
	private String serialNumbers;



	public String getSerialNumbers() {
		return serialNumbers;
	}

	public void setSerialNumbers(String serialNumbers) {
		this.serialNumbers = serialNumbers;
	}

	public JFXComboBox<String> getEvaluationTPPAPC1() {
		return EvaluationTPPAPC1;
	}

	public void setEvaluationTPPAPC1(JFXComboBox<String> evaluationTPPAPC1) {
		EvaluationTPPAPC1 = evaluationTPPAPC1;
	}

	public JFXComboBox<String> getEvaluationTPPAPC2() {
		return EvaluationTPPAPC2;
	}

	public void setEvaluationTPPAPC2(JFXComboBox<String> evaluationTPPAPC2) {
		EvaluationTPPAPC2 = evaluationTPPAPC2;
	}

	public JFXComboBox<String> getEvaluationTPPAPC3() {
		return EvaluationTPPAPC3;
	}

	public void setEvaluationTPPAPC3(JFXComboBox<String> evaluationTPPAPC3) {
		EvaluationTPPAPC3 = evaluationTPPAPC3;
	}

	public JFXComboBox<String> getEvaluationTPPAPC4() {
		return EvaluationTPPAPC4;
	}

	public void setEvaluationTPPAPC4(JFXComboBox<String> evaluationTPPAPC4) {
		EvaluationTPPAPC4 = evaluationTPPAPC4;
	}



	public VBox getDDTPP() {
		return DDTPP;
	}

	public void setDDTPP(VBox DDTPP) {
		this.DDTPP = DDTPP;
	}



	public Question(Integer id, String question, Boolean isAuditProcess, Boolean isFirstPiece, String commentaire) {
		
		super();
		

			this.id = id;
			this.question = question;
			this.isAuditProcess = isAuditProcess;
			this.isFirstPiece = isFirstPiece;
			this.commentaire = commentaire;
			
			ObservableList<String> EvaluationItems = FXCollections.observableArrayList();
			EvaluationItems.addAll("OK", "NOK", "NA", "NOK_C");
			
				Evaluation.setItems(EvaluationItems); Evaluation.setValue("NA");
				EvaluationTAPAPC1.setItems(EvaluationItems); EvaluationTAPAPC1.setValue("NA");
				EvaluationTAPAPC2.setItems(EvaluationItems); EvaluationTAPAPC2.setValue("NA");
				EvaluationTAPAPC3.setItems(EvaluationItems); EvaluationTAPAPC3.setValue("NA");
				EvaluationTAPAPC4.setItems(EvaluationItems); EvaluationTAPAPC4.setValue("NA");

				//EvaluationTAPAPC1.setOnAction(-);


				EvaluationTAPAPC1.setOnAction(event->{
					if(EvaluationTAPAPC1.getSelectionModel().getSelectedItem().equals("NOK")){
						DDTAP1.setDisable(false);
					}
					else {
						DDTAP1.setDisable(true);
						DDTAP1.setValue(null);
						DDTAP1.setStyle("");
					};
				});
				EvaluationTAPAPC2.setOnAction(event->{
					if(EvaluationTAPAPC2.getSelectionModel().getSelectedItem().equals("NOK")){
						DDTAP2.setDisable(false);
					}
					else {
						DDTAP2.setDisable(true);
						DDTAP2.setValue(null);
						DDTAP2.setStyle("");
					};
				});
				EvaluationTAPAPC3.setOnAction(event->{
					if(EvaluationTAPAPC3.getSelectionModel().getSelectedItem().equals("NOK")){
						DDTAP3.setDisable(false);
					}
					else {
						DDTAP3.setDisable(true);
						DDTAP3.setValue(null);
						DDTAP3.setStyle("");
					};
				});
				EvaluationTAPAPC4.setOnAction(event->{
					if(EvaluationTAPAPC4.getSelectionModel().getSelectedItem().equals("NOK")){
						DDTAP4.setDisable(false);
					}else {
						DDTAP4.setDisable(true);
						DDTAP4.setValue(null);
						DDTAP4.setStyle("");
					};
				});

				EvaluationTPPAPC1.setOnAction(event->{
					if(EvaluationTPPAPC1.getSelectionModel().getSelectedItem().equals("NOK")){
						DDTPP1.setDisable(false);
					}else {
						DDTPP1.setDisable(true);
						DDTPP1.setValue(null);
						DDTPP1.setStyle("");
					};
				});
				EvaluationTPPAPC2.setOnAction(event->{
					if(EvaluationTPPAPC2.getSelectionModel().getSelectedItem().equals("NOK")){
						DDTPP2.setDisable(false);
					}else {
						DDTPP2.setDisable(true);
						DDTPP2.setValue(null);
						DDTPP2.setStyle("");
					};
				});
				EvaluationTPPAPC3.setOnAction(event->{
					if(EvaluationTPPAPC3.getSelectionModel().getSelectedItem().equals("NOK")){
						DDTPP3.setDisable(false);
					}else {
						DDTPP3.setDisable(true);
						DDTPP3.setValue(null);
						DDTPP3.setStyle("");
					};
				});
				EvaluationTPPAPC4.setOnAction(event->{
					if(EvaluationTPPAPC4.getSelectionModel().getSelectedItem().equals("NOK")){
						DDTPP4.setDisable(false);
					}else {
						DDTPP4.setDisable(true);
						DDTPP4.setValue(null);
						DDTPP4.setStyle("");
					};
				});

				DDTAP1.setOnAction(event->{ DDTAP1.setStyle("");});
				DDTAP2.setOnAction(event->{ DDTAP2.setStyle("");});
				DDTAP3.setOnAction(event->{ DDTAP3.setStyle("");});
				DDTAP4.setOnAction(event->{ DDTAP4.setStyle("");});
				DDTPP1.setOnAction(event->{ DDTPP1.setStyle("");});
				DDTPP2.setOnAction(event->{ DDTPP2.setStyle("");});
				DDTPP3.setOnAction(event->{ DDTPP3.setStyle("");});
				DDTPP4.setOnAction(event->{ DDTPP4.setStyle("");});


				EvaluationTPPAPC1.setItems(EvaluationItems); EvaluationTPPAPC1.setValue("NA");
				EvaluationTPPAPC2.setItems(EvaluationItems); EvaluationTPPAPC2.setValue("NA");
				EvaluationTPPAPC3.setItems(EvaluationItems); EvaluationTPPAPC3.setValue("NA");
				EvaluationTPPAPC4.setItems(EvaluationItems); EvaluationTPPAPC4.setValue("NA");

				DDTAP1.setPromptText("DESCRIPTION C1");
				DDTAP2.setPromptText("DESCRIPTION C2");
				DDTAP3.setPromptText("DESCRIPTION C3");
				DDTAP4.setPromptText("DESCRIPTION C4");

				DDTPP1.setPromptText("DESCRIPTION C1");
				DDTPP2.setPromptText("DESCRIPTION C2");
				DDTPP3.setPromptText("DESCRIPTION C3");
				DDTPP4.setPromptText("DESCRIPTION C4");

				/*------------------------Changement-------------------------------*/

				DDTAP1.setDisable(true);
				DDTAP2.setDisable(true);
				DDTAP3.setDisable(true);
				DDTAP4.setDisable(true);

				DDTPP1.setDisable(true);
				DDTPP2.setDisable(true);
				DDTPP3.setDisable(true);
				DDTPP4.setDisable(true);


			ObservableList<Défaut> listDefault = FXCollections.observableArrayList();
			ObservableList<Integer> listDefaultLabel = FXCollections.observableArrayList();
			try {
				listDefault = new DéfautTransaction().getAll();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			if(listDefault!=null){
				for(Défaut défaut: listDefault){
					listDefaultLabel.add(défaut.getCode());
				}
			}

			DDTAP1.setItems(listDefaultLabel);
			DDTAP2.setItems(listDefaultLabel);
			DDTAP3.setItems(listDefaultLabel);
			DDTAP4.setItems(listDefaultLabel);

			DDTPP1.setItems(listDefaultLabel);
			DDTPP2.setItems(listDefaultLabel);
			DDTPP3.setItems(listDefaultLabel);
			DDTPP4.setItems(listDefaultLabel);

	}
	
	




	public Question(String question, Boolean isAuditProcess, Boolean isFirstPiece, String commentaire) {
		super();
		this.question = question;
		this.isAuditProcess = isAuditProcess;
		this.isFirstPiece = isFirstPiece;
		this.commentaire = commentaire;
		DateRéalisationPicker.getStyleClass().clear();

	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	public DatePicker getDateRéalisationPicker() {
		return DateRéalisationPicker;
	}


	public void setDateRéalisationPicker(DatePicker dateRéalisationPicker) {
		DateRéalisationPicker = dateRéalisationPicker;
	}


	public JFXTextField getRespRéalisationTextField() {
		return RespRéalisationTextField;
	}


	public void setRespRéalisationTextField(JFXTextField respRéalisationTextField) {
		RespRéalisationTextField = respRéalisationTextField;
	}


	public JFXTextField getActionsExigéesTextField() {
		return ActionsExigéesTextField;
	}


	public void setActionsExigéesTextField(JFXTextField actionsExigéesTextField) {
		ActionsExigéesTextField = actionsExigéesTextField;
	}


	public JFXTextField getAnomaliesObservéesTextField() {
		return AnomaliesObservéesTextField;
	}


	public void setAnomaliesObservéesTextField(JFXTextField anomaliesObservéesTextField) {
		AnomaliesObservéesTextField = anomaliesObservéesTextField;
	}


	public JFXComboBox<String> getEvaluation() {
		return Evaluation;
	}


	public void setEvaluation(JFXComboBox<String> evaluation) {
		Evaluation = evaluation;
	}






	public Boolean getIsAuditProcess() {
		return isAuditProcess;
	}






	public void setIsAuditProcess(Boolean isAuditProcess) {
		this.isAuditProcess = isAuditProcess;
	}






	public Boolean getIsFirstPiece() {
		return isFirstPiece;
	}






	public void setIsFirstPiece(Boolean isFirstPiece) {
		this.isFirstPiece = isFirstPiece;
	}






	public String getQuestionAP() {
		return questionAP;
	}






	public void setQuestionAP(String questionAP) {
		this.questionAP = questionAP;
	}

	public JFXComboBox<String> getEvaluationTAPAPC1() {
		return EvaluationTAPAPC1;
	}

	public void setEvaluationTAPAPC1(JFXComboBox<String> evaluationTAPAPC1) {
		EvaluationTAPAPC1 = evaluationTAPAPC1;
	}

	public JFXComboBox<String> getEvaluationTAPAPC2() {
		return EvaluationTAPAPC2;
	}

	public void setEvaluationTAPAPC2(JFXComboBox<String> evaluationTAPAPC2) {
		EvaluationTAPAPC2 = evaluationTAPAPC2;
	}

	public JFXComboBox<String> getEvaluationTAPAPC3() {
		return EvaluationTAPAPC3;
	}

	public void setEvaluationTAPAPC3(JFXComboBox<String> evaluationTAPAPC3) {
		EvaluationTAPAPC3 = evaluationTAPAPC3;
	}

	public JFXComboBox<String> getEvaluationTAPAPC4() {
		return EvaluationTAPAPC4;
	}

	public void setEvaluationTAPAPC4(JFXComboBox<String> evaluationTAPAPC4) {
		EvaluationTAPAPC4 = evaluationTAPAPC4;
	}


	public VBox getDDTAP() {
		return DDTAP;
	}

	public void setDDTAP(VBox DDTAP) {
		this.DDTAP = DDTAP;
	}

	public JFXComboBox<Integer> getDDTAP1() {
		return DDTAP1;
	}

	public void setDDTAP1(JFXComboBox<Integer> DDTAP1) {
		this.DDTAP1 = DDTAP1;
	}

	public JFXComboBox<Integer> getDDTAP2() {
		return DDTAP2;
	}

	public void setDDTAP2(JFXComboBox<Integer> DDTAP2) {
		this.DDTAP2 = DDTAP2;
	}

	public JFXComboBox<Integer> getDDTAP3() {
		return DDTAP3;
	}

	public void setDDTAP3(JFXComboBox<Integer> DDTAP3) {
		this.DDTAP3 = DDTAP3;
	}

	public JFXComboBox<Integer> getDDTAP4() {
		return DDTAP4;
	}

	public void setDDTAP4(JFXComboBox<Integer> DDTAP4) {
		this.DDTAP4 = DDTAP4;
	}

	public JFXComboBox<Integer> getDDTPP1() {
		return DDTPP1;
	}

	public void setDDTPP1(JFXComboBox<Integer> DDTPP1) {
		this.DDTPP1 = DDTPP1;
	}

	public JFXComboBox<Integer> getDDTPP2() {
		return DDTPP2;
	}

	public void setDDTPP2(JFXComboBox<Integer> DDTPP2) {
		this.DDTPP2 = DDTPP2;
	}

	public JFXComboBox<Integer> getDDTPP3() {
		return DDTPP3;
	}

	public void setDDTPP3(JFXComboBox<Integer> DDTPP3) {
		this.DDTPP3 = DDTPP3;
	}

	public JFXComboBox<Integer> getDDTPP4() {
		return DDTPP4;
	}

	public void setDDTPP4(JFXComboBox<Integer> DDTPP4) {
		this.DDTPP4 = DDTPP4;
	}
}
