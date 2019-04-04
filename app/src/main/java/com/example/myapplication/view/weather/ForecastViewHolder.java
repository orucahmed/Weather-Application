package com.example.myapplication.view.weather;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.hourForecast)
    protected TextView hourForecast;
    @BindView(R.id.iconForecast)
    protected ImageView iconForecast;
    @BindView(R.id.tempTime)
    protected TextView tempForecast;


    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
