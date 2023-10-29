package MenuControllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import Models.Client;
import Models.Famille;
import Models.Ligne;
import Models.Projet;
import Models.Zone;
import Transactions.ClientTransaction;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tornadofx.control.DateTimePicker;



public class IPPM_StatsController implements Initializable {
		
	
	public ObservableList<Client> clients = FXCollections.observableArrayList();
	ObservableList<Zone> zonesList = FXCollections.observableArrayList();
	ObservableList<Projet> Projets = FXCollections.observableArrayList();
	ObservableList<Famille> Familles = FXCollections.observableArrayList();
	ObservableList<Ligne> lignesList = FXCollections.observableArrayList();
	
	
	
	
	@FXML
    private BarChart<String, Integer> BarChart;
	
	@FXML
    private JFXCheckBox ClientCheckBox;

    @FXML
    private JFXComboBox<String> ClientComboBox;

    @FXML
    private DateTimePicker  dayPeaker;

    @FXML
    private DateTimePicker  monthPeaker;

    @FXML
    private DateTimePicker  yearPicker;

    @FXML
    private JFXCheckBox FamilleCheckBox;

    @FXML
    private CategoryAxis LabelX;

    @FXML
    private NumberAxis LabelY;

    @FXML
    private JFXCheckBox LigneCheckBox;

    @FXML
    private JFXCheckBox ProjetCheckBox;

    @FXML
    private JFXComboBox<String> ProjetComboBox;

    @FXML
    private JFXComboBox<String> SpécifiqueFilter;

    @FXML
    private JFXComboBox<String> TimeComboBox;
   
    @FXML
    private Label TimeLabel;
    
    @FXML
    private JFXComboBox<String> ZoneComboBox;
    
    @FXML
    private JFXCheckBox ZoneCheckBox;
    
    @FXML
    private Button BtnSearch;




    @FXML
    void ProjetComboBoxClicked(MouseEvent event) throws SQLException {
    	
    	SpécifiqueFilter.getItems().clear(); 
    	
    	if(!ClientComboBox.getValue().isEmpty()) {
    		
    		ProjetComboBox.getItems().clear();
    		
    		int id_client = clients.get(ClientComboBox.getSelectionModel().getSelectedIndex()).getId();
    		
    		getProjetsByClient(id_client, true);	
    	}	
    }

    @FXML
    void SpécifiqueFilterClicked(MouseEvent event) throws SQLException {
    	
 	
    	SpécifiqueFilter.getItems().clear();
  
    	SpécifiqueFilter.getItems().add("All");
    	
    	if(ClientCheckBox.selectedProperty().get()) { 	
    	
    		getAllClients(false);    		   		
    		
    	} else if(ProjetCheckBox.selectedProperty().get() && !ClientComboBox.getValue().isEmpty()) {

    		int id_client = clients.get(ClientComboBox.getSelectionModel().getSelectedIndex()).getId();
    		
    		getProjetsByClient(id_client, false);
    		
    		
    	} else if(FamilleCheckBox.selectedProperty().get() && !ClientComboBox.getValue().isEmpty() && !ProjetComboBox.getValue().isEmpty()) {
    		
    		int id_projet = Projets.get(ProjetComboBox.getSelectionModel().getSelectedIndex()).getId();
    		
    		getFamillesByProjet(id_projet);
    		
    		
    	} else if(LigneCheckBox.selectedProperty().get() && !ZoneComboBox.getValue().isEmpty()) {
    		
    		
    		int id_zone = zonesList.get(ZoneComboBox.getSelectionModel().getSelectedIndex()).getIdZone();
    		
    		lignesList = new LigneTransaction().getAllByZone(id_zone); 
    		
            for(int i = 0; i < lignesList.size(); i++) {     	
            	SpécifiqueFilter.getItems().add(lignesList.get(i).getLabelLigne());    	
            }		
    		
    	} else if(ZoneCheckBox.selectedProperty().get()) {
    		  		        
            for(int i = 0; i < zonesList.size(); i++) {     	
            	SpécifiqueFilter.getItems().add(zonesList.get(i).getLabelZone());    	
            }		   		
    	}
    }
    
    
    @FXML
    void TimeComboBoxClicked(ActionEvent event) {
    	
    	dayPeaker.setVisible(false);
    	monthPeaker.setVisible(false);
    	yearPicker.setVisible(false);
    	
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
    	setAllInvisible();
    	
    	BtnSearch.setVisible(true);
    	switch(idBtnClicked) {
    	case "ClientCheckBox":{ 
    		ResetAllCheckBoxes();
    		ClientCheckBox.selectedProperty().set(true);	
    		TimeComboBox.setVisible(true);
    		TimeComboBox.setValue("All");
    		TimeLabel.setVisible(true);
    		SpécifiqueFilter.setVisible(true);  
    		showStatistiques('c', false, false, 'a');
    		BarChart.setVisible(true);
    		break;
    	}
    	case "ProjetCheckBox":{
    		ResetAllCheckBoxes();
    		ProjetCheckBox.selectedProperty().set(true);
    		ClientComboBox.setVisible(true);
    		TimeComboBox.setVisible(true);
    		TimeComboBox.setValue("All");
    		TimeLabel.setVisible(true);
    		SpécifiqueFilter.setVisible(true);   
    		showStatistiques('p', false, false, 'a');
    		BarChart.setVisible(true);
    		break;
    	}
    	case "FamilleCheckBox":{
    		ResetAllCheckBoxes();
    		FamilleCheckBox.selectedProperty().set(true);	
    		ClientComboBox.setVisible(true);
    		ProjetComboBox.setVisible(true);
    		TimeComboBox.setVisible(true);
    		TimeComboBox.setValue("All");
    		TimeLabel.setVisible(true);
    		SpécifiqueFilter.setVisible(true); 
    		showStatistiques('f', false, false, 'a');
    		BarChart.setVisible(true);
    		break;
    	}
    	case "LigneCheckBox":{
    		ResetAllCheckBoxes();
    		LigneCheckBox.selectedProperty().set(true);	
    		ZoneComboBox.setVisible(true);
    		TimeComboBox.setVisible(true);
    		TimeComboBox.setValue("All");
    		TimeLabel.setVisible(true);   		 
    		SpécifiqueFilter.setVisible(true);
    		showStatistiques('l', false, false, 'a');
    		BarChart.setVisible(true); 
    		break; 	
    	}
    	
    	case "ZoneCheckBox":
    	{
		ResetAllCheckBoxes();
		ZoneCheckBox.selectedProperty().set(true);	
		TimeComboBox.setVisible(true);
		TimeComboBox.setValue("All");
		TimeLabel.setVisible(true);
		SpécifiqueFilter.setVisible(true);
		showStatistiques('z', false, false, 'a');
		BarChart.setVisible(true);  
		break; 	
		}
    	
    }
    	
    }
    
    public void setAllInvisible(){
    	ClientComboBox.setVisible(false);
    	ProjetComboBox.setVisible(false);
    	ZoneComboBox.setVisible(false);
    	BarChart.getData().clear();
    	TimeComboBox.setVisible(false);
    	TimeLabel.setVisible(false);
    	SpécifiqueFilter.getItems().clear();
    	SpécifiqueFilter.setVisible(false); 	
    	dayPeaker.setVisible(false);
    	monthPeaker.setVisible(false);
    	yearPicker.setVisible(false);
    	
    	
    }
    
    
    public void ResetAllCheckBoxes() {
    	ClientCheckBox.selectedProperty().set(false);
    	ProjetCheckBox.selectedProperty().set(false);
    	FamilleCheckBox.selectedProperty().set(false);
    	LigneCheckBox.selectedProperty().set(false);
    	ZoneCheckBox.selectedProperty().set(false);
    	
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

 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		setAllInvisible();
		
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
		
		
		
		
	
		
		
	}
	

    @FXML
    void BtnSearchClicked(ActionEvent event) throws SQLException {
    	
    	if(ClientCheckBox.selectedProperty().get()) {
    		
    		switch(TimeComboBox.getValue()) {
    		case "All":{
    			if(SpécifiqueFilter.getValue().toString().equals("All")) {
    				
    				showStatistiques('c', false, false, 'a');
    				
    			} else {
    				
    				showStatistiques('c', false, true, 'a');
    				
    			}
    			
    			
    		} break;
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

    		
    	}else if(ProjetCheckBox.selectedProperty().get()) {
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
    		
    	}else if(FamilleCheckBox.selectedProperty().get()) {
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
    		
    	}else if(ZoneCheckBox.selectedProperty().get()) {
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
    		
    	}else if(LigneCheckBox.selectedProperty().get()) {
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
		switch(ch) {
		
			case 'c' : {

				if(isTime || isSpec) {
					if(isTime && isSpec) {	
						
						Client client = clients.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);

						
						switch(typeTime) {
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour le client : " + client.getLabelClient() + " en : " + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getClientIppm(client.getId(), year, 0, 0);
		    				
		    				Series.getData().add(new XYChart.Data<>(client.getLabelClient(), ippm));
		    				
		    				BarChart.getData().add(Series);
		    				
				
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour le client : " + client.getLabelClient() + " en : "+ month + "/" + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getClientIppm(client.getId(), year, month, 0);
		    				
		    				Series.getData().add(new XYChart.Data<>(client.getLabelClient(), ippm));
		    				
		    				BarChart.getData().add(Series);
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour le client : " + client.getLabelClient() + " en : " + day + "/" + month + "/" + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getClientIppm(client.getId(), year, month, day);
		    				
		    				Series.getData().add(new XYChart.Data<>(client.getLabelClient(), ippm));
		    				
		    				BarChart.getData().add(Series);
						

						}break;
			
									
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('c', false, false, 'x');break;
						case 'y':	{
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Client " + "en :" + year);
		    				
		    				int ippm;
		    				
		    				for(Client c : clients) {
		    					ippm = new StatsTransaction().getClientIppm(c.getId(), year, 0, 0);
		    					
		    					Series.getData().add(new XYChart.Data<>(c.getLabelClient(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						
						case 'm':{
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Client " + "en :" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Client c : clients) {
		    					ippm = new StatsTransaction().getClientIppm(c.getId(), year, month, 0);
		    					
		    					Series.getData().add(new XYChart.Data<>(c.getLabelClient(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Client " + "en :" + day + "/" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Client c : clients) {
		    					ippm = new StatsTransaction().getClientIppm(c.getId(), year, month, day);
		    					Series.getData().add(new XYChart.Data<>(c.getLabelClient(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
						
						
						
						}break;
						
						
						
					}
					else if(!isTime && isSpec){
						
						
						Client client = clients.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);
						
				
						XYChart.Series<String, Integer> Series = new XYChart.Series<>();
	    				
	    				Series.setName("IPPM pour le client : " + client.getLabelClient());
	    				
	    				int ippm;
	    				
	    					    				
	    				ippm = new StatsTransaction().getClientIppm(client.getId(), 0, 0, 0);
	    				
	    				
	    				Series.getData().add(new XYChart.Data<>(client.getLabelClient(), ippm));
	    				
	    				
	    				BarChart.getData().add(Series);
						
					}
				
					
				} else {
				
								
    				XYChart.Series<String, Integer> Series = new XYChart.Series<>();
    				
    				Series.setName("IPPM par Client");
    				
    				int ippm;
    				
    				for(Client c : clients) {
    					ippm = new StatsTransaction().getClientIppm(c.getId(), 0, 0, 0);   					
    					Series.getData().add(new XYChart.Data<>(c.getLabelClient(), ippm));
    				}
    				
    				BarChart.getData().add(Series);
					
				}
				
				
			}break;                        	
			case 'p' :	{		// Statistics par projet.
		
				
				if(isTime || isSpec) {
					if(isTime && isSpec) {	

						
						Projet projet = Projets.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);

						switch(typeTime) {
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour le Projet : " + projet.getLabelProjet() + " en : " + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getProjetIppm(projet.getId(), year, 0, 0);
		    				
		    				Series.getData().add(new XYChart.Data<>(projet.getLabelProjet(), ippm));
		    				
		    				BarChart.getData().add(Series);
		    				
				
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour le Projet : " + projet.getLabelProjet() + " en : "+ month + "/" + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getProjetIppm(projet.getId(), year, month, 0);
		    				
		    				Series.getData().add(new XYChart.Data<>(projet.getLabelProjet(), ippm));
		    				
		    				BarChart.getData().add(Series);
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour le Projet : " + projet.getLabelProjet() + " en : " + day + "/" + month + "/" + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getProjetIppm(projet.getId(), year, month, day);
		    				
		    				Series.getData().add(new XYChart.Data<>(projet.getLabelProjet(), ippm));
		    				
		    				BarChart.getData().add(Series);
						

						}break;
			
									
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('c', false, false, 'x');break;
						case 'y':	{
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Client " + "en :" + year);
		    				
		    				int ippm;
		    				
		    				for(Client c : clients) {
		    					ippm = new StatsTransaction().getClientIppm(c.getId(), year, 0, 0);
		    					Series.getData().add(new XYChart.Data<>(c.getLabelClient(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						
						case 'm':{
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Client " + "en :" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Client c : clients) {
		    					ippm = new StatsTransaction().getClientIppm(c.getId(), year, month, 0);
		    					Series.getData().add(new XYChart.Data<>(c.getLabelClient(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Client " + "en :" + day + "/" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Client c : clients) {
		    					ippm = new StatsTransaction().getClientIppm(c.getId(), year, month, day);
		    					Series.getData().add(new XYChart.Data<>(c.getLabelClient(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
						
						
						
						}break;
						
						
						
						
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('p', false, false, 'a');break;
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Projet " + "en :" + year);
		    				
		    				int ippm;
		    				
		    				for(Projet p : Projets) {
		    					ippm = new StatsTransaction().getProjetIppm(p.getId(), year, 0, 0);		    					
		    					Series.getData().add(new XYChart.Data<>(p.getLabelProjet(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						
						case 'm':{
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Projet " + "en :" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Projet p : Projets) {
		    					ippm = new StatsTransaction().getProjetIppm(p.getId(), year, month, 0);
		    					Series.getData().add(new XYChart.Data<>(p.getLabelProjet(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Projet " + "en :" + day + "/" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Projet p : Projets) {
		    					ippm = new StatsTransaction().getClientIppm(p.getId(), year, month, day);
		    					Series.getData().add(new XYChart.Data<>(p.getLabelProjet(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
						
						
						
						}break;
						
						
						
					}
					else if(!isTime && isSpec) {
						
						Projet projet = Projets.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);
						
				
						XYChart.Series<String, Integer> Series = new XYChart.Series<>();
	    				
	    				Series.setName("IPPM pour le projet : " + projet.getLabelProjet());
	    				
	    				int ippm;
	    				
	    					    				
	    				ippm = new StatsTransaction().getProjetIppm(projet.getId(), 0, 0, 0);
	    				
	    				
	    				Series.getData().add(new XYChart.Data<>(projet.getLabelProjet(), ippm));
	    				
	    				
	    				BarChart.getData().add(Series);
						
					}
				
					
				} else {
				
								
    				XYChart.Series<String, Integer> Series = new XYChart.Series<>();
    				
    				Series.setName("IPPM par Projet");
    				
    				int ippm;
    				
    				for(Projet p: Projets) {
    					ippm = new StatsTransaction().getProjetIppm(p.getId(), 0, 0, 0);
    					Series.getData().add(new XYChart.Data<>(p.getLabelProjet(), ippm));
    				}
    				
    				BarChart.getData().add(Series);
					
				}
			
				
				
			}break;						
			case 'f' :	{    // Statistics par famille.
				
				
				if(isTime || isSpec) {
					if(isTime && isSpec) {
						
						Famille famille = Familles.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);
						
						switch(typeTime) {
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour la Famille : " + famille.getCodeFamilleInterne() + " en : " + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getFamilleIppm(famille.getIdFamille(), year, 0, 0);
		    				
		    				Series.getData().add(new XYChart.Data<>(famille.getCodeFamilleInterne(), ippm));
		    				
		    				BarChart.getData().add(Series);
		    				
				
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour le Projet : " + famille.getCodeFamilleInterne() + " en : "+ month + "/" + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getFamilleIppm(famille.getIdFamille(), year, month, 0);
		    				
		    				Series.getData().add(new XYChart.Data<>(famille.getCodeFamilleInterne(), ippm));
		    				
		    				BarChart.getData().add(Series);
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour la Famille : " + famille.getCodeFamilleInterne() + " en : " + day + "/" + month + "/" + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getFamilleIppm(famille.getIdFamille(), year, month, day);
		    				
		    				Series.getData().add(new XYChart.Data<>(famille.getCodeFamilleInterne(), ippm));
		    				
		    				BarChart.getData().add(Series);
						

						}break;
						
						
						
						
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('f', false, false, 'a');break;
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Famille " + "en :" + year);
		    				
		    				int ippm;
		    				
		    				for(Famille f: Familles) {
		    					ippm = new StatsTransaction().getFamilleIppm(f.getIdFamille(), year, 0, 0);		    					
		    					Series.getData().add(new XYChart.Data<>(f.getCodeFamilleInterne(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						
						case 'm':{
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Famille " + "en :" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Famille f: Familles) {
		    					ippm = new StatsTransaction().getFamilleIppm(f.getIdFamille(), year, month, 0);
		    					Series.getData().add(new XYChart.Data<>(f.getCodeFamilleInterne(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Famille " + "en :" + day + "/" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Famille f: Familles) {
		    					ippm = new StatsTransaction().getFamilleIppm(f.getIdFamille(), year, month, day);
		    					Series.getData().add(new XYChart.Data<>(f.getCodeFamilleInterne(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
						
						
						
						}break;
						
						
						
					}
					else if(!isTime && isSpec){
						
						Famille famille = Familles.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);

				
						XYChart.Series<String, Integer> Series = new XYChart.Series<>();
	    				
	    				Series.setName("IPPM pour la famille : " + famille.getCodeFamilleInterne());
	    				
	    				int ippm;
	    				
	    					    				
	    				ippm = new StatsTransaction().getFamilleIppm(famille.getIdFamille(), 0, 0, 0);
	    				
	    				
	    				Series.getData().add(new XYChart.Data<>(famille.getCodeFamilleInterne(), ippm));
	    				
	    				
	    				BarChart.getData().add(Series);
						
					}
				
					
				} else {
				
								
    				XYChart.Series<String, Integer> Series = new XYChart.Series<>();
    				
    				Series.setName("IPPM par Famille");
    				
    				int ippm;
    				
    				for(Famille f: Familles) {
    					ippm = new StatsTransaction().getFamilleIppm(f.getIdFamille(), 0, 0, 0);
    					Series.getData().add(new XYChart.Data<>(f.getCodeFamilleInterne(), ippm));
    				}
    				
    				BarChart.getData().add(Series);
					
				}
			
				
				
			}break;
			
			case 'z' :		{																		// Statistics par Zone.

				
				if(isTime || isSpec) {
					if(isTime && isSpec) {
											
						Zone zone = zonesList.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);

						switch(typeTime) {
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour la Zone : " + zone.getLabelZone() + " en : " + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getZoneIppm(zone.getIdZone(), year, 0, 0);
		    				
		    				Series.getData().add(new XYChart.Data<>(zone.getLabelZone(), ippm));
		    				
		    				BarChart.getData().add(Series);
		    				
				
						}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour la Zone : " + zone.getLabelZone() + " en : "+ month + "/" + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getZoneIppm(zone.getIdZone(), year, month, 0);
		    				
		    				Series.getData().add(new XYChart.Data<>(zone.getLabelZone(), ippm));
		    				
		    				BarChart.getData().add(Series);
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour la Zone : " + zone.getLabelZone() + " en : " + day + "/" + month + "/" + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getZoneIppm(zone.getIdZone(), year, month, day);
		    				
		    				Series.getData().add(new XYChart.Data<>(zone.getLabelZone(), ippm));
		    				
		    				BarChart.getData().add(Series);
						

						}break;
		
						
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('z', false, false, 'a');break;
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Zone " + "en :" + year);
		    				
		    				int ippm;
		    				
		    				for(Zone z : zonesList) {
		    					ippm = new StatsTransaction().getZoneIppm(z.getIdZone(), year, 0, 0);		    					
		    					Series.getData().add(new XYChart.Data<>(z.getLabelZone(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						
						case 'm':{
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Zone " + "en :" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Zone z : zonesList) {
		    					ippm = new StatsTransaction().getZoneIppm(z.getIdZone(), year, month, 0);		    					
		    					Series.getData().add(new XYChart.Data<>(z.getLabelZone(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Zone " + "en :" + day + "/" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Zone z : zonesList) {
		    					
		    					ippm = new StatsTransaction().getZoneIppm(z.getIdZone(), year, month, day);		    					
		    					Series.getData().add(new XYChart.Data<>(z.getLabelZone(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
						
						
						
						}break;
						
						
						
					}
					else if(!isTime && isSpec) {
						
							Zone zone = zonesList.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);
							

							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
	    				
	    					Series.setName("IPPM pour la zone : " + zone.getLabelZone());
	    				
	    					int ippm;
	    				
	    					    				
	    					ippm = new StatsTransaction().getZoneIppm(zone.getIdZone(), 0, 0, 0);
	    				
	    				
	    					Series.getData().add(new XYChart.Data<>(zone.getLabelZone(), ippm));
	    				
	    				
	    					BarChart.getData().add(Series);
						
						
					}
				
					
				} else {
				
								
    				XYChart.Series<String, Integer> Series = new XYChart.Series<>();
    				
    				Series.setName("IPPM par Zone");
    				
    				int ippm = 0;
    				
    				for(Zone z : zonesList) {
    					
    					ippm = new StatsTransaction().getZoneIppm(z.getIdZone(), 0, 0, 0);		    					
    					Series.getData().add(new XYChart.Data<>(z.getLabelZone(), ippm));
    				}
    				
    				
    				BarChart.getData().add(Series);
					
				}
			}break;
			case 'l':{
				
				if(isTime || isSpec) {
					if(isTime && isSpec) {

							
							Ligne ligne =  lignesList.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);

					
							switch(typeTime) {
							case 'y':	{
							
								int year = yearPicker.getValue().getYear();
							
								XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
								Series.setName("IPPM pour la Ligne : " + ligne.getLabelLigne() + " en : " + year);
		    				
								int ippm;
    			
								ippm = new StatsTransaction().getLigneIppm(ligne.getIdLigne(), year, 0, 0);
		    				
								Series.getData().add(new XYChart.Data<>(ligne.getLabelLigne(), ippm));
		    				
								BarChart.getData().add(Series);
							
							
							}break;
						
						case 'm':{
							
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour la Ligne : " + ligne.getLabelLigne() + " en : "+ month + "/" + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getLigneIppm(ligne.getIdLigne(), year, month, 0);
		    				
		    				Series.getData().add(new XYChart.Data<>(ligne.getLabelLigne(), ippm));
		    				
		    				BarChart.getData().add(Series);
							
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM pour la Ligne : " + ligne.getLabelLigne() + " en : " + day + "/" + month + "/" + year);
		    				
		    				int ippm;
    			
		    				ippm = new StatsTransaction().getLigneIppm(ligne.getIdLigne(), year, month, day);
		    				
		    				Series.getData().add(new XYChart.Data<>(ligne.getLabelLigne(), ippm));
		    				
		    				BarChart.getData().add(Series);
						

						}break;
						
						
						

						
						
						
					}
					else if(isTime && !isSpec){
												
						
						switch(typeTime) {

						case 'a' : showStatistiques('l', false, false, 'a');break;
						case 'y':	{
							
							int year = yearPicker.getValue().getYear();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Ligne " + "en :" + year);
		    				
		    				int ippm;
		    				
		    				for(Ligne l : lignesList) {
		    					ippm = new StatsTransaction().getLigneIppm(l.getIdLigne(), year, 0, 0);		    					
		    					Series.getData().add(new XYChart.Data<>(l.getLabelLigne(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						
						case 'm':{
							int year = monthPeaker.getValue().getYear();
							int month = monthPeaker.getValue().getMonthValue();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Ligne " + "en :" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Ligne l : lignesList) {
		    					ippm = new StatsTransaction().getLigneIppm(l.getIdLigne(), year, month, 0);		    					
		    					Series.getData().add(new XYChart.Data<>(l.getLabelLigne(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
							
						}break;
						case 'd':
							
							int year = dayPeaker.getValue().getYear();
							int month = dayPeaker.getValue().getMonthValue();
							int day  = dayPeaker.getValue().getDayOfMonth();
							
							XYChart.Series<String, Integer> Series = new XYChart.Series<>();
		    				
		    				Series.setName("IPPM par Ligne " + "en :" + day + "/" + month + "/" + year);
		    				
		    				int ippm;
		    				
		    				for(Ligne l : lignesList) {
		    					ippm = new StatsTransaction().getLigneIppm(l.getIdLigne(), year, month, day);		    					
		    					Series.getData().add(new XYChart.Data<>(l.getLabelLigne(), ippm));
		    				}
		    				
		    				BarChart.getData().add(Series);
						
						
						
						}break;
						
						
						
					}
					else if(!isTime && isSpec) {
					
						Ligne ligne =  lignesList.get(SpécifiqueFilter.getSelectionModel().getSelectedIndex() - 1);

									
						XYChart.Series<String, Integer> Series = new XYChart.Series<>();
	    				
	    				Series.setName("IPPM pour la ligne : " + ligne.getLabelLigne());
	    				
	    				int ippm;
	    				
	    					    				
	    				ippm = new StatsTransaction().getLigneIppm(ligne.getIdLigne(), 0, 0, 0);

	    				
	    				Series.getData().add(new XYChart.Data<>(ligne.getLabelLigne(), ippm));
	    				
	    				
	    				BarChart.getData().add(Series);
						
					}
				
					
				} else {
				
								
    				XYChart.Series<String, Integer> Series = new XYChart.Series<>();
    				
    				Series.setName("IPPM par Ligne");
    				
    				int ippm;
    				
    				for(Ligne l : lignesList) {
    					ippm = new StatsTransaction().getLigneIppm(l.getIdLigne(), 0, 0, 0);		    					
    					Series.getData().add(new XYChart.Data<>(l.getLabelLigne(), ippm));
    				}
    				
    				BarChart.getData().add(Series);
					
				}
		
			}break;
		
		}

		
	}

	
	
	
	
	
	
}
