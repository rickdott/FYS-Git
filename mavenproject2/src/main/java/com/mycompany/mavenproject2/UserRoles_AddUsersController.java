/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Tarik Yildirim 500780772
 */
public class UserRoles_AddUsersController implements Initializable {
    
     ObservableList<String> Roles = FXCollections.observableArrayList(
                "Manager", "Medewerker");
    @FXML
    private ComboBox<String> Role;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Role.setItems(Roles);
    
    }
    Utilities utilities = new Utilities();
}
