package main.java.Response;

import lombok.Getter;
import lombok.Setter;

public class HTTPResponse {
    @Getter
    @Setter
    private String responseString;

    public void setResponseString(String response) {
        this.responseString = response;
    }
}
