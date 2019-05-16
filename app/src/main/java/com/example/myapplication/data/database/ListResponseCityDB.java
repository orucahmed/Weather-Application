package com.example.myapplication.data.database;

import com.example.myapplication.data.weather.ListResponse;
import com.example.myapplication.data.weather.ListResponseCity;
import com.example.myapplication.data.weather.Main;
import com.example.myapplication.data.weather.Sys;
import com.example.myapplication.data.weather.Weather;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public class ListResponseCityDB {

    @Id(assignable = true)
    public long id;

    public long dt;
    public String name;
    public ToOne<Main> main;
    public ToOne<Sys> sys;

    @Backlink(to = "listResponseCityDB")
    public ToMany<Weather> weather;

    public ToOne<WeatherResponseCitiesDB> weatherResponseCitiesDB;

    public ListResponseCityDB() {
    }

    public ListResponseCityDB(ListResponseCity listResponseCity){
        this.id = listResponseCity.getId();
        this.dt = listResponseCity.getDt();
        this.name = listResponseCity.getName();
        this.main = new ToOne<>(listResponseCity.getMain(), ListResponseCityDB_.main);
        this.sys = new ToOne<>(listResponseCity.getSys(), ListResponseCityDB_.sys);

        this.main.setTarget(listResponseCity.getMain());
        this.sys.setTarget(listResponseCity.getSys());

        for(Weather w : weather){
            weather.remove(w);
        }

        for(Weather w : listResponseCity.getWeather()){
            weather.add(new Weather(w));
        }
    }

    public ListResponseCity getListResponseCity(){
        List<Weather> pomLiosta = new ArrayList<>();
        for(Weather w : weather) pomLiosta.add(w);
        return new ListResponseCity(dt,name,main.getTarget(),sys.getTarget(),id,pomLiosta);
    }


}
