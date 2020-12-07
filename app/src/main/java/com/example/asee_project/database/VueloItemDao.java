package com.example.asee_project.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.asee_project.model.Vuelo;

import java.util.List;

@Dao
public interface VueloItemDao {
    @Query("SELECT * FROM vuelo")
    public List<Vuelo> getAll();

    @Insert
    public long insert(Vuelo vuelo);

    @Query("DELETE FROM vuelo")
    public void deleteAll();

    @Query("DELETE FROM vuelo WHERE salida = :salida")
    public void delete(String salida);

    @Query("UPDATE vuelo SET origen = :origen, destino = :destino, salida = :salida,llegada = :llegada WHERE salida = :salidaAntigua")
    public void update(String origen, String destino, String salida, String llegada, String salidaAntigua);

}
