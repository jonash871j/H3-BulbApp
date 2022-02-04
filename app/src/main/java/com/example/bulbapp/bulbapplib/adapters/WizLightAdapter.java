package com.example.bulbapp.bulbapplib.adapters;

import com.example.bulbapp.bulbapplib.models.Light;
import com.example.bulbapp.bulbapplib.models.wiz.WizLight;

public class WizLightAdapter implements Light {
    private WizLight wizLight;

    public WizLightAdapter(WizLight wizLight){
        this.wizLight = wizLight;
    }

    @Override
    public void setRGB(byte red, byte green, byte blue) {
        wizLight.getVariables().setR(red);
        wizLight.getVariables().setG(green);
        wizLight.getVariables().setB(blue);
    }

    @Override
    public void setWhite(byte white) {
        wizLight.getVariables().setW(white);
    }

    @Override
    public byte getRed() {
        return wizLight.getVariables().getR();
    }

    @Override
    public byte getGreen() {
        return wizLight.getVariables().getG();
    }

    @Override
    public byte getBlue() {
        return wizLight.getVariables().getB();
    }

    @Override
    public byte getWhite() {
        return wizLight.getVariables().getW();
    }

    @Override
    public String getId() {
        return "wiz-" + wizLight.getVariables().getLightId();
    }

    public String getRawId(){
        return wizLight.getVariables().getLightId();
    }
    public WizLight getWizLight(){
        return wizLight;
    }
}
