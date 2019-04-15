package com.example.myapplication.data.weather;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.example.myapplication.R;
import com.example.myapplication.di.DataModule;
import com.example.myapplication.view.weather.WeatherPageActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class NotificationHandler {

    private static String CHANNEL_ID = "1";
    private final SharedPreferences prefCities;
    private final WeatherService weatherService;
    private final List<String> cities = new ArrayList<>();
    private final Context context;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public NotificationHandler(@Named("cities") SharedPreferences prefCities, WeatherService weatherService, Context context) {
        this.prefCities = prefCities;
        this.weatherService = weatherService;
        this.context = context;
    }

    public void loadData() {
        Set<String> query = new TreeSet<>(prefCities.getStringSet("cities", new HashSet<>()));
        for (String s : query) {
            if (s.contains("false")) continue;
            else cities.add(s.split(":")[0]);
        }
        compositeDisposable.add(weatherService.getWeatherCities(TextUtils.join(",", cities), DataModule.API_KEY, "metric").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<WeatherResponseCities>() {
            @Override
            public void accept(WeatherResponseCities weatherResponse) throws Exception {

                List<String> cities = new ArrayList<>();
                List<String> descriptions = new ArrayList<>();
                List<String> temperatures = new ArrayList<>();

                for (int i = 0; i < weatherResponse.getListResponse().size(); i++) {
                    cities.add(weatherResponse.getListResponse().get(i).getName());
                    descriptions.add(weatherResponse.getListResponse().get(i).getWeather().get(0).getDescription());
                    temperatures.add(String.valueOf((int) weatherResponse.getListResponse().get(i).getMain().getTemp()) + "˚");

                }

                callNotification(cities, descriptions, temperatures);


            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }));
    }

    private void callNotification(List<String> city, List<String> desc, List<String> temp) {


        String notificationTex = "";

        for (int i = 0; i < city.size(); i++) {
            if (i == city.size() - 1)
                notificationTex += "<b>" + city.get(i) + "</b>" + " : " + desc.get(i) + " : " + temp.get(i);
            else
                notificationTex += "<b>" + city.get(i) + "</b>" + " : " + desc.get(i) + " : " + temp.get(i) + "<br>";
        }

        createNotificationChannel();
        Intent intent = new Intent(context, WeatherPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("WeatherApp")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(Html.fromHtml(notificationTex)))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        final android.app.Notification notification = notificationBuilder.build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, notification);

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Ahmed";
            String description = "Kako ide sex";
            int importance = android.app.NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notificationHandler behaviors after this
            android.app.NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService(android.app.NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void onDestroy() {
        compositeDisposable.dispose();
    }

}
