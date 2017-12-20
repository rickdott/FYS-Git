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
        populatePieChart(pieChart, countAppearances("primarycolour"));
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

    private void populatePieChart(PieChart chart, ArrayList<ChartEntry> entryList) throws SQLException {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < entryList.size(); i++) {
            pieChartData.add(new PieChart.Data(entryList.get(i).getEntry(), entryList.get(i).getAmount()));
        }

        
//            new PieChart.Data(entryList.get(0).getEntry(), entryList.get(0).getAmount());

        chart.setData(pieChartData);
        chart.setTitle("Vermissingen per kleur 2017");
    }

    private ArrayList countAppearances(String category) {
        ArrayList<ChartEntry> entryList = new ArrayList<>();
        try {
            String query = String.format("SELECT COUNT(registrationnr) AS amount, %s FROM Lostbagage GROUP BY %s", category, category);

            Database database = new Database();
            ResultSet result = database.executeResultSetQuery(query);
            while (result.next()) {
                String colour = database.executeStringListQuery(String.format("SELECT english FROM Colour WHERE ralcode = '%s'", result.getString(category)));
                ChartEntry entry = new ChartEntry(result.getInt("amount"), colour);
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
