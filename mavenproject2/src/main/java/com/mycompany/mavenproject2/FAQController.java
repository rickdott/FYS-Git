package com.mycompany.mavenproject2;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Stan van Weringh
 */
public class FAQController {
    
    @FXML
    private AnchorPane paneFAQ;
    
    Utilities utilities = new Utilities();
    
    @FXML
    private void backToHomescreen() {
        utilities.newAnchorpane("CustomerHomescreen", paneFAQ);
    }
}
