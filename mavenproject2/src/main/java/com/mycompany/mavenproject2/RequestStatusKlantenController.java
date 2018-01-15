/*
 * To change this license header, choose License Headeresult in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the customer section of the request status
 *
 * @author Timur Yagci 500764449 (148 lines) Met hulp van Rick den Otter
 * 500749952
 */
public class RequestStatusKlantenController {

    @FXML
    private AnchorPane requestpage;

    @FXML
    private TextField lastNameField, labelNrField;
    
    @FXML
    private TextField addressField, numberField, cityField, countryField, zipCodeField;

    @FXML
    private HBox hBoxLost, hBoxFound;
    
    @FXML
    private VBox choiceVBox, shippingVBox;
    
    @FXML
    private Label statusMessage, addressLabel, zipCodeLabel, cityAndCountryLabel;
        
    @FXML
    private RadioButton choicePickup, choiceAddress;

    @FXML
    private void getInput() throws SQLException {
        String lastName = lastNameField.getText();
        String labelnr = labelNrField.getText();

        Database database = new Database();
        if (lastNameField.getText().isEmpty() || labelNrField.getText().isEmpty()) {
            System.out.println("No name or label number entered");
            statusMessage.setText("The information you entered is not complete.");
        } else {
            ResultSet resultLost = database.executeResultSetQuery(makeQuery("Lostbagage", labelnr, lastName));
            ResultSet resultFound = database.executeResultSetQuery(makeQuery("Foundbagageinventory", labelnr, lastName));

            ArrayList<LostLuggage> listLost = new ArrayList<>();
            ArrayList<FoundLuggage> listFound = new ArrayList<>();

            listLost = Utilities.listOfLostLuggageFromResultSet(resultLost, listLost);
            listFound = Utilities.listOfFoundLuggageFromResultSet(resultFound, listFound);

            if (!listLost.isEmpty() && !listFound.isEmpty()) {
                // Luggage is both lost and found, case is solved
                choiceVBox.setVisible(true);
                statusMessage.setText("Lost luggage has been found");
                System.out.println("Luggage is both lost and found");
            } else if (!listLost.isEmpty()) {
                //Luggage is only lost
                System.out.println("Luggage is missing");
                statusMessage.setText("Luggage is missing!");
                LostLuggage luggage = (LostLuggage) listLost.get(0);

                hBoxFound.setVisible(false);
                initializeLostFields(luggage);
                hBoxLost.setVisible(true);
            } else if (!listFound.isEmpty()) {
                //Luggage is only found
                System.out.println("Luggage has been found");
                statusMessage.setText("Luggage has been found!");
                FoundLuggage luggage = (FoundLuggage) listFound.get(0);

                hBoxLost.setVisible(false);
                initializeFoundFields(luggage);
                hBoxFound.setVisible(true);
                choiceVBox.setVisible(true);
            } else {
                // Luggage is neither lost or found, unknown luggage or wrong 
                // information has been entered
                System.out.println("WRONGFUL INFORMATION, LUGGAGE NOT FOUND");
                statusMessage.setText("The information you entered does not match a piece of luggage in our systems.");
                hBoxLost.setVisible(false);
                hBoxFound.setVisible(false);
            }

            // Closing open connections
            resultLost.close();
            resultFound.close();
            database.close();
        }
        // back button

    }

    @FXML
    private void submitChoice() throws SQLException {
        //read choice from radio buttons
        if (choicePickup.isSelected()) {
            
        } else {
            //customer wants to deliver to another address, get info from textfields
            long labelnumber = Long.parseLong(labelNrField.getText());
            String address = addressField.getText();
            String number = numberField.getText();
            String city = cityField.getText();
            String country = countryField.getText();
            String zip = zipCodeField.getText();
            
            
            hBoxLost.setVisible(false);
            hBoxFound.setVisible(false);
            shippingVBox.setVisible(true);
            
            addressLabel.setText(String.format("%s %s", address, number));
            zipCodeLabel.setText(String.format("%s", zip));
            cityAndCountryLabel.setText(String.format("%s, %s", city, country));
            Utilities.isSolvedLabelnr(labelnumber, true);
            
        }
    }
    
    private String makeQuery(String fromTable, String labelNr, String lastName) {
        String query = String.format("SELECT * FROM %s WHERE luggagelabelnr = '%s' AND passenger_name_city LIKE '%%%s%%'", fromTable, labelNr, lastName);

        return query;
    }

    private void initializeFoundFields(FoundLuggage luggage) {
        //Loop through all children nodes of FoundHBox that are instanceof label
        //If the label is empty, assign text value, ++
        ObservableList<Node> list = hBoxFound.getChildren();
        ObservableList<Node> listOfStackPanes = FXCollections.observableArrayList();
        ObservableList<Label> listOfLabels = FXCollections.observableArrayList();

        for (int i = 0; i < list.size(); i++) {
            VBox vbox = (VBox) list.get(i);
            listOfStackPanes.addAll(vbox.getChildren());
        }

        for (int i = 0; i < listOfStackPanes.size(); i++) {
            StackPane stackPane = (StackPane) listOfStackPanes.get(i);
            ObservableList<Node> list2 = stackPane.getChildren();
            listOfLabels.add((Label) list2.get(1));
        }

        ArrayList<String> infoList = luggage.getLuggageInfo();

        for (int i = 0; i < listOfLabels.size(); i++) {
            listOfLabels.get(i).setText(infoList.get(i));
        }

    }

    private void initializeLostFields(LostLuggage luggage) {
        ObservableList<Node> list = hBoxLost.getChildren();
        ObservableList<Node> listOfStackPanes = FXCollections.observableArrayList();
        ObservableList<Label> listOfLabels = FXCollections.observableArrayList();

        for (int i = 0; i < list.size(); i++) {
            VBox vbox = (VBox) list.get(i);
            listOfStackPanes.addAll(vbox.getChildren());
        }

        for (int i = 0; i < listOfStackPanes.size(); i++) {
            StackPane stackPane = (StackPane) listOfStackPanes.get(i);
            ObservableList<Node> list2 = stackPane.getChildren();
            listOfLabels.add((Label) list2.get(1));
        }

        ArrayList<String> infoList = luggage.getLuggageInfo();

        System.out.println(listOfLabels.size());
        for (int i = 0; i < listOfLabels.size(); i++) {
            listOfLabels.get(i).setText(infoList.get(i));
        }

    }

    @FXML
    private void backToLogin() {
        Utilities utilities = new Utilities();
        utilities.newAnchorpane("Login", requestpage);
    }
}
