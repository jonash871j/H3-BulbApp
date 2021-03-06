package com.example.bulbapp.bulbapplib.services.library;

import com.android.volley.Request;
import com.example.bulbapp.bulbapplib.adapters.ArduionoLightAdapter;
import com.example.bulbapp.bulbapplib.models.ArduionoLight;
import com.example.bulbapp.bulbapplib.models.HttpContent;
import com.example.bulbapp.bulbapplib.models.Light;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ArduionoLightServiceImpl implements LightService {
    private HttpRequestService httpRequestService;
    private SqlLiteService sqlLiteService;

    public ArduionoLightServiceImpl(
            HttpRequestService httpRequestService,
            SqlLiteService sqlLiteService){
        this.httpRequestService = httpRequestService;
        this.sqlLiteService = sqlLiteService;
    }

    @Override
    public void updateLight(Light light) {
        if (!isLightSupported(light)){
            return;
        }

        Gson gson = new Gson();
        ArduionoLight arduionoLight = ((ArduionoLightAdapter)light).getArduionoLight();
        HttpContent httpContent = new HttpContent();
        httpContent.setBody(gson.toJson(arduionoLight));
        httpRequestService.makeRequest(Request.Method.POST, "http://93.176.82.49/", httpContent);
    }

    @Override
    public void addLight(Light light) {
        if (!isLightSupported(light)){
            return;
        }
    }

    @Override
    public List<Light> getLights() {
        return new ArrayList<Light>(){{
            add(new ArduionoLightAdapter(new ArduionoLight()));
        }};
    }

    @Override
    public Light getLight(String id) {
        List<Light> lights = getLights();
        for (Light light : lights) {
            if (light.getId() == id){
                return light;
            }
        }
        return null;
    }

    @Override
    public boolean isLightSupported(Light light) {
        if (light instanceof ArduionoLightAdapter){
            return true;
        }
        return false;
    }
}
