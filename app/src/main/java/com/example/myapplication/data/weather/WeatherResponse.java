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
    private Sys sys;


    public Sys getSys() {
        return sys;
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
