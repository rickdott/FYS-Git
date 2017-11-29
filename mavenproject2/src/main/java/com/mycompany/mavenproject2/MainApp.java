package com.mycompany.mavenproject2;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class MainApp extends Application {

    public static Stage application;

    // Start van de hele applicatie
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginEmployee.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("BAGGER");
        stage.setScene(scene);
        stage.show();

        application = stage;
        
        // Database testen
        //Database db = new Database();
        //db.executeUpdateQuery("INSERT INTO Airport (iatacode, name, timezone) VALUES ('BBB', 'Oruam', 1)");
        //String temp = db.executeStringListQuery("SELECT * FROM Airport");
        //System.out.print(temp);
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
