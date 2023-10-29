package Models;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import org.openjfx.SGQV2.App;
import org.openjfx.SGQV2.Forms.FomulairePriseDonnéesController;
import org.openjfx.SGQV2.Forms.FormulaireProduitController;
import org.openjfx.SGQV2.Forms.RépondreFAPController;
import org.openjfx.SGQV2.Planings.PlaningController;

import Transactions.ReponseFormulaireTransaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlaningTable {

	private int id_Réponse;
	private String client;
	private String projet;
	private String famille;
	private Date createdAt;
		
	private HBox actions = new HBox();
	
	private FontAwesomeIconView Consulter = new FontAwesomeIconView();
	private FontAwesomeIconView Edit = new FontAwesomeIconView();

	

	
	public PlaningTable(int id_Réponse, String client, String projet, String famille, Date createdAt) {
		super();
		this.id_Réponse = id_Réponse;
		this.client = client;
		this.projet = projet;
		this.famille = famille;
		this.createdAt = createdAt;
		
		Consulter.setGlyphName("FILE");
		Consulter.setFill(Color.WHITE);
		Consulter.setSize("24");


				
		Edit.setGlyphName("EDIT");
		Edit.setFill(Color.WHITE);
		Edit.setSize("24");

		
		Consulter.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent me) {
		    	
		  
		    	ReponseFormulaire r = null;		    	
		    	
		    	try {
					r = new ReponseFormulaireTransaction().getOne(id_Réponse);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	PlaningController.r = r;
		    	

		    	
		    	
		    	if(r.getId_formulaire() == 1) {
		    		
			    		RépondreFAPController.isAdmin = true;
			    		RépondreFAPController.isModAdmin = false;
		    		
		    		
		    		 	FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Forms//RépondreFAP.fxml"));
		    	        Scene scene = null;
						try {
							scene = new Scene(fxmlLoader.load());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    	        Stage stage = new Stage();
		    	        stage.setScene(scene);
		    	        stage.setTitle("Formulaire Audit Process");		    	     
		    	        stage.initModality(Modality.APPLICATION_MODAL);
		    	        stage.showAndWait();   
		    		
		    		
		    	}
		    	else if(r.getId_formulaire() == 2){
		    		
		    		FormulaireProduitController.isAdmin1 = true;
		    		FormulaireProduitController.isModAdmin1= false;
		    		
		    		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Forms//FormulaireProduit.fxml"));
	    	        Scene scene = null;
					try {
						scene = new Scene(fxmlLoader.load());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	        Stage stage = new Stage();
	    	        stage.setScene(scene);
	    	        stage.setTitle("Formulaire Audit Produit");		    	     
	    	        stage.initModality(Modality.APPLICATION_MODAL);
	    	        stage.showAndWait(); 
		    		
		    		
		    	}else if(r.getId_formulaire() == 3){
		    		
		    		FomulairePriseDonnéesController.isAdmin2 = true;
		    		FomulairePriseDonnéesController.isModAdmin2= false;
		    		
		    		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Forms//Prise de données en montage.fxml"));
	    	        Scene scene = null;
					try {
						scene = new Scene(fxmlLoader.load());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	        Stage stage = new Stage();
	    	        stage.setScene(scene);
	    	        stage.setTitle("Formulaire Prise de données en montage");		    	     
	    	        stage.initModality(Modality.APPLICATION_MODAL);
	    	        stage.showAndWait(); 
		    		
		    		
		    	}
		    	
		    	
		    }
		});	
		
		
		
		Edit.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent me) {
		    	
		    	
		      
		    	ReponseFormulaire r = null;		    	
		    	
		    	try {
					r = new ReponseFormulaireTransaction().getOne(id_Réponse);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	PlaningController.r = r;
		    			    	
		    	if(r.getId_formulaire() == 1) {
		    		
		    		RépondreFAPController.isAdmin = true;
			    	RépondreFAPController.isModAdmin = true;
		    		
		    		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Forms//RépondreFAP.fxml"));
	    	        Scene scene = null;
					try {
						scene = new Scene(fxmlLoader.load());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	        Stage stage = new Stage();
	    	        stage.setScene(scene);
	    	        stage.setTitle("Formulaire Audit Process");		    	     
	    	        stage.initModality(Modality.APPLICATION_MODAL);
	    	        stage.showAndWait();   
		    		
		    		
		    		
		    	}
		    	else if(r.getId_formulaire() == 2){
		    		
		    		FomulairePriseDonnéesController.isAdmin2 = true;
		    		FomulairePriseDonnéesController.isModAdmin2= true;
		    		
		    		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Forms//FormulaireProduit.fxml"));
	    	        Scene scene = null;
					try {
						scene = new Scene(fxmlLoader.load());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	        Stage stage = new Stage();
	    	        stage.setScene(scene);
	    	        stage.setTitle("Formulaire Audit Produit");		    	     
	    	        stage.initModality(Modality.APPLICATION_MODAL);
	    	        stage.showAndWait();   
		    		
		    		
		    	}else if(r.getId_formulaire() == 3){
		    		
		    		FormulaireProduitController.isAdmin1 = true;
		    		FormulaireProduitController.isModAdmin1= true;
		    		
		    		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Prise de données en montage.fxml"));
	    	        Scene scene = null;
					try {
						scene = new Scene(fxmlLoader.load());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	        Stage stage = new Stage();
	    	        stage.setScene(scene);
	    	        stage.setTitle("Formulaire Prise de données en montage");		    	     
	    	        stage.initModality(Modality.APPLICATION_MODAL);
	    	        stage.showAndWait();   
		    		
		    		
		    	}
		    	
		    	
		    	
		    }
		});	
		
		
		actions.getChildren().addAll(Consulter, Edit);
		actions.setPadding(new Insets(0, 0, 0, 20));
		actions.setSpacing(16);
		
	}
	
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getProjet() {
		return projet;
	}
	public void setProjet(String projet) {
		this.projet = projet;
	}
	public String getFamille() {
		return famille;
	}
	public void setFamille(String famille) {
		this.famille = famille;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public int getId_Réponse() {
		return id_Réponse;
	}


	public void setId_Réponse(int id_Réponse) {
		this.id_Réponse = id_Réponse;
	}




	public FontAwesomeIconView getConsulter() {
		return Consulter;
	}


	public void setConsulter(FontAwesomeIconView consulter) {
		Consulter = consulter;
	}


	public HBox getActions() {
		return actions;
	}


	public void setActions(HBox actions) {
		this.actions = actions;
	}


	public FontAwesomeIconView getEdit() {
		return Edit;
	}


	public void setEdit(FontAwesomeIconView edit) {
		Edit = edit;
	}


	
	

	

}
