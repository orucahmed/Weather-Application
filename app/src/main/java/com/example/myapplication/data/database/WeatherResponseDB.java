package com.example.myapplication.data.database;

import com.example.myapplication.data.weather.Coord;
import com.example.myapplication.data.weather.Main;
import com.example.myapplication.data.weather.Sys;
import com.example.myapplication.data.weather.Weather;
import com.example.myapplication.data.weather.WeatherResponse;
import com.example.myapplication.data.weather.Wind;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

@Entity
public class WeatherResponseDB {

    @Id(assignable = true)
    public long id;
    public String name;
    public ToOne<Coord> coord;

    @Backlink(to = "weatherResponseDB")
    public ToMany<Weather> weather;

    public ToOne<Main> main;
    public float visibility;
    public ToOne<Wind> wind;
    public ToOne<Sys> sys;

    public WeatherResponseDB() {
    }

    public void populate(WeatherResponse weatherResponse) {
        this.id = weatherResponse.getId();
        this.name = weatherResponse.getName();
        this.coord = new ToOne<>(weatherResponse.getCoord(), WeatherResponseDB_.coord);
        this.main = new ToOne<>(weatherResponse.getMain(), WeatherResponseDB_.main);
        this.visibility = weatherResponse.getVisibility();
        this.wind = new ToOne<>(weatherResponse.getWind(), WeatherResponseDB_.wind);
        this.sys = new ToOne<>(weatherResponse.getSys(), WeatherResponseDB_.sys);

        this.coord.setTarget(weatherResponse.getCoord());
        this.main.setTarget(weatherResponse.getMain());
        this.wind.setTarget(weatherResponse.getWind());
        this.sys.setTarget(weatherResponse.getSys());

        for (Weather w : weather) {
            weather.remove(w);
        }

        for (Weather w : weatherResponse.getWeather()) {
            weather.add(new Weather(w));
        }

    }


    public WeatherResponse getWeatherResponse() {
        List<Weather> pomLiosta = new ArrayList<>();
        for (Weather w : weather) pomLiosta.add(w);
        return new WeatherResponse(id, name, coord.getTarget(), pomLiosta, main.getTarget(), visibility, wind.getTarget(), sys.getTarget());
    }
}
