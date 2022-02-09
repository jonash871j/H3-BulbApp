package com.example.bulbapp.bulbapplib.models;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HttpContent {
    private byte[] body = null;
    private Map<String,String> headers = new HashMap();

    public void setBody(String body){
        try {
            this.body = body.getBytes("utf-8");
        } catch (UnsupportedEncodingException uee) {
        }
    }
    public void addHeader(String key, String value){
        headers.put(key, value);
    }
    public byte[] getBody(){
        return body;
    }
    public Map<String, String> getHeaders(){
        return headers;
    }
}
