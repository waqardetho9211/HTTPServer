package main.java;

import main.java.Request.HTTPRequestType;
import main.java.connection.HTTPConnection;

/**
 * A simple class to test the application
 */

public class Main {
    public static void main(String[] args) {
//        HTTPConnection connection = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders(true).withPath("/").build();
//        HTTPConnection connection2 = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders(true).withPath("/root")
//                .build();
//        HTTPConnection connection3 = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders(true)
//                .withPath("/getRequest").withRequesttype(HTTPRequestType.GET).build();
        HTTPConnection connection4 = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders(true)
                .withPath("/getWithETAG").withRequesttype(HTTPRequestType.GET).withETAG("Sample ETAG").build();
        System.out.println(connection4);
    }
}
