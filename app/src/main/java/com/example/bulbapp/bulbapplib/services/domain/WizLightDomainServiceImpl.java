package com.example.bulbapp.bulbapplib.services.domain;

import android.util.Log;

import com.android.volley.Request;
import com.example.bulbapp.bulbapplib.adapters.WizLightAdapter;
import com.example.bulbapp.bulbapplib.models.HttpContent;
import com.example.bulbapp.bulbapplib.models.Light;
import com.example.bulbapp.bulbapplib.models.wiz.Variables;
import com.example.bulbapp.bulbapplib.models.wiz.WizLight;
import com.example.bulbapp.bulbapplib.services.infrastructure.HttpRequestInfrastructureService;
import com.example.bulbapp.bulbapplib.services.infrastructure.SqlLiteInfrastructureService;
import com.fasterxml.jackson;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectWriter;

public class WizLightDomainServiceImpl implements LightDomainService {
    private HttpRequestInfrastructureService httpRequestInfrastructureService;
    private SqlLiteInfrastructureService sqlLiteInfrastructureService;

    public WizLightDomainServiceImpl(
            HttpRequestInfrastructureService httpRequestInfrastructureService,
            SqlLiteInfrastructureService sqlLiteInfrastructureService){
        this.httpRequestInfrastructureService = httpRequestInfrastructureService;
        this.sqlLiteInfrastructureService = sqlLiteInfrastructureService;
    }

    @Override
    public void updateLight(Light light) {
        if (!(light instanceof WizLightAdapter)){
            return;
        }
        WizLight wizLight = ((WizLightAdapter)light).getWizLight();
        HttpContent httpContent = new HttpContent();
        Log.i("dont care", wizLight.toString());
        httpContent.addHeader("Cookie", "connect.sid=s%3AdudvL4MCgi1YxvK2aBNHyKJnmbFe8Y-M.vUzIeTEsZ%2F2MWyeUw0rtLaeUCuDD0qbkwhrjc8FcF0I");

        ObjectMapper mapper = new ObjectMapper();

        httpContent.setBody(wizLight.toString());
        httpRequestInfrastructureService.makeRequest(Request.Method.POST, "https://api.pro.wizconnected.com/graphql", httpContent);
    }

    @Override
    public void addLight(Light light) {
        if (!(light instanceof WizLightAdapter)){
            return;
        }
        WizLight wizLight = ((WizLightAdapter)light).getWizLight();

    }

    @Override
    public List<Light> getLights() {
        Variables variables = new Variables("14201789", "LightModeColor", "d8a011906b11", "Wiz");
        String query = "mutation ($r: Int!, $g: Int!, $b: Int!, $c: Int!, $w: Int!, $lightId: String!, $provider: String!, $macAddress: String) {\\n  pilotLight(lightId: $lightId, provider: $provider, mac: $macAddress, state: {state: true, r: $r, g: $g, b: $b, cw: $c, ww: $w})\\n}\\n";
        return new ArrayList<Light>(){{
            add(new WizLightAdapter(new WizLight(null, variables, query)));
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
}
