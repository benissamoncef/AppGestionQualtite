package Transactions;

import java.sql.SQLException;

import Models.Client;
import Models.Model;
import Models.Projet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjetTransaction extends BaseTransaction {
	
	public ProjetTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public void save(Projet projet) throws SQLException {
		
		String request= "insert into projet (LabelProjet, commentaire, id_client, id_model) values (?, ?, ?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setString(1, projet.getLabelProjet());
		this.preparedStatement.setString(2, projet.getCommentaire());
		this.preparedStatement.setInt(3, projet.getId_client());
		this.preparedStatement.setInt(4, projet.getId_model());

		
		this.preparedStatement.execute();
		
		
	}
	
	public ObservableList<Projet> getAll() throws SQLException{
		
		ObservableList<Projet> myList = FXCollections.observableArrayList();
		
		String request= "select * from projet"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Client c = null; 
		Model m = null;
		
		while(resultSet.next()) {
		
				c = new ClientTransaction().getById(resultSet.getInt(4));
				m = new ModelTransaction().getById(resultSet.getInt(5));
			
			
				myList.add(new Projet(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5), c.getLabelClient(), m.getLabelModel()));

		}
		
		return myList;
	}
	
	
	public ObservableList<Projet> getAllByClient(int id_client) throws SQLException{
		
		ObservableList<Projet> myList = FXCollections.observableArrayList();
		
		String request= "select * from projet where id_client = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id_client);
		
		this.resultSet = this.preparedStatement.executeQuery();

		while(resultSet.next()) {	
			myList.add(new Projet(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5)));
		}
		
		return myList;
	}
	
	
	public void deleteById(int id) throws SQLException {
		
		String request= "delete from projet where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.preparedStatement.execute();	
		
		
	}
	
	public void update(Projet object) throws SQLException {
		
		String request= "UPDATE projet SET  LabelProjet = ?, commentaire = ?, id_client = ?, id_model = ? WHERE id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
	
		this.preparedStatement.setString(1, object.getLabelProjet());
		this.preparedStatement.setString(2, object.getCommentaire());
		this.preparedStatement.setInt(3, object.getId_client());
		this.preparedStatement.setInt(4, object.getId_model());
		this.preparedStatement.setInt(5, object.getId());

		this.preparedStatement.execute();
		
		
		
	}
	
	public Projet getById(int id) throws SQLException {

		String request= "Select * from projet where id = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);

		this.preparedStatement.setInt(1, id);

		this.resultSet = this.preparedStatement.executeQuery();

		Projet projet = null; 

		while(resultSet.next()) {
			projet = new Projet(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5));
		}

		return projet;


	}

	public Projet getByLabel(String label) throws SQLException {

		String request= "Select * from projet where LabelProjet = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);

		this.preparedStatement.setString(1, label);

		this.resultSet = this.preparedStatement.executeQuery();

		Projet projet = null;

		while(resultSet.next()) {
			projet = new Projet(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5));
		}

		return projet;


	}

}
