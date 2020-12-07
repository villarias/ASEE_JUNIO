
package com.example.asee_project.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Appendix {

    @SerializedName("airlines")
    @Expose
    private List<Airline> airlines = null;
    @SerializedName("airports")
    @Expose
    private List<Airport> airports = null;
    @SerializedName("equipments")
    @Expose
    private List<Equipment> equipments = null;

    public List<Airline> getAirlines() {
        return airlines;
    }

    public void setAirlines(List<Airline> airlines) {
        this.airlines = airlines;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

}
