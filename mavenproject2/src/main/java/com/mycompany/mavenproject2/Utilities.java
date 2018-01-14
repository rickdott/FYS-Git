/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Rick 1-154 (153) Timur 154-261 (107)
 *
 * @author Rick den Otter 500749952 Timur Yagci 500764449
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

    public static final int[] luggageCodes = {1, 2, 3, 4, 5, 6, 7, 8};

    public static final String[][] luggageStrings = new String[][]{
        {"Suitcase", "Bag", "Bagpack", "Box", "Sports Bag", "Business Case", "Case", "Other"},
        {"Koffer", "Tas", "Rugzak", "Doos", "Sporttas", "Zakenkoffer", "Kist", "Anders"},
        {"Bavul", "Canta", "Sırt çantası", "Kutu", "Spor çantası", "Iş çantası", "Göğüs", "Diğer"}
    };

    public static final int[] ralcodes = {1003, 1015, 1024, 2004, 3000, 3005, 3017, 4005,
        4010, 5002, 5015, 5022, 6002, 6004, 6022, 6038, 7000, 7015, 8002,
        8011, 8023, 9001, 9005, 9011};

    public static final String[][] coloursStrings = new String[][]{
        {"Yellow", "Cream", "Olive", "Orange", "Red", "Darkred", "Pink", "Purple", "Violet", "Blue", "Lightblue", "Darkblue", "Green", "Bluegreen", "Darkgreen", "Lightgreen", "Lightgray", "Gray", "Brown", "Darkbrown", "Lightbrown", "White", "Black", "Darkgray"},
        {"Geel", "Crème", "Olijf", "Oranje", "Rood", "Donkerrood", "Roze", "Paars", "Violet", "Blauw", "Lichtblauw", "Donkerblauw", "Groen", "Blauwgroen", "Donkergroen", "Lichtgroen", "Lichtgrijs", "Grijs", "Bruin", "Donkerbruin", "Lichtbruin", "Wit", "Zwart", "Donkergrijs"},
        {"Sarı", "Krem", "Zeytin yeşili", "Turuncu", "Kırmızı", "Koyu kırmızı", "Pembe", "Mor", "Menekşe", "Mavi", "Açık  mavi", "Koyu mavi", "Yeşil", "çamurcun", "Koyu yeşil", "Açık  yeşil", "Açık gri", "Gri", "Kahverengi", "Koyu kahverengi", "Açık kahverengi", "Beyaz", "Siyah", "Koyu gri"}
    };

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
        //label.setText(btn.getText());

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

    public static String getColourFromRal(String ral) throws SQLException {
        if (ral == null) {
            return "";
        } else {
            int ralInt = Integer.parseInt(ral);

            String language = Locale.getDefault().getLanguage();
            String colour = "";

            for (int i = 0; i < Utilities.ralcodes.length; i++) {
                if (Utilities.ralcodes[i] == ralInt) {
                    switch (language) {
                        case "en":
                            colour = Utilities.coloursStrings[0][i];
                            break;
                        case "nl":
                            colour = Utilities.coloursStrings[1][i];
                            break;
                        case "tr":
                            colour = Utilities.coloursStrings[2][i];
                            break;
                    }
                }
            }

            return colour;
        }
    }

    public static int getRalFromColour(String colour) {

        String language = Locale.getDefault().getLanguage();
        int languageIndex = -1;
        switch (language) {
            case "en":
                languageIndex = 0;
                break;
            case "nl":
                languageIndex = 1;
                break;
            case "tr":
                languageIndex = 2;
                break;
        }
        for (int i = 0; i < Utilities.coloursStrings[0].length; i++) {
            if (Utilities.coloursStrings[languageIndex][i].equals(colour)) {
                return Utilities.ralcodes[i];
            }
        }
        return -1;
    }

    public static String getTypeFromNumber(String ral) throws SQLException {
        if (ral == null) {
            return "";
        } else {
            int ralInt = Integer.parseInt(ral);

            String language = Locale.getDefault().getLanguage();
            String type = "";

            for (int i = 0; i < Utilities.luggageCodes.length; i++) {
                if (Utilities.luggageCodes[i] == ralInt) {
                    switch (language) {
                        case "en":
                            type = Utilities.luggageStrings[0][i];
                            break;
                        case "nl":
                            type = Utilities.luggageStrings[1][i];
                            break;
                        case "tr":
                            type = Utilities.luggageStrings[2][i];
                            break;
                    }
                }
            }

            return type;
        }
    }

    public static int getNumberFromType(String colour) {

        String language = Locale.getDefault().getLanguage();
        int languageIndex = -1;
        switch (language) {
            case "en":
                languageIndex = 0;
                break;
            case "nl":
                languageIndex = 1;
                break;
            case "tr":
                languageIndex = 2;
                break;
        }
        for (int i = 0; i < Utilities.luggageStrings[0].length; i++) {
            if (Utilities.luggageStrings[languageIndex][i].equals(colour)) {
                return Utilities.luggageCodes[i];
            }
        }
        return -1;
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
            luggage.setLuggagetype(Utilities.getTypeFromNumber(result.getString("luggagetype")));
            luggage.setBrand(result.getString("brand"));
            luggage.setFlightnumber(result.getString("flightnumber"));
            luggage.setLuggagelabelnr(result.getString("luggagelabelnr"));
            luggage.setLocationfound(result.getString("locationfound"));
            luggage.setPrimarycolour(Utilities.getColourFromRal(result.getString("primarycolour")));
            luggage.setSecondarycolour(Utilities.getColourFromRal(result.getString("secondarycolour")));
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
            luggage.setLuggagetype(Utilities.getTypeFromNumber(result.getString("luggagetype")));
            luggage.setBrand(result.getString("brand"));
            luggage.setFlightnumber(result.getString("flightnumber"));
            luggage.setLuggagelabelnr(result.getString("luggagelabelnr"));
            luggage.setPrimarycolour(Utilities.getColourFromRal(result.getString("primarycolour")));
            luggage.setSecondarycolour(Utilities.getColourFromRal(result.getString("secondarycolour")));
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
            luggage.setLuggagetype(Utilities.getTypeFromNumber(result.getString("luggagetype")));
            luggage.setBrand(result.getString("brand"));
            luggage.setFlightnumber(result.getString("flightnumber"));
            luggage.setLuggagelabelnr(result.getString("luggagelabelnr"));
            luggage.setLocationfound(result.getString("locationfound"));
            luggage.setPrimarycolour(Utilities.getColourFromRal(result.getString("primarycolour")));
            luggage.setSecondarycolour(Utilities.getColourFromRal(result.getString("secondarycolour")));
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
            luggage.setLuggagetype(Utilities.getTypeFromNumber(result.getString("luggagetype")));
            luggage.setBrand(result.getString("brand"));
            luggage.setFlightnumber(result.getString("flightnumber"));
            luggage.setLuggagelabelnr(result.getString("luggagelabelnr"));
            luggage.setPrimarycolour(Utilities.getColourFromRal(result.getString("primarycolour")));
            luggage.setSecondarycolour(Utilities.getColourFromRal(result.getString("secondarycolour")));
            luggage.setSize(result.getString("size"));
            luggage.setWeight(result.getString("weight"));
            luggage.setPassenger_name_city(result.getString("passenger_name_city"));
            luggage.setOtherchar(result.getString("otherchar"));
            luggage.setIdpassenger(result.getInt("idpassenger"));
            foundLuggageList.add(luggage);
        }
        return foundLuggageList;
    }
    
    /**
     * 
     * @param labelnr labelnr of the luggage you want to check
     * @param solveCase true if you want it to put the luggage in SolvedCases if it is only in foundbagageinventory, false if you dont
     * @return returns true if it is already in SolvedCases or if the method puts it there
     * @throws SQLException 
     */
    public static boolean isSolvedLabelnr(long labelnr, boolean solveCase) throws SQLException {
        Database database = new Database();

        ResultSet resultLost = database.executeResultSetQuery(String.format("SELECT * FROM Lostbagage WHERE luggagelabelnr = %d", labelnr));
        ResultSet resultFound = database.executeResultSetQuery(String.format("SELECT * FROM Foundbagageinventory WHERE luggagelabelnr = %d", labelnr));
        ResultSet resultSolved = database.executeResultSetQuery(String.format("SELECT * FROM Solvedcases WHERE luggagelabelnr = %d", labelnr));
        
        database.close();
        //Logic for finding out what kind of luggage it is
        boolean isInLost, isInFound, isInSolved;
        if (resultLost.next() == false) {
            //Luggage is not in lostbagage
            isInLost = false;
        } else {
            isInLost = true;
        }
        if (resultFound.next() == false) {
            //Luggage is not in foundbagage
            isInFound = false;
        } else {
            isInFound = true;
        }
        if (resultSolved.next() == false) {
            //Luggage is not in solved
            isInSolved = false;
        } else {
            isInSolved = true;
        }
//        resultLost.close();
//        resultFound.close();
//        resultSolved.close();
        
        //Logic to decide what to do
        if (isInLost && isInFound && isInSolved) {
            return true;
        } else if ((isInLost && isInFound) && !isInSolved) {
            //put in solved
            solveCase(labelnr, isInLost, isInFound, isInSolved);
            return true;
        } else if (isInFound && solveCase) {
            solveCase(labelnr, isInLost, isInFound, isInSolved);
            return true;
        } else {
            return false;
        }
    }
    
    private static void solveCase(long labelnr, boolean isInLost, boolean isInFound, boolean isInSolved) {
        Database database = new Database();
        
        database.executeUpdateQuery(String.format("INSERT INTO Solvedcases (luggagelabelnr, dateSolved) VALUES (%d, CURDATE());", labelnr));
        if (isInLost) {
            database.executeUpdateQuery(String.format("UPDATE Lostbagage SET isSolved = 1 WHERE luggagelabelnr = %d", labelnr));
        }
        if (isInFound) {
            database.executeUpdateQuery(String.format("UPDATE Foundbagageinventory SET isSolved = 1 WHERE luggagelabelnr = %d", labelnr));
        }
        database.close();
    }
}
