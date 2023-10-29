package Models;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import DrawingsControllers.DrawingTransaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Drawing {
	
	private int id;
	private String path;
	private String références_id;
	private Timestamp CreatedAt;
	
	private int Révision;
	private Timestamp dateRévision;
	
	


	// in tableView :
	
	
	private HBox hbox = new HBox();
	private Label pdfName = new Label();
    private FontAwesomeIconView imagepdf = new FontAwesomeIconView();
	

	public Drawing() {
		super();
	}
	
	
	public Drawing(String références_id, String path, String Révision) {
		super();
		this.path = path;
		this.setRéférences_id(références_id);
		this.Révision = Integer.parseInt(Révision);
	}
	
	
	public Drawing(int id, String path, Timestamp createdAt,Timestamp dateRévision, String références_id, int Révision) {
		super();
		this.id = id;
		this.path = path;
		CreatedAt = createdAt;
		this.setDateRévision(dateRévision);
		this.setRévision(Révision);
		pdfName.setText(références_id + createdAt.getTime() + ".pdf");
		pdfName.setPadding(new Insets(0, 0, 0, 32));			
		imagepdf.setGlyphName("FILE");
		imagepdf.setSize("24");
		imagepdf.setFill(Color.WHITE);
		
		imagepdf.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent me) {
		    	try {
		    		
					new DrawingTransaction().updateRevDrawing(id, Révision);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	
		    	File pdf = new File(path);
	            try {
					Desktop.getDesktop().open(pdf);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            pdf.deleteOnExit();
		    	
		    	
		    }
		});
	
		hbox.getChildren().addAll(imagepdf, pdfName);
	
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Timestamp getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		CreatedAt = createdAt;
	}


	public HBox getHbox() {
		return hbox;
	}


	public void setHbox(HBox hbox) {
		this.hbox = hbox;
	}


	public Label getPdfName() {
		return pdfName;
	}


	public void setPdfName(Label pdfName) {
		this.pdfName = pdfName;
	}


	public FontAwesomeIconView getImagepdf() {
		return imagepdf;
	}


	public void setImagepdf(FontAwesomeIconView imagepdf) {
		this.imagepdf = imagepdf;
	}


	public String getRéférences_id() {
		return références_id;
	}


	public void setRéférences_id(String références_id) {
		this.références_id = références_id;
	}


	public int getRévision() {
		return Révision;
	}


	public void setRévision(int révision) {
		Révision = révision;
	}


	public Timestamp getDateRévision() {
		return dateRévision;
	}


	public void setDateRévision(Timestamp dateRévision) {
		this.dateRévision = dateRévision;
	}



	
	
	
	
	
	
	

}
