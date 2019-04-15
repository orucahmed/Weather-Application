package com.example.myapplication.presentation.weather;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;

public class WeatherPagePresenter {

    private final SharedPreferences prefCities;
    private final SharedPreferences prefPosition;
    private final SharedPreferences prefLastCIty;

    public interface WeatherPageView {
        void setPosition(int position);

        void setCityList(List<String> listCity);
    }

    @Inject
    public WeatherPagePresenter(@Named("cities") SharedPreferences prefCities, @Named("position") SharedPreferences prefPosition, @Named("lastCity") SharedPreferences prefLastCIty) {
        this.prefCities = prefCities;
        this.prefPosition = prefPosition;
        this.prefLastCIty = prefLastCIty;
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
        Set<String> cities = new TreeSet<>(prefCities.getStringSet("cities", new HashSet<String>()));
        List<String> listCity = new ArrayList<>();
        for (String s : cities) listCity.add(s.split(":")[0]);
        NUM_PAGES = listCity.size();
        view.setCityList(listCity);
    }

    public void setPrefPosition(int position) {
        SharedPreferences.Editor editor = prefPosition.edit();
        editor.putInt("pos", position);
        editor.commit();
        SharedPreferences.Editor editor1 = prefLastCIty.edit();
        Set<String> cities = new TreeSet<>(prefCities.getStringSet("cities", new HashSet<>()));

        List<String> listCity = new ArrayList<>();
        for (String s : cities) listCity.add(s);
        int counter = 0;
        for (int i = 0; i < listCity.size(); i++) {
            if (counter == position) {
                editor1.putString("posLastCity", listCity.get(i));
                break;
            }
            counter++;
        }
        editor1.commit();

    }
}
