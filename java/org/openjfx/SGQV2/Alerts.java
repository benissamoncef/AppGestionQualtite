package org.openjfx.SGQV2;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

public class Alerts {
	
	 public static String option = null; 

	// Show a Information Alert 
		public void showInformationAlert(String information) {
			Alert alert = new Alert(AlertType.INFORMATION);
			
			alert.setHeaderText(null);
			alert.setContentText(information);
			alert.setResizable(false);			
			
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));
			alert.showAndWait();
		}
		

		   public void showConfirmation(String title, String content, String header) {

		      Alert alert = new Alert(AlertType.CONFIRMATION);
		      alert.setTitle(title);
		      alert.setHeaderText(content);
		      alert.setContentText(header);
		      
		      alert.setResizable(false);			
				
		      Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			
		      stage.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));

		      // option != null.
		      
		      Optional<ButtonType> option = alert.showAndWait();

		      if (option.get() == null) {
		         Alerts.option = "NoSelection";
		      } else if (option.get() == ButtonType.OK) {
		         Alerts.option = "deleted";

		      } else if (option.get() == ButtonType.CANCEL) {
		         Alerts.option = "Cancelled";
		      } else {
		    	  Alerts.option = "";
		      }
		   }
	

   
}

