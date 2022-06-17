package com.example.felixalarm.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.felixalarm.entities.Alarm;
import com.example.felixalarm.entities.Note;

import java.util.List;

@Dao
public interface AlarmDao {
    @Delete
    void deleteAlarm(Alarm alarm);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlarm(Alarm alarm);

    @Query("SELECT * FROM alarms ORDER BY id ASC")
    List<Alarm> getAllAlarms();


}
