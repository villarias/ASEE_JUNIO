
package com.example.asee_project.modeloApiVuelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DateTime {

    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("hour")
    @Expose
    private String hour;
    @SerializedName("minute")
    @Expose
    private String minute;
    @SerializedName("interpreted")
    @Expose
    private String interpreted;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getInterpreted() {
        return interpreted;
    }

    public void setInterpreted(String interpreted) {
        this.interpreted = interpreted;
    }

}
