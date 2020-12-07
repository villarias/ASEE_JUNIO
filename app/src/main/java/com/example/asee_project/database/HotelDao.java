package com.example.asee_project.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.asee_project.model.Hotel;

import java.util.List;

;

@Dao
public interface HotelDao {

    @Query("SELECT * FROM hotel")
    public List<Hotel> getAll();
    @Insert
    public long insert(Hotel hotel);
    @Delete
    public void delete(Hotel hotel);
    @Update
    public int update(Hotel hotel);
    @Query("SELECT ID FROM hotel WHERE ID LIKE :id_hotel")
    public String getHotel(String id_hotel);

}
