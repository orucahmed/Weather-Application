package com.example.myapplication.view.weather;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ForecastDailyViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.day)
    protected TextView day;
    @BindView(R.id.iconDaily)
    protected ImageView iconDaily;
    @BindView(R.id.maxTempDaily)
    protected TextView tempMaxDaily;
    @BindView(R.id.minTempDaily)
    protected TextView tempMinDaily;

    public ForecastDailyViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
