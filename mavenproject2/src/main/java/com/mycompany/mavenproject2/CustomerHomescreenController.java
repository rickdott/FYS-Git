/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Rick
 */
public class CustomerHomescreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private AnchorPane paneCustomerHomescreen;
    
    Utilities utilities = new Utilities();

    
    @FXML
    private void openRequestStatus(ActionEvent event) {
        utilities.newAnchorpane("RequestStatusKlanten", paneCustomerHomescreen);
    }
    
    @FXML
    private void openRegisterMissing(ActionEvent event) {
        utilities.newAnchorpane("RegisterMissing", paneCustomerHomescreen);
    }
    
    @FXML
    private void openFaq(ActionEvent event) {
        utilities.newAnchorpane("FAQ", paneCustomerHomescreen);
    }
    
    @FXML
    private void backToLogin() {
        utilities.newAnchorpane("Login", paneCustomerHomescreen);
    }
    
}
