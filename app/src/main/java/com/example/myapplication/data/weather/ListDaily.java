package com.example.myapplication.data.weather;

import java.util.List;

public class ListDaily {
    private Temp temp;
    private long dt;
    private List<Weather> weather;


    public List<Weather> getWeather() {
        return weather;
    }

    public long getDt() {
        return dt;
    }

    public Temp getTemp() {
        return temp;
    }


}
