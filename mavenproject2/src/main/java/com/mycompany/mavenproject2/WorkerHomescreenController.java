package com.mycompany.mavenproject2;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.text.Text;

/**
 * NEEDS TO BE CLEANED UP
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
    private Button but1, but2, but3, but4, but5, buttonLogout;

    @FXML
    private VBox VBPane;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResourceBundle mybundle = ResourceBundle.getBundle("languages.Language");
        
        but1.setText(mybundle.getString("Request_Status"));
        but2.setText(mybundle.getString("Register_Missing"));
        but3.setText(mybundle.getString("Reports"));
        but5.setText(mybundle.getString("Register_Found"));
        but4.setText(mybundle.getString("User_Roles"));
        buttonLogout.setText(mybundle.getString("Log_Out"));
        
        
        //infoBox("User ID = " + utilities.userID, "Success", null);
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
                                utilities.newPane("Reports", but3 , mainpage, label1);
                                break;
                            case "UserRoles":
                                utilities.newPane("UserRoles", but4, mainpage, label1);
                                break;
                            case "LoginEmployee":
                                utilities.newPane("LoginEmployee", actualmain);
                                break;
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
    Utilities utilities = new Utilities();

    //Methods to open other pages
    @FXML
    private void openRequestStatus(ActionEvent event) {
        utilities.newPane("RequestStatus",but1, mainpage, label1);
    }

    @FXML
    private void openRegisterMissing(ActionEvent event) {
        utilities.newPane("RegisterMissing",but2, mainpage, label1);
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

    // All methods for hovering off a button
    @FXML
    private void offHoverbut1() {
        Utilities.offHover("Request Status",but1, label1);
    }

    @FXML
    private void offHoverbut2() {
        Utilities.offHover("Register Missing",but2, label1);
    }

    @FXML
    private void offHoverbut3() {
        Utilities.offHover("Request Status",but3, label1);
    }

    @FXML
    private void offHoverbut4() {
        Utilities.offHover("Register Missing",but4, label1);
    }

    // All methods for hovering over a button
    @FXML
    private void onHoverbut1() {
        Utilities.onHover("Request Status",but1, label1);
    }

    @FXML
    private void onHoverbut2() {
        Utilities.onHover("Register Missing",but2, label1);
    }

    @FXML
    private void onHoverbut3() {
        Utilities.onHover("Register Missing",but3, label1);
    }

    @FXML
    private void onHoverbut4() {
        Utilities.onHover("Register Missing",but4, label1);
    }

    @FXML
    private void backToLogin(ActionEvent event) {
        utilities.newAnchorpane("LoginEmployee", actualmain);
        utilities.setEmployee(0, 0);

    }
}
