/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    //dropdownlists
    ObservableList<String> colours = FXCollections.observableArrayList(
            "Yellow", "Olive", "Red", "Darkred", "Pink", "Purple", "Violet",
            "Blue", "Lightblue", "Darkblue", "Bluegreen", "Green", "Darkgreen",
            "Lightgreen", "Gray", "Darkgray", "Lightgray", "Brown", "Darkbrown",
            "Lightbrown", "White", "Black", "Cream");

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
        }
    }

    @FXML
    private void backToLogin() {
        utilities.newAnchorpane("LoginEmployee", registerMissingPane);
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
