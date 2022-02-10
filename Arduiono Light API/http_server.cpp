#include "http_server.hpp"
#define BUFFER_SIZE 512

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

        char buffer[BUFFER_SIZE];
        client.readBytesUntil('\0', buffer, BUFFER_SIZE);
        HttpContent httpContent;
        HttpContentConverter::ConvertFromBuffer(httpContent, buffer, BUFFER_SIZE);

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