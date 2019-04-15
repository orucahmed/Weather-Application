package com.example.myapplication.data.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.objectbox.annotation.Entity;


public class WeatherResponseFiveDays {



    @SerializedName("list")
    private List<ListResponse> listResponse;



    public List<ListResponse> getListResponse() {
        return listResponse;
    }





}
