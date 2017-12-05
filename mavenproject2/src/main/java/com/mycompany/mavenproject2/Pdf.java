package com.mycompany.mavenproject2;

import java.awt.Color;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author Rick pfff opschepper en coole stijn
 */
public class Pdf {

    
    
    
    

    final float LEADING = 20.0f;
    final int FONT_SIZE_BIG = 20, FONT_SIZE_SMALL = 14;

    // Voor later: voeg data uit database toe door + "methode die string 
    //returnt" te doen, of + string
    // Geef deze methode een object mee met alle beschikbare data uit de database
    // Creates a new PDF document, fills it with the missing-luggage form
    public void printPDF(String firstname, String lastname, String adress,
            String city, String zip, String country, String phone, String email)
            {

        try (PDDocument document = new PDDocument()) {
          
            PDPage blank = new PDPage();
            document.addPage(blank);
            PDPage page = document.getPage(0);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDImageXObject pdImage = PDImageXObject.createFromFile("corendonLogo.jpg", document);
            contentStream.drawImage(pdImage, 150, 650);
            Color corendonColor = new Color(216, 30, 5);
            contentStream.setStrokingColor(corendonColor);
            contentStream.addRect(50, 50, 500, 700);
            contentStream.addRect(50, 50, 500, 325);
            contentStream.addRect(50, 50, 500, 506);
            contentStream.stroke();

            contentStream.beginText();
            contentStream.newLineAtOffset(55, 630);
            contentStream.setLeading(LEADING);

            contentStream.setFont(PDType1Font.HELVETICA, FONT_SIZE_BIG);
            contentStream.showText("General");
            contentStream.setFont(PDType1Font.HELVETICA, FONT_SIZE_SMALL);
            contentStream.newLine();
            contentStream.showText("Date: ");
            contentStream.newLine();
            contentStream.showText("Time: ");
            contentStream.newLine();
            contentStream.showText("Airport:");

            contentStream.newLineAtOffset(0, -(LEADING * 2));
            contentStream.setFont(PDType1Font.HELVETICA, FONT_SIZE_BIG);
            contentStream.showText("Traveller");

            contentStream.setFont(PDType1Font.HELVETICA, FONT_SIZE_SMALL);
            contentStream.newLine();
            contentStream.showText("Name: ");
            contentStream.showText(firstname + " " + lastname);
            contentStream.newLine();
            contentStream.showText("Address: ");
            contentStream.showText(adress);
            contentStream.newLine();
            contentStream.showText("City: ");
            contentStream.showText(city);
            contentStream.newLine();
            contentStream.showText("Postal code:");
            contentStream.showText(zip);
            contentStream.newLine();
            contentStream.showText("Country: ");
            contentStream.showText(country);
            contentStream.newLine();
            contentStream.showText("Phone: ");
            contentStream.showText(phone);
            contentStream.newLine();
            contentStream.showText("E-mail: ");
            contentStream.showText(email);
             
            contentStream.newLineAtOffset(0, -(LEADING * 2));
            contentStream.setFont(PDType1Font.HELVETICA, FONT_SIZE_BIG);
            contentStream.showText("Luggage label");

            contentStream.setFont(PDType1Font.HELVETICA, FONT_SIZE_SMALL);
            contentStream.newLine();
            contentStream.showText("Label Number:");
            contentStream.newLine();
            contentStream.showText("Flight Number:");
            contentStream.newLine();
            contentStream.showText("Destination:");

            contentStream.newLineAtOffset(0, -(LEADING * 2));
            contentStream.setFont(PDType1Font.HELVETICA, FONT_SIZE_BIG);
            contentStream.showText("Luggage information");

            contentStream.setFont(PDType1Font.HELVETICA, FONT_SIZE_SMALL);
            contentStream.newLine();
            contentStream.showText("Type:");
            contentStream.newLine();
            contentStream.showText("Brand:");
            contentStream.newLine();
            contentStream.showText("Colour:");
            contentStream.newLine();
            contentStream.showText("Special Characteristics:");

            contentStream.endText();
            contentStream.close();

            document.save("src\\main\\resources\\temp\\PDFTest.pdf");
            document.close();
        } catch (IOException e) {
            System.out.println(e);
        
        }
        System.out.println("PDF created!");

    }
}