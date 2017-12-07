/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author Rick
 */
public class LostLuggage extends Luggage{
    
    private String dateregistered;
    private String timeregistered;

    public String getDateregistered() {
        return dateregistered;
    }

    public void setDateregistered(String dateregistered) {
        this.dateregistered = dateregistered;
    }

    public String getTimeregistered() {
        return timeregistered;
    }

    public void setTimeregistered(String timeregistered) {
        this.timeregistered = timeregistered;
    }
    
    
}
