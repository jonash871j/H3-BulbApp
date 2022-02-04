package com.example.bulbapp.bulbapplib.models.wiz;

public class Variables {
    private String lightId;
    private String typename;
    private String macAddress;
    private String provider;
    private byte r = 0;
    private byte g = 0;
    private byte b = 0;
    private byte c = 0;
    private byte w = 0;

    public Variables(String lightId, String typename, String macAddress, String provider) {
        this.lightId = lightId;
        this.typename = typename;
        this.macAddress = macAddress;
        this.provider = provider;
    }

    public byte getR() {
        return r;
    }

    public void setR(byte r) {
        this.r = r;
    }

    public byte getG() {
        return g;
    }

    public void setG(byte g) {
        this.g = g;
    }

    public byte getB() {
        return b;
    }

    public void setB(byte b) {
        this.b = b;
    }

    public byte getC() {
        return c;
    }

    public void setC(byte c) {
        this.c = c;
    }

    public byte getW() {
        return w;
    }

    public void setW(byte w) {
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
}
