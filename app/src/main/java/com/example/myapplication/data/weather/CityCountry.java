package com.example.myapplication.data.weather;

public class CityCountry implements Comparable<CityCountry> {

    private String name;
    private String country;
    private long id;

    public CityCountry(String name, String country, long id) {
        this.name = name;
        this.country = country;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }


    @Override
    public int compareTo(CityCountry o) {
        return name.compareTo(o.name);
    }
}
