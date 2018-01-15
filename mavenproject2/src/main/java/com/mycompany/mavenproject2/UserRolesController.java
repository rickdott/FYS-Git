/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * <<<<<<< HEAD
 *
 * @author TaRick / ======= YET TO BE IMPLEMENTED
 * @author Tarik 500780772
 *
 */
public class UserRolesController implements Initializable {

    private String Firstname;
    private int IdEmployee;
    private ObservableList<UserDetails> data = FXCollections.observableArrayList();
    private ObservableList<String> listFirstname = FXCollections.observableArrayList();
    Database dc = new Database();

    @FXML
    private void tijdelijkTest(ActionEvent event) throws SQLException {
        selectQuery();
    }

    public void selectQuery()
            throws SQLException {

        Statement stmt = null;
        String query
                = "SELECT max(idEmployee)FROM Employee";

        try {
            ResultSet rs = dc.executeResultSetQuery(query);
            while (rs.next()) {
                IdEmployee = rs.getInt(1);
                System.out.println(IdEmployee);

            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    public void resultTable() {
        /*for (int i = 0; i < IdEmployee; i++) {
            listFirstname = dc.executeStringListQuery(String.format("SELECT firstname FROM Employee"));
            System.out.println("Firstname");
        }*/
    }

    @FXML
    private AnchorPane mainpane;

    @FXML
    private TableView<UserDetails> TableUser;
    @FXML
    private TableColumn<UserDetails, String> firstnameC;
    @FXML
    private TableColumn<UserDetails, String> lastnameC;
    @FXML
    private TableColumn<UserDetails, String> usernameC;
    @FXML
    private TableColumn<UserDetails, String> passwordC;
    @FXML
    private TableColumn<UserDetails, String> roleIDC;

    @FXML
    private Button test;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableUser.setItems(data);
        data = FXCollections.observableArrayList();
    }

    Utilities utilities = new Utilities();

    @FXML
    private void openAddRoles(ActionEvent event) {
        utilities.newAnchorpane("UserRoles_AddUser", mainpane);

    }

    @FXML
    private void loadDataFromDatabase(ActionEvent event) throws SQLException {
        try {

            ResultSet rs = dc.executeResultSetQuery("SELECT * FROM stanviw199_fys.Employee;");
            while (rs.next()) {
                data.add(new UserDetails(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

            }
            dc.close();
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        /* firstname.setCellValueFactory(new PropertyValueFactory<UserDetails, String>("firstName"));
        lastname.setCellValueFactory(new PropertyValueFactory<UserDetails, String>("lastName"));
        username.setCellValueFactory(new PropertyValueFactory<UserDetails, String>("userName"));
        password.setCellValueFactory(new PropertyValueFactory<UserDetails, String>("passWord"));
        TableUser.setItems(data);*/
    }

}
