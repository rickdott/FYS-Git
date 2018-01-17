package com.mycompany.mavenproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the thankyou page of register missing passenger
 *
 * @author Stijn Klopper 500770512 (27 Lines)
 */
public class RegisterMissingPassengerThankYouController {

    @FXML
    private AnchorPane thankYouPage;
    @FXML
    private Button buttonBacktohome;
    
    @FXML
    private void backToLoginTY(ActionEvent event) {
        Utilities utilities = new Utilities();
        utilities.newAnchorpane("CustomerHomescreen", thankYouPage);
    }
    
}
