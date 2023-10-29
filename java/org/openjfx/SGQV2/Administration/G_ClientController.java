package org.openjfx.SGQV2.Administration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXButton;

import Models.Client;
import Transactions.ClientTransaction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;

public class G_ClientController implements Initializable {
	
	
	private Stage stage = new Stage();
	
	@FXML
    private JFXButton BtnCreate;

    @FXML
    private JFXButton BtnDelete;

    @FXML
    private JFXButton BtnEdit;
    
    @FXML
    private JFXButton BtnRetour;

    @FXML
    private TableColumn<Client, String> ClientColumn;

    @FXML
    private TableView<Client> ClientTable;

    @FXML
    private TableColumn<Client, String> CommentaireColumn;




	    @FXML
	    void BtnCreateClicked(ActionEvent event) throws IOException, SQLException {
	    	Add_ModClientController.client = null;
	    	Add_ModClientController.isCreateClient = true;    
        	openStage();
	    }

	    @FXML
	    void BtnDeleteClicked(ActionEvent event) throws SQLException {
	    	if(!ClientTable.getSelectionModel().isEmpty()) {
	    		
	    		new Alerts().showConfirmation("Delete Client", "Are you sure want to delete this client ?", null);
	    		
	    		if(Alerts.option == "deleted") {
	    			int selectedIndex = ClientTable.getSelectionModel().getSelectedIndex();
	    			new ClientTransaction().deleteById(ClientTable.getItems().get(selectedIndex).getId()); 
	    			updateTable();
	    			new Alerts().showInformationAlert("Client Deleted Successfully.");
	    			
	    		}
	    		
	        	
	        }
	    }

	    @FXML
	    void BtnEditClicked(ActionEvent event) throws SQLException, IOException {
	    	if(!ClientTable.getSelectionModel().isEmpty()) {
	        	int index = ClientTable.getSelectionModel().getSelectedIndex();
	        	Add_ModClientController.client = ClientTable.getItems().get(index);	        	
	        	Add_ModClientController.isCreateClient = false;        	
	        	openStage();
	        }
	    }
	    
	    private void openStage() throws IOException, SQLException {
	    	stage = new Stage();
	    	 FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin//Add_ModClient.fxml"));
	         Scene scene = new Scene(fxmlLoader.load());
	         stage.setScene(scene);
	         if(Add_ModClientController.isCreateClient) { stage.setTitle("Create Client");}
	         else{ stage.setTitle("Edit Client");}
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
	      
	    
	    private void updateTable() throws SQLException {
	    	ClientTable.getItems().clear();
	    	ClientTable.setItems(new ClientTransaction().getAll());	
	    }
	    
	    
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			ClientColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("labelClient"));
			CommentaireColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("commentaire"));
			
			try {
				ClientTable.setItems(new ClientTransaction().getAll());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

        	    @Override
        	    public void handle(WindowEvent paramT) {
        	        try {
						updateTable();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        	    }
        	});
         
			
		}		

}
