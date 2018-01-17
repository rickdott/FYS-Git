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
 * @author Tarik 500780772 (77 lines)
 *
 */
public class CustomerHomescreenController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");

        reqStatBut.setText(mybundle.getString("request_status"));
        regMisBut.setText(mybundle.getString("register_missing"));

    }

    @FXML
    private AnchorPane paneCustomerHomescreen;

    @FXML
    private Button reqStatBut, regMisBut;

    Utilities utilities = new Utilities();

    @FXML
    private void openRequestStatus(ActionEvent event) {
        utilities.newAnchorpane("RequestStatusKlanten", paneCustomerHomescreen);
    }

    @FXML
    private void openRegisterMissing(ActionEvent event) {
        utilities.newAnchorpane("RegisterMissing_1", paneCustomerHomescreen);
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
        reqStatBut.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-background-color: #951504; -fx-background-radius: 0;");
    }

    @FXML
    private void offHoverReqStat() {
        reqStatBut.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-background-color: #d81e05; -fx-background-radius: 0;");
    }

    @FXML
    private void onHoverRegMiss() {
        regMisBut.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-background-color: #951504; -fx-background-radius: 0;");
    }

    @FXML
    private void offHoverRegMiss() {
        regMisBut.setStyle("-fx-font-weight: bold; -fx-font-size: 30; -fx-background-color: #d81e05; -fx-background-radius: 0;");
    }
}
