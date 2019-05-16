package com.example.myapplication.data.weather;

import com.example.myapplication.data.database.ListDailyDB;
import com.example.myapplication.data.database.ListResponseCityDB;
import com.example.myapplication.data.database.ListResponseDB;
import com.example.myapplication.data.database.WeatherResponseDB;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public class Weather {

    @Id
    public long idWeather;

    private String icon;
    private String description;

    public ToOne<WeatherResponseDB> weatherResponseDB;
    public ToOne<ListResponseCityDB> listResponseCityDB;
    public ToOne<ListDailyDB> listDailyDB;
    public ToOne<ListResponseDB> listResponseDB;

    public Weather() {
    }

    public Weather(Weather w){
        this.icon=w.getIcon();
        this.description=w.getDescription();
    }


    public Weather(String icon, String description) {
        this.icon = icon;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}
