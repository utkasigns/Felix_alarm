package com.example.felixalarm.activities;

import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.felixalarm.R;

import pl.droidsonroids.gif.GifImageView;

public class AlarmOnActivity extends AppCompatActivity {
    Ringtone ringtone;
    GifImageView gifBack;
    private ImageView buttonAlarmOff;
    TextView textTime;
    int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_on);
        buttonAlarmOff=findViewById(R.id.button_alarm_off);
        textTime = findViewById(R.id.textView2);
        gifBack = findViewById(R.id.gifBackOnAlarm);


        Intent intent = getIntent();
        String description = intent.getStringExtra("description");
        boolean isThemeChanged = intent.getBooleanExtra("theme", false);

        if (isThemeChanged && description != null) {

            if (isThemeChanged == false) {
                gifBack.setAlpha(0.35F);
                switch (description) {
                    case "clear sky":
                        gifBack.setImageResource(R.drawable.sun_clouds);
                        buttonAlarmOff.setImageResource(R.drawable.button_sun);
                        break;
                    case "few clouds": //серые тучки, но с солнышком кек
                        gifBack.setImageResource(R.drawable.sky_clouds);
                        buttonAlarmOff.setImageResource(R.drawable.button_clouds);
                        break;
                    case "scattered clouds":  //серые тучки без грозы(без черых тучек)
                    case "broken clouds":
                        gifBack.setImageResource(R.drawable.half_gray_clouds);
                        buttonAlarmOff.setImageResource(R.drawable.button_clouds);
                        //текстура half gray clouds кривая
                        break;
                    case "overcast clouds": //с черными тучками!
                        gifBack.setImageResource(R.drawable.gray_clouds);//overcast n broker have same icon
                        buttonAlarmOff.setImageResource(R.drawable.button_clouds);
                        break;
                    case "light rain": //слабенький дождь
                        gifBack.setImageResource(R.drawable.raindrops);
                        buttonAlarmOff.setImageResource(R.drawable.button_rain);
                        break;
                    case "moderate rain": //умеренный дождь
                        gifBack.setImageResource(R.drawable.hard_rain);
                        buttonAlarmOff.setImageResource(R.drawable.button_rain);
                        break;
                }
            } else{
                gifBack.setAlpha(0.9F);
                switch (description) {
                    case "clear sky":
                        gifBack.setImageResource(R.drawable.sunny_w);
                        buttonAlarmOff.setImageResource(R.drawable.button_sun);
                        break;
                    case "few clouds": //серые тучки, но с солнышком кек
                    case "scattered clouds":  //серые тучки без грозы(без черых тучек)
                    case "broken clouds":
                    case "overcast clouds": //с черными тучками!
                        gifBack.setImageResource(R.drawable.cloudy_w);//overcast n broker have same icon
                        buttonAlarmOff.setImageResource(R.drawable.button_clouds);
                        break;
                    case "light rain": //слабенький дождь
                    case "moderate rain": //умеренный дождь
                        gifBack.setImageResource(R.drawable.snowy_w); //потом изменим на rainy(когда Аня нарисует кек)
                        buttonAlarmOff.setImageResource(R.drawable.button_rain);
                        break;
                }

            }
        } else {
            gifBack.setImageResource(R.drawable.bachground_create_alarm);
            textTime.setTextColor(Color.BLACK);

        }



        //устанавливаем базовый рингтон
        Uri notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone =RingtoneManager.getRingtone(this,notificationUri);
        //если базовый рингтон не установлен
        if (ringtone==null){
            notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            ringtone =RingtoneManager.getRingtone(this,notificationUri);
        }
        if (ringtone!= null){
            ringtone.play();
        }

            buttonAlarmOff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    count += 1;

                    if (count < 10) {

                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_translate);
                        buttonAlarmOff.startAnimation(anim);
//                        buttonAlarmOff.

                    } else {
                        ringtone.stop();
                        Intent i= new Intent(AlarmOnActivity.this,AlarmActivity.class);
                        startActivity(i);

                    }
//
//                    Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_translate);
//                    buttonAlarmOff.startAnimation(anim);


//                    int count = 0;
////
//                    do {
//                        count++;
//                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_translate);
//                        buttonAlarmOff.startAnimation(anim);
//
//                    } while (count == 10);
//
//                    if (count == 10) {
//                        ringtone.stop();
//                        Intent i = new Intent(AlarmOnActivity.this, AlarmActivity.class);
//                        startActivity(i);
//                    }


//                if (count < 5) {
//                    count++;
//
////                    float fromXdelta = (float) Math.random();
////                    float toXdelta = (float) Math.random();
////                    float fromYdelta = (float) Math.random();
////                    float toYdelta = (float) Math.random();
////
////                    TranslateAnimation animation = new TranslateAnimation(fromXdelta,toXdelta,fromYdelta,toYdelta);
////                    buttonAlarmOff.startAnimation(animation);
//
//                    Animation anim=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_translate);
//                    buttonAlarmOff.startAnimation(anim);
//
//                } else {
//                    ringtone.stop();
//                    Intent i= new Intent(AlarmOnActivity.this,AlarmActivity.class);
//                    startActivity(i);
//                }
                }
            });

//        } while (count==10);

//        ringtone.stop();
//        Intent i= new Intent(AlarmOnActivity.this,AlarmActivity.class);
//        startActivity(i);



    }
}
