package com.example.myapplication.data.weather;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherService {
    @GET("weather/")
    Observable<WeatherResponse> getWeather(@Query(value="id", encoded = true) String city, @Query("APPID") String key, @Query("units") String units);

    @GET("group")
    Observable<WeatherResponseCities> getWeatherCities(@Query(value="id", encoded = true) String city, @Query("APPID") String key, @Query("units") String units);

    @GET("forecast/")
    Observable<WeatherResponseFiveDays> getWeatherFiveDays(@Query(value="id", encoded=true) String city, @Query("APPID") String key, @Query("units") String units);

    @GET("forecast/daily/")
    Observable<WeatherResponseDays> getWeatherDaily(@Query(value="id", encoded=true) String city, @Query("APPID") String key, @Query("units") String units, @Query("cnt") String days);

}
