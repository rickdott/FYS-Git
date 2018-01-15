package com.mycompany.mavenproject2;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * Controller that handles the reports section, showing different statistics on
 * missing luggage
 *
 * @author Rick den Otter 500749952 (360 lines)
 */
public class ReportsController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");
        
        labelSee.setText(mybundle.getString("What_would_you_like_to_see?"));
        missingLuggageToggle.setText(mybundle.getString("Missing_Luggage"));
        foundLuggageToggle.setText(mybundle.getString("Found_Luggage"));
        missingLuggagePerMonthToggle.setText(mybundle.getString("Missing_Luggage/month"));
        solvedToggle.setText(mybundle.getString("Solved_cases"));
        compensationToggle.setText(mybundle.getString("Compensation"));
        submitFoundMissingBut.setText(mybundle.getString("Submit"));
        submitMissingPerMonthBut.setText(mybundle.getString("Submit"));
        
        
        try {
            showMissingStats();
            ArrayList<String> listOfYears = getYearsInDB();
            years.clear();
            years.addAll(listOfYears);
            yearComboBox.setItems(years);
        } catch (SQLException ex) {
            Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML    
    private Label labelSee;
        
    @FXML 
    private RadioButton compensationToggle, solvedToggle, missingLuggagePerMonthToggle, foundLuggageToggle, missingLuggageToggle;

    private static ObservableList<String> years = FXCollections.observableArrayList();
    
    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart lineChart;

    @FXML
    private ToggleGroup selectionGroup;

    //@FXML
    //private Toggle missingLuggageToggle, foundLuggageToggle, missingLuggagePerMonthToggle, solvedToggle, compensationToggle;

    @FXML
    private VBox missingVBox, missingPerMonthVBox;

    @FXML
    private ComboBox yearComboBox;

    @FXML
    private Button submitFoundMissingBut, submitMissingPerMonthBut;

    /**
     * Class to make filling charts easier
     */
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

        public void incrementAmount(double amount) {
            this.amount += amount;
        }

    }

    @FXML
    private void showMissingStats() throws SQLException {
        System.out.println("Showing missing stats");
        lineChart.setVisible(false);
        pieChart.setVisible(true);
        yearComboBox.setVisible(false);
        submitFoundMissingBut.setVisible(true);
        submitMissingPerMonthBut.setVisible(false);
        populatePieChart(pieChart, amountOfMissingAsPieChart("Flight.from", "Lostbagage", findSelectedAirports()));
    }

    @FXML
    private void showFoundStats() throws SQLException {
        System.out.println("Showing found stats");
        lineChart.setVisible(false);
        pieChart.setVisible(true);
        yearComboBox.setVisible(false);
        submitFoundMissingBut.setVisible(true);
        submitMissingPerMonthBut.setVisible(false);
        populatePieChart(pieChart, amountOfMissingAsPieChart("Flight.from", "Foundbagageinventory", findSelectedAirports()));
    }

    @FXML
    private void showMissingPerMonthStats() throws SQLException {
        System.out.println("Showing missing per month stats");
        yearComboBox.setVisible(true);
        submitFoundMissingBut.setVisible(false);
        submitMissingPerMonthBut.setVisible(true);
        String year;
        if (yearComboBox.getValue() != null) {
            year = (String) yearComboBox.getValue();
        } else {
            year = years.get(years.size() - 1);
        }

        populateLineChart(lineChart, findSelectedAirports(), year);
        pieChart.setVisible(false);
        missingPerMonthVBox.setVisible(true);
        lineChart.setVisible(true);
    }

    @FXML
    private void showSolvedCasesStats() {
        System.out.println("Not implemented");
    }

    @FXML
    private void showCompensationStats() {
        System.out.println("Not implement");
    }

    /**
     * Finds the airports currently selected in the reports section
     * @return ArrayList of strings containing airports
     */
    private ArrayList<String> findSelectedAirports() {
        ArrayList<String> selectedAirports = new ArrayList<>();
        ObservableList<Node> checkBoxList = missingVBox.getChildren();

        CheckBox cb;
        for (int i = 0; i < checkBoxList.size(); i++) {
            if (checkBoxList.get(i) instanceof CheckBox) {
                cb = (CheckBox) checkBoxList.get(i);
                if (cb.isSelected()) {
                    selectedAirports.add(cb.getText());
                }
            }
        }
        for (int i = 0; i < selectedAirports.size(); i++) {
            System.out.println(selectedAirports.get(i));
        }
        return selectedAirports;
    }

    /**
     * Populates a piechart
     * @param chart the chart you want to populate
     * @param entryList ArrayList of ChartEntry objects
     * @throws SQLException 
     */
    private void populatePieChart(PieChart chart, ArrayList<ChartEntry> entryList) throws SQLException {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (int i = 0; i < entryList.size(); i++) {
            pieChartData.add(new PieChart.Data(entryList.get(i).getEntry() + ", " + (int) entryList.get(i).getAmount(), entryList.get(i).getAmount()));
        }

        chart.setData(pieChartData);
    }

    /**
     * Checks the amount of missing or found luggage per airport
     * @param category What table to find the information in
     * @param lostOrFound name of the lost or found table, depending on what category you want
     * @param airports List of airports currently selected
     * @return returns an arrayList of ChartEntries
     * @throws SQLException 
     */
    private ArrayList amountOfMissingAsPieChart(String category, String lostOrFound, ArrayList<String> airports) throws SQLException {
        ArrayList<ChartEntry> entryList = new ArrayList<>();

        String query = String.format("SELECT COUNT(registrationnr) AS amount, %s\n"
                + "FROM %s INNER JOIN Flight ON flightnr = flightnumber\n",
                category, lostOrFound);
        if (!airports.isEmpty()) {
            for (int i = 0; i < airports.size(); i++) {
                if (i == 0) {
                    query += "WHERE ";
                } else if (i == airports.size() - 1) {
                    query += String.format("%s = '%s' ", category, airports.get(i));
                } else {
                    query += String.format("%s = '%s' OR ", category, airports.get(i));
                }
            }
        }
        query += "GROUP BY flightnumber";

        Database database = new Database();
        ResultSet result = database.executeResultSetQuery(query);
        while (result.next()) {
            ChartEntry entry = new ChartEntry(result.getInt("amount"), result.getString("from"));
            entryList.add(entry);
        }

        for (int i = 0; i < entryList.size(); i++) {
            String value = entryList.get(i).getEntry();

            for (int j = i + 1; j < entryList.size(); j++) {
                if (value.equals(entryList.get(j).getEntry())) {
                    entryList.get(i).incrementAmount(entryList.get(j).getAmount());
                    entryList.remove(j);
                    j--;
                }
            }
        }

        result.close();
        database.close();

        return entryList;
    }

    @FXML
    private void submitFoundMissingButton() throws SQLException {
        System.out.println("Testfound/missing");
        if (missingLuggageToggle.isSelected()) {
            showMissingStats();
        } else {
            showFoundStats();
        }
    }

    /**
     * Method to populate a line chart
     * @param chart the LineChart to populate
     * @param airports the currently selected airports
     * @param year the year you want information on
     * @throws SQLException 
     */
    private void populateLineChart(LineChart chart, ArrayList<String> airports, String year) throws SQLException {
        chart.setAnimated(false);
        chart.getData().clear();

        String query = "SELECT datefound\n"
                + "FROM Foundbagageinventory INNER JOIN Flight ON flightnr = flightnumber\n"
                + "WHERE datefound LIKE '%" + year + "' ";

        if (!airports.isEmpty()) {
            for (int i = 0; i < airports.size(); i++) {
                if (i == 0) {
                    query += "AND Flight.from IN(";
                } else if (i == airports.size() - 1) {
                    query += String.format("'%s') ", airports.get(i));
                } else {
                    query += String.format("'%s',  ", airports.get(i));
                }
            }
        }

        Database database = new Database();
        ResultSet result = database.executeResultSetQuery(query);

        int[] amountArray = new int[12];
        String[] monthArray = {"jan", "feb", "mrt", "apr", "mei", "jun", "jul", "aug", "sep", "okt", "nov", "dec"};
        while (result.next()) {
            String currentMonth = result.getString("datefound").substring(3, 6);
            switch (currentMonth) {
                case "jan":
                    amountArray[0]++;
                    break;
                case "feb":
                    amountArray[1]++;
                    break;
                case "mrt":
                    amountArray[2]++;
                    break;
                case "apr":
                    amountArray[3]++;
                    break;
                case "mei":
                    amountArray[4]++;
                    break;
                case "jun":
                    amountArray[5]++;
                    break;
                case "jul":
                    amountArray[6]++;
                    break;
                case "aug":
                    amountArray[7]++;
                    break;
                case "sep":
                    amountArray[8]++;
                    break;
                case "okt":
                    amountArray[9]++;
                    break;
                case "nov":
                    amountArray[10]++;
                    break;
                case "dec":
                    amountArray[11]++;
                    break;
                default:
                    System.out.println("default called");
            }
        }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        XYChart.Series data = new XYChart.Series();
        data.setName("Antalya");
        for (int i = 0; i < monthArray.length; i++) {
            data.getData().add(new XYChart.Data(monthArray[i], amountArray[i]));
        }
        chart.getData().add(data);

    }

    /**
     * Gets the years to use in the reports section of the application
     * @return Returns an ArrayList<String> of years available
     * @throws SQLException 
     */
    private ArrayList<String> getYearsInDB() throws SQLException {
        Database database = new Database();
        String query = "SELECT datefound FROM stanviw199_fys.Foundbagageinventory";
        ArrayList<String> list = new ArrayList<>();
        ResultSet result = database.executeResultSetQuery(query);
        while (result.next()) {
            String year = result.getString("datefound").substring(7, 11);
            if (!list.contains(year)) {
                list.add(year);
            }
        }

        return list;
    }

}
