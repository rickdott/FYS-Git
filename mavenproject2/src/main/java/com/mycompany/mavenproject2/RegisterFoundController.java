package com.mycompany.mavenproject2;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Has to be implemented
 *
 * @author Tarik Yildirim 500780772
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
    private static String Registrationnr;
    private String RegistrationString;
    @FXML
    TextField Size;
    @FXML
    TextField Weight;
    @FXML
    TextField LocationFound;

    @FXML
    private TextField BagageLabel;
    @FXML
    private TextField BagageFlight;
    
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
    String BagagePrimaryColourString;
    String BagageSecondaryColourString;
    String LuggageTypeSelect;
    Database db = new Database();

    @FXML
    public void testDatabase(ActionEvent event) throws SQLException {
        
        viewTable();
       
    }

    public void viewTable()
            throws SQLException {

        Statement stmt = null;
        String query
                = "SELECT max(registrationnr)FROM Foundbagageinventory";
        //Ter voorbeeld hieronder
        /* "select COF_NAME, SUP_ID, PRICE, " +
        "SALES, TOTAL " +
        "from " + test2 + ".COFFEES";*/
        try {
            ResultSet rs = db.executeResultSetQuery(query);
            while (rs.next()) {
                Registrationnr = rs.getString(1);
                String subRegistrationnr = Registrationnr.substring(5);
                int intRegistrationnr = Integer.parseInt(subRegistrationnr);
                String stringJaar = GeneralDate2.getText();
                String subJaar = stringJaar.substring(6)+"/";

                //System.out.println("old "+intRegistrationnr);
                intRegistrationnr++;
                //System.out.println("new "+intRegistrationnr);
                
                
                //System.out.println(subJaar);
                String RegistrationnrString = String.valueOf(intRegistrationnr);
                RegistrationString = subJaar+RegistrationnrString;
                System.out.println(RegistrationString);
                
                
                
                
                
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    @FXML
    private void openRegisterThankyou(ActionEvent event) throws SQLException {
        viewTable();

        /*System.out.println("Test!!");
        System.out.println(LuggageType.selectionModelProperty());
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(-2));
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(-1));
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(0));
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(1));
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(2));
        System.out.println(!BagagePrimaryColour.getSelectionModel().isSelected(3));*/
        if (!BagagePrimaryColour.getSelectionModel().isSelected(-1)
                && !BagageSecondaryColour.getSelectionModel().isSelected(-1)
                && !LuggageType.getSelectionModel().isSelected(-1)
                && !BagageBrand.getText().trim().isEmpty()
                && !BagageSpecialchar.getText().trim().isEmpty()
                && !BagageFlight.getText().trim().isEmpty()
                && !BagageLabel.getText().trim().isEmpty()

                && !LocationFound.getText().trim().isEmpty())
                 {

                
            String sql = String.format("SELECT * FROM Flight WHERE flightnr = '%s' ",
                BagageFlight.getText());
            Database db = new Database();
            ResultSet resultSet = db.executeResultSetQuery(sql);
            if(resultSet.next()){
            
            

            if (BagagePrimaryColour.getValue() != null) {
                BagagePrimaryColourString = db.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", BagagePrimaryColour.getValue()));

            } else {
                BagagePrimaryColourString = "1";
            }

            if (BagageSecondaryColour.getValue() != null) {
                BagageSecondaryColourString = db.executeStringListQuery(String.format("SELECT ralcode FROM Colour WHERE english = '%s'", BagageSecondaryColour.getValue()));

            } else {
                BagageSecondaryColourString = "1";
            }
            
            if (LuggageType.getValue() != null) {
                LuggageTypeSelect = db.executeStringListQuery(String.format("SELECT idluggage_type FROM Luggagetype WHERE english = '%s'", LuggageType.getValue()));

            } else {
                LuggageTypeSelect = "10";
            }

            //Registrationnr++;
            String luggageInformation = String.format("INSERT INTO "
                    + "Foundbagageinventory (registrationnr, datefound, timefound, luggagetype, brand, flightnumber, luggagelabelnr, locationfound, primarycolour, secondarycolour, size, weight, passenger_name_city, otherchar, idpassenger)"
                    + "VALUES ('%s','%s','1','%s','%s','%s','%s','1','%s','%s','%s','%s','1','%s','1')",
                    RegistrationString, GeneralDate2.getText(), LuggageTypeSelect, BagageBrand.getText(), BagageFlight.getText(), BagageLabel.getText(), 
                    BagagePrimaryColourString, BagageSecondaryColourString, Size.getText(), Weight.getText(),
                    BagageSpecialchar.getText());
            System.out.println(luggageInformation);
            db.executeUpdateQuery(luggageInformation);

            utilities.newAnchorpane("RegisterFound_thankyou", RegisterFoundPane);
            
            }
            else{infoBox("Flightnumber invalid", "Error", null);}

        } else {
            infoBox("Please try to fill in everyting!.", "Error", null);

        }
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

}
