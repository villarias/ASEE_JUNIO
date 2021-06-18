package com.example.asee_project;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.asee_project.model.Ciudad;
import com.example.asee_project.model.Vuelo;

import java.util.List;

public class ViewModelHome extends ViewModel {
    private final VueloRepository mRepository;
    private final LiveData<List<Vuelo>> VuelosHome;

    public ViewModelHome(VueloRepository mRepository) {
        this.mRepository = mRepository;
        this.VuelosHome = mRepository.getCurrentVuelosFavs();
    }

    public  void setCiudad(Ciudad ciudad){
        mRepository.setCiudadFav(ciudad);
    }

    public LiveData<List<Vuelo>> getVuelosFavs() {
        return VuelosHome;
    }

}