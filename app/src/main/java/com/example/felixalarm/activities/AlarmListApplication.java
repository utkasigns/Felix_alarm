package com.example.felixalarm.activities;

import android.app.Application;
//
//import com.example.felixalarm..Alarm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlarmListApplication extends Application {
    public static int nextId=0;
    public static List<Alarm> alarmList= new ArrayList<>();
//    public AlarmListApplication(){
//        fillAlarmList();
//    }

//    private void fillAlarmList() {
//        Alarm a1=new Alarm(1,"aaa","12:12");
//        Alarm a2=new Alarm(2,"ooo","13:12");
//        alarmList.addAll(Arrays.asList(new Alarm[]{a1,a2}));
//    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        AlarmListApplication.nextId = nextId;
    }

    public static List<Alarm> getAlarmList() {
        return alarmList;
    }

    public static void setAlarmList(List<Alarm> alarmList) {
        AlarmListApplication.alarmList = alarmList;
    }
}
