package Transactions;

import java.sql.SQLException;

import Models.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientTransaction extends BaseTransaction {

	public ClientTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public void save(Client client) throws SQLException {
		
		String request= "insert into client (labelClient, commentaire) values (?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setString(1, client.getLabelClient());
		this.preparedStatement.setString(2, client.getCommentaire());
				
		this.preparedStatement.execute();
		
		
	}
	
	public ObservableList<Client> getAll() throws SQLException{
		
		ObservableList<Client> myList = FXCollections.observableArrayList();
		
		String request= "select * from Client"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
		}
		
		return myList;
	}
	
	public void deleteById(int id) throws SQLException {
		
		String request= "delete from client where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.preparedStatement.execute();	
		
		
	}
	
	public void update(Client object) throws SQLException {
		
		String request= "UPDATE `client` SET `labelClient`= ?,`commentaire`=? WHERE id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
	
		this.preparedStatement.setString(1, object.getLabelClient());
		this.preparedStatement.setString(2, object.getCommentaire());
		this.preparedStatement.setInt(3, object.getId());

		this.preparedStatement.execute();
		
		
		
	}
	
	public Client getByLabelClient(String lblclient) throws SQLException {
		
		String request= "select * from client where labelClient = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setString(1, lblclient);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Client client = null;
		
		while(resultSet.next()) {
			client = new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
		}
		
		return client;
		
	}
	
	public Client getById(int id) throws SQLException {
		
		String request= "select * from client where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Client client = null;
		
		while(resultSet.next()) {
			client = new Client(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
		}
		
		return client;
		
	}
	
	
	

}
 