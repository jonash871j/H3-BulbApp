#include "http_server.hpp"


HttpServer::HttpServer(byte* macAddress, IPAddress& ipAddress, byte port)
    : macAddress(macAddress), ipAddress(ipAddress), port(port)
{
    ethernetServer = new EthernetServer(port);
}

HttpServer::~HttpServer()
{
    delete ethernetServer;
}

void HttpServer::Start()
{
    ServerInfoEvent("Starting server...");
    Ethernet.begin(macAddress, ipAddress);
    ethernetServer->begin();
    ServerInfoEvent("Server started on " + IpAddressToString(Ethernet.localIP()));
}

void HttpServer::Listen()
{
    // listen for incoming clients
    EthernetClient client = ethernetServer->available();
    if (client) 
    {
        // an http request ends with a blank line
        boolean currentLineIsBlank = true;
        String request = "";
        String line = "";

        if (client.connected() && client.available())
        {
            ServerInfoEvent("Client requested");

            char buffer[1024];
            client.readBytesUntil('\0', buffer, 1024);
            HttpContent httpContent;
            HttpContentConverter::ConvertFromBuffer(httpContent, buffer, 1024);

            HttpResponder responder(client);
            RequestEvent(httpContent, responder);

            delay(1);
            client.stop();
        }
    }
}

String HttpServer::IpAddressToString(IPAddress ipAddress)
{
    return String(ipAddress[0]) + String(".") +\
    String(ipAddress[1]) + String(".") +\
    String(ipAddress[2]) + String(".") +\
    String(ipAddress[3])  ;
}

 void HttpServer::SendResponse(EthernetClient client)
 {
    // send a standard http response header
    client.println("HTTP/1.1 200 OK");
    client.println("Content-Type: text/html");
    client.println("Connection: close"); 
    client.println();
    client.println("<!DOCTYPE HTML>");
    client.println("<html>");

    // output the value of each analog input pin
    for (int analogChannel = 0; analogChannel < 6; analogChannel++)
    {
        int sensorReading = analogRead(analogChannel);
        client.print("analog input ");
        client.print(analogChannel);
        client.print(" is ");
        client.print(sensorReading);
        client.println("<br />");
    }
    client.println("</html>");
 }