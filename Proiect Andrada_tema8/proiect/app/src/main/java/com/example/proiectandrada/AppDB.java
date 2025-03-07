package com.example.proiectandrada;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Review.class, Entry.class}, version=1, exportSchema = false)
public abstract class AppDB extends RoomDatabase{
    private static AppDB instance;
    public static final String databaseName = "appDB.db";

    public static AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDB.class, databaseName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract ReviewDAO getReviewDAO();
    public abstract EntryDAO getEntryDAO();
}
