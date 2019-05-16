package com.example.myapplication.data.database;

import com.example.myapplication.data.weather.ListResponse;
import com.example.myapplication.data.weather.ListResponseCity;
import com.example.myapplication.data.weather.Main;
import com.example.myapplication.data.weather.Weather;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public class ListResponseDB {

    @Id
    public long id;

    public ToOne<Main> main;
    public String dt_txt;

    @Backlink(to = "listResponseDB")
    public ToMany<Weather> weather;

    public ToOne<WeatherResponseFiveDaysDB> weatherResponseFiveDaysDB;

    public ListResponseDB() {
    }

    public ListResponseDB(ListResponse listResponse) {

        dt_txt=listResponse.getDt_txt();

        this.main = new ToOne<>(listResponse.getMain(), ListResponseDB_.main);
        this.main.setTarget(listResponse.getMain());


        for(Weather w : weather){
            weather.remove(w);
        }

        for(Weather w : listResponse.getWeather()){
            weather.add(new Weather(w));
        }
    }

    public ListResponse getListResponse(){
        List<Weather> pomLiosta = new ArrayList<>();
        for(Weather w : weather) pomLiosta.add(w);
        return new ListResponse(main.getTarget(),pomLiosta,dt_txt);
    }
}
