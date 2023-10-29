package org.openjfx.SGQV2.Administration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Models.Model;
import Transactions.ModelTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Add_ModModelController implements Initializable{
	
	public static Model model;
	
	public static boolean isCreateModel;
	
	@FXML
    private JFXButton BtnAjouter;

    @FXML
    private JFXButton BtnEdit;

    @FXML
    private JFXTextField ModelField;

    @FXML
    private JFXTextArea CommentaireField;

    @FXML
    private Label EditModelLabel;

    @FXML
    private Label NewModelLabel;


    @FXML
    void BtnAjouterClicked(ActionEvent event) throws SQLException {
    	if(!ModelField.getText().isEmpty()) {
    		new ModelTransaction().save(new Model(ModelField.getText(), CommentaireField.getText()));
            new Alerts().showInformationAlert("Model Created Successfully.");

    	}
    	Stage stage = (Stage) BtnAjouter.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void BtnEditClicked(ActionEvent event) throws SQLException {   
    	if(!ModelField.getText().isEmpty()) {
        	new ModelTransaction().update(new Model (
        			model.getId(),
        			ModelField.getText().toString(),
        			CommentaireField.getText().toString()        			    			      	
        			));
        	
            new Alerts().showInformationAlert("Updated Created Successfully.");

        	Stage stage = (Stage) BtnEdit.getScene().getWindow();
        	stage.close();
        	
        }
    	
        
    }

    
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(isCreateModel) {
			BtnAjouter.setVisible(true);
			BtnEdit.setVisible(false);
			NewModelLabel.setVisible(true);
			EditModelLabel.setVisible(false);			
			
		}
		
		else {
			BtnAjouter.setVisible(false);
			BtnEdit.setVisible(true);
			NewModelLabel.setVisible(false);
			EditModelLabel.setVisible(true);	
			
			Model m = null;
			try {
				m = new ModelTransaction().getById(model.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			ModelField.setText(m.getLabelModel());
			CommentaireField.setText(m.getCommentaire());
					
		}
		
		
	}

}
