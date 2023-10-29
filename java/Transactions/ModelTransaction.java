package Transactions;

import java.sql.SQLException;

import Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModelTransaction extends BaseTransaction {

	public ModelTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void save(Model model) throws SQLException {
		
		String request= "insert into model (LabelModel, commentaire) values (?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setString(1, model.getLabelModel());
		this.preparedStatement.setString(2, model.getCommentaire());


		this.preparedStatement.execute();
		
		
	}
	
	public ObservableList<Model> getAll() throws SQLException{
		
		ObservableList<Model> myList = FXCollections.observableArrayList();
		
		String request= "select * from model"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Model(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3)));
		}
		
		return myList;
	}
	
	public void deleteById(int id) throws SQLException {
		
		String request= "delete from model where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.preparedStatement.execute();	
		
		
	}
	
	public void update(Model object) throws SQLException {
		
		String request= "UPDATE `model` SET `LabelModel`= ?,`commentaire`=? WHERE id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
	
		this.preparedStatement.setString(1, object.getLabelModel());
		this.preparedStatement.setString(2, object.getCommentaire());
		this.preparedStatement.setInt(3, object.getId());

		this.preparedStatement.execute();
		
		
		
	}
	
	public Model getByLabelModel(String LabelModel) throws SQLException {
		
		String request= "select * from model where LabelModel = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setString(1, LabelModel);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Model model = null;
		
		while(resultSet.next()) {
			model = new Model(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
		}
		
		return model;
		
	}
	
	public Model getById(int id) throws SQLException {
		
		String request= "select * from model where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Model model = null;
		
		while(resultSet.next()) {
			model = new Model(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3));
		}
		
		return model;
		
	}
	
	
	
	
	

}
