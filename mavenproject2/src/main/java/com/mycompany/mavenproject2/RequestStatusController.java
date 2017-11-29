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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Rick
 */
public class RequestStatusController implements Initializable {

    @FXML
    private TableView foundLuggageTableView;

    private final ObservableList<FoundLuggage> foundLuggageList
            = FXCollections.observableArrayList();

    @FXML
    private AnchorPane requestpage;

    @FXML
    private TextField destinationField, labelNrField, dateField, bagageidField;

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
    }
    
    FXMLDocumentController controller = new FXMLDocumentController();

    @FXML
    private void openRequestStatusResult(ActionEvent event) {
        controller.newAnchorpane("RequestStatus_Result", requestpage);
    }

    @FXML
    private void getInput() throws SQLException {
        // Creates new List of Strings to be included in the query
        List<String> queryList = new ArrayList<String>();

        if (!destinationField.getText().isEmpty()) {
            queryList.add("destination = '" + destinationField.getText() + "' ");
        }
        if (!labelNrField.getText().isEmpty()) {
            queryList.add("labelnumber = '" + labelNrField.getText() + "' ");
        }
        if (!dateField.getText().isEmpty()) {
            queryList.add("date = '" + dateField.getText() + "' ");
        }
        if (!bagageidField.getText().isEmpty()) {
            queryList.add("bagageid = '" + bagageidField.getText() + "' ");
        }
        // Start of the query
        String query = "SELECT * FROM Bagage ";

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

        // Create database connection, execute the query
        Database database = new Database();
        ResultSet result = database.executeResultSetQuery(query);
        foundLuggageList.clear();

        // Loop through the resultset, making a new 'FoundLuggage' Object 
        // for every result, adding the attributes of the result to the 
        // corresponding attribute in the FoundLuggage object
        while (result.next()) {
            FoundLuggage luggage = new FoundLuggage();
            luggage.setBagageid(result.getInt("bagageid"));
            luggage.setLabelnumber(result.getString("labelnumber"));
            luggage.setFlightnumber(result.getString("flightnumber"));
            luggage.setDestination(result.getString("destination"));
            luggage.setType(result.getString("type"));
            luggage.setBrand(result.getString("brand"));
            luggage.setColour(result.getString("colour"));
            luggage.setSpecialchar(result.getString("specialchar"));
            luggage.setFoundat(result.getString("foundat"));
            luggage.setFoundatdate(result.getString("foundatdate"));
            luggage.setDate(result.getString("date"));
            foundLuggageList.add(luggage);

        }
        // back button
    }
}
