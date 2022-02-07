package com.example.bulbapp.bulbapplib.services.library;

import com.android.volley.Request;
import com.example.bulbapp.bulbapplib.adapters.WizLightAdapter;
import com.example.bulbapp.bulbapplib.models.HttpContent;
import com.example.bulbapp.bulbapplib.models.Light;
import com.example.bulbapp.bulbapplib.models.wiz.Variables;
import com.example.bulbapp.bulbapplib.models.wiz.WizLight;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class WizLightServiceImpl implements LightService {
    private HttpRequestService httpRequestService;
    private SqlLiteService sqlLiteService;

    public WizLightServiceImpl(
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
        WizLight wizLight = ((WizLightAdapter)light).getWizLight();
        HttpContent httpContent = new HttpContent();
        httpContent.addHeader("Cookie", "connect.sid=s%3A8l7--o3F8YFKVJNj-tZhGVPz_PkO-iCN.YeuWbaiNaOTMohWDuyf94khGBCwekYS6cq%2Fi%2FtUVeh0");
        httpContent.setBody(gson.toJson(wizLight));
        httpRequestService.makeRequest(Request.Method.POST, "https://api.pro.wizconnected.com/graphql", httpContent);
    }

    @Override
    public void addLight(Light light) {
        if (!isLightSupported(light)){
            return;
        }
        WizLight wizLight = ((WizLightAdapter)light).getWizLight();
    }

    @Override
    public List<Light> getLights() {
        Variables variables = new Variables("14256970", "LightModeColor", "d8a011906b11", "WiZ");
        return new ArrayList<Light>(){{
            add(new WizLightAdapter(new WizLight(variables, WizLight.getLightOnQuery())));
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
        if (light instanceof WizLightAdapter){
            return true;
        }
        return false;
    }
}
