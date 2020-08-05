package com.practice.weathermodel.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("calctime")
    @Expose
    private double calctime;
    @SerializedName("cnt")
    @Expose
    private int cnt;
    @SerializedName("list")
    @Expose
    private List<City> cities = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Response() {
    }

    /**
     *
     * @param calctime
     * @param cnt
     * @param cod
     * @param cities
     */
    public Response(String cod, double calctime, int cnt, List<City> cities) {
        super();
        this.cod = cod;
        this.calctime = calctime;
        this.cnt = cnt;
        this.cities = cities;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getCalctime() {
        return calctime;
    }

    public void setCalctime(double calctime) {
        this.calctime = calctime;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
