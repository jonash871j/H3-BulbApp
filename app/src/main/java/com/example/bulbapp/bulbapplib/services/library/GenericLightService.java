package com.example.bulbapp.bulbapplib.services.library;

import com.example.bulbapp.bulbapplib.models.Light;

import java.util.List;

public interface GenericLightService {
    void updateLight(Light light);
    void updateAllLightsToNewLight(Light newLight);
    void addLight(Light light);
    List<Light> getAllLights();
    Light getLight(String id);
}
