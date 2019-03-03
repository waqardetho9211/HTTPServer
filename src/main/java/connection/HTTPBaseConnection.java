package main.java.connection;

import com.sun.net.httpserver.HttpServer;
import main.java.Request.HTTPRequestType;
import main.java.Response.HTTPResponse;

import java.io.IOException;
import java.net.InetSocketAddress;

class HTTPBaseConnection {
    HttpServer server;
    private int port = 8989;

    HTTPBaseConnection() {}

    HTTPBaseConnection setupRequest(String connectionPath, HTTPRequestType type, HTTPResponse response){
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            System.err.println("Unable to create HTTP Connection");
        }
        HTTPConnectionFactory httpConnectionFactory = new HTTPConnectionFactory();
        server = httpConnectionFactory.getRequest(this.server, type, response, connectionPath);
        server.setExecutor(null);
        server.start();
        return this;
    }
}
