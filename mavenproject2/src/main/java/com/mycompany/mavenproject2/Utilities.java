/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Rick
 */
public class Utilities {
    
    // Two methods to use when hovering over a button
    @FXML
    public static void onHover(String currentPage, Button btn, Label label) {
        System.out.println("onHover activated");
        if (!label.getText().equals(currentPage)) {
            btn.setStyle("-fx-background-color: #951504; -fx-background-radius: 0; -fx-border-width: 1 0 0 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }
    
    @FXML
    public static void onHover(String currentPage, Button btn, Label label, boolean isLast) {
        System.out.println("onHover activated");
        if (!label.getText().equals(currentPage) && isLast) {
            btn.setStyle("-fx-background-color: #951504; -fx-background-radius: 0; -fx-border-width: 1 0 1 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }
    // Two methods to use when hovered off the button, overloaded
    @FXML
    public static void offHover(String currentPage, Button btn, Label label) {
        System.out.println("offHover activated");
        if (!label.getText().equals(currentPage)) {
            btn.setStyle("-fx-background-color: #D81E05; -fx-background-radius: 0; -fx-border-width: 1 0 0 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    @FXML
    public static void offHover(String currentPage, Button btn, Label label, boolean isLast) {
        System.out.println("offHover activated");
        if (!label.getText().equals(currentPage) && isLast) {
            btn.setStyle("-fx-background-color: #D81E05; -fx-background-radius: 0; -fx-border-width: 1 0 1 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    // Opens a different page, changing just a Pane
    @FXML
    public void newPane(String pageName, Button btn, Pane pane, Label label) {
        System.out.println("Opening another page...");
        
        Parent newPane = loadFXMLFile(pageName + ".fxml");
        pane.getChildren().clear();
        pane.getChildren().add(newPane);
        label.setText(btn.getText());
        
        System.out.println("Another page opened...");
    }
    @FXML
    public void newPane(String pageName, Pane pane) {
        System.out.println("Opening another page...");
        
        Parent newPane = loadFXMLFile(pageName + ".fxml");
        pane.getChildren().clear();
        pane.getChildren().add(newPane);
        
        System.out.println("Another page opened...");
    }
    
    // Opens a different page, changing the AnchorPane
    @FXML
    public void newAnchorpane(String pageName, AnchorPane paneToReplace) {
        System.out.println("Opening another page(anchor)...");
        
        Parent newPane = loadFXMLFile(pageName + ".fxml");
        paneToReplace.getChildren().clear();
        paneToReplace.getChildren().add(newPane);
        
        System.out.println("Another page opened(anchor)...");
    }

    public Parent loadFXMLFile(String fxmlFileName) {
        try {
            return FXMLLoader.load(getClass().getResource(fxmlFileName));
        } catch (IOException ex) {
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
            return null;
        }
    }
    public static FoundLuggage initializeLuggageFromResultSet(ResultSet result, FoundLuggage luggage) throws SQLException {
        // Loop through the resultset, making a new 'FoundLuggage' Object 
        // for every result, adding the attributes of the result to the 
        // corresponding attribute in the FoundLuggage object
        while (result.next()) {
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
        }
        result.close();
        return luggage;
    }
}
