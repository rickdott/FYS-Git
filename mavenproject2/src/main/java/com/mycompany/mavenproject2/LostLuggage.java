package com.mycompany.mavenproject2;

import java.util.ArrayList;

/**
 * LostLuggage class, extension of the luggage class
 * @author Rick den Otter 500749952 (51 lines)
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
    
    /**
     * Method to return info on a piece of luggage as an ArrayLiist
     * @return ArrayList<String> with information on lost luggage
     */
    @Override
    public ArrayList<String> getLuggageInfo() {
        ArrayList<String> list = new ArrayList<>();
        
        list.add(this.registrationnr);
        list.add(this.dateregistered);
        list.add(this.timeregistered);
        list.add(this.luggagetype);
        list.add(this.brand);
        list.add(this.flightnumber);
        list.add(this.luggagelabelnr);
        list.add(this.primarycolour);
        list.add(this.secondarycolour);
        list.add(this.size);
        list.add(this.weight);
        list.add(this.passenger_name_city);
        list.add(this.otherchar);
        
        return list;
    } 
}
