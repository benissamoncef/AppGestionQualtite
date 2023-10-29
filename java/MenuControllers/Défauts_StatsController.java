package MenuControllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;

import Models.Client;
import Models.Défaut;
import Models.Famille;
import Models.Ligne;
import Models.Projet;
import Models.Zone;
import Transactions.ClientTransaction;
import Transactions.DéfautTransaction;
import Transactions.FamilleTransaction;
import Transactions.LigneTransaction;
import Transactions.ProjetTransaction;
import Transactions.StatsTransaction;
import Transactions.ZoneTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import tornadofx.control.DateTimePicker;

public class Défauts_StatsController implements Initializable{
	
	
	ObservableList<Défaut> DéfautsList = FXCollections.observableArrayList();


    @FXML
    private StackPane rootStackPane;
    
    @FXML
    private AnchorPane anchorPane;
	
	@FXML
    private JFXCheckBox AllFilterCheckBox;

    @FXML
    private BarChart<String, Long> BarChart;

    @FXML
    private Button BtnSearch;

    @FXML
    private JFXComboBox<String> ClientComboBox;

    @FXML
    private JFXCheckBox ClientFilterCheckBox;

    @FXML
    private JFXCheckBox FamilleFilterCheckBox;

    @FXML
    private CategoryAxis LabelX;

    @FXML
    private NumberAxis LabelY;

    @FXML
    private JFXCheckBox LigneFilterCheckBox;

    @FXML
    private JFXComboBox<String> ProjetComboBox;

    @FXML
    private JFXCheckBox ProjetFilterCheckBox;

    @FXML
    private JFXComboBox<String> SpécifiqueFilter;

    @FXML
    private JFXComboBox<String> TimeComboBox;


    @FXML
    private JFXComboBox<String> ZoneComboBox;

    @FXML
    private JFXCheckBox ZoneFilterCheckBox;

    @FXML
    private DateTimePicker dayPeaker;

    @FXML
    private DateTimePicker monthPeaker;

    @FXML
    private DateTimePicker yearPicker;
    
    @FXML
    private Label labelTemps;
    
    
    
    public ObservableList<Client> clients = FXCollections.observableArrayList();
	ObservableList<Zone> zonesList = FXCollections.observableArrayList();
	ObservableList<Projet> Projets = FXCollections.observableArrayList();
	ObservableList<Famille> Familles = FXCollections.observableArrayList();
	ObservableList<Ligne> lignesList = FXCollections.observableArrayList();

	
    

    @FXML
    void ProjetComboBoxClicked(MouseEvent event) throws SQLException {
    	if(!ClientComboBox.getValue().isEmpty()) {
    		ProjetComboBox.getItems().clear();
    		int id_client = new ClientTransaction().getByLabelClient(ClientComboBox.getValue()).getId();
    		getProjetsByClient(id_client, true);	
    	}
    }
    
    public void getProjetsByClient(int id_client, Boolean b) throws SQLException {
    	

        Projets = new ProjetTransaction().getAllByClient(id_client);   
        if(Projets != null) {
        	if(b) {
        		for(int i = 0; i < Projets.size(); i++) {     	
        			ProjetComboBox.getItems().add(Projets.get(i).getLabelProjet());    	
        		}
        	} else {
        		for(int i = 0; i < Projets.size(); i++) {     	
        			SpécifiqueFilter.getItems().add(Projets.get(i).getLabelProjet());    	
        		}       		
        	}
        	
        }
   	
    }
    

    @FXML
    void SpécifiqueFilterClicked(MouseEvent event) throws SQLException {
    	SpécifiqueFilter.getItems().clear();
    	  
    	SpécifiqueFilter.getItems().add("All");
    	
    	if(ClientFilterCheckBox.selectedProperty().get()) { 	
    	
    		getAllClients(false);    		   		
    		
    	} else if(ProjetFilterCheckBox.selectedProperty().get() && !ClientComboBox.getValue().isEmpty()) {
 
    		int id_client = new ClientTransaction().getByLabelClient(ClientComboBox.getValue()).getId();
    		getProjetsByClient(id_client, false);
    		
    		
    	} else if(FamilleFilterCheckBox.selectedProperty().get() && !ClientComboBox.getValue().isEmpty() && !ProjetComboBox.getValue().isEmpty()) {
    		
    		int id_projet = new ProjetTransaction().getByLabel(ProjetComboBox.getValue()).getId();
    		getFamillesByProjet(id_projet);
    		
    		
    	} else if(LigneFilterCheckBox.selectedProperty().get() && !ZoneComboBox.getValue().isEmpty()) {
    		
    		int id_zone = new ZoneTransaction().getByLabelZone(ZoneComboBox.getValue()).getIdZone();
    		lignesList = new LigneTransaction().getAllByZone(id_zone);         
            for(int i = 0; i < lignesList.size(); i++) {     	
            	SpécifiqueFilter.getItems().add(lignesList.get(i).getLabelLigne());    	
            }		
    		
    	} else if(ZoneFilterCheckBox.selectedProperty().get()) {
    		  		        
            for(int i = 0; i < zonesList.size(); i++) {     	
            	SpécifiqueFilter.getItems().add(zonesList.get(i).getLabelZone());    	
            }		   		
    	}
    	else if(AllFilterCheckBox.selectedProperty().get()) {
		        
            for(int i = 0; i < DéfautsList.size(); i++) {     	
            	SpécifiqueFilter.getItems().add(String.valueOf(DéfautsList.get(i).getCode()));    	
            }
            		   		
    	}
    }
    
    
    public void getFamillesByProjet(Integer id_projet) throws SQLException { 

        Familles = new FamilleTransaction().getAll(id_projet);   
        
        if(Familles != null) {        	
        	for(int i = 0; i < Familles.size(); i++) {     	
        		SpécifiqueFilter.getItems().add(Familles.get(i).getCodeFamilleInterne());    	
        	}
        }
    	
    }
       

    public void getAllClients(Boolean b) throws SQLException {
    	

		if(clients != null) {
			if(b) {
				for(int i = 0; i < clients.size(); i++) {
					ClientComboBox.getItems().add(clients.get(i).getLabelClient());
				}
			} else {
				for(int i = 0; i < clients.size(); i++) {
					SpécifiqueFilter.getItems().add(clients.get(i).getLabelClient());
				}
			}			
		}
	
    }
    
 

    @FXML
    void TimeComboBoxClicked(ActionEvent event) {
    	
    	yearPicker.setVisible(false);
    	monthPeaker.setVisible(false);
    	dayPeaker.setVisible(false);
    	
    	switch(TimeComboBox.getValue()) {
    	   	
    	case "Par Année": yearPicker.setVisible(true);break;
    	case "Par Mois": monthPeaker.setVisible(true);break;
    	case "Par jour": dayPeaker.setVisible(true);break;
    	
    	}  

    }

    @FXML
    void checkBoxClicked(ActionEvent event) throws SQLException { 	
    	JFXCheckBox BtnClicked = (JFXCheckBox)event.getSource();
    	String idBtnClicked = BtnClicked.getId();  
   	
    	// uncheck all check boxes:
    	ResetAllCheckBoxes();
    	ResetView();
    	
    	
    	

    	switch(idBtnClicked) {
    	
    	case "AllFilterCheckBox":{  		
    		AllFilterCheckBox.selectedProperty().set(true);
    		showStatistiques('d', false, false, 'x');
    		break;
    	}
    	case "ClientFilterCheckBox":{   		
    		ClientFilterCheckBox.selectedProperty().set(true);
    		showStatistiques('c', false, false, 'x');
    		break;
    	}
    	case "ProjetFilterCheckBox":{ 		
    		ProjetFilterCheckBox.selectedProperty().set(true);	
    		ClientComboBox.setVisible(true);
    		showStatistiques('p', false, false, 'x');
    		break;
    	}
    	case "FamilleFilterCheckBox":{   		
    		FamilleFilterCheckBox.selectedProperty().set(true);	
    		ClientComboBox.setVisible(true);
    		ProjetComboBox.setVisible(true);
    		showStatistiques('f', false, false, 'x');
    		break; 	
    	}
    	
    	case "ZoneFilterCheckBox":
    	{		
    	ZoneFilterCheckBox.selectedProperty().set(true);
    	showStatistiques('z', false, false, 'x');
		break; 	
		}

    	case "LigneFilterCheckBox":
    	{
		
    	LigneFilterCheckBox.selectedProperty().set(true);
    	ZoneComboBox.setVisible(true);
    	showStatistiques('l', false, false, 'x');
		break; 	
		}

    	
    }

    }
    
    // uncheck all check boxes:
    private void ResetAllCheckBoxes() {  
    	
    	AllFilterCheckBox.selectedProperty().set(false);
    	ClientFilterCheckBox.selectedProperty().set(false);	
    	ProjetFilterCheckBox.selectedProperty().set(false);
    	FamilleFilterCheckBox.selectedProperty().set(false);
    	ZoneFilterCheckBox.selectedProperty().set(false);	
    	LigneFilterCheckBox.selectedProperty().set(false);

    }
    
    
    private void ResetView() {
    	ClientComboBox.setVisible(false);
    	ProjetComboBox.setVisible(false);
    	ZoneComboBox.setVisible(false);  
    	SpécifiqueFilter.getItems().clear();
    	SpécifiqueFilter.setVisible(true);	
    	BtnSearch.setVisible(true);
    	TimeComboBox.setValue("All");
    	TimeComboBox.setVisible(true);
    	BarChart.setVisible(true);
    	BarChart.getData().clear();
    	dayPeaker.setVisible(false);
    	monthPeaker.setVisible(false);
    	yearPicker.setVisible(false);
    	labelTemps.setVisible(true);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

		// setting time options:
		
		ObservableList<String> timeOptions = FXCollections.observableArrayList();
		
		timeOptions.addAll(new String[] {"All", "Par Année", "Par Mois", "Par jour"});	
		
		TimeComboBox.setItems(timeOptions);
		
		
		
		// setting all clients :

		
			try {
				
				clients = new ClientTransaction().getAll();
				for(int i = 0; i < clients.size(); i++) {
					ClientComboBox.getItems().add(clients.get(i).getLabelClient());
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
			BtnSearch.setVisible(false);
			
		
		// setting all Zones:
		

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
		
		
		
		// setting all défauts:
		
		try {
			DéfautsList = new DéfautTransaction().getAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	

    @FXML
    void BtnSearchClicked(ActionEvent event) throws SQLException {
    	
    	if(AllFilterCheckBox.selectedProperty().get()) {
    		
    		switch(TimeComboBox.getValue()) {
    		case "All":{
    			if(SpécifiqueFilter.getValue().toString().equals("All")) {
    				
    				showStatistiques('d', false, false, 'a');
    				
    			} else {
    				
    				showStatistiques('d', false, true, 'a');
    				
    			}
    			
    			
    		} break;
        	case "Par Année":{
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('d', true, false, 'y');
    				
    			} else {
    				
    				showStatistiques('d', true, true, 'y');
    				
    			}
        		
        		
        	}break;
        	case "Par Mois": {
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('d', true, false, 'm');
    				
    			} else {
    				
    				showStatistiques('d', true, true, 'm');
    				
    			}
        		
        		
        	}break;
        	case "Par jour": {
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('d', true, false, 'd');
    				
    			} else {
    				
    				showStatistiques('d', true, true, 'd');
    				
    			}
        		
        		
        	}break;
        	
        	} 

    		
    	}else if(ClientFilterCheckBox.selectedProperty().get()) {
    		switch(TimeComboBox.getValue()) {
    		
    		
    		case "All":{
    			if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('c', false, false, 'a');
    				
    			} else {
    				
    				showStatistiques('c', false, true, 'a');
    				
    			}
    			
    			
    		}break;
        	case "Par Année":{
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('c', true, false, 'y');
    				
    			} else {
    				
    				showStatistiques('c', true, true, 'y');
    				
    			}
        		
        		
        	}break;
        	case "Par Mois": {
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('c', true, false, 'm');
    				
    			} else {
    				
    				showStatistiques('c', true, true, 'm');
    				
    			}
        		
        		
        	}break;
        	case "Par jour": {
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('c', true, false, 'd');
    				
    			} else {
    				
    				showStatistiques('c', true, true, 'd');
    				
    			}
        		
        	}break;	
    	}
    		
    	}else if(ProjetFilterCheckBox.selectedProperty().get()) {
    		switch(TimeComboBox.getValue()) {
    		
    		
    		case "All":{
    			if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('p', false, false, 'a');
    				
    			} else {
    				
    				showStatistiques('p', false, true, 'a');
    				
    			}
    			
    			
    		}break;
        	case "Par Année":{
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('p', true, false, 'y');
    				
    			} else {
    				
    				showStatistiques('p', true, true, 'y');
    				
    			}
        		
        		
        	}break;
        	case "Par Mois": {
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('p', true, false, 'm');
    				
    			} else {
    				
    				showStatistiques('p', true, true, 'm');
    				
    			}
        		
        		
        	}break;
        	case "Par jour": {
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('p', true, false, 'd');
    				
    			} else {
    				
    				showStatistiques('p', true, true, 'd');
    				
    			}
        		
        	}break;
		}
    		
    	}else if(FamilleFilterCheckBox.selectedProperty().get()) {
    		switch(TimeComboBox.getValue()) {
    		
    		
    		case "All":{
    			if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('f', false, false, 'a');
    				
    			} else {
    				
    				showStatistiques('f', false, true, 'a');
    				
    			}
    			
    			
    		}break;
        	case "Par Année":{
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('f', true, false, 'y');
    				
    			} else {
    				
    				showStatistiques('f', true, true, 'y');
    				
    			}
        		
        		
        	}break;
        	case "Par Mois": {
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('f', true, false, 'm');
    				
    			} else {
    				
    				showStatistiques('f', true, true, 'm');
    				
    			}
        		
        		
        	}break;
        	case "Par jour": {
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('f', true, false, 'd');
    				
    			} else {
    				
    				showStatistiques('f', true, true, 'd');
    				
    			}
        		
        	}break;
    	}
    		
    	}else if(ZoneFilterCheckBox.selectedProperty().get()) {
    		switch(TimeComboBox.getValue()) {
    		
    		
    		case "All":{
    			if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('z', false, false, 'a');
    				
    			} else {
    				
    				showStatistiques('z', false, true, 'a');
    				
    			}
    			
    			
    		}break;
        	case "Par Année":{
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('z', true, false, 'y');
    				
    			} else {
    				
    				showStatistiques('z', true, true, 'y');
    				
    			}
        		
        		
        	}break;
        	case "Par Mois": {
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('z', true, false, 'm');
    				
    			} else {
    				
    				showStatistiques('z', true, true, 'm');
    				
    			}
        		
        		
        	}break;
        	case "Par jour": {
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('z', true, false, 'd');
    				
    			} else {
    				
    				showStatistiques('z', true, true, 'd');
    				
    				}
        		
        		}break;
    		
    		}
    	}else if(LigneFilterCheckBox.selectedProperty().get()) {
    		switch(TimeComboBox.getValue()) {
    		
    		
    		case "All":{
    			if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('l', false, false, 'a');
    				
    			} else {
    				
    				showStatistiques('l', false, true, 'a');
    				
    			}
    			
    			
    		}break;
        	case "Par Année":{
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('l', true, false, 'y');
    				
    			} else {
    				
    				showStatistiques('l', true, true, 'y');
    				
    			}
        		
        		
        	}break;
        	case "Par Mois": {
        		
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('l', true, false, 'm');
    				
    			} else {
    				
    				showStatistiques('l', true, true, 'm');
    				
    			}
        		
        		
        	}break;
        	case "Par jour": {
        		if(SpécifiqueFilter.getValue().equals("All")) {
    				
    				showStatistiques('l', true, false, 'd');
    				
    			} else {
    				
    				showStatistiques('l', true, true, 'd');
    				
    				}
        		
        		}break;
    		
    			}
    	
    	

    		}
    	}
    
    
    public void showStatistiques(char ch, Boolean isTime, Boolean isSpec, char typeTime) throws SQLException {
		BarChart.getData().clear();
		
		
		ProgressIndicator pi = new ProgressIndicator();
        VBox box = new VBox(pi);
        box.setAlignment(Pos.CENTER);
        
        anchorPane.setDisable(true);
        
        rootStackPane.getChildren().add(box);
     
        
		switch(ch) {
			
			case 'd' : {
				
				if(isTime || isSpec) {
					if(isTime && isSpec) {	
						
						Défaut d = DéfautsList.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);

						switch(typeTime) {
						case 'y':	{	
							
						
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();

		    				Series.setName("Qte du Défaut : " + d.getDescription() + " en : " + year);

							
							long qte = (long) new StatsTransaction().getQteBy_Code_Time(d.getCode(), year, 0, 0);
							
							
							Series.getData().add(new XYChart.Data<>(d.getCode().toString(), qte));
		    				
		    				BarChart.getData().add(Series);
							
	
				
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();

		    				Series.setName("Qte du Défaut : " + d.getDescription() + " en : " + month + "/" + year);

							
							long qte = (long) new StatsTransaction().getQteBy_Code_Time(d.getCode(), year, month, 0);
							
							
							Series.getData().add(new XYChart.Data<>(d.getCode().toString(), qte));
		    				
		    				BarChart.getData().add(Series);
							
		    				
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
											
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();

		    				Series.setName("Qte du Défaut : " + d.getDescription() + " en : " + day + "/" + month + "/" + year);

							
							long qte = (long) new StatsTransaction().getQteBy_Code_Time(d.getCode(), year, month, day);							
							
							Series.getData().add(new XYChart.Data<>(d.getCode().toString(), qte));
		    				
		    				BarChart.getData().add(Series);
					

						}break;
			
									
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('c', false, false, 'x');break;
						case 'y':	{
							
							
							int year = yearPicker.getValue().getYear();
							
							long qte = 0;

							XYChart.Series<String, Long> Series = new XYChart.Series<>();

		    				Series.setName("Qte des Défauts en : " + year);
							
		    				for(Défaut d : DéfautsList) {
		    					
								 qte = (long) new StatsTransaction().getQteBy_Code_Time(d.getCode(), year, 0, 0);							
								if(qte != 0) {
									Series.getData().add(new XYChart.Data<>(d.getCode().toString(), qte));
								}
		    					
		    					
		    				}
							
		    				BarChart.getData().add(Series);
							
							
							
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							
							long qte = 0;

							XYChart.Series<String, Long> Series = new XYChart.Series<>();

		    				Series.setName("Qte des Défauts en : " + month + "/" + year);
							
		    				for(Défaut d : DéfautsList) {
		    					
								 qte = (long) new StatsTransaction().getQteBy_Code_Time(d.getCode(), year, month, 0);	
								 
								if(qte != 0) {
									Series.getData().add(new XYChart.Data<>(d.getCode().toString(), qte));
								}
		    					
		    					
		    				}
							
		    				BarChart.getData().add(Series);
							
						
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							
							long qte = 0;

							XYChart.Series<String, Long> Series = new XYChart.Series<>();

		    				Series.setName("Qte des Défauts en : " + day + "/" + month + "/" + year);
							
		    				for(Défaut d : DéfautsList) {
		    					
								 qte = (long) new StatsTransaction().getQteBy_Code_Time(d.getCode(), year, month, day);	
								 
								if(qte != 0) {
									Series.getData().add(new XYChart.Data<>(d.getCode().toString(), qte));
								}
		    					
		    					
		    				}
							
		    				BarChart.getData().add(Series);

						
						}break;
						
						
						
					}
					else if(!isTime && isSpec){
						
						Défaut d = DéfautsList.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);
											
						XYChart.Series<String, Long> Series = new XYChart.Series<>();

	    				Series.setName("Qte du Défaut : " + d.getDescription());

						
						long qte = (long) new StatsTransaction().getQteBy_Code(d.getCode());							
						
						Series.getData().add(new XYChart.Data<>(d.getCode().toString(), qte));
	    				
	    				BarChart.getData().add(Series);
						
						
					}
				
					
					} else {
					

					
					long qte = 0;

					XYChart.Series<String, Long> Series = new XYChart.Series<>();

    				Series.setName("Qte des Défauts");

		
    				for(Défaut d : DéfautsList) {
    					
							qte = (long) new StatsTransaction().getQteBy_Code(d.getCode());	

							
						if(qte != 0) {
							
							Series.getData().add(new XYChart.Data<>(d.getCode().toString(), qte));
						}
    					
    					
    				}
					
    				BarChart.getData().add(Series);

		

				}
			
	
			}break;                  

			case 'c' : {

				if(isTime || isSpec) {
					if(isTime && isSpec) {	
						
						Client c = clients.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);

						
						switch(typeTime) {
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour le client : " + c.getLabelClient() + " en : " + year);				
    			
		    				List<Integer> listDéfauts = new StatsTransaction().getClientDéfauts(c.getId(),true, year, 0, 0);
		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    while (it.hasNext()) {

		    			    	
		    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    }
		    				
		    				BarChart.getData().add(Series);
		    				
				
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour le client : " + c.getLabelClient() + " en : "+ month + "/" + year);
		    				
		    				List<Integer> listDéfauts = new StatsTransaction().getClientDéfauts(c.getId(),true, year, month, 0);
		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    while (it.hasNext()) {

		    			    	
		    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    }
		    				
		    				BarChart.getData().add(Series);
		    				
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour le client : " + c.getLabelClient() + " en : " + day + "/" + month + "/" + year);
		    				
		    				List<Integer> listDéfauts = new StatsTransaction().getClientDéfauts(c.getId(),true, year, month, day);
		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    while (it.hasNext()) {

		    			    	
		    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    }
		    				
		    				BarChart.getData().add(Series);
						

						}break;
			
									
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('c', false, false, 'x');break;
						case 'y':	{
							int year = yearPicker.getValue().getYear();

							XYChart.Series<String, Long> Series;
							
		    				for(Client c : clients) {
		    					Series = new XYChart.Series<>();
		    					Series.setName("Défauts pour le Client " + c.getLabelClient() + "en :" + year);
		    					
		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getClientDéfauts(c.getId(),true, year, 0, 0);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
			    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    while (it.hasNext()) {

			    			    	
			    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    }
		    					
			    			    BarChart.getData().add(Series);
		    					
		    				}
		    				
		    				
							
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							
							XYChart.Series<String, Long> Series;
		    				
		    				for(Client c : clients) {
		    					
		    					Series = new XYChart.Series<>();
			    				
			    				Series.setName("Défauts pour le Client " + c.getLabelClient() + "en :" + month + "/" + year);
		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getClientDéfauts(c.getId(),true, year, month, 0);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
			    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    while (it.hasNext()) {

			    			    	
			    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    }
			    			    BarChart.getData().add(Series);
		    					
		    				}
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							
							XYChart.Series<String, Long> Series;
							
		    				for(Client c : clients) {
		    					
		    					 Series = new XYChart.Series<>();
			    				
			    				Series.setName("Défauts pour le Client " + c.getLabelClient() + "en :" + day + "/" + month + "/" + year);

		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getClientDéfauts(c.getId(),true, year, month, 0);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
			    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    while (it.hasNext()) {

			    			    	
			    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    }
		    					
			    			    BarChart.getData().add(Series);
		    					
		    				}
							
						
						
						
						}break;
						
						
						
					}
					else if(!isTime && isSpec){
						
						Client c = clients.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);
						
				
						XYChart.Series<String, Long> Series = new XYChart.Series<>();
	    				
	    				Series.setName("Défauts pour le client : " + c.getLabelClient());
	    				
	    				List<Integer> listDéfauts = new StatsTransaction().getClientDéfauts(c.getId(),true, 0, 0, 0);
	    				
	    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
	    				
	    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

	    				
	    			    while (it.hasNext()) {

	    			    	
	    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
	    			        
	    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
	    			        
	    			    }
	    			    
	    			    BarChart.getData().add(Series);
	    			    
						
					}
				
					
				} else {
				
								
    				XYChart.Series<String, Long> Series;
    				
    				ObservableList<XYChart.Series<String, Long>> ListSeries = FXCollections.observableArrayList();


    				
    				for(Client c : clients) {
    					
   					 	Series = new XYChart.Series<>();
	    				
   					 	Series.setName("Défauts pour le Client " + c.getLabelClient());

   					
   					 	List<Integer> listDéfauts = new StatsTransaction().getClientDéfauts(c.getId(),true, 0, 0, 0);
				 
	    				
   					 	Map<Integer, Long> result = getMapOfDFs(listDéfauts);
    				
   					 	if(result != null) {
   					 	
   					 		Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

    				
   					 		while (it.hasNext()) {

    			    	
   					 			Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
    			        
   					 			Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
    			        
   					 		}
    					
   					 	}
   					 ListSeries.add(Series);
   					 	
    				}
   					
    				BarChart.getData().addAll(ListSeries);
				}
				
			}break;                        	
			case 'p' :	{		// Statistics par projet.
		
				
				if(isTime || isSpec) {
					if(isTime && isSpec) {	
						

						Projet p = Projets.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);
				
						switch(typeTime) {
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour le Projet : " + p.getLabelProjet() + " en : " + year);
		    				
		    				List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(p.getId(), year, 0, 0);

		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				if(result != null) {	    				
		    				
		    				
		    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    	while (it.hasNext()) {

		    			    	
		    			    		Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    	}
		    			    
		    				}
		    				
		    				BarChart.getData().add(Series);
		    				
				
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour le Projet : " + p.getLabelProjet() + " en : " + month + "/" + year);
		    				
		    				List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(p.getId(), year, month, 0);

		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				if(result != null) {
		    				
		    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    	while (it.hasNext()) {

		    			    	
		    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    	}
		    				}
		    				BarChart.getData().add(Series);
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour le Projet : " + p.getLabelProjet() + " en : " + day + "/" + month + "/" + year);
		    				
		    				List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(p.getId(), year, month, day);

		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);

		    				if(result != null) {
		    				
		    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    	while (it.hasNext()) {

		    			    	
		    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    	}
		    			    
		    				}
		    				
		    				BarChart.getData().add(Series);
						

						}break;
			
									
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('c', false, false, 'x');break;
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Long> Series;

		    				
		    				for(Projet p : Projets) {
		    					
		    					Series = new XYChart.Series<>();	   
		    					
		    					Series.setName("Défauts pour le Projet " + p.getLabelProjet() + "en :" + year);
		    					

		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(p.getId(), year, 0, 0);

			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					
		    					if(result != null) {
		    					
		    					
		    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    	while (it.hasNext()) {

			    			    	
			    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    	}
		    					}	
		    					
		    					BarChart.getData().add(Series);
		    				}
		    				
		    				
							
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Long> Series;

		    				
		    				for(Projet p : Projets) {
		    					
		    					Series = new XYChart.Series<>();	   
		    					
		    					Series.setName("Défauts pour le Projet " + p.getLabelProjet() + "en :" + month + "/" + year);
		    					

		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(p.getId(), year, month, 0);

		    					
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					
		    					if(result != null) {
		    					
		    					
		    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    	while (it.hasNext()) {

			    			    	
			    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    	}
		    					}	
		    					
		    					BarChart.getData().add(Series);
		    				}
		    				
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Long> Series;
		    				
		    				
		    				
		    				for(Projet p : Projets) {
		    					
		    					Series = new XYChart.Series<>();
		    					   
		    					
		    					Series.setName("Défauts pout le Projet " + p.getLabelProjet() + "en :" + day + "/" + month + "/" + year);

		    					List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(p.getId(), year, month, day);
		    					
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					
		    					if(result != null) {
		    					
		    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
		    						while (it.hasNext()) {

			    			    	
		    							Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
		    							Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
		    						}
		    						
		    					}
		    					
		    					
		    					BarChart.getData().add(Series);
		    				}
			
						
						}break;
						
						
						
						
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('p', false, false, 'a');break;
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Long> Series;

		    				
		    				for(Projet p : Projets) {
		    					
		    					Series = new XYChart.Series<>();
		    					   
		    					
		    					Series.setName("Défauts pour le Projet " + "en :" + year);
		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(p.getId(), year, 0, 0);
		    					
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					if(result != null) {
		    					
			    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    	while (it.hasNext()) {

			    			    	
			    			    		Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			    		Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    	}
		    					}
		    						
		    					
		    					BarChart.getData().add(Series);
		    				}
		    				
		    				
							
						}break;
						
						case 'm':{
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Long> Series;
		    							
		    				
		    				for(Projet p : Projets) {
		    					
		    					Series = new XYChart.Series<>();

		    					Series.setName("Défauts pour le Projet " + "en :" + month + "/" + year);
		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(p.getId(), year, month, 0);
		    					
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					if(result != null) {
		    					
		    					
		    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    	while (it.hasNext()) {

			    			    	
			    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    	}
		    					}
		    					
		    					BarChart.getData().add(Series);
		    				}
		    				
		    				
		    				
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Long> Series;
		    				
		    				
		    				
		    				for(Projet p : Projets) {
		    					
		    					Series = new XYChart.Series<>();

		    					Series.setName("Défauts pour le Projet " + "en :" + day + "/" + month + "/" + year);
		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(p.getId(), year, month, day);

		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					
		    					if(result != null) {
		    					
		    					
		    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    	while (it.hasNext()) {

			    			    	
			    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    	}
		    					}	
		    					
		    					BarChart.getData().add(Series);
		    				}
						
						}break;
					
					}
					else if(!isTime && isSpec) {

						
						Projet p = Projets.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);
						
				
						XYChart.Series<String, Long> Series = new XYChart.Series<>();
	    				
	    				Series.setName("Défauts pour le projet : " + p.getLabelProjet());
	    				
	    				
	    				List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(p.getId(), 0, 0, 0);
	    				
	    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
	    				
	    				
	    				if(result != null) {
	    				
	    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

	    				
	    					while (it.hasNext()) {

	    			    	
	    						Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
	    			        
	    						Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
	    			        
	    					} 
	    				}
		
	    				BarChart.getData().add(Series);
						
					}
				
					
				} else {
				
								
    				XYChart.Series<String, Long> Series;
    				ObservableList<XYChart.Series<String, Long>> ListSeries = FXCollections.observableArrayList();

    				

    				for(Projet p : Projets) {
    					
    					Series = new XYChart.Series<>();

    					Series.setName("Défauts Pour le Projet :" + p.getLabelProjet());
    					
    					List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(p.getId(), 0, 0, 0);
	    				
    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
	    				
    					
    					if(result != null) {
    					
    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

	    				
	    			    	while (it.hasNext()) {

	    			    	
	    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
	    			        
	    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
	    			        
	    			    	}
	    			    	
	    			    	ListSeries.add(Series);
	    			    
    					}
    						
    					
    					BarChart.getData().addAll(ListSeries);
    				}
					
				}
			
				
				
			}break;	
			
			case 'f' :	{    // Statistics par famille.
				
				
				if(isTime || isSpec) {
					if(isTime && isSpec) {

						
						Famille f = Familles.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1); 

						
						switch(typeTime) {
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour la Famille : " + f.getCodeFamilleInterne() + " en : " + year);
		    				
		    				
		    				List<Integer> listDéfauts = new StatsTransaction().getFamilleDéfauts(f.getIdFamille(), year, 0, 0);
		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				if(result != null) {
		    				
		    				
		    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    	while (it.hasNext()) {

		    			    	
		    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    	}
		    			    
		    				}
		    				
		    				BarChart.getData().add(Series);
		    				
		    				
				
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour la Famille : " + f.getCodeFamilleInterne() + " en : "+ month + "/" + year);
		    				   			
		    				List<Integer> listDéfauts = new StatsTransaction().getFamilleDéfauts(f.getIdFamille(), year, month, 0);
		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				if(result != null) {		    				
		    				
		    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    	while (it.hasNext()) {

		    			    	
		    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    	}
		    			    
		    				}
		    				
		    				BarChart.getData().add(Series);
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour la Famille : " + f.getCodeFamilleInterne() + " en : " + day + "/" + month + "/" + year);
		    				
		    				List<Integer> listDéfauts = new StatsTransaction().getFamilleDéfauts(f.getIdFamille(), year, month, day);
		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				if(result != null) {
		    				
		    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    	while (it.hasNext()) {

		    			    	
		    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    	}
		    			    
		    				}
		    				
		    				BarChart.getData().add(Series);
						

						}break;
						
						
						
						
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('f', false, false, 'a');break;
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Long> Series;
		    				
	
		    				for(Famille f: Familles) {
		    					
		    					Series = new XYChart.Series<>();	   
		    					
		    					Series.setName("Défauts pour la Famille " + f.getCodeFamilleInterne() + "en :" + year);
		    					

		    					List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(f.getIdFamille(), year, 0, 0);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					
		    					if(result != null) {
		    					
		    					
		    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    	while (it.hasNext()) {

			    			    	
			    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    	}
			    			    
		    					}
		    						
		    					BarChart.getData().add(Series);
		    				}
		    					
							
						}break;
						
						case 'm':{
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Long> Series;
		    				
		    				
		    				
		    				for(Famille f: Familles) {
		    					
		    					Series = new XYChart.Series<>();	   
		    					
		    					Series.setName("Défauts pour la Famille " + f.getCodeFamilleInterne() + "en :" + month + "/" + year);
		    					

		    					List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(f.getIdFamille(), year, month, 0);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					if(result != null) {
		    					
		    					
		    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    	while (it.hasNext()) {

			    			    	
			    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    	}
			    			    
		    					}
			    			    
			    			    
			    			    BarChart.getData().add(Series);
		    					
		    				}
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();

		    				for(Famille f: Familles) {
		    					
		    					Series = new XYChart.Series<>();	   
		    					
		    					Series.setName("Défauts pour la Famille " + f.getCodeFamilleInterne() + "en :" + day + "/" + month + "/" + year);
		    					

		    					List<Integer> listDéfauts = new StatsTransaction().getProjetDéfauts(f.getIdFamille(), year, month, day);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					if(result != null) {
		    					
		    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    	while (it.hasNext()) {

			    			    	
			    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    	}
			    			    
		    					}
			    			    
		    					BarChart.getData().add(Series);
		    				}
						
						
						}break;
						
						
						
					}
					else if(!isTime && isSpec){
						
			
						Famille f = Familles.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1); 

				
						XYChart.Series<String, Long> Series = new XYChart.Series<>();
	    				
	    				Series.setName("Défauts pour la famille : " + f.getCodeFamilleInterne());

	    				List<Integer> listDéfauts = new StatsTransaction().getFamilleDéfauts(f.getIdFamille(), 0, 0, 0);
	    				
	    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
	    				
	    				if(result != null) {
	    				
	    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

	    				
	    			    	while (it.hasNext()) {

	    			    	
	    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
	    			        
	    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
	    			        
	    			    	}
	    				}
	    				
	    				BarChart.getData().add(Series);
						
					}
				
					
				} else {
				
								
    				XYChart.Series<String, Long> Series = new XYChart.Series<>();
    				ObservableList<XYChart.Series<String, Long>> ListSeries = FXCollections.observableArrayList();

		
    				for(Famille f: Familles) {
    					
    					Series = new XYChart.Series<>();	
    					
    					Series.setName("Défauts pour la Famille" + f.getCodeFamilleInterne());    					

    					List<Integer> listDéfauts = new StatsTransaction().getFamilleDéfauts(f.getIdFamille(), 0, 0, 0);
	    				
    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
	    				
    					if(result != null) {
    						
    					
    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

	    				
	    			    	while (it.hasNext()) {

	    			    	
	    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
	    			        
	    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
	    			        
	    			    	}
	    			    	
	    			    	
	    			    	
    					}
    						
    					ListSeries.add(Series);
    				}
    				
    				BarChart.getData().addAll(ListSeries);
					
				}
			
				
				
			}break;
			
			case 'z' :		{					// Statistics par Zone.

				
				if(isTime || isSpec) {
					if(isTime && isSpec) {

						Zone z = zonesList.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);
					
						switch(typeTime) {
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour la Zone : " + z.getLabelZone() + " en : " + year);
		    					    				
		    				List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), year, 0, 0);
		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				
		    				if(result != null) {
		    				
		    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();
		    				
		    				
		    			    	while (it.hasNext()) {

		    			    	
		    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    	}
		    				}

		    				BarChart.getData().add(Series);
		    				
				
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour la Zone : " + z.getLabelZone() + " en : "+ month + "/" + year);
		    				
		    				List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), year, month, 0);
		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				
		    				if(result != null) {
		    				
		    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    	while (it.hasNext()) {

		    			    	
		    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    	}

		    				}
		    			    
		    				BarChart.getData().add(Series);
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour la Zone : " + z.getLabelZone() + " en : " + day + "/" + month + "/" + year);
		    				
		    				List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), year, month, day);
		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				if(result != null) {
		    				
		    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    	while (it.hasNext()) {

		    			    	
		    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    	}
		    				}
		    			    	
		    				BarChart.getData().add(Series);
							
						

						}break;
		
						
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('z', false, false, 'a');break;
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Long> Series;
		    				
		    				
		    				
		    				for(Zone z: zonesList) {
		    					
		    					Series = new XYChart.Series<>();	   
		    					
		    					Series.setName("Défauts pour la Zone " + z.getLabelZone() + "en :" + year);		    					

		    					List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), year, 0, 0);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					
		    					if(result != null) {
		    					
		    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    	while (it.hasNext()) {

			    			    	
			    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    	}
		    					}
		    					
		    					
		    					BarChart.getData().add(Series);
		    				}
							
						}break;
						
						case 'm':{
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Long> Series;
		    				
		    				
		    				
		    				for(Zone z: zonesList) {
		    					
		    					Series = new XYChart.Series<>();	   
		    					
		    					Series.setName("Défauts pour la Zone " + z.getLabelZone() + "en :" + month + "/" + year);
		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), year, month, 0);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					if(result != null) {
		    					
		    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    	while (it.hasNext()) {

			    			    	
			    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    	}
		    					}
		    						
		    					BarChart.getData().add(Series);
		    				}
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Long> Series;
		    				
		    				
		    				for(Zone z: zonesList) {
		    					
		    					Series = new XYChart.Series<>();	   
		    					
			    				Series.setName("Défauts pour la Zone " + z.getLabelZone() + "en :" + day + "/" + month + "/" + year);
		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), year, month, day);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
		    					if(result != null) {
		    					
		    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    	while (it.hasNext()) {
			    			    	
			    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    	}
		    					}	
		    					BarChart.getData().add(Series);
		    				}
						
						
						
						}break;
						
						
						
					}
					else if(!isTime && isSpec) {
												
							Zone z = zonesList.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);

									
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
	    				
	    					Series.setName("Défauts pour la zone : " + z.getLabelZone());
	    					
	    					List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), 0, 0, 0);
		    				
	    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				if(result != null) {
		    				
		    					while (it.hasNext()) {

		    			    	
		    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    	}
		    				}
	    						
	    					BarChart.getData().add(Series);

						
					}
				
					
				} else {
				
								
    				XYChart.Series<String, Long> Series = null;
    				ObservableList<XYChart.Series<String, Long>> ListSeries = FXCollections.observableArrayList();
    				
    				for(Zone z: zonesList) {
    					
    					Series = new XYChart.Series<>();	   
    					
    					Series.setName("Défaut pour la Zone : " + z.getLabelZone() );
    					
    					List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), 0, 0, 0);
    					
    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
	    				
    					if(result != null) {
    						
    						Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();
	    				
	    				
	    			    	while (it.hasNext()) {

	    			    	
	    			    		Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
	    			        
	    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
	    			        
	    			    	}
    					}
    					
    					ListSeries.add(Series);
	    			    
    				}
    					
    					
    						
    				BarChart.getData().addAll(ListSeries);
    				
    				}
				
				
			}break;
			
			case 'l': {
				
				if(isTime || isSpec) {
					if(isTime && isSpec) {
						
							Ligne l = lignesList.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);
					
							switch(typeTime) {
							case 'y':	{
							
								int year = yearPicker.getValue().getYear();
							
								XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
								Series.setName("Défauts pour la Ligne : " + l.getLabelLigne() + " en : " + year);
		    				
								List<Integer> listDéfauts = new StatsTransaction().getLigneDéfauts(l.getIdLigne(), year, 0, 0);
			    				
								Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
			    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    while (it.hasNext()) {

			    			    	
			    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    }
		    						
		    					BarChart.getData().add(Series);
							
							
							}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour la Ligne : " + l.getLabelLigne() + " en : "+ month + "/" + year);
		    				
		    				List<Integer> listDéfauts = new StatsTransaction().getLigneDéfauts(l.getIdLigne(), year, month, 0);
		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    while (it.hasNext()) {

		    			    	
		    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    }
	    						
	    					BarChart.getData().add(Series);
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Long> Series = new XYChart.Series<>();
		    				
		    				Series.setName("Défauts pour la Ligne : " + l.getLabelLigne() + " en : " + day + "/" + month + "/" + year);
		    				
		    				List<Integer> listDéfauts = new StatsTransaction().getLigneDéfauts(l.getIdLigne(), year, month, day);
		    				
		    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
		    				
		    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

		    				
		    			    while (it.hasNext()) {

		    			    	
		    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
		    			        
		    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
		    			        
		    			    }
	    						
	    					BarChart.getData().add(Series);
						

						}break;
						
						
						

						
						
						
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('l', false, false, 'a');break;
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Long> Series;
		    				
		    				
		    				
		    				for(Ligne l: lignesList) {
		    					
		    					Series = new XYChart.Series<>();	   
		    					
		    					Series.setName("Défauts pour la Ligne " + l.getLabelLigne() + "en :" + year);
		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getLigneDéfauts(l.getIdLigne(), year, 0, 0);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
			    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    while (it.hasNext()) {

			    			    	
			    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    }
		    						
		    					BarChart.getData().add(Series);
		    				}
							
						}break;
						
						case 'm':{
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Long> Series;
		    				
		  		
		    				for(Ligne l: lignesList) {
		    					
		    					Series = new XYChart.Series<>();	   
		    					
		    					Series.setName("Défauts pour la Ligne " + l.getLabelLigne() + "en :" + month + "/" + year);
		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getLigneDéfauts(l.getIdLigne(), year, month, 0);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
			    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    while (it.hasNext()) {

			    			    	
			    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    }
		    						
		    					BarChart.getData().add(Series);
		    				}
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Long> Series;
		    						    				
		    				for(Ligne l: lignesList) {
		    					
		    					Series = new XYChart.Series<>();	   
		    					
			    				Series.setName("Défauts pour la Ligne " + l.getLabelLigne() + "en :" + day + "/" + month + "/" + year);
		    					
		    					List<Integer> listDéfauts = new StatsTransaction().getLigneDéfauts(l.getIdLigne(), year, month, day);
			    				
		    					Map<Integer, Long> result = getMapOfDFs(listDéfauts);
			    				
			    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

			    				
			    			    while (it.hasNext()) {

			    			    	
			    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
			    			        
			    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
			    			        
			    			    }
		    						
		    					BarChart.getData().add(Series);
		    				}
						
						
						
						}break;
						
						
						
					}
					else if(!isTime && isSpec) {
						
						Ligne l = lignesList.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);

									
						XYChart.Series<String, Long> Series = new XYChart.Series<>();
	    				
	    				Series.setName("Défauts pour la ligne : " + l.getLabelLigne());
	    				
	    				
	    				List<Integer> listDéfauts = new StatsTransaction().getLigneDéfauts(l.getIdLigne(), 0, 0, 0);
	    				
	    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
	    				
	    				Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

	    				
	    			    while (it.hasNext()) {

	    			    	
	    			        Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
	    			        
	    			        Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
	    			        
	    			    }
    						
    					BarChart.getData().add(Series);
	    				
						
					}
				
					
				} else {
				
								
    				XYChart.Series<String, Long> Series;
    				ObservableList<XYChart.Series<String, Long>> ListSeries = FXCollections.observableArrayList();

    				
    				for(Ligne l: lignesList) {
    					
    					Series = new XYChart.Series<>();	   
    					
    					Series.setName("Défauts pour la Ligne :" + l.getLabelLigne());    
    					
    					
    					List<Integer> listDéfauts = new StatsTransaction().getLigneDéfauts(l.getIdLigne(), 0, 0, 0);

    					if(listDéfauts.size() != 0) {
    						
	    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
	    				
	    				
	    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();
	    				
	    				
	    					while (it.hasNext()) {
    			    	
	    						Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
	    			        	    			        
	    						Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
	    			        
	    					}
	    					
	    					ListSeries.add(Series);
    					}
   				
    					BarChart.getData().addAll(ListSeries);
    				}
				
					
				}
		
			}break;
		
		}
		
		
		rootStackPane.getChildren().remove(1);
		anchorPane.setDisable(false);

    }
    
    
    private Map<Integer, Long> getMapOfDFs(List<Integer> list) throws SQLException{
    	
    	if(list.size() != 0) {
    		
    		Map<Integer, Long> result = new HashMap<Integer, Long>();
    		
    		Map<Integer, Long> frequencyMap = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

    		for(Map.Entry<Integer, Long> entry: frequencyMap.entrySet()) {  

    			result.put(entry.getKey(), entry.getValue());
    		  		
    		}
  	
    		return result;
    	}
    	
    	return null;
    	
    	
    }

	

}
