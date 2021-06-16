package com.example.asee_project.apiHoteles;

import android.widget.ProgressBar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.asee_project.AppExecutors;
import com.example.asee_project.model.HotelDB;
import com.example.asee_project.modeloApiHoteles.Token;

import java.util.List;

public class HotelNetworkDataSource {

    private static HotelNetworkDataSource sInstance;

    // LiveData storing the latest downloaded weather forecasts
    private final MutableLiveData<HotelDB[]> mDownloadedHotel;
    private Token k;

    private HotelNetworkDataSource() {
        mDownloadedHotel = new MutableLiveData<>();
    }

    public synchronized static HotelNetworkDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new HotelNetworkDataSource();

        }
        return sInstance;
    }


    private void loadToken(String fecha_ini, String fecha_fin, String numPersonas, String ciudad ,ProgressBar p){
        AppExecutors.getInstance().networkIO().execute(new TokenRunnable(new OnTokenLoadedListener() {
            @Override
            public void onTokenLoaded(Token token) {
                k=token;
                AppExecutors.getInstance().networkIO().execute(new HotelRunnable(k.getTokenType() + " " + k.getAccessToken(), ciudad, fecha_ini, fecha_fin, numPersonas, p, new OnHotelLoadedListener() {
                    @Override
                    public void onHotelLoaded(List<HotelDB> hoteles) {

                        mDownloadedHotel.postValue(hoteles.toArray(new HotelDB[0]));
                    }
                }));
            }
        }));
    }

    public LiveData<HotelDB[]> getCurrentRepos() {
        return mDownloadedHotel;
    }

    /**
     * Gets the newest repos
     */
    public void fetchHotel(String fecha_ini, String fecha_fin, String numPersonas, String ciudad ,ProgressBar p) {
        loadToken( fecha_ini, fecha_fin, numPersonas,ciudad,p);
        // Get gata from network and pass it to LiveData

    }



}