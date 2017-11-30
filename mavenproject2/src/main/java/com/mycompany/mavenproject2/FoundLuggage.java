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
public class FoundLuggage {
    private int bagageid;
    private String labelnumber;
    private String flightnumber;
    private String destination;
    private String type;
    private String brand;
    private String colour;
    private String specialchar;
    private String foundat;
    private String foundatdate;
    private String date;
    
    FoundLuggage() {
        int test = 100000 + (int)(Math.random() * (999999 - 100000));
        this.bagageid = test;
    }

    public int getBagageid() {
        return bagageid;
    }

    public void setBagageid(int bagageid) {
        this.bagageid = bagageid;
    }

    public String getLabelnumber() {
        return labelnumber;
    }

    public void setLabelnumber(String labelnumber) {
        this.labelnumber = labelnumber;
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSpecialchar() {
        return specialchar;
    }

    public void setSpecialchar(String specialchar) {
        this.specialchar = specialchar;
    }

    public String getFoundat() {
        return foundat;
    }

    public void setFoundat(String foundat) {
        this.foundat = foundat;
    }

    public String getFoundatdate() {
        return foundatdate;
    }

    public void setFoundatdate(String foundatdate) {
        this.foundatdate = foundatdate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
