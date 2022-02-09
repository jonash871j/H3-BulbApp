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

    if (client && client.connected() && client.available())
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

String HttpServer::IpAddressToString(IPAddress ipAddress)
{
    return String(ipAddress[0]) + String(".") +\
    String(ipAddress[1]) + String(".") +\
    String(ipAddress[2]) + String(".") +\
    String(ipAddress[3])  ;
}