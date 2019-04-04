package com.example.myapplication.data.weather;

import java.util.List;


public class ListResponse {
    private long dt;
    private Main main;
    private List<Weather> weather;
    private Clouds clouds;
    private Wind wind;
    private Rain rain;
    private Snow snow;
    private String dt_txt;


    public long getDt() {
        return dt;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public Rain getRain() {
        return rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public String getDt_txt() {
        return dt_txt;
    }
}
