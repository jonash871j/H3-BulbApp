package com.example.bulbapp.bulbapplib.services.application;

import com.example.bulbapp.bulbapplib.models.Light;

import java.util.List;

public interface LightApplicationService {
    void updateLight(Light light);
    void addLight(Light light);
    List<Light> getLights();
    Light getLight(String id);
}
