package Transactions;

import java.sql.SQLException;
import Models.ReponseControles;
import Models.ReponseFormulaire;


public class ReponseControlesTransaction extends BaseTransaction{

	public ReponseControlesTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void save(ReponseControles reponse) throws SQLException {
		


	        String request= "insert into reponse_controles (id_reponse_formulaire, q1 , q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, serialNumbers) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	        this.preparedStatement = this.cnx.prepareStatement(request);

	        this.preparedStatement.setInt(1, reponse.getId_reponse_formulaire());
	        
	        
	        this.preparedStatement.setInt(2, reponse.getQ1());
	        this.preparedStatement.setInt(3, reponse.getQ2());
	        this.preparedStatement.setInt(4, reponse.getQ3());
	        this.preparedStatement.setInt(5, reponse.getQ4());
	        this.preparedStatement.setInt(6, reponse.getQ5());
	        this.preparedStatement.setInt(7, reponse.getQ6());
	        this.preparedStatement.setInt(8, reponse.getQ7());
	        this.preparedStatement.setInt(9, reponse.getQ8());
	        this.preparedStatement.setInt(10, reponse.getQ9());
	        this.preparedStatement.setInt(11, reponse.getQ10());
	        
	        
	        
	        this.preparedStatement.setString(12, reponse.getQ11());
	        this.preparedStatement.setString(13, reponse.getQ12());
	        this.preparedStatement.setString(14, reponse.getQ13());
	        this.preparedStatement.setString(15, reponse.getQ14());
	        this.preparedStatement.setString(16, reponse.getQ15());
	        this.preparedStatement.setString(17, reponse.getQ16());
	        this.preparedStatement.setBoolean(18, reponse.getQ17());
	        this.preparedStatement.setBoolean(19, reponse.getQ18());
	        this.preparedStatement.setBoolean(20, reponse.getQ19());
	        
	        
	        this.preparedStatement.setString(21, reponse.getSerialNumbers());
	        
	        
	        this.preparedStatement.execute();


	}

	public void update(ReponseControles reponse) throws SQLException {
		String request= "UPDATE reponse_controles SET " +
				"q1=?, q2=? , q3=? , q4=? ," +
				" q5=? , q6=? , q7=? , q8=? , " +
				"q9=? , q10=? , q11=? , q12=? , " +
				"q13=? , q14=? , q15=? , q16=? , " +
				"q17=? , q18=? , q19=? , SerialNumbers =?" +
				"WHERE id =?";

		System.out.println(reponse.toString());
		this.preparedStatement = this.cnx.prepareStatement(request);

		this.preparedStatement.setInt(1, reponse.getQ1());
		this.preparedStatement.setInt(2, reponse.getQ2());
		this.preparedStatement.setInt(3, reponse.getQ3());
		this.preparedStatement.setInt(4, reponse.getQ4());
		this.preparedStatement.setInt(5, reponse.getQ5());


		this.preparedStatement.setInt(6, reponse.getQ6());
		this.preparedStatement.setInt(7, reponse.getQ7());
		this.preparedStatement.setInt(8, reponse.getQ8());
		this.preparedStatement.setInt(9, reponse.getQ9());
		this.preparedStatement.setInt(10,reponse.getQ10());

		this.preparedStatement.setString(11, reponse.getQ11());
		this.preparedStatement.setString(12, reponse.getQ12());
		this.preparedStatement.setString(13, reponse.getQ13());
		this.preparedStatement.setString(14, reponse.getQ14());
		this.preparedStatement.setString(15, reponse.getQ15());
		this.preparedStatement.setString(16, reponse.getQ16());

		this.preparedStatement.setBoolean(17, reponse.getQ17());
		this.preparedStatement.setBoolean(18, reponse.getQ18());
		this.preparedStatement.setBoolean(19, reponse.getQ19());

		this.preparedStatement.setString(20,reponse.getSerialNumbers());
		this.preparedStatement.setInt(21,reponse.getId());

		System.out.println(preparedStatement.toString());
		this.preparedStatement.execute();

	}

	public ReponseControles getByIdRF(ReponseFormulaire reponseFormulaire) throws SQLException {




		String request= "select * from reponse_controles where id_reponse_formulaire = ?";

		this.preparedStatement = this.cnx.prepareStatement(request);

		this.preparedStatement.setInt(1, reponseFormulaire.getId());

		this.resultSet = this.preparedStatement.executeQuery();

		ReponseControles reponseControles = null;

		while(resultSet.next()) {

					reponseControles = new ReponseControles(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),
					resultSet.getInt(6), resultSet.getInt(7), resultSet.getInt(8), resultSet.getInt(9), resultSet.getInt(10),resultSet.getInt(11), resultSet.getInt(12)
					,resultSet.getString(13), resultSet.getString(14), resultSet.getString(15), resultSet.getString(16), resultSet.getString(17),
					resultSet.getString(18), resultSet.getBoolean(19),resultSet.getBoolean(20), resultSet.getBoolean(21),resultSet.getString(22));
		}

		return reponseControles;

	}






}
