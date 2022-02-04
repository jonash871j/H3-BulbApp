package com.example.bulbapp.bulbapplib.services.application;

import com.example.bulbapp.bulbapplib.models.Light;
import com.example.bulbapp.bulbapplib.services.domain.LightDomainService;

import java.util.ArrayList;
import java.util.List;

public class LightApplicationServiceImpl implements LightApplicationService {
    private List<LightDomainService> lightDomainServices;

    public LightApplicationServiceImpl(List<LightDomainService> lightDomainServices){
        this.lightDomainServices = lightDomainServices;
    }

    @Override
    public void updateLight(Light light) {
        for (LightDomainService lightDomainService : lightDomainServices){
            lightDomainService.updateLight(light);
        }
    }

    @Override
    public void addLight(Light light) {
        for (LightDomainService lightDomainService : lightDomainServices){
            lightDomainService.addLight(light);
        }
    }

    @Override
    public List<Light> getLights() {
        List<Light> lights = new ArrayList();
        for (LightDomainService lightDomainService : lightDomainServices){
            lights.addAll(lightDomainService.getLights());
        }
        return lights;
    }

    @Override
    public Light getLight(String id) {
        for (LightDomainService lightDomainService : lightDomainServices){
            Light light = lightDomainService.getLight(id);
            if (light != null){
                return light;
            }
        }
        return null;
    }
}
