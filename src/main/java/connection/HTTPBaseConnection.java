package main.java.connection;

import com.sun.net.httpserver.HttpServer;
import main.java.Request.HTTPRequest;
import main.java.Response.HTTPResponse;
import main.java.header.HTTPHeaders;

import java.io.IOException;
import java.net.InetSocketAddress;

class HTTPBaseConnection {
    HttpServer server;
    private int port = 8989;

    HTTPBaseConnection() {}

    HTTPBaseConnection setupRequest(HTTPRequest request, HTTPResponse response, HTTPHeaders headers){
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            System.err.println("Unable to create HTTP Connection");
        }
        HTTPConnectionFactory httpConnectionFactory = new HTTPConnectionFactory();
        server = httpConnectionFactory.getRequest(server, request, response, headers);
        server.setExecutor(null);
        server.start();
        return this;
    }
}
