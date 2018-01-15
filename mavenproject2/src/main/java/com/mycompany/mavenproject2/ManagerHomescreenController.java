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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Controller for the manager homescreen
 *
 * @author Matthijs Snijders 500780453 (113 lines)
 */
public class ManagerHomescreenController implements Initializable {

    @FXML
    private AnchorPane actualmain;

    @FXML
    private Pane mainpage;

    @FXML
    private Label label1;

    @FXML
    private Button but1, but2, but3, but4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    Utilities utilities = new Utilities();

    //Methods to open other pages
    @FXML
    private void openRequestStatus(ActionEvent event) {
        utilities.newPane("RequestStatus", but1, mainpage, label1);
    }

    @FXML
    private void openRegisterMissing(ActionEvent event) {
        utilities.newPane("RegisterMissing", but2, mainpage, label1);
    }

    @FXML
    private void openReports(ActionEvent event) {
        utilities.newPane("Reports", but3, mainpage, label1);
    }

    @FXML
    private void openUserRoles(ActionEvent event) {
        utilities.newPane("UserRoles", but4, mainpage, label1);
    }

    // All methods for hovering off a button
    @FXML
    private void offHoverbut1() {
        utilities.offHover("Request Status", but1, label1);
    }

    @FXML
    private void offHoverbut2() {
        utilities.offHover("Register Missing", but2, label1);
    }

    @FXML
    private void offHoverbut3() {
        utilities.offHover("Reports", but3, label1);
    }

    @FXML
    private void offHoverbut4() {
        utilities.offHover("UserRoles", but4, label1, true);
    }

    // All methods for hovering over a button
    //Vanaf hier
    @FXML
    private void onHoverbut1() {
        utilities.onHover("Request Status", but1, label1);
    }

    @FXML
    private void onHoverbut2() {
        utilities.onHover("Register Missing", but2, label1);
    }

    @FXML
    private void onHoverbut3() {
        utilities.onHover("Reports", but3, label1);
    }

    @FXML
    private void onHoverbut4() {
        utilities.onHover("User Roles", but4, label1);
    }

    @FXML
    private void backToLogin(ActionEvent event) {
        utilities.newAnchorpane("LoginEmployee", actualmain);

    }
}