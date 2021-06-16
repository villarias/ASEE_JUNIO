
package com.example.asee_project.modeloApiVuelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Equipment {

    @SerializedName("iata")
    @Expose
    private String iata;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("turboProp")
    @Expose
    private Boolean turboProp;
    @SerializedName("jet")
    @Expose
    private Boolean jet;
    @SerializedName("widebody")
    @Expose
    private Boolean widebody;
    @SerializedName("regional")
    @Expose
    private Boolean regional;

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTurboProp() {
        return turboProp;
    }

    public void setTurboProp(Boolean turboProp) {
        this.turboProp = turboProp;
    }

    public Boolean getJet() {
        return jet;
    }

    public void setJet(Boolean jet) {
        this.jet = jet;
    }

    public Boolean getWidebody() {
        return widebody;
    }

    public void setWidebody(Boolean widebody) {
        this.widebody = widebody;
    }

    public Boolean getRegional() {
        return regional;
    }

    public void setRegional(Boolean regional) {
        this.regional = regional;
    }

}
