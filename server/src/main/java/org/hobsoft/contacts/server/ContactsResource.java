package org.hobsoft.contacts.server;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("contacts")
public class ContactsResource
{
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response contacts()
	{
		InputStream in = getClass().getResourceAsStream("contacts.html");
		return Response.ok(in, MediaType.TEXT_HTML).build();
	}
}
