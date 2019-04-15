package com.example.myapplication.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesAppContext() {
        return application;
    }

    @Provides
    @Singleton
    @Named("cities")
    SharedPreferences providesSharedPPreferenceCities(Context context) {
        return context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }


    @Provides
    @Singleton
    @Named("position")
    SharedPreferences providesSharedPreferencesPosition(Context context) {
        return context.getSharedPreferences("Position", Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    @Named("lastCity")
    SharedPreferences providesSharedPreferencesLastCity(Context context) {
        return context.getSharedPreferences("LastCity", Context.MODE_PRIVATE);
    }

    @Provides
    Resources getResources(Context context) {
        return context.getResources();
    }

}
