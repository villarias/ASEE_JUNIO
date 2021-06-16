
package com.example.asee_project.modeloApiVuelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Operator {

    @SerializedName("carrierFsCode")
    @Expose
    private String carrierFsCode;
    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("serviceType")
    @Expose
    private String serviceType;
    @SerializedName("serviceClasses")
    @Expose
    private List<String> serviceClasses = null;
    @SerializedName("trafficRestrictions")
    @Expose
    private List<Object> trafficRestrictions = null;

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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<String> getServiceClasses() {
        return serviceClasses;
    }

    public void setServiceClasses(List<String> serviceClasses) {
        this.serviceClasses = serviceClasses;
    }

    public List<Object> getTrafficRestrictions() {
        return trafficRestrictions;
    }

    public void setTrafficRestrictions(List<Object> trafficRestrictions) {
        this.trafficRestrictions = trafficRestrictions;
    }

}
