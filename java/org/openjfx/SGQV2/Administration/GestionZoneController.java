package org.openjfx.SGQV2.Administration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXButton;

import Models.Zone;
import Transactions.ZoneTransaction;
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

public class GestionZoneController implements Initializable {

    @FXML
    private JFXButton btnAjouterZone;

    @FXML
    private JFXButton btnModifierZone;

    @FXML
    private JFXButton BtnRetour;

    @FXML
    private JFXButton btnSupprimerZone;

    @FXML
    private TableColumn<Zone,String> colCommentaire;

    @FXML
    private TableColumn<Zone,String> colIdZone;

    @FXML
    private TableView<Zone> tvZone;
    
    

    @FXML
    void ajouterZone(ActionEvent event) throws IOException, SQLException {
    	Add_ModZoneController.IdselectedItem = -1;
    	Add_ModZoneController.isCreate = true;
        openStage();


    }

    @FXML
    void modifierZone(ActionEvent event) throws SQLException, IOException {
        if(!tvZone.getSelectionModel().isEmpty()) {
            int index = tvZone.getSelectionModel().getSelectedIndex();
            Add_ModZoneController.IdselectedItem = tvZone.getItems().get(index).getIdZone();
            Add_ModZoneController.isCreate = false;
            openStage();
        }
    }

    public void openStage() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin//AjouterModifierZone.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        if(Add_ModZoneController.isCreate) { stage.setTitle("Ajouter zone");}
        else{ stage.setTitle("Modifier zone");}
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
    

    @FXML
    void supprimerZone(ActionEvent event) throws SQLException {
        if(!tvZone.getSelectionModel().isEmpty()) {
        	
        	new Alerts().showConfirmation("Delete Zone", "Are you sure want to delete this Zone ?", null);	
    		
    		if(Alerts.option == "deleted") {
            int selectedIndex = tvZone.getSelectionModel().getSelectedIndex();
            
            new ZoneTransaction().deleteById(tvZone.getItems().get(selectedIndex).getIdZone());
            updateTable();
            
            new Alerts().showInformationAlert("Zone Deleted Successfully.");
            
           
    		}
        }

    }

    public void updateTable() throws SQLException {
        tvZone.getItems().clear();
        colIdZone.setCellValueFactory(new PropertyValueFactory<Zone, String>("labelZone"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<Zone, String>("commentaire"));

        try {
            tvZone.setItems(new ZoneTransaction().getAll());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colIdZone.setCellValueFactory(new PropertyValueFactory<Zone, String>("labelZone"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<Zone, String>("commentaire"));

        try {
            tvZone.setItems(new ZoneTransaction().getAll());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
