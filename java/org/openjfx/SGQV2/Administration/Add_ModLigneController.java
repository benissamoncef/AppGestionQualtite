package org.openjfx.SGQV2.Administration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Models.Ligne;
import Models.Zone;
import Transactions.LigneTransaction;
import Transactions.ZoneTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Add_ModLigneController implements Initializable {
	
    public static int IdselectedItem;

    public static boolean isCreate;
    
    ObservableList<Zone> listZone = FXCollections.observableArrayList();
    Ligne l = null;
    Zone z = null;

    @FXML
    private JFXButton btnEnregistrerAjouterLigne;

    @FXML
    private JFXButton btnEnregistrerModifierLigne;

    @FXML
    private JFXComboBox<String> cbZone;

    @FXML
    private Label labelAjouter;

    @FXML
    private Label labelAjouterCommentaireZone;

    @FXML
    private Label labelAjouterIdZone;

    @FXML
    private Label labelAjouterIdZone1;

    @FXML
    private Label labelModifier;

    @FXML
    private JFXTextArea tfCommentaire;

    @FXML
    private JFXTextField tfIdLigne;

    @FXML
    void enregistrerAjouterLigne(ActionEvent event) throws SQLException {

        if(!tfIdLigne.getText().isEmpty() && cbZone.getValue()!=null) {
            
            int id_zone = listZone.get(cbZone.getSelectionModel().getSelectedIndex()).getIdZone(); 
            		
            Ligne ligne = new Ligne(tfIdLigne.getText().toString(),tfCommentaire.getText().toString(),id_zone);
            new LigneTransaction().save(ligne);
            
            new Alerts().showInformationAlert("Ligne Created Successfully.");
            
            Stage stage = (Stage) btnEnregistrerAjouterLigne.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    void enregistrerModifierLigne(ActionEvent event) throws SQLException {
        if(!tfIdLigne.getText().isEmpty() && cbZone.getValue()!=null) {
            
            int idZone = listZone.get(cbZone.getSelectionModel().getSelectedIndex()).getIdZone();

            new LigneTransaction().update(new Ligne (
                    IdselectedItem,
                    tfIdLigne.getText().toString(),
                    tfCommentaire.getText().toString(),
                    idZone

                ));
            
            new Alerts().showInformationAlert("Ligne Updated Successfully.");

            
            Stage stage = (Stage) btnEnregistrerModifierLigne.getScene().getWindow();
            stage.close();

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        if (isCreate) {
            btnEnregistrerAjouterLigne.setVisible(true);
            btnEnregistrerModifierLigne.setVisible(false);
            labelAjouter.setVisible(true);
            labelModifier.setVisible(false);

        } else {
            btnEnregistrerAjouterLigne.setVisible(false);
            btnEnregistrerModifierLigne.setVisible(true);
            labelAjouter.setVisible(false);
            labelModifier.setVisible(true);

            
            try {
                l = new LigneTransaction().getById(IdselectedItem);
                z = new ZoneTransaction().getById(l.getId_zone());           
                cbZone.setValue(z.getLabelZone());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            tfIdLigne.setText(l.getLabelLigne());
            tfCommentaire.setText(l.getCommentaire());

        }

        
        ObservableList<String> listLabelsZone = FXCollections.observableArrayList();

        try {
            listZone  = new ZoneTransaction().getAll();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        if(listZone != null) {


            for(int i = 0; i < listZone.size(); i++) {

                listLabelsZone.add(listZone.get(i).getLabelZone());

            }
        }


        cbZone.setItems(listLabelsZone);

    }

}
