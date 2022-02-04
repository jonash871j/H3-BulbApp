package com.example.bulbapp.bulbapplib.services.domain;

import com.example.bulbapp.bulbapplib.models.Light;
import com.example.bulbapp.bulbapplib.models.wiz.WizLight;

import java.util.List;

public interface LightDomainService {
    void updateLight(Light light);
    void addLight(Light light);
    List<Light> getLights();
    Light getLight(String id);
}
