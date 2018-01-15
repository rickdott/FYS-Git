/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the thank you message of the register missing screen
 * @author Stijn Klopper 500770512 (31 lines)
 */
public class RegisterMissingThankYouController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");
        
        labelThankyou.setText(mybundle.getString("Missing_luggage_registered!_Thank_you!"));
        buttonBacktohome.setText(mybundle.getString("Back_to_Home"));


    }
        
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
