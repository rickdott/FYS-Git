/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import static com.mycompany.mavenproject2.LoginController.infoBox;
//import static com.mycompany.mavenproject2.RegisterFoundController.infoBox;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Tarik Yildirim 500780772
 */
public class UserRoles_AddUsersController implements Initializable {

    Database dc = new Database();
    private int IdEmployee;
    @FXML
    private ComboBox<String> Role;
    @FXML
    private AnchorPane UserRoles;
    @FXML
    private TextField user;
    @FXML
    private TextField last;
    @FXML
    private TextField first;
    @FXML
    private TextField pass;

    ObservableList<String> Roles = FXCollections.observableArrayList(
            "Manager", "Medewerker");
    
    
    @FXML
    private boolean checkUsername() throws SQLException {
        String InputUser = user.getText();
        String OutputDatabase = dc.executeStringListQuery(String.format("SELECT username FROM Employee WHERE username = '%s'", user.getText()));
        if (InputUser == null ? OutputDatabase == null : InputUser.equals(OutputDatabase)){
            return true;
            //infoBox("Username already taken", "!", null);
        }
        else{
            return false;
        }
          
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

    @FXML
    private void confirmbtn() throws SQLException {
        int RoleValue;
        selectQuery();
        IdEmployee++;

        if (!Role.getSelectionModel().isSelected(-1)
                && !user.getText().trim().isEmpty()
                && !last.getText().trim().isEmpty()
                && !first.getText().trim().isEmpty()
                && !pass.getText().trim().isEmpty()) {
            if (Role.getSelectionModel().isSelected(0)) {
                RoleValue = 2;
            } else {
                RoleValue = 1;
            }
            if (checkUsername() == true) {
                System.out.println(checkUsername());

              infoBox("Username already taken", "!", null);
            } else {
                System.out.println(checkUsername());
                String AddUser = String.format("INSERT INTO "
                        + "Employee (idEmployee, firstname, lastname, username, password, RoleID)"
                        + "VALUES ('%s','%s', '%s','%s','%s','%s')",
                        IdEmployee, first.getText(), last.getText(), user.getText(), pass.getText(), RoleValue);

                System.out.println(AddUser);
                dc.executeUpdateQuery(AddUser);

                infoBox("Change has been successfully made!", "Complete", null);
            }
        } else {
            infoBox("Please try to fill in everyting!", "Error", null);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Role.setItems(Roles);

    }
    Utilities utilities = new Utilities();
      
}
