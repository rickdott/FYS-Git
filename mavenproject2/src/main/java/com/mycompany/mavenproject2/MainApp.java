package com.mycompany.mavenproject2;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.Screen;

public class MainApp extends Application {

    public static Stage application;
    public Utilities myUtils;

    // Start van de hele applicatie
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginEmployee.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setMaximized(true);
        stage.setTitle("BAGGER");
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        
         
        
        stage.show();
        stage.setResizable(true);
   
        application = stage;
        
    }

    // Returnt het pad (String)
    public static String fileChoosePath() {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(application);
        if (file != null) {
            return file.getPath();
        }
        else{
            return "";
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
