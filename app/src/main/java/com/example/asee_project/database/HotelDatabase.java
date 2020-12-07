package com.example.asee_project.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.asee_project.model.Hotel;


@Database(entities = {Hotel.class}, version = 1)
public abstract class HotelDatabase extends RoomDatabase {
    private static HotelDatabase instance;

    public static HotelDatabase getInstance(Context context){
        if (instance==null)
            instance= Room.databaseBuilder(context, HotelDatabase.class,"hotel.db").build();
        return instance;
    }

    public abstract HotelDao getDao();

}
