package com.mycompany.mavenproject2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Rick den Otter 500749952 Lines 93-156 Stan van Weringh 500771870
 * Lines 46-91 This file contains some methods for use when buttons are clicked
 * or when the program has to switch pages
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Pane mainpage;

    @FXML
    private AnchorPane paneLogin, paneCustomerHomescreen;

    @FXML
    private Label label1;

    @FXML
    private Button but1, but2, but3, but4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // Method for creating a PDF ---MOVE TO RELEVANT CONTROLLER
    @FXML
    private void createPdf() {
        System.out.println("Creating PDF...");
        Pdf pdf = new Pdf();
        pdf.printPDF();
        System.out.println("PDF Created...");
    }

    // Method for sending an e-mail ---MOVE TO RELEVANT CONTROLLER
    @FXML
    private void sendMail() {
        System.out.println("Sending mail...");
        Mail mail = new Mail("baggerfys@gmail.com");
        mail.mailsturen();
        System.out.println("Mail sent...");
    }

    // Method om een Excelsheet te importeren ---MOVE TO RELEVANT CONTROLLER
    @FXML
    private void excelImport() {
        System.out.println("Beginning Excel import...");

        // Roept een method aan in de MainApp die het path returnt
        String filePath = MainApp.fileChoosePath();

        ExcelReader reader = new ExcelReader(filePath);
        List<String> row = new ArrayList<>();

        row = reader.getNextRow(); // De header van het excel bestand
        row = reader.getNextRow();
        String mail = row.get(row.size() - 1);
        System.out.println(mail);

        // Opvragen van de idpassenger die hij zoekt via de mail van de passengier
        Database db = new Database();
        String sql = String.format("SELECT idpassenger FROM Passenger WHERE email = '%s'", mail);
        String idpassenger = db.executeStringListQuery(sql);

        // SQL query die alles invoert in de database
        sql = String.format("INSERT INTO `Bagage`(`labelnumber`, `flightnumber`, `destination`, `type`, `brand`, `colour`, `specialchar`, `passengerid`, `foundat`, `foundatdate`, `date`)VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6), idpassenger, row.get(7), row.get(9), row.get(10));
        db.executeUpdateQuery(sql);
        System.out.println("Excel import complete...");
    }

    @FXML
    private void openCustomerHomescreen(ActionEvent event) {
        newAnchorpane("CustomerHomescreen", paneLogin);
    }

    @FXML
    private void openWorkerHomescreen(ActionEvent event) {
        newAnchorpane("WorkerHomescreen", paneLogin);
    }

    @FXML
    public void onHover(String currentPage, Button btn, Label label) {
        System.out.println("onHover activated");
        if (!label.getText().equals(currentPage)) {
            btn.setStyle("-fx-background-color: #951504; -fx-background-radius: 0; -fx-border-width: 1 0 0 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    @FXML
    public void onHover(String currentPage, Button btn, Label label, boolean isLast) {
        System.out.println("onHover activated");
        if (!label.getText().equals(currentPage) && isLast) {
            btn.setStyle("-fx-background-color: #951504; -fx-background-radius: 0; -fx-border-width: 1 0 1 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    // Two methods to use when hovered off the button, overloaded
    @FXML
    public void offHover(String currentPage, Button btn, Label label) {
        System.out.println("offHover activated");
        if (!label.getText().equals(currentPage)) {
            btn.setStyle("-fx-background-color: #D81E05; -fx-background-radius: 0; -fx-border-width: 1 0 0 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    @FXML
    public void offHover(String currentPage, Button btn, Label label, boolean isLast) {
        System.out.println("offHover activated");
        if (!label.getText().equals(currentPage) && isLast) {
            btn.setStyle("-fx-background-color: #D81E05; -fx-background-radius: 0; -fx-border-width: 1 0 1 0; -fx-border-color: white; -fx-font-weight: bold; -fx-font-size: 18;");
        }
    }

    // Opens a different page, changing just a Pane
    @FXML
    public void newPane(String pageName, Button btn, Pane pane, Label label) {
        System.out.println("Opening another page...");

        Parent newPane = loadFXMLFile(pageName + ".fxml");
        pane.getChildren().clear();
        pane.getChildren().add(newPane);
        label.setText(btn.getText());

        System.out.println("Another page opened...");
    }

    // Opens a different page, changing the AnchorPane
    @FXML
    public void newAnchorpane(String pageName, AnchorPane paneToReplace) {
        System.out.println("Opening another page(anchor)...");

        Parent newPane = loadFXMLFile(pageName + ".fxml");
        paneToReplace.getChildren().clear();
        paneToReplace.getChildren().add(newPane);

        System.out.println("Another page opened(anchor)...");
    }

    public Parent loadFXMLFile(String fxmlFileName) {
        try {
            return FXMLLoader.load(getClass().getResource(fxmlFileName));
        } catch (IOException ex) {
            System.out.println(ex.getClass().getName() + ": " + ex.getMessage());
            return null;
        }
    }

}
