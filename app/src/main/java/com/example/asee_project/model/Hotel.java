package com.example.asee_project.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "hotel")
public class Hotel {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String ID;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "puntuacion")
    private String puntuacion;
    @ColumnInfo(name = "precio")
    private String precio;
    @ColumnInfo(name = "calle")
    private String calle;
    @ColumnInfo(name = "contacto")
    private String contacto;


    @Ignore
    public Hotel(String ID, String nombre, String rate, String precio, String calle, String contacto) {
        this.ID = ID;
        this.nombre = nombre;
        this.puntuacion = rate;
        this.precio = precio;
        this.calle = calle;
        this.contacto = contacto;
    }

    public Hotel() {
    }

    @NonNull
    public String getID() {
        return ID;
    }

    public void setID(@NonNull String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
}
