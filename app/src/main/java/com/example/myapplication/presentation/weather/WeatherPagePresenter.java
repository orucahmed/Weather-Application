package com.example.myapplication.presentation.weather;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

public class WeatherPagePresenter {

    private final SharedPreferences prefCities;
    private final SharedPreferences prefPosition;

    public interface WeatherPageView {
        void setPosition(int position);

        void setCityList(List<String> listCity);
    }

    @Inject
    public WeatherPagePresenter(@Named("cities") SharedPreferences prefCities, @Named("position") SharedPreferences prefPosition) {
        this.prefCities = prefCities;
        this.prefPosition = prefPosition;
    }

    private WeatherPageView view;
    public static int NUM_PAGES;

    public void onCreate(WeatherPageView view) {
        this.view = view;
    }

    public void getPosition(int position) {
        if (position == -1) position = prefPosition.getInt("pos", 0);
        view.setPosition(position);
    }

    public void getCityList() {
        Set<String> cities = new HashSet<>();
        List<String> listCity = new ArrayList<>();
        cities.addAll(prefCities.getStringSet("cities", new HashSet<String>()));
        for (String s : cities) listCity.add(s);
        NUM_PAGES = listCity.size();
        view.setCityList(listCity);
    }

    public void setPrefPosition(int position){
        SharedPreferences.Editor editor = prefPosition.edit();
        editor.putInt("pos", position);
        editor.commit();
    }
}
