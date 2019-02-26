package test.java;

import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static org.junit.Assert.assertEquals;

public class NewHTTPServerTest {
    @Test
    public void testServer() {
        final ClientConfig config = new ClientConfig();
        final Client client = ClientBuilder.newClient(config);
        final WebTarget target = client.target(getBaseURI());

        final Response response = target.request().get(Response.class);

        assertEquals(response.getStatus(), 200);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8989").build();
    }

}