package com.mycompany.mavenproject2;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.text.Text;

/**
 *
 * @author Rick
 */
public class ReportsController {

    @FXML
    private Text titleText, textText;

    @FXML
    private PieChart pieChart;

    @FXML
    private void showMissingStats() throws SQLException {
        System.out.println("Showing missing stats");
        makeTextInvisible();
        pieChart.setVisible(true);
        populatePieChart(pieChart);

    }

    @FXML
    private void showReports() {
        System.out.println("Showing reports...");
        pieChart.setVisible(false);
        makeTextVisible();
    }

    @FXML
    private void populatePieChart(PieChart chart) throws SQLException {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("1024", countAppearances("1024")),
                new PieChart.Data("3000", countAppearances("3000")),
                new PieChart.Data("1003", countAppearances("1003")),
                new PieChart.Data("3005", countAppearances("3005")),
                new PieChart.Data("4010", countAppearances("4010")));
        chart.setData(pieChartData);
        chart.setTitle("Vermissingen per kleur 2017");
    }

    @FXML
    private void makeTextVisible() {
        titleText.setVisible(true);
        textText.setVisible(true);
    }

    @FXML
    private void makeTextInvisible() {
        titleText.setVisible(false);
        textText.setVisible(false);
    }

    @FXML
    private int countAppearances(String destination) throws SQLException {
        int counter = 0;

        String query = "SELECT * FROM Lostbagage WHERE primarycolour = '";
        query += destination;
        query += "'";

        Database database = new Database();
        ResultSet result = database.executeResultSetQuery(query);
        while (result.next()) {
            counter++;
        }
        
        result.close();
        database.close();
        
        return counter;
    }
}
