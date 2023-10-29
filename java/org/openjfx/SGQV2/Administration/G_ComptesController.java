package org.openjfx.SGQV2.Administration;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXButton;
import Models.Compte;
import Models.Employee;
import Transactions.CompteTransaction;
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

public class G_ComptesController implements Initializable {

	ObservableList<Employee> EmployeesAdmins;
	
	@FXML
    private TableColumn<Compte, String> AdministrateurColumn;

    @FXML
    private TableView<Compte> CompteTable;

    @FXML
    private JFXButton CreateBtn;

    @FXML
    private TableColumn<Compte, Date> DateCreationColumn;

    @FXML
    private TableColumn<Compte, Date> DateModificationColumn;

    @FXML
    private JFXButton DeleteBtn;

    @FXML
    private JFXButton EditBtn;
    
    @FXML
    private JFXButton BtnRetour;
    
    @FXML
    private TableColumn<Compte, String> EmloyéeColumn;

    @FXML
    private TableColumn<Compte, String> PasswordColumn;

    @FXML
    private TableColumn<Compte, Boolean> StatusColumn;

    @FXML
    private TableColumn<Compte, String> TypeCompteColumn;

    @FXML
    private TableColumn<Compte, String> UsernameColumn;



    @FXML
    void CreateBtnClicked(ActionEvent event) throws IOException, SQLException {
    	Add_ModCompteController.isCreateCompte = true;
    	openStage();
  	
    }
    
    
    @FXML
    void DeleteBtnClicked(ActionEvent event) throws SQLException {
    	if(!CompteTable.getSelectionModel().isEmpty()) {
    		
    		new Alerts().showConfirmation("Delete Account", "Are you sure want to delete this account ?", null);
    		
    		if(Alerts.option == "deleted") {
    			int selectedIndex = CompteTable.getSelectionModel().getSelectedIndex();
    			new CompteTransaction().deleteById(CompteTable.getItems().get(selectedIndex).getId());
    			updateTable();
    			new Alerts().showInformationAlert("Account Deleted Successfully.");
        	}
    	}
    }

    @FXML
    void EditBtnClicked(ActionEvent event) throws SQLException, IOException {
    	if(!CompteTable.getSelectionModel().isEmpty()) {
    		
    		Add_ModCompteController.isCreateCompte = false;
    		
        	int selectedIndex = CompteTable.getSelectionModel().getSelectedIndex();
        	
        	Add_ModCompteController.compte = CompteTable.getItems().get(selectedIndex);
      	
        	openStage();
      	
        }
    }

    
    public void openStage() throws IOException, SQLException {
   	 FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin//Add_ModCompte.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        if(Add_ModCompteController.isCreateCompte) { stage.setTitle("Create Account");}
        else{ stage.setTitle("Edit Compte");}
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
    	CompteTable.getItems().clear();
    	CompteTable.setItems(new CompteTransaction().getAll());
    	
    }
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		EmloyéeColumn.setCellValueFactory(new PropertyValueFactory<Compte, String>("StringNomEmp"));
		TypeCompteColumn.setCellValueFactory(new PropertyValueFactory<Compte, String>("StringTypecompte"));
		UsernameColumn.setCellValueFactory(new PropertyValueFactory<Compte, String>("username"));
		PasswordColumn.setCellValueFactory(new PropertyValueFactory<Compte, String>("password"));
		DateCreationColumn.setCellValueFactory(new PropertyValueFactory<Compte, Date>("date_creation"));
		DateModificationColumn.setCellValueFactory(new PropertyValueFactory<Compte, Date>("date_modification"));
		AdministrateurColumn.setCellValueFactory(new PropertyValueFactory<Compte, String>("StringAdministrateur"));
		StatusColumn.setCellValueFactory(new PropertyValueFactory<Compte, Boolean>("is_online"));		

		
		
		try {
			CompteTable.setItems(new CompteTransaction().getAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		




	

	}
	

}
