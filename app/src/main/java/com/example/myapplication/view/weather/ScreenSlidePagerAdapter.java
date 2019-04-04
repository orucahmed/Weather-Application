package com.example.myapplication.view.weather;

import com.example.myapplication.presentation.weather.WeatherPagePresenter;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private List<String> cities;

    public ScreenSlidePagerAdapter(FragmentManager fm, List<String> cities) {
        super(fm);
        this.cities = cities;
    }

    @Override
    public Fragment getItem(int position) {
        return WeatherPageFragment.getInstance(cities.get(position));
    }

    @Override
    public int getCount() {
        return WeatherPagePresenter.NUM_PAGES;
    }
}
