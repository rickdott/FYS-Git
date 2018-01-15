package com.mycompany.mavenproject2;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * Controller for the login screen, contains methods for logging in and changing
 * languages Stan 1-72, Matthijs 73-208
 *
 * @author Matthijs Snijders 500780453, Stan van Weringh 500771870
 */
public class LoginController implements Initializable {

    @FXML
    private Text labelEmail, labelLastname, textWarning, textCaseSensitive;

    @FXML
    private TextField textEmail, textLastname;

    @FXML
    private AnchorPane paneCustomer;

    @FXML
    private Button buttonEmployee, buttonLoginPassenger, buttonPassenger;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");

//        labelEmail.setText(mybundle.getString("E-Mail"));
//        textEmail.setPromptText(mybundle.getString("Enter_your_E-mail"));
//        labelLastname.setText(mybundle.getString("Lastname"));
//        textLastname.setPromptText(mybundle.getString("Enter_your_lastname"));
//        buttonEmployee.setText(mybundle.getString("Login"));
//        textWarning.setText(mybundle.getString("Warning!"));
//        textCaseSensitive.setText(mybundle.getString("Login_is_case_sensitive!"));
//        buttonLoginPassenger.setText(mybundle.getString("Passenger_Login"));
    }

// End of translation lines
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
        System.out.println("Current Locale: " + Locale.getDefault());
        Locale.setDefault(new Locale(language, lang));
    }

    @FXML
    private void openCustomerHomescreenFromCustomer(ActionEvent event) {
        utilities.newAnchorpane("CustomerHomescreen", paneCustomer);
    }

    @FXML
    private void openWorkerHomescreenFromCustomer(ActionEvent event) {
        utilities.newAnchorpane("EmployeeHomescreen", paneCustomer);
    }

    @FXML
    private void goToEmployee(ActionEvent event) {
        utilities.newAnchorpane("LoginEmployee", paneCustomer);
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
    private void handleButtonActionPassenger(ActionEvent event) {
        Database db = new Database();
        String email = textEmail.getText();

        String lastname = textLastname.getText();
        int usrID = 0;
        int roleID = 0;

        //SQL query checks if email and lastname is equal to input.
        String sql = String.format("SELECT * FROM Passenger "
                + "WHERE email = '%s' "
                + "and lastname = '%s' ",
                email, lastname);

        try {
            ResultSet resultSet = db.executeResultSetQuery(sql);
            if (!resultSet.next()) {
                infoBox("Enter Correct labelnummer And Lastname", "Failed", null);
            } else {
                infoBox("Login Successfull", "Success", null);
                Utilities utilities = new Utilities();

                while (resultSet.next()) {
                    usrID = resultSet.getInt("idEmployee");
                }
                infoBox("User ID = " + usrID, "Success", null);
                utilities.newAnchorpane("CustomerHomescreen", paneCustomer);
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
