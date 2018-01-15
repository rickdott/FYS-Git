package com.mycompany.mavenproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the customer homescreen
 * @author Tarik 500780772 (73 lines)
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
        utilities.newAnchorpane("LoginEmployee", paneCustomerHomescreen);
    }
    
    @FXML
    private void onHoverReqStat() {
        utilities.onHover(reqStatBut);
    }
    
    @FXML
    private void offHoverReqStat() {
        utilities.offHover(reqStatBut);
    }
    
    @FXML
    private void onHoverRegMiss() {
        utilities.onHover(regMisBut);
    }
    
    @FXML
    private void offHoverRegMiss() {
        utilities.offHover(regMisBut);
    }
    
    @FXML
    private void onHoverFaqBut() {
        utilities.onHover(faqBut);
    }
    
    @FXML
    private void offHoverFaqBut() {
        utilities.offHover(faqBut);
    }
    
}