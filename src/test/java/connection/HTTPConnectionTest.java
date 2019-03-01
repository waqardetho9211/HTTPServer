package test.java.connection;

import main.java.connection.HTTPConnection;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static org.junit.Assert.assertEquals;

public class HTTPConnectionTest {
    @Test
    public void testServer() {
        HTTPConnection connection = new HTTPConnection.HTTPConnectionBuilder().withPath("/").build();
        // Using Jersey Libraries to test the connection
        final ClientConfig config = new ClientConfig();
        final Client client = ClientBuilder.newClient(config);
        final WebTarget target = client.target(getBaseURI());

        final Response response = target.request().get(Response.class);

        assertEquals(response.getStatus(), 200);

        connection.Stop();
    }

    @Test
    public void checkIfPathIsCorrect() {
        String path = "/root";
        HTTPConnection connection = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders().withPath(path).build();

        // Using Jersey Libraries to test the connection.
        final ClientConfig config = new ClientConfig();
        final Client client = ClientBuilder.newClient(config);
        final WebTarget target = client.target(getBaseURI());

        final Response response = target.path(path).request().get(Response.class);

        assertEquals(response.getStatus(), 200);

        connection.Stop();
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8989").build();
    }

}
