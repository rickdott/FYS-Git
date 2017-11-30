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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author Rick & Stijn :)
 */
public class RegisterMissingController implements Initializable {
    
    
    
    
    
    //input Traveller
    @FXML private TextField TravellerFirstName;
    @FXML private TextField TravellerSurname;
    @FXML private TextField TravellerAdress;
    @FXML private TextField TravellerCity;
    @FXML private TextField TravellerPostalCode;
    @FXML private TextField TravellerCountry;
    @FXML private TextField TravellerPhone;
    @FXML private TextField TravellerEmail;
    @FXML private TextField BagageFlight;
    
    //input bagage
    @FXML private TextField BagageLabel;
    @FXML private TextField BagageDestination;
    @FXML private TextField BagageType;
    @FXML private TextField BagageBrand;
    @FXML private TextField BagageColour;
    @FXML private TextField BagageSpecialchar;
    
    @FXML private Label warning;
    @FXML private TextField generalDate;
    @FXML private TextField generalTime;
    
        @FXML private CheckBox mailSturen;
    
    
    
    public RegisterMissingController() {
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Datum doet het nog niet :( bug fixen
   /*DateFormat datum = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat tijd = new SimpleDateFormat("HH:mm:ss");
        
        Date date = new Date();
        Date time = new Date();
        
        generalDate.setText((tijd.format(date)));
        generalTime.setText((datum.format(date))); */
   
        
        
    }
    
    @FXML
    private AnchorPane registerMissingPane, thankYouPage;
    
    /*public String email(String input){
    mail = input + "hallo";
    return mail;
    } */
    
           
    FXMLDocumentController controller = new FXMLDocumentController();
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
        
        
        controller.newAnchorpane("RegisterMissing_thankyou", registerMissingPane); 
        
         
        
    }
        else 
            System.out.println("niet alle verplichte velden ingevuld");
            warning.setText("Niet alle verplichte velden zijn ingevuld");
            
          
    }
    @FXML
    private void backToLogin() {
        controller.newAnchorpane("LoginEmployee", registerMissingPane);
    }
    
    @FXML
    private void backToLoginTY() {
        controller.newAnchorpane("LoginEmployee", thankYouPage);
    }
    
    
}
