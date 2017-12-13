/*
 * To change this license header, choose License Headeresult in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Rick
 */
public class RequestStatusKlantenController {

    @FXML
    private TextField lastNameField, labelNrField;

    @FXML
    private Label bagageid, labelnumber, flightnumber, destination, type, brand, colour, specialchar, foundat, foundatdate, date;

    @FXML
    private void getInput() throws SQLException {
        String lastName = lastNameField.getText();
        String labelnr = labelNrField.getText();

        //MAKE THIS COMBINE LABELNR AND LASTNAME, THIS IS LOW SECURITY
        String query = "SELECT * FROM Lostbagage WHERE labelnumber = '" + labelnr + "'";

        Database database = new Database();
        ResultSet result = database.executeResultSetQuery(query);
        FoundLuggage luggage = new FoundLuggage();

        // display all information MAKE THIS USE luggage OBJECT TO SHOW INFO
//        while (result.next()) {
//            luggage = Utilities.initializeLuggageFromResultSet(result, luggage);
//        }

        // Closing open connections
        result.close();
        database.close();
        // back button
    }

}
