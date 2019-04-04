package com.example.myapplication.data.weather;

public class ListResponseCity {
    private long dt;
    private String name;
    private Main main;
    private Sys sys;
    private long id;

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
