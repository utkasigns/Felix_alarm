package com.example.felixalarm.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.felixalarm.R;
import com.example.felixalarm.adapters.AlarmsAdapter;
import com.example.felixalarm.entities.Alarm;
import com.example.felixalarm.database.AlarmsDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmActivity extends AppCompatActivity  {
    public static final int REQUEST_CODE_ADD_ALARM = 1;
    public static final int REQUEST_CODE_UPDATE_ALARM = 2;
    public static final int REQUEST_CODE_SHOW_ALARMS = 3;
    private RecyclerView alarmRecyclerView;


    RecyclerView.LayoutManager alarmManager;
    RecyclerView.Adapter alarmsAdapter;
//    AlarmListApplication alarmListApplication= (AlarmListApplication) this.getApplication();
    private List<Alarm> alarmList;
    private int alarmClickedPosition = -1;
    TextClock currentTime;
    boolean isOpened;

    NotesActivity notesActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmList=AlarmListApplication.getAlarmList();

        Intent intent = getIntent();
        String descriptionT = intent.getStringExtra("description");
        boolean isThemeChanged = intent.getBooleanExtra("theme", false);

        TextView textMyNotes = findViewById(R.id.textMyNotes);
        textMyNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //метод по срабатыванию будильника должен быть!
                isOpened = true;

//                notesActivity.checkNotes(isOpened);
//
//                Intent i2 = new Intent(getApplicationContext(), NotesActivity.class);
//                i2.putExtra("flag", isOpened);
//                startActivity(i2);

                Intent intent1 = new Intent(getApplicationContext(), AlarmOnActivity.class);
                intent1.putExtra("description", descriptionT);
                intent1.putExtra("theme", isThemeChanged);

                startActivity(intent1);

            }
        });


        currentTime = findViewById(R.id.textClock);
//        String currentTimeT = currentTime + " дп";
//
//        Timer t = new Timer();
//        t.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Intent intent = getIntent();
//                String date = intent.getStringExtra("date");
//
//                if (currentTimeT.equals(date)) {
//                    Intent intent1 = new Intent(getApplicationContext(), AlarmOnActivity.class);
//                    startActivity(intent1);
//
//
//                }
//            }
//        },0, 1000);


        ImageView imageAddNewAlarm = findViewById(R.id.imageAddAlarm);
        imageAddNewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                new Intent(AlarmActivity.this, CreateAlarmActivity.class),
                REQUEST_CODE_ADD_ALARM);
                }

            });
        getAlarms();




        alarmRecyclerView = findViewById(R.id.alarmRecycleView);
        alarmRecyclerView.setHasFixedSize(true);

        alarmManager= new LinearLayoutManager(this);
        alarmRecyclerView.setLayoutManager(alarmManager);

        alarmsAdapter=new AlarmsAdapter(alarmList,AlarmActivity.this);
        alarmRecyclerView.setAdapter(alarmsAdapter);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_alarm);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_alarm:
                        return true;
                    case R.id.nav_notes:
                        Intent intent1 = new Intent(getApplicationContext(), NotesActivity.class);
                        intent1.putExtra("flag", isOpened);
                        overridePendingTransition(0, 0);
                        startActivity(intent1);
                        return true;
                    case R.id.nav_weather:
                        Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
                        intent.putExtra("flag", isOpened);
                        overridePendingTransition(0, 0);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });}

     private void getAlarms(){
        class GetAlarmsTask extends AsyncTask<Void, Void, List<Alarm>>{
            @Override
            protected List<Alarm> doInBackground(Void... voids) {
                return AlarmsDatabase
                        .getDatabase(getApplicationContext())
                        .alarmDao().getAllAlarms();
            }

            @Override
            protected void onPostExecute(List<Alarm> alarms) {
                super.onPostExecute(alarms);
                Log.d("MY_ALARMS",alarms.toString());
            }
        }
        new GetAlarmsTask().execute();

     }

    public void onAlarmClicked(Alarm alarm, int position) {
        alarmClickedPosition = position;
        Intent intent = new Intent(getApplicationContext(),CreateAlarmActivity.class);
        intent.putExtra("isViewOrUpdate", true);
        intent.putExtra("alarm", alarm);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_ALARM);
    }


    private void getAlarms(final int requestCode, final boolean isAlarmDeleted) {

        @SuppressLint("StaticFieldLeak")
        class GetAlarmTask extends AsyncTask<Void, Void, List<Alarm>> {

            @Override
            protected List<Alarm> doInBackground(Void... voids) {
                return AlarmsDatabase
                        .getDatabase(getApplicationContext())
                        .alarmDao().getAllAlarms();
            }

            @Override
            protected void onPostExecute(List<Alarm> alarms) {
                super.onPostExecute(alarms);
//                Log.d("MY_NOTES", notes.toString());
                if (requestCode == REQUEST_CODE_SHOW_ALARMS ) {
                    alarmList.addAll(alarms);
                    alarmsAdapter.notifyDataSetChanged();
                } else if (requestCode == REQUEST_CODE_ADD_ALARM) {
                    alarmList.add(0, alarms.get(0));
                    alarmsAdapter.notifyItemInserted(0);
                    alarmRecyclerView.smoothScrollToPosition(0);
                } else if (requestCode ==REQUEST_CODE_UPDATE_ALARM) {
                    alarmList.remove(alarmClickedPosition);

                    if (isAlarmDeleted) {
                        alarmsAdapter.notifyItemRemoved(alarmClickedPosition);
                    }else {
                        alarmList.add(alarmClickedPosition, alarms.get(alarmClickedPosition));
                        alarmsAdapter.notifyItemChanged(alarmClickedPosition);
                    }
                }
            }

        }
        new GetAlarmTask().execute();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQUEST_CODE_ADD_ALARM && resultCode == RESULT_OK) {
            getAlarms(REQUEST_CODE_ADD_ALARM, false);
        } else if (requestCode == REQUEST_CODE_UPDATE_ALARM&& resultCode == RESULT_OK) {
            if (data != null) {
                getAlarms(REQUEST_CODE_UPDATE_ALARM, data.getBooleanExtra("isNoteDeleted", false));
            }
        }
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
