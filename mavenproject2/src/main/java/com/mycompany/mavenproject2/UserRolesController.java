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

/**
 * YET TO BE IMPLEMENTED
 * @author Tarik 500780772
 */
public class UserRolesController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");
        
        but1.setText(mybundle.getString("Add_user"));
        labelName.setText(mybundle.getString("Name"));
        labelRoles.setText(mybundle.getString("Roles"));
        labelRole.setText(mybundle.getString("Rol"));
        labelNameperson.setText(mybundle.getString("Name_Person"));
        labelSubmit.setText(mybundle.getString("Submit"));

    }
        
    @FXML
    private Button but1;
    
    private Label labelName, labelRoles, labelRole, labelNameperson, labelSubmit;
// End of translation lines
  
    
    
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
