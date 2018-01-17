package com.mycompany.mavenproject2;

import static com.mycompany.mavenproject2.UserRolesController.editUser;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Tarik Yildirim 500780772
 */
public class UserRoles_EditUserController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Role.setItems(Roles);

        try {
            viewTable();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoles_EditUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setText();
    }

    Database db = new Database();
    private String first1;
    private String last1;
    private String pass1;
    private int role1;
    private int valueRole;

    Utilities utilities = new Utilities();
    @FXML
    private TextField first;
    @FXML
    private TextField last;
    @FXML
    private TextField user;
    @FXML
    private TextField pass;
    @FXML
    private ComboBox<String> Role;
    @FXML
    private AnchorPane UserRoles;

    ObservableList<String> Roles = FXCollections.observableArrayList(
            "Medewerker", "Manager");

    public void setText() {

        first.setText(first1);
        last.setText(last1);
        user.setText(editUser);
        pass.setText(pass1);

        if (role1 == 1) {
            Role.setValue("Medewerker");

        } else {
            Role.setValue("Manager");
        }

    }

    public void query() {
        String setSafeOff = "SET SQL_SAFE_UPDATES=0;";
        db.executeUpdateQuery(setSafeOff);
        String userNew = String.format("UPDATE Employee SET firstname='%s',lastname = '%s',username = '%s',password = '%s',RoleId = '%s' WHERE username='%s';", first.getText(), last.getText(), user.getText(), pass.getText(), valueRole, editUser);
        db.executeUpdateQuery(userNew);
        String setSafeOn = "SET SQL_SAFE_UPDATES=1;";
        db.executeUpdateQuery(setSafeOn);
    }

    public void getText() {

    }

    @FXML
    public void testEdit(ActionEvent event) {
        UserDetails ud = new UserDetails();
        System.out.println(ud.getUsernameTc());
        System.out.println(editUser);

        getValueRole();
        System.out.println("ValueRole =" + valueRole);
        try {
            viewTable();
            query();
        } catch (SQLException ex) {
            Logger.getLogger(UserRoles_EditUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        utilities.newAnchorpane("UserAdd", UserRoles);

    }

    public void getValueRole() {
        if (Role.getSelectionModel().isSelected(0)) {
            valueRole = 1;
        } else {
            valueRole = 2;
        }
    }

    public void setValueRole() {

    }

    public void viewTable()
            throws SQLException {

        Statement stmt = null;
        String query
                = String.format("SELECT * FROM Employee Where username = '%s';", editUser);

        try {
            ResultSet rs = db.executeResultSetQuery(query);
            while (rs.next()) {
                first1 = rs.getString("firstname");
                last1 = rs.getString("lastname");
                pass1 = rs.getString("password");
                role1 = rs.getInt("RoleId");
                System.out.println(query);
                System.out.println(first1);
                System.out.println(last1);
                System.out.println(pass1);
                System.out.println(role1);
            }
        } catch (SQLException e) {
            System.err.println("Error" + e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

}
