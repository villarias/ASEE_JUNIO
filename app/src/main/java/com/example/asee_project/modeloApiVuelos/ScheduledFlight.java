
package com.example.asee_project.modeloApiVuelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduledFlight {

    @SerializedName("carrierFsCode")
    @Expose
    private String carrierFsCode;
    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("departureAirportFsCode")
    @Expose
    private String departureAirportFsCode;
    @SerializedName("arrivalAirportFsCode")
    @Expose
    private String arrivalAirportFsCode;
    @SerializedName("stops")
    @Expose
    private Integer stops;
    @SerializedName("departureTime")
    @Expose
    private String departureTime;
    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("flightEquipmentIataCode")
    @Expose
    private String flightEquipmentIataCode;
    @SerializedName("isCodeshare")
    @Expose
    private Boolean isCodeshare;
    @SerializedName("isWetlease")
    @Expose
    private Boolean isWetlease;
    @SerializedName("serviceType")
    @Expose
    private String serviceType;
    @SerializedName("trafficRestrictions")
    @Expose
    private List<Object> trafficRestrictions = null;
    @SerializedName("elapsedTime")
    @Expose
    private Integer elapsedTime;
    @SerializedName("codeshares")
    @Expose
    private List<Codeshare> codeshares = null;

    public String getCarrierFsCode() {
        return carrierFsCode;
    }

    public void setCarrierFsCode(String carrierFsCode) {
        this.carrierFsCode = carrierFsCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirportFsCode() {
        return departureAirportFsCode;
    }

    public void setDepartureAirportFsCode(String departureAirportFsCode) {
        this.departureAirportFsCode = departureAirportFsCode;
    }

    public String getArrivalAirportFsCode() {
        return arrivalAirportFsCode;
    }

    public void setArrivalAirportFsCode(String arrivalAirportFsCode) {
        this.arrivalAirportFsCode = arrivalAirportFsCode;
    }

    public Integer getStops() {
        return stops;
    }

    public void setStops(Integer stops) {
        this.stops = stops;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFlightEquipmentIataCode() {
        return flightEquipmentIataCode;
    }

    public void setFlightEquipmentIataCode(String flightEquipmentIataCode) {
        this.flightEquipmentIataCode = flightEquipmentIataCode;
    }

    public Boolean getIsCodeshare() {
        return isCodeshare;
    }

    public void setIsCodeshare(Boolean isCodeshare) {
        this.isCodeshare = isCodeshare;
    }

    public Boolean getIsWetlease() {
        return isWetlease;
    }

    public void setIsWetlease(Boolean isWetlease) {
        this.isWetlease = isWetlease;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<Object> getTrafficRestrictions() {
        return trafficRestrictions;
    }

    public void setTrafficRestrictions(List<Object> trafficRestrictions) {
        this.trafficRestrictions = trafficRestrictions;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<Codeshare> getCodeshares() {
        return codeshares;
    }

    public void setCodeshares(List<Codeshare> codeshares) {
        this.codeshares = codeshares;
    }

}
