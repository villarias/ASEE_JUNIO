
package com.example.asee_project.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Date {

    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("day")
    @Expose
    private String day;
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

    public String getInterpreted() {
        return interpreted;
    }

    public void setInterpreted(String interpreted) {
        this.interpreted = interpreted;
    }

}
