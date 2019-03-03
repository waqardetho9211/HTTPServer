package main.java.header;

public class CacheHeaders {
    public static String attachETAGHeader(String response, HTTPHeaders headers){
        response += HeaderTypes.ETAG + " = " + headers.ETAG + "\n";
        return response;
    }
}
