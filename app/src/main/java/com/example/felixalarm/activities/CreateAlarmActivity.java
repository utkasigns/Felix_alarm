package com.example.felixalarm.activities;

import static com.example.felixalarm.activities.AlarmListApplication.nextId;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.felixalarm.R;
import com.example.felixalarm.database.AlarmsDatabase;
import com.example.felixalarm.entities.Alarm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CreateAlarmActivity extends AppCompatActivity {
    private TextView timer1;
    private int hour1, minute1;
    private Button alarmBack, alarmSave;
    private EditText alarmName;
    List<Alarm> alarmList;
    AlarmListApplication alarmListApplication = (AlarmListApplication) this.getApplication();

    String dateT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        alarmList = AlarmListApplication.getAlarmList();
        timer1 = findViewById(R.id.timer);
        timer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CreateAlarmActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hour1 = hourOfDay;
                                minute1 = minute;
                                //вносим часы и минуты в стрингу
                                String time = hour1 + ":" + minute1;
                                //инициализируем 24 часовой формат
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    //инициализируем 12ти часовой формат
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    //задаем выбранное время в текст вью
                                    timer1.setText(f12Hours.format(date));
                                    dateT = f12Hours.format(date);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, 12, 0, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //отображаем предыдущее выбранное время
                timePickerDialog.updateTime(hour1, minute1);
                timePickerDialog.show();

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                AlarmManager.AlarmClockInfo alarmClockInfo= new AlarmManager.AlarmClockInfo();

            }
        });

        alarmBack = findViewById(R.id.alarmBack);
        alarmBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreateAlarmActivity.this, AlarmActivity.class);
                startActivity(i);
            }
        });
        alarmName = findViewById(R.id.alarmName);

        alarmSave = findViewById(R.id.alarmSave);
        alarmSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarmName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(CreateAlarmActivity.this, "Alarm must have name!", Toast.LENGTH_LONG).show();
                    return;
                } else if (timer1.getText().toString().trim().isEmpty()) {
                    Toast.makeText(CreateAlarmActivity.this, "Select time for alarm!", Toast.LENGTH_LONG).show();
                    return;
                }
                String name = alarmName.getText().toString();
                Intent i = new Intent(CreateAlarmActivity.this, AlarmActivity.class);
                startActivity(i);



//                создаем будильник
//                 int nextId = AlarmListApplication.getNextId();


                 final Alarm alarm = new Alarm();
//                newAlarm.setId(nextId);
                alarm.setTime(timer1.getText().toString());
                alarm.setName(alarmName.getText().toString());


                //добавляем будильник в глобальный список
//
//                alarmList.add(newAlarm);
//                ++nextId;
//                AlarmListApplication.setNextId(nextId);


                @SuppressLint("StaticFieldLeak")
                class saveAlarmTask extends AsyncTask<Void, Void, Void>{

                    @Override
                    protected Void doInBackground(Void... voids) {
                        AlarmsDatabase.getDatabase(getApplicationContext()).alarmDao().insertAlarm(alarm);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        Intent intent=new Intent();
                        setResult(RESULT_OK);
                        finish();
                    }
                }


                new saveAlarmTask().execute();

            }

        });



    }


    @SuppressLint("UnspecifiedImmutableFlag")
    private PendingIntent getAlarmInfoPendingIntend() {
        Intent alarmInfoIntend = new Intent(this, AlarmActivity.class);
        alarmInfoIntend.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(this, 0, alarmInfoIntend, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    private void getAlarmActionPendingIntend() {
        Intent intent = new Intent(this, AlarmOnActivity.class);

    }


}