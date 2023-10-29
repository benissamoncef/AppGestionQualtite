package org.openjfx.SGQV2.Administration;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXButton;

import Models.Famille;
import Transactions.FamilleTransaction;
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

public class GestionFamilleController implements Initializable {

    @FXML
    private JFXButton btnAjouterFamille;

    @FXML
    private JFXButton btnModifierFamille;

    @FXML
    private JFXButton BtnRetour;

    @FXML
    private JFXButton btnSupprimerFamille;

    @FXML
    private TableColumn<Famille, String> colCPN;

    @FXML
    private TableColumn<Famille, String> colCommentaire;

    @FXML
    private TableColumn<Famille, Date> colDateCreation;

    @FXML
    private TableColumn<Famille, String> colEPN;

    @FXML
    private TableColumn<Famille, String> colProjet;

    @FXML
    private TableView<Famille> tvFamille;

    @FXML
    void ajouterFamille(ActionEvent event) throws SQLException, IOException {
    	Add_ModFamilleController.IdselectedItem = -1;
    	Add_ModFamilleController.isCreate = true;
        openStage();
    }

    @FXML
    void modifierFamille(ActionEvent event) throws SQLException, IOException {
        if(!tvFamille.getSelectionModel().isEmpty()) {
            int index = tvFamille.getSelectionModel().getSelectedIndex();
            Add_ModFamilleController.IdselectedItem = tvFamille.getItems().get(index).getIdFamille();
            Add_ModFamilleController.isCreate = false;
            openStage();
        }
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
    void supprimerFamille(ActionEvent event) throws SQLException {
        if(!tvFamille.getSelectionModel().isEmpty()) {
        	
        
        	new Alerts().showConfirmation("Delete Famille", "Are you sure want to delete this Famille ?", null);	
    		
    		if(Alerts.option == "deleted") {
    			int selectedIndex = tvFamille.getSelectionModel().getSelectedIndex();
            	
    			new FamilleTransaction().deleteById(tvFamille.getItems().get(selectedIndex).getIdFamille());
            	
    			updateTable();
            	
    			new Alerts().showInformationAlert("Famille Deleted Successfully.");

    		}
        }
    }

    public void openStage() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin//AjouterModifierFamille.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        if(Add_ModFamilleController.isCreate) { stage.setTitle("Ajouter famille");}
        else{ stage.setTitle("Modifier famille");}
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));
        stage.showAndWait();
        updateTable();
    }

    public void updateTable() throws SQLException {
        tvFamille.getItems().clear();
        colEPN.setCellValueFactory(new PropertyValueFactory<Famille, String>("codeFamilleInterne"));
        colCPN.setCellValueFactory(new PropertyValueFactory<Famille, String>("codeFamilleClient"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<Famille, String>("commentaire"));
        colDateCreation.setCellValueFactory(new PropertyValueFactory<Famille, Date>("dateCreation"));
        colProjet.setCellValueFactory(new PropertyValueFactory<Famille, String>("labelProjet"));

        try {
            tvFamille.setItems(new FamilleTransaction().getAll());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEPN.setCellValueFactory(new PropertyValueFactory<Famille, String>("codeFamilleInterne"));
        colCPN.setCellValueFactory(new PropertyValueFactory<Famille, String>("codeFamilleClient"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<Famille, String>("commentaire"));
        colDateCreation.setCellValueFactory(new PropertyValueFactory<Famille, Date>("dateCreation"));
        colProjet.setCellValueFactory(new PropertyValueFactory<Famille, String>("labelProjet"));


        try {
            tvFamille.setItems(new FamilleTransaction().getAll());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
