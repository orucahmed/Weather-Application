package com.example.myapplication.data.weather;

public class ForecastDaily {
    private String day;
    private String icon;
    private float tempMax;
    private float tempMin;

    public ForecastDaily(String day, String icon, float tempMax, float tempMin) {
        this.day = day;
        this.icon = icon;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public String getDay() {
        return day;
    }

    public String getIcon() {
        return icon;
    }

    public float getTempMax() {
        return tempMax;
    }

    public float getTempMin() {
        return tempMin;
    }
}
