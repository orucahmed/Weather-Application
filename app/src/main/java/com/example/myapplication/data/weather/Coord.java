package com.example.myapplication.data.weather;

import android.location.Location;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Coord {

    @Id
    public long id;

    private double lon;
    private double lat;

    public Coord() {
    }

    public Coord(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }








}
