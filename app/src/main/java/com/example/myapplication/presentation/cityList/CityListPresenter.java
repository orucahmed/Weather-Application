package com.example.myapplication.presentation.cityList;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.myapplication.data.weather.ListCity;
import com.example.myapplication.data.weather.WeatherResponseCities;
import com.example.myapplication.data.weather.WeatherService;
import com.example.myapplication.di.DataModule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CityListPresenter {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final SharedPreferences prefCities;
    private final WeatherService weatherService;

    public interface CityListView {
        void setCityList(List<ListCity> listCities);
    }

    private CityListView view;

    public void onCreate(CityListView view) {
        this.view = view;
    }

    private String getQuery() {
        Set<String> cities = new TreeSet<>(prefCities.getStringSet("cities", new HashSet<String>()));
        TreeSet<String> citiesTrimmed = new TreeSet<>();
        for(String s : cities) citiesTrimmed.add(s.split(":")[0]);
        return TextUtils.join(",",citiesTrimmed );
    }

    @Inject
    public CityListPresenter(@Named("cities") SharedPreferences prefCities, WeatherService weatherService) {
        this.prefCities = prefCities;
        this.weatherService = weatherService;

    }

    public void getWeatherCities() {

        compositeDisposable.add(weatherService.getWeatherCities(getQuery(), DataModule.API_KEY, "metric").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<WeatherResponseCities>() {
            @Override
            public void accept(WeatherResponseCities weatherResponseCities) throws Exception {
                List<ListCity> listCity = new ArrayList<>();
                for (int i = 0; i < weatherResponseCities.getListResponse().size(); i++) {
                    listCity.add(new ListCity(weatherResponseCities.getListResponse().get(i).getDt(), weatherResponseCities.getListResponse().get(i).getName(), weatherResponseCities.getListResponse().get(i).getMain().getTemp(), weatherResponseCities.getListResponse().get(i).getSys().getCountry(), weatherResponseCities.getListResponse().get(i).getId()));
                }
                view.setCityList(listCity);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }));


    }

    public void deleteFromPref(String city) {
        Set<String> cities = new HashSet<>();
        SharedPreferences.Editor editor = prefCities.edit();
        cities=prefCities.getStringSet("cities",new HashSet<String>());
        for(String s : cities) if(s.contains(city)) {
            cities.remove(s);
            break;
        }
        editor.putStringSet("cities", cities);
        editor.commit();
    }

    public void onDestroy() {
        compositeDisposable.dispose();
    }

}
