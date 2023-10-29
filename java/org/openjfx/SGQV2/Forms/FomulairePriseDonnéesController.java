package org.openjfx.SGQV2.Forms;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.App;
import org.openjfx.SGQV2.Administration.HomeController;
import org.openjfx.SGQV2.Planings.PlaningController;

import com.dlsc.gemsfx.TimePicker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Models.Défaut;
import Models.Reference;
import Models.ReponseFormulaire;
import Models.RéponseRapportArretsTableau;
import Models.RéponseRapportDéfautsTableau;
import Models.RéponseRapportProductionTableau;
import Transactions.ClientTransaction;
import Transactions.CompteTransaction;
import Transactions.DéfautTransaction;
import Transactions.FamilleTransaction;
import Transactions.LigneTransaction;
import Transactions.ProjetTransaction;
import Transactions.ReferenceTransaction;
import Transactions.RéponsesPriseDonnéesTransaction;
import Transactions.ShiftTransaction;
import impl.com.calendarfx.view.NumericTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class FomulairePriseDonnéesController  implements Initializable {
	
	public static Boolean isAdmin2 = false;
	public static Boolean isModAdmin2 = false;
	
	public ReponseFormulaire réponseFormulaire;
	
	
	private ObservableList<RéponseRapportDéfautsTableau> ListRéponseRapportDéfauts = FXCollections.observableArrayList(); 
	private ObservableList<RéponseRapportProductionTableau> ListRéponseRapportProduction = FXCollections.observableArrayList(); 
	private ObservableList<RéponseRapportArretsTableau> ListRéponseRapportArrets= FXCollections.observableArrayList(); 

	
	
	
	private ObservableList<Reference> ListReferences = FXCollections.observableArrayList(); 
	private ObservableList<Défaut> ListDéfauts = FXCollections.observableArrayList(); 
	
	public static ObservableList<String> References = FXCollections.observableArrayList(); 
	public static ObservableList<Integer> Défauts = FXCollections.observableArrayList(); 
	

	
    @FXML
    private JFXButton AjouterRA;

    @FXML
    private JFXButton AjouterRDQ;

    @FXML
    private JFXButton AjouterRP;

    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, JFXTextField> CircuitColumn;

    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, JFXTextField> ComposantColumn;

    @FXML
    private TableColumn<RéponseRapportArretsTableau, JFXTextField> DescriptionRAColumn;

    @FXML
    private TableColumn<RéponseRapportArretsTableau, TimePicker> HeurArretRAColumn;

    @FXML
    private TableColumn<RéponseRapportArretsTableau, TimePicker> HeurRepriseRAColumn;

   
    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, NumericTextField> MatriculeColumn;

    @FXML
    private TableColumn<RéponseRapportArretsTableau, NumericTextField> MatriculeRAColumn;

    @FXML
    private TableColumn<RéponseRapportArretsTableau, NumericTextField> NombreOpRAColumn;

    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, Integer> NumColumn;   

    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, JFXComboBox<String>> codeDéfautColumn;

    @FXML
    private TableColumn<RéponseRapportProductionTableau, Integer> NumProductionColumn;

    @FXML
    private TableColumn<RéponseRapportArretsTableau, Integer> NumRAColumn;

    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, NumericTextField> NumSérieColumn;

    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, JFXTextField> ObsColumn;

    @FXML
    private TableColumn<RéponseRapportProductionTableau, JFXTextField> ObservationProductionColumn;

    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, NumericTextField> PosteColumn;

    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, NumericTextField> QteColumn;

    @FXML
    private TableColumn<RéponseRapportProductionTableau, NumericTextField> QteProductionColumn;

    @FXML
    private TableView<RéponseRapportProductionTableau> RapportDeProduction;

    @FXML
    private TableView<RéponseRapportArretsTableau> RapportDesArrêts;

    @FXML
    private TableView<RéponseRapportDéfautsTableau> RapportDesDéfautsQualité;

    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, JFXComboBox<String>> RéfColumn;

    @FXML
    private TableColumn<RéponseRapportProductionTableau, JFXComboBox<String>> RéfProductionColumn;

    @FXML
    private TableColumn<RéponseRapportArretsTableau, JFXComboBox<String>> RéfRAColumn;

    @FXML
    private JFXButton SupprimerRA;

    @FXML
    private JFXButton SupprimerRDQ;

    @FXML
    private JFXButton SupprimerRP;
    
    @FXML
    private JFXButton Enregistrer;

    @FXML
    private TableColumn<RéponseRapportArretsTableau, JFXTextField> TypeRAColumn;

    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, NumericTextField> Voie1Column;

    @FXML
    private TableColumn<RéponseRapportDéfautsTableau, NumericTextField> Voie2Column;

    @FXML
    private TableColumn<RéponseRapportArretsTableau, JFXTextField> intervenantRAColumn;

    @FXML
    private TableColumn<RéponseRapportArretsTableau, NumericTextField> intervensionRAColumn;

    
    
    @FXML
    private Label OptLabel;
    
    @FXML
    private Label clientLabel; 

    @FXML
    private Label dateLabel;
    
    @FXML
    private Label equipeLabel;
    
    @FXML
    private Label ligneLabel;

    @FXML
    private Label shiftLabel;

    @FXML
    private Label teamLeaderLabel;

    @FXML
    private Label techQualitéLabel;


    
    private void Retour() throws IOException {
    	
    	HomeController.StartPane = "Forms//DéclarerFormulaire";	    	
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home//Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage thisStage = (Stage) AjouterRA.getScene().getWindow();
        thisStage.setScene(scene);
    	
    	
    }
    
    
    
    
    @FXML
    void AjouterRAclicked(ActionEvent event) throws NumberFormatException, SQLException {
    	
    	int size = RapportDesArrêts.getItems().size(); 
    	RéponseRapportArretsTableau réponse = new RéponseRapportArretsTableau();
    	réponse.setNumero(size+1);
    	réponse.setId_réponseFormulaire(réponseFormulaire.getId()); 	
    	
    	new RéponsesPriseDonnéesTransaction().saveRéponseRapportArretsTableau(réponse);

    	SaveRA();
    	updateRA();
    	
    	
    }
    
    private void updateRA() throws SQLException {   
    	
    	RapportDesArrêts.getItems().clear();
    	
		ListRéponseRapportArrets = new RéponsesPriseDonnéesTransaction().getAllRéponsesArrets(réponseFormulaire.getId());
		
		int cpt = 1;
		for(RéponseRapportArretsTableau r : ListRéponseRapportArrets) {
			r.setNumero(cpt);
			r.setQnumeroRA(r.getNumero());
			r.getQTypeArret().setText(r.getType());
			r.getQNumeroIntervension().setText(r.getIntervension().toString());
			r.getQIntervenant().setText(r.getIntervenant());
			r.getQDescriptionRA().setText(r.getDescription());
			r.getQMatriculeRA().setText(r.getDescription());
			r.getQheurArret().setTime(r.getHeurArret());
			r.getQheurReprise().setTime(r.getHeurReprise());
			r.getQNbrOp().setText(r.getNbrOp().toString());
			r.setQRéférenceRA(new JFXComboBox<String>(References));
			r.getQRéférenceRA().setValue(r.getRéférence());
			RapportDesArrêts.getItems().add(r);
			cpt++;
		}
 	
    }
    
    

    @FXML
    void AjouterRDQclicked(ActionEvent event) throws NumberFormatException, SQLException {
    	
    	int size = RapportDesDéfautsQualité.getItems().size(); 
    	RéponseRapportDéfautsTableau réponse = new RéponseRapportDéfautsTableau();
    	réponse.setNumero(size + 1);
    	réponse.setId_réponseFormulaire(réponseFormulaire.getId()); 
    	
    	new RéponsesPriseDonnéesTransaction().saveRéponseRapportDéfautsTableau(réponse);

    	SaveRDQ();
    	updateRDQ();
    	
    }
    
    private void updateRDQ() throws SQLException {
	
    	RapportDesDéfautsQualité.getItems().clear();
    	
		ListRéponseRapportDéfauts = new RéponsesPriseDonnéesTransaction().getAllRéponsesDéfauts(réponseFormulaire.getId());
		
		int cpt = 1;
		for(RéponseRapportDéfautsTableau r : ListRéponseRapportDéfauts) {
			r.setNumero(cpt);
			r.setQnumeroRDQ(r.getNumero());
			r.getQNumSérieRDQ().setText(r.getNumeroSérie().toString());
			r.getQpostRDQ().setText(r.getPoste().toString());
			r.getQMatricule().setText(r.getMatricule().toString());
			r.getQComposantRDQ().setText(r.getComposant());
			r.getQCircuitRDQ().setText(r.getCircuit());
			r.getQvoie1().setText(r.getVoie1().toString());
			r.getQvoie2().setText(r.getVoie2().toString());
			r.getQqteRDQ().setText(r.getQte().toString());
			r.getQObservationRDQ().setText(r.getObs());
			
			r.setQDéfautsRDQ(new JFXComboBox<Integer>(Défauts));
			
			r.getQDéfautsRDQ().setValue(r.getCodeDéfaut());
			r.setQRéférenceRDQ(new JFXComboBox<String>(References));
			r.getQRéférenceRDQ().setValue(r.getRéférence());
			RapportDesDéfautsQualité.getItems().add(r);
			cpt++;
		}
    	
    }

    @FXML
    void AjouterRPclicked(ActionEvent event) throws SQLException {
    	
    	int size = RapportDeProduction.getItems().size(); 
    	
    	RéponseRapportProductionTableau réponse = new RéponseRapportProductionTableau();
    	
    	réponse.setNumero(size + 1);
    	
    	réponse.setId_réponseFormulaire(réponseFormulaire.getId()); 
    	
    	new RéponsesPriseDonnéesTransaction().saveRéponseRapportProductionTableau(réponse);
    	
    	SaveRP();   	
    	updateRP();

    }
    
    private void updateRP() throws SQLException {
    	
    	RapportDeProduction.getItems().clear();
		ListRéponseRapportProduction = new RéponsesPriseDonnéesTransaction().getAllRéponsesProduction(réponseFormulaire.getId());
			
		int cpt = 1;
		for(RéponseRapportProductionTableau r : ListRéponseRapportProduction) {
			r.setNumero(cpt);
			r.setQnumeroRP(r.getNumero());
			r.getQqteRP().setText(r.getQte().toString());
			r.getQObservationRP().setText(r.getObs());			
			r.setQRéférenceRP(new JFXComboBox<String>(References));
			r.getQRéférenceRP().setValue(r.getRéférence());
			RapportDeProduction.getItems().add(r);
			cpt++;
		}
    	
    }
    
    
    
    
    
    
    

    @FXML
    void SupprimerRAclicked(ActionEvent event) throws SQLException {
    	
    	if(!RapportDesArrêts.getSelectionModel().isEmpty()) {
    		
    		int id = RapportDesArrêts.getSelectionModel().getSelectedItem().getId();
    		
    		new RéponsesPriseDonnéesTransaction().deleteRéponseRapportArrets(id);	

    		RapportDesArrêts.getItems().remove(RapportDesArrêts.getSelectionModel().getSelectedIndex());
    		
    		SaveRA();
    		
    		updateRA();
    		
    	}
    }

    @FXML
    void SupprimerRDQclicked(ActionEvent event) throws SQLException {
    	
    	if(!RapportDesDéfautsQualité.getSelectionModel().isEmpty()) {
    		int id = RapportDesDéfautsQualité.getSelectionModel().getSelectedItem().getId(); 
    		
    		new RéponsesPriseDonnéesTransaction().deleteRéponseRapportDéfauts(id);	   
    		
    		RapportDesDéfautsQualité.getItems().remove(RapportDesDéfautsQualité.getSelectionModel().getSelectedIndex());
    		
    		SaveRDQ();
    		
    		updateRDQ();
    	}

    }

    @FXML
    void SupprimerRPclicked(ActionEvent event) throws SQLException {
    	
    	if(!RapportDeProduction.getSelectionModel().isEmpty()) {
    		int id = RapportDeProduction.getSelectionModel().getSelectedItem().getId();
    		new RéponsesPriseDonnéesTransaction().deleteRéponseRapportProduction(id);	
    		RapportDeProduction.getItems().remove(RapportDeProduction.getSelectionModel().getSelectedIndex());
    			
    		SaveRP();
    		
    		updateRP();
    	}

    }
    
    
    @FXML
    void EnregistrerClicked(ActionEvent event) throws SQLException, IOException {

    	
    	SaveRDQ();
    	SaveRP();
    	SaveRA();
    	
    	Retour();

    }
    
    private void SaveRDQ() throws SQLException {
    	
    	RéponsesPriseDonnéesTransaction Tr = new RéponsesPriseDonnéesTransaction();


        // saving responses:

    	for(RéponseRapportDéfautsTableau r : RapportDesDéfautsQualité.getItems()) {
    		
    		Tr.updateRéponseRapportDéfautsTableau(new RéponseRapportDéfautsTableau(  				
    					r.getId(),
    					r.getNumero(),
    					Integer.parseInt(r.getQNumSérieRDQ().getText()),
    					r.getQRéférenceRDQ().getValue(),
    					Integer.parseInt(r.getQpostRDQ().getText()),
    					Integer.parseInt(r.getQMatricule().getText()),
    					Integer.parseInt(r.getQDéfautsRDQ().getValue().toString()),
    					r.getQComposantRDQ().getText(),
    					r.getQCircuitRDQ().getText(),
    					Integer.parseInt(r.getQvoie1().getText()),
    					Integer.parseInt(r.getQvoie2().getText()),
    					Integer.parseInt(r.getQqteRDQ().getText()),
    					r.getQObservationRDQ().getText(),
    					réponseFormulaire.getId()
   				
    				));
    		 		
    	}
    	
    	
    	

    	
    }
    
    private void SaveRP() throws NumberFormatException, SQLException {
    	
    	RéponsesPriseDonnéesTransaction Tr = new RéponsesPriseDonnéesTransaction();

        // saving responses:

    	for(RéponseRapportProductionTableau r : RapportDeProduction.getItems()) {
    		
    		Tr.updateRéponseRapportProductionTableau(new RéponseRapportProductionTableau(  				
    					r.getId(),
    					r.getNumero(),
    					r.getQRéférenceRP().getValue(),
    					Integer.parseInt(r.getQqteRP().getText()),
    					r.getQObservationRP().getText(),
    					réponseFormulaire.getId()
   				
    				));
    		 		
    	}
    	
    	
    }
	
    private void SaveRA() throws SQLException {
	
    	RéponsesPriseDonnéesTransaction Tr = new RéponsesPriseDonnéesTransaction();

        // saving responses:

    	for(RéponseRapportArretsTableau r : RapportDesArrêts.getItems()) {
    		
    		Tr.updateRéponseRapportArretsTableau(new RéponseRapportArretsTableau(  				
    					r.getId(),
    					r.getNumero(),
    					r.getQTypeArret().getText(),
    					Integer.parseInt(r.getQNumeroIntervension().getText()),
    					r.getQIntervenant().getText(),
    					r.getQRéférenceRA().getValue(),
    					r.getQDescriptionRA().getText(),
    					r.getQMatriculeRA().getText(),
    					r.getQheurArret().getTime(),
    					r.getQheurReprise().getTime(),
    					Integer.parseInt(r.getQNbrOp().getText()),
    					réponseFormulaire.getId()
   				
    				));
    		 		
    	}
    	
    	
  	
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		// Table Rapport des défauts qualité :
		
		NumColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, Integer>("QnumeroRDQ"));
		NumSérieColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, NumericTextField>("QNumSérieRDQ"));
		RéfColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, JFXComboBox<String>>("QRéférenceRDQ"));
		PosteColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, NumericTextField >("QpostRDQ"));
		MatriculeColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, NumericTextField>("QMatricule"));
		codeDéfautColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, JFXComboBox<String>>("QDéfautsRDQ"));
		ComposantColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, JFXTextField>("QComposantRDQ"));
		CircuitColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, JFXTextField>("QCircuitRDQ"));
		Voie1Column.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, NumericTextField >("Qvoie1"));
		Voie2Column.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, NumericTextField>("Qvoie2"));
		QteColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, NumericTextField>("QqteRDQ"));
		ObsColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportDéfautsTableau, JFXTextField>("QObservationRDQ"));

		
		
		// Rapport de production :
		
		NumProductionColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportProductionTableau, Integer>("QnumeroRP"));
		RéfProductionColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportProductionTableau, JFXComboBox<String>>("QRéférenceRP"));
		QteProductionColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportProductionTableau, NumericTextField>("QqteRP"));
		ObservationProductionColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportProductionTableau, JFXTextField >("QObservationRP"));
		
		
		
		// Rapport des arrets:
		
		
		

		NumRAColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportArretsTableau, Integer>("QnumeroRA"));
		TypeRAColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportArretsTableau, JFXTextField>("QTypeArret"));
		intervensionRAColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportArretsTableau, NumericTextField>("QNumeroIntervension"));
		intervenantRAColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportArretsTableau, JFXTextField >("QIntervenant"));
		RéfRAColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportArretsTableau, JFXComboBox<String>>("QRéférenceRA"));
		DescriptionRAColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportArretsTableau, JFXTextField>("QDescriptionRA"));
		MatriculeRAColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportArretsTableau, NumericTextField>("QMatriculeRA"));
		HeurArretRAColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportArretsTableau, TimePicker>("QheurArret"));
		HeurRepriseRAColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportArretsTableau, TimePicker >("QheurReprise"));
		NombreOpRAColumn.setCellValueFactory(new PropertyValueFactory<RéponseRapportArretsTableau, NumericTextField>("QNbrOp"));
		


		
		
		
		
		// loading responses :
		
		
		
		if(!isAdmin2)
			réponseFormulaire = DéclarerFormController.reponseFormPriseDonnées;
	
		else
			réponseFormulaire = PlaningController.r;
		
		
		
		

		dateLabel.setText(réponseFormulaire.getCreatedAt().toString());	

		String client = null;
		try {
			client = new ClientTransaction().getById(new ProjetTransaction().getById(new FamilleTransaction().getById(réponseFormulaire.getId_famille()).getIdProjet()).getId_client()).getLabelClient();
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		
		clientLabel.setText(client);
		
		equipeLabel.setText(réponseFormulaire.getEquipe());
		
		String ligne = null;
		try {
			ligne = new LigneTransaction().getById(réponseFormulaire.getId_ligne()).getLabelLigne();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		
		ligneLabel.setText(ligne);

		String shift = null;
		try {
			shift = new ShiftTransaction().getById(réponseFormulaire.getId_shift()).getLabelShift();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		shiftLabel.setText(shift);
		

		try {
			techQualitéLabel.setText(new CompteTransaction().getById(réponseFormulaire.getId_compte_technicien()).getUsername());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		OptLabel.setText(réponseFormulaire.getOpérateur());
		teamLeaderLabel.setText(réponseFormulaire.getTeamLeader());
		
		
		
		int id_projet = 0;
		try {
			id_projet = new ProjetTransaction().getById(new FamilleTransaction().getById(réponseFormulaire.getId_famille()).getIdProjet()).getId();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			ListReferences = new ReferenceTransaction().getAll(id_projet, réponseFormulaire.getId_famille());
		} catch (SQLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}	
		
		

		
		try {
			ListDéfauts = new DéfautTransaction().getAll();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		
	for(Reference r : ListReferences) {
		References.add(r.getCodeReferenceInterne());
	}
		
	for(Défaut d : ListDéfauts) {
		Défauts.add(d.getCode());
	}
		
		
		
		
			
		
		try {
			updateRDQ();
			
			updateRP();
			
			updateRA();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(isAdmin2) {
			
			AjouterRA.setVisible(false);
			AjouterRDQ.setVisible(false);
			AjouterRP.setVisible(false);
			SupprimerRA.setVisible(false);
			SupprimerRDQ.setVisible(false);
			SupprimerRP.setVisible(false);
			Enregistrer.setVisible(false);
			RapportDesDéfautsQualité.setDisable(true);	
			RapportDeProduction.setDisable(true);		
			RapportDesArrêts.setDisable(true);	
			
		}
	
		
	
		
	}
}

	
	


