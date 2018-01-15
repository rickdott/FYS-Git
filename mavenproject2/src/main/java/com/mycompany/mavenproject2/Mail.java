package com.mycompany.mavenproject2;

import java.util.Properties;
import javax.activation.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Mail class, to facilitate e-mailing the customer their 
 * property irregularity report
 * @author Stijn Klopper 500770512 (85 lines)
 */
public class Mail {

    private String emailAdress;

    public Mail(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    /**
     * Method to send an e-mail to the customer
     */
    public void mailsturen() {
        //username and password of the senders mail account
        final String username = "baggerfys@gmail.com";
        final String password = "pindakaas";
        
        //properties of SMTP email server used
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            //From, To and Subject of the mail.
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("baggerfys@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(this.emailAdress));
            message.setSubject("Corendon PDF Formulier Email bericht");
            
            // declare the message and attachment and the mail
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            MimeBodyPart attachBodyPart = new MimeBodyPart();

            //text part of the mail
            messageBodyPart.setText("Gegroet beste klant, hierbij ontvangt u een kopie van het ingevulde verloren bagage formulier");

            //adding the PDF attachment to the mail
            String file = "src\\main\\resources\\temp\\PDFTest.pdf";
            String fileName = "PDFTest.pdf";
            DataSource source = new FileDataSource(file);
            attachBodyPart.setDataHandler(new DataHandler(source));
            attachBodyPart.setFileName(fileName);

            //adding the message and the attachment to the body of the mail
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachBodyPart);

            //sending the message
            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Email send");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}