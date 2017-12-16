/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Rick
 */

public class RequestStatusController implements Initializable {

    @FXML
    private TableView foundLuggageTableView;

    @FXML
    private Button editSelectedButton, submitButton;

    private ObservableList<Luggage> foundLuggageList
            = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private ComboBox<String> mainColourComboBox;

    @FXML
    private ComboBox<String> secondaryColourComboBox;

    @FXML
    private ToggleGroup lostFoundGroup;

    @FXML
    private AnchorPane requestpage;

    @FXML
    private VBox vbox1, vbox2;

    @FXML
    private HBox hboxRadio, editHbox;

    @FXML
    private TextField regNrField, dateFoundField, timeFoundField,
            brandField, flightNrField, labelNrField, locationFoundField,
            sizeField, weightField, nameAndCityField, specialCharField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        foundLuggageTableView.setItems(this.foundLuggageList);

        for (int i = 0; i < foundLuggageTableView.getColumns().size(); i++) {
            TableColumn tc = (TableColumn) foundLuggageTableView.getColumns().get(i);
            String propertyName = tc.getId();
            if (propertyName != null && !propertyName.isEmpty()) {
                tc.setCellValueFactory(new PropertyValueFactory<>(propertyName));
                System.out.println("Attached column '" + propertyName + "' in tableview to matching attribute");
            }
        }
        typeComboBox.setItems(Utilities.types);
        mainColourComboBox.setItems(Utilities.colours);
        secondaryColourComboBox.setItems(Utilities.colours);
    }

    Utilities utilities = new Utilities();

    @FXML
    private void getInput() throws SQLException {

        String query = getQueryFromTextfields();

        // Create database connection, execute the query
        Database database = new Database();
        ResultSet result = database.executeResultSetQuery(query);
        foundLuggageList.clear();

        // Loop through the resultset, making a new 'FoundLuggage' Object 
        // for every result, adding the attributes of the result to the 
        // corresponding attribute in the FoundLuggage object
        if (lostSelected()) {
            foundLuggageList = Utilities.initializeLostLuggageFromResultSet(result, foundLuggageList);
            makeTableViewLost();
        } else {
            foundLuggageList = Utilities.initializeFoundLuggageFromResultSet(result, foundLuggageList);
            makeTableViewFound();
        }

        result.close();
        database.close();

        // Show the TableView
        makeTextFieldsAndLabelsInvisible();
        makeTableViewVisible();
    }

    @FXML
    private void editSelected() {
        System.out.println("You clicked on edit selected!");

        // Acquire the selected luggage item and fill the textFields with the data
        if (foundLuggageTableView.getSelectionModel().getSelectedItem() != null) {
            if (lostSelected()) {
                LostLuggage luggage = (LostLuggage) foundLuggageTableView.getSelectionModel().getSelectedItem();
                initTextFieldsWithLostLuggage(luggage);
            } else {
                FoundLuggage luggage = (FoundLuggage) foundLuggageTableView.getSelectionModel().getSelectedItem();
                initTextFieldsWithFoundLuggage(luggage);
            }
            makeTableViewInvisible();
            makeTextFieldsAndLabelsVisible();
            submitButton.setVisible(false);
            hboxRadio.setVisible(false);
            editHbox.setVisible(true);
        } else {
            notSelectedBox("Select a row please", "Selection Error", "No row selected!");
        }
    }

    @FXML
    private void resetEdit() {
        // Todo: reset all the textfields to their original values from luggage object
        if (lostSelected()) {
            LostLuggage luggage = (LostLuggage) foundLuggageTableView.getSelectionModel().getSelectedItem();
        } else {
            FoundLuggage luggage = (FoundLuggage) foundLuggageTableView.getSelectionModel().getSelectedItem();
        }
        
    }

    @FXML
    private void deleteEdit() {
        boolean userIsSure = areYouSureBox("Are you sure you want to remove this?", "Remove", "What is this?");
        if (userIsSure) {
            Database database = new Database();
            String regNr = regNrField.getText();
            database.executeUpdateQuery("DELETE FROM Lostbagage WHERE registrationnr = " + regNr);
            makeTextFieldsAndLabelsInvisible();

            for (int i = 0; i < foundLuggageList.size(); i++) {
                if (Integer.toString(foundLuggageList.get(i).getRegistrationnr()).equals(regNr)) {
                    foundLuggageList.remove(i);
                    System.out.println("Removed row with index " + i);
                    break;
                }
            }
            makeTableViewVisible();

        }
    }

    @FXML
    private void cancelEdit() {
        makeTextFieldsAndLabelsInvisible();
        makeTableViewVisible();
    }

    @FXML
    private void saveEdit() {
        // Todo: make an update query with the changed textfields
        // Go through all textfields, see if content is different from .get method

        Database database = new Database();
        String query;

        if (lostSelected()) {
            LostLuggage luggage = (LostLuggage) foundLuggageTableView.getSelectionModel().getSelectedItem();
            query = getUpdateQuery(luggage, database);
        } else {
            FoundLuggage luggage = (FoundLuggage) foundLuggageTableView.getSelectionModel().getSelectedItem();
            query = getUpdateQuery(luggage, database);
        }

        database.executeUpdateQuery(query);
        database.close();
        foundLuggageTableView.refresh();
        makeTextFieldsAndLabelsInvisible();
        makeTableViewVisible();
        
        // Make two overloaded methods, one with Found, one with Lost
    }

    private String getUpdateQuery(LostLuggage luggage, Database database) {
        List<String> queryList = new ArrayList();
        String query;

//        if (!regNrField.getText().equals(luggage.getRegistrationnr())) {
//            queryList.add("registrationnr = '" + regNrField.getText() + "' ");
//        }
        if (dateFoundField.getText() != null) {
            if (!dateFoundField.getText().equals(luggage.getDateregistered())) {
                queryList.add("dateregistered = '" + dateFoundField.getText() + "'");
                luggage.setDateregistered(dateFoundField.getText());
            }
        }

        if (timeFoundField.getText() != null) {
            if (!timeFoundField.getText().equals(luggage.getTimeregistered())) {
                queryList.add("timeregistered = '" + timeFoundField.getText() + "'");
                luggage.setTimeregistered(timeFoundField.getText());
            }
        }

        // Check this
//        if (typeComboBox.getValue() != null) {
//            String type = database.executeStringListQuery(String.format("SELECT idluggage_type FROM Luggagetype WHERE english = '%s'", typeComboBox.getValue()));
//            queryList.add(String.format("luggagetype = '%s'", type));
//        }
        if (brandField.getText() != null) {
            if (!brandField.getText().equals(luggage.getBrand())) {
                queryList.add("brand = '" + brandField.getText() + "'");
                luggage.setBrand(brandField.getText());
            }
        }

        if (flightNrField.getText() != null) {
            if (!flightNrField.getText().equals(luggage.getFlightnumber())) {
                queryList.add("flightnumber = '" + flightNrField.getText() + "'");
                luggage.setFlightnumber(flightNrField.getText());
            }
        }

        if (labelNrField.getText() != null) {
            if (!labelNrField.getText().equals(luggage.getLuggagelabelnr())) {
                queryList.add("luggagelabelnr = '" + labelNrField.getText() + "'");
                luggage.setLuggagelabelnr(labelNrField.getText());
            }
        }

        // Check these two
//        if (mainColourComboBox.getValue() != null) {
//            String ral = database.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", mainColourComboBox.getValue()));
//            queryList.add(String.format("primarycolour = '%s'", ral));
//        }
//
//        if (secondaryColourComboBox.getValue() != null) {
//            String ral = database.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", secondaryColourComboBox.getValue()));
//            queryList.add(String.format("secondarycolour = '%s'", ral));
//        }
        if (sizeField.getText() != null) {
            if (!sizeField.getText().equals(luggage.getSize())) {
                queryList.add("size = '" + sizeField.getText() + "'");
                luggage.setSize(sizeField.getText());
            }
        }

        if (weightField.getText() != null) {
            if (!weightField.getText().equals(luggage.getWeight())) {
                queryList.add("weight = '" + weightField.getText() + "'");
                luggage.setWeight(weightField.getText());
            }
        }

        if (nameAndCityField.getText() != null) {
            if (!nameAndCityField.getText().equals(luggage.getPassenger_name_city())) {
                queryList.add("passenger_name_city = '" + nameAndCityField.getText() + "'");
                luggage.setPassenger_name_city(nameAndCityField.getText());
            }

            if (!specialCharField.getText().equals(luggage.getOtherchar())) {
                queryList.add("otherchar = '" + specialCharField.getText() + "'");
                luggage.setOtherchar(specialCharField.getText());
            }
        }

        // Start of the query
        query = stringListToUpdateQuery(queryList, "UPDATE Lostbagage SET ");

        return query;
    }

    private String getUpdateQuery(FoundLuggage luggage, Database database) {
        List<String> queryList = new ArrayList();
        String query;

//        if (!regNrField.getText().equals(luggage.getRegistrationnr())) {
//            queryList.add("registrationnr = '" + regNrField.getText() + "'");
//        }
        if (dateFoundField.getText() != null) {
            if (!dateFoundField.getText().equals(luggage.getDatefound())) {
                queryList.add("datefound = '" + dateFoundField.getText() + "'");
                luggage.setDatefound(dateFoundField.getText());
            }
        }

        if (timeFoundField.getText() != null) {
            if (!timeFoundField.getText().equals(luggage.getTimefound())) {
                queryList.add("timefound = '" + timeFoundField.getText() + "'");
                luggage.setTimefound(timeFoundField.getText());
            }
        }

        //check this
//        if (typeComboBox.getValue() != null) {
//            String type = database.executeStringListQuery(String.format("SELECT idluggage_type FROM Luggagetype WHERE english = '%s'", typeComboBox.getValue()));
//            queryList.add(String.format("luggagetype = '%s'", type));
//        }
        if (brandField.getText() != null) {
            if (!brandField.getText().equals(luggage.getBrand())) {
                queryList.add("brand = '" + brandField.getText() + "'");
                luggage.setBrand(brandField.getText());
            }
        }

        if (flightNrField.getText() != null) {
            if (!flightNrField.getText().equals(luggage.getFlightnumber())) {
                queryList.add("flightnumber = '" + flightNrField.getText() + "'");
                luggage.setFlightnumber(flightNrField.getText());
            }
        }

        if (labelNrField.getText() != null) {
            if (!labelNrField.getText().equals(luggage.getLuggagelabelnr())) {
                queryList.add("luggagelabelnr = '" + labelNrField.getText() + "'");
                luggage.setLuggagelabelnr(labelNrField.getText());
            }
        }

        if (locationFoundField.getText() != null) {
            if (!locationFoundField.getText().equals(luggage.getLocationfound())) {
                queryList.add("locationfound = '" + locationFoundField.getText() + "'");
                luggage.setLocationfound(locationFoundField.getText());
            }
        }
        // Fix these
//        if (mainColourComboBox.getValue() != null) {
//            String ral = database.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", mainColourComboBox.getValue()));
//            queryList.add(String.format("primarycolour = '%s'", ral));
//        }
//
//        if (secondaryColourComboBox.getValue() != null) {
//            String ral = database.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", secondaryColourComboBox.getValue()));
//            queryList.add(String.format("secondarycolour = '%s'", ral));
//        }

        if (sizeField.getText() != null) {
            if (!sizeField.getText().equals(luggage.getSize())) {
                queryList.add("size = '" + sizeField.getText() + "'");
                luggage.setSize(sizeField.getText());
            }
        }

        if (weightField.getText() != null) {
            if (!weightField.getText().equals(luggage.getWeight())) {
                queryList.add("weight = '" + weightField.getText() + "'");
                luggage.setWeight(weightField.getText());
            }
        }

        if (nameAndCityField.getText() != null) {
            if (!nameAndCityField.getText().equals(luggage.getPassenger_name_city())) {
                queryList.add("passenger_name_city = '" + nameAndCityField.getText() + "'");
                luggage.setPassenger_name_city(nameAndCityField.getText());
            }
        }

        if (specialCharField.getText() != null) {
            if (!specialCharField.getText().equals(luggage.getOtherchar())) {
                queryList.add("otherchar = '" + specialCharField.getText() + "'");
                luggage.setOtherchar(specialCharField.getText());
            }
        }

        query = stringListToUpdateQuery(queryList, "UPDATE Foundbagageinventory SET ");

        return query;
    }

    private String stringListToUpdateQuery(List<String> stringList, String startOfQuery) {
        String query = startOfQuery;
        for (int i = 0; i < stringList.size(); i++) {
            query += stringList.get(i);
            if (i != stringList.size() - 1) {
                query += ", ";
            }
        }
        query += String.format("WHERE registrationnr = %s", regNrField.getText());
        return query;
    }

    private boolean areYouSureBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == ButtonType.OK);
    }

    private void notSelectedBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    private String getQueryFromTextfields() {
        // Creates new List of Strings to be included in the query
        List<String> queryList = new ArrayList();
        String query;

        Database database = new Database();

        if (lostSelected()) {
            if (!regNrField.getText().isEmpty()) {
                queryList.add("registrationnr = '" + regNrField.getText() + "' ");
            }

            if (!dateFoundField.getText().isEmpty()) {
                queryList.add("dateregistered = '" + dateFoundField.getText() + "' ");
            }

            if (!timeFoundField.getText().isEmpty()) {
                queryList.add("timeregistered = '" + timeFoundField.getText() + "' ");
            }

            if (typeComboBox.getValue() != null) {
                String type = database.executeStringListQuery(String.format("SELECT idluggage_type FROM Luggagetype WHERE english = '%s'", typeComboBox.getValue()));
                queryList.add(String.format("luggagetype = '%s'", type));
            }

            if (!brandField.getText().isEmpty()) {
                queryList.add("brand = '" + brandField.getText() + "' ");
            }

            if (!flightNrField.getText().isEmpty()) {
                queryList.add("flightnumber = '" + flightNrField.getText() + "' ");
            }

            if (!labelNrField.getText().isEmpty()) {
                queryList.add("luggagelabelnr = '" + labelNrField.getText() + "' ");
            }

            if (!locationFoundField.getText().isEmpty()) {
                queryList.add("locationfound = '" + locationFoundField.getText() + "' ");
            }

            if (mainColourComboBox.getValue() != null) {
                String ral = database.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", mainColourComboBox.getValue()));
                queryList.add(String.format("primarycolour = '%s'", ral));
            }

            if (secondaryColourComboBox.getValue() != null) {
                String ral = database.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", secondaryColourComboBox.getValue()));
                queryList.add(String.format("secondarycolour = '%s'", ral));
            }

            if (!sizeField.getText().isEmpty()) {
                queryList.add("size = '" + sizeField.getText() + "' ");
            }

            if (!weightField.getText().isEmpty()) {
                queryList.add("weight = '" + weightField.getText() + "' ");
            }

            if (!nameAndCityField.getText().isEmpty()) {
                queryList.add("passenger_name_city = '" + nameAndCityField.getText() + "' ");
            }

            if (!specialCharField.getText().isEmpty()) {
                queryList.add("otherchar = '" + specialCharField.getText() + "' ");
            }

            // Start of the query
            // FOUNDBAGAGEINVENTORY IS INVALID, FIX THIS
            query = "SELECT * FROM Lostbagage ";
        } else {
            if (!regNrField.getText().isEmpty()) {
                queryList.add("registrationnr = '" + regNrField.getText() + "' ");
            }

            if (!dateFoundField.getText().isEmpty()) {
                queryList.add("datefound = '" + dateFoundField.getText() + "' ");
            }

            if (!timeFoundField.getText().isEmpty()) {
                queryList.add("timefound = '" + timeFoundField.getText() + "' ");
            }

            if (typeComboBox.getValue() != null) {
                String type = database.executeStringListQuery(String.format("SELECT idluggage_type FROM Luggagetype WHERE english = '%s'", typeComboBox.getValue()));
                queryList.add(String.format("luggagetype = '%s'", type));
            }

            if (!brandField.getText().isEmpty()) {
                queryList.add("brand = '" + brandField.getText() + "' ");
            }

            if (!flightNrField.getText().isEmpty()) {
                queryList.add("flightnumber = '" + flightNrField.getText() + "' ");
            }

            if (!labelNrField.getText().isEmpty()) {
                queryList.add("luggagelabelnr = '" + labelNrField.getText() + "' ");
            }

            if (!locationFoundField.getText().isEmpty()) {
                queryList.add("locationfound = '" + locationFoundField.getText() + "' ");
            }

            if (mainColourComboBox.getValue() != null) {
                String ral = database.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", mainColourComboBox.getValue()));
                queryList.add(String.format("primarycolour = '%s'", ral));
            }

            if (secondaryColourComboBox.getValue() != null) {
                String ral = database.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", secondaryColourComboBox.getValue()));
                queryList.add(String.format("secondarycolour = '%s'", ral));
            }

            if (!sizeField.getText().isEmpty()) {
                queryList.add("size = '" + sizeField.getText() + "' ");
            }

            if (!weightField.getText().isEmpty()) {
                queryList.add("weight = '" + weightField.getText() + "' ");
            }

            if (!nameAndCityField.getText().isEmpty()) {
                queryList.add("passenger_name_city = '" + nameAndCityField.getText() + "' ");
            }

            if (!specialCharField.getText().isEmpty()) {
                queryList.add("otherchar = '" + specialCharField.getText() + "' ");
            }

            // Start of the query
            query = "SELECT * FROM Foundbagageinventory ";
        }
        // Loop through the list of Strings to add to the query, adding 
        // 'WHERE ' in front if it's the first, adding 'AND ' in front if it's 
        // not, to create a valid SQL Query
        for (int i = 0; i < queryList.size(); i++) {
            if (i == 0) {
                query += "WHERE ";
                query += queryList.get(i);
            } else {
                query += "AND ";
                query += queryList.get(i);
            }
        }
        database.close();
        return query;
    }

    private void makeTextFieldsAndLabelsInvisible() {
        // Create an ObservableList consisting of all children nodes of the 
        // vboxes, then making them invisible
        ObservableList<Node> vbox1Children = vbox1.getChildren();
        for (Node node : vbox1Children) {
            node.setVisible(false);
        }

        ObservableList<Node> vbox2Children = vbox2.getChildren();
        for (Node node : vbox2Children) {
            node.setVisible(false);
        }
    }

    private void makeTextFieldsAndLabelsVisible() {
        // Create an ObservableList consisting of all children nodes of the 
        // vboxes, then making them visible
        ObservableList<Node> vbox1Children = vbox1.getChildren();
        for (Node node : vbox1Children) {
            node.setVisible(true);
        }

        ObservableList<Node> vbox2Children = vbox2.getChildren();
        for (Node node : vbox2Children) {
            node.setVisible(true);
        }
    }

    private void makeTableViewInvisible() {
        foundLuggageTableView.setVisible(false);
        editSelectedButton.setVisible(false);
    }

    private void makeTableViewVisible() {
        foundLuggageTableView.setVisible(true);
        editSelectedButton.setVisible(true);
    }

    private void makeTableViewFound() {
        List<TableColumn> columnList = new ArrayList();
        columnList = foundLuggageTableView.getColumns();

        for (int i = 0; i < columnList.size(); i++) {
            if (columnList.get(i).getText().equals("Date Found")) {
                columnList.get(i).setVisible(true);
            }
            if (columnList.get(i).getText().equals("Time Found")) {
                columnList.get(i).setVisible(true);
            }
            if (columnList.get(i).getText().equals("Location Found")) {
                columnList.get(i).setVisible(true);
            }
            if (columnList.get(i).getText().contains("Date Registered")) {
                columnList.get(i).setVisible(false);
            }
            if (columnList.get(i).getText().contains("Time Registered")) {
                columnList.get(i).setVisible(false);
            }
        }
    }

    private void makeTableViewLost() {
        List<TableColumn> columnList = new ArrayList();
        columnList = foundLuggageTableView.getColumns();

        for (int i = 0; i < columnList.size(); i++) {
            if (columnList.get(i).getText().equals("Date Found")) {
                columnList.get(i).setVisible(false);
            }
            if (columnList.get(i).getText().equals("Time Found")) {
                columnList.get(i).setVisible(false);
            }
            if (columnList.get(i).getText().equals("Location Found")) {
                columnList.get(i).setVisible(false);
            }
            if (columnList.get(i).getText().contains("Date Registered")) {
                columnList.get(i).setVisible(true);
            }
            if (columnList.get(i).getText().contains("Time Registered")) {
                columnList.get(i).setVisible(true);
            }
        }
    }

    private boolean lostSelected() {
        List<Toggle> toggleList = new ArrayList();
        toggleList.addAll(lostFoundGroup.getToggles());

        return (toggleList.get(0).isSelected());
    }

    private void initTextFieldsWithLostLuggage(LostLuggage luggage) {
        regNrField.setText(Integer.toString(luggage.getRegistrationnr()));
        dateFoundField.setText(luggage.getDateregistered());
        timeFoundField.setText(luggage.getTimeregistered());
        typeComboBox.getSelectionModel().select(luggage.getLuggagetype());
        brandField.setText(luggage.getBrand());
        flightNrField.setText(luggage.getFlightnumber());
        labelNrField.setText(luggage.getLuggagelabelnr());
        mainColourComboBox.getSelectionModel().select(luggage.getPrimarycolour());
        secondaryColourComboBox.getSelectionModel().select(luggage.getSecondarycolour());
        sizeField.setText(luggage.getSize());
        weightField.setText(luggage.getWeight());
        nameAndCityField.setText(luggage.getPassenger_name_city());
        specialCharField.setText(luggage.getOtherchar());

    }

    private void initTextFieldsWithFoundLuggage(FoundLuggage luggage) {
        regNrField.setText(Integer.toString(luggage.getRegistrationnr()));
        dateFoundField.setText(luggage.getDatefound());
        timeFoundField.setText(luggage.getTimefound());
        typeComboBox.getSelectionModel().select(luggage.getLuggagetype());
        brandField.setText(luggage.getBrand());
        flightNrField.setText(luggage.getFlightnumber());
        labelNrField.setText(luggage.getLuggagelabelnr());
        locationFoundField.setText(luggage.getLocationfound());
        mainColourComboBox.getSelectionModel().select(luggage.getPrimarycolour());
        secondaryColourComboBox.getSelectionModel().select(luggage.getSecondarycolour());
        sizeField.setText(luggage.getSize());
        weightField.setText(luggage.getWeight());
        nameAndCityField.setText(luggage.getPassenger_name_city());
        specialCharField.setText(luggage.getOtherchar());
    }
}