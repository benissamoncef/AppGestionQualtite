package Transactions;

import java.sql.SQLException;

import Models.ReponseQuestion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReponseQuestionAPTransaction extends BaseTransaction {

	public ReponseQuestionAPTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public void save(ReponseQuestion reponse) throws SQLException {
		
		String request= "insert into reponse_question (Evaluation, AnomaliesObservées, ActionsExigées, RespRéalisation, DateRéalisation, id_ReponseFormulaire, id_Question) values (?, ?, ?, ?, ?, ?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setString(1, reponse.getEvaluation());
		this.preparedStatement.setString(2, reponse.getAnomaliesObservées());
		this.preparedStatement.setString(3, reponse.getRespRéalisation());
		this.preparedStatement.setString(4, reponse.getActionsExigées());
		this.preparedStatement.setDate(5, reponse.getDateRéalisation());
		this.preparedStatement.setInt(6, reponse.getId_ReponseFormulaire());
		this.preparedStatement.setInt(7, reponse.getId_Question());

	
		this.preparedStatement.execute();
		
		
	}
	
	
	public ObservableList<ReponseQuestion> getAll() throws SQLException{
		
		ObservableList<ReponseQuestion> myList = FXCollections.observableArrayList();
		
		String request= "select * from reponse_question"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new ReponseQuestion(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),  resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getInt(7), resultSet.getInt(8)));
		}
		
		return myList;
	}
	
	
	
	public ObservableList<ReponseQuestion> getAll(Integer id_ReponseFormulaire) throws SQLException{
		
		ObservableList<ReponseQuestion> myList = FXCollections.observableArrayList();
		
		String request= "select * from reponse_question where id_ReponseFormulaire = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		 this.preparedStatement.setInt(1, id_ReponseFormulaire);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new ReponseQuestion(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),  resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getInt(7), resultSet.getInt(8)));
		}
		
		return myList;
	}
	
	
	public ReponseQuestion getOne(Integer id_ReponseFormulaire, Integer id_Question) throws SQLException{
		
		
		String request= "select * from reponse_question where id_ReponseFormulaire = ? and id_Question = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		 this.preparedStatement.setInt(1, id_ReponseFormulaire);
		 this.preparedStatement.setInt(2, id_Question);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		ReponseQuestion rq = null;
		
		while(resultSet.next()) {
			rq = new ReponseQuestion(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),  resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getInt(7), resultSet.getInt(8));
		}
		
		return rq;
	}
	
	
	public void update(ReponseQuestion reponse) throws SQLException{


		
		String request= "UPDATE reponse_question SET  Evaluation= ?,  AnomaliesObservées = ?,  ActionsExigées = ?,  RespRéalisation= ?, DateRéalisation = ?  WHERE id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setString(1, reponse.getEvaluation());
		this.preparedStatement.setString(2, reponse.getAnomaliesObservées());
		this.preparedStatement.setString(3, reponse.getRespRéalisation());
		this.preparedStatement.setString(4, reponse.getActionsExigées());
		this.preparedStatement.setDate(5, reponse.getDateRéalisation());
		this.preparedStatement.setInt(6, reponse.getId());
		
		this.preparedStatement.execute();

		
	}
	
	
	public Boolean check(int id_repform) throws SQLException {
		
		String request= "select * from reponse_question where  id_ReponseFormulaire = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        

        this.preparedStatement.setInt(1, id_repform);

		
        
        this.resultSet = this.preparedStatement.executeQuery();

		
        if(!this.resultSet.next()) return false;
		
		else return true;
		
		
	}

	
	
}
