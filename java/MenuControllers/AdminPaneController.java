package MenuControllers;

import java.io.IOException;

import org.openjfx.SGQV2.App;
import org.openjfx.SGQV2.GestionQuestions.GestionQuestionsController;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AdminPaneController {

    
    @FXML
    private JFXButton BtnClients;

    @FXML
    private JFXButton BtnComptes;

    @FXML
    private JFXButton BtnEmployees;

    @FXML
    private JFXButton BtnFamilles;

    @FXML
    private JFXButton BtnLignes;

    @FXML
    private JFXButton BtnModels;

    @FXML
    private JFXButton BtnProjets;

    @FXML
    private JFXButton BtnQAProcess;

    @FXML
    private JFXButton BtnQAProduit;

    @FXML
    private JFXButton BtnQAProduitPremier;

    @FXML
    private JFXButton BtnReferences;

    @FXML
    private JFXButton BtnShifts;

    @FXML
    private JFXButton BtnZones;

    @FXML
    void BtnClicked(ActionEvent event) throws IOException {
    	
    	JFXButton b = (JFXButton) event.getSource();
    	
    	switch(b.getId()) {
    	case "BtnClients" : loadStage("Admin//G_Client");break;
    	case "BtnComptes" : loadStage("Admin//G_Comptes");break;
    	case "BtnEmployees" : loadStage("Admin//G_Employees");break;
    	case "BtnFamilles" : loadStage("Admin//GestionFamille");break;
    	case "BtnLignes" : loadStage("Admin//GestionLigne");break;
    	case "BtnModels" : loadStage("Admin//G_Models");break;
    	case "BtnProjets" : loadStage("Admin//G_Projets");break;
    	case "BtnQAProcess" : GestionQuestionsController.witchQuestions = "AProcess" ; loadStage("Admin//GestionQuestions//GestionQuestions");break;
    	case "BtnQAProduit" : GestionQuestionsController.witchQuestions = "AProduit" ; loadStage("Admin//GestionQuestions//GestionQuestions");break;
    	case "BtnQAProduitPremier" : GestionQuestionsController.witchQuestions = "AProduitP" ; loadStage("Admin//GestionQuestions//GestionQuestions");break;
    	case "BtnReferences" : loadStage("Admin//GestionReference");break;
    	case "BtnShifts" : loadStage("Admin//G_Shift");break;
    	case "BtnZones" : loadStage("Admin//GestionZones");break;

    	}

    }
    
    public void loadStage(String path) throws IOException {
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(path + ".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage thisStage = (Stage) BtnClients.getScene().getWindow();
        thisStage.setScene(scene);
        thisStage.centerOnScreen();
    	
    	
    }

	
	
}
