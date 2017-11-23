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
 *
 * @author Rick
 */
public class RegisterMissingController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    FXMLDocumentController controller = new FXMLDocumentController();

    @FXML
    private AnchorPane actualmain;

    @FXML
    private void openRegisterThankyou(ActionEvent event) {
        controller.newAnchorpane("RegisterMissing_thankyou", actualmain);
    }

}
