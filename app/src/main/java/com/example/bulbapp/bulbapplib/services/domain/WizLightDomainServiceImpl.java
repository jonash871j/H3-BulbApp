package com.example.bulbapp.bulbapplib.services.domain;


import com.android.volley.Request;
import com.example.bulbapp.bulbapplib.adapters.WizLightAdapter;
import com.example.bulbapp.bulbapplib.models.HttpContent;
import com.example.bulbapp.bulbapplib.models.Light;
import com.example.bulbapp.bulbapplib.models.wiz.WizLight;
import com.example.bulbapp.bulbapplib.services.infrastructure.HttpRequestInfrastructureService;
import com.example.bulbapp.bulbapplib.services.infrastructure.SqlLiteInfrastructureService;

import java.util.List;

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
        httpRequestInfrastructureService.makeRequest(Request.Method.POST, "", httpContent);
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
        return null;
    }

    @Override
    public Light getLight(String id) {
        return null;
    }
}
