package Transactions;

import java.sql.Date;
import java.sql.SQLException;

import java.time.temporal.ChronoField;

import Models.Client;
import Models.Famille;
import Models.PlaningTable;
import Models.Projet;
import Models.ReponseFormulaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ReponseFormulaireTransaction extends BaseTransaction {

	public ReponseFormulaireTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void save(ReponseFormulaire reponseForm) throws SQLException {

        String request= "insert into reponse_formulaire (etat_reponse, id_formulaire, id_compte_technicien ,id_famille, id_ligne, id_shift, equipe, teamLeader, opérateur, Week, CreatedAt) values (?,?,?,?,?,?,?,?,?,?,?)";

        this.preparedStatement = this.cnx.prepareStatement(request);

        
        this.preparedStatement.setBoolean(1, reponseForm.getEtat_reponse());
        this.preparedStatement.setInt(2, reponseForm.getId_formulaire());
        this.preparedStatement.setInt(3, reponseForm.getId_compte_technicien());
        this.preparedStatement.setInt(4, reponseForm.getId_famille());
        this.preparedStatement.setInt(5, reponseForm.getId_ligne());
        this.preparedStatement.setInt(6, reponseForm.getId_shift());
        this.preparedStatement.setString(7, reponseForm.getEquipe());
        this.preparedStatement.setString(8, reponseForm.getTeamLeader());
        this.preparedStatement.setString(9, reponseForm.getOpérateur());
        this.preparedStatement.setInt(10, reponseForm.getCreatedAt().toLocalDate().get(ChronoField.ALIGNED_WEEK_OF_YEAR));
        this.preparedStatement.setDate(11, reponseForm.getCreatedAt());
        
        this.preparedStatement.execute();


    }
	
	
	
	
	public ReponseFormulaire getAuditProcess(ReponseFormulaire reponse) throws SQLException {

        String request= "select * from reponse_formulaire where id_formulaire = ? and id_compte_technicien = ? and id_famille = ? and id_ligne = ?  and Week = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        

        this.preparedStatement.setInt(1, reponse.getId_formulaire());
        this.preparedStatement.setInt(2, reponse.getId_compte_technicien());
        this.preparedStatement.setInt(3, reponse.getId_famille());
        this.preparedStatement.setInt(4, reponse.getId_ligne());
        this.preparedStatement.setInt(5, reponse.getWeek());
        
        this.resultSet = this.preparedStatement.executeQuery();
		
        ReponseFormulaire reponseAP = null;
		
		while(resultSet.next()) {
			reponseAP = new ReponseFormulaire(resultSet.getInt(1), resultSet.getBoolean(2), resultSet.getInt(3), resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getString(8),resultSet.getInt(9),resultSet.getDate(10) );
		}
		
		return reponseAP;


    }
	
	
	public Boolean checkPriseDonnées(ReponseFormulaire reponse) throws SQLException {

        String request= "select * from reponse_formulaire where id_formulaire = ? and id_compte_technicien = ? and id_famille = ? and id_ligne = ? and id_shift = ? and CreatedAt = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        

        this.preparedStatement.setInt(1, reponse.getId_formulaire());
        this.preparedStatement.setInt(2, reponse.getId_compte_technicien());
        this.preparedStatement.setInt(3, reponse.getId_famille());
        this.preparedStatement.setInt(4, reponse.getId_ligne());
        this.preparedStatement.setInt(5, reponse.getId_shift());
        this.preparedStatement.setDate(6, reponse.getCreatedAt());
        
        this.resultSet = this.preparedStatement.executeQuery();

		
        if(!this.resultSet.next()) 
        	return false;
	
		else 
			return true;
		
    }
	
	
	
	public Boolean checkAuditProcess(ReponseFormulaire reponse) throws SQLException {

        String request= "select * from reponse_formulaire where id_formulaire = ? and id_compte_technicien = ? and id_famille = ? and id_ligne = ? and Week = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        

        this.preparedStatement.setInt(1, reponse.getId_formulaire());
        this.preparedStatement.setInt(2, reponse.getId_compte_technicien());
        this.preparedStatement.setInt(3, reponse.getId_famille());
        this.preparedStatement.setInt(4, reponse.getId_ligne());
        this.preparedStatement.setInt(5, reponse.getWeek());
        
        this.resultSet = this.preparedStatement.executeQuery();

		
        if(!this.resultSet.next()) 
        	return false;
	
		else 
			return true;
		
    }
	
	
	public Boolean checkAuditProduit(ReponseFormulaire reponse) throws SQLException {

        String request= "select * from reponse_formulaire where id_formulaire = ? and id_compte_technicien = ? and id_famille = ? and id_ligne = ? and id_shift = ? and CreatedAt = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        

        this.preparedStatement.setInt(1, reponse.getId_formulaire());
        this.preparedStatement.setInt(2, reponse.getId_compte_technicien());
        this.preparedStatement.setInt(3, reponse.getId_famille());
        this.preparedStatement.setInt(4, reponse.getId_ligne());
        this.preparedStatement.setInt(5, reponse.getId_shift());
        this.preparedStatement.setDate(6, reponse.getCreatedAt());
        
        this.resultSet = this.preparedStatement.executeQuery();

		
        if(!this.resultSet.next()) return false;
		
		else return true;
		


    }
	

	public ReponseFormulaire getAuditProduit(ReponseFormulaire reponse) throws SQLException {

        String request= "select * from reponse_formulaire where id_formulaire = ? and id_compte_technicien = ? and id_famille = ? and id_ligne = ? and id_shift = ? and CreatedAt = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        

        this.preparedStatement.setInt(1, reponse.getId_formulaire());
        this.preparedStatement.setInt(2, reponse.getId_compte_technicien());
        this.preparedStatement.setInt(3, reponse.getId_famille());
        this.preparedStatement.setInt(4, reponse.getId_ligne());
        this.preparedStatement.setInt(5, reponse.getId_shift());
        this.preparedStatement.setDate(6, reponse.getCreatedAt());
        
        this.resultSet = this.preparedStatement.executeQuery();
		
        ReponseFormulaire reponseAP = null;
		while(resultSet.next()) {
			reponseAP = new ReponseFormulaire(resultSet.getInt(1), resultSet.getBoolean(2), resultSet.getInt(3), resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getString(8), null, null, resultSet.getDate(12));
		}
		
		return reponseAP;


    }
	
	public ReponseFormulaire getFormulairePriseDonnées(ReponseFormulaire reponse) throws SQLException {

        String request= "select * from reponse_formulaire where id_formulaire = ? and id_compte_technicien = ? and id_famille = ? and id_ligne = ? and id_shift = ? and CreatedAt = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        

        this.preparedStatement.setInt(1, reponse.getId_formulaire());
        this.preparedStatement.setInt(2, reponse.getId_compte_technicien());
        this.preparedStatement.setInt(3, reponse.getId_famille());
        this.preparedStatement.setInt(4, reponse.getId_ligne());
        this.preparedStatement.setInt(5, reponse.getId_shift());
        this.preparedStatement.setDate(6, reponse.getCreatedAt());
        
        this.resultSet = this.preparedStatement.executeQuery();
		
        ReponseFormulaire reponseAP = null;
		while(resultSet.next()) {
			reponseAP = new ReponseFormulaire(resultSet.getInt(1), resultSet.getBoolean(2), resultSet.getInt(3), resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getDate(12));
		}
		
		return reponseAP;


    }
	
	
	
	
	public ObservableList<PlaningTable> getAllAP(int id_Form, int id_tech, int id_Ligne,  int week) throws SQLException {

		ObservableList<PlaningTable> myList = FXCollections.observableArrayList();

		
        String request= "select * from reponse_formulaire where id_formulaire = ? and id_compte_technicien = ?  and id_ligne = ? and Week = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        
        this.preparedStatement.setInt(1, id_Form);
        this.preparedStatement.setInt(2, id_tech);
        this.preparedStatement.setInt(3, id_Ligne);
        this.preparedStatement.setInt(4, week);

        
        
        this.resultSet = this.preparedStatement.executeQuery();
		
        Famille f = null;
        Projet p = null;
        Client c = null;
        PlaningTable test = null;
		
		while(resultSet.next()) {
			
			f = new FamilleTransaction().getById(resultSet.getInt(5));

			if(f != null)
			p = new ProjetTransaction().getById(f.getIdProjet());

			if(p != null)	
			c = new ClientTransaction().getById(p.getId_client());
			
			
			if(c != null && p != null && f != null) {
				test = new PlaningTable(resultSet.getInt(1), c.getLabelClient(), p.getLabelProjet(), f.getCodeFamilleInterne(), resultSet.getDate(11));
			}
			
			myList.add(test);

			
		}
		
		return myList;

    }
	
	
	
	public ObservableList<PlaningTable> getAllNAP(int id_Form, int id_tech, int id_Ligne, Date createdAt) throws SQLException {

		ObservableList<PlaningTable> myList = FXCollections.observableArrayList();

		
        String request= "select * from reponse_formulaire where id_formulaire = ? and id_compte_technicien = ?  and id_ligne = ? and CreatedAt = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        
        this.preparedStatement.setInt(1, id_Form);
        this.preparedStatement.setInt(2, id_tech);
        this.preparedStatement.setInt(3, id_Ligne);
        this.preparedStatement.setDate(4, createdAt);

        
        
        this.resultSet = this.preparedStatement.executeQuery();
		
        Famille f = null;
        Projet p = null;
        Client c = null;
        PlaningTable test = null;
		
		while(resultSet.next()) {
			
			f = new FamilleTransaction().getById(resultSet.getInt(5));

			if(f != null)
			p = new ProjetTransaction().getById(f.getIdProjet());

			if(p != null)	
			c = new ClientTransaction().getById(p.getId_client());
			
			
			if(c != null && p != null && f != null) {
				test = new PlaningTable(resultSet.getInt(1), c.getLabelClient(), p.getLabelProjet(), f.getCodeFamilleInterne(), resultSet.getDate(12));
			}
			
			myList.add(test);

			
		}
		
		return myList;

    }
	
	
	
	public ReponseFormulaire getOne(int id) throws SQLException {
		
		String request = "select * from reponse_formulaire where id = ?";
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
				
        this.resultSet = this.preparedStatement.executeQuery();
        
		ReponseFormulaire r = null;
		
		while(resultSet.next()) {
			
			r = new ReponseFormulaire(resultSet.getInt(1), resultSet.getBoolean(2), resultSet.getInt(3), resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getString(8),null, null, resultSet.getDate(12));		
		}
		
		return r;
		
		
	}
	
	
	
	
	
	
}
