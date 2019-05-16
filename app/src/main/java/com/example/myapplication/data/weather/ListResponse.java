package com.example.myapplication.data.weather;

import java.util.List;


public class ListResponse {

    private Main main;
    private List<Weather> weather;
    private String dt_txt;

    public ListResponse(Main main, List<Weather> weather, String dt_txt) {
        this.main = main;
        this.weather = weather;
        this.dt_txt = dt_txt;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getDt_txt() {
        return dt_txt;
    }
}
