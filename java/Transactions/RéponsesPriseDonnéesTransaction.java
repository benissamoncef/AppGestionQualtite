package Transactions;

import java.sql.SQLException;
import java.sql.Time;

import Models.RéponseRapportArretsTableau;
import Models.RéponseRapportDéfautsTableau;
import Models.RéponseRapportProductionTableau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RéponsesPriseDonnéesTransaction extends BaseTransaction {
	
	
	
	
	public RéponsesPriseDonnéesTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public void saveRéponseRapportDéfautsTableau(RéponseRapportDéfautsTableau r) throws NumberFormatException, SQLException {
		
		
		String request= "insert into réponserapportdéfautstableau (numero, numeroSérie, référence, poste, matricule, codeDéfaut, composant, circuit, voie1, voie2, qte, obs, id_Formulaire) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setInt(1, r.getNumero());
		this.preparedStatement.setInt(2, r.getNumeroSérie());
		this.preparedStatement.setString(3, r.getRéférence());
		this.preparedStatement.setInt(4, r.getPoste());
		this.preparedStatement.setInt(5, r.getMatricule());
		this.preparedStatement.setInt(6, r.getCodeDéfaut());
		this.preparedStatement.setString(7, r.getComposant());
		this.preparedStatement.setString(8, r.getCircuit());
		this.preparedStatement.setInt(9, r.getVoie1());
		this.preparedStatement.setInt(10, r.getVoie2());
		this.preparedStatement.setInt(11, r.getQte());
		this.preparedStatement.setString(12, r.getObs());
		this.preparedStatement.setInt(13, r.getId_réponseFormulaire());



		
		this.preparedStatement.execute();
		
		
		
	}
	
	public void saveRéponseRapportProductionTableau(RéponseRapportProductionTableau r) throws SQLException {
		
		
		String request= "insert into réponserapportproductiontableau (numero, référence, qte, Obs, id_Formulaire) values (?, ?, ?, ?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setInt(1, r.getNumero());
		this.preparedStatement.setString(2, r.getRéférence());
		this.preparedStatement.setInt(3, r.getQte());
		this.preparedStatement.setString(4, r.getObs());
		this.preparedStatement.setInt(5, r.getId_réponseFormulaire());

		
		this.preparedStatement.execute();
		
		
		
	}
	
	public void saveRéponseRapportArretsTableau(RéponseRapportArretsTableau r) throws NumberFormatException, SQLException {
		
		
		String request= "insert into réponserapportarretstableau (numero, type, intervension, intervenant, référence, description, matricule, heurArret, heurReprise, NbrOp, id_Formulaire) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setInt(1, r.getNumero());
		this.preparedStatement.setString(2, r.getType());
		this.preparedStatement.setInt(3, r.getIntervension());
		this.preparedStatement.setString(4, r.getIntervenant());
		this.preparedStatement.setString(5, r.getRéférence());
		this.preparedStatement.setString(6, r.getDescription());
		this.preparedStatement.setString(7, r.getMatricule());
		this.preparedStatement.setTime(8, Time.valueOf(r.getHeurArret()));
		this.preparedStatement.setTime(9, Time.valueOf(r.getHeurReprise()));
		this.preparedStatement.setInt(10, r.getNbrOp());
		this.preparedStatement.setInt(11, r.getId_réponseFormulaire());

		
		this.preparedStatement.execute();
		
		
		
	}
	
	
	public void updateRéponseRapportDéfautsTableau(RéponseRapportDéfautsTableau r) throws SQLException {
		
		String request= "UPDATE réponserapportdéfautstableau SET  numero = ?, numeroSérie = ?, référence = ?,  poste = ?,  matricule = ?, codeDéfaut = ?, composant = ?, circuit = ?, voie1 = ?, voie2 = ?, qte = ?, obs = ?    WHERE id = ?"; 
		

		
		this.preparedStatement = this.cnx.prepareStatement(request);
	
		this.preparedStatement.setInt(1, r.getNumero());
		this.preparedStatement.setInt(2, r.getNumeroSérie());
		this.preparedStatement.setString(3, r.getRéférence());
		this.preparedStatement.setInt(4, r.getPoste());
		this.preparedStatement.setInt(5, r.getMatricule());
		this.preparedStatement.setInt(6, r.getCodeDéfaut());
		this.preparedStatement.setString(7, r.getComposant());
		this.preparedStatement.setString(8, r.getCircuit());
		this.preparedStatement.setInt(9, r.getVoie1());
		this.preparedStatement.setInt(10, r.getVoie2());
		this.preparedStatement.setInt(11, r.getQte());
		this.preparedStatement.setString(12, r.getObs());
		this.preparedStatement.setInt(13, r.getId());
		
		this.preparedStatement.execute();
		

	}
	
	public void updateRéponseRapportProductionTableau(RéponseRapportProductionTableau r) throws SQLException {
		
		String request= "UPDATE réponserapportproductiontableau SET  numero = ?, référence = ?,  qte = ?,  Obs = ?   WHERE id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
	
		this.preparedStatement.setInt(1, r.getNumero());
		this.preparedStatement.setString(2, r.getRéférence());
		this.preparedStatement.setInt(3, r.getQte());
		this.preparedStatement.setString(4, r.getObs());
		this.preparedStatement.setInt(5, r.getId());
		
		this.preparedStatement.execute();
		

	}


	public void updateRéponseRapportArretsTableau(RéponseRapportArretsTableau r) throws SQLException {
	
	String request= "UPDATE réponserapportarretstableau SET  numero = ?, type = ?,  intervension = ?,  intervenant = ?, référence = ?, description = ?, matricule = ?,  heurArret = ?, heurReprise = ?    WHERE id = ?"; 
	
	this.preparedStatement = this.cnx.prepareStatement(request);

	this.preparedStatement.setInt(1, r.getNumero());
	this.preparedStatement.setString(2, r.getType());
	this.preparedStatement.setInt(3, r.getIntervension());
	this.preparedStatement.setString(4, r.getIntervenant());
	this.preparedStatement.setString(5, r.getRéférence());
	this.preparedStatement.setString(6, r.getDescription());
	this.preparedStatement.setString(7, r.getMatricule());
	this.preparedStatement.setTime(8, Time.valueOf(r.getHeurArret()));
	this.preparedStatement.setTime(9, Time.valueOf(r.getHeurReprise()));
	this.preparedStatement.setInt(10, r.getId());
	
	this.preparedStatement.execute();
	

	}
	
	
	
	public ObservableList<RéponseRapportDéfautsTableau> getAllRéponsesDéfauts(int id) throws SQLException{
		
		ObservableList<RéponseRapportDéfautsTableau> myList = FXCollections.observableArrayList(); 
		
		 String request= "select * from réponserapportdéfautstableau where id_Formulaire = ?";

	        this.preparedStatement = this.cnx.prepareStatement(request);
	        
	        this.preparedStatement.setInt(1, id);

	        this.resultSet = this.preparedStatement.executeQuery();

	        while(resultSet.next()) {
	        	
	        	myList.add(new RéponseRapportDéfautsTableau(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7), resultSet.getString(8), resultSet.getString(9), resultSet.getInt(10), resultSet.getInt(11), resultSet.getInt(12), resultSet.getString(13), resultSet.getInt(14)));
	        }
		
		return myList;
		
	}
	
	
	public ObservableList<RéponseRapportProductionTableau> getAllRéponsesProduction(int id) throws SQLException{
		
		ObservableList<RéponseRapportProductionTableau> myList = FXCollections.observableArrayList();; 
		
		 String request= "select * from réponserapportproductiontableau where id_Formulaire = ?";

	        this.preparedStatement = this.cnx.prepareStatement(request);
	        
	        this.preparedStatement.setInt(1, id);

	        this.resultSet = this.preparedStatement.executeQuery();

	        while(resultSet.next()) {
	        	
	        	myList.add(new RéponseRapportProductionTableau(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getInt(6)));
	        }
		
		return myList;
		
	}
	
	public ObservableList<RéponseRapportArretsTableau> getAllRéponsesArrets(int id) throws SQLException{
		
		ObservableList<RéponseRapportArretsTableau> myList = FXCollections.observableArrayList();; 
		
		 String request= "select * from réponserapportarretstableau where id_Formulaire = ?";

	        this.preparedStatement = this.cnx.prepareStatement(request);
	        
	        this.preparedStatement.setInt(1, id);

	        this.resultSet = this.preparedStatement.executeQuery();

	        while(resultSet.next()) {
	        	
	        	myList.add(new RéponseRapportArretsTableau(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getTime(9).toLocalTime(), resultSet.getTime(10).toLocalTime(),resultSet.getInt(11),resultSet.getInt(12)));
	        }
		
		return myList;
		
	}
	
	
	
	public void deleteRéponseRapportDéfauts(int id) throws SQLException {
		
		String request= "delete from réponserapportdéfautstableau where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.preparedStatement.execute();	
		
		
	}
	
	public void deleteRéponseRapportProduction(int id) throws SQLException {
		
		String request= "delete from réponserapportproductiontableau where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.preparedStatement.execute();	
		
		
	}


	public void deleteRéponseRapportArrets(int id) throws SQLException {
	
	String request= "delete from réponserapportarretstableau where id = ?"; 
	
	this.preparedStatement = this.cnx.prepareStatement(request);
	
	this.preparedStatement.setInt(1, id);
	
	this.preparedStatement.execute();	
	
	
	}
	
	
	
	
	
	

}
