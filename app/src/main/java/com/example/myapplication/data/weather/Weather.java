package com.example.myapplication.data.weather;

public class Weather {

    private String main;
    private String icon;
    private long id;
    private String description;

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getMain() {
        return main;
    }


    public String getIcon() {
        return icon;
    }
}
