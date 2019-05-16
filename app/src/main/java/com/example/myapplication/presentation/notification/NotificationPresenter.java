package com.example.myapplication.presentation.notification;

import android.content.SharedPreferences;

import com.example.myapplication.data.notification.NotificationList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;

public class NotificationPresenter {

    private final SharedPreferences prefCities;

    public interface NotificationView {
        void setNotificartion(List<NotificationList> notificationList);
        void setRadioBtn(boolean enable);
    }

    private NotificationView view;

    public void onCreate(NotificationView v) {
        this.view = v;
    }


    @Inject
    public NotificationPresenter(@Named("cities") SharedPreferences prefCities) {
        this.prefCities = prefCities;
    }

    public void loadPref() {
        Set<String> cities =  new TreeSet<>(prefCities.getStringSet("cities", new HashSet<String>()));
        List<NotificationList> notificationList = new ArrayList<>();
        for (String s : cities)
            notificationList.add(new NotificationList(s.split(":")[1], s.split(":")[2], s.split(":")[0]));
        view.setNotificartion(notificationList);
    }

    public void pupulateRadioBtn(){
        view.setRadioBtn(prefCities.getBoolean("notification",false ));
    }

    public void populatePref(List<NotificationList> notificationList) {
        SharedPreferences.Editor editor = prefCities.edit();
        Set<String> cities = new HashSet<>();
        for (NotificationList item : notificationList)
            cities.add(item.getId() + ":" + item.getName() + ":" + item.getChecked());
        editor.putStringSet("cities", cities);
        editor.commit();
    }

    public void populatePrefNotification(boolean enable){
        SharedPreferences.Editor editor = prefCities.edit();
        editor.putBoolean("notification",enable);
        editor.commit();
    }
}
