package com.example.bulbapp.bulbapplib.models;

public interface Light {
    void setLight(Light light);
    void setRGB(int red, int green, int blue);
    void setWhite(int white);
    int getRed();
    int getGreen();
    int getBlue();
    int getWhite();
    String getId();
}
