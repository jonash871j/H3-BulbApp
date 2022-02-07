package com.example.bulbapp.bulbapplib.services.library;

import com.example.bulbapp.bulbapplib.models.Light;

import java.util.ArrayList;
import java.util.List;

public class GenericLightServiceImpl implements GenericLightService {
    private List<LightService> lightServices;

    public GenericLightServiceImpl(List<LightService> lightServices){
        this.lightServices = lightServices;
    }

    @Override
    public void updateLight(Light light) {
        getLightService(light).updateLight(light);
    }

    @Override
    public void updateAllLightsToNewLight(Light newLight) {
        for (Light light :  getAllLights()){
            light.setLight(newLight);
            updateLight(light);
        }
    }

    @Override
    public void addLight(Light light) {
        getLightService(light).addLight(light);
    }

    @Override
    public List<Light> getAllLights() {
        List<Light> lights = new ArrayList();
        for (LightService lightDomainService : lightServices){
            lights.addAll(lightDomainService.getLights());
        }
        return lights;
    }

    @Override
    public Light getLight(String id) {
        for (LightService lightDomainService : lightServices){
            Light light = lightDomainService.getLight(id);
            if (light != null){
                return light;
            }
        }
        return null;
    }

    private LightService getLightService(Light light) {
        for (LightService lightService : lightServices){
            if (lightService.isLightSupported(light)){
                return lightService;
            }
        }
        return null;
    }
}
