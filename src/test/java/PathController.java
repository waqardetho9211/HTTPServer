package test.java;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/path")
public class PathController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResource(@Context Request request) {

		final EntityTag etag = new EntityTag(Integer.toString(1234));
		final ResponseBuilder builder = request.evaluatePreconditions(etag);
		if (builder != null) {
			return builder.build();
		}
		return Response.ok("HelloWorld").tag(etag).build();
	}
}
