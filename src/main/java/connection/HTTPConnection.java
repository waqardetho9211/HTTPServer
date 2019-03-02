package main.java.connection;


import com.sun.net.httpserver.HttpServer;
import lombok.Getter;
import lombok.Setter;
import main.java.Request.HTTPRequest;
import main.java.Request.HTTPRequestFactory;
import main.java.Request.HTTPRequestType;
import main.java.Response.HTTPResponse;
import main.java.header.HTTPHeaders;

/**
 * Base class for creating HTTP connections.
 * Treats each request for e.g. a GET request
 * as a new HTTP connection.
 * This way we can have disposable connections
 * and can create connections based on our requirements.
 */

public class HTTPConnection {

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
    @Getter
    @Setter
    private HTTPRequestType httpRequestType;


    private HTTPConnection(HTTPConnectionBuilder builder) {
        this.request = builder.request;
        this.response = builder.response;
        this.headers = builder.headers;
        this.connectionPath = builder.connectionPath;
        this.httpRequestType = builder.httpRequestType;
    }

    public static class HTTPConnectionBuilder {
        // required parameters
        // Including these fields into required fields as every HTTP connection has at lease requests and responses.
        private HTTPRequest request;
        private HTTPResponse response;

        // optional parameters
        private HTTPHeaders headers;
        private String connectionPath;
        private HTTPRequestType httpRequestType;

        public HTTPConnectionBuilder() {
            this.response = new HTTPResponse();
        }

        public HTTPConnectionBuilder withHTTPHeaders() {
            this.headers = new HTTPHeaders();
            return this;
        }

        public HTTPConnectionBuilder withPath(String connectionPath) {
            this.connectionPath = connectionPath;

            return this;
        }
        public HTTPConnectionBuilder withRequesttype(HTTPRequestType type) {
            this.httpRequestType = httpRequestType;
            return this;
        }

        public HTTPConnection build() {
            this.request = new HTTPRequest();
            this.request.setupRequest(this.connectionPath, this.httpRequestType, this.response);
            return new HTTPConnection(this);
        }
    }

    public void Stop() {
        this.request.server.stop(0);
        System.out.println("server stopped");
    }

}

