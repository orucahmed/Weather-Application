package com.example.myapplication.data.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponseDays {
    @SerializedName("list")
    List<ListDaily> listdaily;




    public List<ListDaily> getListdaily() {
        return listdaily;
    }
}
