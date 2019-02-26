package test.java;

import main.java.NewHTTPServer;
import main.java.Path;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;
import org.reflections.Reflections;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Set;

import static org.junit.Assert.assertEquals;


public class TestPathForRequests {
    public static int port = 8989;
    private static final String path = "testPath";

    @Path(value = path)
    @Test
    public void checkIfPathIsCorrect() {
        NewHTTPServer httpServer = new NewHTTPServer();

        // Using Jersey Libraries to test the connection.
        final ClientConfig config = new ClientConfig();
        final Client client = ClientBuilder.newClient(config);
        final WebTarget target = client.target(getBaseURI());

        final Response response = target.path(path).request().get(Response.class);

        assertEquals(response.getStatus(), 200);
        httpServer.Stop();
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8989").build();
    }
}
