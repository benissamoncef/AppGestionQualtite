package org.openjfx.SGQV2.Administration;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;

import com.jfoenix.controls.JFXButton;

import Models.Reference;
import Transactions.ReferenceTransaction;
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

public class GestionReferenceController implements Initializable {

    @FXML
    private JFXButton btnAjouterReference;

    @FXML
    private JFXButton btnModifierReference;

    @FXML
    private JFXButton btnSupprimerReference;

    @FXML
    private TableColumn<Reference, String> colCRC;

    @FXML
    private TableColumn<Reference, String>  colCRI;

    @FXML
    private TableColumn<Reference, String>  colCommentaire;

    @FXML
    private TableColumn<Reference, Date>  colDateCreation;

    @FXML
    private TableColumn<Reference, String>  colFamille;

    @FXML
    private TableColumn<Reference, String> colProjet;

    @FXML
    private TableView<Reference> tvReference;

    
    @FXML
    private JFXButton btnRetour;
    
    
    @FXML
    void btnRetourClicked(ActionEvent event) throws IOException {
    	
    	HomeController.StartPane = "Home//AdminPane";	    	
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home//Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage thisStage = (Stage) btnRetour.getScene().getWindow();
        thisStage.setScene(scene);
  	
    	
    }
    
    
    @FXML
    void ajouterReference(ActionEvent event) throws SQLException, IOException {
    	Add_ModRéférenceController.IdselectedItem = -1;
    	Add_ModRéférenceController.isCreate = true;
        openStage();
    }

    @FXML
    void modifierReference(ActionEvent event) throws SQLException, IOException {
        if(!tvReference.getSelectionModel().isEmpty()) {
            int index = tvReference.getSelectionModel().getSelectedIndex();
            Add_ModRéférenceController.IdselectedItem = tvReference.getItems().get(index).getIdReference();
            Add_ModRéférenceController.isCreate = false;
            openStage();
        }

    }

    @FXML
    void supprimerReference(ActionEvent event) throws SQLException {
        if(!tvReference.getSelectionModel().isEmpty()) {
        	
        	new Alerts().showConfirmation("Delete Référence", "Are you sure want to delete this Référence ?", null);	
    		
    		if(Alerts.option == "deleted") {
            int selectedIndex = tvReference.getSelectionModel().getSelectedIndex();
            new ReferenceTransaction().deleteById(tvReference.getItems().get(selectedIndex).getIdReference());
            updateTable();
            new Alerts().showInformationAlert("Référence Deleted Successfully.");
           
            
            
    		}
        }
    }


    public void openStage() throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin//AjouterModifierReference.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        if(Add_ModRéférenceController.isCreate) { stage.setTitle("Ajouter reference");}
        else{ stage.setTitle("Modifier reference");}
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));
        stage.showAndWait();
        updateTable();
    }
    public void updateTable() throws SQLException {
        tvReference.getItems().clear();
        colCRI.setCellValueFactory(new PropertyValueFactory<Reference, String>("codeReferenceInterne"));
        colCRC.setCellValueFactory(new PropertyValueFactory<Reference, String>("codeReferenceClient"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<Reference, String>("commentaire"));
        colDateCreation.setCellValueFactory(new PropertyValueFactory<Reference, Date>("dateCreation"));
        colProjet.setCellValueFactory(new PropertyValueFactory<Reference, String>("labelProjet"));
        colFamille.setCellValueFactory(new PropertyValueFactory<Reference, String>("labelFamille"));

        try {
            tvReference.setItems(new ReferenceTransaction().getAll());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colCRI.setCellValueFactory(new PropertyValueFactory<Reference, String>("codeReferenceInterne"));
        colCRC.setCellValueFactory(new PropertyValueFactory<Reference, String>("codeReferenceClient"));
        colCommentaire.setCellValueFactory(new PropertyValueFactory<Reference, String>("commentaire"));
        colDateCreation.setCellValueFactory(new PropertyValueFactory<Reference, Date>("dateCreation"));
        colProjet.setCellValueFactory(new PropertyValueFactory<Reference, String>("labelProjet"));
        colFamille.setCellValueFactory(new PropertyValueFactory<Reference, String>("labelFamille"));

        try {
            tvReference.setItems(new ReferenceTransaction().getAll());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
