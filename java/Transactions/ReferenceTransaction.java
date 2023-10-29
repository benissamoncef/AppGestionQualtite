package Transactions;


import java.sql.SQLException;

import Models.Famille;
import Models.Projet;
import Models.Reference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReferenceTransaction extends BaseTransaction {

    public ReferenceTransaction() throws SQLException {
        super();
    }

    public void save(Reference reference) throws SQLException {

        String request= "insert into reference (codeRefInt, codeRefCli ,commentaireRef, idProjet, idFamille) values (?,?,?,?,?)";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setString(1, reference.getCodeReferenceInterne());
        this.preparedStatement.setString(2, reference.getCodeReferenceClient());
        this.preparedStatement.setString(3, reference.getCommentaire());
        this.preparedStatement.setInt(4, reference.getProjet().getId());
        this.preparedStatement.setInt(5, reference.getFamille().getIdFamille());
        this.preparedStatement.execute();


    }

    public ObservableList<Reference> getAll() throws SQLException{

        ObservableList<Reference> myList = FXCollections.observableArrayList();

        String request= "select * from reference";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.resultSet = this.preparedStatement.executeQuery();

        while(resultSet.next()) {

            Reference reference = new Reference(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getString(4),resultSet.getDate(5),resultSet.getInt(6),resultSet.getInt(7));
            Projet projet = (new ProjetTransaction().getById(resultSet.getInt(6)));
            Famille famille = (new FamilleTransaction().getById(resultSet.getInt(7)));
            if(famille!=null){
                reference.setLabelFamille(famille.getCodeFamilleInterne());
            }
       
            reference.setLabelProjet(projet.getLabelProjet());
            reference.setProjet(projet);
            reference.setFamille(famille);
            myList.add(reference);

        }

        return myList;
    }

    public void deleteById(int idReference) throws SQLException {

        String request= "delete from reference where idReference = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setInt(1, idReference);

        this.preparedStatement.execute();


    }
    
    public Reference getByCode(String Reference) throws SQLException {

        String request= "select * from reference where codeRefInt = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setString(1, Reference);

        this.resultSet = this.preparedStatement.executeQuery();

        Reference reference = null;

        while(resultSet.next()) {
            reference = new Reference(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getInt(7));
        }

        return reference;

    }
    
    

    public void update(Reference reference) throws SQLException {

        String request= "UPDATE reference SET codeRefInt =?,codeRefCli=? ,commentaireRef=?,idProjet=?,idFamille=? WHERE idReference =?";

        this.preparedStatement = this.cnx.prepareStatement(request);
        this.preparedStatement.setString(1, reference.getCodeReferenceInterne());
        this.preparedStatement.setString(2, reference.getCodeReferenceClient());
        this.preparedStatement.setString(3, reference.getCommentaire());
        this.preparedStatement.setInt(4, reference.getIdProjet());
        this.preparedStatement.setInt(5, reference.getIdFamille());
        this.preparedStatement.setInt(6, reference.getIdReference());
        this.preparedStatement.execute();

    }

    public Reference getById(int idRef) throws SQLException {

        String request= "select * from reference where idReference = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setInt(1, idRef);

        this.resultSet = this.preparedStatement.executeQuery();

        Reference reference = null;

        while(resultSet.next()) {
            reference = new Reference(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getInt(7));
        }

        return reference;

    }
    
    public ObservableList<String> getAllReferences() throws SQLException {
    	
    	 ObservableList<String> myList = FXCollections.observableArrayList();

         String request= "select * from reference";

         this.preparedStatement = this.cnx.prepareStatement(request);

         this.resultSet = this.preparedStatement.executeQuery();

         while(resultSet.next()) {
         
             myList.add(resultSet.getString(2));

         }

         return myList;
    	
    	
    }
    
    

	public Reference getByCode(int id_projet, int id_famille, String Nom)  throws SQLException{
		
		
		String request= "select * from reference join famille on (reference.idFamille = famille.idFamille and famille.idFamille = ? and reference.codeRefInt = ?)  join projet on (projet.id = famille.idProjet and projet.id = ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		Reference r = null;
		
		this.preparedStatement.setInt(1, id_famille);
		this.preparedStatement.setString(2, Nom);
		this.preparedStatement.setInt(3, id_projet);
		
				
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			r = new Reference(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getInt(7));
		}
		
		return r;		
		
	}
	
	
	public ObservableList<Reference> getAll(int id_projet, int id_famille)  throws SQLException{
		
		ObservableList<Reference> myList = FXCollections.observableArrayList();
		
		String request= "select * from reference where  idFamille = ? AND idProjet = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, id_famille);
		this.preparedStatement.setInt(2, id_projet);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		while(resultSet.next()) {
			myList.add(new Reference(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDate(5), resultSet.getInt(6), resultSet.getInt(7)));
		}
		
		
		
		return myList;
	}
	
    
    

}
