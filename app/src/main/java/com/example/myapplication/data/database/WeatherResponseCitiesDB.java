package com.example.myapplication.data.database;

import com.example.myapplication.data.weather.ListResponseCity;
import com.example.myapplication.data.weather.Weather;
import com.example.myapplication.data.weather.WeatherResponseCities;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class WeatherResponseCitiesDB {
    @Id
    public long id;

    @Backlink(to = "weatherResponseCitiesDB")
    ToMany<ListResponseCityDB> listResponseCityDB;


    public WeatherResponseCitiesDB() {
    }

    public void populate(WeatherResponseCities weatherResponseCities) {
        for (ListResponseCityDB temp : listResponseCityDB) {
            listResponseCityDB.remove(temp);
        }

        for(ListResponseCity listResponseCity : weatherResponseCities.getListResponse()){
            listResponseCityDB.add(new ListResponseCityDB(listResponseCity));
        }

    }

    public WeatherResponseCities getWeatherResponseCities(){
        List<ListResponseCity> listResponseCities = new ArrayList<>();
        for(ListResponseCityDB  temp : listResponseCityDB){
            listResponseCities.add(temp.getListResponseCity());
        }
        return new WeatherResponseCities(listResponseCities);
    }



}
