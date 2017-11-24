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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Rick
 */
public class RegisterMissingController implements Initializable {
    
    @FXML
    private TextField TravellerName;
    private TextField TravellerAdress;
    private TextField TravellerCity;
    private TextField TravellerPostalCode;
    private TextField TravellerCountry;
    private TextField TravellerPhone;
    private TextField TravellerEmail;

    public RegisterMissingController() {
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    private AnchorPane actualmain, thankyoupage;
        
    FXMLDocumentController controller = new FXMLDocumentController();
    //submit button
    @FXML
    private void openRegisterThankyou(ActionEvent event) {
        controller.newAnchorpane("RegisterMissing_thankyou", actualmain);
        Database db = new Database();
        String sql = String.format("INSERT INTO testje (idtestje) VALUES ('%s')", TravellerName.getText());
        db.executeUpdateQuery(sql);
    }
    
    @FXML
    private void backToLogin(ActionEvent event) {
        controller.newAnchorpane("Login", thankyoupage);
    }
    
    
    
    

}
