package com.example.asee_project;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.asee_project.modelo.Hotel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotelRunnable implements Runnable {

    private OnHotelLoadedListener listener;
    private String authorization;
    private String cityCode;
    private String fechaIni;
    private String fechaFin;
    private String numPersonas;
    private ProgressBar p;

    public HotelRunnable(String authorization, String cityCode, String fechaIni, String fechaFin, String numPersonas, ProgressBar p, OnHotelLoadedListener listener) {
        this.listener = listener;
        this.authorization = authorization;
        this.cityCode = cityCode;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.numPersonas = numPersonas;
        this.p=p;
    }



    @Override
    public void run() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://test.api.amadeus.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HotelService service = retrofit.create(HotelService.class);
        try {
            Hotel hoteles= service.listHoteles(authorization,cityCode,fechaIni,fechaFin,numPersonas).execute().body();
            Log.i("FlyScan Abel: ",service.listHoteles(authorization,cityCode,fechaIni,fechaFin,numPersonas).execute().raw().toString());
            AppExecutors.getInstance().mainThread().execute(new Runnable() {
                @Override
                public void run() {
                        p.setVisibility(View.INVISIBLE);
                        listener.onHotelLoaded(hoteles);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
