/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Rick & Stijn :)
 */
public class RegisterMissingController implements Initializable {

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
    private TextField BagageDestination;
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
    private Label warning;
    @FXML
    private TextField generalDate;
    @FXML
    private TextField generalTime;

    @FXML
    private CheckBox mailSturen;

    // Path van het excel bestand (als die er is)
    private String excelPath;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    private AnchorPane registerMissingPane, thankYouPage;

    Utilities utilities = new Utilities();

    //submit button
    @FXML
    private void openRegisterThankyou(ActionEvent event) {

        if (!TravellerFirstName.getText().trim().isEmpty()
                && !TravellerSurname.getText().trim().isEmpty()
                && !TravellerAdress.getText().trim().isEmpty()
                && !TravellerCity.getText().trim().isEmpty()
                && !TravellerPostalCode.getText().trim().isEmpty()
                && !TravellerCountry.getText().trim().isEmpty()
                && !TravellerPhone.getText().trim().isEmpty()
                && !TravellerEmail.getText().trim().isEmpty()
                && !BagageFlight.getText().trim().isEmpty()
                && !BagageLabel.getText().trim().isEmpty()
                && !BagageDestination.getText().trim().isEmpty()) {

            Database db = new Database();

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

            String luggageInformation = String.format("INSERT INTO "
                    + "Lostbagage (dateregistered, timeregistered, luggagelabelnr, passenger_name_city, luggagetype, brand, primarycolour, secondarycolour, otherchar, flightnumber) "
                    + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    generalDate.getText(), generalTime.getText(), BagageLabel.getText(),
                    BagageDestination.getText(), LuggageTypeSelect,
                    BagageBrand.getText(), BagagePrimaryColourString, BagageSecondaryColourString,
                    BagageSpecialchar.getText(), BagageFlight.getText());

            db.executeUpdateQuery(travellerInformation);
            db.executeUpdateQuery(luggageInformation);

            Pdf pdf = new Pdf();
            pdf.printPDF(TravellerFirstName.getText(), TravellerSurname.getText(),
                    TravellerAdress.getText(), TravellerCity.getText(),
                    TravellerPostalCode.getText(), TravellerCountry.getText(),
                    TravellerPhone.getText(), TravellerEmail.getText(),
                    BagageLabel.getText(), BagageFlight.getText(),
                    BagageDestination.getText(), pdf_inputluggagetype,
                    BagageBrand.getText(), pdf_inputprimarycolour,
                    pdf_inputsecondarycolour,
                    BagageSpecialchar.getText());

            if (mailSturen.isSelected()) {
                System.out.println("Sending mail...");
                Mail mail = new Mail(TravellerEmail.getText().trim());
                mail.mailsturen();
                System.out.println("Mail sent...");
            }

            utilities.newAnchorpane("RegisterMissing_thankyou", registerMissingPane);
        } else if (!excelPath.isEmpty()) {
            excelImport(excelPath);
        } else {
            System.out.println("niet alle verplichte velden ingevuld");
            warning.setText("Niet alle verplichte velden zijn ingevuld.");
        }
    }

    @FXML
    private void backToLogin() {
        utilities.newAnchorpane("LoginEmployee", registerMissingPane);
    }

    @FXML
    private void excelImportPath() {
        // Roept een method aan in de MainApp die het path returnt
        // TODO: Moet alleen een excel kunnen zijn
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

}

/*public String email(String input){
    mail = input + "hallo";
    return mail;
    }
    
           
    Utilities utilities = new Utilities();
    //submit button
    @FXML
    private void openRegisterThankyou(ActionEvent event) {
        
            
        
        if (!TravellerFirstName.getText().trim().isEmpty()&&
            !TravellerSurname.getText().trim().isEmpty()&&
            !TravellerAdress.getText().trim().isEmpty()&&
            !TravellerCity.getText().trim().isEmpty()&&
            !TravellerPostalCode.getText().trim().isEmpty()&&
            !TravellerCountry.getText().trim().isEmpty()&&    
            !TravellerPhone.getText().trim().isEmpty()&&    
            !TravellerEmail.getText().trim().isEmpty()&&
            !BagageFlight.getText().trim().isEmpty()&&
            !BagageLabel.getText().trim().isEmpty()&&
            !BagageDestination.getText().trim().isEmpty()
            ){
            
           
            
           
        
        Database db = new Database();
        
        
                
          String travellerInformation = String.format      
        ("INSERT INTO Passenger "
                + "(firstname, lastname, adress, city, zip, country, phone, email, flightnumber) "
                + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", 
                TravellerFirstName.getText(), TravellerSurname.getText(),
                TravellerAdress.getText(), TravellerCity.getText(),
                TravellerPostalCode.getText(), TravellerCountry.getText(), 
                TravellerPhone.getText(), TravellerEmail.getText(),
                BagageFlight.getText());
        
        String luggageInformation = String.format("INSERT INTO "
                + "Bagage (labelnumber, destination, type, brand, colour, specialchar, flightnumber) "
                + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s','%s')",
                BagageLabel.getText(), 
                BagageDestination.getText(), BagageType.getText(), 
                BagageBrand.getText(), BagageColour.getText(), 
                BagageSpecialchar.getText(), BagageFlight.getText());
        
        Pdf pdf = new Pdf();
        pdf.printPDF(TravellerFirstName.getText(), TravellerSurname.getText(), 
                TravellerAdress.getText(), TravellerCity.getText(), 
                TravellerPostalCode.getText(), TravellerCountry.getText(), 
                TravellerPhone.getText(), TravellerEmail.getText());
        
        if (mailSturen.isSelected()){
        System.out.println("Sending mail...");    
        Mail mail = new Mail(TravellerEmail.getText().trim());
        mail.mailsturen();
        System.out.println("Mail sent...");
        }
        
        
        
        db.executeUpdateQuery(travellerInformation);
        db.executeUpdateQuery(luggageInformation);
        
        
        utilities.newAnchorpane("RegisterMissing_thankyou", registerMissingPane); 
        
         
        
    }
        else 

            System.out.println("niet alle verplichte velden ingevuld");
        }
        warning.setText("Niet alle verplichte velden zijn ingevuld");

    }

    @FXML
    private void backToLogin() {
        utilities.newAnchorpane("LoginEmployee", registerMissingPane);
    }

    
    @FXML
    private void backToLoginTY() {
        utilities.newAnchorpane("LoginEmployee", thankYouPage);
    }
 */
