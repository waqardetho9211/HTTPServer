package main.java;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Base class to handle HTTP Request. In order to distinguish it from
 * HTTPServer JDK class the name is changed to NEWHTTPServer
 */

public class NewHTTPServer {
    private int port = 8989;
    private HttpServer server;

    public NewHTTPServer() {
        try {
            this.port = port;
            server = HttpServer.create(new InetSocketAddress(port), 0);
            System.out.println("server started at " + port);
            server.createContext(getPathFromTheRequest(), new Handlers.RootHandler());
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPathFromTheRequest() {
        String result = "";
        try {
            Class callerClass = getCallerClass(3);
            Path path = (Path) callerClass.getAnnotation(Path.class);
            result = ((path != null) ? path.value() : "/");
        } catch (ClassNotFoundException ex) {
            System.err.println("Cannot read provided server path");
        }
        return result;
    }

    public void Stop() {
        server.stop(0);
        System.out.println("server stopped");
    }


    /**
     * In order to read the path from the calling class
     * to create the server accordingly.
     * @param level
     * @return
     * @throws ClassNotFoundException
     */
    public static Class getCallerClass(int level) throws ClassNotFoundException {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        String rawFQN = stElements[level + 1].toString().split("\\(")[0];
        return Class.forName(rawFQN.substring(0, rawFQN.lastIndexOf('.')));
    }
}

