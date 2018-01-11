package com.mycompany.mavenproject2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for the login screen, contains methods for logging in and changing
 * languages
 * Stan 1-72, Matthijs 73-208
 * @author Matthijs Snijders 500780453, Stan van Weringh 500771870
 */
public class LoginController {

    @FXML
    private AnchorPane paneLogin, paneCustomer;
    
    Utilities utilities = new Utilities();

    // Methods for changing the language
    @FXML
    private void testMethod() {
        System.out.println("Current Locale: " + Locale.getDefault());
	ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");
        System.out.println("Say how are you in US English: " + mybundle.getString("language"));
        
        Locale.setDefault(new Locale("en", "EN"));
        System.out.println(Locale.getDefault());
        mybundle = ResourceBundle.getBundle("languages.Language");
        System.out.println(mybundle.getString("language"));
        
        
//        Locale.setDefault(new Locale("ms", "MY"));
//
//        // read MyLabels_ms_MY.properties
//        System.out.println("Current Locale: " + Locale.getDefault());
//        mybundle = ResourceBundle.getBundle("languages.language");
//        System.out.println("Say how are you in Malaysian Malaya language: " + mybundle.getString("how_are_you"));
    }
    
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

    @FXML
    private TextField textUsername;

    @FXML
    private PasswordField textPassword;

    Stage dialogStage = new Stage();
    Scene scene;

    ResultSet resultSet = null;

    //Login for employee
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Database db = new Database();
        String username = textUsername.getText();
        String password = textPassword.getText();
        int usrID = 0;
        int roleID = 0;
        //String RoleID = EmpSelect.getValue();
        String sql = String.format("SELECT * FROM Employee "
                + "WHERE username = '%s' "
                + "and password = '%s' ",
                //+ "and RoleID = '%s' ", 
                username, password);

        infoBox("sql [" + sql + "]", "Success", null);

        try {
            resultSet = db.executeResultSetQuery(sql);

            if (!resultSet.next()) {
                infoBox("Enter Correct Username and Password", "Failed", null);
            } else {
                //infoBox("Login Successfull", "Success", null);
                resultSet.first();
                usrID = resultSet.getInt("idEmployee");
                roleID = resultSet.getInt("RoleID");

                utilities.setEmployee(usrID, roleID);

                LoginController controller = new LoginController();
                utilities.newAnchorpane("EmployeeHomescreen", paneLogin);
                infoBox("Login Successfull", "Success", null);
            }

        } catch (SQLException e) {
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
        String email = textEmail.getText();
        String lastname = textLastName.getText();
        int usrID = 0;
        int roleID = 0;

        //SQL query checks if email and lastname is equal to input.
        String sql = String.format("SELECT * FROM Passenger "
                + "WHERE email = '%s' "
                + "and lastname = '%s' ",
                email, lastname);

        try {
            resultSet = db.executeResultSetQuery(sql);
            if (!resultSet.next()) {
                infoBox("Enter Correct Email And Lastname", "Failed", null);
            } else {
                infoBox("Login Successfull", "Success", null);
                Utilities utilities = new Utilities();
                while (resultSet.next()) {
                    usrID = resultSet.getInt("idEmployee");
                }
                infoBox("User ID = " + usrID, "Success", null);
                LoginController controller = new LoginController();
                utilities.newAnchorpane("CustomerHomescreen", paneLogin);

            }

        } catch (SQLException e) {
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
