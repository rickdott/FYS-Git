/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Rick
 */
public class LoginController implements Initializable{
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private AnchorPane paneLogin;
    
    FXMLDocumentController controller = new FXMLDocumentController();
    
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
        System.out.printf("Number of sheets: %s\n", reader.getNumberOfSheets());
        System.out.printf("getNextRow(): %s\n", reader.getNextRow());
        
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
