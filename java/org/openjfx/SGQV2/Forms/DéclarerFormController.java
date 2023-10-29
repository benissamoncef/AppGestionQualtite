package org.openjfx.SGQV2.Forms;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.App;
import org.openjfx.SGQV2.Administration.LoginController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Models.Client;
import Models.Famille;
import Models.Ligne;
import Models.Projet;
import Models.ReponseFormulaire;
import Models.Shift;
import Models.Zone;
import Transactions.BaseTransaction;
import Transactions.ClientTransaction;
import Transactions.FamilleTransaction;
import Transactions.LigneTransaction;
import Transactions.ProjetTransaction;
import Transactions.ReponseFormulaireTransaction;
import Transactions.ShiftTransaction;
import Transactions.ZoneTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DéclarerFormController extends BaseTransaction implements Initializable{

	public DéclarerFormController() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public static Boolean isFormAP = false; 
	
	public static Boolean isFormAproduit = false;
	
	public static Boolean isFormPriseDonnées = false;
	
	public static ReponseFormulaire reponseFormAP = null;
	
	public static ReponseFormulaire reponseFormAProduit = null;
	
	public static ReponseFormulaire reponseFormPriseDonnées = null;
	
	public static int actionToPerform;
	
	
	ObservableList<Client> clientsList = FXCollections.observableArrayList();
	ObservableList<Projet> projetsList = FXCollections.observableArrayList();
	ObservableList<Famille> famillesList = FXCollections.observableArrayList();
	ObservableList<Ligne> lignesList = FXCollections.observableArrayList();
	ObservableList<Zone> zonesList = FXCollections.observableArrayList();
	ObservableList<Shift> shiftsList = FXCollections.observableArrayList();
	
	
    @FXML
    private Label AlertDateVide;
    
    @FXML
    private Label AlertClientVide;

    @FXML
    private Label AlertFamilleVide;

    @FXML
    private Label AlertLigneVide;

    @FXML
    private Label AlertProjetVide;

    @FXML
    private Label AlertShiftVide;

    @FXML
    private Label AlertZoneVide;

    @FXML
    private JFXButton BtnActions;

    @FXML
    private JFXButton BtnChercher;

    @FXML
    private JFXComboBox<String> ClientComboBox;

    @FXML
    private DatePicker DateField;

    @FXML
    private JFXTextField EquipeTextField;

    @FXML
    private JFXComboBox<String> FamilleComboBox;

    @FXML
    private JFXComboBox<String> LigneComboBox;

    @FXML
    private JFXTextField MatOpTextField;

    @FXML
    private JFXComboBox<String> ProjetComboBox;

    @FXML
    private JFXComboBox<String> ShiftComboBox;

    @FXML
    private JFXTextField TeamLeaderTextField;

    @FXML
    private Label TechnicienValue;

    @FXML
    private Label TitreFormulaire;

    @FXML
    private JFXComboBox<String> ZoneComboBox;
    
    
    
    @FXML
    void BtnActionsClicked(ActionEvent event) throws IOException, SQLException {
    	
	if(isFormAP) {
    	switch(actionToPerform) {
    	case 0 : openStageFormAP();break;
    	case 1 : {

    				int id_famille = famillesList.get(FamilleComboBox.getSelectionModel().getSelectedIndex()).getIdFamille();
    				int id_ligne = lignesList.get(LigneComboBox.getSelectionModel().getSelectedIndex()).getIdLigne();	
		
    				Date date = Date.valueOf(DateField.getValue());

    				new ReponseFormulaireTransaction().save(new ReponseFormulaire(false, 1, LoginController.LoggerCompte.getId(), id_famille, id_ligne, 0, null, null, null, date));		
    				reponseFormAP = new ReponseFormulaireTransaction().getAuditProcess(new ReponseFormulaire(1, LoginController.LoggerCompte.getId(), id_famille, id_ligne, date));

    				openStageFormAP();
		
    				}break;
    		
    	}
	} else if(isFormAproduit) {
		
	
	  	switch(actionToPerform) {
    	case 0 : openStageFormAProduit();break;
    	case 1 : {
    		
    				int id_famille = famillesList.get(FamilleComboBox.getSelectionModel().getSelectedIndex()).getIdFamille();

    				int id_ligne = lignesList.get(LigneComboBox.getSelectionModel().getSelectedIndex()).getIdLigne();	
    				
    				int id_Shift = shiftsList.get(ShiftComboBox.getSelectionModel().getSelectedIndex()).getId();
    				
    				String equipe = EquipeTextField.getText();
    				
    				Date date = Date.valueOf(DateField.getValue());

    				new ReponseFormulaireTransaction().save(new ReponseFormulaire(false, 2, LoginController.LoggerCompte.getId(), id_famille, id_ligne, id_Shift, equipe, null, null, date));
    				reponseFormAProduit = new ReponseFormulaireTransaction().getAuditProduit(new ReponseFormulaire(2, LoginController.LoggerCompte.getId(), id_famille, id_ligne, date));

    					openStageFormAProduit();
		
    				}break;
    				
	  	}
	} else if(isFormPriseDonnées) {
		
	
	  	switch(actionToPerform) {
    	case 0 : openStageFormPriseDonnnées();break;
    	case 1 : {
    		
    				int id_famille = famillesList.get(FamilleComboBox.getSelectionModel().getSelectedIndex()).getIdFamille();

    				int id_ligne = lignesList.get(LigneComboBox.getSelectionModel().getSelectedIndex()).getIdLigne();	
    				
    				int id_Shift = shiftsList.get(ShiftComboBox.getSelectionModel().getSelectedIndex()).getId();
    				
    				String equipe = EquipeTextField.getText();
    				
    				String teamLeader = TeamLeaderTextField.getText();
    				String opt = MatOpTextField.getText();
    				
    				Date date = Date.valueOf(DateField.getValue());

    				new ReponseFormulaireTransaction().save(new ReponseFormulaire(false, 3, LoginController.LoggerCompte.getId(), id_famille, id_ligne, id_Shift, equipe, teamLeader, opt, date));
    				reponseFormPriseDonnées = new ReponseFormulaireTransaction().getFormulairePriseDonnées(new ReponseFormulaire(3, LoginController.LoggerCompte.getId(), id_famille, id_ligne, id_Shift, date));

    					openStageFormPriseDonnnées();
		
    				}break;
    				
	  	} 
	}

    }
    
    public void openStageFormAP() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Forms//RépondreFAP.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage thisStage = (Stage) BtnActions.getScene().getWindow();
        thisStage.setScene(scene);
    }
    
    
    public void openStageFormAProduit() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Forms//FormulaireProduit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage thisStage = (Stage) BtnActions.getScene().getWindow();
        thisStage.setScene(scene);
    }
    
    public void openStageFormPriseDonnnées() throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Forms//Prise de données en montage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage thisStage = (Stage) BtnActions.getScene().getWindow();
        thisStage.setScene(scene);
    }

  
    @FXML
    void BtnChercherClicked(ActionEvent event) throws SQLException {

    	if(VérifierChamps()) {
    					
			int id_famille = famillesList.get(FamilleComboBox.getSelectionModel().getSelectedIndex()).getIdFamille();
			
			int id_ligne = lignesList.get(LigneComboBox.getSelectionModel().getSelectedIndex()).getIdLigne();	
			
			

			Date date = Date.valueOf(DateField.getValue());
    		
    		if(isFormAP) {			   			
    			Boolean r = new ReponseFormulaireTransaction().checkAuditProcess(new ReponseFormulaire(1, LoginController.LoggerCompte.getId(), id_famille, id_ligne, date));
    			if(r) {
    							
					reponseFormAP = new ReponseFormulaireTransaction().getAuditProduit(
							new ReponseFormulaire(1, LoginController.LoggerCompte.getId(), id_famille, id_ligne, date)
					);

    				actionToPerform = 0;
    				BtnActions.setText("Continuer la déclaration");	
    				BtnActions.setVisible(true);
    				
    			}
    			else {
    				actionToPerform = 1;
    				BtnActions.setText("Déclarer");
    				BtnActions.setVisible(true);
    			}
    						
    						
    		}
    		else  if(isFormAproduit){
    			
    			int id_shift = shiftsList.get(ProjetComboBox.getSelectionModel().getSelectedIndex()).getId();
    			
    			Boolean r = new ReponseFormulaireTransaction().checkAuditProduit(new ReponseFormulaire(2, LoginController.LoggerCompte.getId(), id_famille, id_ligne, id_shift, date));
    			if(r) {
    				
    					reponseFormAProduit = new ReponseFormulaireTransaction().getAuditProduit(
    							new ReponseFormulaire(2, LoginController.LoggerCompte.getId(), id_famille, id_ligne, id_shift,  date)
    					);

    					actionToPerform = 0;
    					BtnActions.setText("Continuer la déclaration");	
    					BtnActions.setVisible(true);
    				
    			}
    			else{
    				actionToPerform = 1;
    				BtnActions.setText("Déclarer");
    				BtnActions.setVisible(true);
    			}
    			
    			
    		}
    		
    		else  if(isFormPriseDonnées){
    			
    			int id_shift = shiftsList.get(ProjetComboBox.getSelectionModel().getSelectedIndex()).getId();

    			
    			Boolean r = new ReponseFormulaireTransaction().checkPriseDonnées(new ReponseFormulaire(3, LoginController.LoggerCompte.getId(), id_famille, id_ligne, id_shift , date));
    			if(r) {
    				
    				reponseFormPriseDonnées = new ReponseFormulaireTransaction().getAuditProduit(
    							new ReponseFormulaire(3, LoginController.LoggerCompte.getId(), id_famille, id_ligne, id_shift, date)
    					);

    					actionToPerform = 0;
    					BtnActions.setText("Continuer la déclaration");	
    					BtnActions.setVisible(true);
    				
    			}
    			else{
    				actionToPerform = 1;
    				BtnActions.setText("Déclarer");
    				BtnActions.setVisible(true);
    			}
    			
    			
    		}
    		
    	}
    	
    }


    @FXML
    void updateProjets(MouseEvent event) throws SQLException {  
    	
    	if(!ClientComboBox.getValue().isEmpty()) {
    		    		
    		int id_client = clientsList.get(ClientComboBox.getSelectionModel().getSelectedIndex()).getId();
    		
    		ProjetComboBox.getItems().clear();
    		FamilleComboBox.getItems().clear();
   		
    		try {
    			projetsList = new ProjetTransaction().getAllByClient(id_client);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		if(projetsList != null) {
    			for(int i = 0; i < projetsList.size(); i++) {		
    				ProjetComboBox.getItems().add(projetsList.get(i).getLabelProjet());
    			}
    		}
    	}
    	
    }
    
    
    @FXML
    void updateFamilles(MouseEvent event) throws SQLException {
    	
    	if(!ClientComboBox.getValue().isEmpty() && !ProjetComboBox.getValue().isEmpty()) {
    		
    		FamilleComboBox.getItems().clear();
    		
    		int id_projet = projetsList.get(ProjetComboBox.getSelectionModel().getSelectedIndex()).getId();

    		
    		try {
    			famillesList = new FamilleTransaction().getAll(id_projet);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		if(famillesList != null) {
    			for(int i = 0; i < famillesList.size(); i++) {		
    				FamilleComboBox.getItems().add(famillesList.get(i).getCodeFamilleInterne());
    			}
    		}
    	}   	   	
    }
    
    
    @FXML
    void updateLignes(MouseEvent event) throws SQLException {
    	
    	if(!ZoneComboBox.getValue().isEmpty()) {
    		
    		LigneComboBox.getItems().clear();

    		int id_zone = zonesList.get(ZoneComboBox.getSelectionModel().getSelectedIndex()).getIdZone();

    		try {
    			lignesList = new LigneTransaction().getAllByZone(id_zone);
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		
    		if(lignesList != null) {
    			for(int i = 0; i < lignesList.size(); i++) {		
    				LigneComboBox.getItems().add(lignesList.get(i).getLabelLigne());
    			}
    		}
    	}  
    	
    	
    }
    
    
    @FXML
    void viderClientFields(MouseEvent event) {
    	ProjetComboBox.getItems().clear();
    	FamilleComboBox.getItems().clear();

    }
    
    
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		AlertClientVide.setVisible(false);
		AlertFamilleVide.setVisible(false);
		AlertLigneVide.setVisible(false);
		AlertShiftVide.setVisible(false);
		AlertProjetVide.setVisible(false);
		AlertDateVide.setVisible(false);
		AlertZoneVide.setVisible(false);
		BtnActions.setVisible(false);
		
		BtnActions.setText("Déclarer");
		
		
		TechnicienValue.setText(LoginController.LoggerCompte.getUsername());
		
		if(isFormAP) {
			TitreFormulaire.setText("Déclaration Formulaire d'Audit Process"); 
			ShiftComboBox.setVisible(false);
			EquipeTextField.setVisible(false);
			
		}
		else if(isFormAproduit){
			TitreFormulaire.setText("Déclaration Formulaire d'Audit Produit"); 
			ShiftComboBox.setVisible(true);
			EquipeTextField.setVisible(true);
		}
		else {
			TitreFormulaire.setText("Déclaration Formulaire de prise de données en montage"); 
			ShiftComboBox.setVisible(true);
			EquipeTextField.setVisible(true);
			TeamLeaderTextField.setVisible(true);
			MatOpTextField.setVisible(true);
		}
		
		
		
		
		
		try {
			clientsList = new ClientTransaction().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(clientsList != null) {
			for(int i = 0; i < clientsList.size(); i++) {		
				ClientComboBox.getItems().add(clientsList.get(i).getLabelClient());
			}
		}
		
		
		
		try {
			zonesList = new ZoneTransaction().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(zonesList != null) {
			for(int i = 0; i < zonesList.size(); i++) {		
				ZoneComboBox.getItems().add(zonesList.get(i).getLabelZone());
			}
		}
		
		if(!isFormAP) {
			
			
			
			try {
				shiftsList = new ShiftTransaction().getAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			if(shiftsList != null) {
				for(int i = 0; i < shiftsList.size(); i++) {		
					ShiftComboBox.getItems().add(shiftsList.get(i).getLabelShift());
				}
			}
		}
		
		

		
	}
	

	
	private Boolean VérifierChamps() {		
		if(!ClientComboBox.getValue().isEmpty()) {
			if(!ProjetComboBox.getValue().isEmpty()) {
				if(!FamilleComboBox.getValue().isEmpty()) {
					if(!LigneComboBox.getValue().isEmpty()) {
							if(DateField.getValue() != null) {								
								return true;
							}
							else {
								AlertDateVide.setVisible(true);
								return false;
							}
							
					
			
					}
					else {
							AlertLigneVide.setVisible(true);
							return false;
					}					
				}
				else {
					AlertFamilleVide.setVisible(true);
					return false;
				}
			}
			else {
				AlertProjetVide.setVisible(true);
				return false;
			}
		}
		else {
			AlertClientVide.setVisible(true);
			return false;
		}
		
	}
	
	
	
	

}
