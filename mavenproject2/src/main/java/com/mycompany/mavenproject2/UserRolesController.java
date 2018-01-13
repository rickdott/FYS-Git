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
 *
 * @author TaRick /
 */
public class UserRolesController implements Initializable {

    @FXML
    private AnchorPane mainpane;

    @FXML
    private TableView<UserDetails> TableUser;
    @FXML
    private TableColumn<UserDetails,String> firstname;
    @FXML
    private TableColumn<UserDetails, String> lastname;
    @FXML
    private TableColumn<UserDetails, String> username;
    @FXML
    private TableColumn<UserDetails , String> password;
    @FXML
    private TableColumn<UserDetails , String> roleID;

    @FXML
    private Button test;

    private ObservableList<UserDetails> data = FXCollections.observableArrayList();

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
            Database dc = new Database();
            ResultSet rs = dc.executeResultSetQuery("SELECT * FROM stanviw199_fys.Employee;");
            while (rs.next()) {
                data.add(new UserDetails(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));

            }
            dc.close();
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        firstname.setCellValueFactory(new PropertyValueFactory<UserDetails , String>("firstName"));
        lastname.setCellValueFactory(new PropertyValueFactory<UserDetails , String>("lastName"));
        username.setCellValueFactory(new PropertyValueFactory<UserDetails , String>("userName"));
        password.setCellValueFactory(new PropertyValueFactory<UserDetails, String>("passWord"));
        TableUser.setItems(data);

    }

}
