package com.example.myapplication.view.cityList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.data.weather.ListCity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityListRecycleViewAdapter extends RecyclerView.Adapter<CityListViewHolder>  {

    private final List<ListCity> cityList = new ArrayList<>();


    private final CityListCustomClickListener listener;

    public CityListRecycleViewAdapter(CityListCustomClickListener listener) {
        this.listener = listener;
    }

    public void setCityList(List<ListCity> cityList) {
        this.cityList.clear();
        this.cityList.addAll(cityList);
    }


    public void deleteItem(int position){
        String o = String.valueOf(cityList.get(position).getId());
        cityList.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();

    }


    public String getCity(int position){
        return String.valueOf(cityList.get(position).getId());
    }


    @NonNull
    @Override
    public CityListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_view_holder, parent, false);
        final CityListViewHolder myViewHolder = new CityListViewHolder(myView);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, myViewHolder.getLayoutPosition());
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityListViewHolder holder, int position) {
        SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm");
        holder.cityListTime.setText(formatDate.format(new Date(cityList.get(position).getDt() * 1000)));
        holder.cityListName.setText(cityList.get(position).getName());
        holder.cityListTemp.setText(String.valueOf((int) cityList.get(position).getTemp()) + "˚");
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }
}
