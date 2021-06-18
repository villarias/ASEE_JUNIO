package com.example.asee_project;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FactoryHome  extends ViewModelProvider.NewInstanceFactory {


    private final VueloRepository mRepository;

    public FactoryHome (VueloRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class <T> modelClass) {
        //noinspection unchecked
        return (T) new ViewModelHome(mRepository);
    }
}