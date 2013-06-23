package org.hobsoft.contacts.server.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Viewable;

public class ContactsResource
{
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable get()
	{
		return new Viewable("index");
	}
}
