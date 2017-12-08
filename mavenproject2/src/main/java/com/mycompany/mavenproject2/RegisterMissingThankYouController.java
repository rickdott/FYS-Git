/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Stijn
 */
public class RegisterMissingThankYouController {
    FXMLDocumentController controller = new FXMLDocumentController();
    @FXML
    private AnchorPane registerMissingPane, thankYouPage;
    
    
    @FXML
    private void backToLogin() {
        controller.newAnchorpane("LoginEmployee", registerMissingPane);
    }
    
    @FXML
    private void backToLoginTY() {
        controller.newAnchorpane("LoginEmployee", thankYouPage);
    }
}
