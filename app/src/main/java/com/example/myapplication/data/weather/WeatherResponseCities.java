package com.example.myapplication.data.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponseCities {

    @SerializedName("list")
    List<ListResponseCity> listResponse;

    public WeatherResponseCities(List<ListResponseCity> listResponse) {
        this.listResponse = listResponse;
    }

    public List<ListResponseCity> getListResponse() {
        return listResponse;
    }

}
