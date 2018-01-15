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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * Controller for the login screen, contains methods for logging in and changing
 * languages Stan 1-72, Matthijs 73-208
 *
 * @author Matthijs Snijders 500780453, Stan van Weringh 500771870
 */
public class LoginEmployeeController implements Initializable {

    @FXML
    private Text textWarning, textCaseSensitive, labelUsername, labelPassword;
    @FXML
    private TextField textUsername;
    @FXML
    private PasswordField textPassword;
    @FXML
    private Button buttonEmployee, buttonLoginPassenger;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLanguageEnglish();
        ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");

        textUsername.setPromptText(mybundle.getString("Username"));
        textPassword.setPromptText(mybundle.getString("Password"));
        labelUsername.setText(mybundle.getString("Username"));
        labelPassword.setText(mybundle.getString("Password"));
        buttonEmployee.setText(mybundle.getString("Employee_Login"));

//        labelEmail.setText(mybundle.getString("E-Mail"));
//        textEmailT.setPromptText(mybundle.getString("Enter_your_E-mail"));
//        labelLastname.setText(mybundle.getString("Lastname"));
//        textLastname.setPromptText(mybundle.getString("Enter_your_lastname"));
//        buttonPassenger.setText(mybundle.getString("Login"));
//        buttonEmployee.setText(mybundle.getString("Employee_Login"));
//        textWarning.setText(mybundle.getString("Warning!"));
//        textCaseSensitive.setText(mybundle.getString("Login_is_case_sensitive!"));
//        buttonLoginPassenger.setText(mybundle.getString("Passenger_Login"));
//        textUsername.setText(mybundle.getString("Enter_your_username"));
//        textPassword.setText(mybundle.getString("Enter_your_password"));
    }

// End of translation lines
    @FXML
    private AnchorPane paneLogin;

    Utilities utilities = new Utilities();

    // Methods for changing the language

    @FXML
    private void setLanguageEnglish() {
        System.out.println("Set language to English");
        loadLanguage("en", "EN");
        utilities.newAnchorpane("LoginEmployee", paneLogin);
    }

    @FXML
    private void setLanguageDutch() {
        System.out.println("Set language to Dutch");
        loadLanguage("nl", "NL");
        utilities.newAnchorpane("LoginEmployee", paneLogin);
    }

    @FXML
    private void setLanguageTurkish() {
        System.out.println("Set language to Turkish");
        loadLanguage("tr", "TR");
        utilities.newAnchorpane("LoginEmployee", paneLogin);
    }

    // Main method for changing languages
    private void loadLanguage(String language, String lang) {
        System.out.println("Current Locale: " + Locale.getDefault());
        Locale.setDefault(new Locale(language, lang));
    }

    @FXML
    private void goToPassenger(ActionEvent event) {
        utilities.newAnchorpane("Login", paneLogin);
    }


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

        try {
            ResultSet resultSet = db.executeResultSetQuery(sql);

            if (!resultSet.next()) {
                infoBox("Enter Correct Username and Password", "Failed", null);
            } else {
                //infoBox("Login Successfull", "Success", null);
                resultSet.first();
                usrID = resultSet.getInt("idEmployee");
                roleID = resultSet.getInt("RoleID");

                utilities.setEmployee(usrID, roleID);

                LoginEmployeeController controller = new LoginEmployeeController();
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

}