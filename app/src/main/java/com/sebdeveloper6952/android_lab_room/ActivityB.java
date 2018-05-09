package com.sebdeveloper6952.android_lab_room;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ActivityB extends AppCompatActivity
{
    protected AppDatabase db;

    protected TextView txtVAllData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_activity_b);
        txtVAllData = findViewById(R.id.txtV_allData);
        db = AppDatabase.getDatabase(ActivityB.this);
        List<WeatherData> list = db.weatherDao().getWeatherDataById(1);
        WeatherData data = null;
        if(!list.isEmpty()) data = list.get(0);
        if(data != null) txtVAllData.setText(data.getData());
        else txtVAllData.setText(R.string.data_not_found);
    }
}
