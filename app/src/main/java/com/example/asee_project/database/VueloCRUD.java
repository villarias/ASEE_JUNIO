package com.example.asee_project.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asee_project.model.Vuelo;

import java.util.ArrayList;
import java.util.List;

public class VueloCRUD {
    private static VueloCRUD mInstance;
    private VueloCrudDBHelper mDbHelper;

    private VueloCRUD(Context context) {
        mDbHelper = new VueloCrudDBHelper(context);
    }

    public static VueloCRUD getInstance(Context context) {
        if (mInstance == null)
            mInstance = new VueloCRUD(context);

        return mInstance;
    }

    public List<Vuelo> getAll() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                DBContract.VueloItem._ID,
                DBContract.VueloItem.COLUMN_NAME_ORIGEN,
                DBContract.VueloItem.COLUMN_NAME_DESTINO,
                DBContract.VueloItem.COLUMN_NAME_LLEGADA,
                DBContract.VueloItem.COLUMN_NAME_SALIDA
        };

        String selection = null;
        String[] selectionArgs = null;

        String sortOrder = null;

        Cursor cursor = db.query(
                DBContract.VueloItem.TABLE_NAME,           // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        ArrayList<Vuelo> items = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                //items.add(getToDoItemFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    public long insert(Vuelo item) {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DBContract.VueloItem.COLUMN_NAME_ORIGEN, item.getOrigen());
        values.put(DBContract.VueloItem.COLUMN_NAME_DESTINO, item.getDestino());
        values.put(DBContract.VueloItem.COLUMN_NAME_LLEGADA, String.valueOf(item.getLlegada()));
        values.put(DBContract.VueloItem.COLUMN_NAME_SALIDA, String.valueOf(item.getSalida()));
        values.put(DBContract.VueloItem.COLUMN_NAME_FAVORITO,String.valueOf(item.getFavorito()));

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(DBContract.VueloItem.TABLE_NAME, null, values);
        return newRowId;
    }

    public void deleteAll() {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Define 'where' part of query.
        String selection = null;
        // Specify arguments in placeholder order.
        String[] selectionArgs = null;

        // Issue SQL statement.
        db.delete(DBContract.VueloItem.TABLE_NAME, selection, selectionArgs);
    }

    public void close() {
        if (mDbHelper != null) mDbHelper.close();
    }

    public static Vuelo getToDoItemFromCursor(Cursor cursor) {

        long   ID = cursor.getInt(cursor.getColumnIndex(DBContract.VueloItem._ID));
        String origen =  cursor.getString(cursor.getColumnIndex(DBContract.VueloItem.COLUMN_NAME_ORIGEN));
        String destino = cursor.getString(cursor.getColumnIndex(DBContract.VueloItem.COLUMN_NAME_DESTINO));
        String llegada = cursor.getString(cursor.getColumnIndex(DBContract.VueloItem.COLUMN_NAME_LLEGADA));
        String salida =  cursor.getString(cursor.getColumnIndex(DBContract.VueloItem.COLUMN_NAME_SALIDA));
        String favorito =  cursor.getString(cursor.getColumnIndex(DBContract.VueloItem.COLUMN_NAME_FAVORITO));

          Vuelo item = new Vuelo();
        return item;
    }

    public void delete(Vuelo vuelo){

    }

}
