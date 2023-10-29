package MenuControllers;

import java.io.IOException;

import org.openjfx.SGQV2.App;
import org.openjfx.SGQV2.Forms.DéclarerFormController;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class FormulaireMenuController {
 
    
    @FXML
    private JFXButton BtnAProc;

    @FXML
    private JFXButton BtnAProd;
    
    @FXML
    private JFXButton BtnFormPD;

    @FXML
    void ChangePane(ActionEvent event) throws IOException {
    	
    	if(event.getSource().equals(BtnAProc)) {
    		DéclarerFormController.isFormAP = true;
    		loadPane();
	
    	}
    	
    	if(event.getSource().equals(BtnAProd)) {
    		DéclarerFormController.isFormAproduit = true;		
    		loadPane();
    	}
    	
    	
    	if(event.getSource().equals(BtnFormPD)) {
    		DéclarerFormController.isFormPriseDonnées = true;
    		loadPane();
    	}
    	
    }
    
    
    
    private void loadPane() throws IOException {

		Pane pane = (Pane)BtnAProc.getParent();
		AnchorPane MenuAP = (AnchorPane)pane.getParent();
	
		MenuAP.getChildren().clear(); 
		
		Pane newPane = FXMLLoader.load(App.class.getResource("Forms//DéclarerFormulaire.fxml"));

		newPane.setPrefHeight(MenuAP.getHeight());
		newPane.setPrefWidth(MenuAP.getWidth());

		MenuAP.getChildren().add(newPane);
    	
    	
    }
    

}
