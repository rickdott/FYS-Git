package com.mycompany.mavenproject2;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *
 * @author Matthijs Snijders 500780453 (32 lines)
 */
public class MainController {
    
    @FXML
    private Label label;
    
    @FXML
    private TextField txtUsername;
    
    @FXML
    private TextField txtPassword;
    
    
    public void Login(ActionEvent event) {
        if (txtUsername.getText().equals("username") && txtPassword.getText().equals("password")) {
            label.setText("Login succes");
        } else {
            label.setText("Login failed");
        }
    }
}
