package org.openjfx.SGQV2.Administration;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXButton;

import Models.Employee;
import Transactions.EmployeeTransaction;
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

public class G_EmployeesController  implements Initializable{

	

	 @FXML
	    private TableColumn<Employee, String> CommentaireColumn;

	    @FXML
	    private JFXButton CreateBtn;

	    @FXML
	    private JFXButton DeleteBtn;
	    
	    @FXML
	    private JFXButton BtnRetour;

	    @FXML
	    private JFXButton EditBtn;

	    @FXML
	    private TableColumn<Employee, String> EmailColumn;

	    @FXML
	    private TableView<Employee> EmployeesTable;

	    @FXML
	    private TableColumn<Employee, Integer> MatriculeColumn;

	    @FXML
	    private TableColumn<Employee, String> MissionColumn;

	    @FXML
	    private TableColumn<Employee, String> NomColumn;

	    @FXML
	    private TableColumn<Employee, String> PosteColumn;

	    @FXML
	    private TableColumn<Employee, String> PrenomColumn;

	    @FXML
	    private TableColumn<Employee, String> TelephoneColumn;

    
	    
	    
	    
	    @FXML
	    void CreateBtnClicked(ActionEvent event) throws IOException, SQLException {
	    	Add_ModEmployeeController.isCreateEmployee = true;
	    	openStage();

	    }
    
    
    @FXML
    void DeleteBtnClicked(ActionEvent event) throws SQLException {
    	if(!EmployeesTable.getSelectionModel().isEmpty()) {
    		
    		new Alerts().showConfirmation("Delete Employee", "Are you sure want to delete this Employee ?", null);	
    		
    		if(Alerts.option == "deleted") {
    			
    			int selectedIndex = EmployeesTable.getSelectionModel().getSelectedIndex();
    			new EmployeeTransaction().deleteByMatricule(EmployeesTable.getItems().get(selectedIndex).getMatricule());
    			updateTable();
    			new Alerts().showInformationAlert("Employee Deleted Successfully.");
    			
    		}
    	}
    }

    @FXML
    void EditBtnClicked(ActionEvent event) throws SQLException, IOException {
    	if(!EmployeesTable.getSelectionModel().isEmpty()) {
    		Add_ModEmployeeController.isCreateEmployee = false;
        	int selectedIndex = EmployeesTable.getSelectionModel().getSelectedIndex();
        	Add_ModEmployeeController.employee = EmployeesTable.getItems().get(selectedIndex);
        	
        	openStage();
    	
        }
    	

    }
    
    
    public void openStage() throws IOException, SQLException {
      	 FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin//Add_ModEmployee.fxml"));
           Scene scene = new Scene(fxmlLoader.load());
           Stage stage = new Stage();
           stage.setScene(scene);
           if(Add_ModEmployeeController.isCreateEmployee) { stage.setTitle("Create Employée");}
           else{ stage.setTitle("Edit Employée");}
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
    	
    	EmployeesTable.getItems().clear();
    	EmployeesTable.setItems(new EmployeeTransaction().getAll());
    	
    }
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		MatriculeColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("matricule"));
		NomColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("nom"));
		PrenomColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("prenom"));
		TelephoneColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("numero_telephone"));
		EmailColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
		MissionColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("mission"));
		PosteColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("poste"));
		CommentaireColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("commentaire"));


	
		
		try {
			EmployeesTable.setItems(new EmployeeTransaction().getAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
