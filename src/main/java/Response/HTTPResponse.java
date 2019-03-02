package main.java.Response;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.OutputStream;

public class HTTPResponse {
    @Getter
    @Setter
    private String responseString;

    public void setResponseString(String response) {
        this.responseString = response;
    }
}
