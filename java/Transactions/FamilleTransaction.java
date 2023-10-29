package Transactions;


import java.sql.SQLException;

import Models.Famille;
import Models.Projet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FamilleTransaction extends BaseTransaction {
    public FamilleTransaction() throws SQLException {
        super();
    }
    public void save(Famille famille) throws SQLException {

        String request= "insert into famille (codeFamilleInterne, codeFamilleExterne ,commentaire, idProjet) values (?,?,?,?)";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setString(1, famille.getCodeFamilleInterne());
        this.preparedStatement.setString(2, famille.getCodeFamilleClient());
        this.preparedStatement.setString(3, famille.getCommentaire());
        this.preparedStatement.setInt(4, famille.getIdProjet());

        this.preparedStatement.execute();


    }

    public ObservableList<Famille> getAll() throws SQLException{

        ObservableList<Famille> myList = FXCollections.observableArrayList();

        String request= "select * from famille";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.resultSet = this.preparedStatement.executeQuery();

        while(resultSet.next()) {

         Famille famille = new Famille(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),resultSet.getDate(5),resultSet.getInt(6));
         Projet projet = (new ProjetTransaction().getById(resultSet.getInt(6)));
         famille.setProjet(projet);
         if(projet!=null) {
        	 
        	 famille.setLabelProjet(projet.getLabelProjet());
         }
         myList.add(famille);

        }

        return myList;
    }

    public void deleteById(int idFamille) throws SQLException {

        String request= "delete from famille where idFamille = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setInt(1, idFamille);

        this.preparedStatement.execute();


    }

    public void update(Famille famille) throws SQLException {

        String request= "UPDATE famille SET codeFamilleInterne =?,codeFamilleExterne=? ,commentaire=? WHERE idFamille =?";

        this.preparedStatement = this.cnx.prepareStatement(request);
        this.preparedStatement.setString(1, famille.getCodeFamilleInterne());
        this.preparedStatement.setString(2, famille.getCodeFamilleClient());
        this.preparedStatement.setString(3, famille.getCommentaire());
        this.preparedStatement.setInt(4, famille.getIdFamille());
        this.preparedStatement.execute();

    }

    public Famille getByEPN(String epn) throws SQLException {

        String request= "select * from famille where codeFamilleInterne = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setString(1, epn);

        this.resultSet = this.preparedStatement.executeQuery();

        Famille famille = null;

        while(resultSet.next()) {
            famille = new Famille(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5),resultSet.getInt(6));
        }

        return famille;

    }

    public Famille getById(int idFamille) throws SQLException {

        String request= "select * from famille where idFamille = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setInt(1, idFamille);

        this.resultSet = this.preparedStatement.executeQuery();

        Famille famille = null;

        while(resultSet.next()) {
            famille = new Famille(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6));
        }

        return famille;

    }
    
    public Famille getByCode(int id_projet, String CodeFamille)  throws SQLException{
		
		
		String request= "select * from famille join projet on (projet.id = famille.idProjet and projet.id = ? and famille.codeFamilleInterne = ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
	
		this.preparedStatement.setInt(1, id_projet);
		this.preparedStatement.setString(2, CodeFamille);
				
		this.resultSet = this.preparedStatement.executeQuery();
		
		Famille f = null;
		
		while(resultSet.next()) {
			f = new Famille(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6));
		}
		
	
		return f;
		
	}
    
    
    public ObservableList<Famille> getAll(int id_projet)  throws SQLException{
		
		ObservableList<Famille> myList = FXCollections.observableArrayList();
		
		String request= "select * from famille where idProjet = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id_projet);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Famille(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6)));
		}
		
		return myList;
	}

    
    

}
