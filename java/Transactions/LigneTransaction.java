package Transactions;



import java.sql.SQLException;

import Models.Ligne;
import Models.Zone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LigneTransaction extends BaseTransaction{


    public LigneTransaction() throws SQLException {
        super();
    }

    public void save(Ligne ligne) throws SQLException {

        String request= "insert into ligne (labelLigne, commentaire, idZone) values (?, ? , ?)";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setString(1, ligne.getLabelLigne());
        this.preparedStatement.setString(2, ligne.getCommentaire());
        this.preparedStatement.setInt(3, ligne.getId_zone());

        this.preparedStatement.execute();


    }

    public ObservableList<Ligne> getAll() throws SQLException{

        ObservableList<Ligne> myList = FXCollections.observableArrayList();

        String request= "select * from ligne";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.resultSet = this.preparedStatement.executeQuery();

        Zone zone = null;
        
        while(resultSet.next()) {

             zone = new ZoneTransaction().getById(resultSet.getInt(4));
             if(zone != null)
             myList.add(new Ligne(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getInt(4), zone.getLabelZone()));
        }
        return myList;
    }
    
    public ObservableList<Ligne> getAllByZone(int id_zone) throws SQLException{

        ObservableList<Ligne> myList = FXCollections.observableArrayList();

        String request= "select * from ligne where idZone = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);
        
        this.preparedStatement.setInt(1, id_zone);

        this.resultSet = this.preparedStatement.executeQuery();

        while(resultSet.next()) {              
            myList.add(new Ligne(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getInt(4)));
        }
        
        return myList;
    }
    

    public void deleteById(int id) throws SQLException {

        String request= "delete from ligne where idLigne = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setInt(1, id);

        this.preparedStatement.execute();


    }

    public void update(Ligne ligne) throws SQLException {

        String request= "UPDATE ligne SET labelLigne =?,commentaire=?, idZone= ? WHERE idLigne =?";

        this.preparedStatement = this.cnx.prepareStatement(request);
        this.preparedStatement.setString(1, ligne.getLabelLigne());
        this.preparedStatement.setString(2, ligne.getCommentaire());
        this.preparedStatement.setInt(3, ligne.getId_zone());
        this.preparedStatement.setInt(4, ligne.getIdLigne());

        this.preparedStatement.execute();

    }

    public Ligne getByMatriculeLigne(int id_zone, String matricule) throws SQLException {

        String request= "select * from ligne where labelLigne = ? and idZone = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setString(1, matricule);
        this.preparedStatement.setInt(2, id_zone);

        this.resultSet = this.preparedStatement.executeQuery();

        Ligne ligne = null;

        while(resultSet.next()) {
            ligne = new Ligne(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
        }

        return ligne;

    }

    public Ligne getById(int idLigne) throws SQLException {

        String request= "select * from ligne where idLigne = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setInt(1, idLigne);

        this.resultSet = this.preparedStatement.executeQuery();

        Ligne ligne = null;

        while(resultSet.next()) {
            ligne = new Ligne(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
        }

        return ligne;

    }


}
