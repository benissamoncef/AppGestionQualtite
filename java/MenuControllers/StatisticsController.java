package MenuControllers;

import java.io.IOException;

import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class StatisticsController {
	
    @FXML
    private JFXButton DéfautsButton;

    @FXML
    private JFXButton ippmButton;

    @FXML
    void DéfautsButtonClicked(ActionEvent event) throws IOException {
    	
    	loadPane(true);

    }

    @FXML
    void ippmButtonClicked(ActionEvent event) throws IOException {
    	loadPane(false);
    }
    
    private void loadPane(Boolean b) throws IOException {

		Pane pane = (Pane)DéfautsButton.getParent();
		AnchorPane MenuAP = (AnchorPane)pane.getParent();
	
		MenuAP.getChildren().clear();
		
		Pane newPane = null;
		
		
		if(!b)
			 newPane = FXMLLoader.load(App.class.getResource("Home//IPPM_Stats.fxml"));
		else
			 newPane = FXMLLoader.load(App.class.getResource("Home//Défauts_Stats.fxml"));
			
		
		newPane.setPrefHeight(MenuAP.getHeight());
		newPane.setPrefWidth(MenuAP.getWidth());

		MenuAP.getChildren().add(newPane);
    	
    	
    }
    

}
