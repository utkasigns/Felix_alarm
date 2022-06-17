package com.example.felixalarm.entities;

import android.text.Editable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.felixalarm.activities.AlarmListApplication;

import java.io.Serializable;
@Entity(tableName = "alarms")

public class Alarm implements Serializable {
    AlarmListApplication alarmListApplication;


    @PrimaryKey(autoGenerate = true)
    private int id;

    public Alarm(int nextId, String toString, String toString1) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ColumnInfo(name = "name")
    private String name;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @ColumnInfo(name = "time")
    private String time  ;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
