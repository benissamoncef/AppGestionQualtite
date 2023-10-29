package Transactions;

import java.sql.SQLException;

import Models.Shift;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShiftTransaction extends BaseTransaction {

	public ShiftTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void save(Shift shift) throws SQLException {
		
		String request= "insert into shift (LabelShift, startShift, endShift, commentaire) values (?, ?, ?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setString(1, shift.getLabelShift());
		this.preparedStatement.setTime(2, shift.getStartShift());
		this.preparedStatement.setTime(3, shift.getEndShift());	
		this.preparedStatement.setString(4, shift.getCommentaire());	
		
		this.preparedStatement.execute();
		
		
	}
	
	public ObservableList<Shift> getAll() throws SQLException{
		
		ObservableList<Shift> myList = FXCollections.observableArrayList();
		
		String request= "select * from shift"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Shift(resultSet.getInt(1), resultSet.getString(2), resultSet.getTime(3), resultSet.getTime(4),resultSet.getString(5)));
		}
		
		return myList;
	}
	
	public void deleteById(int id) throws SQLException {
		
		String request= "delete from shift where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.preparedStatement.execute();	
		
		
	}
	
	public void update(Shift object) throws SQLException {
	
		String request= "UPDATE shift SET  LabelShift = ?, StartShift= ?, EndShift = ?, commentaire = ? WHERE id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
	
		this.preparedStatement.setString(1, object.getLabelShift());
		this.preparedStatement.setTime(2, object.getStartShift());
		this.preparedStatement.setTime(3, object.getEndShift());
		this.preparedStatement.setString(4, object.getCommentaire());
		this.preparedStatement.setInt(5, object.getId());

		this.preparedStatement.execute();
		
		
		
	}
	
	public Shift getByLabelShift(String lblshift) throws SQLException {
		
		String request= "select * from shift where LabelShift = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setString(1, lblshift);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Shift shift = null;
		
		while(resultSet.next()) {
			shift = new Shift(resultSet.getInt(1), resultSet.getString(2), resultSet.getTime(3), resultSet.getTime(4),resultSet.getString(5));
		}
		
		return shift;
		
	}
	
	public Shift getById(int id) throws SQLException {
		
		String request= "select * from shift where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Shift shift = null;
		
		while(resultSet.next()) {
			shift = new Shift(resultSet.getInt(1), resultSet.getString(2), resultSet.getTime(3), resultSet.getTime(4),resultSet.getString(5));
		}
		
		return shift;
		
	}
	
	
	

}
