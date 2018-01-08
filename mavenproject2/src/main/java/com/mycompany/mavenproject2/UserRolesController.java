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
 * YET TO BE IMPLEMENTED
 * @author Tarik 500780772
 */
public class UserRolesController implements Initializable {
    
    
    @FXML
    private AnchorPane mainpane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    Utilities utilities = new Utilities();
    
    @FXML
    private void openAddRoles(ActionEvent event) {
        utilities.newAnchorpane("UserRoles_AddUser", mainpane);
    }
}
