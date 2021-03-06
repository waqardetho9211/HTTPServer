package main.java.Request;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.Response.HTTPResponse;
import main.java.header.BaseHeaders;
import main.java.header.CacheHeaders;
import main.java.header.HTTPHeaders;
import main.java.header.HeaderTypes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTTPGetRequest implements HttpHandler {
    private final HTTPHeaders headers;
    private HTTPResponse response;

    public HTTPGetRequest(HTTPResponse response, HTTPHeaders headers) {
        this.headers = headers;
        this.response = response;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Map<String, Object> parameters = new HashMap<>();
        URI requestedUri = httpExchange.getRequestURI();
        String query = requestedUri.getRawQuery();
        parseQuery(query, parameters);
        // send response
        StringBuilder response = new StringBuilder();
        for (String key : parameters.keySet())
            response.append(key).append(" = ").append(parameters.get(key)).append("\n");

        if (this.headers.headers) {
            response = new StringBuilder(BaseHeaders.attachBaseHeadersInResponse(httpExchange, response.toString()));
            BaseHeaders.attachBaseHeadersInHeaders(httpExchange, headers);
        }
        if (this.headers.ETAG != null) {
            response = new StringBuilder(CacheHeaders.attachETAGHeader(response.toString(), headers));
            CacheHeaders.attachETAGHeadersInHeaders(httpExchange, headers);
        }
        if (this.headers.IfMatch != null) {
            // Todo implement
            System.out.println("The functionality is not yet implemented: "+ HeaderTypes.IF_MATCH);
        }
        if (this.headers.IfNoneMatch != null) {
            // Todo implement
            System.out.println("The functionality is not yet implemented: "+ HeaderTypes.IF_NONE_MATCH);
        }
        if (this.headers.IfModifiedSince != null) {
            // Todo implement
            System.out.println("The functionality is not yet implemented: "+ HeaderTypes.IF_MODIFIED_SINCE);
        }
        this.response.responseString = response.toString();
        HTTPBaseRequest.writeOutputStream(httpExchange, response.toString());
    }

    // Method copied from the internet
    private static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {

        if (query != null) {
            String[] pairs = query.split("[&]");

            for (String pair : pairs) {
                String[] param = pair.split("[=]");

                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);
                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }

}
