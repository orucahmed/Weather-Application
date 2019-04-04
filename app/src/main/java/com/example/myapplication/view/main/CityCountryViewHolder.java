package com.example.myapplication.view.main;

import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CityCountryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cityCounty)
    protected TextView cityCountry;

    public CityCountryViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
