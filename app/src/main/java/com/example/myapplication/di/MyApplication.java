package com.example.myapplication.di;

import android.app.Application;

import com.example.myapplication.data.database.ObjectBox;

public class MyApplication extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        appComponent.inject(this);
        ObjectBox.init(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
