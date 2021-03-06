package test.java.connection;

import main.java.Request.HTTPRequestType;
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
import static org.junit.Assert.assertNotNull;

public class HTTPConnectionTest {
    @Test
    public void testServer() {
        HTTPConnection connection = new HTTPConnection.HTTPConnectionBuilder().withPath("/").build();

        WebTarget target = getWebTarget();
        final Response response = target.request().get(Response.class);

        assertEquals(response.getStatus(), 200);

        connection.Stop();
    }

    @Test
    public void checkIfPathIsCorrect() {
        String path = "/root";
        HTTPConnection connection = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders(true).withPath(path).build();

        WebTarget target = getWebTarget();

        final Response response = target.path(path).request().get(Response.class);

        assertEquals(response.getStatus(), 200);

        connection.Stop();
    }

    @Test
    public void checkGetRequest() {
        String path = "/getRequest";
        HTTPConnection connection = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders(true)
                .withPath(path).withRequesttype(HTTPRequestType.GET).build();

        WebTarget target = getWebTarget();

        final Response response = target.path(path).queryParam("test", "test").request()
                .get(Response.class);

        assertEquals(response.getStatus(), 200);
        assertEquals("", "");

        connection.Stop();
    }

    @Test
    public void ShouldReturnETAGInResponse() {

        String path = "/getRequest";
        HTTPConnection connection = new HTTPConnection.HTTPConnectionBuilder().withHTTPHeaders(true)
                .withPath(path).withRequesttype(HTTPRequestType.GET).withETAG("Sample ETAG").build();

        WebTarget target = getWebTarget();

        final Response response = target.path(path).queryParam("test", "test").request()
                .get(Response.class);

        final String eTag = response.getHeaderString("ETag");

        assertEquals(response.getStatus(), 200);
        assertNotNull(eTag);
        connection.Stop();
    }

    private WebTarget getWebTarget() {
        // Using Jersey Libraries to test the connection.
        final ClientConfig config = new ClientConfig();
        final Client client = ClientBuilder.newClient(config);
        return client.target(getBaseURI());
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8989").build();
    }

}
