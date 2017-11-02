package com.mycompany.mavenproject2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author Rick den Otter 500749952 Lines 93-156
 * Stan van Weringh 500771870 Lines 46-91
 * This file contains some methods for use when buttons are clicked or when the 
 * program has to switch pages
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Pane mainpage;

    @FXML
    private Label label1;

    @FXML
    private Button but1, but2, but3, but4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    // All methods for hovering off a button
    @FXML
    private void onHoverbut1() {
        onHover("Request Status", but1);
    }

    @FXML
    private void onHoverbut2() {
        onHover("Register Missing", but2);
    }

    @FXML
    private void onHoverbut3() {
        onHover("Reports", but3);
    }

    @FXML
    private void onHoverbut4() {
        onHover("User Roles", but4, true);
    }
    
    @FXML
    private void onHover(String currentPage, Button btn) {
        System.out.println("onHover activated");
        if (!label1.getText().equals(currentPage)) {
            btn.setStyle("-fx-background-color: #951504; -fx-background-radius: 0; -fx-border-width: 1 0 0 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    @FXML
    private void onHover(String currentPage, Button btn, boolean isLast) {
        System.out.println("onHover activated");
        if (!label1.getText().equals(currentPage) && isLast) {
            btn.setStyle("-fx-background-color: #951504; -fx-background-radius: 0; -fx-border-width: 1 0 1 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }
    
    // All methods for hovering off a button
    @FXML
    private void offHoverbut1() {
        offHover("Request Status", but1);
    }

    @FXML
    private void offHoverbut2() {
        offHover("Register Missing", but2);
    }

    @FXML
    private void offHoverbut3() {
        offHover("Reports", but3);
    }

    @FXML
    private void offHoverbut4() {
        offHover("User Roles", but4, true);
    }


    // Two methods to use when hovered off the button, overloaded
    @FXML
    private void offHover(String currentPage, Button btn) {
        System.out.println("offHover activated");
        if (!label1.getText().equals(currentPage)) {
            btn.setStyle("-fx-background-color: #D81E05; -fx-background-radius: 0; -fx-border-width: 1 0 0 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    @FXML
    private void offHover(String currentPage, Button btn, boolean isLast) {
        System.out.println("offHover activated");
        if (!label1.getText().equals(currentPage) && isLast) {
            btn.setStyle("-fx-background-color: #D81E05; -fx-background-radius: 0; -fx-border-width: 1 0 1 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    @FXML
    private void openRequestStatus(ActionEvent event) {
        openPage("RequestStatus", but1);

    }

    @FXML
    private void openRegisterMissing(ActionEvent event) {
        openPage("RegisterMissing", but2);

    }

    @FXML
    private void openUserRoles(ActionEvent event) {
        openPage("UserRoles", but4);

    }

    // Opens a different page
    @FXML
    private void openPage(String pageName, Button btn) {
        System.out.println("Andere pagina geopend");
        Parent pane = loadFXMLFile("/fxml/" + pageName + ".fxml");
        mainpage.getChildren().clear();
        mainpage.getChildren().add(pane);
        label1.setText(btn.getText());
    }

    private Parent loadFXMLFile(String fxmlFileName) {
        try {
            return FXMLLoader.load(getClass().getResource(fxmlFileName));
        } catch (IOException ex) {
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
            return null;
        }
    }

}
