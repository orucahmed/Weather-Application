package com.example.myapplication.data.weather;

public class Sys {


    private long type;
    private long id;
    private String message;
    private String country;
    private long sunrise;
    private long sunset;
    private String pod;

    public String getPod() {
        return pod;
    }

    public long getType() {
        return type;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }
}
