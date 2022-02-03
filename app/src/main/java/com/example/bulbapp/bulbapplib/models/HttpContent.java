package com.example.bulbapp.bulbapplib.models;

import java.util.HashMap;
import java.util.Map;

public class HttpContent {
    private String body = null;
    private Map<String,String> headers = new HashMap<>();

    public void setBody(String body){
        this.body = body;
    }

    public void addHeader(String key, String value){
        headers.put(key, value);
    }

    public String getBody(){
        return body;
    }

    public Map<String, String> getHeaders(){
        return headers;
    }
}
