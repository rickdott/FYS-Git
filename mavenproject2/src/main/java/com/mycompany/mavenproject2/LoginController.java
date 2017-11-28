/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Staick
 */
public class LoginController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private AnchorPane paneLogin;
    private ResourceBundle bundle;
    private Locale locale;

    FXMLDocumentController controller = new FXMLDocumentController();

    // Methods for changing the language
    @FXML
    private void setLanguageEnglish() {
        System.out.println("Set language to English");
        loadLanguage("en", "EN");
    }

    @FXML
    private void setLanguageDutch() {
        System.out.println("Set language to Dutch");
        loadLanguage("nl", "NL");
    }

    @FXML
    private void setLanguageGerman() {
        System.out.println("Set language to German");
        //loadLanguage("");
    }

    @FXML
    private void setLanguagePortuguese() {
        System.out.println("Set language to Portuguese");
        //loadLanguage("");
    }

    @FXML
    private void setLanguageTurkish() {
        System.out.println("Set language to Turkish");
        //loadLanguage("");
    }

    // Main method for changing languages WERKT NIET (PATH IN .getBundle() kan niet worden gevonden)
    private void loadLanguage(String language, String lang) {

        Locale.setDefault(new Locale(language, lang));
        bundle = ResourceBundle.getBundle("lang");
        System.out.println("GVD WERK EENS! : " + bundle.getString("language"));
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
        row = reader.getNextRow(); // Eerste row
        Database db = new Database();
        while (row != null) {

            String mail = row.get(row.size() - 1);
            System.out.println(mail);

            // Opvragen van de idpassenger die hij zoekt via de mail van de passengier
            String sql = String.format("SELECT idpassenger FROM Passenger WHERE email = '%s'", mail);
            String idpassenger = db.executeStringListQuery(sql);

            // SQL query die alles invoert in de database
            sql = String.format("INSERT INTO `Bagage`(`labelnumber`, `flightnumber`, `destination`, `type`, `brand`, `colour`, `specialchar`, `passengerid`, `foundat`, `foundatdate`, `date`)VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", row.get(0), row.get(1), row.get(2), row.get(3), row.get(4), row.get(5), row.get(6), idpassenger, row.get(7), row.get(8), row.get(9));
            db.executeUpdateQuery(sql);

            // Pakt alvast de volgende row
            row = reader.getNextRow();
        }

        System.out.println("Excel import complete...");
    }

    @FXML
    private void openCustomerHomescreen(ActionEvent event) {
        controller.newAnchorpane("CustomerHomescreen", paneLogin);
    }

    @FXML
    private void openWorkerHomescreen(ActionEvent event) {
        controller.newAnchorpane("WorkerHomescreen", paneLogin);
    }

}
