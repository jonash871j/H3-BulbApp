package com.example.bulbapp.bulbapplib;

import com.android.volley.RequestQueue;
import com.example.bulbapp.bulbapplib.services.library.GenericLightService;
import com.example.bulbapp.bulbapplib.services.library.GenericLightServiceImpl;
import com.example.bulbapp.bulbapplib.services.library.HttpRequestService;
import com.example.bulbapp.bulbapplib.services.library.HttpRequestServiceImpl;
import com.example.bulbapp.bulbapplib.services.library.LightService;
import com.example.bulbapp.bulbapplib.services.library.SqlLiteService;
import com.example.bulbapp.bulbapplib.services.library.WizLightServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager {

    private static ServiceManager instance = null;
    private GenericLightService genericLightService = null;
    private List<LightService> lightServices = null;
    private HttpRequestService httpRequestService = null;
    private SqlLiteService sqlLiteService = null;

    private ServiceManager(RequestQueue requestQueue){
        httpRequestService = new HttpRequestServiceImpl(requestQueue);
        lightServices = new ArrayList<LightService>() {{
            add(new WizLightServiceImpl(httpRequestService, null));
        }};
        genericLightService = new GenericLightServiceImpl(lightServices);
    }

    public static void Initialize(RequestQueue requestQueue){
        instance = new ServiceManager(requestQueue);
    }
    public static ServiceManager get(){
        return instance;
    }

    public GenericLightService getGenericLightService() {
        return genericLightService;
    }
}
