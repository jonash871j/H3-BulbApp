#pragma once
#include "def.hpp"

struct Header
{
    String key = "";
    String value = "";
};

struct HttpContent
{
    static const int HEADER_MAX = 10;
    int headerAmount = 0;
    Header headers[HEADER_MAX];
    String method;
    String body;

    Header* FindHeader(String key);
};

class HttpContentConverter
{
public:
    static void ConvertFromBuffer(HttpContent& httpContent, char* buffer, int size);
};