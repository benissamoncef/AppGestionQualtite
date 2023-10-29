package Transactions;

import java.sql.SQLException;

import Models.ReponseAProdTableau;
import Models.ReponseFormulaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReponseAProdTableauTransaction extends BaseTransaction{


	  public ReponseAProdTableauTransaction() throws SQLException {
	        super();
	    }

	    /*----------------------------------TAP---------------------------------*/
	    public ObservableList<ReponseAProdTableau> getAllTAP(ReponseFormulaire reponseFormulaire) throws SQLException{

	        ObservableList<ReponseAProdTableau> myList = FXCollections.observableArrayList();

	        String request= "select * from reponse_tableaux where idReponse=? and isTAP=True";

	        this.preparedStatement = this.cnx.prepareStatement(request);
			this.preparedStatement.setInt(1, reponseFormulaire.getId());
			this.resultSet = this.preparedStatement.executeQuery();

	        while(resultSet.next()) {

	        	ReponseAProdTableau reponseTableaux = new ReponseAProdTableau(

	                    resultSet.getInt(1),
	                    resultSet.getInt(2),
	                    resultSet.getInt(3),
	                    resultSet.getInt(4),
	                    resultSet.getInt(5),
	                    resultSet.getInt(6),
	                    resultSet.getInt(7),
	                    resultSet.getInt(8),
	                    resultSet.getInt(9),
	                    resultSet.getString(10),
	                    resultSet.getString(11),
	                    resultSet.getString(12),
	                    resultSet.getString(13),
	                    null, null, null, null, null, null, null, null,
	                    resultSet.getInt(22),
	                    resultSet.getInt(23),
	                    resultSet.getBoolean(24));

	            myList.add(reponseTableaux);

	        }

	        return myList;
	    }
		public ReponseAProdTableau getTapByQ(ReponseFormulaire reponseFormulaire,int idQuestion) throws SQLException {

			ReponseAProdTableau reponseAProdTableau = null;

			String request = "select * from reponse_tableaux where idRF = ? and idQuestion= ? and isTAP = true";
			this.preparedStatement = this.cnx.prepareStatement(request);

			this.preparedStatement.setInt(1, reponseFormulaire.getId());
			this.preparedStatement.setInt(2, idQuestion);

			this.resultSet = this.preparedStatement.executeQuery();

			while(resultSet.next()) {
				reponseAProdTableau = new ReponseAProdTableau(

						resultSet.getInt(1),
						resultSet.getInt(2),
						resultSet.getInt(3),
						resultSet.getInt(4),
						resultSet.getInt(5),
						resultSet.getInt(6),
						resultSet.getInt(7),
						resultSet.getInt(8),
						resultSet.getInt(9),
						resultSet.getString(10),
						resultSet.getString(11),
						resultSet.getString(12),
						resultSet.getString(13),
						null, null, null, null, null, null, null, null,
						resultSet.getInt(22),
						resultSet.getInt(23),
						resultSet.getBoolean(24));
			}



			return reponseAProdTableau;
		}
	    public ReponseAProdTableau getTapByRF(ReponseFormulaire reponseFormulaire) throws SQLException {

		ReponseAProdTableau reponseAProdTableau = null;

		String request = "select * from reponse_tableaux where idRF = ? and isTAP = true";
		this.preparedStatement = this.cnx.prepareStatement(request);

		this.preparedStatement.setInt(1, reponseFormulaire.getId());

		this.resultSet = this.preparedStatement.executeQuery();

		while(resultSet.next()) {
			reponseAProdTableau = new ReponseAProdTableau(

					resultSet.getInt(1),
					resultSet.getInt(2),
					resultSet.getInt(3),
					resultSet.getInt(4),
					resultSet.getInt(5),
					resultSet.getInt(6),
					resultSet.getInt(7),
					resultSet.getInt(8),
					resultSet.getInt(9),
					resultSet.getString(10),
					resultSet.getString(11),
					resultSet.getString(12),
					resultSet.getString(13),
					null, null, null, null, null, null, null, null,
					resultSet.getInt(22),
					resultSet.getInt(23),
					resultSet.getBoolean(24));
		}



		return reponseAProdTableau;
	}

	    /*----------------------------------TPP---------------------------------*/

	    public ObservableList<ReponseAProdTableau> getAllTPP() throws SQLException{

	        ObservableList<ReponseAProdTableau> myList = FXCollections.observableArrayList();

	        String request= "select * from reponse_tableaux where isTAP=False";

	        this.preparedStatement = this.cnx.prepareStatement(request);

	        this.resultSet = this.preparedStatement.executeQuery();

	        while(resultSet.next()) {

	        	ReponseAProdTableau reponseTableaux = new ReponseAProdTableau(

	                    resultSet.getInt(1),
	                    resultSet.getInt(2),
	                    resultSet.getInt(3),
	                    resultSet.getInt(4),
	                    resultSet.getInt(5),
	                    resultSet.getInt(6),
	                    resultSet.getInt(7),
	                    resultSet.getInt(8),
	                    resultSet.getInt(9),
	                    resultSet.getString(10),
	                    resultSet.getString(11),
	                    resultSet.getString(12),
	                    resultSet.getString(13),
	                    resultSet.getString(14),
	                    resultSet.getString(15),
	                    resultSet.getString(16),
	                    resultSet.getString(17),
	                    resultSet.getString(18),
	                    resultSet.getString(19),
	                    resultSet.getString(20),
	                    resultSet.getString(21),
	                    resultSet.getInt(22),
	                    resultSet.getInt(23),
	                    resultSet.getBoolean(24));

	            myList.add(reponseTableaux);

	        }

	        return myList;
	    }
		public ReponseAProdTableau getTppByRF(ReponseFormulaire reponse) throws SQLException {

		ReponseAProdTableau reponseAProdTableau = null;

		String request = "select * from reponse_tableaux where idRF = ? and isTAP = false";
		this.preparedStatement = this.cnx.prepareStatement(request);

		this.preparedStatement.setInt(1, reponse.getId());

		this.resultSet = this.preparedStatement.executeQuery();

		while(resultSet.next()) {
			reponseAProdTableau = new ReponseAProdTableau(

					resultSet.getInt(1),
					resultSet.getInt(2),
					resultSet.getInt(3),
					resultSet.getInt(4),
					resultSet.getInt(5),
					resultSet.getInt(6),
					resultSet.getInt(7),
					resultSet.getInt(8),
					resultSet.getInt(9),
					resultSet.getString(10),
					resultSet.getString(11),
					resultSet.getString(12),
					resultSet.getString(13),
					resultSet.getString(14),
					resultSet.getString(15),
					resultSet.getString(16),
					resultSet.getString(17),
					resultSet.getString(18),
					resultSet.getString(19),
					resultSet.getString(20),
					resultSet.getString(21),
					resultSet.getInt(22),
					resultSet.getInt(23),
					resultSet.getBoolean(24));
		}



		return reponseAProdTableau;
	}
		public ReponseAProdTableau getTppByQ(ReponseFormulaire reponseFormulaire,int idQuestion) throws SQLException {

		ReponseAProdTableau reponseAProdTableau = null;

		String request = "select * from reponse_tableaux where idRF = ? and idQuestion= ? and isTAP = false";
		this.preparedStatement = this.cnx.prepareStatement(request);

		this.preparedStatement.setInt(1, reponseFormulaire.getId());
		this.preparedStatement.setInt(2, idQuestion);

		this.resultSet = this.preparedStatement.executeQuery();

		while(resultSet.next()) {
			reponseAProdTableau = new ReponseAProdTableau(

					resultSet.getInt(1),
					resultSet.getInt(2),
					resultSet.getInt(3),
					resultSet.getInt(4),
					resultSet.getInt(5),
					resultSet.getInt(6),
					resultSet.getInt(7),
					resultSet.getInt(8),
					resultSet.getInt(9),
					resultSet.getString(10),
					resultSet.getString(11),
					resultSet.getString(12),
					resultSet.getString(13),
					null, null, null, null, null, null, null, null,
					resultSet.getInt(22),
					resultSet.getInt(23),
					resultSet.getBoolean(24));
		}



		return reponseAProdTableau;
	}

	    /*----------------------------------GENERAL---------------------------------*/

	    public void deleteById(int idReponseQuestion) throws SQLException {

	        String request= "delete from reponse_tableaux where idReponse = ?";

	        this.preparedStatement = this.cnx.prepareStatement(request);

	        this.preparedStatement.setInt(1, idReponseQuestion);

	        this.preparedStatement.execute();


	    }
	    public void save(ReponseAProdTableau reponseTableaux) throws SQLException {

	        String request= "insert into reponse_tableaux (idRef1, idRef2 ,idRef3, idRef4,idDD1,idDD2,idDD3,idDD4,statut1,statut2,statut3,statut4,sn1,sn2,sn3,sn4,nt1,nt2,nt3,nt4,idQuestion,idRF,isTAP) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	        this.preparedStatement = this.cnx.prepareStatement(request);

	        this.preparedStatement.setInt(1, reponseTableaux.getIdRef1());
	        this.preparedStatement.setInt(2, reponseTableaux.getIdRef2());
	        this.preparedStatement.setInt(3, reponseTableaux.getIdRef3());
	        this.preparedStatement.setInt(4, reponseTableaux.getIdRef4());


	        this.preparedStatement.setInt(5, reponseTableaux.getIdDD1());
	        this.preparedStatement.setInt(6, reponseTableaux.getIdDD2());
	        this.preparedStatement.setInt(7, reponseTableaux.getIdDD3());
	        this.preparedStatement.setInt(8, reponseTableaux.getIdDD4());


	        this.preparedStatement.setString(9, reponseTableaux.getStatut1());
	        this.preparedStatement.setString(10, reponseTableaux.getStatut2());
	        this.preparedStatement.setString(11, reponseTableaux.getStatut3());
	        this.preparedStatement.setString(12, reponseTableaux.getStatut4());


	        this.preparedStatement.setString(13, reponseTableaux.getSn1());
	        this.preparedStatement.setString(14, reponseTableaux.getSn2());
	        this.preparedStatement.setString(15, reponseTableaux.getSn3());
	        this.preparedStatement.setString(16, reponseTableaux.getSn4());


	        this.preparedStatement.setString(17, reponseTableaux.getNt1());
	        this.preparedStatement.setString(18, reponseTableaux.getNt2());
	        this.preparedStatement.setString(19, reponseTableaux.getNt3());
	        this.preparedStatement.setString(20, reponseTableaux.getSn4());

	        this.preparedStatement.setInt(21, reponseTableaux.getIdQuestion());
	        this.preparedStatement.setInt(22, reponseTableaux.getIdRF());
	        this.preparedStatement.setBoolean(23,reponseTableaux.isTAP());

	        this.preparedStatement.execute();


	    }
	    public void update(ReponseAProdTableau reponseTableaux) throws SQLException {

	        String request= "UPDATE reponse_tableaux SET " +
	                "idRef1=?, idRef2=? ,idRef3=?, idRef4=?," +
	                "idDD1=?,idDD2=?,idDD3=?,idDD4=?," +
	                "statut1=?,statut2=?,statut3=?,statut4=?," +
	                "sn1=?,sn2=?,sn3=?,sn4=?," +
	                "nt1=?,nt2=?,nt3=?,nt4=? WHERE idReponse =?";

	        this.preparedStatement = this.cnx.prepareStatement(request);

	        this.preparedStatement.setInt(1, reponseTableaux.getIdRef1());
	        this.preparedStatement.setInt(2, reponseTableaux.getIdRef2());
	        this.preparedStatement.setInt(3, reponseTableaux.getIdRef3());
	        this.preparedStatement.setInt(4, reponseTableaux.getIdRef4());


	        this.preparedStatement.setInt(5, reponseTableaux.getIdDD1());
	        this.preparedStatement.setInt(6, reponseTableaux.getIdDD2());
	        this.preparedStatement.setInt(7, reponseTableaux.getIdDD3());
	        this.preparedStatement.setInt(8, reponseTableaux.getIdDD4());


	        this.preparedStatement.setString(9, reponseTableaux.getStatut1());
	        this.preparedStatement.setString(10, reponseTableaux.getStatut2());
	        this.preparedStatement.setString(11, reponseTableaux.getStatut3());
	        this.preparedStatement.setString(12, reponseTableaux.getStatut4());


	        this.preparedStatement.setString(13, reponseTableaux.getSn1());
	        this.preparedStatement.setString(14, reponseTableaux.getSn2());
	        this.preparedStatement.setString(15, reponseTableaux.getSn3());
	        this.preparedStatement.setString(16, reponseTableaux.getSn4());


	        this.preparedStatement.setString(17, reponseTableaux.getNt1());
	        this.preparedStatement.setString(18, reponseTableaux.getNt2());
	        this.preparedStatement.setString(19, reponseTableaux.getNt3());
	        this.preparedStatement.setString(20, reponseTableaux.getSn4());

	        this.preparedStatement.setInt(21,reponseTableaux.getIdReponse());

	        this.preparedStatement.execute();

	    }


}
