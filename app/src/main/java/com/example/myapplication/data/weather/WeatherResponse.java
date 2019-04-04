package com.example.myapplication.data.weather;


import java.util.List;

public class WeatherResponse {
    private long id;
    private String name;
    private Coord coord;
    private List<Weather> weather;
    private Main main;
    private float visibility;
    private Wind wind;
    private Rain rain;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private long cod;
    private String base;
    private Snow snow;


    public Sys getSys() {
        return sys;
    }

    public Snow getSnow() {
        return snow;
    }

    public String getBase() {
        return base;
    }

    public long getCod() {
        return cod;
    }

    public long getDt() {
        return dt;
    }

    public Rain getRain() {
        return rain;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public float getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
