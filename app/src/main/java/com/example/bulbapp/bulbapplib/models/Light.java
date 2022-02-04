package com.example.bulbapp.bulbapplib.models;

public interface Light {
    void setRGB(byte red, byte green, byte blue);
    void setWhite(byte white);
    byte getRed();
    byte getGreen();
    byte getBlue();
    byte getWhite();
    String getId();
}
