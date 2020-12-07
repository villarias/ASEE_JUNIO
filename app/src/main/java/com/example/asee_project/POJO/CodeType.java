
package com.example.asee_project.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CodeType {

    @SerializedName("requested")
    @Expose
    private String requested;
    @SerializedName("interpreted")
    @Expose
    private String interpreted;

    public String getRequested() {
        return requested;
    }

    public void setRequested(String requested) {
        this.requested = requested;
    }

    public String getInterpreted() {
        return interpreted;
    }

    public void setInterpreted(String interpreted) {
        this.interpreted = interpreted;
    }

}
