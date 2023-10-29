package DrawingsControllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.openjfx.SGQV2.Administration.HomeController;
import Models.Drawing;
import Transactions.BaseTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class DrawingTransaction extends BaseTransaction{
	

	public DrawingTransaction() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public void save(Drawing drawing) throws SQLException {
		
		String request= "insert into drawing (références_id, data, Révision) values (?, ?, ?)"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);

		
		try {
			FileInputStream pdf = new FileInputStream(drawing.getPath());
			
			this.preparedStatement.setString(1, drawing.getRéférences_id());
			this.preparedStatement.setBinaryStream(2, pdf);
			this.preparedStatement.setInt(3, drawing.getRévision());
			
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.preparedStatement.execute();
		
	}
	
	
	
	public Drawing GetOne(String références_id) throws SQLException, IOException {
		
			Drawing drawing = new Drawing();
			
			java.nio.file.Path pt = Paths.get(HomeController.tmpdir);
		            
		
			String request= "Select * from drawing where CreatedAt = (select Max(CreatedAt) from drawing where références_id = ?)";
					
			    
			this.preparedStatement = this.cnx.prepareStatement(request);
			
			this.preparedStatement.setString(1, références_id);
		
			this.resultSet = this.preparedStatement.executeQuery();
			
			
			java.nio.file.Path p  = Files.createTempFile(pt, références_id + this.resultSet.getDate(3).getTime(), ".pdf");;

			File file = new File(p.toString());;

			
			FileOutputStream output = new FileOutputStream(file);	;
			
			
			while (this.resultSet.next()) { 

                InputStream input = this.resultSet.getBinaryStream("data");
                
                byte[] buffer = new byte[1024];
                
                while (input.read(buffer) > 0) {
                	output.write(buffer);
                }
                
                drawing.setRéférences_id(références_id);
                
                drawing.setCreatedAt(this.resultSet.getTimestamp(3));
       
                drawing.setDateRévision(this.resultSet.getTimestamp(6));

                drawing.setRévision(5);
            }
			
		String path = file.getAbsolutePath();
		output.close();
		
		drawing.setPath(path);
	
		return drawing;
		
		}
	
	
	
	
	
	public Boolean checkByRef(String références_id) throws SQLException {
		
		String request= "select * from drawing where références_id = ?"; 
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setString(1, références_id);
		
		this.resultSet = this.preparedStatement.executeQuery();
		
		
		if(resultSet.next()) {
			return true;
		}
		
		return false;
	
		
	}
		
	
	
	
	public ObservableList<Drawing> GetAll(String références_id) throws SQLException, IOException {
		
		
		ObservableList<Drawing> myList = FXCollections.observableArrayList();

		String path = null;
		File file = null;
		FileOutputStream output = null;
		InputStream input = null;

		
	
		String request= "Select * from drawing where références_id = ?";
		
		java.nio.file.Path p;
				
		java.nio.file.Path pt = Paths.get(HomeController.tmpdir);    
			
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setString(1, références_id);
	
		this.resultSet = this.preparedStatement.executeQuery();
		
		
		while (this.resultSet.next()) {
			

            p = Files.createTempFile(pt, références_id + resultSet.getTimestamp(3).getTime(), ".pdf");
			
			file = new File(p.toString());
			
			output = new FileOutputStream(file);
			
            input = this.resultSet.getBinaryStream("data");
            
            byte[] buffer = new byte[1024];
            
            while (input.read(buffer) > 0) {
            	output.write(buffer);
            }
            
            path = file.getAbsolutePath();
        	output.close();
            
            
            myList.add(new Drawing(this.resultSet.getInt(1), path, this.resultSet.getTimestamp(3), this.resultSet.getTimestamp(6), this.resultSet.getString(2), this.resultSet.getInt(5)));
                       
        }
		


	return myList;
		
		
	}
	
	
	public void updateRevDrawing(int id,int Révision) throws SQLException {
		
		String request= "UPDATE `drawing` SET ` Révision ` = ? ,`dateRévision` = ? WHERE id = ?";
		
	    
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, Révision);
		this.preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
		this.preparedStatement.setInt(3, id);
	
		this.preparedStatement.execute();

		
	}
	
	

}
