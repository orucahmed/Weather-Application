package com.example.myapplication.presentation.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;

import com.example.myapplication.data.weather.CityCountry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private final SharedPreferences prefCities;
    private final SharedPreferences prefPosition;
    private final Context context;

    public interface MainView {
        void setListCity(List<String> listCity);

        void setEmpty();

        void navigateToNextActivity();
    }


    private List<CityCountry> loadJSONFromAsset(Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open("city.list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        List<CityCountry> cityCountryList = new Gson().fromJson(json, new TypeToken<List<CityCountry>>() {
        }.getType());
        Collections.sort(cityCountryList);
        return cityCountryList;

    }

    private MainView view;
    private List<CityCountry> listCityCountry;
    private List<Integer> listCityId;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public MainPresenter(@Named("cities") SharedPreferences prefCities, @Named("position") SharedPreferences prefPosition, Context context) {
        this.prefCities = prefCities;
        this.prefPosition = prefPosition;
        this.context = context;
    }

    public void onCreate(MainView view) {
        this.view = view;

        Observable<List<CityCountry>> observable = Observable.create(new ObservableOnSubscribe<List<CityCountry>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CityCountry>> emitter) throws Exception {
                emitter.onNext(loadJSONFromAsset(context));
                emitter.onComplete();
            }
        });

        compositeDisposable.add(observable.subscribeOn(Schedulers.io()).subscribe(new Consumer<List<CityCountry>>() {
            @Override
            public void accept(List<CityCountry> cityCountries) throws Exception {
                listCityCountry = cityCountries;

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }));


    }

    public void getCityCountry(String input) {
        List<String> listCity = new ArrayList<>();
        listCityId = new ArrayList<>();
        input = input.trim();
        input = input.toLowerCase();
        input = input.substring(0, 1).toUpperCase() + input.substring(1);

        for (int i = 0; i < listCityCountry.size(); i++) {
            if (listCityCountry.get(i).getName().contains(input)) {
                listCity.add(listCityCountry.get(i).getName() + ", " + listCityCountry.get(i).getCountry());
                listCityId.add((int) listCityCountry.get(i).getId());
            }
        }
        if (listCity.isEmpty()) view.setEmpty();
        else view.setListCity(listCity);
    }

    public void populatePreferences(int position, String citytoAdd) {
        Set<String> cities = new TreeSet<>(prefCities.getStringSet("cities", new HashSet<String>()));
        SharedPreferences.Editor editor = prefCities.edit();

        cities.add(String.valueOf(listCityId.get(position)) + ":" + citytoAdd.split(",")[0] + ":false");
        editor.putStringSet("cities", cities);
        editor.commit();

        SharedPreferences.Editor positionEditor = prefPosition.edit();
        int temp = 0;
        for (String city : cities) {
            if (city.equals(String.valueOf(listCityId.get(position)))) {
                positionEditor.putInt("pos", temp);
                break;
            }
            temp++;
        }
        positionEditor.commit();
    }

    public void navigatePreferences(String from) {
        if (prefCities.getString("lastCity", "none") != "none" && TextUtils.isEmpty(from)) {
            view.navigateToNextActivity();
        }
    }

    public void onDestroy() {
        compositeDisposable.dispose();
    }

}
