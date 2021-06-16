package com.example.asee_project.apiVuelos;

import com.example.asee_project.modeloApiVuelos.ResponseRoute;

public interface onFlightsLoadedListener {
    //public void onFlightsLoaded(ResponseFlight vuelos);
    public void onFlightsRouteLoaded(ResponseRoute ruta);
}
