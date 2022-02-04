package com.example.bulbapp.bulbapplib.services.infrastructure;

import com.example.bulbapp.bulbapplib.models.HttpContent;

public interface HttpRequestInfrastructureService {
    void makeRequest(int method, String url, HttpContent httpContent);
}
