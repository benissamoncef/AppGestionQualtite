package DrawingsControllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.Scan;

import com.jfoenix.controls.JFXTextField;

import Models.Drawing;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class DrawingsIngQualitéController implements Initializable{

    @FXML
    private FontAwesomeIconView Btnsearch;

    @FXML
    private TableColumn<Drawing, Timestamp> DateCréationColumn;

    @FXML
    private TableColumn<Drawing, HBox> DrawingColumn;

    @FXML
    private TableView<Drawing> DrawingTable;
    
    @FXML
    private TableColumn<Drawing, Integer> RévisionColumn;
    

    @FXML
    private TableColumn<Drawing, Timestamp> DateRévisionColumn;


    @FXML
    private JFXTextField SearchField;

    @FXML
    void Btnsearch(KeyEvent event) throws SQLException, IOException {
    	if(event.getCode().equals(KeyCode.ENTER))
    	updateDrawings();
    }

    @FXML
    void BtnsearchClicked(MouseEvent event) throws SQLException, IOException {
    	updateDrawings();
    }
    
    
    
    private void updateDrawings() throws SQLException, IOException {
    	
    	String s = SearchField.getText();
    	
    	if(!s.isEmpty()) {
    		if(isMultiplerRéf(s))
    			s = new Scan().scanForDrawing(s);
    			
			
    		if (new DrawingTransaction().checkByRef(s)) {   	
    			
    			SearchField.setText(s);
    			DrawingTable.setItems(new DrawingTransaction().GetAll(s));
    			
    			
    		}
    		else {
    			
    			DrawingTable.getItems().clear();		
    		}
    			
    		
    	} 
    }
    
    private Boolean isMultiplerRéf(String s) {
    	 long cnt = s.chars().filter(ch -> ch == '-').count();
    	 if(cnt == 2) 
    		 return true;
    	 else 
    		 return false;
	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		DrawingColumn.setCellValueFactory(new PropertyValueFactory<Drawing, HBox>("hbox"));
		DateCréationColumn.setCellValueFactory(new PropertyValueFactory<Drawing, Timestamp>("CreatedAt"));
		RévisionColumn.setCellValueFactory(new PropertyValueFactory<Drawing, Integer>("Révision"));
		DateRévisionColumn.setCellValueFactory(new PropertyValueFactory<Drawing, Timestamp>("dateRévision"));

		
	}

}
