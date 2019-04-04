package com.example.myapplication.presentation.weather;

import android.content.SharedPreferences;
import android.util.Pair;

import com.example.myapplication.R;
import com.example.myapplication.data.weather.Container;
import com.example.myapplication.data.weather.Forecast;
import com.example.myapplication.data.weather.ForecastDaily;
import com.example.myapplication.data.Location.LocationService;
import com.example.myapplication.data.Location.TimeOffest;
import com.example.myapplication.data.weather.WeatherResponse;
import com.example.myapplication.data.weather.WeatherResponseDays;
import com.example.myapplication.data.weather.WeatherResponseFiveDays;
import com.example.myapplication.data.weather.WeatherService;
import com.example.myapplication.di.DataModule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

public class WeatherPageFragmentPresenter {
    public interface WeatherPageFragmentView {

        void setweatherResponseFiveDays(List<Forecast> forecast);

        void setWeatherResponse(String cityName, String description, String temperature, String wind, String humidity, String pressure, String visibility, String maxTemp, String minTemp, String icon, String sunrise, String sunset, String day , String date);

        void setWeatherResponseDays(List<ForecastDaily> forecastDaily);


    }

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final SharedPreferences prefCities;
    private final WeatherService weatherService;
    private final LocationService locationService;

    private WeatherPageFragmentView view;

    @Inject
    public WeatherPageFragmentPresenter(@Named("cities") SharedPreferences prefCities, WeatherService weatherService, LocationService locationService) {
        this.prefCities = prefCities;
        this.weatherService = weatherService;
        this.locationService = locationService;
    }

    public void onCreate(WeatherPageFragmentView view) {
        this.view = view;
    }


    public String convertDegreeToCardinalDirection(int directionInDegrees) {
        String cardinalDirection = null;
        if ((directionInDegrees >= 348.75) && (directionInDegrees <= 360) ||
                (directionInDegrees >= 0) && (directionInDegrees <= 11.25)) {
            cardinalDirection = "N";
        } else if ((directionInDegrees >= 11.25) && (directionInDegrees <= 33.75)) {
            cardinalDirection = "NNE";
        } else if ((directionInDegrees >= 33.75) && (directionInDegrees <= 56.25)) {
            cardinalDirection = "NE";
        } else if ((directionInDegrees >= 56.25) && (directionInDegrees <= 78.75)) {
            cardinalDirection = "ENE";
        } else if ((directionInDegrees >= 78.75) && (directionInDegrees <= 101.25)) {
            cardinalDirection = "E";
        } else if ((directionInDegrees >= 101.25) && (directionInDegrees <= 123.75)) {
            cardinalDirection = "ESE";
        } else if ((directionInDegrees >= 123.75) && (directionInDegrees <= 146.25)) {
            cardinalDirection = "SE";
        } else if ((directionInDegrees >= 146.25) && (directionInDegrees <= 168.75)) {
            cardinalDirection = "SSE";
        } else if ((directionInDegrees >= 168.75) && (directionInDegrees <= 191.25)) {
            cardinalDirection = "S";
        } else if ((directionInDegrees >= 191.25) && (directionInDegrees <= 213.75)) {
            cardinalDirection = "SSW";
        } else if ((directionInDegrees >= 213.75) && (directionInDegrees <= 236.25)) {
            cardinalDirection = "SW";
        } else if ((directionInDegrees >= 236.25) && (directionInDegrees <= 258.75)) {
            cardinalDirection = "WSW";
        } else if ((directionInDegrees >= 258.75) && (directionInDegrees <= 281.25)) {
            cardinalDirection = "W";
        } else if ((directionInDegrees >= 281.25) && (directionInDegrees <= 303.75)) {
            cardinalDirection = "WNW";
        } else if ((directionInDegrees >= 303.75) && (directionInDegrees <= 326.25)) {
            cardinalDirection = "NW";
        } else if ((directionInDegrees >= 326.25) && (directionInDegrees <= 348.75)) {
            cardinalDirection = "NNW";
        } else {
            cardinalDirection = "?";
        }

        return cardinalDirection;
    }


    public void populatePreferences(String city) {
        SharedPreferences.Editor editor = prefCities.edit();
        editor.putString("lastCity", city);
        editor.commit();
    }


    public void getData(String city) {

        compositeDisposable.add(Observable.zip(weatherService.getWeatherFiveDays(city, DataModule.API_KEY, "metric").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()),
                weatherService.getWeatherDaily(city, DataModule.API_KEY, "metric", "10").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()),
                weatherService.getWeather(city, DataModule.API_KEY, "metric").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()),
                new Function3<WeatherResponseFiveDays, WeatherResponseDays, WeatherResponse, Container>() {
                    @Override
                    public Container apply(WeatherResponseFiveDays weatherResponseFiveDays, WeatherResponseDays weatherResponseDays, WeatherResponse weatherResponse) throws Exception {
                        return new Container(weatherResponse,weatherResponseDays,weatherResponseFiveDays);
                    }
                }).flatMap(new Function<Container, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Container container) throws Exception {
                return locationService.getOffset(DataModule.API_KEY_LOCATION, "json", "position", container.getWeatherResponse().getCoord().getLon(), container.getWeatherResponse().getCoord().getLat()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<TimeOffest>() {
                            @Override
                            public void accept(TimeOffest timeOffest) throws Exception {
                                SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm");
                                SimpleDateFormat formatDateDay = new SimpleDateFormat("EEEE");
                                SimpleDateFormat forma = new SimpleDateFormat("dd.MM.yyyy");
                                List<Forecast> forecast = new ArrayList<>();
                                for (int i = 0; i < container.getWeatherResponseFiveDays().getListResponse().size(); i++)
                                    forecast.add(new Forecast(container.getWeatherResponseFiveDays().getListResponse().get(i).getDt_txt(), container.getWeatherResponseFiveDays().getListResponse().get(i).getWeather().get(0).getIcon(), container.getWeatherResponseFiveDays().getListResponse().get(i).getMain().getTemp()));
                                List<ForecastDaily> forecastDaily = new ArrayList<>();
                                for (int i = 1; i < container.getWeatherResponseDays().getListdaily().size(); i++)
                                    forecastDaily.add(new ForecastDaily(formatDateDay.format(
                                            container.getWeatherResponseDays().getListdaily().get(i).getDt()  * 1000),
                                            container.getWeatherResponseDays().getListdaily().get(i).getWeather().get(0).getIcon(),
                                            container.getWeatherResponseDays().getListdaily().get(i).getTemp().getMax(), container.getWeatherResponseDays().getListdaily().get(i).getTemp().getMin()));
                                view.setWeatherResponse(container.getWeatherResponse().getName(),
                                        container.getWeatherResponse().getWeather().get(0).getDescription(),
                                        String.valueOf(Math.round(container.getWeatherResponse().getMain().getTemp())) + "˚",
                                        convertDegreeToCardinalDirection((int) container.getWeatherResponse().getWind().getSpeed()) + " " + String.valueOf(Math.round(container.getWeatherResponse().getWind().getSpeed() * 3.6)) + " km/h",
                                        String.valueOf((int) container.getWeatherResponse().getMain().getHumidity()) + "%",
                                        String.valueOf((int) container.getWeatherResponse().getMain().getPressure()) + " hPA",
                                        String.valueOf((container.getWeatherResponse().getVisibility()) / 1000) + " km",
                                        String.valueOf((int) container.getWeatherResponse().getMain().getTemp_max()) + "˚",
                                        String.valueOf((int) container.getWeatherResponse().getMain().getTemp_min()) + "˚",
                                        "icon" + container.getWeatherResponse().getWeather().get(0).getIcon(), formatDate.format(new Date((container.getWeatherResponse().getSys().getSunrise() + timeOffest.getCurrentOffsert() ) * 1000)),
                                        formatDate.format(new Date((container.getWeatherResponse().getSys().getSunset() + timeOffest.getCurrentOffsert()) * 1000)),
                                        formatDateDay.format(new Date(container.getWeatherResponse().getSys().getSunset() * 1000)),
                                        forma.format(new Date(container.getWeatherResponse().getSys().getSunset() * 1000)));
                                view.setWeatherResponseDays( forecastDaily);
                                view.setweatherResponseFiveDays(forecast);
                            }
                        });
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {


            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }));
    }

    public void onDestroy() {
        compositeDisposable.dispose();
    }
}
