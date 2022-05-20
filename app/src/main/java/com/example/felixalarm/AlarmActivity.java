package com.example.felixalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AlarmActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_alarm);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_alarm:
                        return true;
                    case R.id.nav_notes:
                        startActivity(new Intent(getApplicationContext(), NotesActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_weather:
                        startActivity(new Intent(getApplicationContext(), WeatherActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }
}

//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        View itemAlarm = findViewById(R.id.nav_alarm);
//        View itemNotes = findViewById(R.id.nav_notes);
//        View itemWeather = findViewById(R.id.nav_weather);
//
//        itemAlarm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openAlarmActivity();
//            }
//        });
//        itemNotes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openNotesActivity();
//            }
//        });
//        itemWeather.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openWeatherActivity();
//            }
//        });
//    }
//    public void openAlarmActivity() {
//        Intent intent = new Intent(this, AlarmActivity.class);
//        startActivity(intent);}
//    public void openNotesActivity() {
//        Intent intent = new Intent(this, NotesActivity.class);
//        startActivity(intent);}
//    public void openWeatherActivity() {
//        Intent intent = new Intent(this, WeatherActivity.class);
//        startActivity(intent);}
//    }
//}
