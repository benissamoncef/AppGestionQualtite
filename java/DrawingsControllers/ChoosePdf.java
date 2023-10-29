package DrawingsControllers;

import java.io.File;

import org.openjfx.SGQV2.App;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class ChoosePdf {

	static Stage stage; 
	static String path;
	
	private TextArea textArea = null;
	
	public void show() {
		
		path = "";
		stage = new Stage();
		stage.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));		
		final FileChooser fileChooser = new FileChooser();
	    configuringFileChooser(fileChooser);

	        textArea = new TextArea();
	        textArea.setMinHeight(70);

	        Button button = new Button("Select Drawing");
	       

	        button.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent event) {
	                File dir = fileChooser.showOpenDialog(stage);
	                if (dir != null) {
	                    textArea.setText(dir.getAbsolutePath());
	                } else {
	                    textArea.setText(null);
	                }
	            }
	        });
	        
	        Button buttonOk = new Button("OK");
	        
	        
	        buttonOk.setOnAction(e -> OKclicked());
	        
	        Button buttonClear = new Button("Clear");
	        
	        buttonClear.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent event) {
	            	textArea.setText(null);
	            }
	        });
	        
	        
	        VBox root = new VBox();
	        root.setPadding(new Insets(10));
	        root.setSpacing(5);

	        root.getChildren().addAll(button, textArea, buttonOk, buttonClear);

	        Scene scene = new Scene(root, 720, 480);
	        

	        stage.setTitle("FileChooser (PDF)");
	        stage.setScene(scene);
	        stage.showAndWait();
		
		
		
	}
	
	private void configuringFileChooser(FileChooser fileChooser) {
        // Set title for FileChooser
        fileChooser.setTitle("Select Drawing");

        // Set Initial Directory
        fileChooser.setInitialDirectory(new File("C:\\Users\\moham\\OneDrive\\Desktop"));

        // Add Extension Filters
        fileChooser.getExtensionFilters().addAll(//
                new FileChooser.ExtensionFilter("PDF", "*.pdf")); //
               
    }
    
  
    public void OKclicked(){ 
    		ChoosePdf.path = textArea.getText().toString();
    		stage.close();	
    }
	
}
