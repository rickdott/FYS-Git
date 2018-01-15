/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private ObservableList<UserDetails> userList
            = FXCollections.observableArrayList();

    Utilities utilities = new Utilities();

    @FXML
    private AnchorPane mainpane;

    @FXML
    private TableView tableUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableUser.setItems(this.userList);

        for (int i = 0; i < tableUser.getColumns().size(); i++) {
            TableColumn tc = (TableColumn) tableUser.getColumns().get(i);
            String propertyName = tc.getId();
            if (propertyName != null && !propertyName.isEmpty()) {
                tc.setCellValueFactory(new PropertyValueFactory<>(propertyName));
                System.out.println("Attached column '" + propertyName + "' in tableview to matching attribute");
            }
        }
    }
    
    @FXML
    private void loadDataFromDatabase(ActionEvent event) throws SQLException {
        Database dc = new Database();
        ResultSet rs = dc.executeResultSetQuery("SELECT * FROM Employee;");
        ObservableList<UserDetails> list
            = FXCollections.observableArrayList();
        while (rs.next()) {
            UserDetails user = new UserDetails();
            user.setFirstnameTc(rs.getString("firstname"));
            user.setLastnameTc(rs.getString("lastname"));
            user.setUsernameTc(rs.getString("username"));
            user.setPasswordTc(rs.getString("password"));
            
            System.out.println(user.getFirstnameTc());
            list.add(user);
        }
        userList.setAll(list);
        dc.close();
    }

    @FXML
    private void openAddRoles(ActionEvent event) {
        utilities.newAnchorpane("UserRoles_AddUser", mainpane);

    }


}
