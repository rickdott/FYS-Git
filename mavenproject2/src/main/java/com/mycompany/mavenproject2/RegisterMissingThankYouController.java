package com.mycompany.mavenproject2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the thank you message of the register missing screen
 * @author Stijn Klopper 500770512 (36 lines)
 */
public class RegisterMissingThankYouController {
        
    @FXML
    private Label labelThankyou;
    
    @FXML
    private Button buttonBacktohome;
  
    Utilities utilities = new Utilities();
    
    @FXML
    private AnchorPane registerMissingPane, thankYouPage;
    
    
    @FXML
    private void backToLogin() {
        utilities.newAnchorpane("LoginEmployee", registerMissingPane);
    }
    
    @FXML
    private void backToLoginTY() {
        utilities.newAnchorpane("RegisterMissing", thankYouPage);
    }
}
