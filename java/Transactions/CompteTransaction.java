package Transactions;

import java.sql.SQLException;

import Models.Compte;
import Models.Employee;
import Models.TypeCompte;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CompteTransaction extends BaseTransaction {

	public CompteTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void save(Compte compte) throws SQLException {
		
		String request= "insert into compte (`username`, `password`, `is_online`, `id_employee`, `id_administrateur`, `id_type_compte`) values (?, ?, ?, ?, ?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
			
		this.preparedStatement.setString(1, compte.getUsername());
		this.preparedStatement.setString(2, compte.getPassword());
		this.preparedStatement.setBoolean(3, compte.getIs_online());
		this.preparedStatement.setInt(4, compte.getId_employee());
		this.preparedStatement.setInt(5, compte.getId_Administrateur());
		this.preparedStatement.setInt(6, compte.getIdTypecompte());
				
		this.preparedStatement.execute();
		
		
	}
	
	
	
	
	public Compte getByLogIn(String username, String password) throws SQLException {
		
		
		
		String request = "SELECT * FROM compte WHERE username = ? AND password = ?";
			
		this.preparedStatement = this.cnx.prepareStatement(request);
				
		this.preparedStatement.setString(1, username);
		this.preparedStatement.setString(2, password);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Compte c = null;
		
		while(resultSet.next()) {
			c = new Compte(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), 
					resultSet.getBoolean(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9));
		}
		
		return c;
		
		
	}
	
	
	public void deleteById(int id) throws SQLException {
		
		String request= "delete from compte where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.preparedStatement.execute();	
		
		
	}
	
	public ObservableList<Compte> getAll() throws SQLException{
		
		ObservableList<Compte> myList = FXCollections.observableArrayList();
		
		String request= "select * from compte"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		TypeCompte type = null;
		Employee e = null;
		Employee admin = null;

		while(resultSet.next()) {
			
			e = new EmployeeTransaction().getById(resultSet.getInt(7));
			admin = new EmployeeTransaction().getEmpAdmin(resultSet.getInt(8));
			type = new TypeCompteTransaction().getById(resultSet.getInt(9));
			if(e!= null && type != null) {
				if(admin != null )
					myList.add(new Compte(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getBoolean(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), type.getLabelType(), admin.getNom()+"\t"+admin.getPrenom(), e.getNom()+"\t"+e.getPrenom()));
				else
					myList.add(new Compte(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getBoolean(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), type.getLabelType(), "None", e.getNom()+"\t"+e.getPrenom()));
			}
		}
		
		return myList;
	}
	
	public void update(Compte object) throws SQLException {
		
		String request= "UPDATE `compte` SET ` username `= ?,`password`=?,`id_administrateur`=?,`id_type_compte`=? WHERE id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
	
		this.preparedStatement.setString(1, object.getUsername());
		this.preparedStatement.setString(2, object.getPassword());
		this.preparedStatement.setInt(3, object.getId_Administrateur());
		this.preparedStatement.setInt(4, object.getIdTypecompte());
		this.preparedStatement.setInt(5, object.getId());
	
		this.preparedStatement.execute();
		
		
		
	}
	
	
	public ObservableList<Compte> getAllAdmins() throws SQLException{
		
		ObservableList<Compte> myList = FXCollections.observableArrayList();
		
		String request= "select * from compte where id_type_compte = 1"; 

		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Compte(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getBoolean(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9)));
		}
		
		return myList;
	}
	
	
	public Compte getById(int id) throws SQLException{
		
		
		String request= "select * from compte where id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);

		Compte c = null;
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			c = new Compte(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getBoolean(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9));
		}
		
		return c;
	}
	
	
	public ObservableList<Compte> getAllTechsByAdmin(int id) throws SQLException{
		
		ObservableList<Compte> myList = FXCollections.observableArrayList();
		
		String request= "select * from compte where id_administrateur = ? and id_type_compte = 3"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.resultSet = this.preparedStatement.executeQuery();


		while(resultSet.next()) {
				
				myList.add(new Compte(resultSet.getInt(1), resultSet.getDate(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5), resultSet.getBoolean(6), resultSet.getInt(7), id, 3));			
			
		}
		
		return myList;
	}

	
	
	
	

}
