/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //String Check = "SELECT * FROM Employee WHERE function = Medewerker";
        //but4.setVisible(false);
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
        Utilities.offHover("Request Status", but1, label1);
    }

    @FXML
    private void offHoverbut2() {
        Utilities.offHover("Register Missing", but2, label1);
    }

    @FXML
    private void offHoverbut3() {
        Utilities.offHover("Reports", but3, label1);
    }

    @FXML
    private void offHoverbut4() {
        Utilities.offHover("User Roles", but4, label1, true);
    }
    
    // All methods for hovering over a button
    @FXML
    private void onHoverbut1() {
        Utilities.onHover("Request Status", but1, label1);
    }

    @FXML
    private void onHoverbut2() {
        Utilities.onHover("Register Missing", but2, label1);
    }

    @FXML
    private void onHoverbut3() {
        Utilities.onHover("Reports", but3, label1);
    }

    @FXML
    private void onHoverbut4() {
        Utilities.onHover("User Roles", but4, label1, true);
    }
    
    @FXML
    private void backToLogin(ActionEvent event) {
        utilities.newAnchorpane("LoginEmployee", actualmain);  
    
}
}
