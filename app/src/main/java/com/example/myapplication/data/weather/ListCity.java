package com.example.myapplication.data.weather;

public class ListCity {

    private long dt;
    private String name;
    private float temp;
    private String country;
    private long id;



    public ListCity(long dt, String name, float temp, String country, long id) {
        this.dt = dt;
        this.name = name;
        this.temp = temp;
        this.country = country;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getDt() {
        return dt;
    }

    public String getName() {
        return name;
    }

    public float getTemp() {
        return temp;
    }

    public String getCountry() {
        return country;
    }
}
