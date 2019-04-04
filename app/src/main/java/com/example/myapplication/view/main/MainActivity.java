package com.example.myapplication.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.di.MyApplication;
import com.example.myapplication.presentation.main.MainPresenter;
import com.example.myapplication.view.weather.WeatherPageActivity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainView {

    @Inject
    protected MainPresenter presenter;

    @BindView(R.id.textInput)
    protected EditText textInput;
    @BindView(R.id.listCities)
    protected RecyclerView recyclerView;
    @BindView(R.id.noResults)
    protected TextView noResults;

    private RecycleViewAdapter adapter;

    @OnEditorAction(R.id.textInput)
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            presenter.getCityCountry(textInput.getText().toString());
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getAppComponent().inject(this);

        presenter.onCreate(this);
        presenter.navigatePreferences(getIntent().getStringExtra("from"));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        adapter = new RecycleViewAdapter(new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                presenter.populatePreferences(position);
                Intent myIntent = new Intent(MainActivity.this, WeatherPageActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListCity(List<String> listCity) {
        noResults.setText("");
        adapter.setCities(listCity);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setEmpty() {
        noResults.setText("No results found...");
    }

    @Override
    public void navigateToNextActivity() {
        Intent myIntent = new Intent(MainActivity.this, WeatherPageActivity.class);
        MainActivity.this.startActivity(myIntent);
    }

}
