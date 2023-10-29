package org.openjfx.SGQV2.GestionQuestions;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Models.Question;
import Transactions.QuestionTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class Add_ModQuestionAPController implements Initializable{
	
	
	
	public static Question questionAP;
	
	public static Boolean isCreateQuestionAP;
	
	@FXML
    private JFXButton BtnAjouter;

    @FXML
    private JFXButton BtnModifier;

    @FXML
    private JFXTextField QuestionField;
    
    @FXML
    private JFXTextArea CommentaireField;

    @FXML
    void BtnAjouterClicked(ActionEvent event) throws SQLException {
    	
    	
    	if(!QuestionField.getText().isEmpty() ) {
    		if(GestionQuestionsController.witchQuestions.equals("AProcess")) {
    		
    			new QuestionTransaction().save(new Question(QuestionField.getText(), true, false, CommentaireField.getText()));  		
    			Stage stage = (Stage) BtnAjouter.getScene().getWindow();
    			stage.close(); 
        	
    		}
    	
    		else if(GestionQuestionsController.witchQuestions.equals("AProduit")) {
    			new QuestionTransaction().save(new Question(QuestionField.getText(), false, false, CommentaireField.getText()));  		
    			Stage stage = (Stage) BtnAjouter.getScene().getWindow();
    			stage.close(); 
    		
    		}
    	
    		else if(GestionQuestionsController.witchQuestions.equals("AProduitP")) {
    			new QuestionTransaction().save(new Question(QuestionField.getText(), false, true, CommentaireField.getText()));  		
    			Stage stage = (Stage) BtnAjouter.getScene().getWindow();
    			stage.close(); 
    				
    		}
    		
    		new Alerts().showInformationAlert("Question Created Successfully.");
    	}
    		
 	
    	
    }

    @FXML
    void BtnModifierClicked(ActionEvent event) throws SQLException {
    	
    	if(!QuestionField.getText().isEmpty()) {
    		
    		if(GestionQuestionsController.witchQuestions.equals("AProcess")) {
    			new QuestionTransaction().update(new Question(questionAP.getId(), QuestionField.getText(), true, false, CommentaireField.getText())); 	
    			Stage stage = (Stage) BtnAjouter.getScene().getWindow();
    			stage.close(); 
    		}
    		
    		else if(GestionQuestionsController.witchQuestions.equals("AProduit")) {
    			new QuestionTransaction().update(new Question(questionAP.getId(), QuestionField.getText(), false, false, CommentaireField.getText())); 	
    			Stage stage = (Stage) BtnAjouter.getScene().getWindow();
    			stage.close(); 
    		}
    		
    		else if(GestionQuestionsController.witchQuestions.equals("AProduitP")) {
    			new QuestionTransaction().update(new Question(questionAP.getId(), QuestionField.getText(), false, true, CommentaireField.getText())); 	
    			Stage stage = (Stage) BtnAjouter.getScene().getWindow();
    			stage.close(); 
    		}
    		
    		new Alerts().showInformationAlert("Question Updated Successfully.");
    		
    	}
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		if(Add_ModQuestionAPController.isCreateQuestionAP) {
			BtnModifier.setVisible(false);	
			BtnAjouter.setVisible(true);	
			
		}
		else {
			BtnModifier.setVisible(true);	
			BtnAjouter.setVisible(false);
	
			QuestionField.setText(questionAP.getQuestion());
			CommentaireField.setText(questionAP.getCommentaire());
	
			
		}
		
		
	}


}
