package com.mycompany.mavenproject2;

import static com.mycompany.mavenproject2.LoginEmployeeController.infoBox;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Class to facilitate registering a piece of missing luggage into the database
 * Stijn Klopper: 1-216 (215) and 387-483 (94) Stan van Weringh: 216-386 (170)
 *
 * @author Stijn Klopper 500770512, Stan van Weringh 500771870
 */
public class RegisterMissingController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");

        labelGeneral.setText(mybundle.getString("General"));
        labelDate.setText(mybundle.getString("Date"));
        labelTime.setText(mybundle.getString("Time"));
        labelLuggage.setText(mybundle.getString("Luggage_Label"));
        labelLabelNumber.setText(mybundle.getString("Label_Number*"));
        labelFlightNumber.setText(mybundle.getString("Flight_Number*"));
        labelLuggageInformation.setText(mybundle.getString("Luggage_Information"));
        labelType.setText(mybundle.getString("Type"));
        labelBrand.setText(mybundle.getString("Brand"));
        labelSpecialChar.setText(mybundle.getString("Special_Characteristics"));
        labelSize.setText(mybundle.getString("Size"));
        labelWeight.setText(mybundle.getString("Weight"));
        labelRequired.setText(mybundle.getString("Fields_with_*_are_required_to_fill_in"));
        labelTraveller.setText(mybundle.getString("Traveller"));
        labelFirstname.setText(mybundle.getString("Firstname*"));
        labelSurname.setText(mybundle.getString("Surname*"));
        labelAdress.setText(mybundle.getString("Adress*"));
        labelCity.setText(mybundle.getString("City*"));
        labelPostalcode.setText(mybundle.getString("Postal_code*"));
        labelCountry.setText(mybundle.getString("Country*"));
        labelPhone.setText(mybundle.getString("Phone*"));
        labelEmail.setText(mybundle.getString("E-mail*"));
        labelPColour.setText(mybundle.getString("Primary_Colour"));
        labelSColour.setText(mybundle.getString("Secondary_Colour"));
        labelSendmail.setText(mybundle.getString("Send_a_copy_of_this_document_to_my_email"));
        //labelExcel.setText(mybundle.getString("Import_Excel"));
        //buttonBacktologin.setText(mybundle.getString("Submit"));

        DateFormat datum = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat tijd = new SimpleDateFormat("HH:mm:ss");

        Date date = new Date();
        Date time = new Date();

        generalTime.setText((tijd.format(date)));
        generalDate.setText((datum.format(time)));

        BagagePrimaryColour.setItems(colours);
        BagageSecondaryColour.setItems(colours);
        LuggageType.setItems(Luggagetypes);
    }

    @FXML
    private Text labelGeneral, labelDate, labelTime, labelLocation, labelLuggage, labelLabelNumber,
            labelFlightNumber, labelLuggageInformation, labelType, labelBrand, labelColour, labelSpecialChar, labelRequired,
            labelTraveller, labelFirstname, labelSurname, labelAdress, labelCity, labelPostalcode, labelCountry, labelPhone, labelEmail, labelPColour, labelSColour, 
            labelSize, labelWeight;

    @FXML
    private Button buttonBacktologin, labelExcel, labelSubmit;

    @FXML
    private CheckBox labelSendmail;

    //input Traveller
    @FXML
    private TextField TravellerFirstName;
    @FXML
    private TextField TravellerSurname;
    @FXML
    private TextField TravellerAdress;
    @FXML
    private TextField TravellerCity;
    @FXML
    private TextField TravellerPostalCode;
    @FXML
    private TextField TravellerCountry;
    @FXML
    private TextField TravellerPhone;
    @FXML
    private TextField TravellerEmail;
    @FXML
    private TextField BagageFlight;

    //input bagage
    @FXML
    private TextField BagageLabel;

    @FXML
    private ComboBox<String> LuggageType;
    @FXML
    private TextField BagageBrand;
    @FXML
    private ComboBox<String> BagagePrimaryColour;
    @FXML
    private ComboBox<String> BagageSecondaryColour;
    @FXML
    private TextField BagageSpecialchar;

    @FXML
    private TextField BagageSize;

    @FXML
    private TextField BagageWeight;

    @FXML
    private Label warning;

    @FXML
    private ProgressIndicator draaiding;

    @FXML
    private TextField generalDate;
    @FXML
    private TextField generalTime;

    @FXML
    private StackPane loadingScreen;

    // Path van het excel bestand (als die er is)
    private String excelPath;
    
    ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");

    //dropdownlists
    ObservableList<String> colours = FXCollections.observableArrayList(
            "Yellow", "Olive", "Red", "Darkred", "Pink", "Purple", "Violet",
            "Blue", "Lightblue", "Darkblue", "Bluegreen", "Green", "Darkgreen",
            "Lightgreen", "Gray", "Darkgray", "Lightgray", "Brown", "Darkbrown",
            "Lightbrk\", \"Box\", \"Sports\",\n" +
"            \"Bag\", \"Business Case\", \"Case\", \"Other\");own", "White", "Black", "Cream");

    ObservableList<String> Luggagetypes = FXCollections.observableArrayList(
            "Suitcase", "Bag", "Bagpack", "Box", "Sports",
            "Bag", "Business Case", "Case", "Other");

    @FXML
    private AnchorPane registerMissingPane, thankYouPage;

    Utilities utilities = new Utilities();

    //submit button
    @FXML
    private void openRegisterThankyou(ActionEvent event) throws SQLException {

        if (!TravellerFirstName.getText().trim().isEmpty()
                && !TravellerSurname.getText().trim().isEmpty()
                && !TravellerAdress.getText().trim().isEmpty()
                && !TravellerCity.getText().trim().isEmpty()
                && !TravellerPostalCode.getText().trim().isEmpty()
                && !TravellerCountry.getText().trim().isEmpty()
                && !TravellerPhone.getText().trim().isEmpty()
                && !TravellerEmail.getText().trim().isEmpty()
                && !BagageFlight.getText().trim().isEmpty()
                && !BagageLabel.getText().trim().isEmpty()) {
            //ResultSet resultSet = null;
            String sql = String.format("SELECT * FROM Flight WHERE flightnr = '%s' ",
                    BagageFlight.getText());
            Database db = new Database();
            ResultSet resultSet = db.executeResultSetQuery(sql);
            if (resultSet.next()) {

                Mail mail = new Mail(TravellerEmail.getText().trim());
                if (mail.ValidateMail(TravellerEmail.getText().trim()) == true) {

                    String BagagePrimaryColourString;
                    String BagageSecondaryColourString;
                    String LuggageTypeSelect;

                    String pdf_inputprimarycolour = "";
                    String pdf_inputsecondarycolour = "";
                    String pdf_inputluggagetype = "";

                    //misschien temp manier van lege choicebox check
                    if (BagagePrimaryColour.getValue() != null) {
                        BagagePrimaryColourString = db.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", BagagePrimaryColour.getValue()));
                        pdf_inputprimarycolour = BagagePrimaryColour.getValue();

                    } else {
                        BagagePrimaryColourString = "1";
                    }

                    if (BagageSecondaryColour.getValue() != null) {
                        BagageSecondaryColourString = db.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", BagageSecondaryColour.getValue()));
                        pdf_inputsecondarycolour = BagageSecondaryColour.getValue();
                    } else {
                        BagageSecondaryColourString = "1";
                    }

                    if (LuggageType.getValue() != null) {
                        LuggageTypeSelect = db.executeStringListQuery(String.format("SELECT idluggage_type FROM Luggagetype WHERE english = '%s'", LuggageType.getValue()));
                        pdf_inputluggagetype = LuggageType.getValue();
                    } else {
                        LuggageTypeSelect = "10";
                    }

                    String travellerInformation = String.format("INSERT INTO Passenger "
                            + "(firstname, lastname, adress, city, zip, country, phone, email, flightnumber) "
                            + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                            TravellerFirstName.getText(), TravellerSurname.getText(),
                            TravellerAdress.getText(), TravellerCity.getText(),
                            TravellerPostalCode.getText(), TravellerCountry.getText(),
                            TravellerPhone.getText(), TravellerEmail.getText(),
                            BagageFlight.getText());

                    db.executeUpdateQuery(travellerInformation);

                    ResultSet bagagenummer = db.executeResultSetQuery("SELECT idpassenger FROM Passenger ORDER BY idpassenger DESC LIMIT 1;");
                    bagagenummer.next();
                    System.out.println("TEST LET OP !!!" + bagagenummer.getInt("idpassenger"));

                    String luggageInformation = String.format("INSERT INTO "
                            + "Lostbagage (dateregistered, timeregistered, luggagelabelnr, passenger_name_city, luggagetype, brand, primarycolour, secondarycolour, otherchar, flightnumber, idpassenger, size, weight) "
                            + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                            generalDate.getText(), generalTime.getText(), BagageLabel.getText(),
                            TravellerFirstName.getText() + TravellerAdress.getText(), LuggageTypeSelect,
                            BagageBrand.getText(), BagagePrimaryColourString, BagageSecondaryColourString,
                            BagageSpecialchar.getText(), BagageFlight.getText(), bagagenummer.getInt("idpassenger"),
                            BagageSize.getText(), BagageWeight.getText());

                    db.executeUpdateQuery(luggageInformation);

                    Pdf pdf = new Pdf();
                    pdf.printPDF(TravellerFirstName.getText(), TravellerSurname.getText(),
                            TravellerAdress.getText(), TravellerCity.getText(),
                            TravellerPostalCode.getText(), TravellerCountry.getText(),
                            TravellerPhone.getText(), TravellerEmail.getText(),
                            BagageLabel.getText(), BagageFlight.getText(),
                            pdf_inputluggagetype,
                            BagageBrand.getText(), pdf_inputprimarycolour,
                            pdf_inputsecondarycolour,
                            BagageSpecialchar.getText(), generalDate.getText(), generalTime.getText());

                    if (mail.ValidateMail(TravellerEmail.getText().trim()) == true) {

                        if (labelSendmail.isSelected()) {

                            System.out.println("Sending mail...");
                            mailThread(mail);

                            infoBox("Thanks for registering luggage", "Success", null);
                        } else {
                            infoBox("Thanks for registering luggage", "Success", null);
                        }

                    }
                } else {
                    warning.setText(mybundle.getString("EmailIncorrect"));
                }
            } else {
                warning.setText(mybundle.getString("FlightnumberInvalid"));
            }
        } else if(excelPath == null){
            System.out.println("niet alle verplichte velden ingevuld");
            warning.setText(mybundle.getString("NietAllesIngevuld"));
        }

        if (excelPath != null) {
            if (excelPath.contains(".xlsx")) {
                System.out.println("test");
                System.out.println(excelPath);

                excelThread();
                infoBox("Thanks for registering luggage", "Success", null);
            } else {
                warning.setText(mybundle.getString("WrongFile"));
            }

        }

    }

    private void excelThread() {

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                excelImport(excelPath);

            }
        });
    }

    private void mailThread(final Mail mail) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                mail.mailsturen();
                System.out.println("Mail sent...");

            }
        });
    }

    @FXML
    private void backToLogin() {
        utilities.newAnchorpane("LoginEmployee", registerMissingPane);
    }

    @FXML
    private void excelImportPath() {
        // Roept een method aan in de MainApp die het path returnt
        excelPath = MainApp.fileChoosePath();
        System.out.println("String path: " + excelPath);
        warning.setText(excelPath);
    }

    // Method om een Excelsheet te importeren
    private void excelImport(String filePath) {
        System.out.println("Beginning Excel import...");

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
            String luggagetype = db.executeStringListQuery(String.format("SELECT `idluggage_type` FROM Luggagetype WHERE english = '%s' OR dutch = '%s' OR spanish = '%s' OR turkish = '%s';", row.get(3), row.get(3), row.get(3), row.get(3)));
            String maincolor = db.executeStringListQuery(String.format("SELECT `ralcode` FROM Colour WHERE english = '%s' OR dutch = '%s' OR spanish = '%s' OR turkish = '%s';", row.get(8), row.get(8), row.get(8), row.get(8)));
            String secondcolor = db.executeStringListQuery(String.format("SELECT `ralcode` FROM Colour WHERE english = '%s' OR dutch = '%s' OR spanish = '%s' OR turkish = '%s';", row.get(9), row.get(9), row.get(9), row.get(9)));
            String locationfound = db.executeStringListQuery(String.format("SELECT `idlocation` FROM Location WHERE english = '%s' OR dutch = '%s' OR spanish = '%s' OR turkish = '%s';", row.get(7), row.get(7), row.get(7), row.get(7)));

            // Luggage tag, als er geen is wordt de value op 0 gezet
            String luggagetag = row.get(6);
            if ("".equals(luggagetag)) {
                luggagetag = "0";
            }

            // Kijkt of er een ' voorkomt en als dat zo is zet er een \ voor
            String passengernamecity = row.get(12);
            if (passengernamecity.contains("'")) {
                passengernamecity = passengernamecity.substring(0, passengernamecity.indexOf("'")) + "\\"
                        + passengernamecity.substring(passengernamecity.indexOf("'"), passengernamecity.length());
            }

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

}