package com.mycompany.mavenproject2.connection;


import java.sql.*;
import javax.swing.*;

/**
 * With this class I connected to the database which we are using for this project.
 * 
 * @author Matthijs Snijders (studentnummer)
 * 
 */
public class sqlDatabaseConnection {

    Connection conn = null;

    public static Connection connectdb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://svwdesigners.nl:3306/stanviw199_fys", "stanviw199_fys", "fysbagger");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
