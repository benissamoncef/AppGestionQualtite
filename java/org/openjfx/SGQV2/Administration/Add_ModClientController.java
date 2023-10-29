package org.openjfx.SGQV2.Administration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Models.Client;
import Transactions.ClientTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Add_ModClientController implements Initializable {
	
	public static Client client;
	
	public static boolean isCreateClient;
	
	@FXML
    private JFXButton BtnAjouter;

    @FXML
    private JFXButton BtnEdit;

    @FXML
    private JFXTextField ClientField;

    @FXML
    private JFXTextArea CommentaireField;

    @FXML
    private Label EditClientLabel;

    @FXML
    private Label NewClientLabel;
    

    @FXML
    void BtnAjouterClicked(ActionEvent event) throws SQLException {
    	if(!ClientField.getText().isEmpty()) {
    		new ClientTransaction().save(new Client(ClientField.getText(), CommentaireField.getText()));
    		new Alerts().showInformationAlert("Client Created Successfully.");
    	}
    	Stage stage = (Stage) BtnAjouter.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void BtnEditClicked(ActionEvent event) throws SQLException {   
    	if(!ClientField.getText().isEmpty()) {
        	new ClientTransaction().update(new Client (
        			client.getId(),
        			ClientField.getText().toString(),
        			CommentaireField.getText().toString()        			    			      	
        			));
        	new Alerts().showInformationAlert("Client Updated Successfully.");
        	Stage stage = (Stage) BtnEdit.getScene().getWindow();
        	stage.close();
        	
        }
    	
        
    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(isCreateClient) {
			BtnAjouter.setVisible(true);
			BtnEdit.setVisible(false);
			NewClientLabel.setVisible(true);
			EditClientLabel.setVisible(false);			
			
		}
		
		else {
			BtnAjouter.setVisible(false);
			BtnEdit.setVisible(true);
			NewClientLabel.setVisible(false);
			EditClientLabel.setVisible(true);	
			
			Client c = null;
			try {
				c = new ClientTransaction().getById(client.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
			ClientField.setText(c.getLabelClient());
			CommentaireField.setText(c.getCommentaire());
					
		}
		
		
	}
    
    

}
