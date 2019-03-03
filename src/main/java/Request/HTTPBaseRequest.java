package main.java.Request;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.java.Response.HTTPResponse;

import java.io.IOException;
import java.io.OutputStream;

public class HTTPBaseRequest implements HttpHandler {

    private HTTPResponse response;

    public HTTPBaseRequest(HTTPResponse response) {
        this.response = response;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "this is a basic http response";
        this.response.responseString = response;
        writeOutputStream(httpExchange, response);
    }

    static void writeOutputStream(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
