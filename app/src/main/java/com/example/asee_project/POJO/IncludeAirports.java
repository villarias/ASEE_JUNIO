
package com.example.asee_project.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IncludeAirports {

    @SerializedName("interpreted")
    @Expose
    private List<Object> interpreted = null;

    public List<Object> getInterpreted() {
        return interpreted;
    }

    public void setInterpreted(List<Object> interpreted) {
        this.interpreted = interpreted;
    }

}
