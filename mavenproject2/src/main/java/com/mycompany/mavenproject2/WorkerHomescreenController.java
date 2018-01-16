package com.mycompany.mavenproject2;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * NEEDS TO BE CLEANED UP
 *
 * @author Matthijs Snijders 500780453
 */
public class WorkerHomescreenController implements Initializable {

    @FXML
    private AnchorPane actualmain;

    @FXML
    private Pane mainpage;

    @FXML
    private Label label1;

    @FXML
    private Button but1, but2, but3, but4, but5, but6, but7;

    @FXML
    private VBox VBPane;

    Utilities utilities = new Utilities();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Database db = new Database();
        ResultSet resultSet = null;
        String sqlMenu = String.format("SELECT * FROM Menu "
                + "WHERE idMenu IN (SELECT MenuID FROM MenuRoles WHERE RoleID = " + utilities.roleID + ") ");
        try {
            resultSet = db.executeResultSetQuery(sqlMenu);
            int i = 0;
            while (resultSet.next()) {
                Button button = new Button(resultSet.getString("Name"));
                //VBPane.add(button, 1, i++);
                VBPane.setPrefWidth(100);
                button.setMinWidth(VBPane.getPrefWidth());
                VBPane.getChildren().add(button);

                button.setStyle("-fx-font: 22 arial; -fx-base: #d81e05; "
                        + "-fx-background-radius: 0; -fx-border-width: 1 0 0 0; "
                        + "-fx-border-color: white; -fx-font-weight: bold; "
                        + "-fx-font-size: 18; -fx-alignment: CENTER;");

                final String url2 = resultSet.getString("Link");

                /**
                 * Sets the standard button style
                 */
                if (resultSet.isLast()) {
                    button.setStyle("-fx-font: 22 arial; -fx-background-color: #d81e05; "
                            + "-fx-background-radius: 0; -fx-border-width: 1 0 1 0; "
                            + "-fx-border-color: white; -fx-font-weight: bold; "
                            + "-fx-font-size: 18; -fx-alignment: CENTER;"
                            + "-fx-min-width: 227; -fx-min-height: 50;"
                            + "-fx-text-fill: white;");
                } else {
                    button.setStyle("-fx-font: 22 arial; -fx-background-color: #d81e05; "
                            + "-fx-background-radius: 0; -fx-border-width: 1 0 0 0; "
                            + "-fx-border-color: white; -fx-font-weight: bold; "
                            + "-fx-font-size: 18; -fx-alignment: CENTER;"
                            + "-fx-min-width: 227; -fx-min-height: 50;"
                            + "-fx-text-fill: white;");
                }

                /**
                 * Gives all the buttons the correct onHover and offHover
                 */
                switch (url2) {
                    case "RequestStatus":
                        but1 = button;
                        Utilities.onHover("Request Status", but1, label1);
                        Utilities.offHover("Request Status", but1, label1);
                        break;
                    case "RegisterMissing":
                        but2 = button;
                        Utilities.onHover("Register Missing", but2, label1);
                        Utilities.offHover("Register Missing", but2, label1);
                        break;
                    case "Reports":
                        but3 = button;
                        Utilities.onHover("Reports", but3, label1);
                        Utilities.offHover("Reports", but3, label1);
                        break;
                    case "UserRoles":
                        but4 = button;
                        Utilities.onHover("UserRoles", but4, label1);
                        Utilities.offHover("UserRoles", but4, label1);
                        break;
                    case "RegisterFound":
                        but5 = button;
                        Utilities.onHover("RegisterFound", but5, label1);
                        Utilities.offHover("RegisterFound", but5, label1);
                        Utilities.offHover("RegisteFound", but5, label1);
                        break;
                    case "Login":
                        but6 = button;
                        Utilities.onHover("Login", but6, label1);
                        Utilities.offHover("Login", but6, label1);
                        break;
                    default:
                        break;
                }
                /**
                 * Gives all the buttons the correct link
                 */

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        switch (url2) {
                            case "RequestStatus":
                                utilities.newPane("RequestStatus", but1, mainpage, label1);
                                break;
                            case "RegisterMissing":
                                utilities.newPane("RegisterMissing", but2, mainpage, label1);
                                break;
                            case "Reports":
                                utilities.newPane("Reports", but3, mainpage, label1);
                                break;
                            case "UserRoles":
                                utilities.newPane("UserRoles", but4, mainpage, label1);
                                break;
                            case "RegisterFound":
                                utilities.newPane("RegisterFound", but5, mainpage, label1);
                                break;
                            case "LoginEmployee":
                                utilities.newPane("LoginEmployee", actualmain);
                                break;
                            case "Login":
                                utilities.newPane("Login", actualmain);
                            default:
                                break;
                        }
                    }
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //Methods to open other pages
    @FXML
    private void openRequestStatus(ActionEvent event) {
        utilities.newPane("RequestStatus", but1, mainpage, label1);
    }

    @FXML
    private void openRegisterMissing(ActionEvent event) {
        utilities.newPane("RegisterMissing", but2, mainpage, label1);
    }

    //TEMP
    @FXML
    private void openRequestTest(ActionEvent event) {
        utilities.newPane("RequestStatus", but3, mainpage, label1);
    }

    @FXML
    private void openRegistertesting(ActionEvent event) {
        utilities.newPane("RegisterMissing", but4, mainpage, label1);
    }

    @FXML
    private void openRegisterFound(ActionEvent event) {
        utilities.newPane("RegisterFound", but5, mainpage, label1);
    }

    @FXML
    private void openPassengerLogin(ActionEvent event) {
        utilities.newPane("Login", but6, mainpage, label1);
    }

    // All methods for hovering off a button
    @FXML
    private void offHoverbut1() {
        Utilities.offHover("Request Status", but1, label1);
    }

    @FXML
    private void offHoverbut2() {
        Utilities.offHover("Register Missing", but2, label1);
    }

    @FXML
    private void offHoverbut3() {
        Utilities.offHover("Request Status", but3, label1);
    }

    @FXML
    private void offHoverbut4() {
        Utilities.offHover("Register Missing", but4, label1);
    }

    @FXML
    private void offHoverbut5() {
        Utilities.offHover("registerFound", but5, label1);
    }

    @FXML
    private void offHoverbut6() {
        Utilities.offHover("Login", but6, label1);
    }

    @FXML
    private void offHoverbut7() {
        but7.setStyle("-fx-font: 22 arial; -fx-background-color: #d81e05; -fx-background-radius: 0; -fx-border-width: 1 1 1 1; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: white; -fx-min-height: 50; -fx-min-width: 150;");
    }

    // All methods for hovering over a button
    @FXML
    private void onHoverbut1() {
        Utilities.onHover("Request Status", but1, label1);
    }

    @FXML
    private void onHoverbut2() {
        Utilities.onHover("Register Missing", but2, label1);
    }

    @FXML
    private void onHoverbut3() {
        Utilities.onHover("Register Missing", but3, label1);
    }

    @FXML
    private void onHoverbut4() {
        Utilities.onHover("Register Missing", but4, label1);
    }

    @FXML
    private void onHoverbut5() {
        Utilities.onHover("Register Found", but5, label1);
    }

    @FXML
    private void onHoverbut6() {
        Utilities.onHover("Login", but6, label1);
    }

    @FXML
    private void onHoverbut7() {
        but7.setStyle("-fx-font: 22 arial; -fx-background-color: #951504; -fx-background-radius: 0; -fx-border-width: 1 1 1 1; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18; -fx-text-fill: white; -fx-min-height: 50; -fx-min-width: 150;");
    }

    @FXML
    private void backToLogin(ActionEvent event) {
        utilities.newAnchorpane("LoginEmployee", actualmain);
        utilities.setEmployee(0, 0);

    }
}
