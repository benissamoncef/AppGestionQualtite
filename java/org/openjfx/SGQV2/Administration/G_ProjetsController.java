package org.openjfx.SGQV2.Administration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXButton;

import Models.Client;
import Models.Model;
import Models.Projet;
import Transactions.ModelTransaction;
import Transactions.ProjetTransaction;
import javafx.collections.ObservableList;
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

public class G_ProjetsController implements Initializable{
	
	ObservableList<Client> ClientObjects;
	ObservableList<Model> ModelObjects; 
	
	 	
	  @FXML
	    private JFXButton BtnCreate;

	    @FXML
	    private JFXButton BtnDelete;

	    @FXML
	    private JFXButton BtnEdit;

	    @FXML
	    private TableColumn<Projet, String> ClientColumn;

	    @FXML
	    private TableColumn<Projet, String> CommentaireColumn;

	    @FXML
	    private TableColumn<Projet, String> ModelColumn;

	    @FXML
	    private TableColumn<Projet, String> ProjetColumn;

	    @FXML
	    private TableView<Projet> ProjetTable;
	    
	    @FXML
	    private JFXButton BtnRetour;
	
	    
	    
	    @FXML
	    void BtnRetourClicked(ActionEvent event) throws IOException {
	    	
	    	HomeController.StartPane = "Home//AdminPane";	    	
	    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home//Home.fxml"));
	        Scene scene = new Scene(fxmlLoader.load());
	        Stage thisStage = (Stage) BtnRetour.getScene().getWindow();
	        thisStage.setScene(scene);
   	
	    }
	    
	
	    @FXML
	    void BtnEditClicked(ActionEvent event) throws IOException, SQLException {
	    	if(!ProjetTable.getSelectionModel().isEmpty()) {
	    		Add_ModProjetController.isCreateProjet = false;
	        	int selectedIndex = ProjetTable.getSelectionModel().getSelectedIndex();
	        	Add_ModProjetController.projet = ProjetTable.getItems().get(selectedIndex);
	        	
	        	openStage();
	    	
	        }

	    }
	
	
	
	    @FXML
	    void BtnCreateClicked(ActionEvent event) throws IOException, SQLException {
	    	Add_ModProjetController.isCreateProjet = true;
	    	openStage();
  	
	    }

	    @FXML
	    void BtnDeleteClicked(ActionEvent event) throws SQLException {
	    	if(!ProjetTable.getSelectionModel().isEmpty()) {
	    		
	    		new Alerts().showConfirmation("Delete Project", "Are you sure want to delete this Project ?", null);	
	    		
	    		if(Alerts.option == "deleted") {
	    			int selectedIndex = ProjetTable.getSelectionModel().getSelectedIndex();
	        		new ModelTransaction().deleteById(ProjetTable.getItems().get(selectedIndex).getId());
	        		updateTable();
	    			new Alerts().showInformationAlert("Project Deleted Successfully.");

	    		}
	        }
	    		
	    }

	    public void openStage() throws IOException, SQLException {
	      	 FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin//Add_ModProjet.fxml"));
	           Scene scene = new Scene(fxmlLoader.load());
	           Stage stage = new Stage();
	           stage.setScene(scene);
	           if(Add_ModProjetController.isCreateProjet) { stage.setTitle("Create Projet");}
	           else{ stage.setTitle("Edit Projet");}
	           stage.initModality(Modality.APPLICATION_MODAL);
	           stage.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));
	           stage.showAndWait();
	           updateTable();
	      }
    
    
	    private void updateTable() throws SQLException {
	    	
	    	ProjetTable.getItems().clear();
			ProjetTable.setItems(new ProjetTransaction().getAll());
    	
	    }
	    
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			ClientColumn.setCellValueFactory(new PropertyValueFactory<Projet, String>("StringNomClient"));
			ProjetColumn.setCellValueFactory(new PropertyValueFactory<Projet, String>("labelProjet"));
			ModelColumn.setCellValueFactory(new PropertyValueFactory<Projet, String>("StringModel"));
			CommentaireColumn.setCellValueFactory(new PropertyValueFactory<Projet, String>("Commentaire"));

			
			try {
				ProjetTable.setItems(new ProjetTransaction().getAll());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	
		
			
		}
			
		

}
