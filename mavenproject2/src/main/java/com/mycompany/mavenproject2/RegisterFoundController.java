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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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

        ObservableList<String> colours = FXCollections.observableArrayList(
                "Yellow", "Olive", "Red", "Darkred", "Pink", "Purple", "Violet",
                "Blue", "Lightblue", "Darkblue", "Bluegreen", "Green", "Darkgreen",
                "Lightgreen", "Gray", "Darkgray", "Lightgray", "Brown", "Darkbrown",
                "Lightbrown", "White", "Black", "Cream");

        ObservableList<String> Luggagetypes = FXCollections.observableArrayList(
                "Suitcase", "Bag", "Bagpack", "Box", "Sports",
                "Bag", "Business Case", "Case", "Other");

        BagagePrimaryColour.setItems(colours);
        BagageSecondaryColour.setItems(colours);
        LuggageType.setItems(Luggagetypes);

    }
    //Alle text velden en objecten in de FMXL page worden aangeroepen met hun code
  

    @FXML
    private TextField BagageLabel;
    @FXML
    private TextField BagageFlight;
    @FXML
    private TextField BagageDestination;
    @FXML
    private ComboBox<String> LuggageType;
    @FXML
    private TextField BagageBrand;
    @FXML
    private ComboBox<String> BagagePrimaryColour;
    @FXML
    private ComboBox<String> BagageSecondaryColour;
    @FXML
    private TextField BagageSpecialchar;
    @FXML
    TextField GeneralDate2;
    @FXML
    TextField GeneralTime;
    
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
        System.out.println("Test!!");
        System.out.println(LuggageType.selectionModelProperty());
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(-2));
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(-1));
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(0));
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(1));
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(2));
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(3));

        if (!BagagePrimaryColour.getSelectionModel().isSelected(-1)
                && !BagageSecondaryColour.getSelectionModel().isSelected(-1)
                && !LuggageType.getSelectionModel().isSelected(-1)
                && !BagageBrand.getText().trim().isEmpty()
                && !BagageSpecialchar.getText().trim().isEmpty()
                && !BagageFlight.getText().trim().isEmpty()
                && !BagageLabel.getText().trim().isEmpty()
                && !BagageDestination.getText().trim().isEmpty()) {
            utilities.newAnchorpane("RegisterMissing_thankyou", RegisterFoundPane);
        }
    }
}
