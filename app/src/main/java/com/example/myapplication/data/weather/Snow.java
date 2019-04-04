package com.example.myapplication.data.weather;

import com.google.gson.annotations.SerializedName;

public class Snow {

    @SerializedName("1h")
    private float oneHour;
    @SerializedName("3h")
    private float threeHours;


    public float getOneHour() {
        return oneHour;
    }

    public float getThreeHours() {
        return threeHours;
    }
}
