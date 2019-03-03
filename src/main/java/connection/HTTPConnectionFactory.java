package main.java.connection;

import com.sun.net.httpserver.HttpServer;
import main.java.Request.HTTPBaseRequest;
import main.java.Request.HTTPGetRequest;
import main.java.Request.HTTPRequest;
import main.java.Request.HTTPRequestType;
import main.java.Response.HTTPResponse;
import main.java.header.HTTPHeaders;

/**
 * Class to parse HTTP requests.
 */
class HTTPConnectionFactory {

    HttpServer getRequest(HttpServer server, HTTPRequest request, HTTPResponse response, HTTPHeaders headers) {
        if (request.httpRequestType == null) {
            server.createContext(request.connectionPath, new HTTPBaseRequest(response));
            return server;
        }
        if (request.httpRequestType.equals(HTTPRequestType.GET)) {
            server.createContext(request.connectionPath, new HTTPGetRequest(response, headers));
            return server;

        } else if (request.httpRequestType.equals(HTTPRequestType.HEADER)) {
            // Tod Implement
            return null;

        } else if (request.httpRequestType.equals(HTTPRequestType.POST)) {
            // Tod Implement
            return null;
        }

        return null;
    }

}
