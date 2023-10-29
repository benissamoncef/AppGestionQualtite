package DrawingsControllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Alerts;
import org.openjfx.SGQV2.Scan;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import Models.Client;
import Models.Drawing;
import Models.Famille;
import Models.Projet;
import Models.Reference;
import Transactions.ClientTransaction;
import Transactions.FamilleTransaction;
import Transactions.ProjetTransaction;
import Transactions.ReferenceTransaction;
import impl.com.calendarfx.view.NumericTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class ImporterDrawing implements Initializable{
	

	
		ObservableList<Famille> ListFamilles  = FXCollections.observableArrayList();
		ObservableList<Projet> ListProjets = FXCollections.observableArrayList();
		ObservableList<Client> ListClients = FXCollections.observableArrayList();	
		ObservableList<Reference> ListRéférences = FXCollections.observableArrayList();
	
	
	  	@FXML
	    private JFXButton AjouterDrawingField;

	    @FXML
	    private JFXComboBox<String> ComboBoxClient = new JFXComboBox<String>(); 

	    @FXML
	    private JFXComboBox<String> ComboBoxFamille;

	    @FXML
	    private JFXComboBox<String> ComboBoxProjet;

	    @FXML
	    private JFXComboBox<String> ComboBoxRéférence;

	    @FXML
	    private JFXButton ImportButton;

	    @FXML
	    private Label ImportPath;
	    

	    @FXML
	    private NumericTextField Révision;
	    
	    
	    


    @FXML
    void btnAjouterDrawingClicked(ActionEvent event) throws SQLException {
    	   	
    	if(!ComboBoxRéférence.getValue().toString().isEmpty() && !Révision.getText().isEmpty() &&  !ImportPath.getText().toString().isEmpty()) {
    		
    		String références_id = ListRéférences.get(ComboBoxRéférence.getSelectionModel().getSelectedIndex()).getCodeReferenceInterne();

    		DrawingTransaction DT = new DrawingTransaction();
    		
    		DT.save(new Drawing(new Scan().scanForDrawing(références_id), ImportPath.getText().toString(), Révision.getText()));
    		
    		new Alerts().showInformationAlert("Drawing Imported Successfully");
    		
	
    	}
    	
    	
    	clear();
    		
    }

    @FXML
    void btnImportClicked(ActionEvent event) {
    	new ChoosePdf().show();	
    	this.ImportPath.setText(ChoosePdf.path);
    }

    
    
    @FXML
    void updateFamilles(MouseEvent event) throws SQLException {
    	
    	ComboBoxRéférence.getItems().clear();
    	
    	ListFamilles  = new FamilleTransaction().getAll(ListProjets.get(ComboBoxProjet.getSelectionModel().getSelectedIndex()).getId());
    	
    	if(!ListFamilles.isEmpty()) {
    		int c = ListFamilles.size();
    		ObservableList<String> Familles = FXCollections.observableArrayList();
    		for(int i = 0; i < c; i++ ) {
    			Familles.add(ListFamilles.get(i).getCodeFamilleInterne());
    		
    		}
    		
    		ComboBoxFamille.setItems(Familles);
    	} 
    	
    }

    
    @FXML
    void updateProjets(MouseEvent event) throws SQLException {
    	
    	ComboBoxFamille.getItems().clear();
    	ComboBoxRéférence.getItems().clear();

    	
    	ListProjets  = new ProjetTransaction().getAllByClient(ListClients.get(ComboBoxClient.getSelectionModel().getSelectedIndex()).getId());
    	
    	if(!ListProjets.isEmpty()) {
    		int c = ListProjets.size();
    		ObservableList<String> Projects = FXCollections.observableArrayList();
    		for(int i = 0; i < c; i++ ) {
    			Projects.add(ListProjets.get(i).getLabelProjet());
    		
    		}
    	  		
    		ComboBoxProjet.setItems(Projects);
    	
    	}
    }

    
    @FXML
    void updateRéférences(MouseEvent event) throws SQLException {
    	

    	
    	int id_projet = ListProjets.get(ComboBoxProjet.getSelectionModel().getSelectedIndex()).getId();
    	
    	int id_famille = ListFamilles.get(ComboBoxFamille.getSelectionModel().getSelectedIndex()).getIdFamille();

    	
    	ListRéférences  = new ReferenceTransaction().getAll(id_projet, id_famille);
    	
    	
    	if(!ListRéférences.isEmpty()) {
    		int c = ListRéférences.size();
    		ObservableList<String> Références = FXCollections.observableArrayList();
    		for(int i = 0; i < c; i++ ) {
    			Références.add(ListRéférences.get(i).getCodeReferenceInterne());
    		}
    		
    		
    		ComboBoxRéférence.setItems(Références);
    		
    	} 
   	
    }
    
    
    private void clear() {
    ComboBoxClient.setValue(null);	
    ComboBoxProjet.getItems().clear();
    ComboBoxFamille.getItems().clear();
    ComboBoxRéférence.getItems().clear();
	ImportPath.setText(null);
	Révision.setText(null);
	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
		try {
			ListClients = new ClientTransaction().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		for(Client c : ListClients) {
			
			ComboBoxClient.getItems().add(c.getLabelClient());
		
		}
		
	
	}

	
	

}
