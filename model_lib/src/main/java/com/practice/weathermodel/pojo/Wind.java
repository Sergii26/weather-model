package com.practice.weathermodel.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    @Expose
    private double speed;
    @SerializedName("deg")
    @Expose
    private int deg;
    @SerializedName("var_beg")
    @Expose
    private int varBeg;
    @SerializedName("var_end")
    @Expose
    private int varEnd;

    /**
     * No args constructor for use in serialization
     *
     */
    public Wind() {
    }

    /**
     *
     * @param varEnd
     * @param deg
     * @param varBeg
     * @param speed
     */
    public Wind(double speed, int deg, int varBeg, int varEnd) {
        super();
        this.speed = speed;
        this.deg = deg;
        this.varBeg = varBeg;
        this.varEnd = varEnd;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getVarBeg() {
        return varBeg;
    }

    public void setVarBeg(int varBeg) {
        this.varBeg = varBeg;
    }

    public int getVarEnd() {
        return varEnd;
    }

    public void setVarEnd(int varEnd) {
        this.varEnd = varEnd;
    }
}
