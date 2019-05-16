package com.example.myapplication.data.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponseDays {

    @SerializedName("list")
    List<ListDaily> listdaily;

    private City city;

    public WeatherResponseDays(List<ListDaily> listdaily) {
        this.listdaily = listdaily;
    }

    public City getCity() {
        return city;
    }

    public List<ListDaily> getListdaily() {
        return listdaily;
    }
}
