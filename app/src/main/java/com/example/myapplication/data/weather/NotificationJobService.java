package com.example.myapplication.data.weather;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import com.example.myapplication.di.MyApplication;

import javax.inject.Inject;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationJobService extends JobService {

    @Inject
    protected NotificationHandler notificationHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        ((MyApplication) getApplication()).getAppComponent().inject(this);
    }

    @Override
    public boolean onStartJob(JobParameters params) {

        notificationHandler.loadData();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
