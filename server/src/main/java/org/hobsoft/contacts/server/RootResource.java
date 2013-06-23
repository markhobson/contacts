package org.hobsoft.contacts.server;

import javax.ws.rs.Path;

@Path("/")
public class RootResource
{
	@Path("contacts")
	public ContactsResource contacts()
	{
		return new ContactsResource();
	}
}
