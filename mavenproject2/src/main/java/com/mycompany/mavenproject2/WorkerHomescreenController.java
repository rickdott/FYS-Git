/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Rick, Matthijs
 */
public class WorkerHomescreenController implements Initializable {
    
    @FXML
    private AnchorPane actualmain;
    
    @FXML
    private Pane mainpage;

    @FXML
    private Label label1;

    @FXML
    private Button but1, but2, but3, but4;
    
    Connection connection = null;
    public WorkerHomescreenController() {
        connection = sqlDatabaseConnection.connectdb();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String Check = "SELECT * FROM Employee WHERE function = Medewerker";
        //but4.setVisible(false);
    }
    
    FXMLDocumentController controller = new FXMLDocumentController();
    
    //Methods to open other pages
    @FXML
    private void openRequestStatus(ActionEvent event) {
        controller.newPane("RequestStatus", but1, mainpage, label1);
    }

    @FXML
    private void openRegisterMissing(ActionEvent event) {
        controller.newPane("RegisterMissing", but2, mainpage, label1);
    }

    @FXML
    private void openReports(ActionEvent event) {
        controller.newPane("Reports", but3, mainpage, label1);
    }
    
    
    @FXML
    private void openUserRoles(ActionEvent event) {
        controller.newPane("UserRoles", but4, mainpage, label1);
    }

    
    // All methods for hovering off a button
    @FXML
    private void offHoverbut1() {
        controller.offHover("Request Status", but1, label1);
    }

    @FXML
    private void offHoverbut2() {
        controller.offHover("Register Missing", but2, label1);
    }

    @FXML
    private void offHoverbut3() {
        controller.offHover("Reports", but3, label1);
    }

    @FXML
    private void offHoverbut4() {
        controller.offHover("User Roles", but4, label1, true);
    }
    
    // All methods for hovering over a button
    @FXML
    private void onHoverbut1() {
        controller.onHover("Request Status", but1, label1);
    }

    @FXML
    private void onHoverbut2() {
        controller.onHover("Register Missing", but2, label1);
    }

    @FXML
    private void onHoverbut3() {
        controller.onHover("Reports", but3, label1);
    }

    @FXML
    private void onHoverbut4() {
        controller.onHover("User Roles", but4, label1, true);
    }
    
    @FXML
    private void backToLogin(ActionEvent event) {
        controller.newAnchorpane("LoginEmployee", actualmain);
    }
    
    
}
