package org.openjfx.SGQV2;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App 
 */
public class App extends Application {
		
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        scene = new Scene(loadFXML("Login//LogIn"));
		stage.setScene(scene);   
		stage.getIcons().add(new Image(App.class.getResource("Home//images//lear.png").toString()));
        stage.show();   
    	
   
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}