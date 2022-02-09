package com.example.bulbapp.bulbapplib.adapters;

import com.example.bulbapp.bulbapplib.models.ArduionoLight;
import com.example.bulbapp.bulbapplib.models.Light;
import com.example.bulbapp.bulbapplib.models.wiz.WizLight;

public class ArduionoLightAdapter implements Light {
    private ArduionoLight arduionoLight;

    public ArduionoLightAdapter(ArduionoLight arduionoLight){
        this.arduionoLight = arduionoLight;
    }

    @Override
    public void setLight(Light light) {
        setRGB(light.getRed(), light.getGreen(), light.getBlue());
        setWhite(light.getWhite());
    }

    @Override
    public void setRGB(int red, int green, int blue) {
        arduionoLight.setR(red);
        arduionoLight.setG(green);
        arduionoLight.setB(blue);
    }

    @Override
    public void setWhite(int white) {
        if (white == 0){
            return;
        }

        arduionoLight.setR(white);
        arduionoLight.setG(white);
        arduionoLight.setB(white);
    }

    @Override
    public int getRed() {
        return arduionoLight.getR();
    }

    @Override
    public int getGreen() {
        return arduionoLight.getG();
    }

    @Override
    public int getBlue() {
        return arduionoLight.getB();
    }

    @Override
    public int getWhite() {
        return (getRed() + getGreen() + getBlue() / 765);
    }

    @Override
    public String getId() {
        return "arduiono-1";
    }

    public ArduionoLight getArduionoLight(){
        return arduionoLight;
    }
}
