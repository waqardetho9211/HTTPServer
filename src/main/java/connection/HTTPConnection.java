package main.java.connection;


import com.sun.net.httpserver.HttpServer;
import lombok.Getter;
import lombok.Setter;
import main.java.Request.HTTPRequest;
import main.java.Response.HTTPResponse;
import main.java.header.HTTPHeaders;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Base class for creating HTTP connections.
 * Treats each request for e.g. a GET request
 * as a new HTTP connection.
 * This way we can have disposable connections
 * and can create connections based on our requirements.
 */

public class HTTPConnection {
    // Using HttpServer JDK Library
    private int port = 8989;
    private HttpServer server;

    //required parameters
    @Getter
    @Setter
    private HTTPRequest request;
    @Getter
    @Setter
    private HTTPResponse response;

    //optional parameters
    @Getter
    @Setter
    private HTTPHeaders headers;
    @Getter
    @Setter
    private String connectionPath;

    private HTTPConnection(HTTPConnectionBuilder builder) {
        this.request = builder.request;
        this.response = builder.response;
        this.headers = builder.headers;
        this.connectionPath = builder.connectionPath;
        this.server = builder.server;
    }

    public static class HTTPConnectionBuilder {
        private HttpServer server;
        private int port = 8989;
        // required parameters
        // Including these fields into required fields as every HTTP connection has at lease requests and responses.
        private HTTPRequest request;
        private HTTPResponse response;

        // optional parameters
        private HTTPHeaders headers;
        private String connectionPath;

        public HTTPConnectionBuilder() {
            try {
                server = HttpServer.create(new InetSocketAddress(port), 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.request = new HTTPRequest();
            this.response = new HTTPResponse();
        }

        public HTTPConnectionBuilder withHTTPHeaders() {
            this.headers = new HTTPHeaders();
            return this;
        }

        public HTTPConnectionBuilder withPath(String connectionPath){
            this.connectionPath = connectionPath;
            server.createContext(connectionPath, this.response);
            return this;
        }

        public HTTPConnection build() {
            server.setExecutor(null);
            server.start();
            return new HTTPConnection(this);
        }

    }

    public void Stop() {
        server.stop(0);
        System.out.println("server stopped");
    }

}

