/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                new PieChart.Data("Schiphol", countAppearances("Schiphol")),
                new PieChart.Data("Malaga", countAppearances("Malaga")),
                new PieChart.Data("Parijs", countAppearances("Parijs")),
                new PieChart.Data("London", countAppearances("London")),
                new PieChart.Data("Athene", countAppearances("Athene")));
        chart.setData(pieChartData);
        chart.setTitle("Vermissingen 2017");
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

        String query = "SELECT * FROM Bagage WHERE destination = '";
        query += destination;
        query += "'";

        Database database = new Database();
        ResultSet result = database.executeResultSetQuery(query);
        while (result.next()) {
            counter++;
        }
        return counter;
    }
}
