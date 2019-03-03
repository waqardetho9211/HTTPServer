package main.java;

import main.java.Request.HTTPRequestType;
import main.java.connection.HTTPConnection;

/**
 * A simple class to test the application
 */

public class Main {
    // Just to distinguish a request from a normal http request using a different port
    public static int port = 8989;

    public static void main(String[] args) {
        HTTPConnection connection = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders().withPath("/").build();
        HTTPConnection connection2 = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders().withPath("/root")
                .build();
        HTTPConnection connection3 = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders()
                .withPath("/getRequest").withRequesttype(HTTPRequestType.GET).build();
        HTTPConnection connection4 = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders()
                .withPath("/getWithETAG").withRequesttype(HTTPRequestType.GET).withETAG("Sample ETAG").build();
    }
}
