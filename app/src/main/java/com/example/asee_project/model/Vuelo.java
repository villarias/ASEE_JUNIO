package com.example.asee_project.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Entity(tableName = "vuelo")
public class Vuelo {
    @Ignore
    public static final String ITEM_SEP = System.getProperty("line.separator");
    @Ignore
    public final static String ID = "ID";
    @Ignore
    public final static String ORIGEN = "origen";
    @Ignore
    public final static String DESTINO = "destino";
    @Ignore
    public final static String LLEGADA = "llegada";
    @Ignore
    public final static String SALIDA = "salida";

    public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.US);
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long idVuelo;
    @ColumnInfo(name = "origen")
    private String origen;
    @ColumnInfo(name = "destino")
    private String destino;
    @ColumnInfo(name = "llegada")
    private String llegada;
    @ColumnInfo(name = "salida")
    private String salida;
    @Ignore
    public Vuelo(){

    }
    @Ignore
    public Vuelo(long idVuelo,  String origen, String destino, String salida, String llegada){
        this.idVuelo=idVuelo;
        this.origen=origen;
        this.destino=destino;
        this.salida=salida;
        this.llegada=llegada;
    }

    public Vuelo(  String origen, String destino, String salida, String llegada){
        this.origen=origen;
        this.destino=destino;
        this.salida=salida;
        this.llegada=llegada;
    }

//gets
    public String getSalida() { return salida; }

    public String getLlegada() { return llegada; }

    public String getOrigen() { return origen; }

    public String getDestino() { return destino; }


    @NonNull
    public Long getIdVuelo() { return idVuelo; }


//sets
    public void setSalida(String salida) { this.salida = salida; }

    public void setLlegada(String llegada) { this.llegada = llegada; }

    public void setOrigen(String origen) { this.origen = origen; }

    public void setDestino(String destino) { this.destino = destino; }

    public void setIdVuelo(@NonNull long idVuelo) { this.idVuelo = idVuelo; }

}
