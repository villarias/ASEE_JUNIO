package com.example.asee_project.database;

import android.provider.BaseColumns;

public class DBContract {
    private DBContract() {}

    public static class VueloItem implements BaseColumns {
        public static final String TABLE_NAME = "vuelo";
        public static final String COLUMN_NAME_ORIGEN = "origen";
        public static final String COLUMN_NAME_DESTINO = "destino";
        public static final String COLUMN_NAME_LLEGADA = "llegada";
        public static final String COLUMN_NAME_SALIDA = "salida";


    }

}
