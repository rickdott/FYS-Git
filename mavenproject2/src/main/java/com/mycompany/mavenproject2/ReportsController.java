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
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author Rick
 */
public class ReportsController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showMissingStats();
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void showMissingStats() throws SQLException {
        System.out.println("Showing missing stats");
        pieChart.setVisible(true);
//        populatePieChart(pieChart);
    }

    @FXML
    private void showFoundStats() {
        countAppearances("primarycolour");
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

    private void populatePieChart(PieChart chart, ArrayList entryList) throws SQLException {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
        for (int i = 0, i < entryList.size(), i++) {
            pieChartData.addAll(entryList);
        }
        
        chart.setData (pieChartData);
        chart.setTitle("Vermissingen per kleur 2017");
    }

    private ArrayList countAppearances(String category) {

        class chartEntry {

            int amount;
            String entry;

            public chartEntry(int amount, String entry) {
                this.amount = amount;
                this.entry = entry;
            }
        }

        ArrayList<chartEntry> entryList = new ArrayList<>();

        try {
            int counter = 0;
            String query = String.format("SELECT COUNT(registrationnr) AS amount, %s FROM Lostbagage GROUP BY %s", category, category);

            Database database = new Database();
            ResultSet result = database.executeResultSetQuery(query);
            while (result.next()) {
                chartEntry entry = new chartEntry(result.getInt("amount"), result.getString(category));
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
