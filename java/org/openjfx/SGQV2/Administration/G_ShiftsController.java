package org.openjfx.SGQV2.Administration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXButton;

import Models.Shift;
import Transactions.ShiftTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class G_ShiftsController implements Initializable{
	
	    @FXML
	    private JFXButton BtnCreate;

	    @FXML
	    private JFXButton BtnDelete;

	    @FXML
	    private JFXButton BtnEdit;

	    @FXML
	    private TableColumn<Shift, String> CommentaireColumn;

	    @FXML
	    private TableColumn<Shift, String> EndTimeColumn;

	    @FXML
	    private TableColumn<Shift, String> ShiftColumn;

	    @FXML
	    private TableView<Shift> ShiftTable;

	    @FXML
	    private TableColumn<Shift, String> StartTimeColumn;
	    
	    @FXML
	    private JFXButton btnRetour;
	    
	    

	    @FXML
	    void BtnCreateClicked(ActionEvent event) throws IOException, SQLException {
	    	Add_ModShiftController.IsShiftCreate = true;
	    	openStage();
	    }

	    @FXML
	    void BtnDeleteClicked(ActionEvent event) throws SQLException {
	    	if(!ShiftTable.getSelectionModel().isEmpty()) {
	    		
	    		new Alerts().showConfirmation("Delete Shift", "Are you sure want to delete this Shift ?", null);	
	    		
	    		if(Alerts.option == "deleted") {
	    			int selectedIndex = ShiftTable.getSelectionModel().getSelectedIndex();
	    			new ShiftTransaction().deleteById(ShiftTable.getItems().get(selectedIndex).getId());
	    			updateTable();
	    			new Alerts().showInformationAlert("Shift Deleted Successfully.");

	    		}
	        }
	    }
	    
	    @FXML
	    void btnRetourClicked(ActionEvent event) throws IOException {
	    	
	    	HomeController.StartPane = "Home//AdminPane";	    	
	    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home//Home.fxml"));
	        Scene scene = new Scene(fxmlLoader.load());
	        Stage thisStage = (Stage) btnRetour.getScene().getWindow();
	        thisStage.setScene(scene);
	    	
	    	
	    	
	    }

	    @FXML
	    void BtnEditClicked(ActionEvent event) throws IOException, SQLException {
	    	if(!ShiftTable.getSelectionModel().isEmpty()) {
	    		Add_ModShiftController.IsShiftCreate = false;
	        	int selectedIndex = ShiftTable.getSelectionModel().getSelectedIndex();
	        	Add_ModShiftController.shift = ShiftTable.getItems().get(selectedIndex);
	        	
	        	openStage();
	    	
	        }
	    }
	    
	    
	    
	    public void openStage() throws IOException, SQLException {
	      	 FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin//Add_ModShift.fxml"));
	           Scene scene = new Scene(fxmlLoader.load());
	           Stage stage = new Stage();
	           stage.setScene(scene);
	           if(Add_ModShiftController.IsShiftCreate) { stage.setTitle("Create Shift");}
	           else{ stage.setTitle("Edit Shift");}
	           stage.initModality(Modality.APPLICATION_MODAL);
	           stage.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));
	           stage.showAndWait(); 
	           updateTable();
	      }

	    
	   private void updateTable() throws SQLException {
		   
		   	ShiftTable.getItems().clear();
			ShiftTable.setItems(new ShiftTransaction().getAll());
	   
	   }
	   
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
			
			ShiftColumn.setCellValueFactory(new PropertyValueFactory<Shift, String>("LabelShift"));
			StartTimeColumn.setCellValueFactory(new PropertyValueFactory<Shift, String>("startShift"));
			EndTimeColumn.setCellValueFactory(new PropertyValueFactory<Shift, String>("endShift"));
			CommentaireColumn.setCellValueFactory(new PropertyValueFactory<Shift, String>("commentaire"));

			
			try {
				ShiftTable.setItems(new ShiftTransaction().getAll());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	    
} 
