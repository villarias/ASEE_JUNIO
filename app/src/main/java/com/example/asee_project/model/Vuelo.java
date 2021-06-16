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
    @Ignore
    public  final static String COD = "codigo";

    public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.US);
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long idVuelo;
    @ColumnInfo(name = "origen")
    private String origen;
    @ColumnInfo(name = "destino")
    private String destino;
    @ColumnInfo(name = "codOrigen")
    private String codOrigen;
    @ColumnInfo(name = "codDestino")
    private String codDestino;
    @ColumnInfo(name = "llegada")
    private String llegada;
    @ColumnInfo(name = "salida")
    private String salida;
    @ColumnInfo(name = "favorito")
    private int favorito;

    @Ignore
    public Vuelo(){

    }
    @Ignore
    public Vuelo(String origen, String destino, String salida, String llegada,int favorito){
        this.origen=origen;
        this.destino=destino;
        this.salida=salida;
        this.llegada=llegada;
        this.favorito=0;
    }
    @Ignore
    public Vuelo(String origen, String destino, String salida, String llegada){
        this.origen=origen;
        this.destino=destino;
        this.salida=salida;
        this.llegada=llegada;
        this.favorito=0;
    }

    public Vuelo(String codOrigen,String codDestino,String origen, String destino, String salida, String llegada){
        this.codOrigen = codOrigen;
        this.codDestino = codDestino;
        this.origen=origen;
        this.destino=destino;
        this.salida=salida;
        this.llegada=llegada;
        this.favorito=0;
    }

//gets
    public String getSalida() { return salida; }

    public String getLlegada() { return llegada; }

    public String getOrigen() { return origen; }

    public String getDestino() { return destino; }

    public int getFavorito(){ return  favorito; }

    public String getCodOrigen(){return codOrigen;}

    public String getCodDestino(){return codDestino;}




    @NonNull
    public Long getIdVuelo() { return idVuelo; }


//sets

    public void setSalida(String salida) { this.salida = salida; }

    public void setLlegada(String llegada) { this.llegada = llegada; }

    public void setOrigen(String origen) { this.origen = origen; }

    public void setDestino(String destino) { this.destino = destino; }

    public void setIdVuelo(@NonNull long idVuelo) { this.idVuelo = idVuelo; }

    public  void setFavorito(int fav){this.favorito = fav;}

    public void setCodOrigen(String codOrigen){this.codOrigen = codOrigen;}

    public void setCodDestino(String codDestino){this.codDestino = codDestino;}
}
