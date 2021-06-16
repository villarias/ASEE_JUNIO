
package com.example.asee_project.modeloApiVuelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Connection {

    @SerializedName("elapsedTime")
    @Expose
    private Integer elapsedTime;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("scheduledFlight")
    @Expose
    private List<ScheduledFlight> scheduledFlight = null;

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<ScheduledFlight> getScheduledFlight() {
        return scheduledFlight;
    }

    public void setScheduledFlight(List<ScheduledFlight> scheduledFlight) {
        this.scheduledFlight = scheduledFlight;
    }

}
