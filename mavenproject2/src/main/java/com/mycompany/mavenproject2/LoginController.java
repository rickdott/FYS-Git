package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.connection.sqlDatabaseConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Rick, Matthijs, Stan
  */ 

public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private AnchorPane paneLogin, paneCustomer;
    private ResourceBundle bundle;
    private Locale locale;

    Utilities utilities = new Utilities();

    // Methods for changing the language
    @FXML
    private void setLanguageEnglish() {
        System.out.println("Set language to English");
        loadLanguage("en", "EN");
    }

    @FXML
    private void setLanguageDutch() {
        System.out.println("Set language to Dutch");
        loadLanguage("nl", "NL");
    }

    @FXML
    private void setLanguageGerman() {
        System.out.println("Set language to German");
        loadLanguage("de", "DE");
    }

    @FXML
    private void setLanguagePortuguese() {
        System.out.println("Set language to Portuguese");
        loadLanguage("pt", "PT");
    }

    @FXML
    private void setLanguageTurkish() {
        System.out.println("Set language to Turkish");
        loadLanguage("tr", "TR");
    }

    // Main method for changing languages
    private void loadLanguage(String language, String lang) {
        Locale.setDefault(new Locale(language, lang));
        ResourceBundle bundle = ResourceBundle.getBundle("Language"); // TODO: Path veranderen zodat de .properties in een map languages kunnen
        //System.out.println(bundle.getString("language"));
    }

    // Method for creating a PDF ---MOVE TO RELEVANT CONTROLLER
    @FXML
    private void createPdf() {
        System.out.println("Creating PDF...");
        // Pdf pdf = new Pdf();
        //pdf.printPDF();
        System.out.println("PDF Created...");
    }

    // Method for sending an e-mail ---MOVE TO RELEVANT CONTROLLER
    @FXML
    private void sendMail() {
        System.out.println("Sending mail...");
        Mail mail = new Mail("baggerfys@gmail.com");
        mail.mailsturen();
        System.out.println("Mail sent...");
    }

    @FXML
    private void openCustomerHomescreen(ActionEvent event) {
        utilities.newAnchorpane("CustomerHomescreen", paneLogin);
    }
    
    @FXML
    private void openCustomerHomescreenFromCustomer(ActionEvent event) {
        utilities.newAnchorpane("CustomerHomescreen", paneCustomer);
    }

    @FXML
    private void openWorkerHomescreen(ActionEvent event) {
        utilities.newAnchorpane("WorkerHomescreen", paneLogin);
    }
    
    @FXML
    private void openWorkerHomescreenFromCustomer(ActionEvent event) {
        utilities.newAnchorpane("WorkerHomescreen", paneCustomer);
    }
    
    @FXML
    private void goToEmployee(ActionEvent event) {
        utilities.newAnchorpane("LoginEmployee", paneCustomer);
    }
    
    @FXML
    private void goToPassenger(ActionEvent event) {
        utilities.newAnchorpane("Login", paneLogin);
    }
    //Login for employee
    @FXML
    private TextField textUsername;

    @FXML
    private PasswordField textPassword;
    
    @FXML
    private ChoiceBox<String> EmpSelect;

    Stage dialogStage = new Stage();
    Scene scene;
    
    ResultSet resultSet = null;

    //Login for employee
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Database db = new Database();
        String username = textUsername.getText().toString();
        String password = textPassword.getText().toString();
        String function = EmpSelect.getValue().toString();
        String sql = String.format("SELECT * FROM Employee WHERE username = '%s' and password = '%s' and function = '%s' ", username, password, function);

        try {
            resultSet = db.executeResultSetQuery(sql);

            if (!resultSet.next()) {
                infoBox("Enter Correct Username and Password", "Failed", null);
            } else {
                if ("Medewerker".equals(function)) {
                    infoBox("Login Successfull", "Success", null);
                    Utilities utilities = new Utilities();
                    utilities.newAnchorpane("WorkerHomescreen", paneLogin);
                } else if ("Manager".equals(function)) {
                    infoBox("Login Successfull", "Success", null);
                    Utilities utilities = new Utilities();
                    utilities.newAnchorpane("WorkerHomescreen", paneLogin);
                }
                infoBox("Login Successfull", "Success", null);
                Utilities utilities = new Utilities();
                utilities.newAnchorpane("WorkerHomescreen", paneLogin);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    //Login for passenger
    @FXML
    private TextField textEmail;

    @FXML
    private TextField textLastName;

    @FXML
    private void handleButtonActionPassenger(ActionEvent event) {
        Database db = new Database();
        String email = textEmail.getText().toString();
        String lastname = textLastName.getText().toString();

        //SQL query checks if email and lastname is equal to input.
         String sql = String.format("SELECT * FROM Passenger WHERE email = '%s' and lastname = '%s' ", email, lastname);

        try {
            resultSet = db.executeResultSetQuery(sql);
            if (!resultSet.next()) {
                infoBox("Enter Correct Email And Lastname", "Failed", null);
            } else {
                infoBox("Login Successfull", "Success", null);
                Utilities utilities = new Utilities();
                utilities.newAnchorpane("CustomerHomescreen", paneLogin);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void infoBoxPassenger(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

}
