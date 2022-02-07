package com.example.bulbapp.bulbapplib.models.wiz;

import com.google.gson.annotations.SerializedName;

public class Variables {

    private String lightId;
    @SerializedName(value = "__typename")
    private String typename;
    private String macAddress;
    private String provider;
    private int r = 0;
    private int g = 0;
    private int b = 0;
    private int c = 0;
    private int w = 0;
    private boolean status = true;

    public Variables(String lightId, String typename, String macAddress, String provider) {
        this.lightId = lightId;
        this.typename = typename;
        this.macAddress = macAddress;
        this.provider = provider;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public String getLightId() {
        return lightId;
    }

    public String getTypename() {
        return typename;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getProvider() {
        return provider;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
