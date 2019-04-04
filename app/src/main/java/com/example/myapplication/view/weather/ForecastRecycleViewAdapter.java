package com.example.myapplication.view.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.data.weather.Forecast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastRecycleViewAdapter extends RecyclerView.Adapter<ForecastViewHolder> {


    private final List<Forecast> forecast = new ArrayList<>();


    public void setForecast(List<Forecast> forecast) {
        this.forecast.clear();
        this.forecast.addAll(forecast);
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_view_holder, parent, false);
        final ForecastViewHolder myViewHolder = new ForecastViewHolder(myView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        holder.tempForecast.setText(String.valueOf((int) forecast.get(position).getTemp())+"˚");
        String hour = forecast.get(position).getHour();
        holder.hourForecast.setText(hour.substring(11,13));
        Picasso.get().load("https://openweathermap.org/img/w/"+forecast.get(position).getIcon()+".png").into(holder.iconForecast);
    }

    @Override
    public int getItemCount() {
        return forecast.size();
    }
}
