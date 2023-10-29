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
import Models.Reference;
import Transactions.FamilleTransaction;
import Transactions.ProjetTransaction;
import Transactions.ReferenceTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Add_ModRéférenceController implements Initializable {
    public static int IdselectedItem;

    public static boolean isCreate;

    @FXML
    private JFXButton btnEnregistrerAjouterRef;

    @FXML
    private JFXButton btnEnregistrerModifierRef;


    @FXML
    private JFXComboBox<String> cbFamille;

    @FXML
    private JFXComboBox<String> cbProjet;

    @FXML
    private Label labelAjouter;

    @FXML
    private Label labelAjouterCommentaireZone;

    @FXML
    private Label labelAjouterCommentaireZone1;

    @FXML
    private Label labelAjouterCommentaireZone11;

    @FXML
    private Label labelAjouterIdZone;

    @FXML
    private Label labelAjouterIdZone1;

    @FXML
    private Label labelModifier;

    @FXML
    private JFXTextField tfCRC;

    @FXML
    private JFXTextField tfCRI;

    @FXML
    private JFXTextArea tfCommentaire;

    

    @FXML
    void enregistrerAjouterReference(ActionEvent event) throws SQLException {

        if(!tfCRI.getText().isEmpty() && cbProjet.getValue()!=null && cbFamille.getValue()!=null) {
            Projet projet = new ProjetTransaction().getByLabel(cbProjet.getValue());
            Famille famille = new FamilleTransaction().getByEPN(cbFamille.getValue());
            new ReferenceTransaction().save(new Reference(tfCRI.getText(),tfCRC.getText(),tfCommentaire.getText(),projet,famille));
            
            new Alerts().showInformationAlert("Référence Created Successfully.");

            Stage stage = (Stage) btnEnregistrerAjouterRef.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void enregistrerModifierReference(ActionEvent event) throws SQLException {
        if(!tfCRI.getText().isEmpty() && cbProjet.getValue()!=null && cbFamille.getValue()!=null) {
            new ReferenceTransaction().update(new Reference (
                    IdselectedItem,
                    tfCRI.getText().toString(),
                    tfCRC.getText().toString(),
                    tfCommentaire.getText().toString(),
                    new ProjetTransaction().getByLabel(cbProjet.getValue()).getId(),
                    new FamilleTransaction().getByEPN(cbFamille.getValue()).getIdFamille()
            ));
            
            new Alerts().showInformationAlert("Référence Updated Successfully.");

            Stage stage = (Stage) btnEnregistrerModifierRef.getScene().getWindow();
            stage.close();

        }
    }


    

    @FXML
    void updateFamille(MouseEvent event) throws SQLException {
    	cbFamille.getItems().clear();
    	
    	if(cbProjet.getValue() != null ) {

    		
    		String s1 = cbProjet.getValue().toString();
    		
    		int id_projet = new ProjetTransaction().getByLabel(s1).getId();   		
		
    		ObservableList<Famille> listF = new FamilleTransaction().getAll(id_projet);
    		for(int i = 0; i < listF.size(); i++) {
    			cbFamille.getItems().add(listF.get(i).getCodeFamilleInterne());
    		}
  		
    	}

    }
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(isCreate) {
           btnEnregistrerAjouterRef.setVisible(true);
           btnEnregistrerModifierRef.setVisible(false);
            labelAjouter.setVisible(true);
            labelModifier.setVisible(false);

        }

        else {
            btnEnregistrerAjouterRef.setVisible(false);
            btnEnregistrerModifierRef.setVisible(true);
            labelAjouter.setVisible(false);
            labelModifier.setVisible(true);

            Reference r = null;
            Projet p = null;
            Famille f = null;
            try {
                r = new  ReferenceTransaction().getById(IdselectedItem);
                p = new ProjetTransaction().getById(r.getIdProjet());
                f = new FamilleTransaction().getById(r.getIdFamille());


            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

           if(r!=null){
               tfCRC.setText(r.getCodeReferenceClient());
               tfCRI.setText(r.getCodeReferenceInterne());
               tfCommentaire.setText(r.getCommentaire());
               cbFamille.setValue(f.getCodeFamilleInterne());
               cbProjet.setValue(p.getLabelProjet());
           }

        }


        ObservableList<Projet> listProjet = null;
        ObservableList<String> listLabelsProjet = FXCollections.observableArrayList();
        ObservableList<Famille> listFamille = null;
        ObservableList<String> listLabelsFamille = FXCollections.observableArrayList();
        try {
            listProjet  = new ProjetTransaction().getAll();
            listFamille  = new FamilleTransaction().getAll();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        if(listProjet != null) {
            for(int i = 0; i < listProjet.size(); i++) { listLabelsProjet.add(listProjet.get(i).getLabelProjet());  }
        }

        if(listFamille != null) {
            for(int i = 0; i < listFamille.size(); i++) { listLabelsFamille.add(listFamille.get(i).getCodeFamilleInterne());  }
        }


        cbProjet.setItems(listLabelsProjet);
        cbFamille.setItems(listLabelsFamille);
    }
}
