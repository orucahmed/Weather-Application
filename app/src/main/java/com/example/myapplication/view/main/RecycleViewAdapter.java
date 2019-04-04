package com.example.myapplication.view.main;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecycleViewAdapter extends RecyclerView.Adapter<CityCountryViewHolder> {

    private final List<String> cities = new ArrayList<>();
    private final CustomItemClickListener listener;

    public RecycleViewAdapter(CustomItemClickListener listener) {
        this.listener = listener;
    }


    public void setCities(List<String> cities) {
        this.cities.clear();
        this.cities.addAll(cities);

    }

    @NonNull
    @Override
    public CityCountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_country_view_holder, parent, false);
        final CityCountryViewHolder mViewHolder = new CityCountryViewHolder(mView);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getLayoutPosition());
            }
        });
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CityCountryViewHolder holder, final int position) {
        holder.cityCountry.setText(cities.get(position));
    }


    @Override
    public int getItemCount() {
        return cities.size();
    }


}
