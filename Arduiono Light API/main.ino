#include "http_server.hpp"

void OnServerInfo(String msg);
void OnRequest(HttpContent& httpContent, HttpResponder& responder);

struct RGB
{
    byte r = 0;
    byte g = 0;
    byte b = 0;
};

byte macAddress[] = {
  0x90, 0xA2, 0xDA, 0x10, 0x5F, 0x80
};
IPAddress ip(192, 168, 1, 3);
HttpServer httpServer(macAddress, ip, 80);

void setup() 
{
    Serial.begin(9600);
    while (!Serial) 
    {
        ; // wait for serial port to connect. Needed for native USB port only
    }
    httpServer.ServerInfoEvent = &OnServerInfo;
    httpServer.RequestEvent = &OnRequest;
    httpServer.Start();

    pinMode(5, OUTPUT);
    pinMode(6, OUTPUT);
    pinMode(7, OUTPUT);
}


void loop() 
{
    httpServer.Listen();
}

void OnServerInfo(String msg)
{
    Serial.println("[Server] " + msg);
}

void OnRequest(HttpContent& httpContent, HttpResponder& responder)
{
    if (httpContent.method == "POST")
    {
        char* bytes = (char*)httpContent.body.c_str();
        RGB* rgb = reinterpret_cast<RGB*>(bytes);
        RGBColor(rgb->r, rgb->g, rgb->b);

        responder.AddLine("r = " + String(rgb->r) + " g = " + String(rgb->g) + " b = " + String(rgb->b));
        responder.Send();
    }
    else
    {
        responder.SetStausCode(400);
        responder.Send();
    }
}

void RGBColor(int red, int green, int blue)
{
    analogWrite(5, red);
    analogWrite(6, green);
    analogWrite(7, blue);
}