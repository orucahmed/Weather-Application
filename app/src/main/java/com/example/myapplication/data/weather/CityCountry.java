package com.example.myapplication.data.weather;

public class CityCountry implements Comparable<CityCountry> {

    public String name;
    public String country;
    public long id;

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
