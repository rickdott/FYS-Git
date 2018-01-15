package com.mycompany.mavenproject2;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * Has to be implemented
 * @author Tarik Yildirim 500780772
 */
public class RegisterFoundController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date date = new Date();
        GeneralDate2.setText(dateFormat.format(date));
        
        ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");
        
        labelGeneral.setText(mybundle.getString("General"));
        labelDate.setText(mybundle.getString("Date"));
        labelTime.setText(mybundle.getString("Time"));
        labelAirport.setText(mybundle.getString("Airport"));       
        labelLocation.setText(mybundle.getString("Location_Found"));
        labelLuggage.setText(mybundle.getString("Luggage_Label"));
        labelLabelNumber.setText(mybundle.getString("Label_Number*"));
        labelFlightNumber.setText(mybundle.getString("Flight_Number*"));
        labelDestination.setText(mybundle.getString("Destination*"));       
        labelLuggageInformation.setText(mybundle.getString("Luggage_Information")); 
        labelType.setText(mybundle.getString("Type"));
        labelBrand.setText(mybundle.getString("Brand"));
        labelColour.setText(mybundle.getString("Colour"));
        labelSpecialChar.setText(mybundle.getString("Special_Characteristics"));       
        labelSubmit.setText(mybundle.getString("Submit"));  
        labelRequired.setText(mybundle.getString("Fields_with_*_are_required_to_fill_in"));  
    }
        
    @FXML
    private Text labelGeneral, labelDate, labelTime, labelAirport, labelLocation, labelLuggage, labelLabelNumber,
            labelFlightNumber, labelDestination, labelLuggageInformation, labelType,labelBrand,labelColour,labelSpecialChar,labelRequired;

    @FXML
    private TextField GeneralDate2;

    @FXML
    private AnchorPane RegisterFoundPane;
    
    @FXML
    private Button labelSubmit;
    
    Utilities utilities = new Utilities();

    @FXML
    private void openRegisterThankyou(ActionEvent event) {
        utilities.newAnchorpane("RegisterMissing_thankyou", RegisterFoundPane);
    }

}
