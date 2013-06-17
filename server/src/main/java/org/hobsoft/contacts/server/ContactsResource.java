package org.hobsoft.contacts.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("contacts")
public class ContactsResource
{
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String contacts()
	{
		return "<html><body><h1>Contacts</h1></body></html>";
	}
}
