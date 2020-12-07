package com.example.asee_project.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.asee_project.model.Vuelo;

@Database(entities = {Vuelo.class}, version = 1)
public abstract class VueloDataBase extends RoomDatabase {
    private static VueloDataBase instance;

    public static VueloDataBase getInstance(Context context){
        if(instance == null){
            instance =  Room.databaseBuilder(context, VueloDataBase.class, "ToDoDatabase").build();
        }
        return instance;
    }
    public  abstract VueloItemDao getDao();
}
