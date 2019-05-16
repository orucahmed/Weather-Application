package com.example.myapplication.data.database;


import com.example.myapplication.data.weather.ListDaily;
import com.example.myapplication.data.weather.ListResponseCity;
import com.example.myapplication.data.weather.WeatherResponseCities;
import com.example.myapplication.data.weather.WeatherResponseDays;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class WeatherResponseDaysDB {

    @Id(assignable = true)
    public long id;

    @Backlink(to = "weatherResponseDaysDB")
    public ToMany<ListDailyDB> listDailyDB;

    public WeatherResponseDaysDB() {
    }

    public void populate(WeatherResponseDays weatherResponseDays) {
        this.id=weatherResponseDays.getCity().getId();
        for (ListDailyDB temp : listDailyDB) {
            listDailyDB.remove(temp);
        }

        for(ListDaily listDaily : weatherResponseDays.getListdaily()){
            listDailyDB.add(new ListDailyDB(listDaily));
        }

    }

    public WeatherResponseDays getWeatherResponseDays(){
        List<ListDaily> listDaily = new ArrayList<>();
        for(ListDailyDB  temp : listDailyDB){
            listDaily.add(temp.getListDaily());
        }
        return new WeatherResponseDays(listDaily);
    }
}
