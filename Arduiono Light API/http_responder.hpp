#pragma once
#include "def.hpp"
#include "http_content.hpp"
#include "http_responder.hpp"

class HttpResponder
{
private:
    EthernetClient client;
    int stausCode = 200;
    String data = "";

public:
    HttpResponder(EthernetClient client);

public:
    void SetStausCode(int stausCode);
    void AddLine(String text);
    void Send();
};