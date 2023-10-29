package org.openjfx.SGQV2.Administration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Models.Zone;
import Transactions.ZoneTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Add_ModZoneController implements Initializable {

    @FXML
    private Label labelAjouter;
    @FXML
    private Label labelModifier;
    
    public static int IdselectedItem;

    public static boolean isCreate;

    @FXML
    private JFXButton btnEnregistrerAjouterZone;

    @FXML
    private JFXButton btnEnregistrerModifierZone;

    @FXML
    private Label labelAjouterCommentaireZone;

    @FXML
    private Label labelAjouterIdZone;

    @FXML
    private JFXTextArea tfAjouterCommentaireZone;

    @FXML
    private JFXTextField tfAjouterIdzone;

    @FXML
    void enregistrerAjouterzone(ActionEvent event) throws SQLException {
        if(!tfAjouterIdzone.getText().isEmpty()) {
            new ZoneTransaction().save(new Zone(tfAjouterIdzone.getText(), tfAjouterCommentaireZone.getText()));
            
            new Alerts().showInformationAlert("Zone Created Successfully.");
            
            Stage stage = (Stage) btnEnregistrerAjouterZone.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void enregistrerModifierZone(ActionEvent event) throws SQLException {
        if(!tfAjouterIdzone.getText().isEmpty()) {
            new ZoneTransaction().update(new Zone (
                    IdselectedItem,
                    tfAjouterIdzone.getText().toString(),
                    tfAjouterCommentaireZone.getText().toString()
            ));

            new Alerts().showInformationAlert("Zone Updated Successfully.");

            Stage stage = (Stage) btnEnregistrerModifierZone.getScene().getWindow();
            stage.close();

        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(isCreate) {
            btnEnregistrerAjouterZone.setVisible(true);
            btnEnregistrerModifierZone.setVisible(false);
            labelAjouter.setVisible(true);
            labelModifier.setVisible(false);

        }

        else {
            btnEnregistrerAjouterZone.setVisible(false);
            btnEnregistrerModifierZone.setVisible(true);
            labelAjouter.setVisible(false);
            labelModifier.setVisible(true);

            Zone z = null;
            try {
                z = new ZoneTransaction().getById(IdselectedItem);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            tfAjouterIdzone.setText(z.getLabelZone());
            tfAjouterCommentaireZone.setText(z.getCommentaire());

        }
}
}
