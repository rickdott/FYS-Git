/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.util.ArrayList;

/**
 *
 * @author Rick
 */
public abstract class Luggage {

    protected String registrationnr;
    //private String datefound, timefound;
    protected String luggagetype, brand;
    protected String flightnumber, luggagelabelnr;
    //private String locationfound;
    protected String primarycolour, secondarycolour;
    protected String size, weight;
    protected String passenger_name_city;
    protected String otherchar;
    protected int idpassenger;

    public Luggage() {

    }

    public String getRegistrationnr() {
        return registrationnr;
    }

    public void setRegistrationnr(String registrationnr) {
        this.registrationnr = registrationnr;
    }

    public String getLuggagetype() {
        return luggagetype;
    }

    public void setLuggagetype(String luggagetype) {
        this.luggagetype = luggagetype;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public String getLuggagelabelnr() {
        return luggagelabelnr;
    }

    public void setLuggagelabelnr(String luggagelabelnr) {
        this.luggagelabelnr = luggagelabelnr;
    }

    public String getPrimarycolour() {
        return primarycolour;
    }

    public void setPrimarycolour(String primarycolour) {
        this.primarycolour = primarycolour;
    }

    public String getSecondarycolour() {
        return secondarycolour;
    }

    public void setSecondarycolour(String secondarycolour) {
        this.secondarycolour = secondarycolour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPassenger_name_city() {
        return passenger_name_city;
    }

    public void setPassenger_name_city(String passenger_name_city) {
        this.passenger_name_city = passenger_name_city;
    }

    public String getOtherchar() {
        return otherchar;
    }

    public void setOtherchar(String otherchar) {
        this.otherchar = otherchar;
    }

    public int getIdpassenger() {
        return idpassenger;
    }

    public void setIdpassenger(int idpassenger) {
        this.idpassenger = idpassenger;
    }

    public abstract ArrayList<String> getLuggageInfo();

}
