package com.example.bulbapp.bulbapplib.adapters;

import com.example.bulbapp.bulbapplib.models.Light;
import com.example.bulbapp.bulbapplib.models.wiz.Variables;
import com.example.bulbapp.bulbapplib.models.wiz.WizLight;

public class WizLightAdapter implements Light {
    private WizLight wizLight;

    public WizLightAdapter(WizLight wizLight){
        this.wizLight = wizLight;
    }

    @Override
    public void setLight(Light light) {
        Variables variables = wizLight.getVariables();
        variables.setR(light.getRed());
        variables.setG(light.getGreen());
        variables.setB(light.getBlue());
        variables.setC(light.getWhite());
        turnOffIfAllValuesIsZero();
    }

    @Override
    public void setRGB(int red, int green, int blue) {
        Variables variables = wizLight.getVariables();
        variables.setR(red);
        variables.setG(green);
        variables.setB(blue);
        turnOffIfAllValuesIsZero();
    }

    @Override
    public void setWhite(int white) {
        wizLight.getVariables().setC(white);
        turnOffIfAllValuesIsZero();
    }

    @Override
    public int getRed() {
        return wizLight.getVariables().getR();
    }

    @Override
    public int getGreen() {
        return wizLight.getVariables().getG();
    }

    @Override
    public int getBlue() {
        return wizLight.getVariables().getB();
    }

    @Override
    public int getWhite() {
        return wizLight.getVariables().getC();
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

    private void turnOffIfAllValuesIsZero(){
        Variables variables = wizLight.getVariables();
        if (variables.getR() == 0 && variables.getG() == 0 && variables.getB() == 0 && variables.getC() == 0){
            variables.setStatus(false);
            wizLight.setQuery(WizLight.getLightOffQuery());
        }
        else{
            variables.setStatus(true);
            wizLight.setQuery(WizLight.getLightOnQuery());
        }
    }
}
