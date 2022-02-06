package com.example.bulbapp.bulbapplib;

import com.android.volley.RequestQueue;
import com.example.bulbapp.bulbapplib.services.application.LightApplicationService;
import com.example.bulbapp.bulbapplib.services.application.LightApplicationServiceImpl;
import com.example.bulbapp.bulbapplib.services.domain.LightDomainService;
import com.example.bulbapp.bulbapplib.services.domain.WizLightDomainServiceImpl;
import com.example.bulbapp.bulbapplib.services.infrastructure.HttpRequestInfrastructureService;
import com.example.bulbapp.bulbapplib.services.infrastructure.HttpRequestInfrastructureServiceImpl;
import com.example.bulbapp.bulbapplib.services.infrastructure.SqlLiteInfrastructureService;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {

    private static LightApplicationService lightApplicationService = null;
    private static List<LightDomainService> lightDomainServices = null;
    private static HttpRequestInfrastructureService httpRequestInfrastructureService = null;
    private static SqlLiteInfrastructureService sqlLiteInfrastructureService = null;

    public static void Initialize(RequestQueue requestQueue){
        httpRequestInfrastructureService = new HttpRequestInfrastructureServiceImpl(requestQueue);

        lightDomainServices = new ArrayList<LightDomainService>() {{
            add(new WizLightDomainServiceImpl(httpRequestInfrastructureService, null));
        }};

        lightApplicationService = new LightApplicationServiceImpl(lightDomainServices);
    }

    public static LightApplicationService getLightApplicationService() {
        return lightApplicationService;
    }
}
