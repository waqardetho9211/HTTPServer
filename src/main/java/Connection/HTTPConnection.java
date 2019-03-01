package main.java.Connection;


import lombok.Getter;
import lombok.Setter;
import main.java.Request.HTTPRequest;
import main.java.Response.HTTPResponse;
import main.java.header.HTTPHeaders;

/* Base class for creating HTTP connection.
 * Treats each request for e.g. a GET request
 * as a new HTTP connection.
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

    private HTTPConnection(ComputerBuilder builder) {
        this.request = builder.request;
        this.response = builder.response;
        this.headers = builder.headers;
        this.connectionPath = builder.connectionPath;
    }

    public static class ComputerBuilder {
        // required parameters
        // Including these fields into required fields as every HTTP connection has at lease requests and responses.
        private HTTPRequest request;
        private HTTPResponse response;

        // optional parameters
        private HTTPHeaders headers;
        private String connectionPath;

        public ComputerBuilder() {
            this.request = new HTTPRequest();
            this.response = new HTTPResponse();
        }

        public ComputerBuilder withHTTPHeaders() {
            this.headers = new HTTPHeaders();
            return this;
        }

        public ComputerBuilder withPath(String connectionPath){
            this.connectionPath = connectionPath;
            return this;
        }

        public HTTPConnection build() {
            return new HTTPConnection(this);
        }

    }

}

