package com.example.myapplication.data.weather;

import com.google.gson.annotations.SerializedName;

public class Rain {

    @SerializedName("3h")
    private float threeHours;
    @SerializedName("1h")
    private float oneHour;

    public float getThreeHours() {
        return threeHours;
    }

    public float getOneHour() {
        return oneHour;
    }
}
