package com.example.myapplication.view.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.di.MyApplication;
import com.example.myapplication.presentation.weather.WeatherPagePresenter;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherPageActivity extends FragmentActivity implements WeatherPagePresenter.WeatherPageView {

    @Inject
    protected WeatherPagePresenter presenter;

    @BindView(R.id.viewPager)
    protected ViewPager mPager;

    private PagerAdapter pagerAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_page);
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getAppComponent().inject(this);
        Intent intent = getIntent();
        presenter.onCreate(this);
        presenter.getCityList();
        presenter.getPosition(intent.getIntExtra("position", -1));
        pagerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.setPrefPosition(mPager.getCurrentItem());
    }

    @Override
    public void setPosition(int position) {
        mPager.setCurrentItem(position);
    }

    @Override
    public void setCityList(List<String> listCity) {
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), listCity);
        mPager.setAdapter(pagerAdapter);
    }
}
