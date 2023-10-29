package Transactions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public abstract class BaseTransaction {
	
	
	protected Connection cnx ;
	
	protected Statement statement ;
	
	
	protected PreparedStatement preparedStatement ;
	
	
	protected ResultSet resultSet ;
	
	private String url = "jdbc:mysql://localhost:3306/testsgq";
	private String login = "root";
	private String pass = "";
	
	
	public BaseTransaction() throws SQLException {
		
		this.cnx = DriverManager.getConnection(url, login, pass);
	
		
	}
	

}
