/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import static com.mycompany.mavenproject2.RegisterFoundController.infoBox;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.mail.FetchProfile.Item;

/**
 * <<<<<<< HEAD
 *
 * @author TaRick / ======= YET TO BE IMPLEMENTED
 * @author Tarik 500780772
 *
 */
public class UserRolesController implements Initializable {
    public static String editUser;
    UserDetails U = new UserDetails();

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
        try {
            loadDataFromDatabase();
        } catch (SQLException ex) {
            Logger.getLogger(UserRolesController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private void loadDataFromDatabase() throws SQLException {
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
            user.setRoleTc(rs.getInt("RoleId"));
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

    @FXML
    private void deleteUser(ActionEvent event) throws SQLException {
        Database dc = new Database();

        if (tableUser.getSelectionModel().isEmpty()) {
            infoBox("Please Select a row", "!", null);

        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Are you ok with this?????");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                UserDetails deleteUser = (UserDetails) tableUser.getSelectionModel().getSelectedItem();

                System.out.println(deleteUser.getUsernameTc());
                String setSafeOff = "SET SQL_SAFE_UPDATES=0;";
                dc.executeUpdateQuery(setSafeOff);
                String deleteUserString = String.format("DELETE FROM Employee WHERE username = '%s';", deleteUser.getUsernameTc());
                System.out.println(deleteUserString);
                dc.executeUpdateQuery(deleteUserString);
                String setSafeOn = "SET SQL_SAFE_UPDATES=1;";
                dc.executeUpdateQuery(setSafeOn);
                loadDataFromDatabase();

            } else {
                System.out.println("cancelled");
            }
        }

    }

    @FXML
    private void editUser(ActionEvent event) {
        if (tableUser.getSelectionModel().isEmpty()) {
            infoBox("Please Select a row", "!", null);
        }
        {
            UserDetails edituser1 = (UserDetails) tableUser.getSelectionModel().getSelectedItem();
            editUser = edituser1.getUsernameTc().toString();
            
            
            
            utilities.newAnchorpane("UserRoles_EditUser", mainpane);
        }
    }

    private boolean areYouSureBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == ButtonType.OK);
    }
}
