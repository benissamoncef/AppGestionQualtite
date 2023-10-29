package Transactions;

import java.sql.SQLException;

import Models.Défaut;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DéfautTransaction extends BaseTransaction{

	public DéfautTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void save(Défaut defaut) throws SQLException {
		
		String request= "insert into defaults (code, description) values (?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setInt(1, defaut.getCode());
		this.preparedStatement.setString(2, defaut.getDescription());
		
		this.preparedStatement.execute();
		
	}
	
	
	public ObservableList<Défaut> getAll() throws SQLException{
		
		ObservableList<Défaut> myList = FXCollections.observableArrayList();
		
		String request= "SELECT * FROM defaults "; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Défaut(resultSet.getInt(1), resultSet.getString(2)));
		}
		
		return myList;
	}
	
	public Défaut getByCode(int code) throws SQLException{
		
	
		String request= "select * from defaults where code  = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, code);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Défaut défaut = null;
		
		while(resultSet.next()) {
			défaut = new Défaut(resultSet.getInt(1), resultSet.getString(2));
		}
		
		return défaut;
	}
	
	
//	public Défaut getByDescription(String description) throws SQLException{
//		
//		
//		String request= "select * from default where description = ?"; 
//		
//		this.preparedStatement = this.cnx.prepareStatement(request);
//		
//		this.preparedStatement.setString(1, description);
//		
//		this.resultSet = this.preparedStatement.executeQuery();
//		
//		Défaut défaut = null;
//		
//		while(resultSet.next()) {
//			défaut = new Défaut(resultSet.getInt(1), resultSet.getString(2));
//		}
//		
//		return défaut;
//	}
	
	

	

}
