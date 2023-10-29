package org.openjfx.SGQV2.Administration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Models.Famille;
import Models.Projet;
import Transactions.FamilleTransaction;
import Transactions.ProjetTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Add_ModFamilleController implements Initializable {

    public static int IdselectedItem;

    public static boolean isCreate;
    
    
    
    ObservableList<Projet> listProjet = FXCollections.observableArrayList();
    
    
    
    
    
    
    
    
    @FXML
    private JFXButton btnEnregistrerAjouterFamille;

    @FXML
    private JFXButton btnEnregistrerModifierFamille;

    @FXML
    private JFXComboBox<String> cbProjet;

    @FXML
    private Label labelAjouter;

    @FXML
    private Label labelAjouterCommentaireZone;

    @FXML
    private Label labelAjouterCommentaireZone1;

    @FXML
    private Label labelAjouterIdZone;

    @FXML
    private Label labelAjouterIdZone1;

    @FXML
    private Label labelModifier;

    @FXML
    private JFXTextField tfCPN;

    @FXML
    private JFXTextArea tfCommentaire;

    @FXML
    private JFXTextField tfEPN;

    @FXML
    void enregistrerAjouterFamille(ActionEvent event) throws SQLException {
        if(!tfEPN.getText().isEmpty()) {
            new FamilleTransaction().save(new Famille(tfEPN.getText().toString(),tfCPN.getText().toString(),tfCommentaire.getText().toString(),listProjet.get(cbProjet.getSelectionModel().getSelectedIndex()).getId()));
   		
            new Alerts().showInformationAlert("Famille Created Successfully.");

            Stage stage = (Stage) btnEnregistrerAjouterFamille.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    void enregistrerModifierFamille(ActionEvent event) throws SQLException {
        if(!tfEPN.getText().isEmpty() && cbProjet.getValue()!=null) {
            new FamilleTransaction().update(new Famille (
                    IdselectedItem,
                    tfEPN.getText().toString(),
                    tfCPN.getText().toString(),
                    tfCommentaire.getText().toString(),
                    listProjet.get(cbProjet.getSelectionModel().getSelectedIndex()).getId()
            ));
            
    		new Alerts().showInformationAlert("Famille Updated Successfully.");

            Stage stage = (Stage) btnEnregistrerModifierFamille.getScene().getWindow();
            stage.close();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            if (isCreate) {
                btnEnregistrerAjouterFamille.setVisible(true);
                btnEnregistrerModifierFamille.setVisible(false);
                labelAjouter.setVisible(true);
                labelModifier.setVisible(false);

            } else {
                btnEnregistrerAjouterFamille.setVisible(false);
                btnEnregistrerModifierFamille.setVisible(true);
                labelAjouter.setVisible(false);
                labelModifier.setVisible(true);

                Famille f = null;
                Projet p = null;
                try {
                    f = new FamilleTransaction().getById(IdselectedItem);
                    p = new ProjetTransaction().getById(f.getIdProjet()); 
                    cbProjet.setValue(p.getLabelProjet());
                    
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                tfEPN.setText(f.getCodeFamilleInterne());
                tfCPN.setText(f.getCodeFamilleClient());
                tfCommentaire.setText(f.getCommentaire());

            }

        // Set all Employees that are admins :
       
        ObservableList<String> listLabelsProjet = FXCollections.observableArrayList();

        try {
            listProjet  = new ProjetTransaction().getAll();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        if(listProjet != null) {


            for(int i = 0; i < listProjet.size(); i++) {

                listLabelsProjet.add(listProjet.get(i).getLabelProjet());

            }
        }


        cbProjet.setItems(listLabelsProjet);
    
    }


}
