package main.java.Request;

import com.sun.net.httpserver.HttpServer;
import main.java.Response.HTTPResponse;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HTTPRequest {
    public HttpServer server;
    private int port = 8989;

    public HTTPRequest() {}

    public HTTPRequest setupRequest(String connectionPath, HTTPRequestType type, HTTPResponse response){
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            System.err.println("Unable to create HTTP Connection");
        }
        HTTPRequestFactory httpRequestFactory = new HTTPRequestFactory();
        server = httpRequestFactory.getRequest(this.server, type, response, connectionPath);
        server.setExecutor(null);
        server.start();
        return this;
    }
}
