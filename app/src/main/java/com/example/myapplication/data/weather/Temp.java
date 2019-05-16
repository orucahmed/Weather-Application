package com.example.myapplication.data.weather;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Temp {

    @Id
    public long id;

    private float min;
    private float max;

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }


}
