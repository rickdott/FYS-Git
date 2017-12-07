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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Staick
 */
public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private AnchorPane paneLogin;
    Utilities utilities = new Utilities();
    private ResourceBundle bundle;
    private Locale locale;

    FXMLDocumentController controller = new FXMLDocumentController();

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

    // Method om een Excelsheet te importeren ---MOVE TO RELEVANT CONTROLLER
    @FXML
    private void excelImport() {
        System.out.println("Beginning Excel import...");

        // Roept een method aan in de MainApp die het path returnt
        String filePath = MainApp.fileChoosePath();

        ExcelReader reader = new ExcelReader(filePath);
        List<String> row = new ArrayList<>();

        reader.getNextRow(); // De header van het excel bestand skippen
        reader.getNextRow();
        reader.getNextRow();
        reader.getNextRow();

        row = reader.getNextRow(); // Eerste row
        Database db = new Database();
        boolean moreData = true;
        int currTabExcel = 0;
        while (moreData) {

            // Gaat naar de volgende tab als je bij de laatste row bent
            if (row.get(0).isEmpty()) {
                // Kijkt of er nog een tab te gaan is
                if (currTabExcel < (reader.getNumberOfSheets() - 1)) {
                    currTabExcel += 1;
                    reader.setSelectedSheet(currTabExcel);
                    reader.getNextRow(); // De header van het excel bestand skippen
                    reader.getNextRow();
                    reader.getNextRow();
                    reader.getNextRow();

                    row = reader.getNextRow(); // Eerste row
                } else {
                    moreData = false;
                    break;
                }
            }

            // Opvragen van de keys 
            String luggagetype = db.executeStringListQuery(String.format("SELECT `idluggage type` FROM Luggagetype WHERE english = '%s' OR dutch = '%s' OR spanish = '%s' OR turkish = '%s';", row.get(3), row.get(3), row.get(3), row.get(3)));
            String maincolor = db.executeStringListQuery(String.format("SELECT `ralcode` FROM Colour WHERE english = '%s' OR dutch = '%s' OR spanish = '%s' OR turkish = '%s';", row.get(8), row.get(8), row.get(8), row.get(8)));
            String secondcolor = db.executeStringListQuery(String.format("SELECT `ralcode` FROM Colour WHERE english = '%s' OR dutch = '%s' OR spanish = '%s' OR turkish = '%s';", row.get(9), row.get(9), row.get(9), row.get(9)));
            String locationfound = db.executeStringListQuery(String.format("SELECT `idlocation` FROM Location WHERE english = '%s' OR dutch = '%s' OR spanish = '%s' OR turkish = '%s';", row.get(7), row.get(7), row.get(7), row.get(7)));

            // Luggage tag, als er geen is wordt de value op 0 gezet
            String luggagetag = row.get(6);
            if ("".equals(luggagetag)) {
                luggagetag = "0";
            }

            //x = x.substring(0, 4) + "." + x.substring(4, x.length());
            // Kijkt of er een ' voorkomt en als dat zo is zet er een \ voor
            String passengernamecity = row.get(12);
            if (passengernamecity.contains("'")) {
                passengernamecity = passengernamecity.substring(0, passengernamecity.indexOf("'")) + "\\"
                        + passengernamecity.substring(passengernamecity.indexOf("'"), passengernamecity.length());
            }

            // Test prints
            /*
            System.out.println("registrationnr: " + row.get(0));
            System.out.println("datefound: " + row.get(1));
            System.out.println("timefound: " + row.get(2));
            System.out.println("luggagetype: " + luggagetype);
            System.out.println("brand: " + row.get(4));
            System.out.println("flightnumber: " + row.get(5));
            System.out.println("luggagelabelnr: " + luggagetag);
            System.out.println("locationfound: " + locationfound);
            System.out.println("primarycolour: " + maincolor);
            System.out.println("secondarycolour: " + secondcolor);
            System.out.println("size: " + row.get(10));
            System.out.println("weight: " + row.get(11));
            System.out.println("passenger_name_city: " + passengernamecity);
            System.out.println("otherchar: " + row.get(13));
            */

            // Kijk of hij al in de db zit als dat niet zo is zet de record in de db
            String checkIfInDB = db.executeStringQuery(String.format("SELECT registrationnr FROM Foundbagageinventory WHERE registrationnr = '%s'", row.get(0)));
            System.out.println("checkIfInDB: " + checkIfInDB + "\n");
            if (checkIfInDB == null) {

                // SQL query die alles invoert in de database 
                // In de if kijken welke query er gemaakt moet worden
                String sql;
                if (secondcolor == null && locationfound == null) {
                    sql = String.format("INSERT INTO `Foundbagageinventory`"
                            + "(`registrationnr`,"
                            + "`datefound`,"
                            + "`timefound`,"
                            + "`luggagetype`,"
                            + "`brand`,"
                            + "`flightnumber`,"
                            + "`luggagelabelnr`,"
                            + "`primarycolour`,"
                            + "`size`,"
                            + "`weight`,"
                            + "`passenger_name_city`,"
                            + "`otherchar`)"
                            + "VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                            row.get(0), row.get(1), row.get(2), luggagetype, row.get(4), row.get(5), luggagetag, maincolor, row.get(10), row.get(11), passengernamecity, row.get(13));

                } else if (locationfound == null) {
                    sql = String.format("INSERT INTO `Foundbagageinventory`"
                            + "(`registrationnr`,"
                            + "`datefound`,"
                            + "`timefound`,"
                            + "`luggagetype`,"
                            + "`brand`,"
                            + "`flightnumber`,"
                            + "`luggagelabelnr`,"
                            + "`primarycolour`,"
                            + "`secondarycolour`,"
                            + "`size`,"
                            + "`weight`,"
                            + "`passenger_name_city`,"
                            + "`otherchar`)"
                            + "VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                            row.get(0), row.get(1), row.get(2), luggagetype, row.get(4), row.get(5), luggagetag, maincolor, secondcolor, row.get(10), row.get(11), passengernamecity, row.get(13));

                } else if (secondcolor == null) {
                    sql = String.format("INSERT INTO `Foundbagageinventory`"
                            + "(`registrationnr`,"
                            + "`datefound`,"
                            + "`timefound`,"
                            + "`luggagetype`,"
                            + "`brand`,"
                            + "`flightnumber`,"
                            + "`luggagelabelnr`,"
                            + "`locationfound`,"
                            + "`primarycolour`,"
                            + "`size`,"
                            + "`weight`,"
                            + "`passenger_name_city`,"
                            + "`otherchar`)"
                            + "VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                            row.get(0), row.get(1), row.get(2), luggagetype, row.get(4), row.get(5), luggagetag, locationfound, maincolor, row.get(10), row.get(11), passengernamecity, row.get(13));
                } else {
                    sql = String.format("INSERT INTO `Foundbagageinventory`"
                            + "(`registrationnr`,"
                            + "`datefound`,"
                            + "`timefound`,"
                            + "`luggagetype`,"
                            + "`brand`,"
                            + "`flightnumber`,"
                            + "`luggagelabelnr`,"
                            + "`locationfound`,"
                            + "`primarycolour`,"
                            + "`secondarycolour`,"
                            + "`size`,"
                            + "`weight`,"
                            + "`passenger_name_city`,"
                            + "`otherchar`)"
                            + "VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                            row.get(0), row.get(1), row.get(2), luggagetype, row.get(4), row.get(5), luggagetag, locationfound, maincolor, secondcolor, row.get(10), row.get(11), passengernamecity, row.get(13));
                }
                db.executeUpdateQuery(sql);
            }

            // Pakt alvast de volgende row
            row = reader.getNextRow();
        }
        System.out.println("Excel import complete...");
    }

    @FXML
    private void openCustomerHomescreen(ActionEvent event) {
        utilities.newAnchorpane("CustomerHomescreen", paneLogin);
    }

    @FXML
    private void openWorkerHomescreen(ActionEvent event) {
        utilities.newAnchorpane("WorkerHomescreen", paneLogin);
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
                utilities.newAnchorpane("WorkerHomescreen", paneLogin);
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
                utilities.newAnchorpane("CustomerHomescreen", paneLogin);
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
