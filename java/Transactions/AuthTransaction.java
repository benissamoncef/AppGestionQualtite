package Transactions;

import java.sql.SQLException;

import Models.Auth;

public class AuthTransaction extends BaseTransaction {

	public AuthTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void save(Auth auth) throws SQLException {

			
			String request= "insert into auth (id_compte) values (?)"; 
			
			this.preparedStatement = this.cnx.prepareStatement(request);
			
			
			this.preparedStatement.setInt(1, auth.getIdCompte());
					
			this.preparedStatement.execute();
			
			}
			
	
	

}
