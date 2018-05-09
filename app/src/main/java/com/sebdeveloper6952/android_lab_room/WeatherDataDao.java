package com.sebdeveloper6952.android_lab_room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WeatherDataDao
{
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insertWeatherData(WeatherData data);

    @Query("select * from table_weather where id = :weatherId")
    List<WeatherData> getWeatherDataById(int weatherId);
}
