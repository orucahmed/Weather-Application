package com.example.myapplication.data.weather;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Main {

    @Id
    public long id;

    private float temp;
    private float pressure;
    private float humidity;
    private float temp_min;
    private float temp_max;

    public Main() {
    }

    public Main(float temp, float pressure, float humidity, float temp_min, float temp_max) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public float getTemp() {
        return temp;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }


}
