package main.java.header;

import lombok.Getter;
import lombok.Setter;

public class HTTPHeaders {
    @Getter
    @Setter
    public String ETAG;
    @Getter
    @Setter
    public boolean headers;


    public HTTPHeaders() {
        this.headers = true;
    }
}
