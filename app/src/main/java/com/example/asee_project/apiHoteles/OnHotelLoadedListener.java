package com.example.asee_project.apiHoteles;

import com.example.asee_project.model.HotelDB;

import java.util.List;

public interface OnHotelLoadedListener {
    public void onHotelLoaded(List<HotelDB> hoteles);
}
