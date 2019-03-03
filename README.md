# HTTP Server
A simple HTTP web server. It can be used to create HTTP connections depending upon the provided requirements. 

## How To Run
The app is made using Java and tested using JDK 1.8. Create a jar file from the source code and run directly. 

## Architecture
HTTPConnection (a builder class) is the main entry point in pursuit of creating HTTP connections or HTTP servers. It uses further subclasses (such as HTTPRequest, HTTPResponse etc.) in order to create a web-server based on the provided parameters. The big picture of the app can be described through the following picture:

![Alt text](static/linked.jpg?raw=true "Arch")

## Database
No Databases has been used

## How to use
The base builder class, i.e. (HTTPConnection) can be used to create an HTTP server depending upon the requirements. For e.g.:

```
HTTPConnection connection4 = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders(true)
                .withPath("/getWithETAG").withRequesttype(HTTPRequestType.GET).withETAG("Sample ETAG").build();
```

## Testing
The application can be tested using JUnit. All the tests present in test package have only written using JUnit.

## References
Some ideas about the source code have been taken from the following sources: 
- https://www.codeproject.com/Tips/1040097/Create-a-Simple-Web-Server-in-Java-HTTP-Server
- https://medium.com/@ssaurel/create-a-simple-http-web-server-in-java-3fc12b29d5fd