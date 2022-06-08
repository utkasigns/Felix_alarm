package com.example.felixalarm.activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.felixalarm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class CreateAlarmActivity extends AppCompatActivity {
    TextView timer1,timer2;
    int hour1,minute1;
    int hour2,minute2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);
        timer1 =findViewById(R.id.timer1);
        timer2=findViewById(R.id.timer2);

        timer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //инициализируем таймпикердайлог
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        CreateAlarmActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                                //инициализируем часы и минуты
                                hour1 = hourOfDay;
                                minute1 = minute;
                                //инициализируем календарь
                                Calendar calendar = Calendar.getInstance();
                                //задаем часы и минуты
                                calendar.set(0, 0, 0, hour1, minute1);
                                //задаем выбранное время в текствью
                                timer1.setText(DateFormat.format("hh:mm aa", calendar));
                            }
                        }, 12, 0, false
                );
                //отображаем предыдущее выбранное время
                timePickerDialog.updateTime(hour1, minute1);
                //показать диалог
                timePickerDialog.show();
            }
        });
        timer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog =new TimePickerDialog(
                        CreateAlarmActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                hour2=hourOfDay;
                                minute2=minute;
                                //вносим часы и минуты в стрингу
                                String time= hour2+":"+minute2;
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
                                    timer2.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, 12,0,false
                );
            // set transparent background транспарент вроде прозрачный
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //отображаем предыдущее выбранное время
                timePickerDialog.updateTime(hour2, minute2);
                timePickerDialog.show();




            }
        });



//    public void popTimePicker(View view)
//    {
//        TimePickerDialog.OnTimeSetListener OnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
//
//            @Override
//            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
//            {
//                hour=selectedHour;
//                minute=selectedMinute;
//                timeButton.setText(String.format(Locale.getDefault(),"%02d.%02d",hour,minute));
//            }
//        };
//        int style= AlertDialog.THEME_HOLO_DARK;
//
//        TimePickerDialog TimePickerDialog = new TimePickerDialog(this,style, OnTimeSetListener,hour, minute,true);
//    }
}}




