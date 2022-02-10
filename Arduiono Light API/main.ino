#include "http_server.hpp"
#define RED_PIN 5
#define GREEN_PIN 6
#define BLUE_PIN 7

void OnServerInfo(String msg);
void OnRequest(HttpContent& httpContent, HttpResponder& responder);

HttpServer* httpServer;

void setup() 
{
    Serial.begin(9600);
    pinMode(RED_PIN, OUTPUT);
    pinMode(GREEN_PIN, OUTPUT);
    pinMode(BLUE_PIN, OUTPUT);
    
    byte macAddress[] = { 0x90, 0xA2, 0xDA, 0x10, 0x5F, 0x80 };
    IPAddress ip(192, 168, 1, 3);
    httpServer = new HttpServer(macAddress, ip, 80);
    httpServer->ServerInfoEvent = &OnServerInfo;
    httpServer->RequestEvent = &OnRequest;
    httpServer->Start();
}

void loop() 
{
    httpServer->Listen();
}

void OnServerInfo(String msg)
{
    Serial.println("[Server] " + msg);
}

void OnRequest(HttpContent& httpContent, HttpResponder& responder)
{
    if (httpContent.method == "POST")
    {
        DynamicJsonDocument doc(128);
        deserializeJson(doc, httpContent.body);
        analogWrite(RED_PIN, doc["r"]);
        analogWrite(GREEN_PIN, doc["g"]);
        analogWrite(BLUE_PIN, doc["b"]);
        responder.Send();
    }
    else
    {
        responder.SetStausCode(400);
        responder.Send();
    }
}