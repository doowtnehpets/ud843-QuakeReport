package com.example.android.quakereport;

public class Earthquake {

    private double magnitude;
    private String place;
    private long timeInMilliseconds;
    private String url;

    /**
     * Constructs a new {@link Earthquake} object
     * @param magnitude is the magnitude of the earthquake
     * @param place is the place of the earthquake
     * @param timeInMilliseconds is the date the earthquake happened
     * @param url is the URL for the earthquake
     */
    public Earthquake(double magnitude, String place, long timeInMilliseconds, String url) {
        this.magnitude = magnitude;
        this.place = place;
        this.timeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public String getUrl() {
        return url;
    }
}
