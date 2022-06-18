package com.example.felixalarm.activities;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.felixalarm.R;

import pl.droidsonroids.gif.GifImageView;

public class AlarmOnActivity extends AppCompatActivity {
    Ringtone ringtone;
    GifImageView gifBack;
    private ImageView buttonAlarmOff;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_on);
        buttonAlarmOff=findViewById(R.id.button_alarm_off);
        gifBack = findViewById(R.id.gifBackOnAlarm);


        Intent intent = getIntent();
        String description = intent.getStringExtra("description");
        boolean isThemeChanged = intent.getBooleanExtra("theme", false);

        if (isThemeChanged == false) {
            gifBack.setAlpha(0.35F);
            switch (description) {
                case "clear sky":
                    gifBack.setImageResource(R.drawable.sun_clouds);
                    break;
                case "few clouds": //серые тучки, но с солнышком кек
                    gifBack.setImageResource(R.drawable.sky_clouds);
                    break;
                case "scattered clouds":  //серые тучки без грозы(без черых тучек)
                case "broken clouds":
                    gifBack.setImageResource(R.drawable.half_gray_clouds); //текстура half gray clouds кривая
                    break;
                case "overcast clouds": //с черными тучками!
                    gifBack.setImageResource(R.drawable.gray_clouds);//overcast n broker have same icon
                    break;
                case "light rain": //слабенький дождь
                    gifBack.setImageResource(R.drawable.raindrops);
                    break;
                case "moderate rain": //умеренный дождь
                    gifBack.setImageResource(R.drawable.hard_rain);
                    break;
            }
        } else{
            gifBack.setAlpha(0.9F);
            switch (description) {
                case "clear sky":
                    gifBack.setImageResource(R.drawable.sunny_w);
                    break;
                case "few clouds": //серые тучки, но с солнышком кек
                case "scattered clouds":  //серые тучки без грозы(без черых тучек)
                case "broken clouds":
                case "overcast clouds": //с черными тучками!
                    gifBack.setImageResource(R.drawable.cloudy_w);//overcast n broker have same icon
                    break;
                case "light rain": //слабенький дождь
                case "moderate rain": //умеренный дождь
                    gifBack.setImageResource(R.drawable.snowy_w); //потом изменим на rainy(когда Аня нарисует кек)
                    break;
            }

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
                ringtone.stop();
                Intent i= new Intent(AlarmOnActivity.this,AlarmActivity.class);
                startActivity(i);

                ;

            }
        });



    }
}
