
package com.example.asee_project.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("endpoint")
    @Expose
    private String endpoint;
    @SerializedName("departure")
    @Expose
    private Departure departure;
    @SerializedName("arrival")
    @Expose
    private Arrival arrival;
    @SerializedName("includeAirports")
    @Expose
    private IncludeAirports includeAirports;
    @SerializedName("excludeAirports")
    @Expose
    private ExcludeAirports excludeAirports;
    @SerializedName("includeAirlines")
    @Expose
    private IncludeAirlines includeAirlines;
    @SerializedName("excludeAirlines")
    @Expose
    private ExcludeAirlines excludeAirlines;
    @SerializedName("numHours")
    @Expose
    private NumHours numHours;
    @SerializedName("maxConnections")
    @Expose
    private MaxConnections maxConnections;
    @SerializedName("includeSurface")
    @Expose
    private IncludeSurface includeSurface;
    @SerializedName("payloadType")
    @Expose
    private PayloadType payloadType;
    @SerializedName("includeCodeshares")
    @Expose
    private IncludeCodeshares includeCodeshares;
    @SerializedName("includeMultipleCarriers")
    @Expose
    private IncludeMultipleCarriers includeMultipleCarriers;
    @SerializedName("maxResults")
    @Expose
    private MaxResults maxResults;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("dateTime")
    @Expose
    private DateTime dateTime;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public IncludeAirports getIncludeAirports() {
        return includeAirports;
    }

    public void setIncludeAirports(IncludeAirports includeAirports) {
        this.includeAirports = includeAirports;
    }

    public ExcludeAirports getExcludeAirports() {
        return excludeAirports;
    }

    public void setExcludeAirports(ExcludeAirports excludeAirports) {
        this.excludeAirports = excludeAirports;
    }

    public IncludeAirlines getIncludeAirlines() {
        return includeAirlines;
    }

    public void setIncludeAirlines(IncludeAirlines includeAirlines) {
        this.includeAirlines = includeAirlines;
    }

    public ExcludeAirlines getExcludeAirlines() {
        return excludeAirlines;
    }

    public void setExcludeAirlines(ExcludeAirlines excludeAirlines) {
        this.excludeAirlines = excludeAirlines;
    }

    public NumHours getNumHours() {
        return numHours;
    }

    public void setNumHours(NumHours numHours) {
        this.numHours = numHours;
    }

    public MaxConnections getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(MaxConnections maxConnections) {
        this.maxConnections = maxConnections;
    }

    public IncludeSurface getIncludeSurface() {
        return includeSurface;
    }

    public void setIncludeSurface(IncludeSurface includeSurface) {
        this.includeSurface = includeSurface;
    }

    public PayloadType getPayloadType() {
        return payloadType;
    }

    public void setPayloadType(PayloadType payloadType) {
        this.payloadType = payloadType;
    }

    public IncludeCodeshares getIncludeCodeshares() {
        return includeCodeshares;
    }

    public void setIncludeCodeshares(IncludeCodeshares includeCodeshares) {
        this.includeCodeshares = includeCodeshares;
    }

    public IncludeMultipleCarriers getIncludeMultipleCarriers() {
        return includeMultipleCarriers;
    }

    public void setIncludeMultipleCarriers(IncludeMultipleCarriers includeMultipleCarriers) {
        this.includeMultipleCarriers = includeMultipleCarriers;
    }

    public MaxResults getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(MaxResults maxResults) {
        this.maxResults = maxResults;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

}
