#include "http_responder.hpp"

HttpResponder::HttpResponder(EthernetClient client)
    : client(client)
{

}

void HttpResponder::SetStausCode(int stausCode)
{
    this->stausCode = stausCode;
}
void HttpResponder::AddLine(String text)
{
    data += text + "\n";
}
void HttpResponder::Send()
{
    client.println("HTTP/1.1 "+ String(stausCode));
    client.println("Content-Type: text/html");
    client.println("Connection: close"); 
    client.println();
    client.println(data); 
}