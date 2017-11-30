package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.connection.sqlDatabaseConnection;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
        
/**
 *
 * @author Rick den Otter 500749952 Lines 93-156 Stan van Weringh 500771870
 * Lines 46-91 This file contains some methods for use when buttons are clicked
 * or when the program has to switch pages
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField textEmail;
    
    @FXML
    private PasswordField textPassword;
    
    Stage dialogStage = new Stage();
    Scene scene;
    
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public FXMLDocumentController() {
        connection = sqlDatabaseConnection.connectdb();
    }
    
    //Login for employee
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String username = textEmail.getText().toString();
        String password = textPassword.getText().toString();

        //String for the sql query
        String sql = "SELECT * FROM Employee WHERE username = ? and password = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                infoBox("Enter Correct Email and Password", "Failed", null);
            } else {
                infoBox("Login Successfull", "Success", null);
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                //This line checks if the input of the user is equal to a 0 or a 1 in the function column. And displays the correct page.
                scene = new Scene((Parent) FXMLLoader.load(getClass().getResource(resultSet.getIn‌​t(01) == 1 ? "WorkerHomescreen.fxml" : "FAQ.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
    
     // Login for passenger
     @FXML
    private void handleButtonActionPassenger(ActionEvent event) {
        String flightnumber = textEmail.getText().toString();
        String lastname = textPassword.getText().toString();

        //String for the sql query
        String sql = "SELECT * FROM Passenger WHERE flightnumber = ? and lastname = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, flightnumber);
            preparedStatement.setString(2, lastname);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                infoBox("Enter Correct flightnumber and lastname", "Failed", null);
            } else {
                infoBox("Login Successfull", "Success", null);
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                //This line checks if the input of the user is equal to a 0 or a 1 in the function column. And displays the correct page.
                scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("CustomerHomeScreen.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public static void infoBox2(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
 
    @FXML
    public void onHover(String currentPage, Button btn, Label label) {
        System.out.println("onHover activated");
        if (!label.getText().equals(currentPage)) {
            btn.setStyle("-fx-background-color: #951504; -fx-background-radius: 0; -fx-border-width: 1 0 0 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    @FXML
    public void onHover(String currentPage, Button btn, Label label, boolean isLast) {
        System.out.println("onHover activated");
        if (!label.getText().equals(currentPage) && isLast) {
            btn.setStyle("-fx-background-color: #951504; -fx-background-radius: 0; -fx-border-width: 1 0 1 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    // Two methods to use when hovered off the button, overloaded
    @FXML
    public void offHover(String currentPage, Button btn, Label label) {
        System.out.println("offHover activated");
        if (!label.getText().equals(currentPage)) {
            btn.setStyle("-fx-background-color: #D81E05; -fx-background-radius: 0; -fx-border-width: 1 0 0 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    @FXML
    public void offHover(String currentPage, Button btn, Label label, boolean isLast) {
        System.out.println("offHover activated");
        if (!label.getText().equals(currentPage) && isLast) {
            btn.setStyle("-fx-background-color: #D81E05; -fx-background-radius: 0; -fx-border-width: 1 0 1 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    // Opens a different page, changing just a Pane
    @FXML
    public void newPane(String pageName, Button btn, Pane pane, Label label) {
        System.out.println("Opening another page...");
        
        Parent newPane = loadFXMLFile(pageName + ".fxml");
        pane.getChildren().clear();
        pane.getChildren().add(newPane);
        label.setText(btn.getText());
        
        System.out.println("Another page opened...");
    }
    @FXML
    public void newPane(String pageName, Pane pane) {
        System.out.println("Opening another page...");
        
        Parent newPane = loadFXMLFile(pageName + ".fxml");
        pane.getChildren().clear();
        pane.getChildren().add(newPane);
        
        System.out.println("Another page opened...");
    }
    
    // Opens a different page, changing the AnchorPane
    @FXML
    public void newAnchorpane(String pageName, AnchorPane paneToReplace) {
        System.out.println("Opening another page(anchor)...");
        
        Parent newPane = loadFXMLFile(pageName + ".fxml");
        paneToReplace.getChildren().clear();
        paneToReplace.getChildren().add(newPane);
        
        System.out.println("Another page opened(anchor)...");
    }

    public Parent loadFXMLFile(String fxmlFileName) {
        try {
            return FXMLLoader.load(getClass().getResource(fxmlFileName));
        } catch (IOException ex) {
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
            return null;
        }
    }

}
