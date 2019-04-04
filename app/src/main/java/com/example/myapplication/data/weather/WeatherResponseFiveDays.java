package com.example.myapplication.data.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponseFiveDays {

    private long code;
    private String message;
    private City city;
    private long cnt;
    @SerializedName("list")
    private List<ListResponse> listResponse;



    public List<ListResponse> getListResponse() {
        return listResponse;
    }

    public City getCity() {
        return city;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public long getCnt() {
        return cnt;
    }
}
