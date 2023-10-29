package org.openjfx.SGQV2.Administration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXButton;

import Models.Model;
import Transactions.ModelTransaction;
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

public class G_ModelsController implements Initializable{

	@FXML
    private JFXButton BtnCreate;

    @FXML
    private JFXButton BtnDelete;

    @FXML
    private JFXButton BtnEdit;
    
    @FXML
    private JFXButton BtnRetour;

    @FXML
    private TableColumn<Model, String> ModelColumn;

    @FXML
    private TableView<Model> ModelTable;

    @FXML
    private TableColumn<Model, String> CommentaireColumn;




	    @FXML
	    void BtnCreateClicked(ActionEvent event) throws IOException, SQLException {
	    	Add_ModModelController.model = null;
	    	Add_ModModelController.isCreateModel = true;    
        	openStage();
	    }

	    @FXML
	    void BtnDeleteClicked(ActionEvent event) throws SQLException {
	    	if(!ModelTable.getSelectionModel().isEmpty()) {
	    		
	    	
	    		new Alerts().showConfirmation("Delete Model", "Are you sure want to delete this Model ?", null);	
	    		
	    		if(Alerts.option == "deleted") {
	        	int selectedIndex = ModelTable.getSelectionModel().getSelectedIndex();
	        	new ModelTransaction().deleteById(ModelTable.getItems().get(selectedIndex).getId());
	        	updateTable();
    			new Alerts().showInformationAlert("Model Deleted Successfully.");

	    		}
	        }
	    }

	    @FXML
	    void BtnEditClicked(ActionEvent event) throws SQLException, IOException {
	    	if(!ModelTable.getSelectionModel().isEmpty()) {
	        	int index = ModelTable.getSelectionModel().getSelectedIndex();
	        	Add_ModModelController.model = ModelTable.getItems().get(index);	        	
	        	Add_ModModelController.isCreateModel = false;        	
	        	openStage();
	        }
	    }
	    
	    public void openStage() throws IOException, SQLException {
	    	 FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin//Add_ModModel.fxml"));
	         Scene scene = new Scene(fxmlLoader.load());
	         Stage stage = new Stage();
	         stage.setScene(scene);
	         if(Add_ModModelController.isCreateModel) { stage.setTitle("Create Model");}
	         else{ stage.setTitle("Edit Model");}
	         stage.initModality(Modality.APPLICATION_MODAL);
	         stage.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));
	         stage.showAndWait(); 
	         updateTable();
	    }
	    

	    @FXML
	    void BtnRetourClicked(ActionEvent event) throws IOException {
	    	
	    	HomeController.StartPane = "Home//AdminPane";	    	
	    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home//Home.fxml"));
	        Scene scene = new Scene(fxmlLoader.load());
	        Stage thisStage = (Stage) BtnRetour.getScene().getWindow();
	        thisStage.setScene(scene);
    	
	    	
	    }
	      
	    
	    public void updateTable() throws SQLException {
	    	ModelTable.getItems().clear();
			ModelTable.setItems(new ModelTransaction().getAll());
  	
	    }
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			ModelColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("LabelModel"));
			CommentaireColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("commentaire"));
			
			try {
				ModelTable.setItems(new ModelTransaction().getAll());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
}
