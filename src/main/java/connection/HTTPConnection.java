package main.java.connection;


import lombok.Getter;
import lombok.Setter;
import main.java.Request.HTTPRequest;
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
    private HTTPBaseConnection connection;


    private HTTPConnection(HTTPConnectionBuilder builder) {
        this.request = builder.request;
        this.response = builder.response;
        this.headers = builder.headers;
        this.connection = builder.connection;
    }

    public static class HTTPConnectionBuilder {
        private HTTPBaseConnection connection;

        // required parameters
        // Including these fields into required fields as every HTTP connection has at lease requests and responses.
        private HTTPRequest request;
        private HTTPResponse response;

        // optional parameters
        private HTTPHeaders headers;

        public HTTPConnectionBuilder() {
            this.request = new HTTPRequest();
            this.response = new HTTPResponse();
        }

        public HTTPConnectionBuilder withHTTPHeaders(boolean headers) {
            this.headers = new HTTPHeaders();
            return this;
        }

        public HTTPConnectionBuilder withPath(String connectionPath) {
            this.request.connectionPath = connectionPath;
            return this;
        }

        public HTTPConnectionBuilder withRequesttype(HTTPRequestType type) {
            this.request.httpRequestType = type;
            return this;
        }

        public HTTPConnection build() {
            this.connection = new HTTPBaseConnection();
            this.connection.setupRequest(this.request, this.response, this.headers);
            return new HTTPConnection(this);
        }

        public HTTPConnectionBuilder withETAG(String etag) {
            this.headers.ETAG = etag;
            return this;
        }
    }

    public void Stop() {
        this.connection.server.stop(0);
        System.out.println("server stopped");
    }

}

