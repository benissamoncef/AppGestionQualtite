package Transactions;

import java.sql.SQLException;

import Models.TypeCompte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TypeCompteTransaction extends BaseTransaction {

	public TypeCompteTransaction() throws SQLException {
		super();
	}
	
	
	
	public TypeCompte getById(int id) throws SQLException {
		
		String request= "select * from type_Compte where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
				
		this.resultSet = this.preparedStatement.executeQuery();
		
		TypeCompte t = null;
		
		while(resultSet.next()) {
			t = new TypeCompte(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
		}
		
		return t;	

	}
	
	
	
	public ObservableList<TypeCompte> getAll() throws SQLException{
		
		ObservableList<TypeCompte> myList = FXCollections.observableArrayList();
		
		String request= "select * from type_compte"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new TypeCompte(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
		}
		
		return myList;
	}
	
	
	public TypeCompte getByType(String Type) throws SQLException {
		
		String request= "select * from type_Compte where labelType = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setString(1, Type);
				
		this.resultSet = this.preparedStatement.executeQuery();
		
		TypeCompte t = null;
		
		while(resultSet.next()) {
			t = new TypeCompte(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
		}
		
		return t;	

	}
	

}
