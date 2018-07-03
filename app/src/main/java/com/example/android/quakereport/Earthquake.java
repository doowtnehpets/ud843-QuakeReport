package com.example.android.quakereport;

public class Earthquake {

    private String magnitude;
    private String place;
    private String date;


    /**
     * Constructs a new {@link Earthquake} object
     * @param magnitude is the magnitude of the earthquake
     * @param place is the place of the earthquake
     * @param date is the date the earthquake happened
     */
    public Earthquake(String magnitude, String place, String date) {
        this.magnitude = magnitude;
        this.place = place;
        this.date = date;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }
}
