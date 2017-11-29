/*
 * To change this license header, choose License Headevalue in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 *
 * @author Rick
 */
public class RequestStatusKlantenController {
    @FXML
    private TextField lastNameField, labelNrField;
    
    @FXML
    private void getInput() throws SQLException {
        String lastName = lastNameField.getText();
        String labelnumber = labelNrField.getText();
        
        // open database, look for entries with lastName and labelNumber as primary key
        Connection connection = sqlDatabaseConnection.connectdb();
        
        String query = "SELECT * FROM Bagage WHERE destination = 'Malaga'";
        
        Database database = new Database();
        ResultSet value = database.executeResultSetQuery(query);
        
        // display all information in a seperate screen
        //System.out.println(value.getString(3));
        while (value.next()) {
            String coffeeName = value.getString("labelnumber");
            String supplierID = value.getString("type");
            String price = value.getString("brand");
            String sales = value.getString("specialchar");

            System.out.println(coffeeName + "\t" + supplierID +
                               "\t" + price + "\t" + sales +
                               "\t");
        }
        
        // back button
    }
}
