package com.example.myapplication.data.viewModel;

import com.example.myapplication.data.weather.ListDaily;
import com.example.myapplication.data.weather.ListResponse;
import com.example.myapplication.data.weather.ListResponseCity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherViewModel {


    private long id;

    private String name;

    private double lon;
    private double lat;


    private String main;
    private String icon;
    private long weatherId;
    private String description;


    private float temp;
    private float pressure;
    private float humidity;
    private float temp_min;
    private float temp_max;

    private float visibility;

    private float speed;

    private long sysId;
    private String country;
    private long sunrise;
    private long sunset;


    @SerializedName("list")
    List<ListResponseCity> listResponseCity;

    //


    @SerializedName("list")
    List<ListResponse> listResponse;

    //


    @SerializedName("list")
    List<ListDaily> listdaily;



}
