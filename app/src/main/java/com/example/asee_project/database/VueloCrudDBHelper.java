package com.example.asee_project.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VueloCrudDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "vuelos.db";


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_VUELOS =
            "CREATE TABLE " + DBContract.VueloItem.TABLE_NAME + " (" +
                    DBContract.VueloItem._ID + " INTEGER PRIMARY KEY," +
                    DBContract.VueloItem.COLUMN_NAME_ORIGEN + TEXT_TYPE + COMMA_SEP +
                    DBContract.VueloItem.COLUMN_NAME_DESTINO + TEXT_TYPE + COMMA_SEP +
                    DBContract.VueloItem.COLUMN_NAME_LLEGADA + TEXT_TYPE + COMMA_SEP +
                    DBContract.VueloItem.COLUMN_NAME_SALIDA + TEXT_TYPE +
                    " )";

    private static final String SQL_DELETE_VUELOS =
            "DROP TABLE IF EXISTS " + DBContract.VueloItem.TABLE_NAME;


    public VueloCrudDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_VUELOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_VUELOS);
        onCreate(db);
    }


}
