package com.example.asee_project.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.asee_project.model.Ciudad;

@Database(entities = {Ciudad.class}, version = 6,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    //Singlenton
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class,"ciudad.db").build();
        }
        return instance;
    }


    public abstract CiudadDao getCiudadDao();
}