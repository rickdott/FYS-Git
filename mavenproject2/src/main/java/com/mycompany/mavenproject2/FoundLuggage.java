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
public class FoundLuggage extends Luggage {

    private String datefound, timefound;
    private String locationfound;

    public String getDatefound() {
        return datefound;
    }

    public void setDatefound(String datefound) {
        this.datefound = datefound;
    }

    public String getTimefound() {
        return timefound;
    }

    public void setTimefound(String timefound) {
        this.timefound = timefound;
    }

    public String getLocationfound() {
        return locationfound;
    }

    public void setLocationfound(String locationfound) {
        this.locationfound = locationfound;
    }
    
    @Override
    public ArrayList<String> getLuggageInfo() {
        ArrayList<String> list = new ArrayList<>();
        
        list.add(this.registrationnr);
        list.add(this.datefound);
        list.add(this.timefound);
        list.add(this.luggagetype);
        list.add(this.brand);
        list.add(this.flightnumber);
        list.add(this.luggagelabelnr);
        list.add(this.locationfound);
        list.add(this.primarycolour);
        list.add(this.secondarycolour);
        list.add(this.size);
        list.add(this.weight);
        list.add(this.passenger_name_city);
        list.add(this.otherchar);
        
        return list;
    }
    
}
