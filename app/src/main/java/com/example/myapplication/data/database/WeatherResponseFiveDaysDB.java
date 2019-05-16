package com.example.myapplication.data.database;

import com.example.myapplication.data.weather.ListDaily;
import com.example.myapplication.data.weather.ListResponse;
import com.example.myapplication.data.weather.WeatherResponse;
import com.example.myapplication.data.weather.WeatherResponseDays;
import com.example.myapplication.data.weather.WeatherResponseFiveDays;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;

@Entity
public class WeatherResponseFiveDaysDB {
    @Id(assignable = true)
    public long id;

    @Backlink(to = "weatherResponseFiveDaysDB")
    public ToMany<ListResponseDB> listResponseDB;

    public WeatherResponseFiveDaysDB() {
    }

    public void populate(WeatherResponseFiveDays weatherResponseFiveDays){
        this.id=weatherResponseFiveDays.getCity().getId();
        for (ListResponseDB temp : listResponseDB) {
            listResponseDB.remove(temp);
        }

        for(ListResponse listResponse : weatherResponseFiveDays.getListResponse()){
            listResponseDB.add(new ListResponseDB(listResponse));
        }
    }

    public WeatherResponseFiveDays getWeatherResponseFiveDays(){
        List<ListResponse> listDaily = new ArrayList<>();
        for(ListResponseDB  temp : listResponseDB){
            listDaily.add(temp.getListResponse());
        }
        return new WeatherResponseFiveDays(listDaily);
    }
}
