package com.example.myapplication.data.weather;

import java.util.List;

public class ListResponseCity {
    private long dt;
    private String name;
    private Main main;
    private Sys sys;
    private long id;
    private List<Weather> weather;

    public ListResponseCity(long dt, String name, Main main, Sys sys, long id, List<Weather> weather) {
        this.dt = dt;
        this.name = name;
        this.main = main;
        this.sys = sys;
        this.id = id;
        this.weather = weather;
    }

    public Sys getSys() {
        return sys;
    }

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

}
