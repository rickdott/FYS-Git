package com.mycompany.mavenproject2;

import com.mycompany.mavenproject2.connection.sqlDatabaseConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Rick
 */
public class LoginController implements Initializable{
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private AnchorPane paneLogin;
    
    FXMLDocumentController controller = new FXMLDocumentController();
    
    // Method for creating a PDF ---MOVE TO RELEVANT CONTROLLER
    @FXML
    private void createPdf() {
        System.out.println("Creating PDF...");
        Pdf pdf = new Pdf();
        pdf.printPDF();
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
    
    // Method om een Excelsheet te importeren ---MOVE TO RELEVANT CONTROLLER
    @FXML
    private void excelImport() {
        System.out.println("Beginning Excel import...");

        // Roept een method aan in de MainApp die het path returnt
        String filePath = MainApp.fileChoosePath();

        ExcelReader reader = new ExcelReader(filePath);
        List<String> row = new ArrayList<>();

        row = reader.getNextRow(); // De header van het excel bestand
        row = reader.getNextRow(); // Eerste row
        Database db = new Database();
        while (row != null) {
            
            String mail = row.get(row.size() - 1);
            System.out.println(mail);

            // Opvragen van de idpassenger die hij zoekt via de mail van de passengier
            String sql = String.format("SELECT idpassenger FROM Passenger WHERE email = '%s'", mail);
            String idpassenger = db.executeStringListQuery(sql);

            // SQL query die alles invoert in de database
            sql = String.format("INSERT INTO `Bagage`(`labelnumber`, `flightnumber`, `destination`, `type`, `brand`, `colour`, `specialchar`, `passengerid`, `foundat`, `foundatdate`, `date`)VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6), idpassenger, row.get(7), row.get(8), row.get(9));
            db.executeUpdateQuery(sql);
            
            // Pakt alvast de volgende row
            row = reader.getNextRow();
        }

        System.out.println("Excel import complete...");
    }
    
    @FXML
    private void openCustomerHomescreen(ActionEvent event) {
        controller.newAnchorpane("CustomerHomescreen", paneLogin);
    }

    @FXML
    private void openWorkerHomescreen(ActionEvent event) {
        controller.newAnchorpane("WorkerHomescreen", paneLogin);
    }
    
    //Login for employee
    @FXML
    private TextField textEmail;

    @FXML
    private PasswordField textPassword;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public LoginController() {
        connection = sqlDatabaseConnection.connectdb();
    }

    //Login for employee
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String username = textEmail.getText().toString();
        String password = textPassword.getText().toString();

        //SQL query checks if username and password is equal to input.
        String sql = "SELECT * FROM Employee WHERE username = ? and password = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                infoBox("Enter Correct Username and Password", "Failed", null);
            } else {
                infoBox("Login Successfull", "Success", null);
                /*Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();*/
                
                FXMLDocumentController controller = new FXMLDocumentController();
                controller.newAnchorpane("WorkerHomescreen", paneLogin);
                /*scene = new Scene((Parent) FXMLLoader.load(getClass().getResource(resultSet.getIn‌​t(01) == 0 ? "WorkerHomescreen.fxml" : "Reports.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();*/
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
    private TextField textFlight;

    @FXML
    private TextField textLastName;
    
    @FXML
    private void handleButtonActionPassenger(ActionEvent event) {
        String flight = textFlight.getText().toString();
        String lastname = textLastName.getText().toString();

        String sql = "SELECT * FROM Passenger WHERE flightnumber = ? and lastname = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, flight);
            preparedStatement.setString(2, lastname);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                infoBox("Enter Correct Flight number and Lastname", "Failed", null);
            } else {
                infoBox("Login Successfull", "Success", null);
                /*Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();*/
                
                FXMLDocumentController controller = new FXMLDocumentController();
                controller.newAnchorpane("CustomerHomescreen", paneLogin);
                /*scene = new Scene((Parent) FXMLLoader.load(getClass().getResource("CustomerHomescreen.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();*/
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
