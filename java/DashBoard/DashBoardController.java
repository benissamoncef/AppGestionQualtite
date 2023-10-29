package DashBoard;

import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.jfoenix.controls.JFXComboBox;
import Models.Ligne;
import Models.Zone;
import Transactions.LigneTransaction;
import Transactions.StatsTransaction;
import Transactions.ZoneTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DashBoardController implements Initializable{
	
	
	
	ObservableList<Ligne> ListLignes = FXCollections.observableArrayList();
	ObservableList<Zone> ListZones = FXCollections.observableArrayList();
	
	
	
	@FXML
    private BarChart<String, Long> CHART_DEFAUTS_LIGNE;

    @FXML
    private BarChart<String, Long> CHART_DEFAUTS_ZONE;

    @FXML
    private LineChart<String, Long> CHART_IPPM_LIGNE;

    @FXML
    private LineChart<String, Long> CHART_IPPM_ZONE;

    @FXML
    private JFXComboBox<String> ComboBoxDL;

    @FXML
    private JFXComboBox<String> ComboBoxDZ;

    @FXML
    private JFXComboBox<String> ComboBoxIL;

    @FXML
    private JFXComboBox<String> ComboBoxIZ;
    
    @FXML
    private JFXComboBox<String> ZoneComboBoxDL;

    @FXML
    private JFXComboBox<String> ZoneComboBoxIL;
    

    @FXML
    private ImageView refreshDefautsLigne;

    @FXML
    private ImageView refreshDefautsZone;

    @FXML
    private ImageView refreshIPPMLigne;

    @FXML
    private ImageView refreshIPPMZone;

    @FXML
    void refreshDefautsLigneClicked(MouseEvent event) throws SQLException {
    	
    	
    	CHART_DEFAUTS_LIGNE.getData().clear();
    	
    	String T = ComboBoxDL.getValue();
    	
    	Zone z = ListZones.get(ZoneComboBoxDL.getSelectionModel().getSelectedIndex());
    	
    	ListLignes = new LigneTransaction().getAllByZone(z.getIdZone());
    	
    	ObservableList<XYChart.Series<String, Long>> ListSeries = FXCollections.observableArrayList();
    	
    	LocalDate date = LocalDate.now();

		XYChart.Series<String, Long> Series;

    	
    	
    	switch(T) {
    	
    	
    	
    		case "today":{
    			
    	
    				
			
    				for(Ligne l: ListLignes) {
    					
    					Series = new XYChart.Series<>();	   
    					
    					Series.setName("Défauts pour la Ligne :" + l.getLabelLigne());   
    					
    					List<Integer> listDéfauts = new StatsTransaction().getLigneDéfauts(l.getIdLigne(), date.getYear(), date.getMonthValue(), date.getDayOfMonth());

    					
    					
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
				
    				
    			CHART_DEFAUTS_LIGNE.getData().addAll(ListSeries);	
    				
    			
    		
    		}break;
    		
    		case "this Week":{
    			
    			
    			LocalDate d = null;

    			int cpt = 1;
    				
    			for(Ligne l: ListLignes) {
    				
    				cpt = 1;
    				
    				Series = new XYChart.Series<>();	   
					
					Series.setName("Défauts pour la Ligne :" + l.getLabelLigne()); 
    				
    			
    				while(cpt <= date.getDayOfWeek().getValue()) {		
    					  
    					
    					d = date.minusDays(date.getDayOfWeek().getValue() - cpt);
    					
    					List<Integer> listDéfauts = new StatsTransaction().getLigneDéfauts(l.getIdLigne(), d.getYear(), d.getMonthValue(), d.getDayOfMonth());
	
    					
	    				Map<Integer, Long> result = getMapOfDFs(listDéfauts);
	    				
	    				if(result != null) {
	    				
	    					Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

	    				
	    			    	while (it.hasNext()) {

	    			    	
	    			        	Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
	    			        	Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
	    			        
	    			    	}
	    				}
	    				
    					cpt ++;	
    					
    					
	    			    
    				}
    				
    			ListSeries.add(Series);
    			
    				
    		}
        		
        	CHART_DEFAUTS_LIGNE.getData().addAll(ListSeries);	
        		
    		}break;
    		
    		case "this Month":{
  			
	
    				for(Ligne l: ListLignes) {
    					
    					Series = new XYChart.Series<>();	   
    					
    					Series.setName("Défauts pour la Ligne :" + l.getLabelLigne());   
    					
    					List<Integer> listDéfauts = new StatsTransaction().getLigneDéfauts(l.getIdLigne(), date.getYear(), date.getMonthValue(), 0);
	    			
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
    				
    				
    		
        	CHART_DEFAUTS_LIGNE.getData().addAll(ListSeries);
        		
        		
        		
    		}break;
    		
    		case "this Year":{
        		
        		
    			
    			
	
    				for(Ligne l: ListLignes) {
    					
    					Series = new XYChart.Series<>();	   
    					
    					Series.setName("Défauts pour la Ligne :" + l.getLabelLigne());   
    					
    					List<Integer> listDéfauts= new StatsTransaction().getLigneDéfauts(l.getIdLigne(), date.getYear(), 0, 0);
    					
    					
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
    				
    			CHART_DEFAUTS_LIGNE.getData().addAll(ListSeries);
    		
    			
        		
    		}break;
    	
    	
    	}
    	
    	
    }

    @FXML
    void refreshDefautsZoneClicked(MouseEvent event) throws SQLException {
    	
    	

    	CHART_DEFAUTS_ZONE.getData().clear();
    	
    	LocalDate date = LocalDate.now();
    	
    	ObservableList<XYChart.Series<String, Long>> ListSeries = FXCollections.observableArrayList();


		XYChart.Series<String, Long> Series;

		String T = ComboBoxDZ.getValue();
    	
    	
    	switch(T) {
    	
    		case "today":{
    			
    	
    				
			
    				for(Zone z: ListZones) {
    					
    					Series = new XYChart.Series<>();	   
    					
    					Series.setName("Défauts pour la Zone :" + z.getLabelZone());   
    					
    					List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), date.getYear(), date.getMonthValue(), date.getDayOfMonth());

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
    				
    		CHART_DEFAUTS_ZONE.getData().addAll(ListSeries);
    		
    		}break;
    		
    		case "this Week":{
    			

    			int cpt = 0;
    				
    			LocalDate d = null;
    			
    			for(Zone z: ListZones) {
    			
    				Series = new XYChart.Series<>();	   
    					
    				Series.setName("Défauts pour la Zone :" + z.getLabelZone());  
    					
    				cpt = 1;
    				
    					while(cpt <= date.getDayOfWeek().getValue()) {  
			 
    						d = date.minusDays(date.getDayOfWeek().getValue() - cpt);
    						
    						List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), d.getYear(), d.getMonthValue(), d.getDayOfMonth());

    					
    						Map<Integer, Long> result = getMapOfDFs(listDéfauts);
	    				
    						if(result != null) {
	    				
    							Iterator<Entry<Integer, Long>> it = result.entrySet().iterator();

	    				
	    					while (it.hasNext()) {

	    			    	
	    						Map.Entry<Integer, Long> entry = (Map.Entry<Integer, Long>)it.next();
	    						Series.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));
	    			        
	    					}
	    				}
	    				cpt ++;
    				}
    				
    				ListSeries.add(Series);
    			
    		}
        		
        	CHART_DEFAUTS_ZONE.getData().addAll(ListSeries);		
        		
    		}break;
    		
    		case "this Month":{
    			

    			
    			
				for(Zone z: ListZones) {
					
					Series = new XYChart.Series<>();	   
					
					Series.setName("Défauts pour la Zone :" + z.getLabelZone());   
					
					List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), date.getYear(), date.getMonthValue(), 0);

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
				
				CHART_DEFAUTS_ZONE.getData().addAll(ListSeries);
		
		}break;
		
    		case "this Year":{
        		
        		
    			
    			
    			
				for(Zone z: ListZones) {
					
					Series = new XYChart.Series<>();	   
					
					Series.setName("Défauts pour la Zone :" + z.getLabelZone());   
					
					List<Integer> listDéfauts = new StatsTransaction().getZoneDéfauts(z.getIdZone(), date.getYear(), 0, 0);			

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
			CHART_DEFAUTS_ZONE.getData().addAll(ListSeries);
		
		}break;
	
    				
    				
    				
    	}
	
    	

    }

    @FXML
    void refreshIPPMLigneClicked(MouseEvent event) throws SQLException {
    	
    	
    	CHART_IPPM_LIGNE.getData().clear();
	
    	LocalDate date = LocalDate.now();
    	
    	ObservableList<XYChart.Series<String, Long>> ListSeries = FXCollections.observableArrayList();
    	
    	Series<String, Long> s = new Series<String, Long>();

		String T = ComboBoxIL.getValue();
		

		int id_zone = ListZones.get(ZoneComboBoxIL.getSelectionModel().getSelectedIndex()).getIdZone();
    	
		ListLignes = new LigneTransaction().getAllByZone(id_zone);
    	
    	switch(T) {
    	
  		
    		case "this Week":{
    			

    			int cpt = 1;
    			
    			long ippm = 0;
    			
    			LocalDate d = null;
    			
    			int weekNumber = date.getDayOfWeek().getValue();
    				
    			for(Ligne l: ListLignes) {
    			
    			cpt = 1;
    			ippm = 0;
    			
    			s = new Series<String, Long>();
    			
    			s.setName("IPPM pour la Ligne :" + l.getLabelLigne()); 
    				
    			while(cpt <= weekNumber) {  

    				d = date.minusDays(date.getDayOfWeek().getValue() - cpt);
	
    				ippm = (long) new StatsTransaction().getLigneIppm(l.getIdLigne(), date.getYear(), d.getMonth().getValue(), d.getDayOfMonth());
    				
    				s.getData().add(new XYChart.Data<>(DayOfWeek.of(cpt).name(), ippm));
        				
    				
        				
    				cpt ++;
    					
	    
    				}
    			
    			ListSeries.add(s);	
    				
    		}
        		
        	CHART_IPPM_LIGNE.getData().addAll(ListSeries);	
        		
    		}break;
    		
    		case "this Month":{
	
    			int cpt = 1;
    			
    			long ippm = 0;
    			
    			int weekNumber = date.getDayOfMonth();
    			
    			
    			for(Ligne l: ListLignes) {
    				
    			s = new Series<String, Long>();
    				
    			cpt = 1;	
    			
    			s.setName("IPPM pour la Ligne :" + l.getLabelLigne());
    			
    			
    			
    			
    				while(cpt <= weekNumber) {  
        						
    					ippm = (long) new StatsTransaction().getLigneIppm(l.getIdLigne(), date.getYear(), date.getMonth().getValue(), cpt);
    					
    					s.getData().add(new XYChart.Data<>(String.valueOf(cpt) , ippm));			
    				    				
    					cpt ++;
		    
    				}
    			ListSeries.add(s);		
    				
    				
    		}
    		CHART_IPPM_LIGNE.getData().addAll(ListSeries);
    		
		}break;
		
    		case "this Year":{
        		
    			int cpt = 1;
    			
    			long ippm = 0;
    			
    			for(Ligne l: ListLignes) {
    				
    				s = new Series<String, Long>();	
    				
    				s.setName("IPPM pour la Ligne :" + l.getLabelLigne()); 
    			
    				cpt = 1;
    			
    			while( cpt <= date.getMonthValue()) {  

    					ippm = (long) new StatsTransaction().getLigneIppm(l.getIdLigne(), date.getYear(), cpt, 0);

    					s.getData().add(new XYChart.Data<>(Month.of(cpt).name(), ippm));
	
    					cpt ++;
			    
    				}
    			ListSeries.add(s);
    			
    		}
    			
    			
		CHART_IPPM_LIGNE.getData().addAll(ListSeries);
		
	
		}break;

    	}
	

    }

    @FXML
    void refreshIPPMZoneClicked(MouseEvent event) throws SQLException {
    	
    	
    	CHART_IPPM_ZONE.getData().clear();
    	
    	LocalDate date = LocalDate.now();
    	
    	ObservableList<XYChart.Series<String, Long>> ListSeries = FXCollections.observableArrayList();
    	
    	Series<String, Long> s = new Series<String, Long>();

		String T = ComboBoxIZ.getValue();
    	
    	
    	switch(T) {
    	case "this Week":{
	
			int cpt = 1;
			
			long ippm = 0;
			
			LocalDate d = null;
			
			
			int weekNumber = date.getDayOfWeek().getValue();
			
	
			
			for(Zone z: ListZones) {
				
				s = new Series<String, Long>();
				
				s.setName("IPPM pour la Zone :" + z.getLabelZone());  
				
				cpt = 1;	
			
				ippm = 0;
				
			while(cpt <= weekNumber) {  
			
					d = date.minusDays(date.getDayOfWeek().getValue() - cpt);

	 
					
					ippm = new StatsTransaction().getZoneIppm(z.getIdZone(), date.getYear(), d.getMonth().getValue(), d.getDayOfMonth());

					s.getData().add(new XYChart.Data<>(DayOfWeek.of(cpt).name(), ippm));
					
					
					cpt ++;
		    
				}
			
			ListSeries.add(s);
			
		
		}
    		
    	CHART_IPPM_ZONE.getData().addAll(ListSeries);	
    		
		}break;
   		
    		case "this Month":{
    			

    			int cpt = 1;
    			
    			long ippm = 0;
    			
    			int monthNumber = date.getDayOfMonth();
    			
    			
    			
    			for(Zone z: ListZones) {
    				
    				s = new Series<String, Long>();
    				
    				s.setName("IPPM pour la Zone :" + z.getLabelZone());  	
    				
    				cpt = 1;	
    			

    				
    			while(cpt <= monthNumber) {  

   					
    					ippm = new StatsTransaction().getZoneIppm(z.getIdZone(), date.getYear(), date.getMonth().getValue(), cpt);
	
    					
    					s.getData().add(new XYChart.Data<>(String.valueOf(cpt) , ippm));
        				
        				
        				cpt ++;
    		    
    				}
    			ListSeries.add(s);
			
    			}
    			
    		CHART_IPPM_ZONE.getData().addAll(ListSeries);	

		}break;
		
    		case "this Year":{
        		
        		
		    			
				int cpt = 1;
    			
				long ippm = 0;
				
				int day = date.getMonthValue();
    			
				for(Zone z: ListZones) {
					
				s = new Series<String, Long>();
    				
	    		s.setName("IPPM pour la Zone :" + z.getLabelZone());  
		
					
    			cpt = 1;	

    			
    			while(cpt <= day) {   

		
    					ippm = (long) new StatsTransaction().getZoneIppm(z.getIdZone(), date.getYear(), cpt, 0);
	
    					s.getData().add(new XYChart.Data<>(Month.of(cpt).name(), ippm));
        				       				
        				cpt ++;
    		    
    				}
    			
    			System.out.print(s.getData());
    			
    			ListSeries.add(s);
    				
    		}
				
        		
		CHART_IPPM_ZONE.getData().addAll(ListSeries);	
    		
		}break;
	
    				
    				
    				
    	}
	
    	

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
    
    
    
    
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		ObservableList<String> DéfautTimeOptions = FXCollections.observableArrayList();
		DéfautTimeOptions.addAll("today", "this Week","this Month", "this Year");
		
		ObservableList<String> IPPMTimeOptions = FXCollections.observableArrayList();
		IPPMTimeOptions.addAll("this Week","this Month", "this Year");
		
		
		
		ComboBoxDZ.setItems(DéfautTimeOptions);
		ComboBoxDZ.setValue("today");
		
		ComboBoxDL.setItems(DéfautTimeOptions);
		ComboBoxDL.setValue("today");
		
		ComboBoxIZ.setItems(IPPMTimeOptions);
		ComboBoxIZ.setValue("this Week");
		
		ComboBoxIL.setItems(IPPMTimeOptions);
		ComboBoxIL.setValue("this Week");
		
		
		try {
			
			ListZones = new ZoneTransaction().getAll();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(Zone z : ListZones) {
			
			ZoneComboBoxDL.getItems().add(z.getLabelZone());
			ZoneComboBoxIL.getItems().add(z.getLabelZone());
			
		}
		
		ZoneComboBoxDL.setValue(ListZones.get(0).getLabelZone());
		ZoneComboBoxIL.setValue(ListZones.get(0).getLabelZone());
	
		
		try {
			refreshDefautsLigneClicked(null);
			refreshDefautsZoneClicked(null);
			refreshIPPMZoneClicked(null);
			refreshIPPMLigneClicked(null);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
