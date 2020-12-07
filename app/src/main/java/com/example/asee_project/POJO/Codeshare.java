
package com.example.asee_project.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Codeshare {

    @SerializedName("carrierFsCode")
    @Expose
    private String carrierFsCode;
    @SerializedName("serviceType")
    @Expose
    private String serviceType;
    @SerializedName("trafficRestrictions")
    @Expose
    private List<Object> trafficRestrictions = null;

    public String getCarrierFsCode() {
        return carrierFsCode;
    }

    public void setCarrierFsCode(String carrierFsCode) {
        this.carrierFsCode = carrierFsCode;
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

}
