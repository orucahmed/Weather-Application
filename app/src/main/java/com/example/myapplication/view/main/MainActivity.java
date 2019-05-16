package com.example.myapplication.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.notification.NotificationJobService;
import com.example.myapplication.di.MyApplication;
import com.example.myapplication.presentation.main.MainPresenter;
import com.example.myapplication.view.weather.WeatherPageActivity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainView {

    private static final long time = 3600000L;

    @Inject
    protected MainPresenter presenter;

    @BindView(R.id.textInput)
    protected EditText textInput;
    @BindView(R.id.listCities)
    protected RecyclerView recyclerView;
    @BindView(R.id.noResults)
    protected TextView noResults;

    private RecycleViewAdapter adapter;

    @OnClick(R.id.closeBtn)
    public void onClickClose(View v) {
        finish();
    }


    @OnEditorAction(R.id.textInput)
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            presenter.getCityCountry(textInput.getText().toString());
            return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getAppComponent().inject(this);

        callBackgroundProcces(getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE).getBoolean("notification", false));

        presenter.onCreate(this);
        presenter.navigatePreferences(getIntent().getStringExtra("from"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        adapter = new RecycleViewAdapter(new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                presenter.populatePreferences(position, adapter.getCity(position));
                Intent myIntent = new Intent(MainActivity.this, WeatherPageActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListCity(List<String> listCity) {
        noResults.setText("");
        adapter.setCities(listCity);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setEmpty() {
        noResults.setText("No results found...");
    }

    @Override
    public void navigateToNextActivity() {
        Intent myIntent = new Intent(MainActivity.this, WeatherPageActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void callBackgroundProcces(boolean enable) {
        if (enable) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                JobScheduler jobScheduler = (JobScheduler) getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
                ComponentName componentName = new ComponentName(this, NotificationJobService.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    JobInfo jobInfoObj = new JobInfo.Builder(1, componentName)
                            .setPeriodic(time)
                            .setRequiresBatteryNotLow(true)
                            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NOT_ROAMING)
                            .setPersisted(true).build();
                    jobScheduler.schedule(jobInfoObj);
                }
            }
        }
    }

}
