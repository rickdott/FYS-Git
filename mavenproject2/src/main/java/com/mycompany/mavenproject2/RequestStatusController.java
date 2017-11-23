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
public class RequestStatusController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    

    @FXML
    private AnchorPane requestpage;        
    FXMLDocumentController controller = new FXMLDocumentController();

    @FXML
    private void openRequestStatusResult(ActionEvent event) {
        controller.newAnchorpane("RequestStatus_Result",requestpage);
    }
    
    // TODO
}
