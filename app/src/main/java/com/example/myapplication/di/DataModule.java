package com.example.myapplication.di;

import com.example.myapplication.data.location.LocationService;
import com.example.myapplication.data.weather.WeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static final String BASE_URL_LOCATION = "https://api.timezonedb.com/v2.1/";
    public static final String API_KEY = "15646a06818f61f7b8d7823ca833e1ce";
    public static final String API_KEY_LOCATION = "97ZQTZ12XC49";


    @Provides
    @Singleton
    WeatherService providesWeatherService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(WeatherService.class);
    }

    @Provides
    @Singleton
    LocationService providesLocationService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_LOCATION)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(LocationService.class);
    }
}
