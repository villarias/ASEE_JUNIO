package com.example.asee_project.apiVuelos;

import com.example.asee_project.modeloApiVuelos.ResponseFlight;
import com.example.asee_project.modeloApiVuelos.ResponseRoute;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("connections/rest/v2/json/firstflightin/{ciudadSalida}/to/{ciudadLlegada}/arriving_before/{year}/{month}/{day}/{hour}/{minute}")
    Call<ResponseFlight> listVuelos(@Path("ciudadSalida") String ciudadSalida, @Path("ciudadLlegada") String ciudadLlegada, @Path("year") String year, @Path("month") String month, @Path("day") String day, @Path("hour") String hour, @Path("minute") String minute, @Query("appId") String appID, @Query("appKey") String appKey, @Query("numHours") String numHours, @Query("maxConnections") String maxConnections, @Query("includeSurface") boolean includeSurface, @Query("payloadType") String payload, @Query("includeCodeshares") boolean codeShares, @Query("includeMultipleCarriers") boolean MultipleCarriers, @Query("maxResults") int maxResults);

    @GET("schedules/rest/v1/json/from/{ciudadSalida}/to/{ciudadLlegada}/arriving/{year}/{month}/{day}")
    Call<ResponseRoute> listVuelosDay(@Path("ciudadSalida") String ciudadSalida, @Path("ciudadLlegada") String ciudadLlegada, @Path("year") String year, @Path("month") String month, @Path("day") String day, @Query("appId") String appID, @Query("appKey") String appKey);
}
