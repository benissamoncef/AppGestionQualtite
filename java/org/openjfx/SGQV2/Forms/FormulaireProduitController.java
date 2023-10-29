package org.openjfx.SGQV2.Forms;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Transactions.*;
import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.App;
import org.openjfx.SGQV2.Administration.HomeController;
import org.openjfx.SGQV2.Administration.LoginController;
import org.openjfx.SGQV2.Planings.PlaningController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Models.Ligne;
import Models.Question;
import Models.Reference;
import Models.ReponseAProdTableau;
import Models.ReponseControles;
import Models.ReponseFormulaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormulaireProduitController implements Initializable{
	
	
	public static Boolean isAdmin1 = false;
	public static Boolean isModAdmin1 = false;
	

    /*-----------------------------TABLEAU DECLARATION PRODUIT:-------------------------------------*/

    //TABLE VIEW
    @FXML   private TableView<Models.Question> TAP;

    //TABLE COLUMNS
   
    @FXML
    private JFXButton BtnRetour;
    
    @FXML
    private TableColumn<Models.Question, JFXComboBox<String>> TapColC1;

    @FXML
    private TableColumn<Question, JFXComboBox<String>> TapColC2;

    @FXML
    private TableColumn<Question, JFXComboBox<String>> TapColC3;

    @FXML
    private TableColumn<Question, JFXComboBox<String>> TapColC4;

    @FXML
    private TableColumn<Question, VBox> TapColDD;

    @FXML
    private TableColumn<Question, String> TapColQ;

    @FXML
    private TableColumn<Question, JFXComboBox<String>> colcr1;

    @FXML
    private TableColumn<Question, JFXComboBox<String>> colcr2;

    @FXML
    private TableColumn<Question, JFXComboBox<String>> colcr3;

    @FXML
    private TableColumn<Question, JFXComboBox<String>> colcr4;

    //TABLE COMBOBOXES:
    @FXML
    private JFXComboBox<String> TapREFc1;
    @FXML
    private JFXComboBox<String> TapREFc2;

    @FXML
    private JFXComboBox<String> TapREFc3;

    @FXML
    private JFXComboBox<String> TapREFc4;




    /*-----------------------------TABLEAU DECLARATION PREMIERE PIECE:-------------------------------------*/

    //TABLE VIEW
    @FXML
    private TableView<Question> TPP;

    //TABLE COLUMNS:
    @FXML
    private TableColumn<Question, VBox> TPPColDD; //COLUMN DESCRIPTION DEFAULT

    @FXML
    private TableColumn<Question, String> TPPColQ;  //COLUMN QUESTION

    @FXML
    private TableColumn<Question, JFXComboBox<String>> TPP1;

    @FXML
    private TableColumn<Question, JFXComboBox<String>> TPP2;

    @FXML
    private TableColumn<Question, JFXComboBox<String>> TPP3;

    @FXML
    private TableColumn<Question, JFXComboBox<String>> TPP4;


    //TABLE TEXT FIELDS:
    @FXML
    private JFXTextField TPPtfnt1; //TEXT FIELD NUMERO TABLEAU 1

    @FXML
    private JFXTextField TPPtfnt2; //TEXT FIELD NUMERO TABLEAU 2

    @FXML
    private JFXTextField TPPtfnt3; //TEXT FIELD NUMERO TABLEAU 3

    @FXML
    private JFXTextField TPPtfnt4; //TEXT FIELD NUMERO TABLEAU 4

    @FXML
    private JFXTextField TPPtfsn1; //TEXT FIELD SERIAL NUMBER 1

    @FXML
    private JFXTextField TPPtfsn2; //TEXT FIELD SERIAL NUMBER 2

    @FXML
    private JFXTextField TPPtfsn3; //TEXT FIELD SERIAL NUMBER 3

    @FXML
    private JFXTextField TPPtfsn4; //TEXT FIELD SERIAL NUMBER 4


    //TABLE COMBOBOXES:
    @FXML
    private JFXComboBox<String> TppREFc1;

    @FXML
    private JFXComboBox<String> TppREFc2;

    @FXML
    private JFXComboBox<String> TppREFc3;

    @FXML
    private JFXComboBox<String> TppREFc4;



    /*-----------------------------CONTROLE VISUEL:-------------------------------------*/

    @FXML
    private JFXTextField cvtfq1; //TEXT FIELD QUESTION1

    @FXML
    private JFXTextField cvtfq2; //TEXT FIELD QUESTION2

    @FXML
    private JFXTextField cvtfq3; //TEXT FIELD QUESTION3

    @FXML
    private JFXTextField cvtfq4; //TEXT FIELD QUESTION4

    @FXML
    private JFXTextField cvtfq5; //TEXT FIELD QUESTION5

    /*-----------------------------CONTROLE DIMENSIENNEL-------------------------------------*/

    @FXML
    private JFXTextField cdtfq1; //TEXT FIELD QUESTION1

    @FXML
    private JFXTextField cdtfq2; //TEXT FIELD QUESTION2

    @FXML
    private JFXTextField cdtfq3; //TEXT FIELD QUESTION3

    @FXML
    private JFXTextField cdtfq4; //TEXT FIELD QUESTION4

    @FXML
    private JFXTextField cdtfq5; //TEXT FIELD QUESTION5

    /*-----------------------------MATRICULE DES POSTES-------------------------------------*/

    @FXML
    private JFXTextField mpctfq1; //TEXT FIELD QUESTION1

    @FXML
    private JFXTextField mpctfq2; //TEXT FIELD QUESTION2

    @FXML
    private JFXTextField mpctfq3; //TEXT FIELD QUESTION3

    @FXML
    private JFXTextField mpctfq4; //TEXT FIELD QUESTION4

    @FXML
    private JFXTextField mpctfq5; //TEXT FIELD QUESTION5

    @FXML
    private JFXTextField mpctfq6; //TEXT FIELD QUESTION6


    /*-----------------------------STATUT-------------------------------------*/
    @FXML
    private JFXComboBox<Boolean> statutcb1;

    @FXML
    private JFXComboBox<Boolean> statutcb2;

    @FXML
    private JFXComboBox<Boolean> statutcb3;


    /*-----------------------------TABLEAU SERIAL NUMBERS-------------------------------------*/

    //TABLE VIEW

    @FXML
    private ListView<String> TSN;
    private int selectedIndex = -1;
    String selectedItem;

    String serialnumbersstring = "";

    //TABLE COLUMNS

    @FXML
    private TableColumn<Question, String> TSNcolSup;

    @FXML
    private TableColumn<String[],String> TSNcolsn;

    // TEXT FIELD AND BUTTON
    @FXML
    private JFXButton TSNbtnAjouter;

    @FXML
    private JFXButton btnSupprimerSN;
    @FXML
    private JFXTextField TSNtf;

    /*-----------------------------GENERAL-------------------------------------*/

    private Boolean isFirstTime = true;


    @FXML
    private JFXButton btnEnregistrer;
    @FXML
    private Label labelDate;

    @FXML
    private Label labelEquipe;

    @FXML
    private Label labelFamille;

    @FXML
    private Label labelLigne;

    @FXML
    private Label labelMatricule;

    @FXML
    private Label labelNomAuditeur;

    @FXML
    private Label labelShift;
    
    @FXML
    private Label labelZone;
    
    @FXML
    private JFXButton BtnEnregistrerModAdmin;



    /*-----------------------------FUNCTIONS-------------------------------------*/

    ObservableList<String> listSN;
    String serialNumbers = new String("");
    String serialNumber = new String("");
    @FXML
    void ajouterSN(ActionEvent event) {


        if(TSNtf.getText() != null){
            TSN.getItems().add(TSNtf.getText());
            TSNtf.setText("");
            serialnumbersstring="";
            for (String str : TSN.getItems()){
                serialnumbersstring += str;
            }
        }

        else{
            /*---------------------------------ALERT------------------------------------------*/
        }

    }



    @FXML
    void supprimerSN(ActionEvent event) {
        if(selectedItem != null){
             TSN.getItems().remove(selectedIndex);
        }
    }

    Boolean verifierChampsTAP(){
        Boolean result = true;
        for (Question question : TAP.getItems()) {
            if(!question.getDDTAP1().isDisable() && question.getDDTAP1().getValue()==null){
                question.getDDTAP1().setStyle(" -fx-background-color: orangered ;");
                result= false;
            };
            if(!question.getDDTAP2().isDisable() && question.getDDTAP2().getValue()==null){
                question.getDDTAP2().setStyle(" -fx-background-color: orangered ;");
                result= false;
            };
            if(!question.getDDTAP3().isDisable() && question.getDDTAP3().getValue()==null){
                question.getDDTAP3().setStyle(" -fx-background-color: orangered ;");
                result= false;
            };
            if(!question.getDDTAP4().isDisable() && question.getDDTAP4().getValue()==null){
                question.getDDTAP4().setStyle(" -fx-background-color: orangered ;");
                result= false;
            };
        }
        return result;
    }

    Boolean verifierChampsTPP(){
        boolean result = true;
        for (Question question : TPP.getItems()) {
            if(!question.getDDTPP1().isDisable() && question.getDDTPP1().getValue()==null){
                question.getDDTPP1().setStyle(" -fx-background-color: orangered ;");
                result = false;
            };
            if(!question.getDDTPP2().isDisable() && question.getDDTPP2().getValue()==null){
                question.getDDTPP2().setStyle(" -fx-background-color: orangered ;");
                result = false;
            };
            if(!question.getDDTPP3().isDisable() && question.getDDTPP3().getValue()==null){
                question.getDDTPP3().setStyle(" -fx-background-color: orangered ;");
                result = false;
            };
            if(!question.getDDTPP4().isDisable() && question.getDDTPP4().getValue()==null){
                question.getDDTPP4().setStyle(" -fx-background-color: orangered ;");
                result = false;
            };
        }
        return result;
    }

    boolean verifierChampsControle(){

            if(
                       cvtfq1.getText() != null
                    && cvtfq2.getText() != null
                    && cvtfq3.getText() != null
                    && cvtfq4.getText() != null
                    && cvtfq5.getText() != null

                    && cdtfq1.getText() != null
                    && cdtfq2.getText() != null
                    && cdtfq3.getText() != null
                    && cdtfq4.getText() != null
                    && cdtfq5.getText() != null

                    && mpctfq1.getText() != null
                    && mpctfq2.getText() != null
                    && mpctfq3.getText() != null
                    && mpctfq4.getText() != null
                    && mpctfq5.getText() != null
                    && mpctfq6.getText() != null

                    && statutcb1.getValue() != null
                    && statutcb2.getValue() != null
                    && statutcb3.getValue() != null

            ) 	return true;
            return false;
    }

    @FXML
    void enregistrerReponse(ActionEvent event) throws SQLException, IOException {
        ReponseAProdTableau reponseAProdTableauTAP = null;
        ReponseAProdTableau reponseAProdTableauTPP = null;
        ReponseControles reponseControles = null;

        if(verifierChampsTAP() && verifierChampsTPP() && TAP.getItems()!=null && TPP.getItems()!=null && verifierChampsControle()){

            if(DéclarerFormController.actionToPerform == -1 && isFirstTime){
    	    isFirstTime = false;

               for (Question question : TAP.getItems()){

                   int refTemp1,refTemp2,refTemp3,refTemp4;
                   refTemp1 = (TapREFc1.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc1.getValue()).getIdReference(): 0;
                   refTemp2 = (TapREFc2.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc2.getValue()).getIdReference(): 0;
                   refTemp3 = (TapREFc3.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc3.getValue()).getIdReference(): 0;
                   refTemp4 = (TapREFc4.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc4.getValue()).getIdReference(): 0;

                   int defTemp1,defTemp2,defTemp3,defTemp4;
                   defTemp1 = (question.getDDTAP1().getValue() != null)? question.getDDTAP1().getValue(): 0;
                   defTemp2 = (question.getDDTAP2().getValue() != null)? question.getDDTAP2().getValue(): 0;
                   defTemp3 = (question.getDDTAP3().getValue() != null)? question.getDDTAP3().getValue(): 0;
                   defTemp4 = (question.getDDTAP4().getValue() != null)? question.getDDTAP4().getValue(): 0;




                   reponseAProdTableauTAP = new ReponseAProdTableau(

                           refTemp1,
                           refTemp2,
                           refTemp3,
                           refTemp4,

                           defTemp1,
                           defTemp2,
                           defTemp3,
                           defTemp4,

                           question.getEvaluationTAPAPC1().getValue(),
                           question.getEvaluationTAPAPC2().getValue(),
                           question.getEvaluationTAPAPC3().getValue(),
                           question.getEvaluationTAPAPC4().getValue(),

                           null,null,null,null,null,null,null,null,

                           question.getId(),DéclarerFormController.reponseFormAProduit.getId(),true);


                   new ReponseAProdTableauTransaction().save(reponseAProdTableauTAP);

               }
               for (Question question : TPP.getItems()) {

                   int refTemp1, refTemp2, refTemp3, refTemp4;
                   refTemp1 = (TppREFc1.getValue() != null) ? new ReferenceTransaction().getByCode(TppREFc1.getValue()).getIdReference() : 0;
                   refTemp2 = (TppREFc2.getValue() != null) ? new ReferenceTransaction().getByCode(TppREFc2.getValue()).getIdReference() : 0;
                   refTemp3 = (TppREFc3.getValue() != null) ? new ReferenceTransaction().getByCode(TppREFc3.getValue()).getIdReference() : 0;
                   refTemp4 = (TppREFc4.getValue() != null) ? new ReferenceTransaction().getByCode(TppREFc4.getValue()).getIdReference() : 0;

                   int defTemp1, defTemp2, defTemp3, defTemp4;
                   defTemp1 = (question.getDDTPP1().getValue() != null) ? question.getDDTPP1().getValue() : 0;
                   defTemp2 = (question.getDDTPP2().getValue() != null) ? question.getDDTPP2().getValue() : 0;
                   defTemp3 = (question.getDDTPP3().getValue() != null) ? question.getDDTPP3().getValue() : 0;
                   defTemp4 = (question.getDDTPP4().getValue() != null) ? question.getDDTPP4().getValue() : 0;

                   String sn1T, sn2T, sn3T, sn4T, nt1T, nt2T, nt3T, nt4T;

                   sn1T = (TPPtfsn1.getText() != null) ? TPPtfsn1.getText() : null;
                   sn2T = (TPPtfsn2.getText() != null) ? TPPtfsn2.getText() : null;
                   sn3T = (TPPtfsn3.getText() != null) ? TPPtfsn3.getText() : null;
                   sn4T = (TPPtfsn4.getText() != null) ? TPPtfsn4.getText() : null;
                   nt1T = (TPPtfnt1.getText() != null) ? TPPtfnt1.getText() : null;
                   nt2T = (TPPtfnt2.getText() != null) ? TPPtfnt2.getText() : null;
                   nt3T = (TPPtfnt3.getText() != null) ? TPPtfnt3.getText() : null;
                   nt4T = (TPPtfnt4.getText() != null) ? TPPtfnt4.getText() : null;


                   reponseAProdTableauTPP = new ReponseAProdTableau(

                           refTemp1,
                           refTemp2,
                           refTemp3,
                           refTemp4,

                           defTemp1,
                           defTemp2,
                           defTemp3,
                           defTemp4,

                           question.getEvaluationTPPAPC1().getValue(),
                           question.getEvaluationTPPAPC2().getValue(),
                           question.getEvaluationTPPAPC3().getValue(),
                           question.getEvaluationTPPAPC4().getValue(),

                           sn1T, sn2T, sn3T, sn4T, nt1T, nt2T, nt3T, nt4T,

                           question.getId(), DéclarerFormController.reponseFormAProduit.getId(), false

                   );
                   new ReponseAProdTableauTransaction().save(reponseAProdTableauTPP);
               }

               int int1,int2,int3,int4,int5,int6,int7,int8,int9,int10;

                int1 = (verifiationChampsEntier(cdtfq1.getText()))? Integer.parseInt(cdtfq1.getText()) : 0;
                int2 = (verifiationChampsEntier(cdtfq2.getText()))? Integer.parseInt(cdtfq2.getText()) : 0;
                int3 = (verifiationChampsEntier(cdtfq3.getText()))? Integer.parseInt(cdtfq3.getText()) : 0;
                int4 = (verifiationChampsEntier(cdtfq4.getText()))? Integer.parseInt(cdtfq4.getText()) : 0;
                int5 = (verifiationChampsEntier(cdtfq5.getText()))? Integer.parseInt(cdtfq5.getText()) : 0;

                int6 = (verifiationChampsEntier(cvtfq1.getText()))? Integer.parseInt(cvtfq1.getText()) : 0;
                int7 = (verifiationChampsEntier(cvtfq2.getText()))? Integer.parseInt(cvtfq2.getText()) : 0;
                int8 = (verifiationChampsEntier(cvtfq3.getText()))? Integer.parseInt(cvtfq3.getText()) : 0;
                int9 = (verifiationChampsEntier(cvtfq4.getText()))? Integer.parseInt(cvtfq4.getText()) : 0;
                int10 = (verifiationChampsEntier(cvtfq5.getText()))? Integer.parseInt(cvtfq5.getText()) : 0;


                reponseControles = new ReponseControles(

                        DéclarerFormController.reponseFormAProduit.getId(),
                        int1,int2,int3,int4,int5,int6,int7,int8,int9,int10,

                        mpctfq1.getText(),
                        mpctfq2.getText(),
                        mpctfq3.getText(),
                        mpctfq4.getText(),
                        mpctfq5.getText(),
                        mpctfq6.getText(),

                        statutcb1.getValue(),
                        statutcb2.getValue(),
                        statutcb3.getValue(),

                        serialnumbersstring

                );

                new ReponseControlesTransaction().save(reponseControles);

            }

            else{

                if(verifierChampsTAP() && TAP.getItems()!=null ){


                    for (Question question : TAP.getItems()){

                        int refTemp1,refTemp2,refTemp3,refTemp4;
                        refTemp1 = (TapREFc1.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc1.getValue()).getIdReference(): 0;
                        refTemp2 = (TapREFc2.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc2.getValue()).getIdReference(): 0;
                        refTemp3 = (TapREFc3.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc3.getValue()).getIdReference(): 0;
                        refTemp4 = (TapREFc4.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc4.getValue()).getIdReference(): 0;

                        int defTemp1,defTemp2,defTemp3,defTemp4;
                        defTemp1 = (question.getDDTAP1().getValue() != null)? question.getDDTAP1().getValue(): 0;
                        defTemp2 = (question.getDDTAP2().getValue() != null)? question.getDDTAP2().getValue(): 0;
                        defTemp3 = (question.getDDTAP3().getValue() != null)? question.getDDTAP3().getValue(): 0;
                        defTemp4 = (question.getDDTAP4().getValue() != null)? question.getDDTAP4().getValue(): 0;

                        int idReponse = new ReponseAProdTableauTransaction().getTapByQ(DéclarerFormController.reponseFormAProduit, question.getId()).getIdReponse();



                        reponseAProdTableauTAP = new ReponseAProdTableau(
                                idReponse,
                                refTemp1,
                                refTemp2,
                                refTemp3,
                                refTemp4,

                                defTemp1,
                                defTemp2,
                                defTemp3,
                                defTemp4,

                                question.getEvaluationTAPAPC1().getValue(),
                                question.getEvaluationTAPAPC2().getValue(),
                                question.getEvaluationTAPAPC3().getValue(),
                                question.getEvaluationTAPAPC4().getValue(),

                                null,null,null,null,null,null,null,null,

                                question.getId(),DéclarerFormController.reponseFormAProduit.getId(),true

                        );


                        try {
                            new ReponseAProdTableauTransaction().update(reponseAProdTableauTAP);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }

                if(verifierChampsTPP() && TPP.getItems()!=null){

                    for (Question question : TPP.getItems()){

                        int refTemp1,refTemp2,refTemp3,refTemp4;
                        refTemp1 = (TppREFc1.getValue() != null)? new ReferenceTransaction().getByCode(TppREFc1.getValue()).getIdReference(): 0;
                        refTemp2 = (TppREFc2.getValue() != null)? new ReferenceTransaction().getByCode(TppREFc2.getValue()).getIdReference(): 0;
                        refTemp3 = (TppREFc3.getValue() != null)? new ReferenceTransaction().getByCode(TppREFc3.getValue()).getIdReference(): 0;
                        refTemp4 = (TppREFc4.getValue() != null)? new ReferenceTransaction().getByCode(TppREFc4.getValue()).getIdReference(): 0;

                        int defTemp1,defTemp2,defTemp3,defTemp4;
                        defTemp1 = (question.getDDTPP1().getValue() != null)? question.getDDTPP1().getValue(): 0;
                        defTemp2 = (question.getDDTPP2().getValue() != null)? question.getDDTPP2().getValue(): 0;
                        defTemp3 = (question.getDDTPP3().getValue() != null)? question.getDDTPP3().getValue(): 0;
                        defTemp4 = (question.getDDTPP4().getValue() != null)? question.getDDTPP4().getValue(): 0;

                        String sn1T,sn2T,sn3T,sn4T,nt1T,nt2T,nt3T,nt4T;

                        sn1T = (TPPtfsn1.getText()!=null)? TPPtfsn1.getText() : null;
                        sn2T = (TPPtfsn2.getText()!=null)? TPPtfsn2.getText() : null;
                        sn3T = (TPPtfsn3.getText()!=null)? TPPtfsn3.getText() : null;
                        sn4T = (TPPtfsn4.getText()!=null)? TPPtfsn4.getText() : null;
                        nt1T = (TPPtfnt1.getText()!=null)? TPPtfnt1.getText() : null;
                        nt2T = (TPPtfnt2.getText()!=null)? TPPtfnt2.getText() : null;
                        nt3T = (TPPtfnt3.getText()!=null)? TPPtfnt3.getText() : null;
                        nt4T = (TPPtfnt4.getText()!=null)? TPPtfnt4.getText() : null;

                        int idReponse = new ReponseAProdTableauTransaction().getTppByQ(DéclarerFormController.reponseFormAProduit, question.getId()).getIdReponse();


                        reponseAProdTableauTPP = new ReponseAProdTableau(
                                idReponse,
                                refTemp1,
                                refTemp2,
                                refTemp3,
                                refTemp4,

                                defTemp1,
                                defTemp2,
                                defTemp3,
                                defTemp4,

                                question.getEvaluationTPPAPC1().getValue(),
                                question.getEvaluationTPPAPC2().getValue(),
                                question.getEvaluationTPPAPC3().getValue(),
                                question.getEvaluationTPPAPC4().getValue(),

                                sn1T,sn2T,sn3T,sn4T,nt1T,nt2T,nt3T,nt4T,

                                question.getId(),DéclarerFormController.reponseFormAProduit.getId(),false

                        );
                        new ReponseAProdTableauTransaction().update(reponseAProdTableauTPP);
                    }

                }

                int int1,int2,int3,int4,int5,int6,int7,int8,int9,int10;

                int1 = (verifiationChampsEntier(cdtfq1.getText()))? Integer.parseInt(cdtfq1.getText()) : 0;
                int2 = (verifiationChampsEntier(cdtfq2.getText()))? Integer.parseInt(cdtfq2.getText()) : 0;
                int3 = (verifiationChampsEntier(cdtfq3.getText()))? Integer.parseInt(cdtfq3.getText()) : 0;
                int4 = (verifiationChampsEntier(cdtfq4.getText()))? Integer.parseInt(cdtfq4.getText()) : 0;
                int5 = (verifiationChampsEntier(cdtfq5.getText()))? Integer.parseInt(cdtfq5.getText()) : 0;

                int6 = (verifiationChampsEntier(cvtfq1.getText()))? Integer.parseInt(cvtfq1.getText()) : 0;
                int7 = (verifiationChampsEntier(cvtfq2.getText()))? Integer.parseInt(cvtfq2.getText()) : 0;
                int8 = (verifiationChampsEntier(cvtfq3.getText()))? Integer.parseInt(cvtfq3.getText()) : 0;
                int9 = (verifiationChampsEntier(cvtfq4.getText()))? Integer.parseInt(cvtfq4.getText()) : 0;
                int10 = (verifiationChampsEntier(cvtfq5.getText()))? Integer.parseInt(cvtfq5.getText()) : 0;

                int idReponseC = new ReponseControlesTransaction().getByIdRF(DéclarerFormController.reponseFormAProduit).getId_reponse_formulaire();
                reponseControles = new ReponseControles(

                        DéclarerFormController.reponseFormAProduit.getId(),idReponseC,
                        int1,int2,int3,int4,int5,int6,int7,int8,int9,int10,
                        mpctfq1.getText(),
                        mpctfq2.getText(),
                        mpctfq3.getText(),
                        mpctfq4.getText(),
                        mpctfq5.getText(),
                        mpctfq6.getText(),

                        statutcb1.getValue(),
                        statutcb2.getValue(),
                        statutcb3.getValue(),

                        serialnumbersstring

                );

                new ReponseControlesTransaction().update(reponseControles);

}

            BtnRetourClicked(null);
           }
        else{
            new Alerts().showInformationAlert("Veuillez remplire tous les champs svp");
        }


    }


    boolean verifiationChampsEntier(String text){
        if(
                !text.isEmpty() && text.matches("[0-9]+")
        ){
            return true;
        }
        return false;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		TapColQ.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        TapColC1.setCellValueFactory(new PropertyValueFactory<  Question, JFXComboBox<String>>("EvaluationTAPAPC1"));
        TapColC2.setCellValueFactory(new PropertyValueFactory<Question, JFXComboBox<String>>("EvaluationTAPAPC2"));
        TapColC3.setCellValueFactory(new PropertyValueFactory<Question, JFXComboBox<String> >("EvaluationTAPAPC3"));
        TapColC4.setCellValueFactory(new PropertyValueFactory<Question, JFXComboBox<String>>("EvaluationTAPAPC4"));
        TapColDD.setCellValueFactory(new PropertyValueFactory<Question, VBox>("DDTAP"));


        TPPColQ.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        TPP1.setCellValueFactory(new PropertyValueFactory<Question, JFXComboBox<String>>("EvaluationTPPAPC1"));
        TPP2.setCellValueFactory(new PropertyValueFactory<Question, JFXComboBox<String>>("EvaluationTPPAPC2"));
        TPP3.setCellValueFactory(new PropertyValueFactory<Question, JFXComboBox<String> >("EvaluationTPPAPC3"));
        TPP4.setCellValueFactory(new PropertyValueFactory<Question, JFXComboBox<String>>("EvaluationTPPAPC4"));
        TPPColDD.setCellValueFactory(new PropertyValueFactory<Question, VBox>("DDTPP"));

        
        
        
        
        
		
		
		if(isAdmin1) {
			if(isModAdmin1) {
				
				BtnEnregistrerModAdmin.setVisible(true);
		        BtnEnregistrerModAdmin.setDisable(false);

			}
			
			BtnEnregistrerModAdmin.setVisible(false);
	        BtnEnregistrerModAdmin.setDisable(true);
			
			BtnRetour.setVisible(false);
	        BtnRetour.setDisable(true);	
			
	        btnEnregistrer.setVisible(false);
	        btnEnregistrer.setDisable(true);
			
		}else {
			
			BtnEnregistrerModAdmin.setVisible(false);
	        BtnEnregistrerModAdmin.setDisable(true);
			
	        btnEnregistrer.setVisible(true);
	        btnEnregistrer.setDisable(false);
			
	        BtnRetour.setVisible(true);
	        BtnRetour.setDisable(false);	
	        
			
		}
        
   
        
        ObservableList<Boolean> EvaluationItems = FXCollections.observableArrayList();
        EvaluationItems.addAll(true,false);

        statutcb1.setItems(EvaluationItems);
        statutcb2.setItems(EvaluationItems);
        statutcb3.setItems(EvaluationItems);



        statutcb1.setValue(true);
        statutcb2.setValue(true);
        statutcb3.setValue(true);


        String loggerUsername = null;
        Date createdAt = null;
        String labelFamilleValue = null;
        String labelShiftValue = null;
        String EquipeValue = null;
        Ligne l = null;
        String labelZoneValue = null;
        
        if(!isAdmin1) {
        	
        	 loggerUsername = LoginController.LoggerCompte.getUsername();
        	 
        	 
        	 createdAt = DéclarerFormController.reponseFormAProduit.getCreatedAt();
        	 
        	 
        	 try {
				labelFamilleValue = new FamilleTransaction().getById(DéclarerFormController.reponseFormAProduit.getId_famille()).getCodeFamilleInterne();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 
        	 
        	 try {
        		 
				labelShiftValue = new ShiftTransaction().getById(DéclarerFormController.reponseFormAProduit.getId_shift()).getLabelShift();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 
        	 
        	 EquipeValue = DéclarerFormController.reponseFormAProduit.getEquipe();    
        	 
        	 try {
				l = new LigneTransaction().getById(DéclarerFormController.reponseFormAProduit.getId_ligne());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

   	
       
        
        }else {
        	
        	
        	try {
				loggerUsername = new CompteTransaction().getById(PlaningController.r.getId_compte_technicien()).getUsername();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
       	 
       	 
       	 	createdAt = PlaningController.r.getCreatedAt();
       	 
       	 
       	 try {
			labelFamilleValue = new FamilleTransaction().getById(PlaningController.r.getId_famille()).getCodeFamilleInterne();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       	 
       	 
       	labelShiftValue = PlaningController.r.getLabelShift();
       	 
       	 
       	EquipeValue = PlaningController.r.getEquipe();    
       	 
       	 	try {
				l = new LigneTransaction().getById(PlaningController.r.getId_ligne());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	    	 
	
        }

        if(l != null)
        	
			try {
				labelZoneValue = new ZoneTransaction().getById(l.getId_zone()).getLabelZone();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
        
        
        
        labelNomAuditeur.setText(loggerUsername);
        labelMatricule.setText(loggerUsername);
        labelDate.setText(createdAt.toString());
        labelFamille.setText(labelFamilleValue);
        labelShift.setText(labelShiftValue);
        labelEquipe.setText(EquipeValue);
        labelLigne.setText(l.getLabelLigne());
        labelZone.setText(labelZoneValue);
        

		ObservableList<Question> listQuestionTAP = null;
        ObservableList<Question> listQuestionTPP = null;
        ObservableList<String> sNumbers = FXCollections.observableArrayList();

		try {

            listQuestionTAP = new QuestionTransaction().getAllAProduit();
            listQuestionTPP = new QuestionTransaction().getAllAProduitFirstPiece();


            TAP.setItems(listQuestionTAP);
            TPP.setItems(listQuestionTPP);

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}





		TSN.setOnMouseClicked(event->{
            selectedItem = TSN.getSelectionModel().getSelectedItem();
            selectedIndex = TSN.getSelectionModel().getSelectedIndex();
            TSNtf.setText(selectedItem);
        });

        ObservableList<Reference> listRef = null;
        ObservableList<String> listLabelsRef = FXCollections.observableArrayList();
        int idFamille = 0;
        int idProjet = 0;

        try {
            if(isAdmin1){
                 idFamille = PlaningController.r.getId_famille();
                 idProjet = new FamilleTransaction().getById(PlaningController.r.getId_famille()).getIdProjet();

            } else {
                idFamille = DéclarerFormController.reponseFormAProduit.getId_famille();
                idProjet = new FamilleTransaction().getById(DéclarerFormController.reponseFormAProduit.getId_famille()).getIdProjet();
            }

            listRef  = new ReferenceTransaction().getAll(idProjet,idFamille);

        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        if(listRef != null) {


            for(int i = 0; i < listRef.size(); i++) {
                listLabelsRef.add(listRef.get(i).getCodeReferenceInterne());
            }
        }

        TapREFc1.setItems(listLabelsRef);
        TapREFc2.setItems(listLabelsRef);
        TapREFc3.setItems(listLabelsRef);
        TapREFc4.setItems(listLabelsRef);


        TppREFc1.setItems(listLabelsRef);
        TppREFc2.setItems(listLabelsRef);
        TppREFc3.setItems(listLabelsRef);
        TppREFc4.setItems(listLabelsRef);


        
        
        
        boolean b = false;
		boolean disable = false;

		ReponseFormulaire réponseForm = null;
		
		if(isAdmin1) {
			b = true;
			
			réponseForm = PlaningController.r;
	
		}
		
		else {
			b = false;
            réponseForm = DéclarerFormController.reponseFormAProduit;
	
		}

        if(b){

            btnEnregistrer.setText("MODIFIER");
            try {
                ReponseControles reponseControles = new ReponseControlesTransaction().getByIdRF(réponseForm);
                ReponseAProdTableau reponseAProdTableauTAP = new ReponseAProdTableauTransaction().getTapByRF(réponseForm);
                ReponseAProdTableau reponseAProdTableauTPP = new ReponseAProdTableauTransaction().getTppByRF(réponseForm);





                //INITIALISATION DU TAP :

                String s1,s2,s3,s4;

                s1= (new ReferenceTransaction().getById(reponseAProdTableauTAP.getIdRef1()) != null )? new ReferenceTransaction().getById(reponseAProdTableauTAP.getIdRef1()).getCodeReferenceInterne():null;
                s2= (new ReferenceTransaction().getById(reponseAProdTableauTAP.getIdRef2()) != null )? new ReferenceTransaction().getById(reponseAProdTableauTAP.getIdRef2()).getCodeReferenceInterne():null;
                s3= (new ReferenceTransaction().getById(reponseAProdTableauTAP.getIdRef3()) != null )? new ReferenceTransaction().getById(reponseAProdTableauTAP.getIdRef3()).getCodeReferenceInterne():null;
                s4= (new ReferenceTransaction().getById(reponseAProdTableauTAP.getIdRef4()) != null )? new ReferenceTransaction().getById(reponseAProdTableauTAP.getIdRef4()).getCodeReferenceInterne():null;

                TapREFc1.setValue(s1);
                TapREFc2.setValue(s2);
                TapREFc3.setValue(s3);
                TapREFc4.setValue(s4);

                for(Question question : TAP.getItems()){

                    ReponseAProdTableau reponseAProdTableau = new ReponseAProdTableauTransaction().getTapByQ(réponseForm, question.getId());
                    question.getEvaluationTAPAPC1().setValue(reponseAProdTableau.getStatut1());

                    question.getEvaluationTAPAPC2().setValue(reponseAProdTableau.getStatut2());
                    question.getEvaluationTAPAPC3().setValue(reponseAProdTableau.getStatut3());
                    question.getEvaluationTAPAPC4().setValue(reponseAProdTableau.getStatut4());

                    question.getDDTAP1().setValue(reponseAProdTableau.getIdDD1());
                    question.getDDTAP2().setValue(reponseAProdTableau.getIdDD2());
                    question.getDDTAP3().setValue(reponseAProdTableau.getIdDD3());
                    question.getDDTAP4().setValue(reponseAProdTableau.getIdDD4());


                    question.getDDTAP1().setPromptText(String.valueOf(reponseAProdTableau.getIdDD1()));
                    question.getDDTAP2().setPromptText(String.valueOf(reponseAProdTableau.getIdDD2()));
                    question.getDDTAP3().setPromptText(String.valueOf(reponseAProdTableau.getIdDD3()));
                    question.getDDTAP4().setPromptText(String.valueOf(reponseAProdTableau.getIdDD4()));
                    
                    
                    if(disable) {
                    	
                        question.getEvaluationTAPAPC1().setDisable(true); 
                        question.getEvaluationTAPAPC2().setDisable(true); 
                        question.getEvaluationTAPAPC3().setDisable(true); 
                        question.getEvaluationTAPAPC4().setDisable(true); 

                        question.getDDTAP1().setDisable(true); 
                        question.getDDTAP2().setDisable(true); 
                        question.getDDTAP3().setDisable(true); 
                        question.getDDTAP4().setDisable(true); 

                    }
                    
                    

                }


                //INITIALISATION DU TPP :

                String s5,s6,s7,s8;

                s5= (new ReferenceTransaction().getById(reponseAProdTableauTPP.getIdRef1()) != null )? new ReferenceTransaction().getById(reponseAProdTableauTPP.getIdRef1()).getCodeReferenceInterne():null;
                s6= (new ReferenceTransaction().getById(reponseAProdTableauTPP.getIdRef2()) != null )? new ReferenceTransaction().getById(reponseAProdTableauTPP.getIdRef2()).getCodeReferenceInterne():null;
                s7= (new ReferenceTransaction().getById(reponseAProdTableauTPP.getIdRef3()) != null )? new ReferenceTransaction().getById(reponseAProdTableauTPP.getIdRef3()).getCodeReferenceInterne():null;
                s8= (new ReferenceTransaction().getById(reponseAProdTableauTPP.getIdRef4()) != null )? new ReferenceTransaction().getById(reponseAProdTableauTPP.getIdRef4()).getCodeReferenceInterne():null;

                TppREFc1.setValue(s5); TPPtfnt1.setText(reponseAProdTableauTPP.getNt1()); TPPtfsn1.setText(reponseAProdTableauTPP.getSn1());
                TppREFc2.setValue(s6); TPPtfnt2.setText(reponseAProdTableauTPP.getNt2()); TPPtfsn2.setText(reponseAProdTableauTPP.getSn2());
                TppREFc3.setValue(s7); TPPtfnt3.setText(reponseAProdTableauTPP.getNt3()); TPPtfsn3.setText(reponseAProdTableauTPP.getSn3());
                TppREFc4.setValue(s8); TPPtfnt4.setText(reponseAProdTableauTPP.getNt4()); TPPtfsn4.setText(reponseAProdTableauTPP.getSn4());

                for(Question question : TPP.getItems()){
                    ReponseAProdTableau reponseAProdTableau = new ReponseAProdTableauTransaction().getTppByQ(réponseForm, question.getId());
                    question.getEvaluationTPPAPC1().setValue(reponseAProdTableau.getStatut1());
                    question.getEvaluationTPPAPC2().setValue(reponseAProdTableau.getStatut2());
                    question.getEvaluationTPPAPC3().setValue(reponseAProdTableau.getStatut3());
                    question.getEvaluationTPPAPC4().setValue(reponseAProdTableau.getStatut4());

                    question.getDDTPP1().setValue(reponseAProdTableau.getIdDD1());
                    question.getDDTPP2().setValue(reponseAProdTableau.getIdDD2());
                    question.getDDTPP3().setValue(reponseAProdTableau.getIdDD3());
                    question.getDDTPP4().setValue(reponseAProdTableau.getIdDD4());

                    question.getDDTPP1().setPromptText(String.valueOf(reponseAProdTableau.getIdDD1()));
                    question.getDDTPP2().setPromptText(String.valueOf(reponseAProdTableau.getIdDD2()));
                    question.getDDTPP3().setPromptText(String.valueOf(reponseAProdTableau.getIdDD3()));
                    question.getDDTPP4().setPromptText(String.valueOf(reponseAProdTableau.getIdDD4()));
                    
                    if(disable) {
                    	
                        question.getEvaluationTPPAPC1().setDisable(true);
                        question.getEvaluationTPPAPC2().setDisable(true);
                        question.getEvaluationTPPAPC3().setDisable(true);
                        question.getEvaluationTPPAPC4().setDisable(true);

                        question.getDDTPP1().setDisable(true);
                        question.getDDTPP2().setDisable(true);
                        question.getDDTPP3().setDisable(true);
                        question.getDDTPP4().setDisable(true);

                    }


                }

                String str1 = (reponseControles.getQ1() != null)? String.valueOf(reponseControles.getQ1()) : "";
                String str2 = (reponseControles.getQ2() != null)? String.valueOf(reponseControles.getQ2()) : "";
                String str3 = (reponseControles.getQ3() != null)? String.valueOf(reponseControles.getQ3()) : "";
                String str4 = (reponseControles.getQ4() != null)? String.valueOf(reponseControles.getQ4()) : "";
                String str5 = (reponseControles.getQ5() != null)? String.valueOf(reponseControles.getQ5()) : "";
                String str6 = (reponseControles.getQ6() != null)? String.valueOf(reponseControles.getQ6()) : "";
                String str7 = (reponseControles.getQ7() != null)? String.valueOf(reponseControles.getQ7()) : "";
                String str8 = (reponseControles.getQ8() != null)? String.valueOf(reponseControles.getQ8()) : "";
                String str9 = (reponseControles.getQ9() != null)? String.valueOf(reponseControles.getQ9()) : "";
                String str10 = (reponseControles.getQ10() != null)? String.valueOf(reponseControles.getQ10()) : "";


               
                //INITIALISATION DU TABLEAU SERIAL NUMBERS :

                cdtfq1.setText(str1);
                cdtfq2.setText(str2);
                cdtfq3.setText(str3);
                cdtfq4.setText(str4);
                cdtfq5.setText(str5);

                cvtfq1.setText(str6);
                cvtfq2.setText(str7);
                cvtfq3.setText(str8);
                cvtfq4.setText(str9);
                cvtfq5.setText(str10);

                mpctfq1.setText(reponseControles.getQ11());
                mpctfq2.setText(reponseControles.getQ12());
                mpctfq3.setText(reponseControles.getQ13());
                mpctfq4.setText(reponseControles.getQ14());
                mpctfq5.setText(reponseControles.getQ15());
                mpctfq6.setText(reponseControles.getQ16());

                statutcb1.setValue(reponseControles.getQ17());
                statutcb2.setValue(reponseControles.getQ18());
                statutcb3.setValue(reponseControles.getQ19());

                int index=0;

                for (int i = 0; i<reponseControles.getSerialNumbers().length()/4; i++){
                    String temp = reponseControles.getSerialNumbers().substring(index,index+4);
                    sNumbers.add(temp);
                    index+=4;

                }
                
                TSN.getItems().addAll(sNumbers);

                
                



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
            
            
            
            if(disable) {

                TapREFc1.setDisable(true);
                TapREFc2.setDisable(true);
                TapREFc3.setDisable(true);
                TapREFc4.setDisable(true);

                TppREFc1.setDisable(true); TPPtfnt1.setDisable(true); TPPtfsn1.setDisable(true);
                TppREFc2.setDisable(true); TPPtfnt2.setDisable(true); TPPtfsn2.setDisable(true);
                TppREFc3.setDisable(true); TPPtfnt3.setDisable(true); TPPtfsn3.setDisable(true);
                TppREFc4.setDisable(true); TPPtfnt4.setDisable(true); TPPtfsn4.setDisable(true);

                cdtfq1.setDisable(true);
                cdtfq2.setDisable(true);
                cdtfq3.setDisable(true);
                cdtfq4.setDisable(true);
                cdtfq5.setDisable(true);

                cvtfq1.setDisable(true);
                cvtfq2.setDisable(true);
                cvtfq3.setDisable(true);
                cvtfq4.setDisable(true);
                cvtfq5.setDisable(true);
                
                
                mpctfq1.setDisable(true);
                mpctfq2.setDisable(true);
                mpctfq3.setDisable(true);
                mpctfq4.setDisable(true);
                mpctfq5.setDisable(true);
                mpctfq6.setDisable(true);

                statutcb1.setDisable(true);
                statutcb2.setDisable(true);
                statutcb3.setDisable(true);

            	
                //TSN.setDisable(true);
                TSNtf.setDisable(true);
                btnSupprimerSN.setDisable(true);
                TSNbtnAjouter.setDisable(true);
                btnEnregistrer.setDisable(true);
            	
            }
            
           
 }

    }
	
	  	@FXML
	    void BtnRetourClicked(ActionEvent event) throws IOException {

	  		HomeController.StartPane = "Forms//DéclarerFormulaire";	    	
	    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home//Home.fxml"));
	        Scene scene = new Scene(fxmlLoader.load());
	        Stage thisStage = (Stage) BtnRetour.getScene().getWindow();
	        thisStage.setScene(scene);
	  		
	    }
	    @FXML
	    void BtnEnregistrerModAdminClicked(ActionEvent event) throws SQLException {
	    	
	    	  	ReponseAProdTableau reponseAProdTableauTAP = null;
	            ReponseAProdTableau reponseAProdTableauTPP = null;
	            ReponseControles reponseControles = null;

	           if(verifierChampsTAP() && verifierChampsTPP() && TAP.getItems()!=null && TPP.getItems()!=null && verifierChampsControle() ){


	               for (Question question : TAP.getItems()){

	                   int refTemp1,refTemp2,refTemp3,refTemp4;

	                   refTemp1 = (TapREFc1.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc1.getValue()).getIdReference(): 0;
	                   refTemp2 = (TapREFc2.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc2.getValue()).getIdReference(): 0;
	                   refTemp3 = (TapREFc3.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc3.getValue()).getIdReference(): 0;
	                   refTemp4 = (TapREFc4.getValue() != null)? new ReferenceTransaction().getByCode(TapREFc4.getValue()).getIdReference(): 0;

	                   int defTemp1,defTemp2,defTemp3,defTemp4;

	                   defTemp1 = (question.getDDTAP1().getValue() != null)? question.getDDTAP1().getValue(): 0;
	                   defTemp2 = (question.getDDTAP2().getValue() != null)? question.getDDTAP2().getValue(): 0;
	                   defTemp3 = (question.getDDTAP3().getValue() != null)? question.getDDTAP3().getValue(): 0;
	                   defTemp4 = (question.getDDTAP4().getValue() != null)? question.getDDTAP4().getValue(): 0;

	                   int idReponse = new ReponseAProdTableauTransaction().getTapByQ(PlaningController.r, question.getId()).getIdReponse();



	                   reponseAProdTableauTAP = new ReponseAProdTableau(
	                           idReponse,
	                           refTemp1,
	                           refTemp2,
	                           refTemp3,
	                           refTemp4,

	                           defTemp1,
	                           defTemp2,
	                           defTemp3,
	                           defTemp4,

	                           question.getEvaluationTAPAPC1().getValue(),
	                           question.getEvaluationTAPAPC2().getValue(),
	                           question.getEvaluationTAPAPC3().getValue(),
	                           question.getEvaluationTAPAPC4().getValue(),

	                           null,null,null,null,null,null,null,null,

	                           question.getId(),PlaningController.r.getId(),true

	                   );


	                   new ReponseAProdTableauTransaction().update(reponseAProdTableauTAP);

	               }

                   for (Question question : TPP.getItems()){

                       int refTemp1,refTemp2,refTemp3,refTemp4;
                       refTemp1 = (TppREFc1.getValue() != null)? new ReferenceTransaction().getByCode(TppREFc1.getValue()).getIdReference(): 0;
                       refTemp2 = (TppREFc2.getValue() != null)? new ReferenceTransaction().getByCode(TppREFc2.getValue()).getIdReference(): 0;
                       refTemp3 = (TppREFc3.getValue() != null)? new ReferenceTransaction().getByCode(TppREFc3.getValue()).getIdReference(): 0;
                       refTemp4 = (TppREFc4.getValue() != null)? new ReferenceTransaction().getByCode(TppREFc4.getValue()).getIdReference(): 0;

                       int defTemp1,defTemp2,defTemp3,defTemp4;
                       defTemp1 = (question.getDDTPP1().getValue() != null)? question.getDDTPP1().getValue(): 0;
                       defTemp2 = (question.getDDTPP2().getValue() != null)? question.getDDTPP2().getValue(): 0;
                       defTemp3 = (question.getDDTPP3().getValue() != null)? question.getDDTPP3().getValue(): 0;
                       defTemp4 = (question.getDDTPP4().getValue() != null)? question.getDDTPP4().getValue(): 0;

                       String sn1T,sn2T,sn3T,sn4T,nt1T,nt2T,nt3T,nt4T;

                       sn1T = (TPPtfsn1.getText()!=null)? TPPtfsn1.getText() : null;
                       sn2T = (TPPtfsn2.getText()!=null)? TPPtfsn2.getText() : null;
                       sn3T = (TPPtfsn3.getText()!=null)? TPPtfsn3.getText() : null;
                       sn4T = (TPPtfsn4.getText()!=null)? TPPtfsn4.getText() : null;
                       nt1T = (TPPtfnt1.getText()!=null)? TPPtfnt1.getText() : null;
                       nt2T = (TPPtfnt2.getText()!=null)? TPPtfnt2.getText() : null;
                       nt3T = (TPPtfnt3.getText()!=null)? TPPtfnt3.getText() : null;
                       nt4T = (TPPtfnt4.getText()!=null)? TPPtfnt4.getText() : null;

                       int idReponse = new ReponseAProdTableauTransaction().getTppByQ(PlaningController.r, question.getId()).getIdReponse();


                       reponseAProdTableauTPP = new ReponseAProdTableau(
                               idReponse,
                               refTemp1,
                               refTemp2,
                               refTemp3,
                               refTemp4,

                               defTemp1,
                               defTemp2,
                               defTemp3,
                               defTemp4,

                               question.getEvaluationTPPAPC1().getValue(),
                               question.getEvaluationTPPAPC2().getValue(),
                               question.getEvaluationTPPAPC3().getValue(),
                               question.getEvaluationTPPAPC4().getValue(),

                               sn1T,sn2T,sn3T,sn4T,nt1T,nt2T,nt3T,nt4T,

                               question.getId(),PlaningController.r.getId(),false

                       );
                       new ReponseAProdTableauTransaction().update(reponseAProdTableauTPP);
                   }



                   int int1,int2,int3,int4,int5,int6,int7,int8,int9,int10;

                   int1 = (verifiationChampsEntier(cdtfq1.getText()))? Integer.parseInt(cdtfq1.getText()) : 0;
                   int2 = (verifiationChampsEntier(cdtfq2.getText()))? Integer.parseInt(cdtfq2.getText()) : 0;
                   int3 = (verifiationChampsEntier(cdtfq3.getText()))? Integer.parseInt(cdtfq3.getText()) : 0;
                   int4 = (verifiationChampsEntier(cdtfq4.getText()))? Integer.parseInt(cdtfq4.getText()) : 0;
                   int5 = (verifiationChampsEntier(cdtfq5.getText()))? Integer.parseInt(cdtfq5.getText()) : 0;

                   int6 = (verifiationChampsEntier(cvtfq1.getText()))? Integer.parseInt(cvtfq1.getText()) : 0;
                   int7 = (verifiationChampsEntier(cvtfq2.getText()))? Integer.parseInt(cvtfq2.getText()) : 0;
                   int8 = (verifiationChampsEntier(cvtfq3.getText()))? Integer.parseInt(cvtfq3.getText()) : 0;
                   int9 = (verifiationChampsEntier(cvtfq4.getText()))? Integer.parseInt(cvtfq4.getText()) : 0;
                   int10 = (verifiationChampsEntier(cvtfq5.getText()))? Integer.parseInt(cvtfq5.getText()) : 0;

                   int idReponseC = new ReponseControlesTransaction().getByIdRF(PlaningController.r).getId_reponse_formulaire();
                     reponseControles = new ReponseControles(

                    		 PlaningController.r.getId(),idReponseC,
                           int1,int2,int3,int4,int5,int6,int7,int8,int9,int10,
                           mpctfq1.getText(),
                           mpctfq2.getText(),
                           mpctfq3.getText(),
                           mpctfq4.getText(),
                           mpctfq5.getText(),
                           mpctfq6.getText(),

                           statutcb1.getValue(),
                           statutcb2.getValue(),
                           statutcb3.getValue(),

                           serialnumbersstring

                   );
                 
                    new ReponseControlesTransaction().update(reponseControles);

               }
        }

	           


}
