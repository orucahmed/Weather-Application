package com.example.myapplication.data.weather;

import com.google.gson.annotations.SerializedName;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Sys {


    @Id
    public long idSys;

    private String country;
    private long sunrise;
    private long sunset;

    public Sys() {
    }

    public Sys(String country, long sunrise, long sunset) {
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getCountry() {
        return country;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }


}
