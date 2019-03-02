package main.java.Request;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.Response.HTTPResponse;

import java.io.IOException;
import java.io.OutputStream;

public class HTTPBaseRequest implements HttpHandler {

    HTTPResponse response;

    public HTTPBaseRequest(HTTPResponse response) {
        this.response = response;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "this is a basic http response";
        this.response.setResponseString(response);
        writeOutputStream(httpExchange, response);
        return;
    }

    static void writeOutputStream(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
