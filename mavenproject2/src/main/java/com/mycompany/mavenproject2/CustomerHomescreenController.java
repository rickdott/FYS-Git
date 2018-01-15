package com.mycompany.mavenproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the customer homescreen, only has a few buttons to help 
 * with navigating the customer portion of the application
 * @author Tarik 500780772 (74 lines)
 *
 */
public class CustomerHomescreenController {

    @FXML
    private AnchorPane paneCustomerHomescreen;
    
    @FXML
    private Button reqStatBut, regMisBut, faqBut;
    
    Utilities utilities = new Utilities();
    
    @FXML
    private void openRequestStatus(ActionEvent event) {
        utilities.newAnchorpane("RequestStatusKlanten", paneCustomerHomescreen);
    }
    
    @FXML
    private void openRegisterMissing(ActionEvent event) {
        utilities.newAnchorpane("RegisterMissingPassenger", paneCustomerHomescreen);
    }
    
    @FXML
    private void openFaq(ActionEvent event) {
        utilities.newAnchorpane("FAQ", paneCustomerHomescreen);
    }
    
    @FXML
    private void backToLogin() {
        utilities.newAnchorpane("Login", paneCustomerHomescreen);
    }
    
    @FXML
    private void onHoverReqStat() {
        Utilities.onHover(reqStatBut);
    }
    
    @FXML
    private void offHoverReqStat() {
        Utilities.offHover(reqStatBut);
    }
    
    @FXML
    private void onHoverRegMiss() {
        Utilities.onHover(regMisBut);
    }
    
    @FXML
    private void offHoverRegMiss() {
        Utilities.offHover(regMisBut);
    }
    
    @FXML
    private void onHoverFaqBut() {
        Utilities.onHover(faqBut);
    }
    
    @FXML
    private void offHoverFaqBut() {
        Utilities.offHover(faqBut);
    }
    
}