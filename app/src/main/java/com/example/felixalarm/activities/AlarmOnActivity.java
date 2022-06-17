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

public class AlarmOnActivity extends AppCompatActivity {
    Ringtone ringtone;
    private ImageView buttonAlarmOff;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_on);
        buttonAlarmOff=findViewById(R.id.button_alarm_off);

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
