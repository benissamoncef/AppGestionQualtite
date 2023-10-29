package org.openjfx.SGQV2.Administration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXButton;

import Models.Ligne;
import Transactions.LigneTransaction;
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

public class GestionLigneController implements Initializable {

    @FXML
    private JFXButton btnAjouterLigne;

    @FXML
    private JFXButton btnModifierLigne;

    @FXML
    private JFXButton btnRetour;

    @FXML
    private JFXButton btnSupprimerLigne;

    @FXML
    private TableColumn<Ligne, String> colCommentaire;

    @FXML
    private TableColumn<Ligne, String> colIdLigne;

    @FXML
    private TableColumn<Ligne, String> colZone;

    @FXML
    private TableView<Ligne> tvLigne;

    

    
    
    @FXML
    void BtnRetourClicked(ActionEvent event) throws IOException {
    	
    	HomeController.StartPane = "Home//AdminPane";	    	
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home//Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage thisStage = (Stage) btnRetour.getScene().getWindow();
        thisStage.setScene(scene);
    	
    	
    	
    }
    
    
    @FXML
    void ajouterLigne(ActionEvent event) throws SQLException, IOException {
    	Add_ModLigneController.IdselectedItem = -1;
    	Add_ModLigneController.isCreate = true;
        openStage();
    }

    @FXML
    void modifierLigne(ActionEvent event) throws SQLException, IOException {
        if(!tvLigne.getSelectionModel().isEmpty()) {
            int index = tvLigne.getSelectionModel().getSelectedIndex();
            Add_ModLigneController.IdselectedItem = tvLigne.getItems().get(index).getIdLigne();
            Add_ModLigneController.isCreate = false;
            openStage();
        }
    }

    @FXML
    void supprimerLigne(ActionEvent event) throws SQLException {
        if(!tvLigne.getSelectionModel().isEmpty()) {
        	
        	new Alerts().showConfirmation("Delete Ligne", "Are you sure want to delete this Ligne ?", null);	
    		
    		if(Alerts.option == "deleted") {
    			int selectedIndex = tvLigne.getSelectionModel().getSelectedIndex();
    			new LigneTransaction().deleteById(tvLigne.getItems().get(selectedIndex).getIdLigne());
    			updateTable();
    			new Alerts().showInformationAlert("Ligne Deleted Successfully.");
    		}
        }

    }

    public void openStage() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin//AjouterModifierLigne.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        if(Add_ModLigneController.isCreate) { stage.setTitle("Ajouter Ligne");}
        else{ stage.setTitle("Modifier Ligne");}
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));
        stage.showAndWait();
        updateTable();
    }

    public void updateTable() throws SQLException {
        
        try {
            tvLigne.setItems(new LigneTransaction().getAll());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colIdLigne.setCellValueFactory(new PropertyValueFactory<Ligne, String>("labelLigne"));
        colZone.setCellValueFactory(new PropertyValueFactory<Ligne, String>("StringZone"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<Ligne, String>("commentaire"));

        try {
            tvLigne.setItems(new LigneTransaction().getAll());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
