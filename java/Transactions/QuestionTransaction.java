package Transactions;

import java.sql.SQLException;

import Models.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuestionTransaction extends BaseTransaction {

	public QuestionTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void save(Question question) throws SQLException {
		
		String request= "insert into question (question, isAuditProcess, isFirstPiece, commentaire) values (?, ?, ?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setString(1, question.getQuestion());
		this.preparedStatement.setBoolean(2, question.getIsAuditProcess());
		this.preparedStatement.setBoolean(3, question.getIsFirstPiece());
		this.preparedStatement.setString(4, question.getCommentaire());

		
		this.preparedStatement.execute();
		
		
	}
	
	
	public ObservableList<Question> getAllAPQuestions() throws SQLException{
		
		ObservableList<Question> myList = FXCollections.observableArrayList();
		
		String request= "select * from question where isAuditProcess = true"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Question(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(4),resultSet.getBoolean(5), resultSet.getString(3)));
		}
		
		return myList;
	}
	
	
	public ObservableList<Question> getAllAProduit() throws SQLException{
		
		ObservableList<Question> myList = FXCollections.observableArrayList();
		
		String request= "select * from question where isAuditProcess = 0 and isFirstPiece = 0"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Question(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(4),resultSet.getBoolean(5), resultSet.getString(3)));
		}
		
		return myList;
	}
	
	
	public ObservableList<Question> getAllAProduitFirstPiece() throws SQLException{
		
		ObservableList<Question> myList = FXCollections.observableArrayList();
		
		String request= "select * from question where isAuditProcess = 0 and isFirstPiece = 1"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Question(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(4),resultSet.getBoolean(5), resultSet.getString(3)));
		}
		
		return myList;
	}
	
	
	
	public void deleteById(Integer id) throws SQLException {
		
		String request= "delete from question where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.preparedStatement.execute();	
		
		
	}
	
	public void update(Question question) throws SQLException {
		
		String request= "UPDATE question SET  question = ?, isAuditProcess = ?,  isFirstPiece = ?,  commentaire = ?  WHERE id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
	
		this.preparedStatement.setString(1, question.getQuestion());
		this.preparedStatement.setBoolean(2, question.getIsAuditProcess());
		this.preparedStatement.setBoolean(3, question.getIsFirstPiece());
		this.preparedStatement.setString(4, question.getCommentaire());
		this.preparedStatement.setInt(5, question.getId());
		
		this.preparedStatement.execute();
		

	}
	
	public Question getById(Integer id) throws SQLException {

		String request= "Select * from question where id = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);

		this.preparedStatement.setInt(1, id);

		this.resultSet = this.preparedStatement.executeQuery();

		Question question = null; 

		while(resultSet.next()) {
			question = new Question(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(4),resultSet.getBoolean(5), resultSet.getString(3));
		}

		return question;


	}


	
	
	

}
