#include "http_content.hpp"

Header* HttpContent::FindHeader(String key)
{
    for (int i = 0; i < headerAmount; i++)
    {
        if (headers[i].key == key)
        {
            return &headers[i];
        }
    }
    return nullptr;
}

static void ReadTopHeader(HttpContent& httpContent, String line)
{
    for (int i = 0; i < line.length(); i++)
    {
        if (line[i] == ' ' || line[i] == '/') 
        {
            break;
        }
        httpContent.method += line[i];
    }
}

static void ReadHeader(HttpContent& httpContent, String line)
{
    if (httpContent.headerAmount >= HttpContent::HEADER_MAX)
    {
        return;
    }

    Header header;
    bool isKeyElseValue = true;
    for (int i = 0; i < line.length(); i++)
    {
        if (isKeyElseValue)
        {
            if (line[i] == ':')
            {
                i++;
                isKeyElseValue = false;
                continue;
            }
            header.key += line[i];
        }
        else
        {
            header.value += line[i];
        }
    }
    httpContent.headers[httpContent.headerAmount] = header; 
    httpContent.headerAmount++;
}

static void ReadBody(HttpContent& httpContent, int i, char* buffer, int size)
{
    Header* contentLengthHeader = httpContent.FindHeader("Content-Length");
    int contentLength = contentLengthHeader->value.toInt() + i;

    for (int j = i; j < contentLength; j++)
    {
        httpContent.body += buffer[j];
    }
}

void HttpContentConverter::ConvertFromBuffer(HttpContent& httpContent, char* buffer, int size)
{
    byte lineNumber = 0; 
    String line = "";

    for (int i = 0; i < size; i++)
    {
        if (buffer[i] == '\n')
        {
            if (lineNumber == 0)
            {
                ReadTopHeader(httpContent, line);
            }
            else
            {
                ReadHeader(httpContent, line);
            }
            if (httpContent.FindHeader("Content-Length") != nullptr)
            {
                ReadBody(httpContent, i + 3, buffer, size);
                return;
            }
            line = "";
            lineNumber++;
        }
        else if (buffer[i] == '\0')
        {
            break;
        }
        else
        {
            line += buffer[i];
        }
    }
}