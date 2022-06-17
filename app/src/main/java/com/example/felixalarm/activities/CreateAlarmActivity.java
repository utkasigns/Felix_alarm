package com.example.felixalarm.activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AsyncNotedAppOp;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.felixalarm.R;
import com.example.felixalarm.entities.Alarm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CreateAlarmActivity extends AppCompatActivity {
    private TextView timer1;
    private int hour1,minute1;
    private Button alarmBack, alarmSave;
    private EditText alarmName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);
        timer1=findViewById(R.id.timer);
        timer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog =new TimePickerDialog(
                        CreateAlarmActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hour1=hourOfDay;
                                minute1=minute;
                                //вносим часы и минуты в стрингу
                                String time= hour1+":"+minute1;
                                //инициализируем 24 часовой формат
                                SimpleDateFormat f24Hours= new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date=f24Hours.parse(time);
                                    //инициализируем 12ти часовой формат
                                    SimpleDateFormat f12Hours= new SimpleDateFormat(
                                           "hh:mm aa"
                                    );
                                    //задаем выбранное время в текст вью
                                    timer1.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, 12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //отображаем предыдущее выбранное время
                timePickerDialog.updateTime(hour1, minute1);
                timePickerDialog.show();

                AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                AlarmManager.AlarmClockInfo alarmClockInfo= new AlarmManager.AlarmClockInfo();

            }
        });


        //....................................................................................
        //здесь  я тоже забыла чет дописать и теперь выходит ошибка

        //я перенесла эти 2 метода после метода ОнКриэйт, а то там были методы в методе кхехе
//        private PendingIntent getAlarmInfoPendingIntend(){
//            Intent alarmInfoIntend =new Intent(this,AlarmActivity.class);
//            alarmInfoIntend.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            return; PendingIntent.getActivity(this,0,alarmInfoIntend,PendingIntent.FLAG_UPDATE_CURRENT);
//
//        }
//        private PendingIntent  getAlarmActionPendingIntend(){
//            Intent intent=new Intent(this,AlarmOnActivity.class);
//     }
        //.....................................................................................


        alarmBack=findViewById(R.id.alarmBack);
        alarmBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        alarmName=findViewById(R.id.alarmName);
        alarmSave=findViewById(R.id.alarmSave);
        alarmSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAlarm();
            }
        });



        alarmSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String settingAlarmName=alarmName.getText().toString();

                Context context=CreateAlarmActivity.this;
                Intent i=new Intent(context,AlarmActivity.class);
                //...............................................
                //тут мне нужно как раз таки передать данные чтобы они сохранялись
//                    i.putExtra(AlarmClock.EXTRA_HOUR, hours);
//                    i.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
                    i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
            }
            //.....................................................................................
        });

        alarmName=findViewById(R.id.alarmName);



}
    private void saveAlarm() {
        if(alarmName==null) {
            Toast.makeText(this,"Alarm must have name!", Toast.LENGTH_LONG).show();
            return;
        }else if (timer1==null) {
            Toast.makeText(this, "Select time for alarm!", Toast.LENGTH_LONG).show();
            return;
        }
        final Alarm alarm=new Alarm();
        alarm.setName(alarmName.getText().toString());
        alarm.setTime(timer1.getText().toString());


    }



    private PendingIntent getAlarmInfoPendingIntend(){
        Intent alarmInfoIntend =new Intent(this,AlarmActivity.class);
        alarmInfoIntend.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(this,0,alarmInfoIntend,PendingIntent.FLAG_UPDATE_CURRENT);

    }
    private void getAlarmActionPendingIntend(){
        Intent intent=new Intent(this,AlarmOnActivity.class);

    }



}




