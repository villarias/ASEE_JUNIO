package com.example.asee_project.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.asee_project.model.Ciudad;
import com.example.asee_project.model.Vuelo;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface VueloItemDao {
    @Query("SELECT * FROM vuelo WHERE favorito =1")
    public List<Vuelo> getAll();

    @Query("SELECT * FROM vuelo WHERE favorito =0")
    public List<Vuelo> getCache();

    @Insert
    public long insert(Vuelo vuelo);

    @Query("DELETE FROM vuelo")
    public void deleteAll();

    @Query("INSERT INTO vuelo(origen,destino,salida,llegada,favorito)  VALUES(:origen, :destino,:salida,:llegada,:fav)")
    public void fav(String origen, String destino, String salida, String llegada, int fav);

    @Query("DELETE FROM vuelo WHERE salida = :salida")
    public void delete(String salida);

    @Query("UPDATE vuelo SET origen = :origen, destino = :destino, salida = :salida,llegada = :llegada WHERE salida = :salidaAntigua")
    public void update(String origen, String destino, String salida, String llegada, String salidaAntigua);

    @Insert(onConflict = REPLACE)
    void bulkInsert(List<Vuelo> vuelos);

    @Query("SELECT * FROM vuelo WHERE origen = :origenIn AND destino = :destinoIn AND favorito =0" )
    LiveData<List<Vuelo>> getVueloBySearch(String origenIn,String destinoIn);

    @Query("DELETE FROM vuelo WHERE codOrigen = :origenIn AND codDestino = :destinoIn AND favorito =0")
    int deleteVueloBySearch(String origenIn,String destinoIn);

    @Query("SELECT count(*) FROM vuelo WHERE salida = :salidaIn AND origen = :origenIn AND  favorito = 0")
    int getNumberVuelo(String salidaIn,String origenIn);

    @Query("SELECT * FROM vuelo WHERE codOrigen = 'MAD' AND codDestino = :cod AND favorito =0")
    public LiveData<List<Vuelo>> getVuelosFavs(String cod);

    @Query("DELETE FROM vuelo WHERE favorito =0")
    void deleteCache();
}
