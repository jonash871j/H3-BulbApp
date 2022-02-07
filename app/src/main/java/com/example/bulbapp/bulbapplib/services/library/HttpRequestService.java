package com.example.bulbapp.bulbapplib.services.library;

import com.example.bulbapp.bulbapplib.models.HttpContent;

public interface HttpRequestService {
    void makeRequest(int method, String url, HttpContent httpContent);
}
