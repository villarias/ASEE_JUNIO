package com.example.asee_project;


import com.example.asee_project.modelo.Hotel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface HotelService {
   @GET("shopping/hotel-offers")
   Call<Hotel> listHoteles(@Header("Authorization") String Authorization, @Query("cityCode") String cityCode, @Query("checkInDate") String checkInDate, @Query("checkOutDate") String checkOutDate, @Query("adults") String adults);
}
