package com.example.myapplication.view.cityList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.data.weather.ListCity;
import com.example.myapplication.di.MyApplication;
import com.example.myapplication.presentation.cityList.CityListPresenter;
import com.example.myapplication.view.main.MainActivity;
import com.example.myapplication.view.notification.NotificationActivity;
import com.example.myapplication.view.weather.WeatherPageActivity;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CityListActivity extends AppCompatActivity implements CityListPresenter.CityListView, DeleteItemSwipeListener {
    @Inject
    protected CityListPresenter presenter;

    @BindView(R.id.cityList)
    protected RecyclerView recyclerView;


    private CityListRecycleViewAdapter adapter;
    private ItemTouchHelper itemTouchHelper;

    @OnClick(R.id.closeBtn)
    public void onClickClose(View v){
        finish();
    }

    @OnClick(R.id.moreBtn)
    public void onClickMore(View v){
        Intent myIntent = new Intent(CityListActivity.this, NotificationActivity.class);
        CityListActivity.this.startActivity(myIntent);
    }


    @OnClick(R.id.addCIty)
    public void onClick(View v){
        Intent myIntent = new Intent(CityListActivity.this, MainActivity.class);
        myIntent.putExtra("from", "CityListActivity");
        CityListActivity.this.startActivity(myIntent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);


        adapter = new CityListRecycleViewAdapter(new CityListCustomClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent myIntent = new Intent(CityListActivity.this, WeatherPageActivity.class);
                myIntent.putExtra("position", position);
                CityListActivity.this.startActivity(myIntent);

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter, this));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        presenter.onCreate(this);
        presenter.getWeatherCities();
    }

    @Override
    public void setCityList(List<ListCity> listCities) {
        adapter.setCityList(listCities);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleted(View v, int position) {
        presenter.deleteFromPref(adapter.getCity(position));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
