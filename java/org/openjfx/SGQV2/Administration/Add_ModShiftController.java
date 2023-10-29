package org.openjfx.SGQV2.Administration;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;

import com.dlsc.gemsfx.TimePicker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;


import Models.Shift;
import Transactions.ShiftTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class Add_ModShiftController implements Initializable{
	
	public static Shift shift;
	
	public static Boolean IsShiftCreate = false;

	 @FXML
	    private JFXButton BtnAjouter;

	    @FXML
	    private JFXButton BtnModifier;


	    @FXML
	    private JFXTextArea CommentaireField;

	    @FXML
	    private TimePicker EndTime;

	    @FXML
	    private JFXTextField LabelShiftField;

	    @FXML
	    private TimePicker StartTime;

	    @FXML
	    void BtnAjouterClicked(ActionEvent event) throws SQLException {
	    	if(!LabelShiftField.getText().isEmpty()) {
	       		
	    		new ShiftTransaction().save(new Shift(   					    				 
	    				LabelShiftField.getText(),
	    				Time.valueOf(StartTime.getTime()),
	    				Time.valueOf(EndTime.getTime()),
	    				CommentaireField.getText()
	    				
	    				));
	    		
	            new Alerts().showInformationAlert("Shift Created Successfully.");

	    	}
	    	Stage stage = (Stage) BtnAjouter.getScene().getWindow();
	    	stage.close();  

	    }


	    @FXML
	    void BtnModifierClicked(ActionEvent event) throws SQLException {

	    	new ShiftTransaction().update(new Shift (
        			shift.getId(),
        			LabelShiftField.getText().toString(),
        			Time.valueOf(StartTime.getTime()),
        			Time.valueOf(EndTime.getTime()),
        			CommentaireField.getText().toString()
        			));
        	
            new Alerts().showInformationAlert("Shift Modified Successfully.");

        	Stage stage = (Stage) BtnModifier.getScene().getWindow();
	    	stage.close();
	    }


		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
			  if(IsShiftCreate) {
				  	BtnModifier.setVisible(false);
				  	BtnModifier.setDisable(true);
				  	BtnAjouter.setVisible(true);
				  	BtnAjouter.setDisable(false);

		        }

		        else {
		        	BtnModifier.setVisible(true);
				  	BtnModifier.setDisable(false);
				  	BtnAjouter.setVisible(false);
				  	BtnAjouter.setDisable(true);
				  	

		            LabelShiftField.setText(shift.getLabelShift());
		            StartTime.setTime(shift.getStartShift().toLocalTime());
		            EndTime.setTime(shift.getEndShift().toLocalTime());
		            CommentaireField.setText(shift.getCommentaire());

		        }
			
			
		}
	
}
