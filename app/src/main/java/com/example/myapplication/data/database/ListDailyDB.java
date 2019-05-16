package com.example.myapplication.data.database;

import com.example.myapplication.data.weather.City;
import com.example.myapplication.data.weather.ListDaily;
import com.example.myapplication.data.weather.ListResponseCity;
import com.example.myapplication.data.weather.Temp;
import com.example.myapplication.data.weather.Weather;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public class ListDailyDB {

    @Id
    public long id;

    public ToOne<Temp> temp;
    public long dt;

    @Backlink(to="listDailyDB")
    public ToMany<Weather> weather;

    public ToOne<WeatherResponseDaysDB> weatherResponseDaysDB;

    public ListDailyDB() {
    }

    public ListDailyDB(ListDaily listDaily) {
        this.dt=listDaily.getDt();
        this.temp = new ToOne<>(listDaily.getTemp(), ListDailyDB_.temp);
        this.temp.setTarget(listDaily.getTemp());

        for(Weather w : weather){
            weather.remove(w);
        }

        for(Weather w : listDaily.getWeather()){
            weather.add(new Weather(w));
        }


    }

    public ListDaily getListDaily(){
        List<Weather> pomLiosta = new ArrayList<>();
        for(Weather w : weather) pomLiosta.add(w);
        return new ListDaily(temp.getTarget(),dt,pomLiosta);
    }




}
