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

    public WeatherResponse(long id, String name, Coord coord, List<Weather> weather, Main main, float visibility, Wind wind, Sys sys) {
        this.id = id;
        this.name = name;
        this.coord = coord;
        this.weather = weather;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.sys = sys;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public float getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Sys getSys() {
        return sys;
    }
}
