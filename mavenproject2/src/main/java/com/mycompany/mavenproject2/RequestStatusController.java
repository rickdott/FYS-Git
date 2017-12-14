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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Rick
 */
public class RequestStatusController implements Initializable {

    @FXML
    private TableView foundLuggageTableView;

    private ObservableList<FoundLuggage> foundLuggageList
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
        foundLuggageTableView.setVisible(true);
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
            // FOUNDBAGAGEINVENTORY IS INVALID, FIX THIS
            query = "SELECT * FROM Lostbagage ";
        } else {
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

//            if (!locationFoundField.getText().isEmpty()) {
//                queryList.add("locationfound = '" + locationFoundField.getText() + "' ");
//            }
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

    private void initTableView() {
        foundLuggageTableView.setItems(this.foundLuggageList);

        for (int i = 0; i < foundLuggageTableView.getColumns().size(); i++) {
            TableColumn tc = (TableColumn) foundLuggageTableView.getColumns().get(i);
            String propertyName = tc.getId();
            if (propertyName != null && !propertyName.isEmpty()) {
                tc.setCellValueFactory(new PropertyValueFactory<>(propertyName));
                System.out.println("Attached column '" + propertyName + "' in tableview to matching attribute");
            }
        }
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
}
