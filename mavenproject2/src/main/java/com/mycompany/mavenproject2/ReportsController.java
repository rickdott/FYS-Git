package com.mycompany.mavenproject2;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 *
 * @author Rick
 */
public class ReportsController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            showMissingStats();
//        } catch (SQLException ex) {
//            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private PieChart pieChart;

    @FXML
    private DatePicker datePickerStart, datePickerEnd;

    @FXML
    private ToggleGroup selectionGroup;

    @FXML
    private Toggle missingLuggageToggle, foundLuggageToggle, missingLuggagePerMonthToggle, solvedToggle, compensationToggle;
    
    @FXML
    private VBox checkBoxVBox;

    class ChartEntry {

        private double amount;
        private String entry;

        public ChartEntry(double amount, String entry) {
            this.amount = amount;
            this.entry = entry;
        }

        public double getAmount() {
            return amount;
        }

        public String getEntry() {
            return entry;
        }

    }

    @FXML
    private void showMissingStats() throws SQLException {
        System.out.println("Showing missing stats");
        pieChart.setVisible(true);
        
        populatePieChart(pieChart, amountOfMissingAsPieChart("Flight.from", findSelectedAirports()));
    }

    @FXML
    private void showFoundStats() {
        ArrayList<String> testList = findSelectedAirports();
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i));
        }
    }

    @FXML
    private void showMissingPerMonthStats() {
        System.out.println("bar");
    }

    @FXML
    private void showSolvedCasesStats() {
        System.out.println("pik");
    }

    @FXML
    private void showCompensationStats() {
        System.out.println("kut");
    }
    
    private ArrayList<String> findSelectedAirports(){
        ArrayList<String> selectedAirports = new ArrayList<>();
        ObservableList<Node> checkBoxList = checkBoxVBox.getChildren();
        
        CheckBox cb;
        for (int i = 0; i < checkBoxList.size(); i++) {
            cb = (CheckBox) checkBoxList.get(i);
            if (cb.isSelected()) {
                selectedAirports.add(cb.getText());
            }
        }
        return selectedAirports;
    }

    @FXML
    private String datePickerToSQLString() {
        String date;
        if (datePickerStart.getValue() != null) {
            LocalDate datum = datePickerStart.getValue();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            date = datum.format(format);
            System.out.println(datum);
            System.out.println(datum.toString());
            System.out.println(date);
        } else {
            return null;
        }
        return date;
    }

    private void populatePieChart(PieChart chart, ArrayList<ChartEntry> entryList) throws SQLException {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < entryList.size(); i++) {
            pieChartData.add(new PieChart.Data(entryList.get(i).getEntry(), entryList.get(i).getAmount()));
        }
        
        chart.setData(pieChartData);
        chart.setTitle("Missing Luggage");
    }

    private ArrayList amountOfMissingAsPieChart(String category, ArrayList<String> airports) {
        ArrayList<ChartEntry> entryList = new ArrayList<>();
        try {
            String query = String.format("SELECT COUNT(registrationnr) AS amount, %s\n" +
                                         "FROM Foundbagageinventory INNER JOIN Flight ON flightnr = flightnumber\n" +
                                         "GROUP BY flightnumber", category);

            Database database = new Database();
            ResultSet result = database.executeResultSetQuery(query);
            while (result.next()) {
                ChartEntry entry = new ChartEntry(result.getInt("amount"), result.getString("from"));
                entryList.add(entry);
            }

            result.close();
            database.close();

        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entryList;
    }
}
