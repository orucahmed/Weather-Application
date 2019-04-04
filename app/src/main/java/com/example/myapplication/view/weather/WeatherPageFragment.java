package com.example.myapplication.view.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.weather.Forecast;
import com.example.myapplication.data.weather.ForecastDaily;
import com.example.myapplication.di.MyApplication;
import com.example.myapplication.presentation.weather.WeatherPageFragmentPresenter;
import com.example.myapplication.view.cityList.CityListActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class WeatherPageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, WeatherPageFragmentPresenter.WeatherPageFragmentView {


    private int getDrawable(String name) {

        int resId = this.getContext().getResources().getIdentifier(getContext().getPackageName() + ":drawable/" + name, null, null);
        return resId;
    }

    public static WeatherPageFragment getInstance(String city) {
        WeatherPageFragment myFragment = new WeatherPageFragment();
        Bundle args = new Bundle();
        args.putString("city", city);
        myFragment.setArguments(args);
        return myFragment;

    }

    @Inject
    protected WeatherPageFragmentPresenter presenter;

    @BindView(R.id.cityName)
    protected TextView cityName;

    @BindView(R.id.temperature)
    protected TextView temperature;

    @BindView(R.id.weathedDescription)
    protected TextView description;

    @BindView(R.id.background)
    protected ImageView background;

    @BindView(R.id.sunriseTime)
    protected TextView sunrise;

    @BindView(R.id.sunsetTime)
    protected TextView sunset;

    @BindView(R.id.humidityValue)
    protected TextView humidity;

    @BindView(R.id.windValue)
    protected TextView wind;

    @BindView(R.id.pressureValue)
    protected TextView pressure;

    @BindView(R.id.visibilityValue)
    protected TextView visibility;

    @BindView(R.id.day)
    protected TextView day;

    @BindView(R.id.maxTemp)
    protected TextView maxTemp;

    @BindView(R.id.minTemp)
    protected TextView minTemp;

    @BindView(R.id.date)
    protected TextView date;

    @BindView(R.id.weatherHours)
    protected RecyclerView recyclerViewDaily;

    @BindView(R.id.forecast)
    protected RecyclerView recyclerView;

    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeRefreshLayout;

    private ForecastRecycleViewAdapter adapter;
    private ForecastDailyRecycleViewAdapter adapterDaily;

    @OnClick(R.id.listOfCities)
    public void onClick(View v) {
        Intent myIntent = new Intent(getContext(), CityListActivity.class);
        WeatherPageFragment.this.startActivity(myIntent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_weather_page, container, false);
        ButterKnife.bind(this, view);

        swipeRefreshLayout.setOnRefreshListener(this);
        presenter.onCreate(this);
        adapter = new ForecastRecycleViewAdapter();
        adapterDaily = new ForecastDailyRecycleViewAdapter();
        presenter.getData(getArguments().getString("city").replaceAll("\\s+", ""));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        recyclerViewDaily.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerViewDaily.setAdapter(adapterDaily);
        presenter.populatePreferences(getArguments().getString("city").replaceAll("\\s+", ""));
        return view;
    }

    @Override
    public void onAttach(Context context) {
        ((MyApplication) context.getApplicationContext()).getAppComponent().inject(this);
        super.onAttach(context);

    }

    @Override
    public void onRefresh() {
        presenter.getData(getArguments().getString("city").replaceAll("\\s+", ""));


    }

    @Override
    public void setweatherResponseFiveDays(List<Forecast> forecast) {
        adapter.setForecast(forecast);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setWeatherResponse(String cityName, String description, String temperature, String wind, String humidity, String pressure, String visibility, String maxTemp, String minTemp, String icon, String sunrise, String sunset, String day, String date) {
        this.cityName.setText(cityName);
        this.description.setText(description);
        this.temperature.setText(temperature);
        this.wind.setText(wind);
        this.humidity.setText(humidity);
        this.pressure.setText(pressure);
        this.visibility.setText(visibility);
        this.maxTemp.setText(maxTemp);
        this.minTemp.setText(minTemp);
        this.sunrise.setText(sunrise);
        this.sunset.setText(sunset);
        this.day.setText(day);
        this.date.setText(date);

        Picasso.get().load(getDrawable(icon)).resize(getContext().getResources().getDisplayMetrics().widthPixels, getContext().getResources().getDisplayMetrics().heightPixels).into(background);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setWeatherResponseDays(List<ForecastDaily> forecastDaily) {
        adapterDaily.setForecast(forecastDaily);
        adapterDaily.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }
}
