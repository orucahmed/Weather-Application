package com.example.myapplication.view.cityList;

import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CityListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.cityListTime)
    protected TextView cityListTime;
    @BindView(R.id.cityListName)
    protected TextView cityListName;
    @BindView(R.id.cityListTemp)
    protected TextView cityListTemp;

    public CityListViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
