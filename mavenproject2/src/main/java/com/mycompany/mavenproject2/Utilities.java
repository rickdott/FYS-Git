/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.util.ArrayList;
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

    public static ObservableList<String> colours = FXCollections.observableArrayList(
            "Yellow", "Olive", "Red", "Darkred", "Pink", "Purple", "Violet",
            "Blue", "Lightblue", "Darkblue", "Bluegreen", "Green", "Darkgreen",
            "Lightgreen", "Gray", "Darkgray", "Lightgray", "Brown", "Darkbrown",
            "Lightbrown", "White", "Black", "Cream");

    public static ObservableList<String> types = FXCollections.observableArrayList(
            "Suitcase", "Bag", "Bagpack", "Box", "Sports",
            "Bag", "Business Case", "Case", "Other");
    
    // Three methods to use when hovering over a button, overloaded
    // First method is only used for big button
    @FXML
    public static void onHover(Button btn) {
        btn.setStyle("-fx-background-color: #951504; -fx-background-radius: 0; -fx-border-width: 1 0 0 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 30;");
    }
    
    public static int userID;
    public static int roleID;

    
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

    // Three methods to use when hovered off the button, overloaded
    // First method is only used for big buttons
    @FXML
    public static void offHover(Button btn) {
        btn.setStyle("-fx-background-color: #D81E05; -fx-background-radius: 0; -fx-border-width: 1 0 0 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 30;");
    }
            
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
    
    @FXML
    public void setEmployee(int idEmployee, int RoleID) {
        this.userID = idEmployee;
        this.roleID = RoleID;
    }
    
    

    public Parent loadFXMLFile(String fxmlFileName) {
        try {
            return FXMLLoader.load(getClass().getResource(fxmlFileName));
        } catch (IOException ex) {
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
            return null;
        }
    }

    public static String GetCurrentDateTime2() {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //get current date time with Date()
        Date date = new Date();
        return (dateFormat.format(date));

    }

    public static ObservableList initializeFoundLuggageFromResultSet(ResultSet result, ObservableList foundLuggageList) throws SQLException {
        // Loop through the resultset, making a new 'FoundLuggage' Object 
        // for every result, adding the attributes of the result to the 
        // corresponding attribute in the FoundLuggage object
        
        while (result.next()) {
            FoundLuggage luggage = new FoundLuggage();
            luggage.setRegistrationnr(result.getString("registrationnr"));
            luggage.setDatefound(result.getString("datefound"));
            luggage.setTimefound(result.getString("timefound"));
            luggage.setLuggagetype(result.getString("luggagetype"));
            luggage.setBrand(result.getString("brand"));
            luggage.setFlightnumber(result.getString("flightnumber"));
            luggage.setLuggagelabelnr(result.getString("luggagelabelnr"));
            luggage.setLocationfound(result.getString("locationfound"));
            luggage.setPrimarycolour(result.getString("primarycolour"));
            luggage.setSecondarycolour(result.getString("secondarycolour"));
            luggage.setSize(result.getString("size"));
            luggage.setWeight(result.getString("weight"));
            luggage.setPassenger_name_city(result.getString("passenger_name_city"));
            luggage.setOtherchar(result.getString("otherchar"));
            luggage.setIdpassenger(result.getInt("idpassenger"));
            foundLuggageList.add(luggage);
        }

        return foundLuggageList;
    }
    
    public static ObservableList initializeLostLuggageFromResultSet(ResultSet result, ObservableList foundLuggageList) throws SQLException {
        // Loop through the resultset, making a new 'FoundLuggage' Object 
        // for every result, adding the attributes of the result to the 
        // corresponding attribute in the FoundLuggage object
        
        while (result.next()) {
            LostLuggage luggage = new LostLuggage();
            luggage.setRegistrationnr(result.getString("registrationnr"));
            luggage.setDateregistered(result.getString("dateregistered"));
            luggage.setTimeregistered(result.getString("timeregistered"));
            luggage.setLuggagetype(result.getString("luggagetype"));
            luggage.setBrand(result.getString("brand"));
            luggage.setFlightnumber(result.getString("flightnumber"));
            luggage.setLuggagelabelnr(result.getString("luggagelabelnr"));
            luggage.setPrimarycolour(result.getString("primarycolour"));
            luggage.setSecondarycolour(result.getString("secondarycolour"));
            luggage.setSize(result.getString("size"));
            luggage.setWeight(result.getString("weight"));
            luggage.setPassenger_name_city(result.getString("passenger_name_city"));
            luggage.setOtherchar(result.getString("otherchar"));
            luggage.setIdpassenger(result.getInt("idpassenger"));
            foundLuggageList.add(luggage);
        }
        return foundLuggageList;
    }
    
    public static ArrayList<FoundLuggage> listOfFoundLuggageFromResultSet(ResultSet result, ArrayList<FoundLuggage> foundLuggageList) throws SQLException {
        // Loop through the resultset, making a new 'FoundLuggage' Object 
        // for every result, adding the attributes of the result to the 
        // corresponding attribute in the FoundLuggage object
        
        while (result.next()) {
            FoundLuggage luggage = new FoundLuggage();
            luggage.setRegistrationnr(result.getString("registrationnr"));
            luggage.setDatefound(result.getString("datefound"));
            luggage.setTimefound(result.getString("timefound"));
            luggage.setLuggagetype(result.getString("luggagetype"));
            luggage.setBrand(result.getString("brand"));
            luggage.setFlightnumber(result.getString("flightnumber"));
            luggage.setLuggagelabelnr(result.getString("luggagelabelnr"));
            luggage.setLocationfound(result.getString("locationfound"));
            luggage.setPrimarycolour(result.getString("primarycolour"));
            luggage.setSecondarycolour(result.getString("secondarycolour"));
            luggage.setSize(result.getString("size"));
            luggage.setWeight(result.getString("weight"));
            luggage.setPassenger_name_city(result.getString("passenger_name_city"));
            luggage.setOtherchar(result.getString("otherchar"));
            luggage.setIdpassenger(result.getInt("idpassenger"));
            foundLuggageList.add(luggage);
        }
        return foundLuggageList;
    }
    
    public static ArrayList<LostLuggage> listOfLostLuggageFromResultSet(ResultSet result, ArrayList<LostLuggage> foundLuggageList) throws SQLException {
        // Loop through the resultset, making a new 'FoundLuggage' Object 
        // for every result, adding the attributes of the result to the 
        // corresponding attribute in the FoundLuggage object
        
        while (result.next()) {
            LostLuggage luggage = new LostLuggage();
            luggage.setRegistrationnr(result.getString("registrationnr"));
            luggage.setDateregistered(result.getString("dateregistered"));
            luggage.setTimeregistered(result.getString("timeregistered"));
            luggage.setLuggagetype(result.getString("luggagetype"));
            luggage.setBrand(result.getString("brand"));
            luggage.setFlightnumber(result.getString("flightnumber"));
            luggage.setLuggagelabelnr(result.getString("luggagelabelnr"));
            luggage.setPrimarycolour(result.getString("primarycolour"));
            luggage.setSecondarycolour(result.getString("secondarycolour"));
            luggage.setSize(result.getString("size"));
            luggage.setWeight(result.getString("weight"));
            luggage.setPassenger_name_city(result.getString("passenger_name_city"));
            luggage.setOtherchar(result.getString("otherchar"));
            luggage.setIdpassenger(result.getInt("idpassenger"));
            foundLuggageList.add(luggage);
        }
        return foundLuggageList;
    }
}
