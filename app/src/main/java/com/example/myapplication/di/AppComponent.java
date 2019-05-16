package com.example.myapplication.di;

import com.example.myapplication.data.notification.NotificationJobService;
import com.example.myapplication.view.cityList.CityListActivity;
import com.example.myapplication.view.main.MainActivity;
import com.example.myapplication.view.notification.NotificationActivity;
import com.example.myapplication.view.weather.WeatherPageActivity;
import com.example.myapplication.view.weather.WeatherPageFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    void inject(MyApplication application);

    void inject(MainActivity mainActivity);

    void inject(WeatherPageActivity weatherPageActivity);

    void inject(CityListActivity cityListActivity);

    void inject(WeatherPageFragment weatherPageFragment);

    void inject(NotificationActivity notificationActivity);

    void inject(NotificationJobService notificationJobService);
}
