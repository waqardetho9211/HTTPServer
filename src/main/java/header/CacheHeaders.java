package main.java.header;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;

public class CacheHeaders {
    public static String attachETAGHeader(String response, HTTPHeaders headers){
        response += HeaderTypes.ETAG + " = " + headers.ETAG + "\n";
        return response;
    }

    public static void attachETAGHeadersInHeaders(HttpExchange httpExchange, HTTPHeaders headers) {
        byte[] response = "loaded=true".getBytes(StandardCharsets.UTF_8);
        HashMap responseHeaders = new HashMap();
        responseHeaders.put("ETag", Collections.singletonList(headers.ETAG));
        responseHeaders.put("Content-Type", Collections.singletonList("text/plain"));
        httpExchange.getResponseHeaders().putAll(responseHeaders);
    }
}
