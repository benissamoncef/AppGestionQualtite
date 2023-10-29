package Transactions;

import java.sql.SQLException;

import Models.Zone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ZoneTransaction extends BaseTransaction {

    public ZoneTransaction() throws SQLException {
        super();
    }

    public void save(Zone zone) throws SQLException {

        String request= "insert into zone (labelZone, commentaireZone) values (?, ?)";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setString(1, zone.getLabelZone());
        this.preparedStatement.setString(2, zone.getCommentaire());

        this.preparedStatement.execute();


    }

    public ObservableList<Zone> getAll() throws SQLException{

        ObservableList<Zone> myList = FXCollections.observableArrayList();

        String request= "select * from zone";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.resultSet = this.preparedStatement.executeQuery();

        while(resultSet.next()) {
            myList.add(new Zone(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
        }

        return myList;
    }

    public void deleteById(int id) throws SQLException {

        String request= "delete from zone where idZone = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setInt(1, id);

        this.preparedStatement.execute();


    }

    public void update(Zone zone) throws SQLException {

        String request= "UPDATE zone SET labelZone =?,commentaireZone=? WHERE idZone =?";

       this.preparedStatement = this.cnx.prepareStatement(request);
        this.preparedStatement.setString(1, zone.getLabelZone());
        this.preparedStatement.setString(2, zone.getCommentaire());
        this.preparedStatement.setInt(3, zone.getIdZone());

        this.preparedStatement.execute();

    }

    public Zone getByLabelZone(String labelZone) throws SQLException {

        String request= "select * from zone where labelZone = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setString(1, labelZone);

        this.resultSet = this.preparedStatement.executeQuery();

        Zone zone = null;

        while(resultSet.next()) {
            zone = new Zone(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        }

        return zone;

    }

    public Zone getById(int idZone) throws SQLException {

        String request= "select * from zone where idZone = ?";

        this.preparedStatement = this.cnx.prepareStatement(request);

        this.preparedStatement.setInt(1, idZone);

        this.resultSet = this.preparedStatement.executeQuery();

        Zone zone = null;

        while(resultSet.next()) {
            zone = new Zone(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        }

        return zone;

    }




}
