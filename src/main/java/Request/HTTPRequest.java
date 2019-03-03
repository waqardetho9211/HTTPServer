package main.java.Request;

import lombok.Getter;
import lombok.Setter;

public class HTTPRequest {
    @Getter
    @Setter
    public String connectionPath;
    @Getter
    @Setter
    public HTTPRequestType httpRequestType;
    public HTTPRequest() {}

}
