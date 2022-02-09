#pragma once
#include "def.hpp"
#include "http_content.hpp"
#include "http_responder.hpp"

class HttpServer
{
public:
    void (*ServerInfoEvent)(String msg);
    void (*RequestEvent)(HttpContent& httpContent, HttpResponder& responder);

private:
    EthernetServer* ethernetServer;
    byte* macAddress;
    IPAddress& ipAddress;
    byte port;

public:
    HttpServer(byte* macAddress, IPAddress& ipAddress, byte port);
    ~HttpServer();

public:
    void Start();
    void Listen();

private:
    String IpAddressToString(IPAddress ipAddress);
    void SendResponse(EthernetClient client);
};