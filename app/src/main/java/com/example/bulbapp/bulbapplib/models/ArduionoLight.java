package com.example.bulbapp.bulbapplib.models;

public class ArduionoLight {
    private int r = 0;
    private int g = 0;
    private int b = 0;

    public byte[] toBinary()
    {
        return new byte[]
        {
                (byte)r,
                (byte)g,
                (byte)b
        };
    }

    public int getR() {
        return r;
    }
    public void setR(int r) {
        this.r = r;
    }
    public void setG(int g) {
        this.g = g;
    }
    public int getG() {
        return g;
    }
    public int getB() {
        return b;
    }
    public void setB(int b) {
        this.b = b;
    }
}
