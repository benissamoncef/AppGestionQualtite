package Transactions;

import java.sql.SQLException;

import Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmployeeTransaction extends BaseTransaction {

	public EmployeeTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void save(Employee object) throws SQLException {
		
			String request= "insert into employee (`matricule`, `nom`, `prenom`, `numero_telephone`, `email`, `mission`, `poste`, `commentaire`) values (?, ?, ?, ?, ?, ?, ?, ?)"; 
		
			this.preparedStatement = this.cnx.prepareStatement(request);
		
			this.preparedStatement.setInt(1, object.getMatricule());
			this.preparedStatement.setString(2, object.getNom());
			this.preparedStatement.setString(3, object.getPrenom());
			this.preparedStatement.setString(4, object.getNumero_telephone());
			this.preparedStatement.setString(5, object.getEmail());
			this.preparedStatement.setString(6, object.getMission());
			this.preparedStatement.setString(7, object.getPoste());
			this.preparedStatement.setString(8, object.getCommentaire());


				
			this.preparedStatement.execute();
			
		

	}

	
	public ObservableList<Employee> getAll() throws SQLException{
		
		ObservableList<Employee> myList = FXCollections.observableArrayList();
		
		String request= "select * from employee"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
		}
		
		return myList;
	}
	

	public void deleteByMatricule(Integer matricule) throws SQLException {
		
		String request= "delete from employee where matricule = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, matricule);
		
		this.preparedStatement.execute();	
		

	}
	
	
	public Employee getByNomPrenom(String nom, String prenom) throws SQLException {
		
		
		String request = "select * from employee where nom = ? and prenom = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setString(1, nom);
		
		this.preparedStatement.setString(2, prenom);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Employee e = null;
		
		while(resultSet.next()) {
			e = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
		}
		
		return e;

	}
	
	
	public void update(Employee object) throws SQLException {
		
		String request= "UPDATE `employee` SET `nom`= ?,`prenom`=?,`numero_telephone`=?,`email`=?,`mission`=?,`poste`=?,`commentaire`=? WHERE matricule = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
	
		this.preparedStatement.setString(1, object.getNom());
		this.preparedStatement.setString(2, object.getPrenom());
		this.preparedStatement.setString(3, object.getNumero_telephone());
		this.preparedStatement.setString(4, object.getEmail());
		this.preparedStatement.setString(5, object.getMission());
		this.preparedStatement.setString(6, object.getPoste());
		this.preparedStatement.setString(7, object.getCommentaire());
		this.preparedStatement.setInt(8, object.getMatricule());

	
		this.preparedStatement.execute();
		
		
		
	}
	
	public ObservableList<Employee> getAllAdmins() throws SQLException{
		
		ObservableList<Employee> myList = FXCollections.observableArrayList();
		
		String request= "select * from employee join compte on (compte.id_employee = employee.matricule and  compte.id_type_compte= 1)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
		}
		
		return myList;
	}
	
	
	public Employee getById(int id) throws SQLException{
				
		String request= "select * from employee where matricule = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Employee employee = null;
		
		while(resultSet.next()) {
			employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
		}
		
		return employee;
	}
	
	
	public Employee getEmpAdmin(int idEmpAdmin) throws SQLException{
		
		
		String request= "select * from employee join compte on (compte.id_employee = employee.matricule and  compte.id_type_compte= 1 and compte.id_administrateur = ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, idEmpAdmin);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		Employee employee = null;
		
		while(resultSet.next()) {
			employee = new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
		}
		
		return employee;
	}
	
	
	

}
