package com.example.myapplication.data.weather;

public class Forecast {

    private String hour;
    private String icon;
    private float temp;

    public Forecast(String day, String icon, float temp) {
        this.hour = day;
        this.icon = icon;
        this.temp = temp;
    }

    public String getHour() {
        return hour;
    }

    public String getIcon() {
        return icon;
    }

    public float getTemp() {
        return temp;
    }
}
