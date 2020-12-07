package com.example.asee_project;

import com.example.asee_project.POJO.ResponseRoute;

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
    final String appKey="01e0e7ff9159624edbfeea4bb53da807";
    final String appID="8d075311";
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
        // Instanciación de Retrofit y llamada síncrona

        try {
            ResponseRoute route = service.listVuelosDay(Origen,Destino,Anio,Mes,Dia,appID,appKey).execute().body();
            AppExecutors.getInstance().mainThread().execute(()-> {
                mLoadedListener.onFlightsRouteLoaded(route);
            });
        }catch (IOException e){
            e.printStackTrace();
        }
/*
        try {
            ResponseFlight vuelos = service.listVuelos("MAD","BCN","2020","11","22","10","10",appID,appKey,"6","2",false,"passenger",true,true,25).execute().body();
            AppExecutors.getInstance().mainThread().execute(()-> {
                mLoadedListener.onFlightsLoaded(vuelos);
            });
        }catch (IOException e){
            e.printStackTrace();
        }
*/

        // Llamada al Listener con los datos obtenidos
    }

}
