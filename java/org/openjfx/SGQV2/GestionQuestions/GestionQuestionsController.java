package org.openjfx.SGQV2.GestionQuestions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.SGQV2.App;
import org.openjfx.SGQV2.Administration.HomeController;

import com.jfoenix.controls.JFXButton;

import Models.Question;
import Transactions.QuestionTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GestionQuestionsController implements Initializable {
	
	
		public static String witchQuestions;

	  	@FXML
	    private JFXButton BtnCreate;

	    @FXML
	    private JFXButton BtnDelete;

	    @FXML
	    private JFXButton BtnEdit;

	    @FXML
	    private JFXButton BtnRetour;
	    

	    @FXML
	    private TableColumn<Question, String> QuestionColumn;
	    
	    @FXML
	    private TableColumn<Question, String> CommentaireColumn;

	    @FXML
	    private TableView<Question> QuestionTable;
	    
	    @FXML
	    private Label labelAP;

	    @FXML
	    private Label labelTP;

	    @FXML
	    private Label labelTPP;

	

	    @FXML
	    void BtnCreateClicked(ActionEvent event) throws IOException, SQLException {
	    	Add_ModQuestionAPController.questionAP = null;
	    	Add_ModQuestionAPController.isCreateQuestionAP = true;    
        	openStage();
	    }

	    @FXML
	    void BtnDeleteClicked(ActionEvent event) throws SQLException {
	    	if(!QuestionTable.getSelectionModel().isEmpty()) {
	        	int selectedIndex = QuestionTable.getSelectionModel().getSelectedIndex();
	        	new QuestionTransaction().deleteById(QuestionTable.getItems().get(selectedIndex).getId());
	        }
	    }

	    @FXML
	    void BtnEditClicked(ActionEvent event) throws IOException, SQLException {
	    	
	    	if(!QuestionTable.getSelectionModel().isEmpty()) {
	        	int index = QuestionTable.getSelectionModel().getSelectedIndex();
	        	Add_ModQuestionAPController.questionAP = QuestionTable.getItems().get(index);	        	
	        	Add_ModQuestionAPController.isCreateQuestionAP = false;        	
	        	openStage();
	        }	
	    	
	    }

	    @FXML
	    void BtnRetourClicked(ActionEvent event) throws IOException {
	    	
	    	HomeController.StartPane = "Home//AdminPane";	    	
	    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home//Home.fxml"));
	        Scene scene = new Scene(fxmlLoader.load());
	        Stage thisStage = (Stage) BtnRetour.getScene().getWindow();
	        thisStage.setScene(scene);
	        
	    }
	    
	    
	    public void openStage() throws IOException, SQLException {
	    	 FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Admin//GestionQuestions//AddModQuestions.fxml"));
	         Scene scene = new Scene(fxmlLoader.load());
	         Stage stage = new Stage();
	         stage.setScene(scene);
	         if(Add_ModQuestionAPController.isCreateQuestionAP) { stage.setTitle("Create Question");}
	         else{ stage.setTitle("Edit Question");}
	         stage.initModality(Modality.APPLICATION_MODAL);
	         stage.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));
	         stage.showAndWait();  

	         updateTable();
	    }
	    
	    
	    
	    public void updateTable() throws SQLException {
	    	if(witchQuestions.equals("AProcess")) {
	    		
	    		QuestionTable.getItems().clear();			
				QuestionTable.setItems(new QuestionTransaction().getAllAPQuestions());
				
	    	}
	    	else if(witchQuestions.equals("AProduit")) {
	    		
	    		QuestionTable.getItems().clear();			
				QuestionTable.setItems(new QuestionTransaction().getAllAProduit());
				
	    	}else if(witchQuestions.equals("AProduitP")) {
	    		
	    		QuestionTable.getItems().clear();			
				QuestionTable.setItems(new QuestionTransaction().getAllAProduitFirstPiece());
				
				
	    	}
	    	
	    }
	    

		@Override
		public void initialize(URL location, ResourceBundle resources) {

			QuestionColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
			CommentaireColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("commentaire"));

			if(witchQuestions.equals("AProcess")) {
			
				try {
					QuestionTable.getItems().clear();
					
					labelAP.setVisible(true);
		
					QuestionTable.setItems(new QuestionTransaction().getAllAPQuestions());
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(witchQuestions.equals("AProduit")) {
			
				try {
					QuestionTable.getItems().clear();
					QuestionTable.setItems(new QuestionTransaction().getAllAProduit());
					labelTP.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if(witchQuestions.equals("AProduitP")) {
				try {
					QuestionTable.getItems().clear();
					QuestionTable.setItems(new QuestionTransaction().getAllAProduitFirstPiece());
					labelTPP.setVisible(true);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
			
		
		}
	    
	
	
	

	
	
	
}
