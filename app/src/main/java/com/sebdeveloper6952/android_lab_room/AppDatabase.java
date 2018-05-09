package com.sebdeveloper6952.android_lab_room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {WeatherData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract WeatherDataDao weatherDao();

    public static AppDatabase getDatabase(Context c) {
        if (instance == null) instance = Room.databaseBuilder(c, AppDatabase.class,
                "table_weather").allowMainThreadQueries().build();
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }
}
