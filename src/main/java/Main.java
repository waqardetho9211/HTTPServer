package main.java;

import main.java.connection.HTTPConnection;

/**
 * A simple class to test the entire application
 */

public class Main {
    // Just to distinguish a request from a normal http request using a different port
    public static int port = 8989;

    public static void main(String[] args) {
        //NewHTTPServer httpServer = new NewHTTPServer();

        //HTTPConnection connection = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders().build();

        HTTPConnection connection2 = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders().withPath("/root").build();

        System.out.println(connection2);
    }
}
