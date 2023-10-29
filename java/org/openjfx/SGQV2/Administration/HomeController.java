package org.openjfx.SGQV2.Administration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.App;

import Transactions.TypeCompteTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class HomeController implements Initializable {
	
 
    public static String StartPane;
    
    public static String tmpdir;
	
    @FXML
    private VBox HomeMenu;
    
	@FXML
    private Label username = new Label("");
	
	@FXML
    private Label AccessLevel = new Label("");

    @FXML
    private Button btnAdministration;
    
    
    @FXML
    private Button btnImporterDrawing;

    @FXML
    private Button btnSignout;
    
    
    @FXML
    private AnchorPane MenuPane  = null;
    
    private  AnchorPane newPane = null;
    
    @FXML
    private Button btnFormulaires;
    
    @FXML
    private Button btnStatistics;
    
    @FXML
    private Button btnConsulterDrawingAdmin;
    
    @FXML
    private Button btnRéponsesAuxForms;


    @FXML
    void ChangePane(ActionEvent event) throws IOException {
    	
    	if(event.getSource().equals(btnAdministration)) {
    		loadPane("Home//AdminPane");
    	}

    	
    	if(event.getSource().equals(btnFormulaires)) {
 		
    		loadPane("Home//FormulairesPane");
    	}  	
  	
    	if(event.getSource().equals(btnStatistics)) {
     		
    		loadPane("Home//Statistics");
    	}
    	
    	if(event.getSource().equals(btnImporterDrawing)) {
     		
    		loadPane("Home//ImporterDrawing");
    	}
    	
    	if(event.getSource().equals(btnConsulterDrawingAdmin)) {
     		
    		loadPane("Home//DrawingsIngQualité");
    	}
    
    	
    	if(event.getSource().equals(btnRéponsesAuxForms)) {
     		
    		loadPane("Home//Planing");
    	}
    	  	
    }
    
    
    private void loadPane(String path) throws IOException {
    	
    		MenuPane.getChildren().clear(); //Removing previous nodes
    		
    		newPane = FXMLLoader.load(App.class.getResource(path + ".fxml"));

        	newPane.setPrefHeight(MenuPane.getHeight());
        	newPane.setPrefWidth(MenuPane.getWidth());

        	MenuPane.getChildren().add(newPane);
    	
    }
    

    @FXML
    void btnSignoutClicked(ActionEvent event) throws IOException {
    	
    	
    	Stage Home = (Stage)username.getScene().getWindow();
    	Home.close();

        try {
            // Setting login window
            Parent root = FXMLLoader.load(App.class.getResource("Login//LogIn.fxml"));
            Scene scene = new Scene(root);
            Stage logIn = new Stage();
            logIn.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));         
            logIn.setScene(scene);
            logIn.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    	

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		tmpdir = System.getProperty("java.io.tmpdir");
		
	
		username.setText("Username : " + LoginController.LoggerCompte.getUsername());
		
		String access_level = null;
		try {
			access_level = new TypeCompteTransaction().getById(LoginController.LoggerCompte.getId_typecompte()).getLabelType();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		AccessLevel.setText("Access Level : " + access_level);
		
		disableAllButtons();
		HomeMenu.getChildren().remove(3, 10);
		
		switch(access_level) {
		
		case "Admin":{
		
			btnAdministration.setVisible(true);
			btnAdministration.setDisable(false);
			btnStatistics.setVisible(true);
			btnStatistics.setDisable(false);
			btnImporterDrawing.setVisible(true);
			btnImporterDrawing.setDisable(false);
			btnConsulterDrawingAdmin.setVisible(true);	
			btnConsulterDrawingAdmin.setDisable(false);
			btnRéponsesAuxForms.setVisible(true);	
			btnRéponsesAuxForms.setDisable(false);
			
			
			HomeMenu.getChildren().add(btnStatistics);
			HomeMenu.getChildren().add(btnRéponsesAuxForms);
			HomeMenu.getChildren().add(btnConsulterDrawingAdmin);
			HomeMenu.getChildren().add(btnImporterDrawing);
			HomeMenu.getChildren().add(btnAdministration);
			
			
			
			break;
		}
		case "technicienQualité":{
			
			btnConsulterDrawingAdmin.setVisible(true);	
			btnConsulterDrawingAdmin.setDisable(false);
			btnFormulaires.setVisible(true);
			btnFormulaires.setDisable(false);
			
			HomeMenu.getChildren().add(btnConsulterDrawingAdmin);
			HomeMenu.getChildren().add(btnFormulaires);
			
			
			break;
		}
		case "manager":{
			
			btnStatistics.setVisible(false);
			btnStatistics.setDisable(true);
			HomeMenu.getChildren().add(btnStatistics);
			
			break;
		}
	
		}
		
		HomeMenu.getChildren().add(btnSignout);

		

		
		if(StartPane != null) {
			try {
				loadPane(StartPane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private void disableAllButtons() {
		
		btnAdministration.setVisible(false);
		btnAdministration.setDisable(true);
		

		btnFormulaires.setVisible(false);
		btnFormulaires.setDisable(true);
			
		btnStatistics.setVisible(false);
		btnStatistics.setDisable(true);
		
		btnImporterDrawing.setVisible(false);
		btnImporterDrawing.setDisable(true);
		
		btnConsulterDrawingAdmin.setVisible(false);	
		btnConsulterDrawingAdmin.setDisable(true);
		
		btnRéponsesAuxForms.setVisible(false);	
		btnRéponsesAuxForms.setDisable(true);
	}
	

	
	
}
