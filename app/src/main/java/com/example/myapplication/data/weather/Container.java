package com.example.myapplication.data.weather;

public class Container {
    private WeatherResponse weatherResponse;
    private WeatherResponseDays weatherResponseDays;
    private WeatherResponseFiveDays weatherResponseFiveDays;

    public Container(WeatherResponse weatherResponse, WeatherResponseDays weatherResponseDays, WeatherResponseFiveDays weatherResponseFiveDays) {
        this.weatherResponse = weatherResponse;
        this.weatherResponseDays = weatherResponseDays;
        this.weatherResponseFiveDays = weatherResponseFiveDays;
    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public WeatherResponseDays getWeatherResponseDays() {
        return weatherResponseDays;
    }

    public WeatherResponseFiveDays getWeatherResponseFiveDays() {
        return weatherResponseFiveDays;
    }
}
