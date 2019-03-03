package main.java.connection;

import com.sun.net.httpserver.HttpServer;
import main.java.Request.HTTPBaseRequest;
import main.java.Request.HTTPGetRequest;
import main.java.Request.HTTPRequestType;
import main.java.Response.HTTPResponse;

/**
 * Class to parse HTTP requests.
 */
class HTTPConnectionFactory {

    HttpServer getRequest(HttpServer server, HTTPRequestType type, HTTPResponse response, String connectionPath){
        if(type == null){
            server.createContext(connectionPath, new HTTPBaseRequest(response));
            return server;
        }
        if(type.equals(HTTPRequestType.GET)){
            server.createContext(connectionPath, new HTTPGetRequest(response));
            return server;

        } else if(type.equals(HTTPRequestType.HEADER)){
            return null;

        } else if(type.equals(HTTPRequestType.POST)){
            return null;
        }

        return null;
    }

}
