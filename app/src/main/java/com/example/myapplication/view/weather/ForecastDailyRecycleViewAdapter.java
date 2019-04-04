package com.example.myapplication.view.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.data.weather.ForecastDaily;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastDailyRecycleViewAdapter extends RecyclerView.Adapter<ForecastDailyViewHolder> {

    private final List<ForecastDaily> forecast = new ArrayList<>();

    public void setForecast(List<ForecastDaily> forecast) {
        this.forecast.clear();
        this.forecast.addAll(forecast);
    }

    @NonNull
    @Override
    public ForecastDailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_hours_view_holder, parent, false);
        final ForecastDailyViewHolder myViewHolder = new ForecastDailyViewHolder(myView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastDailyViewHolder holder, int position) {
        holder.day.setText(forecast.get(position).getDay());
        Picasso.get().load("https://openweathermap.org/img/w/" + forecast.get(position).getIcon() + ".png").into(holder.iconDaily);
        holder.tempMinDaily.setText(String.valueOf((int) forecast.get(position).getTempMin()) + "˚");
        holder.tempMaxDaily.setText(String.valueOf((int) forecast.get(position).getTempMax()) + "˚");
    }

    @Override
    public int getItemCount() {
        return forecast.size();
    }
}
