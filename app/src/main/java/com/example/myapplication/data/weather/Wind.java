package com.example.myapplication.data.weather;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Wind {

    @Id
    public long id;

    private float speed;

    public Wind() {
    }

    public Wind(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }






}
