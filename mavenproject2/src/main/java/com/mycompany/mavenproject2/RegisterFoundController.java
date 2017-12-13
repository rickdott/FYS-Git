/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Tarik Yildirim Eshketittttttttttttttttt 500780772
 */
public class RegisterFoundController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date date = new Date();
        GeneralDate2.setText(dateFormat.format(date));
        
       

    }
    @FXML
    TextField GeneralDate2;

    @FXML
    private AnchorPane RegisterFoundPane;

    /**
     *
     */
    public RegisterFoundController() {

    }
    Utilities utilities = new Utilities();

    @FXML
    private void openRegisterThankyou(ActionEvent event) {
        utilities.newAnchorpane("RegisterMissing_thankyou", RegisterFoundPane);
    }

//   GetCurrentDateTime time = new GetCurrentDateTime();
}
