package Transactions;

import java.sql.SQLException;

import Models.GroupeQuestions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GroupeQuestionTransaction extends BaseTransaction{

	public GroupeQuestionTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void save(GroupeQuestions GRQ) throws SQLException {
		
		String request= "insert into groupequestions (titre_groupe, id_formulaire) values (?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setString(1, GRQ.getTitre_groupe());
		this.preparedStatement.setInt(2, GRQ.getId_table_formulaire());

		
		this.preparedStatement.execute();
		
		
	}
	
	
	public ObservableList<GroupeQuestions> getAll() throws SQLException{
		
		ObservableList<GroupeQuestions> myList = FXCollections.observableArrayList();
		
		String request= "select * from groupequestions"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new GroupeQuestions(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
		}
		
		return myList;
	}
	
	
	public void deleteById(Integer id) throws SQLException {
		
		String request= "delete from groupequestions where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.preparedStatement.execute();	
		
		
	}
	
	public void update(GroupeQuestions object) throws SQLException {
		
		String request= "UPDATE `groupequestions` SET ` titre_groupe `= ? WHERE id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
	
		this.preparedStatement.setString(1, object.getTitre_groupe());
		this.preparedStatement.setInt(2, object.getId());

		this.preparedStatement.execute();
		

	}
	
	public GroupeQuestions getById(Integer id) throws SQLException {

		String request= "Select * from groupequestions where id = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);

		this.preparedStatement.setInt(1, id);

		this.resultSet = this.preparedStatement.executeQuery();

		GroupeQuestions groupe = null; 

		while(resultSet.next()) {
			groupe = new GroupeQuestions(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
		}

		return groupe;


	}
	
	public GroupeQuestions getByTitre(String question) throws SQLException {

		String request= "Select * from groupequestions where titre_groupe = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);

		this.preparedStatement.setString(1, question);

		this.resultSet = this.preparedStatement.executeQuery();

		GroupeQuestions groupe = null;

		while(resultSet.next()) {
			groupe = new GroupeQuestions(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
		}

		return groupe ;


	}
	
	

}
