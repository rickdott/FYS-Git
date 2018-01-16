package com.mycompany.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for the customer homescreen, only has a few buttons to help with
 * navigating the customer portion of the application
 *
 * @author Tarik 500780772 (74 lines)
 *
 */
public class CustomerHomescreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");

        reqStatBut.setText(mybundle.getString("request_status"));
        regMisBut.setText(mybundle.getString("register_missing"));
        //faqBut.setText(mybundle.getString("faq"));

    }

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
