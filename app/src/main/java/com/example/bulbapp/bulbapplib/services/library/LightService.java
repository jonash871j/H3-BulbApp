package com.example.bulbapp.bulbapplib.services.library;

import com.example.bulbapp.bulbapplib.models.Light;

import java.util.List;

public interface LightService {
    void updateLight(Light light);
    void addLight(Light light);
    List<Light> getLights();
    Light getLight(String id);
    boolean isLightSupported(Light light);
}
