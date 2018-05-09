package com.sebdeveloper6952.android_lab_room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "table_weather")
public class WeatherData
{
    @PrimaryKey
    private final int id;
    private String data;

    public WeatherData(int id, String data)
    {
        this.id = id;
        this.data = data;
    }

    public int getId() { return id; }
    public void setData(String data) { this.data = data; }
    public String getData() { return data; }
}
