package org.openjfx.SGQV2.Administration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Models.Client;
import Models.Model;
import Models.Projet;
import Transactions.ClientTransaction;
import Transactions.ModelTransaction;
import Transactions.ProjetTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Add_ModProjetController implements Initializable{
	
	
	public static Projet projet;
	public static Boolean isCreateProjet;
	
	
	ObservableList<Client> clients = FXCollections.observableArrayList();
	ObservableList<Model> Models = FXCollections.observableArrayList();
	
	
	
	
	@FXML
    private JFXButton BtnAjouter;
	
	@FXML
    private JFXButton BtnModifier;

    @FXML
    private JFXComboBox<String> ClientComboBox;

    @FXML
    private JFXTextArea CommentaireField;

    @FXML
    private JFXTextField ProjetField;

    @FXML
    private JFXComboBox<String> ModelComboBox;
    

    @FXML
    private Label labelAjouterProjet;
    
    @FXML
    private Label labelModifierProjet;

    
    
    
    @FXML
    void BtnAjouterClicked(ActionEvent event) throws SQLException {
    	if(!ClientComboBox.getValue().isEmpty() && !ProjetField.getText().isEmpty() && !ModelComboBox.getValue().isEmpty()) {
   		
    		new ProjetTransaction().save(new Projet(   					    				 
    				ProjetField.getText(),
    				CommentaireField.getText(),
    				new ClientTransaction().getByLabelClient(ClientComboBox.getValue()).getId(),
    				new ModelTransaction().getByLabelModel(ModelComboBox.getValue()).getId()
    				
    				));
    	}
    	
        new Alerts().showInformationAlert("Project Created Successfully.");

    	
    	Stage stage = (Stage) BtnAjouter.getScene().getWindow();
    	stage.close();  	
    	
    }

    
    @FXML
    void BtnModifierClicked(ActionEvent event) throws SQLException {     	
        	new ProjetTransaction().update(new Projet (
        			projet.getId(),
        			ProjetField.getText().toString(),
        			CommentaireField.getText().toString(),
        			clients.get(ClientComboBox.getSelectionModel().getSelectedIndex()).getId(),
        			Models.get(ModelComboBox.getSelectionModel().getSelectedIndex()).getId()
        			));
        	
            new Alerts().showInformationAlert("Project Updated Successfully.");

        	
        	Stage stage = (Stage) BtnModifier.getScene().getWindow();
	    	stage.close();
        }
    	

    
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		// Init clients combobox :
		
		
		
		try {
			clients  = new ClientTransaction().getAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ObservableList<String> ClientsList = FXCollections.observableArrayList();	

		if(clients != null) {
	
			for(int i = 0; i < clients.size(); i++) {
		
				ClientsList.add(clients.get(i).getLabelClient());
		
			}
		}
	

		ClientComboBox.setItems(ClientsList);
		
		
		
		// Init Models combobox :
	
		
		
		try {
			Models  = new ModelTransaction().getAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ObservableList<String> ModelsList = FXCollections.observableArrayList();	

		if(Models != null) {
	
			for(int i = 0; i < Models.size(); i++) {
		
				ModelsList.add(Models.get(i).getLabelModel());
		
			}
		}
	

		ModelComboBox.setItems(ModelsList);
		
		
		
		
		if(isCreateProjet) {
			BtnAjouter.setVisible(true);
			BtnModifier.setVisible(false);
			labelAjouterProjet.setVisible(true);
			labelModifierProjet.setVisible(false);
			
		}
		else {
			
			BtnAjouter.setVisible(false);
			BtnModifier.setVisible(true);
			labelAjouterProjet.setVisible(false);
			labelModifierProjet.setVisible(true);
			

			try {
				ClientComboBox.setValue(new ClientTransaction().getById(projet.getId_client()).getLabelClient());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ProjetField.setText(projet.getLabelProjet());
			
			try {
				ModelComboBox.setValue(new ModelTransaction().getById(projet.getId_model()).getLabelModel());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			CommentaireField.setText(projet.getCommentaire());
			
			
			
		}
		
		
		
	}
	

}
