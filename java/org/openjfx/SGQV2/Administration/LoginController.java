package org.openjfx.SGQV2.Administration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Models.Compte;
import Transactions.CompteTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;



public class LoginController implements Initializable {


    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;


    @FXML
    private Label lblWarnUsername;

    @FXML
    private Label lblWarnPassword;
    
    public static Compte LoggerCompte;

    
    @FXML
    void LoginBtnClicked(ActionEvent event) throws SQLException, IOException {
    	handleLogin();
    }
    
    @FXML
    void onEnterKey(KeyEvent event) throws SQLException, IOException {
        if(event.getCode().equals(KeyCode.ENTER)) {
        	handleLogin();
        }
    }
    

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    void closeStage(ActionEvent event) {
    	((Stage) username.getScene().getWindow()).close();
    }
   

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		username.setOnMouseClicked(event -> {
            lblWarnUsername.setVisible(false);
        });
		
		password.setOnMouseClicked(event -> {
            lblWarnPassword.setVisible(false);
        });
		
	}
	
	
	
	
	private void handleLogin() throws SQLException, IOException {
		
		String user = username.getText();
		String pwd = password.getText();
		
		 if (user.equals("")) {
	            lblWarnUsername.setVisible(true);
	     } else if(pwd.equals("")) {
	            lblWarnPassword.setVisible(true);
	     } else {

	    	 LoggerCompte = new CompteTransaction().getByLogIn(user, pwd);
             
             if(LoggerCompte != null) {
            	 switch(LoggerCompte.getId_typecompte()) {
            	 	case 1 : HomeController.StartPane = "Home\\Statistics";break;
            	 	case 2 : HomeController.StartPane = "Home\\Statistics";break;
            	 	case 3 : HomeController.StartPane = "Home\\FormulairesPane";break;
            	 }
            	 
            	 
            	 if(LoggerCompte.getId_typecompte() == 4) {
            		 
            		 
            		 Stage logIn = (Stage) username.getScene().getWindow(); //Getting current window
                     
                     Parent root = null;

                     
                     root = FXMLLoader.load(App.class.getResource("dashboard//dashboard.fxml"));
                     
                     
                	 
                	 
                     Scene s = new Scene(root,1080,1920);
                     
                     logIn.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));
                     
                     
                     logIn.setScene(s);
                     
                     logIn.setFullScreen(true);
                     
                     logIn.centerOnScreen();
            		 
            		 
            		 
            		 
            	 }else {

            	 

                 
                 
                 Stage logIn = (Stage) username.getScene().getWindow(); //Getting current window
                 
                 Parent root = null;

                 
                 root = FXMLLoader.load(App.class.getResource("Home//Home.fxml"));
                 
                 
            	 
            	 
                 Scene s = new Scene(root);
                 
                 logIn.setScene(s);
                 
                 logIn.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));

                 
                 logIn.centerOnScreen();
                 
            	 }
            	 

             } else {
            	 
                 new Alerts().showInformationAlert("Authentification failed : username or password incorrect !");
             }
                 
                 
                 
             }
	    	 
	    	 
	     }
		
		
		

   }
