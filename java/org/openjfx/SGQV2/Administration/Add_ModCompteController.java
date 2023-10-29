package org.openjfx.SGQV2.Administration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Models.Compte;
import Models.Employee;
import Models.TypeCompte;
import Transactions.CompteTransaction;
import Transactions.EmployeeTransaction;
import Transactions.TypeCompteTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Add_ModCompteController implements Initializable {
	
	
	
		public static Compte compte;
		public static Boolean isCreateCompte;
		
	
		ObservableList<Employee> EmployeesAdmins;
		ObservableList<Employee> Employees;

		ObservableList<String> TypesCompte = FXCollections.observableArrayList();
		
		
		@FXML
	    private JFXButton BtnAjouterCompte;
		
		@FXML
	    private JFXButton BtnModifierCompte;

	    @FXML
	    private JFXComboBox<String> ComboBoxAdministrateur = new JFXComboBox<String>();

	    @FXML
	    private JFXComboBox<String> ComboBoxEmployée;

	    @FXML
	    private JFXComboBox<String> ComboBoxTypesCompte;

	    @FXML
	    private JFXPasswordField ConfirmPasswordField;


	    @FXML
	    private JFXPasswordField PasswordField;

	    @FXML
	    private JFXTextField UsernameField;

	    
	    @FXML
	    private Label labelAdmin;


	    

	    @FXML
	    void BtnAjouterCompteClicked(ActionEvent event) throws SQLException {

	    	if(VérifierChamps()) {
	    		int admin = 0;
	    		if(ComboBoxAdministrateur.getValue() != null) {
	    			admin = EmployeesAdmins.get(ComboBoxAdministrateur.getSelectionModel().getSelectedIndex()).getMatricule();
	    		}
	    		
	    		
	    		new CompteTransaction().save(new Compte( 
	        			null,
	        			null,
	        			UsernameField.getText().toString(),
	        			PasswordField.getText().toString(),
	        			false,
	        			Employees.get(ComboBoxEmployée.getSelectionModel().getSelectedIndex()).getMatricule(),
	        			admin,   
	        			ComboBoxTypesCompte.getSelectionModel().getSelectedIndex()	        
	    				));
	    		new Alerts().showInformationAlert("Account Created Successfully.");
	    	}
	    	
	    	
	    	Stage stage = (Stage) BtnAjouterCompte.getScene().getWindow();
	    	stage.close();

	    }
	    
	    @FXML
	    void BtnModifierCompteClicked(ActionEvent event) throws SQLException {   
	    	if(VérifierChamps()) {
	        	new CompteTransaction().update(new Compte (
	        			compte.getId(), 
	        			null,
	        			null,
	        			UsernameField.getText().toString(),
	        			PasswordField.getText().toString(),
	        			false,
	        			null,
	        			ComboBoxTypesCompte.getSelectionModel().getSelectedIndex(),
	        			EmployeesAdmins.get(ComboBoxAdministrateur.getSelectionModel().getSelectedIndex()).getMatricule()          			    			      	
	        			));
	        	
	        	new Alerts().showInformationAlert("Account Updated Successfully.");
	        	Stage stage = (Stage) BtnModifierCompte.getScene().getWindow();
	        	stage.close();
	        	
	        }
	    	
	        
	    }

  
	    @FXML
	    public void TechnicienSelected(ActionEvent event) {
			if(ComboBoxTypesCompte.getValue().toString().equals("technicienQualité")) {
				setAdminVisible(true);
			}
			else {
				setAdminVisible(false);
			}
		}
	    
	    private void setAdminVisible(Boolean b) {
	    	if(b) {
	    		
	    		labelAdmin.setVisible(true);
				ComboBoxAdministrateur.setVisible(true);
				labelAdmin.setDisable(false);
				ComboBoxAdministrateur.setDisable(false);
	    		
	    	}else {
	    	
	    	labelAdmin.setVisible(false);
			ComboBoxAdministrateur.setVisible(false);
			labelAdmin.setDisable(true);
			ComboBoxAdministrateur.setDisable(true);
			
	    	}
	    }

	    
	    

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			// adding items to types comptes combo box :
			
			
			ObservableList<String> TypesCompte = FXCollections.observableArrayList();	
			
			TypesCompte.add("Admin");
			TypesCompte.add("manager");
			TypesCompte.add("technicienQualité");			
			TypesCompte.add("dashboard");
			TypesCompte.add("technicienProcess");

			ComboBoxTypesCompte.setItems(TypesCompte);
			
			
			
			
			// downloading Employées administrateurs :
					
			
			
			
			
			try {
				EmployeesAdmins  = new EmployeeTransaction().getAllAdmins();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ObservableList<String> EmployeesAdminsList = FXCollections.observableArrayList();	

			if(EmployeesAdmins != null) {
		
				for(int i = 0; i < EmployeesAdmins.size(); i++) {
			
					EmployeesAdminsList.add(EmployeesAdmins.get(i).getNom() + "\t" + EmployeesAdmins.get(i).getPrenom());
			
				}
			}
		

			ComboBoxAdministrateur.setItems(EmployeesAdminsList);

			
			setAdminVisible(false);
			
			
			
			
			
			
			
			// downloading & adding Employées to the combo box :
			
			
			
			
			try {
				Employees  = new EmployeeTransaction().getAll();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ObservableList<String> EmployeesList = FXCollections.observableArrayList();	

			if(Employees != null) {
		
				for(int i = 0; i < Employees.size(); i++) {
			
					EmployeesList.add(Employees.get(i).getNom() + "\t" + Employees.get(i).getPrenom());
			
				}
			}
		

			ComboBoxEmployée.setItems(EmployeesList);

			
			if(isCreateCompte) {
				BtnAjouterCompte.setVisible(true);
				BtnModifierCompte.setVisible(false);
			
			}
			
			else {
				BtnAjouterCompte.setVisible(false);
				BtnModifierCompte.setVisible(true);

			
				Employee e = null;
				
				try {
					e = new EmployeeTransaction().getById(compte.getId_employee());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				ComboBoxEmployée.setValue(e.getNom() + "\t" + e.getPrenom());
				
				
				TypeCompte t = null;
				try {
					t = new TypeCompteTransaction().getById(compte.getId_typecompte());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ComboBoxTypesCompte.setValue(t.getLabelType()); 
				
				
				if(compte.getId_typecompte() == 3) {
					ComboBoxAdministrateur.setValue(compte.getId_Administrateur().toString());
				}
				
				UsernameField.setText(compte.getUsername());
				PasswordField.setText(compte.getPassword());
				ConfirmPasswordField.setText(PasswordField.getText());
				
				
			}
			
			
			
			
		}
		
		
		private Boolean VérifierChamps() {
			if(!ComboBoxEmployée.getValue().isEmpty()) {
				if(!ComboBoxTypesCompte.getValue().isEmpty()) {
					if(ComboBoxTypesCompte.getValue().equals("technicienQualité")) {
						if(ComboBoxAdministrateur.getValue().isEmpty()) {
							new Alerts().showInformationAlert("Administrateur is not selected !!!");
							
							return false;
						}
					}	
					if(!UsernameField.getText().isEmpty()) {
							if(!PasswordField.getText().isEmpty() && !ConfirmPasswordField.getText().isEmpty() /*&& checkPSW()*/) {
								return true;
							}
							else {
								new Alerts().showInformationAlert("Password Problem !!!");

								return false;
							}
					}
					else {

						new Alerts().showInformationAlert("Username Problem !!!");

						return false;
					}
					
				}	
				else {
					new Alerts().showInformationAlert("Type Compte is not selected !!!");

					return false;		
				}				
		
				
			}
		
			else {
				
				new Alerts().showInformationAlert("Employée is not selected !!!");

				return false;
			}
							
			}

			
			
			
			
	

}
