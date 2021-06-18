package com.example.asee_project.apiVuelos;

import com.example.asee_project.AppExecutors;
import com.example.asee_project.modeloApiVuelos.ResponseRoute;

import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlightsNetworkLoaderRunnable implements Runnable{


    final onFlightsLoadedListener mLoadedListener;
    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.flightstats.com/flex/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIInterface service = retrofit.create(APIInterface.class);
    final String appKey="7ec88c3315a4b1cd2bfcae1dac52cb3f";
    final String appID="09f5a7d1";
    String Origen;
    String Destino;
    String Mes;
    String  Anio;
    String Dia;

    public FlightsNetworkLoaderRunnable(onFlightsLoadedListener LoadedListener, String origen, String destino, String dia, String mes, String anio){
        mLoadedListener = LoadedListener;
        Origen  = origen;
        Destino = destino;
        Dia = dia;
        Mes = mes;
        Anio = anio;
    }

    @Override
    public void run() {
        try {
            ResponseRoute route = service.listVuelosDay(Origen,Destino,Anio,Mes,Dia,appID,appKey).execute().body();
            AppExecutors.getInstance().mainThread().execute(()-> {
                mLoadedListener.onFlightsRouteLoaded(route);
            });
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
