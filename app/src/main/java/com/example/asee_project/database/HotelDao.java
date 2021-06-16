package com.example.asee_project.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.asee_project.model.HotelDB;

import java.util.List;

;import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface HotelDao {

    @Query("SELECT * FROM hotel WHERE favorito LIKE 1")
    public List<HotelDB> getAll();
    @Insert
    public long insert(HotelDB hotel);
    @Delete
    public void delete(HotelDB hotel);
    @Update
    public int update(HotelDB hotel);
    @Query("SELECT * FROM hotel WHERE  fechaini LIKE :fechaini AND fechafin LIKE :fechafin AND numpersonas LIKE :numPersonas AND favorito LIKE 1 AND nombre = :nombre AND calle = :calle")
    public HotelDB getHotel(String fechaini, String fechafin, String numPersonas, String nombre, String calle);


    @Insert(onConflict = REPLACE)
    void bulkInsert(List<HotelDB> repo);

    @Query("SELECT * FROM hotel WHERE fechaini LIKE :fechaini AND fechafin LIKE :fechafin AND numpersonas LIKE :numPersonas AND favorito LIKE 0 AND ciudad = :ciudad AND fechaini >= date('now')")
    LiveData<List<HotelDB>> getHotelBySearch(String fechaini,String fechafin, String numPersonas, String ciudad);

    @Query("DELETE FROM hotel WHERE ciudad = :ciudad AND favorito = 0")
    int deleteHotelBySearch(String ciudad);

    @Query("SELECT count(*) FROM hotel WHERE fechaini LIKE :fechaini AND fechafin LIKE :fechafin AND numpersonas LIKE :numPersonas AND favorito LIKE 0 AND ciudad = :ciudad")
    int getNumberHotel(String fechaini,String fechafin, String numPersonas, String ciudad);


}
