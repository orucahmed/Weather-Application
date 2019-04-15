package com.example.myapplication.data.weather;

import java.util.List;

public class ListResponseCity {
    private long dt;
    private String name;
    private Main main;
    private Sys sys;
    private long id;
    private List<Weather> weather;

    public List<Weather> getWeather() {
        return weather;
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

    public Main getMain() {
        return main;
    }

    public Sys getSys() {
        return sys;
    }
}
