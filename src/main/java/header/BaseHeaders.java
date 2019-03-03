package main.java.header;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseHeaders {

    public static String attachBaseHeaders(HttpExchange httpExchange, String response) {
        Headers headers = httpExchange.getRequestHeaders();
        Set<Map.Entry<String, List<String>>> entries = headers.entrySet();
        StringBuilder responseBuilder = new StringBuilder(response);
        for (Map.Entry<String, List<String>> entry : entries)
            responseBuilder.append(entry.toString()).append("\n");
        response = responseBuilder.toString();

        return response;
    }
}
