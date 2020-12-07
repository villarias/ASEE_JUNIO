
package com.example.asee_project.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaxConnections {

    @SerializedName("requested")
    @Expose
    private String requested;
    @SerializedName("interpreted")
    @Expose
    private Integer interpreted;

    public String getRequested() {
        return requested;
    }

    public void setRequested(String requested) {
        this.requested = requested;
    }

    public Integer getInterpreted() {
        return interpreted;
    }

    public void setInterpreted(Integer interpreted) {
        this.interpreted = interpreted;
    }

}