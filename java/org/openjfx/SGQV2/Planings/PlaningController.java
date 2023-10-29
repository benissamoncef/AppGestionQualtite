package org.openjfx.SGQV2.Planings;



import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Administration.LoginController;

import com.jfoenix.controls.JFXListView;
import Models.Compte;
import Models.Employee;
import Models.Ligne;
import Models.PlaningTable;
import Models.ReponseFormulaire;
import Models.Zone;
import Transactions.CompteTransaction;
import Transactions.EmployeeTransaction;
import Transactions.LigneTransaction;
import Transactions.ReponseFormulaireTransaction;
import Transactions.ZoneTransaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import tornadofx.control.DateTimePicker;

public class PlaningController implements Initializable{
	
	
	ObservableList<Compte> TechsOfThisAdminList = FXCollections.observableArrayList();
	ObservableList<Zone> zonesList = FXCollections.observableArrayList();
	ObservableList<Ligne> lignesList = FXCollections.observableArrayList();

	public static ReponseFormulaire r = null;
	
	@FXML
    private TableColumn<PlaningTable, HBox> ActionsColumn;

    @FXML
    private TableColumn<PlaningTable, String> ClientColumn;

    @FXML
    private TableColumn<PlaningTable, Date> DateColumn;

    @FXML
    private TableColumn<PlaningTable, String> FamilleColumn;

    @FXML
    private JFXListView<String> LinesList;

    @FXML
    private TableColumn<PlaningTable, String> ProjetColumn;

    @FXML
    private TableView<PlaningTable> RapportTable;

    @FXML
    private JFXListView<String> TechsList;

    @FXML
    private DateTimePicker TimePeacker;

    @FXML
    private JFXListView<String> TypesPlanningList;

    @FXML
    private JFXListView<String> ZonesList;
       
    @FXML
    private Label WarningLigne;

    @FXML
    private Label WarningTech;

    @FXML
    private Label WarningTime;
    
    @FXML
    private Label WarningZone;
    
    @FXML
    private Label WarningTypePlaning;
    
    @FXML
    private FontAwesomeIconView SearchBtn;


    @FXML
    void LigneClicked(MouseEvent event) {
    	WarningLigne.setVisible(false);
    }

    @FXML
    void TechClicked(MouseEvent event) {
    	WarningTech.setVisible(false);
    }

    @FXML
    void TimeClicked(ActionEvent event) {
    	WarningTime.setVisible(false);
    }

    @FXML
    void TypePlaningClicked(MouseEvent event) {
    	WarningTypePlaning.setVisible(false);
    }
    

    private void ZoneClicked() {
    	WarningZone.setVisible(false);
    }
    
    
    @FXML
    void SearchBtnClicked(MouseEvent event) throws SQLException {
    	if(checkFields()) { 
    		
    		RapportTable.getItems().clear();
    		
    		int id_formulaire = 1;
    		if(TypesPlanningList.getSelectionModel().getSelectedItem().toString().equals("Formulaire Audit Produit")) id_formulaire = 2;
    		else if(TypesPlanningList.getSelectionModel().getSelectedItem().toString().equals("Formulaire Prise de données")) id_formulaire = 3; 
    		
    		int id_technicien = TechsOfThisAdminList.get(TechsList.getSelectionModel().getSelectedIndex()).getId();
    		int id_Ligne = lignesList.get(LinesList.getSelectionModel().getSelectedIndex()).getIdLigne();
    		
    		LocalDate date = TimePeacker.getValue();
    		
    		if(id_formulaire == 1)
    			RapportTable.setItems(new ReponseFormulaireTransaction().getAllAP(id_formulaire, id_technicien, id_Ligne, date.get(ChronoField.ALIGNED_WEEK_OF_YEAR)));
	
    		else
    			RapportTable.setItems(new ReponseFormulaireTransaction().getAllNAP(id_formulaire, id_technicien, id_Ligne, Date.valueOf(date)));

    		
    	}

    }
    
    
    @FXML
    void updateLines(MouseEvent event) throws SQLException {
    	ZoneClicked();
    	if(ZonesList.getSelectionModel() != null) {
    		LinesList.getItems().clear();
    		int id_zone = zonesList.get(ZonesList.getSelectionModel().getSelectedIndex()).getIdZone();
    		lignesList = new LigneTransaction().getAllByZone(id_zone);
    		for(Ligne l : lignesList) {   		
    			LinesList.getItems().add(l.getLabelLigne());
    		}
    	}
    	
    	
    }
    
    
    private Boolean checkFields() {   	
    	if(TypesPlanningList.getSelectionModel().getSelectedItem() != null) {
    		if(TechsList.getSelectionModel().getSelectedItem() != null) {
    			if(ZonesList.getSelectionModel().getSelectedItem() != null) {
    				if(LinesList.getSelectionModel().getSelectedItem() != null) {
    					if(!TimePeacker.getValue().toString().isEmpty()) {	
    						
    						return true;
    						
    					} else {
    						
    						WarningTime.setVisible(true);
    						return false;
    						
    					}
    					
    					
    				}else {
    					
    					WarningLigne.setVisible(true);
						return false;
    					
    				}
    				
    				
    				
    			}else {
    				
    				WarningZone.setVisible(true);
					return false;
    				
    			}
    			
    			
    		}else {
    			
    			WarningTech.setVisible(true);
				return false;
    			
    		}
    		
    	} else {
    		
    		WarningTypePlaning.setVisible(true);
			return false;
    		 		
    	}
    	
    }
    
    
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ClientColumn.setCellValueFactory(new PropertyValueFactory<PlaningTable, String>("client"));
		ProjetColumn.setCellValueFactory(new PropertyValueFactory<PlaningTable, String>("projet"));
		FamilleColumn.setCellValueFactory(new PropertyValueFactory<PlaningTable, String>("famille"));
		DateColumn.setCellValueFactory(new PropertyValueFactory<PlaningTable, Date>("createdAt"));
		ActionsColumn.setCellValueFactory(new PropertyValueFactory<PlaningTable, HBox>("actions"));

	
		
		// Loading Planing Types :
		
		ObservableList<String> typesPlaning = FXCollections.observableArrayList();
		typesPlaning.addAll("Formulaire Audit Process", "Formulaire Audit Produit", "Formulaire Prise de données");		
		TypesPlanningList.setItems(typesPlaning);
		
		
		// Loading Planing Types :
		
		int id_Admin = 0;
		//int id_Admin = LoginController.LoggerCompte.getId();
		if(LoginController.LoggerCompte.getId_typecompte() == 1)
	
		id_Admin = LoginController.LoggerCompte.getId();
		
		try {
			 TechsOfThisAdminList = new CompteTransaction().getAllTechsByAdmin(id_Admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Employee e = null;
		
		for(Compte c : TechsOfThisAdminList) {
			try {
				e = new EmployeeTransaction().getById(c.getId_employee());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			TechsList.getItems().add(e.getNom() + "\t" + e.getPrenom());
			
		}
		
		
		try {
			 zonesList = new ZoneTransaction().getAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(Zone z : zonesList) {
			
			ZonesList.getItems().add(z.getLabelZone());
			
		}
		
		
	}
}
