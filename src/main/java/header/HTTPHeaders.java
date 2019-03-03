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
    @Getter
    @Setter
    public String IfMatch;
    @Getter
    @Setter
    public String IfNoneMatch;
    @Getter
    @Setter
    public String IfModifiedSince;


    public HTTPHeaders() {
        this.headers = true;
    }
}
