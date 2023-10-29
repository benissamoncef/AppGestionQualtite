package org.openjfx.SGQV2.Administration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openjfx.SGQV2.Alerts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Models.Employee;
import Transactions.EmployeeTransaction;
import impl.com.calendarfx.view.NumericTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

public class Add_ModEmployeeController implements Initializable {
	
	
	public static Employee employee;
	public static Boolean isCreateEmployee;
	
	 @FXML
	    private JFXButton BtnAjouterEmployée;

	    @FXML
	    private JFXTextArea CommentaireField;

	    @FXML
	    private JFXTextField EmailField;

	    @FXML
	    private NumericTextField  MatriculeField;

	    @FXML
	    private JFXTextField MissionField;

	    @FXML
	    private JFXTextField NomField;

	    @FXML
	    private JFXTextField PosteField;

	    @FXML
	    private JFXTextField PrenomField;

	    @FXML
	    private JFXTextField TelephoneField;
	    
	    @FXML
	    private JFXButton BtnModifierEmployée;
	    
    
	    
	    @FXML
	    void BtnModifierEmployéeClicked(ActionEvent event) throws SQLException {	
	    	
	        	new EmployeeTransaction().update(new Employee (
	        			employee.getMatricule(), 
	        			NomField.getText().toString(),
	        			PrenomField.getText().toString(),
	        			TelephoneField.getText().toString(),
	        			EmailField.getText().toString(),
	        			MissionField.getText().toString(),
	        			PosteField.getText().toString(),
	        			CommentaireField.getText().toString()
	        			      			
	        			));

	        	new Alerts().showInformationAlert("Employee Updated Successfully.");

	        	Stage stage = (Stage) BtnModifierEmployée.getScene().getWindow();
		    	stage.close();
	       }
	    
	    

    @FXML
    void BtnAjouterEmployéeClicked(ActionEvent event) throws SQLException {
    	
    		String Prenom = PrenomField.getText().toString();
    		String Nom = NomField.getText().toString();
    		// vérifier si le matricule est integer ?
    		Integer Matricule = Integer.parseInt(MatriculeField.getText().toString());
    		
    	
    	if(!Prenom.toString().isEmpty() && !Nom.toString().isEmpty() && !Matricule.toString().isEmpty()) {
    		String Telephone = TelephoneField.getText().toString();
    		String Email = EmailField.getText().toString();
    		String Mission = MissionField.getText().toString();
    		String Poste = PosteField.getText().toString();
    		String Commentaire = CommentaireField.getText().toString();
    		
    		
    		if(!vérifierNumero(Telephone) && !Telephone.isEmpty()) {   			
    			new Alerts().showInformationAlert("Numero Invalid !!!");
    			return;
    		}
    		
    		if(!vérifierEmail(Email) && !Email.isEmpty()) {
    			new Alerts().showInformationAlert("Email Invalid !!!");
    			return;
    		}
    		
    		
    		new EmployeeTransaction().save(new Employee(Matricule, Nom, Prenom, Telephone, Email, Mission, Poste, Commentaire));
    		
    		new Alerts().showInformationAlert("Employee Created Successfully.");
    		
    		
    		Stage stage = (Stage) BtnAjouterEmployée.getScene().getWindow();
	    	stage.close();
    		
    		
    	} 	
    }
    
    public boolean vérifierNumero(String tel) {
   
        String regex = "^(?:(?:\\+|00)212|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$";
        
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(tel);
        
        
        if(matcher.matches()) return true;
        
        return false;

        
     }
    
    
    public boolean vérifierEmail(String Email) {
 
    	String regex = "^(.+)@(.+)$";  
        //Compile regular expression to get the pattern  
        Pattern pattern = Pattern.compile(regex);  

        // checking the email
       
        Matcher matcher = pattern.matcher(Email);  
       
        if(matcher.matches()) return true;
        return false;
        

        
     }




	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(isCreateEmployee) {
			BtnAjouterEmployée.setVisible(true);
			BtnModifierEmployée.setVisible(false);
		}
		
		else {
			BtnAjouterEmployée.setVisible(false);
			BtnModifierEmployée.setVisible(true);
			
			
			MatriculeField.setText(employee.getMatricule().toString());
			NomField.setText(employee.getNom());
			PrenomField.setText(employee.getPrenom());
			TelephoneField.setText(employee.getNumero_telephone());
			EmailField.setText(employee.getEmail());
			MissionField.setText(employee.getMission());
			PosteField.setText(employee.getPoste());
			CommentaireField.setText(employee.getCommentaire());
			
			
		}
		
		
		
	}
    
    
    
    
    
    
    
    

}
