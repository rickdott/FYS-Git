/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the thank you message of the register missing screen
 * @author Stijn Klopper 500770512 (31 lines)
 */
public class RegisterMissingThankYouController {
    Utilities utilities = new Utilities();
    @FXML
    private AnchorPane registerMissingPane, thankYouPage;
    
    
    @FXML
    private void backToLogin() {
        utilities.newAnchorpane("LoginEmployee", registerMissingPane);
    }
    
    @FXML
    private void backToLoginTY() {
        utilities.newAnchorpane("LoginEmployee", thankYouPage);
    }
}
