package com.example.felixalarm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.felixalarm.R;
import com.example.felixalarm.adapters.NotesAdapter;
import com.example.felixalarm.adapters.WeatherAdapter;
import com.example.felixalarm.adapters.WeatherModal;
import com.example.felixalarm.entities.Note;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class WeatherActivity extends AppCompatActivity {

    private EditText inputCity;
    private TextView cityNameText, temperatureText, conditionText;
    private ImageView iconImage;

    private RecyclerView weatherRecyclerView;
    private List<WeatherModal> weatherList;
    private WeatherAdapter weatherAdapter;

    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "d2d4dc6c3e99f743a99857ee56a2e875";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        inputCity = findViewById(R.id.inputCity);
        cityNameText = findViewById(R.id.textCityName);
        temperatureText = findViewById(R.id.textTemperature);
        conditionText = findViewById(R.id.textCondition);
        iconImage = findViewById(R.id.imageIcon);

        weatherRecyclerView = findViewById(R.id.weatherRecyclerView);
        weatherRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL)
        );

        weatherList = new ArrayList<>();
        weatherRecyclerView.setAdapter(weatherAdapter);






        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_weather);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_alarm:
                        startActivity(new Intent(getApplicationContext(), AlarmActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_notes:
                        startActivity(new Intent(getApplicationContext(), NotesActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_weather:
                        return true;
                }
                return false;
            }
        });
    }
}
