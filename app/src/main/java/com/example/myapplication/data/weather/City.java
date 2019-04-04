package com.example.myapplication.data.weather;

public class City {
    private long id;
    private String name;
    private Coord coord;
    private String country;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }
}
