package com.example.bulbapp.bulbapplib.models;

public class BasicLight implements Light {
    private int white = 0;
    private int red = 0;
    private int green = 0;
    private int blue = 0;

    private BasicLight(){

    }
    public BasicLight(int red, int green, int blue) {
        this(0, red, green, blue);
    }

    public BasicLight(int white, int red, int green, int blue) {
        this.white = white;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public void setLight(Light light) {
        red = light.getRed();
        green = light.getGreen();
        blue = light.getBlue();
        white = light.getWhite();
    }

    @Override
    public void setRGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public void setWhite(int white) {
        this.white = white;
    }

    @Override
    public int getRed() {
        return red;
    }

    @Override
    public int getGreen() {
        return green;
    }

    @Override
    public int getBlue() {
        return blue;
    }

    @Override
    public int getWhite() {
        return white;
    }

    @Override
    public String getId() {
        return null;
    }
}
