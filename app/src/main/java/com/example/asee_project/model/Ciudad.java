package com.example.asee_project.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Blob;


@Entity(tableName = "Ciudades")
public class Ciudad
{

    @PrimaryKey
    @ColumnInfo(name = "cod_ciudad")
    @NonNull private String cod_ciudad;

    @ColumnInfo(name = "nombre")
    private String nombre;

    @ColumnInfo(name = "favorito")
    private String favorite;

    @ColumnInfo(name = "image")
    private int image;


    public Ciudad(String cod_ciudad, String nombre) {
        this.cod_ciudad = cod_ciudad;
        this.nombre = nombre;
        this.favorite = "0";
    }
    public Ciudad(String cod_ciudad, String nombre,String favorito) {
        this.cod_ciudad = cod_ciudad;
        this.nombre = nombre;
        this.favorite = favorito;
    }
    public Ciudad(String cod_ciudad, String nombre,String favorito,int image) {
        this.cod_ciudad = cod_ciudad;
        this.nombre = nombre;
        this.favorite = "0";
        this.image = image;
    }

    public Ciudad(Ciudad c){
        this.cod_ciudad = c.cod_ciudad;
        this.nombre = c.nombre;
        this.favorite = c.favorite;
        this.image = c.image;
    }

    // ####################
    // GETTERS AND SETTERS
    public String getCod_ciudad() {
        return cod_ciudad;
    }

    public void setCod_ciudad(String cod_ciudad) {
        this.cod_ciudad = cod_ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFavorite() {     return favorite;   }

    public void setFavorite(String favorite) { this.favorite = favorite;  }

    public int getImage() {     return image;   }

    public void setImage(int image) { this.image = image;  }


}