package org.openjfx.SGQV2.Forms;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.App;
import org.openjfx.SGQV2.Administration.HomeController;
import org.openjfx.SGQV2.Administration.LoginController;
import org.openjfx.SGQV2.Planings.PlaningController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Models.Client;
import Models.Compte;
import Models.Famille;
import Models.Ligne;
import Models.Projet;
import Models.Question;
import Models.ReponseQuestion;
import Models.Zone;
import Transactions.ClientTransaction;
import Transactions.CompteTransaction;
import Transactions.FamilleTransaction;
import Transactions.LigneTransaction;
import Transactions.ProjetTransaction;
import Transactions.QuestionTransaction;
import Transactions.ReponseQuestionAPTransaction;
import Transactions.ZoneTransaction;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RépondreFAPController implements Initializable {
	
	public static Boolean isAdmin = false;
	
	public static Boolean isModAdmin = false;
	
    @FXML
    private TableColumn<Question, JFXTextField> ActionsExigéesColumn;

    @FXML
    private TableColumn<Question, JFXTextField> AnomaliesObservéesColumn;

    @FXML
    private TableColumn<Question, DatePicker> DateRéalisationColumn;

    @FXML
    private TableColumn<Question, JFXComboBox<String>> EvaluationColumn;

    @FXML
    private TableColumn<Question, String> QuestionColumn;

    @FXML
    private TableColumn<Question, JFXTextField> RespRéalisationColumn;

    @FXML
    private TableView<Question> RéponseAPFTable;


    @FXML
    private Label AuditeurValue;

    @FXML
    private Label ClientValue;


    @FXML
    private Label DateaumditValue;


    @FXML
    private Label FamilleValue;

    @FXML
    private Label LigneValue;

    @FXML
    private Label ProjetValue;


    @FXML
    private JFXButton SaveButton;


    @FXML
    private Label ZoneValue;
    
    @FXML
    private JFXButton BtnRetour;
    
    @FXML
    private JFXButton EnregistrerAdmin;
    

	    @FXML
	    void SaveButtonClicked(ActionEvent event) throws SQLException, IOException {
	    	
	    	Date date = null;
	
	    		
	    			for(Question question : RéponseAPFTable.getItems()) {
	    				
	    				date = null;
	    				if(question.getDateRéalisationPicker().getValue() != null)
	    				date  = Date.valueOf(question.getDateRéalisationPicker().getValue());
	    				
	    				
	    				new ReponseQuestionAPTransaction().update(new ReponseQuestion(
	    						new ReponseQuestionAPTransaction().getOne(DéclarerFormController.reponseFormAP.getId(), question.getId()).getId(),
	    						question.getEvaluation().getValue(),
	    						question.getAnomaliesObservéesTextField().getText(),
	    						question.getActionsExigéesTextField().getText(),
	    						question.getRespRéalisationTextField().getText(),	    						
	    						date,  
	    						DéclarerFormController.reponseFormAP.getId(),
	    						question.getId()	
	    						));
	    			}
	    	
	    	
	    	BtnRetourClicked(null);

	    }
	    
	    
	    @FXML
	    void EnregistrerAdminClicked(ActionEvent event) throws SQLException, IOException {
	    	
	    	
	    	Date date = null;
	    	
	    	for(Question question : RéponseAPFTable.getItems()) {
	    		
	    		date = null;    		
	    		if(question.getDateRéalisationPicker().getValue() != null)
	    			date  = Date.valueOf(question.getDateRéalisationPicker().getValue());
	    		
				new ReponseQuestionAPTransaction().update(new ReponseQuestion(
						new ReponseQuestionAPTransaction().getOne(PlaningController.r.getId(), question.getId()).getId(),
						question.getEvaluation().getValue(),
						question.getAnomaliesObservéesTextField().getText(),
						question.getActionsExigéesTextField().getText(),
						question.getRespRéalisationTextField().getText(),	    						
						date,  
						PlaningController.r.getId(),
						question.getId()	
						));
			}
	    	
	    	BtnRetourClicked(null);
	    	
	    }
	    

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			
			ActionsExigéesColumn.setCellValueFactory(new PropertyValueFactory<Question, JFXTextField>("ActionsExigéesTextField"));
			AnomaliesObservéesColumn.setCellValueFactory(new PropertyValueFactory<Question, JFXTextField>("AnomaliesObservéesTextField"));
			DateRéalisationColumn.setCellValueFactory(new PropertyValueFactory<Question, DatePicker >("DateRéalisationPicker"));	
			QuestionColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
			RespRéalisationColumn.setCellValueFactory(new PropertyValueFactory<Question, JFXTextField>("RespRéalisationTextField"));
			EvaluationColumn.setCellValueFactory(new PropertyValueFactory<Question, JFXComboBox<String>>("Evaluation"));

			QuestionColumn.setSortable(true);
			
			EnregistrerAdmin.setVisible(false);
			EnregistrerAdmin.setDisable(true);
			
			
			if(isAdmin) {
				
				BtnRetour.setVisible(false);
				SaveButton.setVisible(false);

				
				BtnRetour.setDisable(true);
				SaveButton.setDisable(true);


				
			}
			else {
				
				if(DéclarerFormController.actionToPerform == 1) {
				
					SaveButton.setVisible(false);

			
					SaveButton.setDisable(true);


					
				}
				
				else {
				
				
				SaveButton.setVisible(true);

				
				
				SaveButton.setDisable(false);

				}

				
				BtnRetour.setVisible(true);
				BtnRetour.setDisable(false);

			}
			
			
		
			
			
			
			// if the Logger is Admin :
			
			if(!isAdmin) {
			
			
				AuditeurValue.setText(LoginController.LoggerCompte.getUsername());
			
			
				Famille f = null;
				Projet p = null;
				Client c = null;
				Ligne l = null;
				Zone z = null;
				
				try {
				
					f = new FamilleTransaction().getById(DéclarerFormController.reponseFormAP.getId_famille());
					p = new ProjetTransaction().getById(f.getIdProjet());
					c = new ClientTransaction().getById(p.getId_client());
					l = new LigneTransaction().getById(DéclarerFormController.reponseFormAP.getId_ligne()); 
					z = new ZoneTransaction().getById(l.getId_zone());
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				if(f != null &&  p != null && c != null && l != null) {
				
					ClientValue.setText(c.getLabelClient());


					ProjetValue.setText(p.getLabelProjet());
				
				
					FamilleValue.setText(f.getCodeFamilleInterne());
				
				
					DateaumditValue.setText(DéclarerFormController.reponseFormAP.getWeek().toString());
				
				
					ZoneValue.setText(z.getLabelZone());
				
					LigneValue.setText(l.getLabelLigne());
					
				}
			} else {
				
				Compte compte = null;
				
				try {
					compte = new CompteTransaction().getById(PlaningController.r.getId_compte_technicien());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(compte != null)
				AuditeurValue.setText(compte.getUsername());
				
				
				Famille f = null;
				Projet p = null;
				Client c = null;
				Ligne l = null;
				Zone z = null;
				
				try {
				
					f = new FamilleTransaction().getById(PlaningController.r.getId_famille());
					p = new ProjetTransaction().getById(f.getIdProjet());
					c = new ClientTransaction().getById(p.getId_client());
					l = new LigneTransaction().getById(PlaningController.r.getId_ligne()); 
					z = new ZoneTransaction().getById(l.getId_zone());
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				if(f != null &&  p != null && c != null && l != null) {
				
					ClientValue.setText(c.getLabelClient());


					ProjetValue.setText(p.getLabelProjet());
				
				
					FamilleValue.setText(f.getCodeFamilleInterne());
				
				
					DateaumditValue.setText(PlaningController.r.getWeek().toString());
				
				
					ZoneValue.setText(z.getLabelZone());
				
					LigneValue.setText(l.getLabelLigne());
					
				}
				
				
				
				
				
				
			}

			ObservableList<Question> listQuestion = null;
			
			try {
				listQuestion = new QuestionTransaction().getAllAPQuestions();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			Boolean b = false;
			Boolean disable = false;
			int id_réponseForm = 0;
			
			if(isAdmin) {
				b = true;
				
				id_réponseForm = PlaningController.r.getId();
				
				if(isModAdmin) {
					disable = false;
					EnregistrerAdmin.setVisible(true);
					EnregistrerAdmin.setDisable(false);
				}
			}
			
			else {
				
				if(DéclarerFormController.actionToPerform != -1)
					b = true;
				
				id_réponseForm = DéclarerFormController.reponseFormAP.getId();
				
				if(DéclarerFormController.actionToPerform == 1) {
					disable = true;
				}	
				
			}
	
			
			if(b) {
		
					for(int i = 0; i < listQuestion.size(); i++) {
						
						ReponseQuestion r = null;
						
						
						try {
								r = new ReponseQuestionAPTransaction().getOne(id_réponseForm , listQuestion.get(i).getId());
								
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
						
						
						
						if(r != null) {
							
								if(r.getActionsExigées() != null)
									listQuestion.get(i).getActionsExigéesTextField().setText(r.getActionsExigées());
								
								if(r.getAnomaliesObservées() != null)
									listQuestion.get(i).getAnomaliesObservéesTextField().setText(r.getAnomaliesObservées());
								
								if(r.getEvaluation() != null)
									listQuestion.get(i).getEvaluation().setValue(r.getEvaluation());
								
								if(r.getDateRéalisation() != null) 
									listQuestion.get(i).getDateRéalisationPicker().setValue(r.getDateRéalisation().toLocalDate());
								
								if(r.getRespRéalisation() != null)
									listQuestion.get(i).getRespRéalisationTextField().setText(r.getRespRéalisation());
							
							if(disable) {
								
								listQuestion.get(i).getActionsExigéesTextField().setDisable(true);
								listQuestion.get(i).getAnomaliesObservéesTextField().setDisable(true);
								listQuestion.get(i).getEvaluation().setDisable(true);
								listQuestion.get(i).getDateRéalisationPicker().setDisable(true);
								listQuestion.get(i).getRespRéalisationTextField().setDisable(true);							
							}
						}

						
					}
					
					RéponseAPFTable.getItems().addAll(listQuestion);		
				
			}
			
			else{
				
				try {
					RéponseAPFTable.setItems(new QuestionTransaction().getAllAPQuestions());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			Date date = null;
			
			if(DéclarerFormController.actionToPerform == -1 ) {
				
				for(Question question : RéponseAPFTable.getItems()) {
					
					if(question.getDateRéalisationPicker().getValue() != null) 
						date  = Date.valueOf(question.getDateRéalisationPicker().getValue());
					
						try {
							new ReponseQuestionAPTransaction().save(new ReponseQuestion(	    						
								question.getEvaluation().getValue(),
								question.getAnomaliesObservéesTextField().getText(),
								question.getActionsExigéesTextField().getText(),
								question.getRespRéalisationTextField().getText(),	    						
								date,  
								DéclarerFormController.reponseFormAP.getId(),
								question.getId()	    						
								));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}
			
				
			}
			

		
			
		}
		

		
		@FXML
	    void BtnRetourClicked(ActionEvent event) throws IOException {

	  		HomeController.StartPane = "Forms//DéclarerFormulaire";	    	
	    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home//Home.fxml"));
	        Scene scene = new Scene(fxmlLoader.load());
	        Stage thisStage = (Stage) BtnRetour.getScene().getWindow();
	        thisStage.setScene(scene);
	  		
	    }
		
		

}
