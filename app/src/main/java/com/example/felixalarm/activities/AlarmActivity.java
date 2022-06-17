package com.example.felixalarm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.felixalarm.R;
import com.example.felixalarm.adapters.AlarmsAdapter;
//import com.example.felixalarm.entities.Alarm;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlarmActivity extends AppCompatActivity  {
    public static final int REQUEST_CODE_ADD_ALARM = 1;
    public static final int REQUEST_CODE_UPDATE_ALARM = 2;
    public static final int REQUEST_CODE_SHOW_ALARMS = 3;

    LinearLayoutManager alarmManager;
    AlarmsAdapter alarmsAdapter;
    AlarmListApplication alarmListApplication= (AlarmListApplication) this.getApplication();

    List<Alarm> alarmList=new ArrayList<Alarm>();
    private static final String TAG="alarm app";

    AlarmClock alarmClock;
    int hours,minutes;

    private int alarmClickedPosition = -1;
    private RecyclerView alarmRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        fillAlarmList();
        Log.d(TAG, "OnCreate:" +alarmList.toString());
        alarmList=AlarmListApplication.getAlarmList();


        ImageView imageAddNewAlarm = findViewById(R.id.imageAddAlarm);
        imageAddNewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AlarmActivity.this, CreateAlarmActivity.class);
                startActivity(i);
                ;}

            });


        alarmRecyclerView=findViewById(R.id.alarmRecycleView);
        alarmRecyclerView.setHasFixedSize(true);

        alarmManager= new LinearLayoutManager(this);
        alarmRecyclerView.setLayoutManager(alarmManager);

        alarmsAdapter=new AlarmsAdapter(alarmList,AlarmActivity.this);
        alarmRecyclerView.setAdapter(alarmsAdapter);


//        AlarmsAdapter alarmsAdapter=new AlarmsAdapter(alarmList);
//        alarmRecyclerView.setAdapter(alarmsAdapter);
//        alarmRecyclerView.setLayoutManager(new LinearLayoutManager(this));





//                Intent i=new Intent(getApplicationContext(), );
//                 i.putExtra(AlarmClock.EXTRA_HOUR, hours);
//                 i.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
//                 i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
//                 startActivity(i);

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
        });}

    private void fillAlarmList() {
        Alarm a1=new Alarm(1,"aaa","12:12");
        Alarm a2=new Alarm(2,"ooo","13:12");
        alarmList.addAll(Arrays.asList(new Alarm[]{a1,a2}));
    }
}
















//        @Override
//        public void onAlarmClicked (Alarm alarm,int position){
//            alarmClickedPosition = position;
//            Intent intent = new Intent(getApplicationContext(), CreateAlarmActivity.class);
//            intent.putExtra("isViewOrUpdate", true);
//            intent.putExtra("alarm",alarm);
//            startActivityForResult(intent, REQUEST_CODE_UPDATE_ALARM);
//        }

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
////        Intent intent = new Intent(this, NotesActivity.class);
////        startActivity(intent);}
//    public void openWeatherActivity() {
//        Intent intent = new Intent(this, WeatherActivity.class);
//        startActivity(intent);}
//    }
//}});
